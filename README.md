# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph) [ ![Download](https://api.bintray.com/packages/ftsrg/maven/ingraph/images/download.svg) ](https://bintray.com/ftsrg/maven/ingraph/_latestVersion)

## Third-party sources

* The project uses the Xtext grammar of the [slizaa-opencypher-xtext project](https://github.com/slizaa/slizaa-opencypher-xtext/). See also the [Why Xtext?](docs/why-xtext.md) document.

## Generated artifacts

* [Test summary](http://docs.inf.mit.bme.hu/ingraph/test/)
* [Technical report on compliance with the OpenCypher TCK](http://docs.inf.mit.bme.hu/ingraph/pub/opencypher-report.pdf)

## User's guide

For a high-level overview, see the [ingraph website](http://docs.inf.mit.bme.hu/ingraph/).

ingraph is released on [Bintray](https://bintray.com/ftsrg/maven/ire).

For Maven or Gradle, use the following snippets.

### Maven

```xml
<dependency>
  <groupId>ingraph</groupId>
  <artifactId>ingraph-...</artifactId>
  <version>0.1.0</version>
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
	compile 'ingraph:ingraph-...:0.1.0'
}
```

## Contributor's Guide

### Eclipse

1. It is recommended to start with the latest milestone (currently, [Oxygen M2](http://www.eclipse.org/downloads/packages/release/Oxygen/M2)) **Eclipse IDE for Java and DSL Developers** distribution. :notebook_with_decorative_cover:
1. Import the project with **Import...** | **Gradle** | **Gradle Project**, select the directory of this repository. When prompted whether to overwrite the existing project files, click **Keep**. (This is required for the VIATRA projects, as they require custom natures to work properly.)
1. Go to the **ingraph** parent project, right click and choose **Gradle** | **Refresh Gradle Project**. (_This is required for Buildship to notice the Xcore source files that were just generated._)
1. You may have to clean the workspace once.

:notebook_with_decorative_cover: The _DSL Developers_ flavor of Eclipse has all the required dependencies. If you start from another Eclipse flavor/distribution, you should install the missing plug-ins:

* From the Marketplace:
  * If you do not have Buildship: go to the **Eclipse Marketplace**, install the **Buildship: Eclipse Plug-ins for Gradle** plug-in. You may also want to install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki> to provide an editor for the `.gradle` configuration files.
* From the update site of your Eclipse release (e.g. the Oxygen update site).
  * **Xtend IDE**
  * **Xtext SDK**
  * **EMF - Eclipse Modeling Framework Xcore SDK**

#### Troubleshooting

* **Problem:** during import, you may encounter the following error:

  ```
  Synchronize Gradle builds with workspace failed due to an unexpected error.
  Unsupported method: HierarchicalEclipseProject.getIdentifier().
  ```

* **Solution:** click **Previous** and set the **Gradle wrapper**'s version manually to 3.0 (in our experience, 3.1 and 3.2 do not work).

#### How to run the tests

Running Xtend JUnit tests from a Gradle project is tricky. If you encounter a `ClassNotFound` exception, you should navigate the cursor to the **name of the class**, not the name of a test method, and press <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>X</kbd>, <kbd>T</kbd>.

#### How to update the relational algebra model

Go to `ingraph-relalg-xcore` project, navigate to the `src/main/resources` directory and open the `relalg.xcore` file. The code is regenerated on every save operation. If there are errors in the generated code, it's worth deleting the `src/main/java-gen` directory manually.

#### How to update the grammars

1. Go to the **ingraph-cypher-parser** project, navigate to the `src/main/java` source folder, and right click the `org.slizaa.neo4j.opencypher` package's `GenerateOpenCypher.mwe2` file and choose **Run As** | **MWE2 Workflow**. If you get a warning that there are errors in the project, click **Proceed**.
1. Go to the **ingraph-report** project, navigate to the `src/main/java` source folder, and right click the `ingraph.report` package's `GenerateFeature.mwe2` file and choose **Run As** | **MWE2 Workflow** and ignore the warning message.

#### Opening `cypxmi` models

The `cypxmi` files contain the models as parsed by Xtext.

To open them, you have to import the [org.slizaa.neo4j.opencypher](https://github.com/slizaa/slizaa-opencypher-xtext/tree/master/plugins/org.slizaa.neo4j.opencypher) project from the [slizaa-opencypher-xtext](https://github.com/slizaa/slizaa-opencypher-xtext) repository. You should use the **Sample Reflective Ecore Model Editor** for opening these models (and make that editor the default for the `cypxmi` files).

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

To upload the artifacts to [Bintray](https://bintray.com/ftsrg/maven/ire), use the following commands:

```
# set your username (e.g. szarnyasg)
$ export BINTRAY_USER=
# set your Bintray API key (from https://bintray.com/profile/edit/, **API Key**)
$ export BINTRAY_KEY=
# upload
$ gradle bintrayUpload
```

Go to the Bintray [Maven repository site](https://bintray.com/ftsrg/maven) and click **Publish**.

## License

The project uses the Apache 2.0 license and is supported by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
