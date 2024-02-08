// This file is part of JavaSMT,
// an API wrapper for a collection of SMT solvers:
// https://github.com/sosy-lab/java-smt
//
// SPDX-FileCopyrightText: 2023 Dirk Beyer <https://www.sosy-lab.org>
//
// SPDX-License-Identifier: Apache-2.0

package org.sosy_lab.java_smt.solvers.bitwuzla;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.io.IOException;
import org.sosy_lab.common.Appender;
import org.sosy_lab.common.Appenders;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.basicimpl.AbstractFormulaManager;
import org.sosy_lab.java_smt.solvers.bitwuzla.api.Bitwuzla;
import org.sosy_lab.java_smt.solvers.bitwuzla.api.Options;
import org.sosy_lab.java_smt.solvers.bitwuzla.api.Parser;
import org.sosy_lab.java_smt.solvers.bitwuzla.api.Sort;
import org.sosy_lab.java_smt.solvers.bitwuzla.api.Term;
import org.sosy_lab.java_smt.solvers.bitwuzla.api.Vector_Term;

final class BitwuzlaFormulaManager
    extends AbstractFormulaManager<Term, Sort, Void, BitwuzlaDeclaration> {
  private final BitwuzlaFormulaCreator creator;
  private final Options bitwuzlaOption;

  BitwuzlaFormulaManager(
      BitwuzlaFormulaCreator pFormulaCreator,
      BitwuzlaUFManager pFunctionManager,
      BitwuzlaBooleanFormulaManager pBooleanManager,
      BitwuzlaBitvectorFormulaManager pBitvectorManager,
      BitwuzlaQuantifiedFormulaManager pQuantifierManager,
      BitwuzlaFloatingPointManager pFloatingPointManager,
      BitwuzlaArrayFormulaManager pArrayManager,
      Options pBitwuzlaOptions) {
    super(
        pFormulaCreator,
        pFunctionManager,
        pBooleanManager,
        null,
        null,
        pBitvectorManager,
        pFloatingPointManager,
        pQuantifierManager,
        pArrayManager,
        null,
        null,
        null);
    creator = pFormulaCreator;
    bitwuzlaOption = pBitwuzlaOptions;
  }

  @Override
  public BooleanFormula parse(String formulaStr) throws IllegalArgumentException {
    // Strip the input string and remove everything but declarations and assertions
    // FIXME: We should handle this in AbstractFormulaManager as it affects all solvers
    String s = formulaStr;
    if (s.startsWith("(set-logic ")) {
      s = s.substring(1 + s.indexOf(')'));
    }
    if (s.contains("(check-sat)")) {
      s = s.replace("(check-sat)", "");
    }
    if (s.contains("(exit)")) {
      s = s.replace("(exit)", "");
    }

    Parser parser = new Parser(bitwuzlaOption);
    parser.parse(s, true, false);

    Vector_Term assertions = parser.bitwuzla().get_assertions();
    Preconditions.checkArgument(
        !assertions.isEmpty(), "No assertion found in input string \"%s\"", formulaStr);

    return creator.encapsulateBoolean(Iterables.getLast(assertions));
  }

  @Override
  public Appender dumpFormula(Term pTerm) {
    // There are 2 ways of SMT2 printing in Bitwuzla, bitwuzla_term_print() and
    // bitwuzla_term_print_fmt(), which print a single formula, and bitwuzla_print_formula(),
    // which prints the complete assertion stack of the bitwuzla instance given to the function.
    // Only bitwuzla_print_formula() gives us the proper SMT2 format, with (check-sat) etc.
    // Note: bitwuzla_print_formula() is wrapped in dump_assertions_smt2()
    return new Appenders.AbstractAppender() {
      @Override
      public void appendTo(Appendable out) throws IOException {
        Bitwuzla bitwuzla = new Bitwuzla(); // TODO: It would be better to keep this instance around
        for (Term t : BitwuzlaFormulaCreator.getVariableCasts()) {
          bitwuzla.assert_formula(t);
        }
        bitwuzla.assert_formula(pTerm);
        String dump = bitwuzla.print_formula();
        if (dump.startsWith("(set-logic ")) {
          dump = dump.substring(1 + dump.indexOf(')'));
        }
        dump = dump.replace("(check-sat)", "");
        dump = dump.replace("(exit)", "");
        out.append(dump);
      }
    };
  }

  static Term getBitwuzlaTerm(Formula pT) {
    return ((BitwuzlaFormula) pT).getTerm();
  }
}
