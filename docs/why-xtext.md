# Why Xtext?

Instead of using the generated [ANTLR4/ENBF grammars](http://www.opencypher.org/#resources), we decided to use [Xtext](https://eclipse.org/Xtext/).

* Xtext is able to create a syntax graph, not just a syntax tree. For example, through scoping it is easy to decide if variable `n` refers to the same variable in `MATCH (n) RETURN n`.
* The resulting syntax graph is much cleaner, e.g. compared to ANTLR, arithmetic and logical expressions can be simplified a lot.
* With the Xtext grammar, we also [get an editor for free](http://blog.efftinge.de/2015/09/what-i-learned-at-javazone-2015.html), which also works [on the web](http://www.eclipse.org/Xtext/documentation/330_web_support.html) (with some limitations).
* The Gradle integration of the X* technologies (Xtext, Xtend, Xcore) is very good, see the [Xtext Gradle Plugins](https://github.com/xtext/xtext-gradle-plugin) repository.
* Xtext is not exclusive to Eclipse, there is some support in IntelliJ through the [Xtext plugin](https://plugins.jetbrains.com/plugin/8074).
* At the MODELS 2016 conference, Dimitris Kolovos, the creator of [Epsilon](http://www.eclipse.org/epsilon/) said ["I wish we could have built on top of Xtext."](https://twitter.com/richpaige/status/784011354009206785) (instead of implementing the grammar in ANTLR3).
* We use EMF models to represent the relational algebra expressions, so Xtext is a good fit to the other technologies in the project.
* Finally, we discovered that there is already an Xtext-based parser for openCypher: the [slizaa-opencypher-xtext project](https://github.com/slizaa/slizaa-opencypher-xtext).

For a detailed look on how we process queries, see the [workflow of ingraph](ingraph-workflow.md).
