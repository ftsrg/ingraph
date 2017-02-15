# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph) [ ![Download](https://api.bintray.com/packages/ftsrg/maven/ingraph/images/download.svg) ](https://bintray.com/ftsrg/maven/ingraph/_latestVersion)

## Third-party dependencies

This project uses the Xtext grammar of the [slizaa-opencypher-xtext project](https://github.com/slizaa/slizaa-opencypher-xtext/) licensed under [EPL-v1.0](https://www.eclipse.org/legal/epl-v10.html). (See also the [Why Xtext?](docs/why-xtext.md) document.)

To use it, clone the `slizaa-opencypher-xtext` repository and install it to your local Maven repository:

```bash
git clone https://github.com/slizaa/slizaa-opencypher-xtext/
cd slizaa-opencypher-xtext
mvn clean install
```

You can also use the [`scripts/get-slizaa.sh`](scripts/get-slizaa.sh) to run these commands.

## Generated artifacts

These artifacts are automatcially generated for the last commit:
* [Regression tests](http://docs.inf.mit.bme.hu/ingraph/tests/regressionTests/)
* [Failing tests](http://docs.inf.mit.bme.hu/ingraph/tests/failingTests/)
* [Technical report on compliance with the OpenCypher TCK](http://docs.inf.mit.bme.hu/ingraph/pub/opencypher-report.pdf)

## User's guide

For a high-level overview, see the [ingraph website](http://docs.inf.mit.bme.hu/ingraph/).

ingraph is released on [Bintray](https://bintray.com/ftsrg/maven/ingraph).

For Maven or Gradle, use the following snippets.

### Maven

```xml
<dependency>
  <groupId>ingraph</groupId>
  <artifactId>ingraph-...</artifactId>
  <version>0.2.0</version>
</dependency>

<repositories>
  <repository>
    <id>ftsrg</id>
    <url>https://dl.bintray.com/ftsrg/maven</url>
  </repository>
</repositories>
```

### Gradle

```groovy
repositories {
	maven { url "https://dl.bintray.com/ftsrg/maven" }
}

dependencies {
	compile 'ingraph:ingraph-...:0.2.0'
}
```

## Contributor's Guide

The ingraph project is implemented in Java, Scala and Xtend. The development team uses both Eclipse and IntelliJ IDEA, so both of the are supported. According to our experience, Eclipse has a superior editor for Xtend, while IntelliJ has better support for Scala.

### Eclipse

#### Prerequisites

1. It is recommended to start with the latest version (currently [Oxygen](http://www.eclipse.org/downloads/packages/release/Oxygen/)) **Eclipse IDE for Java and DSL Developers** distribution.
1. Install the following plug-ins for Scala:
  * [Scala IDE](http://scala-ide.org/). Install all optional dependencies.
  * [ScalaTest for Scala IDE](http://www.scalatest.org/user_guide/using_scalatest_with_eclipse) plug-in.
1. The latest editions of the Scala IDE (currently 4.15) only support Scala 2.11, hence you need to install 2.12 separately.
  * Download and install [Scala 2.12](https://www.scala-lang.org/download/) (The archive is enough, no need for the installer).
  * Set your Scala distribution [for the Scala IDE](http://scala-ide.org/blog/scala-installations.html#BYOS).
    * Go to **Window** | **Preferences** | **Scala** | **Installations**, click **Add**, browse the directory for the Scala JARs, name the installation as `Scala` and click **OK**.
    * :bulb: On Ubuntu-based systems, the Scala JARs are located in `/usr/share/scala/lib`.

:notebook_with_decorative_cover: The _DSL Developers_ flavor of Eclipse has all the required dependencies. If you start from another Eclipse flavor/distribution, you should install the missing plug-ins:

* From the Marketplace:
  * If you do not have Buildship: go to the **Eclipse Marketplace**, install the **Buildship: Eclipse Plug-ins for Gradle** plug-in. You may also want to install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki> to provide an editor for the `.gradle` configuration files.
* From the update site of your Eclipse release (e.g. the Oxygen update site):
  * **Xtend IDE**
  * **Xtext SDK**
  * **EMF - Eclipse Modeling Framework Xcore SDK**

#### Importing the projects

1. Build the projects from command line with the following command:

  ```bash
  ./gradlew clean build eclipse -x test
  ```
1. Import the project with **Import...** | **Gradle** | **Gradle Project**, select the directory of this repository. When prompted whether to overwrite the existing project files, click **Keep**. (This is required for the VIATRA projects, as they require custom natures to work properly.)
1. To fix the Scala projects (`ire` and `ingraph-ire`), go to each one and change the Scala library to 2.12: project **Properties** | **Java Build Path** | **Libraries**, **Remove** the previous one, click **Add Library...** | **Scala Library** | **Next** and pick the one for **2.12**.

### IntelliJ IDEA

* Install the [Scala plug-in](https://plugins.jetbrains.com/idea/plugin/1347-scala)
* Install the [Xtend plug-in](https://plugins.jetbrains.com/idea/plugin/8073-xtend-support)
* Import the projects using **New** | **Project from Existing Sources...**. Untick **Create separate module per source set**.
* After importing, add the Scala SDK (version 2.12) to the module. (If you did not
use Scala before, download it from the dialog box provided by IntelliJ).

### Troubleshooting

#### General debug mode

For the modules that support it, you can define environment variables to enable debug mode:

* `DEBUG=1` or `DEBUG_INGRAPH=1` enables debug mode for all modules
* `DEBUG_<MODULENAME>=1` (e.g. `DEBUG_INGRAPH_CYPHER2RELALG=1`) enables debug mode for that particular module

Currently, these modules have support:

* `ingraph-cypher2relalg`

#### Eclipse

* Xtend classes do not compile
  * **Problem:** Xtend classes do not compile due to character encoding errors.
  * **Solution:** change the character encoding to **UTF-8** (preferably for the whole workspace, in **Window** | **Preferences** | **General** | **Workspace** | **Text file encoding**).
* Gradle error
  * **Problem:** during import, you may encounter the following error:

    ```
    Synchronize Gradle builds with workspace failed due to an unexpected error.
    Unsupported method: HierarchicalEclipseProject.getIdentifier().
    ```
  * **Solution:** click **Previous** and set the **Gradle wrapper**'s version manually to 3.0 (in our experience, 3.1 and 3.2 do not work).

* Xtext generation error

  * **Problem:** during the `generateXtext` task, you get the following error:

    ```
    java.lang.IllegalArgumentException: The 'no null' constraint is violated
    ```
  * **Solution:** [terminate the Gradle daemon](https://github.com/xtext/xtext-gradle-plugin/issues/58#issue-167052300), e.g. use `./gradlew --stop`. If the problem persists, you might want to [disable Gradle daemons](https://docs.gradle.org/current/userguide/gradle_daemon.html#sec:disabling_the_daemon).

  * **Problem:**

    ```
    ERROR:Ecore cannot be resolved. (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 1 column : 2)
    ERROR:GenModel cannot be resolved. (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 2 column : 2)
    ERROR:A generic type in this context must refer to a classifier or a type parameter (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 10 column : null)
    ERROR:A generic type in this context must refer to a classifier or a type parameter (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 17 column : null)
    ERROR:The default value literal 'false' must be a valid literal of the attribute's type (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 149 column : null)
    ```

  * **Solution:** include the `clean` task, i.e. run `./gradlew clean build`

#### IntelliJ

* `NoClassDefFoundError` for Scala code:
  * **Problem:** The code compiles, but running the errors results in the following error:

    ```
    java.lang.NoClassDefFoundError: akka/testkit/ImplicitSender$class
    ```
  * **Solution:** update the Scala SDK in your project to 2.12.


#### How to run the tests

Running Xtend JUnit tests from a Gradle project is tricky. If you encounter a `ClassNotFound` exception, you should navigate the cursor to the **name of the class**, not the name of a test method, and press <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>X</kbd>, <kbd>T</kbd>.

#### How to update the relational algebra model

Go to `ingraph-relalg-model` project, navigate to the `src/main/resources` directory and open the `relalg.xcore` file. The code is regenerated on every save operation. If there are errors in the generated code, it's worth deleting the `build/xcore/main` directory manually.

#### How to update the grammars for parsing the Cucumber tests

Go to the `ingraph-report` project, navigate to the `src/main/java` source folder, and right click the `ingraph.report` package's `GenerateFeature.mwe2` file and choose **Run As** | **MWE2 Workflow** and ignore the warning message.

#### Opening `cypxmi` models

If you want to investigate the generated Xtext models, generate the `cypxmi` files using the provided unit tests.

To open them, you have to import the [`org.slizaa.neo4j.opencypher`](https://github.com/slizaa/slizaa-opencypher-xtext/tree/master/plugins/org.slizaa.neo4j.opencypher) project from the [slizaa-opencypher-xtext](https://github.com/slizaa/slizaa-opencypher-xtext) repository. You should use the **Sample Reflective Ecore Model Editor** for opening these models (and make that editor the default for the `cypxmi` files).

### IntelliJ IDEA

In IntelliJ, go to **File** | **Settings** | **Plug-ins**, search for `Xtend`, click **Search in repositories** and install the plug-in.

### Gradle

To build the projects from command line, use the following command:

```bash
./gradlew clean build --continue
```

If you get `duplicate class` errors for the Xtend classes, you probably omitted the `clean` target from the Gradle build.

## Using from other projects

To deploy the artifacts in your local Maven repository, issue `gradle publishToMavenLocal`.

### Deploying to Bintray

To upload the binary package of the driver to [Bintray](https://bintray.com/ftsrg/maven/ingraph), get your [Bintray API key](https://bintray.com/profile/edit/) and use the following commands:

```
# set your username (e.g. szarnyasg)
$ export BINTRAY_USER=
# set your Bintray API key (from https://bintray.com/profile/edit/, **API Key**)
$ export BINTRAY_KEY=
# upload driver
$ ./gradlew ingraph-driver:bintray
```

Go to the Bintray [Maven repository site](https://bintray.com/ftsrg/maven/ingraph-driver) and click **Publish**.

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
