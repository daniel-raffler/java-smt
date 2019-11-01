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
package org.sosy_lab.java_smt.solvers.boolector;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
import com.google.common.collect.ImmutableMap;
import java.util.Map.Entry;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sosy_lab.common.NativeLibraries;
import org.sosy_lab.common.ShutdownNotifier;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.configuration.Option;
import org.sosy_lab.common.configuration.Options;
import org.sosy_lab.common.io.PathCounterTemplate;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.api.SolverContext.ProverOptions;

@Options(prefix = "solver.boolector")
class BoolectorEnvironment {

  enum SatSolver {
    LINGELING,
    PICOSAT,
    MINISAT,
    CADICAL
  }

  @Option(secure = true, description = "The SAT solver used by Boolector.")
  private SatSolver satSolver = SatSolver.PICOSAT;

  @Option(
    secure = true,
    description = "Further options for Boolector in addition to the default options. "
        + "Format:  \"Optionname=value\" with ’,’ to seperate options. "
        + "Optionname and value can be found in BtorOption or Boolector C Api."
        + "Example: \"BTOR_OPT_MODEL_GEN=2,BTOR_OPT_INCREMENTAL=1\".")
  private String furtherOptions = "";

  private final int randomSeed;
  private final @Nullable PathCounterTemplate basicLogfile;
  private final LogManager logger;
  private final ShutdownNotifier shutdownNotifier;
  private final long btor;

  BoolectorEnvironment(
      Configuration config,
      LogManager pLogger,
      @Nullable final PathCounterTemplate pBasicLogfile,
      ShutdownNotifier pShutdownNotifier,
      final int pRandomSeed)
      throws InvalidConfigurationException {

    this.logger = pLogger;
    this.basicLogfile = pBasicLogfile;
    this.shutdownNotifier = pShutdownNotifier;
    this.randomSeed = pRandomSeed;

    NativeLibraries.loadLibrary("boolector");

    btor = BtorJNI.boolector_new();
    config.inject(this);

    Preconditions.checkNotNull(satSolver);
    // TODO implement non-incremental stack-handling in the TheoremProver.
    Preconditions.checkArgument(
        satSolver != SatSolver.CADICAL,
        "CaDiCal is not useable with JavaSMT, because it does not support incremental mode.");
    BtorJNI.boolector_set_sat_solver(btor, satSolver.name());

    // Default Options to enable multiple SAT, auto cleanup on close, incremental mode
    BtorJNI.boolector_set_opt(btor, BtorOption.BTOR_OPT_MODEL_GEN.getValue(), 2);
    // Auto memory clean after closing
    BtorJNI.boolector_set_opt(btor, BtorOption.BTOR_OPT_AUTO_CLEANUP.getValue(), 1);
    // Incremental needed for push/pop!
    BtorJNI.boolector_set_opt(btor, BtorOption.BTOR_OPT_INCREMENTAL.getValue(), 1);
    // Sets randomseed accordingly
    BtorJNI.boolector_set_opt(btor, BtorOption.BTOR_OPT_SEED.getValue(), randomSeed);
    // Dump in SMT-LIB2 Format
    BtorJNI.boolector_set_opt(btor, BtorOption.BTOR_OPT_OUTPUT_FORMAT.getValue(), 2);

    setOptions();
    startLogging();
  }

  /**
   * Tries to split the options string and set the options for boolector.
   *
   * @throws InvalidConfigurationException signals that the format for the options string was wrong
   *         (most likely).
   */
  private void setOptions() throws InvalidConfigurationException {
    if (furtherOptions.isEmpty()) {
      return;
    }
    MapSplitter optionSplitter =
        Splitter.on(',')
            .trimResults()
            .omitEmptyStrings()
            .withKeyValueSeparator(Splitter.on('=').limit(2).trimResults());
    ImmutableMap<String, String> furtherOptionsMap;

    try {
      furtherOptionsMap = ImmutableMap.copyOf(optionSplitter.split(furtherOptions));
    } catch (IllegalArgumentException e) {
      throw new InvalidConfigurationException(
          "Invalid Boolector option in \"" + furtherOptions + "\": " + e.getMessage(), e);
    }
    for (Entry<String, String> option : furtherOptionsMap.entrySet()) {
      try {
        BtorOption btorOption = BtorOption.valueOf(option.getKey());
        long optionValue = Long.parseLong(option.getValue());
        BtorJNI.boolector_set_opt(btor, btorOption.getValue(), optionValue);
      } catch (IllegalArgumentException e) {
        throw new InvalidConfigurationException(e.getMessage(), e);
      }
    }
  }

  /**
   * This method returns a new prover, that is registered in this environment. All variables are
   * shared in all registered Boolector instances(btor).
   */
  BoolectorAbstractProver<?> getNewProver(
      BoolectorFormulaManager manager,
      BoolectorFormulaCreator creator,
      Set<ProverOptions> pOptions) {
    return new BoolectorTheoremProver(manager, creator, btor, shutdownNotifier, pOptions);
  }

  public Long getBtor() {
    return btor;
  }

  public Long getBoolSort() {
    return BtorJNI.boolector_bool_sort(btor);
  }

  private void startLogging() {
    if (basicLogfile != null) {
      String filename = basicLogfile.getFreshPath().toAbsolutePath().toString();
      BtorJNI.boolector_set_trapi(btor, filename);
    }
  }
}
