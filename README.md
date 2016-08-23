# ingraph

[![Build Status](https://travis-ci.com/bme-db-lab/ingraph.svg?token=dduaCwDzExdmU27AvBiK&branch=master)](https://travis-ci.com/bme-db-lab/ingraph)

## Contributor's Guide

### Eclipse

Start with Eclipse Modeling and install Xtend or start with Eclipse DSL.

For the Gradle projects, go to the **Eclipse Marketplace**, e.g. the **Buildship: Eclipse Plug-ins for Gradle**. You may also want to install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki> to provide an editor for the `.gradle` configuration files.

#### Troubleshooting for Eclipse

If you have compile errors in the imported Xtend projects, remove the `build/` directories from the projects.

### IntelliJ IDEA

In IntelliJ, go to **File** | **Settings** | **Plug-ins**, search for `Xtend`, click **Search in repositories** and install the plug-in.

### Gradle

To build the projects from command line

If you get `duplicate class` errors for the Xtend classes, you probably omitted the `clean` target from the Gradle build.
