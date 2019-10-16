/*
 *  JavaSMT is an API wrapper for a collection of SMT solvers.
 *  This file is part of JavaSMT.
 *
 *  Copyright (C) 2007-2019  Dirk Beyer
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
package org.sosy_lab.java_smt.solvers.yices2;



import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_ABS;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_APP_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_ARITH_CONST;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_ARITH_GE_ATOM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_ARITH_SUM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BIT_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BOOL_CONST;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_ARRAY;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_ASHR;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_CONST;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_DIV;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_GE_ATOM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_LSHR;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_REM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_SDIV;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_SGE_ATOM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_SHL;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_SMOD;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_SREM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_BV_SUM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_CEIL;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_DISTINCT_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_DIVIDES_ATOM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_EQ_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_FLOOR;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_IDIV;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_IMOD;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_IS_INT_ATOM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_ITE_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_NOT_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_OR_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_POWER_PRODUCT;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_RDIV;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_SELECT_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_UNINTERPRETED_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_VARIABLE;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.YICES_XOR_TERM;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_abs;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_application;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_arith_geq_atom;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bool_const_value;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bool_type;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bv_const_value;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bv_type;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvashr;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvconst_from_array;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvdiv;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvge_atom;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvlshr;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvrem;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvsdiv;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvsge_atom;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvshl;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvsmod;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvsrem;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvsum;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvsum_component;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_bvtype_size;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_ceil;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_distinct;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_divides_atom;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_division;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_eq;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_false;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_floor;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_function_type;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_get_term_name;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_idiv;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_imod;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_int_type;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_is_int_atom;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_ite;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_named_variable;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_not;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_or;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_parse_rational;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_product;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_product_component;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_proj_arg;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_proj_index;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_rational_const_value;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_real_type;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_sum;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_sum_component;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_bitsize;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_child;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_constructor;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_is_bitvector;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_is_bool;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_is_int;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_is_projection;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_is_real;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_num_children;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_term_to_string;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_true;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_type_is_bitvector;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_type_of_term;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_type_to_string;
import static org.sosy_lab.java_smt.solvers.yices2.Yices2NativeApi.yices_xor;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sosy_lab.common.rationals.Rational;
import org.sosy_lab.java_smt.api.BitvectorFormula;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.FormulaType;
import org.sosy_lab.java_smt.api.FormulaType.FloatingPointType;
import org.sosy_lab.java_smt.api.FunctionDeclarationKind;
import org.sosy_lab.java_smt.api.visitors.FormulaVisitor;
import org.sosy_lab.java_smt.basicimpl.FormulaCreator;
import org.sosy_lab.java_smt.basicimpl.FunctionDeclarationImpl;
import org.sosy_lab.java_smt.solvers.yices2.Yices2Formula.Yices2BitvectorFormula;
import org.sosy_lab.java_smt.solvers.yices2.Yices2Formula.Yices2BooleanFormula;
import org.sosy_lab.java_smt.solvers.yices2.Yices2Formula.Yices2IntegerFormula;
import org.sosy_lab.java_smt.solvers.yices2.Yices2Formula.Yices2RationalFormula;

public class Yices2FormulaCreator extends FormulaCreator<Integer, Integer, Long, Integer> {

  protected Yices2FormulaCreator(long env) {
    super(env, yices_bool_type(), yices_int_type(), yices_real_type());
  }

  @Override
  public Integer getBitvectorType(int pBitwidth) {
    return yices_bv_type(pBitwidth);
  }

  @Override
  public Integer getFloatingPointType(FloatingPointType pType) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Integer getArrayType(Integer pIndexType, Integer pElementType) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Integer makeVariable(Integer pType, String pVarName) {
    return yices_named_variable(pType, pVarName);
  }

  @Override
  public Integer extractInfo(Formula pT) {
    return Yices2FormulaManager.getYicesTerm(pT);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends Formula> T encapsulate(FormulaType<T> pType, Integer pTerm) {
    assert pType.equals(getFormulaType(pTerm))
            || (pType.equals(FormulaType.RationalType)
                && getFormulaType(pTerm).equals(FormulaType.IntegerType))
        : String.format(
            "Trying to encapsulate formula %s of type %s as %s",
            yices_term_to_string(pTerm), getFormulaType(pTerm), pType);
    if (pType.isBooleanType()) {
      return (T) new Yices2BooleanFormula(pTerm);
    } else if (pType.isIntegerType()) {
      return (T) new Yices2IntegerFormula(pTerm);
    } else if (pType.isRationalType()) {
      return (T) new Yices2RationalFormula(pTerm);
    } else if (pType.isBitvectorType()) {
      return (T) new Yices2BitvectorFormula(pTerm);
    }
    throw new IllegalArgumentException("Cannot create formulas of type " + pType + " in Yices");
  }

  @Override
  public BooleanFormula encapsulateBoolean(Integer pTerm) {
    assert getFormulaType(pTerm).isBooleanType();
    return new Yices2BooleanFormula(pTerm);
  }

  @Override
  public BitvectorFormula encapsulateBitvector(Integer pTerm) {
    assert getFormulaType(pTerm).isBitvectorType();
    return new Yices2BitvectorFormula(pTerm);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends Formula> FormulaType<T> getFormulaType(T pFormula) {
    if (pFormula instanceof BitvectorFormula) {
      int type = yices_type_of_term(extractInfo(pFormula));
      return (FormulaType<T>) FormulaType.getBitvectorTypeWithSize(yices_bvtype_size(type));
    } else {
      return super.getFormulaType(pFormula);
    }
  }

  @Override
  public FormulaType<?> getFormulaType(Integer pFormula) {
    if (yices_term_is_bool(pFormula)) {
      return FormulaType.BooleanType;
    } else if (yices_term_is_int(pFormula)) {
      return FormulaType.IntegerType;
    } else if (yices_term_is_real(pFormula)) {
      return FormulaType.RationalType;
    } else if (yices_term_is_bitvector(pFormula)) {
      return FormulaType.getBitvectorTypeWithSize(yices_term_bitsize(pFormula));
    }
    throw new IllegalArgumentException(
        String.format(
            "Unknown formula type '%s' for formula '%s'",
            yices_type_to_string(yices_type_of_term(pFormula)), yices_term_to_string(pFormula)));
  }

  // TODO Visit fails when encountering YICES_BV_ARRAY that is not a bv extend/multiply or
  // YICES_BIT_TERM
  @Override
  public <R> R visit(FormulaVisitor<R> pVisitor, Formula pFormula, Integer pF) {
    int constructor = yices_term_constructor(pF);
    int functionDeclaration = -constructor;
    switch (constructor) {
      case YICES_BOOL_CONST:
        return pVisitor.visitConstant(pFormula, yices_bool_const_value(pF));
      case YICES_ARITH_CONST:
        return pVisitor.visitConstant(pFormula, convertValue(pF, pF));
      case YICES_BV_CONST:
        return pVisitor.visitConstant(pFormula, convertValue(pF, pF));
      case YICES_UNINTERPRETED_TERM:
        return pVisitor.visitFreeVariable(pFormula, yices_get_term_name(pF));
      default:
        final FunctionDeclarationKind kind = getDeclarationKind(pF);
        final ImmutableList.Builder<Formula> args = ImmutableList.builder();
        final ImmutableList.Builder<FormulaType<?>> argTypes = ImmutableList.builder();
        final boolean isAnd = kind == FunctionDeclarationKind.AND && isNestedConjunction(pF);
        final boolean isUF = kind == FunctionDeclarationKind.UF;
        final boolean isSum = kind == FunctionDeclarationKind.ADD;
        final boolean isBvAdd = kind == FunctionDeclarationKind.BV_ADD;
        final boolean isBvMul = kind == FunctionDeclarationKind.BV_MUL;
        final boolean isMultiply = kind == FunctionDeclarationKind.MUL;
        final boolean isExtend =
            (kind == FunctionDeclarationKind.BV_SIGN_EXTENSION)
                || (kind == FunctionDeclarationKind.BV_ZERO_EXTENSION);
        System.out.println("DeclarationKind is: " + kind.toString());
        System.out.println("Term is: " + yices_term_to_string(pF));
        List<Integer> yicesArgs;
        String name;
        if (isAnd) {
          name = FunctionDeclarationKind.AND.toString();
          yicesArgs = getNestedConjunctionArgs(pF);
        } else if (isUF) {
          yicesArgs = getArgs(pF);
          name = yices_term_to_string(yicesArgs.get(0));
          functionDeclaration = yicesArgs.get(0);
          yicesArgs.remove(0);
        } else if (isSum) {
          name = FunctionDeclarationKind.ADD.toString();
          yicesArgs = getSumArgs(pF);
        } else if (isBvAdd) {
          name = FunctionDeclarationKind.BV_ADD.toString();
          yicesArgs = getBvSumArgs(pF);
        } else if (isBvMul) {
          name = FunctionDeclarationKind.BV_MUL.toString();
          yicesArgs = getMultiplyArgs(pF);
        } else if (isMultiply) {
          name = FunctionDeclarationKind.MUL.toString();
          yicesArgs = getMultiplyArgs(pF);
        } else if (isExtend) {
          name = kind.toString();
          yicesArgs = getExtendArgs(pF);
        } else {
          name = kind.toString();
          yicesArgs = getArgs(pF);
        }
        for (int arg : yicesArgs) {
          System.out.println("Arg: " + yices_term_to_string(arg));
          if (yices_term_is_projection(arg)) {
            System.out.println("Index: " + yices_proj_index(arg));
            System.out.println("Child: " + yices_proj_arg(arg));
          }
          FormulaType<?> argumentType = getFormulaType(arg);
          args.add(encapsulate(argumentType, arg));
          argTypes.add(argumentType);
        }
        return pVisitor.visitFunction(
            pFormula,
            args.build(),
            FunctionDeclarationImpl.of(
                name, kind, argTypes.build(), getFormulaType(pF), functionDeclaration));
    }
  }

  private static FunctionDeclarationKind getExtendKind(Integer pF) {
    System.out.println(yices_term_to_string(pF));
    if (yices_term_is_projection(yices_term_child(pF, 0))) {
      int bv = yices_proj_arg(yices_term_child(pF, 0));
      int bvSize = yices_term_bitsize(bv);
      int extendedBy = yices_term_num_children(pF) - bvSize;
      if (extendedBy != 0) {
        if (yices_term_child(pF, bvSize) == yices_false()) {
          return FunctionDeclarationKind.BV_ZERO_EXTENSION;
        } else {
          return FunctionDeclarationKind.BV_SIGN_EXTENSION;
        }
      }
    }
    return null;
  }

  private FunctionDeclarationKind getDeclarationKind(int pF) {
    List<Integer> constantsAndVariables =
        ImmutableList.of(
            YICES_BOOL_CONST,
            YICES_ARITH_CONST,
            YICES_BV_CONST,
            YICES_VARIABLE,
            YICES_UNINTERPRETED_TERM);
    int constructor = yices_term_constructor(pF);
    assert !constantsAndVariables.contains(constructor)
        : String.format(
            "Term %s with constructor %d should be handled somewhere else",
            yices_term_to_string(pF), constructor);

    switch (constructor) {
      case YICES_ITE_TERM:
        return FunctionDeclarationKind.ITE;
      case YICES_APP_TERM:
        return FunctionDeclarationKind.UF;
      case YICES_EQ_TERM:
        return FunctionDeclarationKind.EQ; // Covers all equivalences
      case YICES_DISTINCT_TERM:
        return FunctionDeclarationKind.DISTINCT;
      case YICES_NOT_TERM:
        if (isNestedConjunction(pF)) {
          return FunctionDeclarationKind.AND;
        } else {
          return FunctionDeclarationKind.NOT;
        }
      case YICES_OR_TERM:
        return FunctionDeclarationKind.OR;
      case YICES_XOR_TERM:
        return FunctionDeclarationKind.XOR;
      case YICES_BV_DIV:
        return FunctionDeclarationKind.BV_UDIV;
      case YICES_BV_REM:
        return FunctionDeclarationKind.BV_UREM;
      case YICES_BV_SDIV:
        return FunctionDeclarationKind.BV_SDIV;
      case YICES_BV_SREM:
        return FunctionDeclarationKind.BV_SREM;
      case YICES_BV_SHL:
        return FunctionDeclarationKind.BV_SHL;
      case YICES_BV_LSHR:
        return FunctionDeclarationKind.BV_LSHR;
      case YICES_BV_ASHR:
        return FunctionDeclarationKind.BV_ASHR;
      case YICES_BV_GE_ATOM:
        return FunctionDeclarationKind.BV_UGE;
      case YICES_BV_SGE_ATOM:
        return FunctionDeclarationKind.BV_SGE;
      case YICES_ARITH_GE_ATOM:
        return FunctionDeclarationKind.GTE;
      case YICES_FLOOR:
        return FunctionDeclarationKind.FLOOR;
      case YICES_RDIV:
        return FunctionDeclarationKind.DIV;
      case YICES_IDIV:
        return FunctionDeclarationKind.DIV; // correct?
      case YICES_SELECT_TERM:
        return FunctionDeclarationKind.SELECT;
      case YICES_BV_SUM:
        return FunctionDeclarationKind.BV_ADD;
      case YICES_ARITH_SUM:
        return FunctionDeclarationKind.ADD;
      case YICES_POWER_PRODUCT:
        System.out.println("Term type: " + yices_type_to_string(yices_type_of_term(pF)));
        if (yices_type_is_bitvector(yices_type_of_term(pF))) {
          return FunctionDeclarationKind.BV_MUL;
        } else {
          return FunctionDeclarationKind.MUL;
        }

      default:
        System.out.println("Constructor is:" + constructor);
        if (constructor == YICES_BV_ARRAY) {
          FunctionDeclarationKind possibleExtend = getExtendKind(pF);
          if (possibleExtend != null) {
            return possibleExtend;
          }
        } else if (constructor == YICES_BIT_TERM) {
          // TODO Visit bit(t i) in meaningful way
        }
        return FunctionDeclarationKind.OTHER;
    }
  }

  /** Yices transforms <code>AND(x,...)</code> into <code>NOT(OR(NOT(X),NOT(...))</code>. */
  private static boolean isNestedConjunction(int outerTerm) {
    if (yices_term_constructor(outerTerm) != YICES_NOT_TERM) {
      return false;
    }

    int middleTerm = yices_term_child(outerTerm, 0);
    if (yices_term_constructor(middleTerm) != YICES_OR_TERM) {
      return false;
    }

    // code commented out --> ignore nested NOTs and just negate all resulting child-terms.
    // for (int child : getArgs(middleTerm)) {
    //   if (yices_term_constructor(child) != YICES_NOT_TERM) {
    //     return false;
    //   }
    // }

    return true;
  }

  /**
   * Yices transforms <code>AND(x,...)</code> into <code>NOT(OR(NOT(X),NOT(...))</code>.
   *
   * <p>Only call this method for terms that are nested conjunctions!
   */
  private static List<Integer> getNestedConjunctionArgs(int outerTerm) {
    Preconditions.checkArgument(yices_term_constructor(outerTerm) == YICES_NOT_TERM);
    int middleTerm = yices_term_child(outerTerm, 0);
    Preconditions.checkArgument(yices_term_constructor(middleTerm) == YICES_OR_TERM);
    List<Integer> result = new ArrayList<>();
    for (int child : getArgs(middleTerm)) {
      result.add(yices_not(child));
    }
    return result;
  }

  private static List<Integer> getArgs(int parent) {
    System.out.println("Getting args of: " + yices_term_to_string(parent));
    List<Integer> children = new ArrayList<>();
    for (int i = 0; i < yices_term_num_children(parent); i++) {
      children.add(yices_term_child(parent, i));
    }
    return children;
  }

  private static List<Integer> getExtendArgs(int parent) {
    List<Integer> children = new ArrayList<>();
    int bv = yices_proj_arg(yices_term_child(parent, 0));
    int bvSize = yices_term_bitsize(bv);
    int extendedBy = yices_term_num_children(parent) - bvSize;
    if (extendedBy > 0) {
      for (int i = 0; i < yices_term_num_children(parent); i++) {
        children.add(yices_term_child(parent, i));
      }
    } else {
      throw new IllegalArgumentException("Not a bv extension term.");
    }
    return children;
  }

  private static List<Integer> getSumArgs(int parent) {
    System.out.println(yices_term_to_string(parent));
    List<Integer> children = new ArrayList<>();
    for (int i = 0; i < yices_term_num_children(parent); i++) {
      String[] child = yices_sum_component(parent, i);
      String coeff = child[0];
      int term = Integer.parseInt(child[1]);
      if (term == -1) { // No term just a number
        children.add(yices_parse_rational(coeff));
      } else { // return only term / ignores coefficient
        // int coeffTerm = yices_parse_rational(coeff);
        // int term = Integer.parseInt(parts[1]);
        // children.add(yices_mul(coeffTerm, term));
        children.add(term);
      }
    }
    return children;
  }

  private static List<Integer> getBvSumArgs(int parent) {
    List<Integer> children = new ArrayList<>();
    int bitsize = yices_term_bitsize(parent);
    for (int i = 0; i < yices_term_num_children(parent); i++) {
      int[] component = yices_bvsum_component(parent, i, bitsize);
      if (component[component.length - 1] == -1) { // No term
        children.add(yices_bvconst_from_array(bitsize, Arrays.copyOfRange(component, 0, bitsize)));
      } else { // return only term / ignores coefficient
        children.add(component[component.length - 1]);
      }
    }
    return children;
  }

  private static List<Integer> getMultiplyArgs(int parent) {
    List<Integer> children = new ArrayList<>();
    for (int i = 0; i < yices_term_num_children(parent); i++) {
      int[] component = yices_product_component(parent, i);
      children.add(component[0]); // add term ignore exponent
    }
    return children;
  }

  @Override
  public Integer callFunctionImpl(Integer pDeclaration, List<Integer> pArgs) {
    if (pDeclaration < 0) { // is constant function application from API
      switch (-pDeclaration) {
        case YICES_ITE_TERM:
          Preconditions.checkArgument(pArgs.size() == 3);
          return yices_ite(pArgs.get(0), pArgs.get(1), pArgs.get(0));
        case YICES_EQ_TERM:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_eq(pArgs.get(0), pArgs.get(1));
        case YICES_DISTINCT_TERM:
          return yices_distinct(pArgs.size(), Ints.toArray(pArgs));
        case YICES_NOT_TERM:
          Preconditions.checkArgument(pArgs.size() == 1);
          return yices_not(pArgs.get(0));
        case YICES_OR_TERM:
          return yices_or(pArgs.size(), Ints.toArray(pArgs));
        case YICES_XOR_TERM:
          return yices_xor(pArgs.size(), Ints.toArray(pArgs));
        case YICES_BV_DIV:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvdiv(pArgs.get(0), pArgs.get(1));
        case YICES_BV_REM:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvrem(pArgs.get(0), pArgs.get(1));
        case YICES_BV_SDIV:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvsdiv(pArgs.get(0), pArgs.get(1));
        case YICES_BV_SREM:
          return yices_bvsrem(pArgs.get(0), pArgs.get(1));
        case YICES_BV_SMOD:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvsmod(pArgs.get(0), pArgs.get(1));
        case YICES_BV_SHL:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvshl(pArgs.get(0), pArgs.get(1));
        case YICES_BV_LSHR:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvlshr(pArgs.get(0), pArgs.get(1));
        case YICES_BV_ASHR:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvashr(pArgs.get(0), pArgs.get(1));
        case YICES_BV_GE_ATOM:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvge_atom(pArgs.get(0), pArgs.get(1));
        case YICES_BV_SGE_ATOM:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_bvsge_atom(pArgs.get(0), pArgs.get(1));
        case YICES_ARITH_GE_ATOM:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_arith_geq_atom(pArgs.get(0), pArgs.get(1));
        case YICES_ABS:
          Preconditions.checkArgument(pArgs.size() == 1);
          return yices_abs(pArgs.get(0));
        case YICES_CEIL:
          Preconditions.checkArgument(pArgs.size() == 1);
          return yices_ceil(pArgs.get(0));
        case YICES_FLOOR:
          Preconditions.checkArgument(pArgs.size() == 1);
          return yices_floor(pArgs.get(0));
        case YICES_RDIV:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_division(pArgs.get(0), pArgs.get(1));
        case YICES_IDIV:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_idiv(pArgs.get(0), pArgs.get(1));
        case YICES_IMOD:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_imod(pArgs.get(0), pArgs.get(1));
        case YICES_IS_INT_ATOM:
          Preconditions.checkArgument(pArgs.size() == 1);
          return yices_is_int_atom(pArgs.get(0));
        case YICES_DIVIDES_ATOM:
          Preconditions.checkArgument(pArgs.size() == 2);
          return yices_divides_atom(pArgs.get(0), pArgs.get(1));
        case YICES_BV_SUM:
          return yices_bvsum(pArgs.size(), Ints.toArray(pArgs));
        case YICES_ARITH_SUM:
          return yices_sum(pArgs.size(), Ints.toArray(pArgs));
        case YICES_POWER_PRODUCT:
          return yices_product(pArgs.size(), Ints.toArray(pArgs));
          // TODO add more cases
        default:
          throw new IllegalArgumentException(
              "Unknown function declaration with constant value " + -pDeclaration);
      }
    } else { // is UF Application
      System.out.println("Input: " + yices_term_to_string(pDeclaration));
      int size = pArgs.size();
      if (size == 0) {
        return pDeclaration;
      } else {
        int[] argArray = new int[size];
        for (int i = 0; i < size; i++) {
          argArray[i] = pArgs.get(i);
        }
        int app = yices_application(pDeclaration, size, argArray);
        System.out.println("App: " + yices_term_to_string(app));
        return app;
      }
    }
  }

  @Override
  public Integer declareUFImpl(String pName, Integer pReturnType, List<Integer> pArgTypes) {
    int size = pArgTypes.size();
    int[] argTypeArray = Ints.toArray(pArgTypes);
    final int yicesFuncType;
    if (pArgTypes.isEmpty()) {
      // a nullary function is a plain symbol (variable)
      yicesFuncType = pReturnType;
    } else {
      yicesFuncType = yices_function_type(size, argTypeArray, pReturnType);
    }
    int temp = yices_named_variable(yicesFuncType, pName);
    System.out.println("Creating UF: " + pName + " with index: " + temp);
    return temp;
  }

  @Override
  protected Integer getBooleanVarDeclarationImpl(Integer pTFormulaInfo) {
    return yices_term_constructor(pTFormulaInfo);
  }

  private Object parseNumeralValue(Integer pF, FormulaType<?> type) {
    if (yices_term_constructor(pF) == YICES_ARITH_CONST) {
      String value = yices_rational_const_value(pF);
      if (type.isRationalType()) {
        return Rational.of(value);
      } else if (type.isIntegerType()) {
        return new BigInteger(value);
      } else {
        throw new IllegalArgumentException("Unexpected type: " + type);
      }
    } else {
      throw new IllegalArgumentException(
          String.format(
              "Term: '%s' with type '%s' is not an arithmetic constant",
              yices_term_to_string(pF), yices_type_to_string(yices_type_of_term(pF))));
    }
  }

  private BigInteger parseBitvector(int pF) {
    if (yices_term_constructor(pF) == YICES_BV_CONST) {
      int[] littleEndianBV = yices_bv_const_value(pF, yices_term_bitsize(pF));
      Preconditions.checkArgument(littleEndianBV.length != 0, "BV was empty");
      String bigEndianBV = Joiner.on("").join(Lists.reverse(Ints.asList(littleEndianBV)));
      return new BigInteger(bigEndianBV, 2);
    } else {
      throw new IllegalArgumentException(
          String.format("Term: '%s' is not a bitvector constant", yices_term_to_string(pF)));
    }
  }

  @Override
  public Object convertValue(Integer key) {
    throw new UnsupportedOperationException(
        "Yices needs a second term to determine a correct type. Please use the other method.");
  }

  @Override
  public Object convertValue(Integer typeKey, Integer pF) {
    FormulaType<?> type = getFormulaType(typeKey);
    if (type.isBooleanType()) {
      return pF.equals(yices_true());
    } else if (type.isRationalType() || type.isIntegerType()) {
      return parseNumeralValue(pF, type);
    } else if (type.isBitvectorType()) {
      return parseBitvector(pF);
    } else {
      throw new IllegalArgumentException(
          "Unexpected type: " + yices_type_to_string(yices_type_of_term(pF)));
    }
  }
}