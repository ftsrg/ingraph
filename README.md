# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph)

## Third Party Sources

* The project uses the Xtext grammar of the [slizaa-opencypher-xtext project](https://github.com/slizaa/slizaa-opencypher-xtext/). See also the [Why Xtext?](docs/why-xtext.md) document.

## Generated Artifacts

* [Test summary](http://docs.inf.mit.bme.hu/ingraph/test/)
* [Technical report on compliance with the OpenCypher TCK](http://docs.inf.mit.bme.hu/ingraph/pub/opencypher-report.pdf)

## User's Guide

See the [ingraph website](http://docs.inf.mit.bme.hu/ingraph/).

## Contributor's Guide

### Eclipse

1. It is recommended to start with the **Eclipse IDE for Java Developers** distribution.
1. Install the following from your the update site of your Eclipse release (e.g. the Neon update site).
    * **Xtend IDE**
    * **Xtext SDK**
    * **EMF - Eclipse Modeling Framework Xcore SDK**
1. Import the project with **Import...** | **Gradle** | **Gradle Project**, select the directory of this repository. When prompted whether to overwrite the existing project files, click **Keep**. (This is required for the Modeling and VIATRA projects, as they require custom natures to work properly.)

If you do not have Buildship: go to the **Eclipse Marketplace**, e.g. the **Buildship: Eclipse Plug-ins for Gradle**. You may also want to install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki> to provide an editor for the `.gradle` configuration files.

#### How to update the relational algebra model

Go to `ingraph-relalg-xcore` project, navigate to the `src/main/resources` directory and open the `relalg.xcore` file. The code is regenerated on every save operation. If there are errors in the generated code, it's worth deleting the `src/main/java-gen` directory manually.

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

## License

The project uses the Apache 2.0 license and is supported by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
