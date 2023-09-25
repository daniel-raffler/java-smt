// This file is part of JavaSMT,
// an API wrapper for a collection of SMT solvers:
// https://github.com/sosy-lab/java-smt
//
// This file is based on "btortypes.h" from Boolector.
//
// SPDX-FileCopyrightText: 2007-2020 Dirk Beyer <https://www.sosy-lab.org>
// SPDX-FileCopyrightText: 2015-2020 Mathias Preiner
// SPDX-FileCopyrightText: 2016 Armin Biere
// SPDX-FileCopyrightText: 2016-2020 Aina Niemetz
//
// SPDX-License-Identifier: MIT AND Apache-2.0

package org.sosy_lab.java_smt.solvers.bitwuzla;

/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

public final class BitwuzlaOption {
  public static final BitwuzlaOption BITWUZLA_OPT_LOGLEVEL =
      new BitwuzlaOption("BITWUZLA_OPT_LOGLEVEL");
  public static final BitwuzlaOption BITWUZLA_OPT_PRODUCE_MODELS =
      new BitwuzlaOption("BITWUZLA_OPT_PRODUCE_MODELS");
  public static final BitwuzlaOption BITWUZLA_OPT_PRODUCE_UNSAT_ASSUMPTIONS =
      new BitwuzlaOption("BITWUZLA_OPT_PRODUCE_UNSAT_ASSUMPTIONS");
  public static final BitwuzlaOption BITWUZLA_OPT_PRODUCE_UNSAT_CORES =
      new BitwuzlaOption("BITWUZLA_OPT_PRODUCE_UNSAT_CORES");
  public static final BitwuzlaOption BITWUZLA_OPT_SEED = new BitwuzlaOption("BITWUZLA_OPT_SEED");
  public static final BitwuzlaOption BITWUZLA_OPT_VERBOSITY =
      new BitwuzlaOption("BITWUZLA_OPT_VERBOSITY");
  public static final BitwuzlaOption BITWUZLA_OPT_BV_SOLVER =
      new BitwuzlaOption("BITWUZLA_OPT_BV_SOLVER");
  public static final BitwuzlaOption BITWUZLA_OPT_REWRITE_LEVEL =
      new BitwuzlaOption("BITWUZLA_OPT_REWRITE_LEVEL");
  public static final BitwuzlaOption BITWUZLA_OPT_SAT_SOLVER =
      new BitwuzlaOption("BITWUZLA_OPT_SAT_SOLVER");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_CONST_BITS =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_CONST_BITS");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_INFER_INEQ_BOUNDS =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_INFER_INEQ_BOUNDS");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_NPROPS =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_NPROPS");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_NUPDATES =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_NUPDATES");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_PATH_SEL =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_PATH_SEL");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_PROB_RANDOM_INPUT =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_PROB_RANDOM_INPUT");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_PROB_USE_INV_VALUE =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_PROB_USE_INV_VALUE");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_SEXT =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_SEXT");
  public static final BitwuzlaOption BITWUZLA_OPT_PROP_NORMALIZE =
      new BitwuzlaOption("BITWUZLA_OPT_PROP_NORMALIZE");
  public static final BitwuzlaOption BITWUZLA_OPT_PREPROCESS =
      new BitwuzlaOption("BITWUZLA_OPT_PREPROCESS");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_CONTRADICTING_ANDS =
      new BitwuzlaOption("BITWUZLA_OPT_PP_CONTRADICTING_ANDS");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_ELIM_BV_EXTRACTS =
      new BitwuzlaOption("BITWUZLA_OPT_PP_ELIM_BV_EXTRACTS");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_EMBEDDED_CONSTR =
      new BitwuzlaOption("BITWUZLA_OPT_PP_EMBEDDED_CONSTR");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_FLATTEN_AND =
      new BitwuzlaOption("BITWUZLA_OPT_PP_FLATTEN_AND");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_NORMALIZE =
      new BitwuzlaOption("BITWUZLA_OPT_PP_NORMALIZE");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_NORMALIZE_SHARE_AWARE =
      new BitwuzlaOption("BITWUZLA_OPT_PP_NORMALIZE_SHARE_AWARE");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_SKELETON_PREPROC =
      new BitwuzlaOption("BITWUZLA_OPT_PP_SKELETON_PREPROC");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_VARIABLE_SUBST =
      new BitwuzlaOption("BITWUZLA_OPT_PP_VARIABLE_SUBST");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_EQ =
      new BitwuzlaOption("BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_EQ");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_DISEQ =
      new BitwuzlaOption("BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_DISEQ");
  public static final BitwuzlaOption BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_BV_INEQ =
      new BitwuzlaOption("BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_BV_INEQ");
  public static final BitwuzlaOption BITWUZLA_OPT_DBG_RW_NODE_THRESH =
      new BitwuzlaOption("BITWUZLA_OPT_DBG_RW_NODE_THRESH");
  public static final BitwuzlaOption BITWUZLA_OPT_DBG_PP_NODE_THRESH =
      new BitwuzlaOption("BITWUZLA_OPT_DBG_PP_NODE_THRESH");
  public static final BitwuzlaOption BITWUZLA_OPT_DBG_CHECK_MODEL =
      new BitwuzlaOption("BITWUZLA_OPT_DBG_CHECK_MODEL");
  public static final BitwuzlaOption BITWUZLA_OPT_DBG_CHECK_UNSAT_CORE =
      new BitwuzlaOption("BITWUZLA_OPT_DBG_CHECK_UNSAT_CORE");
  public static final BitwuzlaOption BITWUZLA_OPT_NUM_OPTS =
      new BitwuzlaOption("BITWUZLA_OPT_NUM_OPTS");

  public final int swigValue() {
    return swigValue;
  }

  @Override
  public String toString() {
    return swigName;
  }

  public static BitwuzlaOption swigToEnum(int swigValue) {
    if (swigValue < swigValues.length
        && swigValue >= 0
        && swigValues[swigValue].swigValue == swigValue) return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue) return swigValues[i];
    throw new IllegalArgumentException(
        "No enum " + BitwuzlaOption.class + " with value " + swigValue);
  }

  private BitwuzlaOption(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  @SuppressWarnings({"unused", "StaticAssignmentInConstructor"})
  private BitwuzlaOption(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue + 1;
  }

  @SuppressWarnings({"StaticAssignmentInConstructor", "unused"})
  private BitwuzlaOption(String swigName, BitwuzlaOption swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue + 1;
  }

  private static BitwuzlaOption[] swigValues = {
    BITWUZLA_OPT_LOGLEVEL,
    BITWUZLA_OPT_PRODUCE_MODELS,
    BITWUZLA_OPT_PRODUCE_UNSAT_ASSUMPTIONS,
    BITWUZLA_OPT_PRODUCE_UNSAT_CORES,
    BITWUZLA_OPT_SEED,
    BITWUZLA_OPT_VERBOSITY,
    BITWUZLA_OPT_BV_SOLVER,
    BITWUZLA_OPT_REWRITE_LEVEL,
    BITWUZLA_OPT_SAT_SOLVER,
    BITWUZLA_OPT_PROP_CONST_BITS,
    BITWUZLA_OPT_PROP_INFER_INEQ_BOUNDS,
    BITWUZLA_OPT_PROP_NPROPS,
    BITWUZLA_OPT_PROP_NUPDATES,
    BITWUZLA_OPT_PROP_PATH_SEL,
    BITWUZLA_OPT_PROP_PROB_RANDOM_INPUT,
    BITWUZLA_OPT_PROP_PROB_USE_INV_VALUE,
    BITWUZLA_OPT_PROP_SEXT,
    BITWUZLA_OPT_PROP_NORMALIZE,
    BITWUZLA_OPT_PREPROCESS,
    BITWUZLA_OPT_PP_CONTRADICTING_ANDS,
    BITWUZLA_OPT_PP_ELIM_BV_EXTRACTS,
    BITWUZLA_OPT_PP_EMBEDDED_CONSTR,
    BITWUZLA_OPT_PP_FLATTEN_AND,
    BITWUZLA_OPT_PP_NORMALIZE,
    BITWUZLA_OPT_PP_NORMALIZE_SHARE_AWARE,
    BITWUZLA_OPT_PP_SKELETON_PREPROC,
    BITWUZLA_OPT_PP_VARIABLE_SUBST,
    BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_EQ,
    BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_DISEQ,
    BITWUZLA_OPT_PP_VARIABLE_SUBST_NORM_BV_INEQ,
    BITWUZLA_OPT_DBG_RW_NODE_THRESH,
    BITWUZLA_OPT_DBG_PP_NODE_THRESH,
    BITWUZLA_OPT_DBG_CHECK_MODEL,
    BITWUZLA_OPT_DBG_CHECK_UNSAT_CORE,
    BITWUZLA_OPT_NUM_OPTS
  };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}
