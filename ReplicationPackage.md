## [Supplementary material] AGORA: Automated Generation of Test Oracles for REST APIs

This is the supplementary material of the paper entitled *AGORA: Automated Generation of Test Oracles for REST APIs*

**NOTE:** We recommend navigating this documentation using VS code (which preserves hyperlinks and renders Markdown).

## Contents

1. [Bug reports](#bug-reports)
1. [Datasets](#datasets)
1. [Documentation](#documentation)
1. [Java projects](#java-projects)
1. [Reports](#reports)
1. [RESTest configuration files](#restest-configuration-files)
1. [Videos](#videos)


## Bug reports

This directory contains anonymized reports of the real faults detected in 5 commercial APIs (Amadeus Hotel, GitHub, OMDb, Marvel and YouTube),
as well as videos that show how to replicate these bugs.

## Datasets

This directory contains the datasets used for our experiments, namely the different test suites used for experiment 1 (divided in
sets of 50, 100, 500, 1000 and 10K requests), the test suites that were mutated for experiment 2, and our invariant taxonomy.

## Documentation

This directory contains the following documentation:

* **Beet wiki:** Extended running example showcasing the functionality of the Beet instrumenter.
* **JSONMutator configuration:** Description of the mutation operators used for our evaluation (experiment 2).
* **Modifications on Daikon:** Detailed description of the modifications performed on the Daikon software tool.

## Java projects

Contains the Java projects that compose our approach (Beet and Daikon modified) and JSONMutator. These projects contain all the files necessary to
replicate our experiments (see "Videos" directory for more details). These projects require Java 8. Consult the official Daikon user manual to obtain more
details about how to configure Daikon. 

In order to make the replication of our experiments easier, we provide a **Ubuntu Virtual Machine** with all the projects configured. This virtual machine can
be found at: https://doi.org/10.5281/zenodo.7641127 (user: agora, password: agora).

## Reports

This directory contains the reports resulting from our experiments.

* **Invariants classified:** Contains the invariants reported by AGORA and the default version of Daikon in all the API operations. These invariants have been classified as true positives and false positives.
* **Reports experiment 2:** Contains the mutation reports generated after the execution of experiment 2.

## RESTest configuration files

This directory contains the OAS specifications, data dictionaries and test configuration files used for generating the test cases used for our experiments.

## Videos

This directory contains tutorials that show how to use the developed software (Beet, Daikon modified and JSONMutator) and how to replicate our experiments.

These videos were recorded in the provided Virtual Machine.