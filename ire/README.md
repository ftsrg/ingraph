# ire: an Incremental Relational Engine

[![Build Status](https://travis-ci.org/FTSRG/ire.svg)](https://travis-ci.org/FTSRG/ire) [ ![Download](https://api.bintray.com/packages/ftsrg/maven/ire/images/download.svg) ](https://bintray.com/ftsrg/maven/ire/_latestVersion)

## User's guide

ire is released on [Bintray](https://bintray.com/ftsrg/maven/ire).

For Maven or Gradle, use the following snippets.

### Maven

```xml
<dependency>
  <groupId>hu.bme.mit</groupId>
  <artifactId>ire</artifactId>
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
	compile 'hu.bme.mit:ire:0.1.0'
}
```

## Contributor's guide

To build the project, issue the following command:

```bash
gradle clean build
```

The project is implemented in Scala. We recommend the following IDEs:
* IntelliJ IDEA with the [Scala plug-in](https://plugins.jetbrains.com/plugin/?id=1347)
* [Eclipse Oxygen](http://www.eclipse.org/downloads/packages/release/Oxygen/) with the [Scala IDE](http://scala-ide.org/). Do not forget to install the [ScalaTest for Scala IDE](http://www.scalatest.org/user_guide/using_scalatest_with_eclipse) plug-in.

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

### Troubleshooting

* **Problem:** The code compiles, but IntelliJ shows the following error when running the tests:

    > java.lang.NoClassDefFoundError: akka/testkit/ImplicitSender$class

* **Solution:** update the Scala SDK in your project to 2.12.

## Use cases

[ingraph](https://github.com/FTSRG/ingraph), an incremental graph query engine (based on the openCypher query language) uses ire as its computation engine.

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
