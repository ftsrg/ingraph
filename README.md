# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph)

For a summary on the project, visit the [ingraph web page](http://docs.inf.mit.bme.hu/ingraph/).

ingraph is built with the Gradle system.

## User's guide

To run the tests, issue the following command:

```
./gradlew test
```

## Contributor's guide

### Prerequisites

We recommend using IntelliJ for developing ingraph. Make sure you install the [Scala plug-in](https://plugins.jetbrains.com/idea/plugin/1347-scala).

### Initializing test data

Clean any data that might left over and unzip the test data set:

```console
rm -rf graphs/ldbc-snb-bi/db-sftiny/graph.db/
unzip graphs/ldbc-snb-bi/db-sftiny.zip -d graphs/ldbc-snb-bi/
```

### Importing the projects

* Import the projects using **New** | **Project from Existing Sources...** (or simply click **Import Project** on the welcome screen).
  * Select the `ingraph` directory.
  * Tick **Auto import**.
  * ~~Untick **Create separate module per source set**.~~ Until this [IntelliJ issue](https://youtrack.jetbrains.com/issue/SCL-12718) is resolved, tick **Create separate module per source set**.
  * Pick **Use default gradle wrapper** as your Gradle installation.
  * If you get errors while trying to run tests, perform a manual build.

## Third-party dependencies

For parsing queries, we use the [Slizaa openCypher Xtext grammar](https://github.com/slizaa/slizaa-opencypher-xtext).

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
