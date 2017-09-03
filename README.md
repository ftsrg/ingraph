# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph)

[[report on openCypher TCK results]](http://docs.inf.mit.bme.hu/ingraph/pub/opencypher-report.pdf) | [[regression tests]](http://docs.inf.mit.bme.hu/ingraph/tests/regressionTests/)

For a summary on the project, visit the [ingraph web page](http://docs.inf.mit.bme.hu/ingraph/).

## User's guide

To run the tests, issue the following command:

```
./gradlew test
```

## Contributor's guide

ingraph is defined as a Gradle project.

### Prerequisites

* We recommend to use IntelliJ for developing the engine.
* Install the [Scala plug-in](https://plugins.jetbrains.com/idea/plugin/1347-scala).

*Note.* Currently, there is no VIATRA plug-in for IntelliJ. You are able to use the previously defined patterns, but cannot define new patterns or edit existing ones.

### Importing the projects

* Import the projects using **New** | **Project from Existing Sources...** (or simply click **Import Project** on the welcome screen).
  * Select the `ingraph` directory.
  * Tick **Auto import**.
  * Untick **Create separate module per source set**.
  * Use default Gradle wrapper as your Gradle installation.
* In the Gradle view, click **Execute Gradle Task** and type `assemble` to the **Command line** field. This will generate source files from the Xcore model.
* After importing, add the Scala SDK (version 2.11) to the module. (If you did not use Scala before, download it from the dialog box provided by IntelliJ).

### Running the tests

Run the tests in `LdbcSnbBiDriverTest`. They will fail, so click the `LdbcSnbBiDriverTest` caption next to the "green play button", choose **Edit configuration...** and set the **Working directory** to `[...]/ingraph/ingraph-engine/ingraph-engine-driver`.

## Debug mode

You can define environment variables to enable debug mode, for all ingraph projects or only for specific modules:

* `DEBUG=1` or `DEBUG_INGRAPH=1` enables debug mode for all modules
* `DEBUG_<MODULENAME>=1` (e.g. `DEBUG_INGRAPH_CYPHER2SEARCH=1`) enables debug mode for that particular module

Currently, these modules have support for debugging:

* `ingraph-cypher2search`

## Pages

* [Troubleshooting](troubleshooting.md)

## Third-party dependencies

For parsing queries, we use the [Slizaa openCypher Xtext grammar](https://github.com/slizaa/slizaa-opencypher-xtext).

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
