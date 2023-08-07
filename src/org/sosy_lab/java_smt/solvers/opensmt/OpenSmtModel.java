// This file is part of JavaSMT,
// an API wrapper for a collection of SMT solvers:
// https://github.com/sosy-lab/java-smt
//
// SPDX-FileCopyrightText: 2022 Dirk Beyer <https://www.sosy-lab.org>
//
// SPDX-License-Identifier: Apache-2.0

package org.sosy_lab.java_smt.solvers.opensmt;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import opensmt.Logic;
import opensmt.MainSolver;
import opensmt.Model;
import opensmt.PTRef;
import opensmt.Sort;
import opensmt.SRef;
import opensmt.SymRef;
import opensmt.Symbol;
import opensmt.TemplateFunction;
import opensmt.VectorPTRef;
import org.sosy_lab.java_smt.basicimpl.AbstractModel;

public class OpenSmtModel extends AbstractModel<PTRef, SRef, Logic> {

  private final Logic osmtLogic;
  private final Model osmtModel;

  private final ImmutableList<ValueAssignment> model;

  OpenSmtModel(
      OpenSmtAbstractProver<?> pProver,
      OpenSmtFormulaCreator pCreator,
      Collection<PTRef> pAssertedTerms) {
    super(pProver, pCreator);

    osmtLogic = pCreator.getEnv();
    osmtModel = pProver.getOsmtSolver().getModel();
    
    PTRef asserts = osmtLogic.mkAnd(new VectorPTRef(pAssertedTerms));
    Map<String, PTRef> userDeclarations = creator.extractVariablesAndUFs(asserts, true);

    ImmutableList.Builder<ValueAssignment> builder = ImmutableList.builder();

    for (PTRef term : userDeclarations.values()) {
      SymRef ref = osmtLogic.getSymRef(term);
      Symbol sym = osmtLogic.getSym(ref);

      int numArgs = sym.size() - 1;
      SRef sort = sym.rsort();

      if (osmtLogic.isArraySort(sort)) {
        // INFO: Disable model generation if arrays are used
        // https://github.com/usi-verification-and-security/opensmt/issues/630
        throw new UnsupportedOperationException("OpenSMT does not support model generation when arrays are used");
      }

      if (numArgs == 0) {
        PTRef key = osmtLogic.mkVar(sort, osmtLogic.getSymName(ref));
        PTRef value = osmtModel.evaluate(key);

        builder.add(
            new ValueAssignment(
                  pCreator.encapsulate(key),
                  pCreator.encapsulate(value),
                  pCreator.encapsulateBoolean(osmtLogic.mkEq(key, value)),
                  osmtLogic.getSymName(ref),
                  pCreator.convertValue(value),
                  new ArrayList<>()));
      } else {
        TemplateFunction tf = osmtModel.getDefinition(ref);

        for (List<PTRef> path : unfold(numArgs, tf.getBody())) {
          List<PTRef> args = path.subList(0, numArgs);

          PTRef key = osmtLogic.insertTerm(ref, new VectorPTRef(args));
          PTRef value = path.get(numArgs);

          builder.add(
              new ValueAssignment(
                    pCreator.encapsulate(key),
                    pCreator.encapsulate(value),
                    pCreator.encapsulateBoolean(osmtLogic.mkEq(key, value)),
                    osmtLogic.getSymName(ref),
                    pCreator.convertValue(value),
                    args.stream()
                        .map((val) -> pCreator.convertValue(val))
                        .collect(Collectors.toList())));
        }
      }
    }
    model = builder.build();
  }

  @Override
  public PTRef evalImpl(PTRef f) {
    Preconditions.checkState(!isClosed());
    Map<String, PTRef> userDeclarations = creator.extractVariablesAndUFs(f, true);

    for (PTRef term : userDeclarations.values()) {
      SRef sort = osmtLogic.getSortRef(term);
      if (osmtLogic.isArraySort(sort)) {
        // INFO: Disable model generation if arrays are used
        // https://github.com/usi-verification-and-security/opensmt/issues/630
        throw new UnsupportedOperationException("OpenSMT does not support model generation when arrays are used");
      }
    }
    return osmtModel.evaluate(f);
  }

  /** OpenSMT represents uninterpreted functions as nested ite statements.<p>
    * For example:
    * <pre>
    * (define-fun g ((x1 Int) (x2 Int)) Int
    *   (ite (= 5 x1) (ite (= 3 x2) 2 (ite (= 1 x2) 2 0)) 0))
    * </pre>
    * We use unfold() to extract an array of value tuples from such a definition.
    */
  private ArrayList<ArrayList<PTRef>> unfold(int numArgs, PTRef body) {
    ArrayList<ArrayList<PTRef>> unwrapped = new ArrayList<>();

    if (osmtLogic.isIte(body)) {
      VectorPTRef subterms = osmtLogic.getPterm(body).getArgs();
      PTRef left = subterms.get(1);
      PTRef right = subterms.get(2);
      PTRef value = osmtLogic.getPterm(subterms.get(0)).getArgs().get(0);

      for (ArrayList<PTRef> nested : unfold(numArgs - 1, left)) {
        ArrayList<PTRef> prefixed = new ArrayList<>();
        prefixed.add(value);
        prefixed.addAll(nested);

        unwrapped.add(prefixed);
      }
      unwrapped.addAll(unfold(numArgs, right));
    }

    if (numArgs == 0) {
      ArrayList<PTRef> value = new ArrayList<>();
      value.add(body);

      unwrapped.add(value);
    }
    return unwrapped;
  }

  @Override
  public ImmutableList<ValueAssignment> asList() {
    return model;
  }
}