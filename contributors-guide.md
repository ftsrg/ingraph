# Contributor's guide

The ingraph project is implemented in Java, Scala and Xtend. The development team uses both Eclipse and IntelliJ IDEA, so both of the are supported. According to our experience, Eclipse has a superior editor for Xtend, while IntelliJ has better support for Scala.

## Developing the code

### Eclipse

#### Prerequisites

It is recommended to start with [Eclipse Neon](http://www.eclipse.org/downloads/packages/release/Neon) and selecting **Eclipse IDE for Java and DSL Developers** distribution.

:notebook_with_decorative_cover: The _DSL Developers_ flavor of Eclipse has the required dependencies for Xtend, Xtext and Xcore. If you start from another Eclipse flavor, you should install the missing plug-ins from the update site of your Eclipse release (e.g. the Neon update site):

* **Xtend IDE**
* **Xtext Complete SDK**
* **EMF - Eclipse Modeling Framework Xcore SDK**

Proceed with installing the required dependencies:

1. Install the following plug-ins for Scala:
  * [Scala IDE](http://scala-ide.org/). Install all optional dependencies.
  * [ScalaTest for Scala IDE](http://www.scalatest.org/user_guide/using_scalatest_with_eclipse) plug-in.
1. Go to **Help** | **Eclipse Marketplace...** and install the **Buildship: Eclipse Plug-ins for Gradle** plug-in. You may also want to install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki> to provide an editor for the `.gradle` configuration files.
1. Go to **Help** | **Eclipse Marketplace...** and install the **EditorConfig** plug-in.
1. Install **VIATRA Core** 1.6.x from the [milestone update site](
http://download.eclipse.org/viatra/updates/milestone).

#### Importing the projects

Import the project with **Import...** | **Gradle** | **Gradle Project**, select the directory of this repository. When prompted whether to overwrite the existing project files, click **Keep**.

#### Opening relational algebra models

1. Open the `relalg` models with the **Sample Reflective Ecore Model Editor**.

#### Using VIATRA

1. Go to **Window** | **Preferences** | **VIATRA** | **Query Explorer** and make sure that the **Dynamic EMF mode** is _turned on_ (it is turned off by default).
1. Use the **Query Explorer** view and do not load the model by clicking the green play button, instead click to the downward pointing triangle and click **Load Resource**.
1. Open the `vql` query specification.
1. Click the **green play button** to load the query.

### IntelliJ IDEA

#### Prerequisites

* Install the [Scala plug-in](https://plugins.jetbrains.com/idea/plugin/1347-scala).
* Install the [Xtend plug-in](https://plugins.jetbrains.com/idea/plugin/8073-xtend-support).
* Currently, VIATRA is not supported in IntelliJ. You are able to use the previously defined patterns, but cannot define new patterns or edit existing ones.

#### Importing the projects

* Import the projects using **New** | **Project from Existing Sources...**. Untick **Create separate module per source set**.
* After importing, add the Scala SDK (version 2.11) to the module. (If you did not use Scala before, download it from the dialog box provided by IntelliJ).

## Debug mode

For the modules that support it, you can define environment variables to enable debug mode:

* `DEBUG=1` or `DEBUG_INGRAPH=1` enables debug mode for all modules
* `DEBUG_<MODULENAME>=1` (e.g. `DEBUG_INGRAPH_CYPHER2RELALG=1`) enables debug mode for that particular module

Currently, these modules have support for debugging:

* `ingraph-cypher2relalg`
