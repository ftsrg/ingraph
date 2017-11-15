# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph)

For a summary on the project, visit the [ingraph web page](http://docs.inf.mit.bme.hu/ingraph/).

## User's guide

To run the tests, issue the following command:

```
./gradlew test
```

## Contributor's guide

ingraph is defined as a Gradle project.

### Prerequisites

* We recommend using IntelliJ for developing ingraph. Make sure you install the [Scala plug-in](https://plugins.jetbrains.com/idea/plugin/1347-scala).
* For SRE and other Clojure projects we recommend Emacs with [CIDER](https://cider.readthedocs.io/en/latest/). If you want to develop Clojure in IntelliJ, check out [Cursive](https://cursive-ide.com/).

### Importing the projects

* Import the projects using **New** | **Project from Existing Sources...** (or simply click **Import Project** on the welcome screen).
  * Select the `ingraph` directory.
  * Tick **Auto import**.
  * ~~Untick **Create separate module per source set**.~~ Until this [IntelliJ issue](https://youtrack.jetbrains.com/issue/SCL-12718) is resolved, tick **Create separate module per source set**.
  * Pick **Use default gradle wrapper** as your Gradle installation.
* After importing, add the Scala SDK (version 2.11) to the module. (If you did not use Scala before, download it from the dialog box provided by IntelliJ).

## Third-party dependencies

For parsing queries, we use the [Slizaa openCypher Xtext grammar](https://github.com/slizaa/slizaa-opencypher-xtext).

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
