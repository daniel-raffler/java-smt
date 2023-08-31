/*
 *  JavaSMT is an API wrapper for a collection of SMT solvers.
 *  This file is part of JavaSMT.
 *
 *  Copyright (C) 2007-2016  Dirk Beyer
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.sosy_lab.java_smt.solvers.bitwuzla;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.LongStream;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.FormulaType;
import org.sosy_lab.java_smt.api.FormulaType.FloatingPointType;
import org.sosy_lab.java_smt.api.FunctionDeclarationKind;
import org.sosy_lab.java_smt.api.QuantifiedFormulaManager.Quantifier;
import org.sosy_lab.java_smt.api.visitors.FormulaVisitor;
import org.sosy_lab.java_smt.basicimpl.FormulaCreator;
import org.sosy_lab.java_smt.basicimpl.FunctionDeclarationImpl;
import org.sosy_lab.java_smt.solvers.bitwuzla.BitwuzlaFormula.BitwuzlaBooleanFormula;
import static org.sosy_lab.java_smt.solvers.bitwuzla.BitwuzlaKind.*;

public class BitwuzlaFormulaCreator extends FormulaCreator<Long, Long, Long, Long> {
  private final Table<String, Long, Long> formulaCache = HashBasedTable.create();
  protected BitwuzlaFormulaCreator(Long pBitwuzlaEnv) {
    super(pBitwuzlaEnv, bitwuzlaJNI.bitwuzla_mk_bool_sort(), null, null, null, null);
  }

  @Override
  public Long getBitvectorType(int bitwidth) {
    return bitwuzlaJNI.bitwuzla_mk_bv_sort( bitwidth);
  }

  // Assuming that JavaSMT FloatingPointType follows IEEE 754, if it is in the decimal
  // system instead use bitwuzla_mk_fp_value_from_real somehow or convert myself
  @Override
  public Long getFloatingPointType(FloatingPointType type) {
    long fpSort =
        bitwuzlaJNI.bitwuzla_mk_fp_sort( type.getExponentSize(), type.getMantissaSize());
    return fpSort;
  }

  @Override
  public Long getArrayType(Long indexType, Long elementType) {
    return bitwuzlaJNI.bitwuzla_mk_array_sort( indexType, elementType);
  }

  @Override
  public Long makeVariable(Long pLong, String varName) {
    Long maybeFormula = formulaCache.get(varName, pLong);
    if (maybeFormula != null) {
      return maybeFormula;
    }
    if (formulaCache.containsRow(varName)) {
      throw new IllegalArgumentException("Symbol already used: " + varName);
    }
    long newVar = bitwuzlaJNI.bitwuzla_mk_const( pLong, varName);
    formulaCache.put(varName, pLong, newVar);
    return newVar;
  }

  // TODO What about function types? BW has function Sorts. bitwuzla_sort_is_uninterpreted() in
  //  doc, but not in bitwuzla.h for the C API?
  public FormulaType<? extends Formula> bitwuzlaSortToType(Long pSort) {
    if (bitwuzlaJNI.bitwuzla_sort_is_fp(pSort)) {
      long exponent = bitwuzlaJNI.bitwuzla_sort_fp_get_exp_size(pSort);
      long mantissa = bitwuzlaJNI.bitwuzla_sort_fp_get_sig_size(pSort);
      return FormulaType.getFloatingPointType((int) exponent, (int) mantissa);
    } else if (bitwuzlaJNI.bitwuzla_sort_is_bv(pSort)) {
      return FormulaType.getBitvectorTypeWithSize(
          (int) bitwuzlaJNI.bitwuzla_sort_bv_get_size(pSort));
    } else if (bitwuzlaJNI.bitwuzla_sort_is_array(pSort)) {
      FormulaType<? extends Formula> domainSort =
          bitwuzlaSortToType(bitwuzlaJNI.bitwuzla_term_array_get_index_sort(pSort));
      FormulaType<? extends Formula> rangeSort =
          bitwuzlaSortToType(bitwuzlaJNI.bitwuzla_term_array_get_index_sort(pSort));
      return FormulaType.getArrayType(domainSort, rangeSort);
    } else {
      throw new UnsupportedOperationException("Unsupported Formulatype.");
    }
  }

  private <R> R visitKind(FormulaVisitor<R> visitor, Formula formula, Long f) {
    BitwuzlaKind kind = BitwuzlaKind.swigToEnum(bitwuzlaJNI.bitwuzla_term_get_kind(f));


    // filled later, except for some special function applications
    String functionName = null;
    List<Formula> functionArgs = new ArrayList<>();
    List<FormulaType<?>> argTypes = new ArrayList<>();

    // filled directly when handling the function application
    final FunctionDeclarationKind functionKind;
    long[] childrenSize = new long[1];

    if (kind.equals(BITWUZLA_KIND_CONST_ARRAY)) {
      //TODO: How to visit const array?
    } else if (kind.equals(BITWUZLA_KIND_VARIABLE)) {
      visitor.visitBoundVariable(formula, 0);
    } else if (kind.equals(BITWUZLA_KIND_AND)) {
      functionKind = FunctionDeclarationKind.AND;
    } else if (kind.equals(BITWUZLA_KIND_DISTINCT)) {
      functionKind = FunctionDeclarationKind.DISTINCT;
    } else if (kind.equals(BITWUZLA_KIND_EQUAL)) {
      functionKind = FunctionDeclarationKind.EQ;
    } else if (kind.equals(BITWUZLA_KIND_IFF)) {
      functionKind = FunctionDeclarationKind.IFF;
    } else if (kind.equals(BITWUZLA_KIND_IMPLIES)) {
      functionKind = FunctionDeclarationKind.IMPLIES;
    } else if (kind.equals(BITWUZLA_KIND_NOT)) {
      functionKind = FunctionDeclarationKind.NOT;
    } else if (kind.equals(BITWUZLA_KIND_OR)) {
      functionKind = FunctionDeclarationKind.OR;
    } else if (kind.equals(BITWUZLA_KIND_XOR)) {
      functionKind = FunctionDeclarationKind.XOR;
    } else if (kind.equals(BITWUZLA_KIND_ITE)) {
      functionKind = FunctionDeclarationKind.ITE;
    } else if (kind.equals(BITWUZLA_KIND_EXISTS)) {
      visitor.visitQuantifier()
    } else if (kind.equals(BITWUZLA_KIND_FORALL)) {
      sfas
    } else if (kind.equals(BITWUZLA_KIND_APPLY)) {
      functionKind = FunctionDeclarationKind.;
    } else if (kind.equals(BITWUZLA_KIND_LAMBDA)) {
      sfdg
    } else if (kind.equals(BITWUZLA_KIND_ARRAY_SELECT)) {
      functionKind = FunctionDeclarationKind.SELECT;
    } else if (kind.equals(BITWUZLA_KIND_ARRAY_STORE)) {
      functionKind = FunctionDeclarationKind.STORE;
    } else if (kind.equals(BITWUZLA_KIND_BV_ADD)) {
      functionKind = FunctionDeclarationKind.BV_ADD;
    } else if (kind.equals(BITWUZLA_KIND_BV_AND)) {
      functionKind = FunctionDeclarationKind.BV_AND;
    } else if (kind.equals(BITWUZLA_KIND_BV_ASHR)) {
      functionKind = FunctionDeclarationKind.BV_ASHR;
    } else if (kind.equals(BITWUZLA_KIND_BV_COMP)) {
      functionKind = FunctionDeclarationKind.BVC;
    } else if (kind.equals(BITWUZLA_KIND_BV_CONCAT)) {
      functionKind = FunctionDeclarationKind.BV_CONCAT;
    } else if (kind.equals(BITWUZLA_KIND_BV_DEC)) {
      functionKind = FunctionDeclarationKind.BV_D;
    } else if (kind.equals(BITWUZLA_KIND_BV_INC)) {
      functionKind = FunctionDeclarationKind.BV_INC;
    } else if (kind.equals(BITWUZLA_KIND_BV_MUL)) {
      functionKind = FunctionDeclarationKind.BV_MUL;
    } else if (kind.equals(BITWUZLA_KIND_BV_NAND)) {
      functionKind = FunctionDeclarationKind.BVN;
    } else if (kind.equals(BITWUZLA_KIND_BV_NEG)) {
      functionKind = FunctionDeclarationKind.BV_NEG;
    } else if (kind.equals(BITWUZLA_KIND_BV_NOR)) {
      functionKind = FunctionDeclarationKind.BVN;
    } else if (kind.equals(BITWUZLA_KIND_BV_NOT)) {
      functionKind = FunctionDeclarationKind.BV_NOT;
    } else if (kind.equals(BITWUZLA_KIND_BV_OR)) {
      functionKind = FunctionDeclarationKind.BV_OR;
    } else if (kind.equals(BITWUZLA_KIND_BV_REDAND)) {
      functionKind = FunctionDeclarationKind.RED;
    } else if (kind.equals(BITWUZLA_KIND_BV_REDOR)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_REDXOR)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_ROL)) {
      functionKind = FunctionDeclarationKind.ROL;
    } else if (kind.equals(BITWUZLA_KIND_BV_ROR)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_SADD_OVERFLOW)) {
      functionKind = FunctionDeclarationKind.OVERFl;
    } else if (kind.equals(BITWUZLA_KIND_BV_SDIV_OVERFLOW)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_SDIV)) {
      functionKind = FunctionDeclarationKind.BV_SDIV;
    } else if (kind.equals(BITWUZLA_KIND_BV_SGE)) {
      functionKind = FunctionDeclarationKind.BV_SGE;
    } else if (kind.equals(BITWUZLA_KIND_BV_SGT)) {
      functionKind = FunctionDeclarationKind.BV_SGT;
    } else if (kind.equals(BITWUZLA_KIND_BV_SHL)) {
      functionKind = FunctionDeclarationKind.BV_SHL;
    } else if (kind.equals(BITWUZLA_KIND_BV_SHR)) {
      functionKind = FunctionDeclarationKind.SHR;
    } else if (kind.equals(BITWUZLA_KIND_BV_SLE)) {
      functionKind = FunctionDeclarationKind.BV_SLE;
    } else if (kind.equals(BITWUZLA_KIND_BV_SLT)) {
      functionKind = FunctionDeclarationKind.BV_SLT;
    } else if (kind.equals(BITWUZLA_KIND_BV_SMOD)) {
      functionKind = FunctionDeclarationKind.BVM;
    } else if (kind.equals(BITWUZLA_KIND_BV_SMUL_OVERFLOW)) {
      functionKind = FunctionDeclarationKind.MU;
    } else if (kind.equals(BITWUZLA_KIND_BV_SREM)) {
      functionKind = FunctionDeclarationKind.SR;
    } else if (kind.equals(BITWUZLA_KIND_BV_SSUB_OVERFLOW)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_SUB)) {
      functionKind = FunctionDeclarationKind.BV_SUB;
    } else if (kind.equals(BITWUZLA_KIND_BV_UADD_OVERFLOW)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_UDIV)) {
      functionKind = FunctionDeclarationKind.BV_UDIV;
    } else if (kind.equals(BITWUZLA_KIND_BV_UGE)) {
      functionKind = FunctionDeclarationKind.BV_UGE;
    } else if (kind.equals(BITWUZLA_KIND_BV_UGT)) {
      functionKind = FunctionDeclarationKind.BV_UGT;
    } else if (kind.equals(BITWUZLA_KIND_BV_ULE)) {
      functionKind = FunctionDeclarationKind.BV_ULE;
    } else if (kind.equals(BITWUZLA_KIND_BV_ULT)) {
      functionKind = FunctionDeclarationKind.BV_ULT;
    } else if (kind.equals(BITWUZLA_KIND_BV_UMUL_OVERFLOW)) {

    } else if (kind.equals(BITWUZLA_KIND_BV_UREM)) {
      functionKind = FunctionDeclarationKind.BV_UREM;
    } else if (kind.equals(BITWUZLA_KIND_BV_USUB_OVERFLOW)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_XNOR)) {
      functionKind = FunctionDeclarationKind.XNOR;
    } else if (kind.equals(BITWUZLA_KIND_BV_XOR)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_EXTRACT)) {
      functionKind = FunctionDeclarationKind.BV_EXTRACT;
    } else if (kind.equals(BITWUZLA_KIND_BV_REPEAT)) {
      functionKind = FunctionDeclarationKind.REPEA;
    } else if (kind.equals(BITWUZLA_KIND_BV_ROLI)) {
      functionKind = FunctionDeclarationKind.ROLI;
    } else if (kind.equals(BITWUZLA_KIND_BV_RORI)) {
    } else if (kind.equals(BITWUZLA_KIND_BV_SIGN_EXTEND)) {
      functionKind = FunctionDeclarationKind.BV_SIGN_EXTENSION;
    } else if (kind.equals(BITWUZLA_KIND_BV_ZERO_EXTEND)) {
      functionKind = FunctionDeclarationKind.BV_ZERO_EXTENSION;
    } else if (kind.equals(BITWUZLA_KIND_FP_ABS)) {
      functionKind = FunctionDeclarationKind.FP_ABS;
    } else if (kind.equals(BITWUZLA_KIND_FP_ADD)) {
      functionKind = FunctionDeclarationKind.FP_ADD;
    } else if (kind.equals(BITWUZLA_KIND_FP_DIV)) {
      functionKind = FunctionDeclarationKind.FP_DIV;
    } else if (kind.equals(BITWUZLA_KIND_FP_EQUAL)) {
      functionKind = FunctionDeclarationKind.FP_EQ;
    } else if (kind.equals(BITWUZLA_KIND_FP_FMA)) {
      functionKind = FunctionDeclarationKind.FMA
    } else if (kind.equals(BITWUZLA_KIND_FP_FP)) {
    } else if (kind.equals(BITWUZLA_KIND_FP_GEQ)) {
      functionKind = FunctionDeclarationKind.FP_GE;
    } else if (kind.equals(BITWUZLA_KIND_FP_GT)) {
      functionKind = FunctionDeclarationKind.GT;
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_INF)) {
      functionKind = FunctionDeclarationKind.FP_IS_INF;
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_NAN)) {
      functionKind = FunctionDeclarationKind.FP_IS_NAN;
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_NEG)) {
      functionKind = FunctionDeclarationKind.FP_IS_NEGATIVE;
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_NORMAL)) {
      functionKind = FunctionDeclarationKind.FP_IS_NORMAL;
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_POS)) {
      functionKind = FunctionDeclarationKind.FP_is
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_SUBNORMAL)) {
      functionKind = FunctionDeclarationKind.FP_IS_SUBNORMAL;
    } else if (kind.equals(BITWUZLA_KIND_FP_IS_ZERO)) {
      functionKind = FunctionDeclarationKind.FP_IS_ZERO;
    } else if (kind.equals(BITWUZLA_KIND_FP_LEQ)) {
      functionKind = FunctionDeclarationKind.FP_LE;
    } else if (kind.equals(BITWUZLA_KIND_FP_LT)) {
      functionKind = FunctionDeclarationKind.FP_LT;
    } else if (kind.equals(BITWUZLA_KIND_FP_MAX)) {
      functionKind = FunctionDeclarationKind.FP_MAX;
    } else if (kind.equals(BITWUZLA_KIND_FP_MIN)) {
      functionKind = FunctionDeclarationKind.FP_MIN;
    } else if (kind.equals(BITWUZLA_KIND_FP_MUL)) {
      functionKind = FunctionDeclarationKind.FP_MUL;
    } else if (kind.equals(BITWUZLA_KIND_FP_NEG)) {
      functionKind = FunctionDeclarationKind.FP_IS_NEGATIVE;
    } else if (kind.equals(BITWUZLA_KIND_FP_REM)) {
      functionKind = FunctionDeclarationKind.REM
    } else if (kind.equals(BITWUZLA_KIND_FP_RTI)) {
      functionKind = FunctionDeclarationKind.FP_ROUND_TO_INTEGRAL;
    } else if (kind.equals(BITWUZLA_KIND_FP_SQRT)) {
      functionKind = FunctionDeclarationKind.FP_SQRT;
    } else if (kind.equals(BITWUZLA_KIND_FP_SUB)) {
      functionKind = FunctionDeclarationKind.FP_SUB;
    } else if (kind.equals(BITWUZLA_KIND_FP_TO_FP_FROM_BV)) {

    } else if (kind.equals(BITWUZLA_KIND_FP_TO_FP_FROM_FP)) {
      functionKind = FunctionDeclarationKind.FP_CASTTO_FP;
    } else if (kind.equals(BITWUZLA_KIND_FP_TO_FP_FROM_SBV)) {

    } else if (kind.equals(BITWUZLA_KIND_FP_TO_FP_FROM_UBV)) {

    } else if (kind.equals(BITWUZLA_KIND_FP_TO_SBV)) {
      functionKind = FunctionDeclarationKind.FP_CASTTO_SBV;
    } else if (kind.equals(BITWUZLA_KIND_FP_TO_UBV)) {
      functionKind = FunctionDeclarationKind.FP_CASTTO_UBV;
    } else if (kind.equals(BITWUZLA_KIND_NUM_KINDS)) {
      functionKind = FunctionDeclarationKind.NU
    }

    if (functionName == null) {
    functionName = functionKind.toString();
  }
    if (functionArgs.isEmpty()) {
      long pfunctionArgs = bitwuzlaJNI.bitwuzla_term_get_children(f, childrenSize);
      for (int i = 0; i<childrenSize[0]; i++) {
        long currentTerm = bitwuzlaJNI.BitwuzlaTermArray_getitem(pfunctionArgs, i);
        FormulaType<? extends Formula> currentType = bitwuzlaSortToType(currentTerm);
        argTypes.add(currentType);
        functionArgs.add(encapsulate(currentType, currentTerm));
      }
  }

    Preconditions.checkState(
        functionArgs.size() == argTypes.size(),
        "different size of args (%s) and their types (%s) in term %s",
  functionArgs,
  argTypes,
  f);


    return visitor.visitFunction(
        formula,
        functionArgs,
        FunctionDeclarationImpl.of(
          functionName, functionKind, argTypes, getFormulaType(f), kind));
  }
  @Override
  public FormulaType<?> getFormulaType(Long formula) {
    long pType = bitwuzlaJNI.bitwuzla_term_get_sort(formula);
    return bitwuzlaSortToType(pType);
  }

  /**
   * @param visitor
   * @param formula
   * @param f
   * @see FormulaManager#visit
   */

  @Override
  public <R> R visit(FormulaVisitor<R> visitor, Formula formula, Long f) {
    BitwuzlaKind kind = BitwuzlaKind.swigToEnum(bitwuzlaJNI.bitwuzla_term_get_kind(f));
    if (bitwuzlaJNI.bitwuzla_term_is_fun(f) || bitwuzlaJNI.bitwuzla_term_is_uninterpreted(f)) {
      visitor.visitFunction();
    } else if (bitwuzlaJNI.bitwuzla_term_is_bv_value(f)) {
      visitor.visitConstant(formula,
          new BigInteger(bitwuzlaJNI.bitwuzla_term_value_get_str(f, 10)));
    } else if (bitwuzlaJNI.bitwuzla_term_is_fp_value(f)) {
      BigDecimal.valueOf("sd", 5);
      // The Bitwuzla string for FPs is always in binary, regardless of the second argument.
      String fp = bitwuzlaJNI.bitwuzla_term_value_get_str(f, 2);
      Long.parseLong(bitwuzlaJNI.bitwuzla_term_value_get_str(f, 2), 2);
      visitor.visitConstant(formula,
          Double.valueOf(bitwuzlaJNI.bitwuzla_term_value_get_str(f, 10)));
    } else if (bitwuzlaJNI.bitwuzla_term_is_array(f)) {
        visitor.
    } else if (kind == BitwuzlaKind.BITWUZLA_KIND_EXISTS) {
      // TODO I hope this works. Not quite sure if get_children always returns the body as second
      //  list entry.
      visitor.visitQuantifier((BooleanFormula) formula, Quantifier.EXISTS,
          Collections.emptyList(),
          new BitwuzlaBooleanFormula(bitwuzlaJNI.BitwuzlaTermArray_getitem(bitwuzlaJNI.bitwuzla_term_get_children(f, null), 1)));
      bitwuzlaJNI.term_get
    } else if (kind == BitwuzlaKind.BITWUZLA_KIND_EXISTS){

      if(bitwuzlaJNI.bitwuzla_term_is_bool(f)){
        visitor.visitFreeVariable(formula, bitwuzlaJNI.bitwuzla_term_get_symbol(f));
      }
    } else if (bitwuzlaJNI.bitwuzla_term_is_var(f)) {
      bj.var
      visitor.visitBoundVariable(f, )
    } else if (bitwuzlaJNI.bitwuzla_term_is_fun(f)) {
      visitor.visitFunction()
    } else {

      if (kind == BitwuzlaKind.BITWUZLA_KIND_EXISTS || kind == BitwuzlaKind.BITWUZLA_KIND_FORALL){
        Quantifier forall = Quantifier.FORALL;
        visitor.visitQuantifier(f)
      }
      String name = kind.toString();
    }
    return null;
  }

  @Override
  public Long callFunctionImpl(Long declaration, List<Long> args) {
    long[] functionAndArgs =
        LongStream.concat(LongStream.of(declaration), args.stream().mapToLong(Long::longValue))
            .toArray();
    return bitwuzlaJNI.bitwuzla_mk_term(
        BitwuzlaKind.BITWUZLA_KIND_APPLY.swigValue(), args.size(), functionAndArgs);

//    return bitwuzlaJNI.bitwuzla_mk_term(declaration.intValue(), args.size(),
//        args.stream().mapToLong(Long::longValue).toArray());
  }

  @Override
  public Long declareUFImpl(String name, Long pReturnType, List<Long> pArgTypes) {
    long functionSort = bitwuzlaJNI.bitwuzla_mk_fun_sort(pArgTypes.size(),
        pArgTypes.stream().mapToLong(Long::longValue).toArray(), pReturnType);

    Long maybeFormula = formulaCache.get(name, functionSort);
    if (maybeFormula != null) {
      return maybeFormula;
    }
    if (formulaCache.containsRow(name)) {
      throw new IllegalArgumentException("Symbol already used: " + name);
    }
    long uf = bitwuzlaJNI.bitwuzla_mk_const(functionSort, name);
    formulaCache.put(name, functionSort, uf);
    return uf;
  }

  // TODO: What to do with the input?
  @Override
  protected Long getBooleanVarDeclarationImpl(Long pLong) {
    long boolSort = bitwuzlaJNI.bitwuzla_mk_bool_sort();
    return null;
  }

  @Override
  public Long extractInfo(Formula pT) {
    return BitwuzlaFormulaManager.getBitwuzlaTerm(pT);
  }

  @Override
  public BooleanFormula encapsulateBoolean(Long pTerm) {
    assert getFormulaType(pTerm).isBooleanType();
    return new BitwuzlaBooleanFormula(pTerm);
  }

  protected Table<String, Long, Long> getCache() {
    return formulaCache;
  }

  // True if the entered String has an existing variable in the cache.
  protected boolean formulaCacheContains(String variable) {
    // There is always only 1 type permitted per variable
    return formulaCache.containsRow(variable);
  }

  // Optional that contains the variable to the entered String if there is one.
  protected Optional<Long> getFormulaFromCache(String variable) {
    Iterator<Entry<Long, Long>> entrySetIter =
        formulaCache.row(variable).entrySet().iterator();
    if (entrySetIter.hasNext()) {
      // If there is a non empty row for an entry, there is only one entry
      return Optional.of(entrySetIter.next().getValue());
    }
    return Optional.empty();
  }
}