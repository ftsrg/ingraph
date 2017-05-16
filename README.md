# ingraph

[![Build Status](https://travis-ci.org/FTSRG/ingraph.svg?branch=master)](https://travis-ci.org/FTSRG/ingraph)

[[report on openCypher TCK results]](http://docs.inf.mit.bme.hu/ingraph/pub/opencypher-report.pdf) | [[regression tests]](http://docs.inf.mit.bme.hu/ingraph/tests/regressionTests/)

For a summary on the project, visit the [ingraph web page](http://docs.inf.mit.bme.hu/ingraph/).

## Projects

ingraph consists of two main projects:

1. `ingraph-compiler`, which compiles openCypher queries to relational algebra search plans / Rete networks. The compiler components are built using EMF-based technologies (Xcore, Xtend, Xtext and VIATRA), so we recommended the Eclipse IDE for developing them.

2. `ingraph-engine`, which evaluates the search plans / Rete networks. The engine is built using Scala-based technologies (Akka, ScalaTest and Scala Cukes), so we recommend contribtors to use IntelliJ IDEA for developing these components.

## Pages

* [Contributor's guide](contributors-guide.md)
* [Troubleshooting](troubleshooting.md)
* [Deploying binary artifacts](deploying-binary-artifacts.md)

## Third-party dependencies

For parsing queries, we use the [Slizaa openCypher Xtext grammar](https://github.com/slizaa/slizaa-opencypher-xtext).

## License

All code in this repository is available under the [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html). The project was supported by the MONDO EU FP7 (EU ICT-611125) project and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
