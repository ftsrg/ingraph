name := "project-work"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

libraryDependencies +=  "com.typesafe.akka" % "akka-actor_2.11" % "2.4-SNAPSHOT"

libraryDependencies +=  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4-SNAPSHOT"

libraryDependencies += "org.w3" % "banana-rdf_2.11" % "0.8.1"

libraryDependencies += "org.w3" % "banana-jena_2.11" % "0.8.1"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"