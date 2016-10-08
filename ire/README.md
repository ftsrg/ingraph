# ire: an Incremental Relational Engine

[![Build Status](https://travis-ci.org/FTSRG/ire.svg)](https://travis-ci.org/FTSRG/ire)

## User's Guide

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
    <id>ire</id>
    <url>https://ftsrg.bintray.com/maven</url>
  </repository>
</repositories>
```

### Gradle

```groovy
repositories {
	maven { url "http://ftsrg.bintray.com/maven" }
}

dependencies {
	compile 'hu.bme.mit:ire:0.1.0'
}
```

## Contributor's Guide

To build the project, issue the following command:

```bash
gradle clean build
```

The project is implemented in Scala. We recommend using IntelliJ IDEA with the Scala plug-in or Eclipse with the [Scala IDE](http://scala-ide.org/).

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
