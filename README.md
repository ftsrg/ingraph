# ingraph

[![Build Status](https://travis-ci.com/bme-db-lab/ingraph.svg?token=dduaCwDzExdmU27AvBiK&branch=master)](https://travis-ci.com/bme-db-lab/ingraph)

## Contributor's Guide

### Eclipse

1. Start with Eclipse Modeling.
1. Install the **Xtend IDE** from the Mars/Neon update site.
1. Go to the **Eclipse Marketplace**, e.g. the **Buildship: Eclipse Plug-ins for Gradle**. You may also want to install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki> to provide an editor for the `.gradle` configuration files.

#### How to update the EMF model

1. Go to the **ingraph-relalg-model** project.
1. Open the **Modeling** perspective (`Ctrl`+`3`, Modeling).
1. Open the `model/relalg.aird` file by double clicking on it, then clicking the small black triangle (left to the file name). Open **Representations per category**, **Design**, **Entities**, **relalg**.

#### How to regenerate the code

1. Go to the **ingraph-relalg-model** project.
1. Open the `model/relalg.genmodel` file.
1. Right click the root node of the tree and choose **Reload...**. Select **Ecore model**, click **Next** and click **Load**. Click **Next** and **Finish**.
1. Right click the root node of the tree and choose **Generate Model Code**.

#### Troubleshooting for Eclipse

If you have compile errors in the imported Xtend projects, remove the `build/` directories from the projects.

### IntelliJ IDEA

In IntelliJ, go to **File** | **Settings** | **Plug-ins**, search for `Xtend`, click **Search in repositories** and install the plug-in.

### Gradle

To build the projects from command line

If you get `duplicate class` errors for the Xtend classes, you probably omitted the `clean` target from the Gradle build.
