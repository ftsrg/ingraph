# ingraph
 
[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph)

[[report on openCypher TCK results]](http://docs.inf.mit.bme.hu/ingraph/pub/opencypher-report.pdf) | [[regression tests]](http://docs.inf.mit.bme.hu/ingraph/tests/regressionTests/)

For a summary on the project, visit the [ingraph web page](http://docs.inf.mit.bme.hu/ingraph/).

## User's guide

To run the tests, issue the following command:

```
./gradlew :ingraph-engine:ingraph-engine-ingraph-ire:test
```

## Contributor's guide

ingraph is defined as a single Gradle project, so it can be built in a single step. However, the subprojects are split to two categories:

1. `ingraph-compiler`, which compiles openCypher queries to relational algebra expressions (search plans / Rete networks). The compiler components are built using EMF-based technologies (Xcore, Xtend, Xtext and VIATRA), so we recommend the Eclipse IDE for developing them.

2. `ingraph-engine`, which evaluates relational algebra expressions. The engine is built using Scala-based technologies (Akka, ScalaTest and Scala Cukes), so we recommend contributors to use IntelliJ IDEA for developing these components.

### Developing the compiler

#### Prerequisites

We recommend Eclipse for developing the compiler.

Start with [Eclipse Oxygen](http://www.eclipse.org/downloads/packages/release/Oxygen/) and select **Eclipse IDE for Java and DSL Developers** distribution. It is recommended to increase the memory available for Eclipse: edit the `eclipse.ini` file and set a larger value, e.g. `-Xmx2G`.

Required dependencies:

* Install the **VIATRA Query and Transformation SDK** 1.6.0.M3 plug-in from the [VIATRA milestone update site](http://download.eclipse.org/viatra/updates/milestone/).

Recommended dependencies:

* Go to **Help** | **Eclipse Marketplace...** and install the following plugins:
  * **EditorConfig**
  * **Minimalist Gradle Editor**
* If you are brave, install the **Scala IDE** and **ScalaTest** from the [Scala IDE update site](http://download.scala-ide.org/sdk/lithium/e46/scala212/stable/site). It was developed for Neon, but works with Oxygen, except for the Scala worksheet.

:notebook_with_decorative_cover: If you start from another Eclipse flavor (e.g. the Scala IDE), you should install the following plug-ins. However, this is quite tricky and is _not recommended_.

  * **Xtend IDE**
  * **Xtext Complete SDK**
  * **EMF - Eclipse Modeling Framework Xcore SDK** (1.5.0+)
  * **Buildship Gradle Integration 2.0**

#### Importing the projects

1. Build the projects from command line with the following command:

    ```bash
    ./gradlew assemble eclipse
    ```

    This will generate 1. source files from the Xcore model and 2. the Eclipse project files.
1. Import the project with **Import...** | **Gradle** | **Gradle Project**, select the `ingraph-compiler` directory. Note that you *should not* select the `ingraph` directory, but the compiler's directory instead. When prompted whether to overwrite existing project files, click **Keep**.

Optionally, you might also import the `ingraph-engine` directory.

#### relalg models

There are two kinds for relational algebra (`relalg`) models:

* non-incremental search plans (`search`)
* incremental Rete networks (`rete`)

Both models use the same metamodel defined in the [`relalg.xcore`](ingraph-compiler/ingraph-compiler-relalg-model/src/main/resources/relalg.xcore) source file. The compiler produces `search` models, which can be transformed to `rete` models using the `Search2ReteTransformation` class. For `search` models, the `incrementalPlan` boolean flag is set to `false`, while for `rete` models, it is set to `true`.

To open the `relalg` models with the **Sample Reflective Ecore Model Editor**.

#### Using VIATRA

1. Go to **Window** | **Preferences** | **VIATRA** | **Query Explorer** and make sure that the **Dynamic EMF mode** is _turned on_ (it is turned off by default).
1. Use the **Query Explorer** view and do not load the model by clicking the green play button (it will take minutes), instead click to the downward pointing triangle and click **Load Resource**.
1. Open the `vql` query specification.
1. Click the **green play button** to load the query.

### Developing the engine

#### Prerequisites

* We recommend to use IntelliJ for developing the engine.
* Install the [Scala plug-in](https://plugins.jetbrains.com/idea/plugin/1347-scala).

*Note.* Currently, there is no VIATRA plug-in for IntelliJ. You are able to use the previously defined patterns, but cannot define new patterns or edit existing ones.

#### Importing the projects

* Import the projects using **New** | **Project from Existing Sources...** (or simply click **Import Project** on the welcome screen).
  * Select the `ingraph` directory.
  * Tick **Auto import**.
  * Untick **Create separate module per source set**.
* In the Gradle view, click **Execute Gradle Task** and type `assemble` to the **Command line** field. This will generate source files from the Xcore model.
* After importing, add the Scala SDK (version 2.11) to the module. (If you did not use Scala before, download it from the dialog box provided by IntelliJ).

#### Running the tests

Run the tests in `LdbcSnbBiDriverTest`. They will fail, so click the `LdbcSnbBiDriverTest` caption next to the "green play button", choose **Edit configuration...** and set the **Working directory** to `[...]/ingraph/ingraph-engine/ingraph-engine-driver`.

## Debug mode

You can define environment variables to enable debug mode, for all ingraph projects or only for specific modules:

* `DEBUG=1` or `DEBUG_INGRAPH=1` enables debug mode for all modules
* `DEBUG_<MODULENAME>=1` (e.g. `DEBUG_INGRAPH_CYPHER2SEARCH=1`) enables debug mode for that particular module

Currently, these modules have support for debugging:

* `ingraph-cypher2search`

## Pages

* [Troubleshooting](troubleshooting.md)
* [Deploying binary artifacts](deploying-binary-artifacts.md)

## Third-party dependencies

For parsing queries, we use the [Slizaa openCypher Xtext grammar](https://github.com/slizaa/slizaa-opencypher-xtext).

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
