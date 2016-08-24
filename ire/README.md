# Scala implementation of the Rete algorithm

[![Build Status](https://travis-ci.com/wafle/gqo.svg?token=zJXFEucjUmoe79eTxUYj&branch=master)](https://travis-ci.com/wafle/gqo)

The Rete algorithm is a way of doing incremental pattern matching. The point of it being incremental, is
that small changes to the model don't require full reevaluation, and hence can be done very quickly.

This repository contains the implementation of a  truly scalable distributed Rete network, that is able to split the memory requirement of the Rete nodes amongst multiple machines, by sharding them.

## Prerequisites

The build requires SBT.

* [Installing sbt on Linux](http://www.scala-sbt.org/0.13/docs/Installing-sbt-on-Linux.html)
