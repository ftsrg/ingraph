# Scala implementation of the Rete algorithm

[![Build Status](https://api.travis-ci.org/FTSRG/incquery-ds.png)](https://travis-ci.org/FTSRG/incquery-ds)

The Rete algorithm is a way of doing incremental pattern matching. The point of it being incremental, is
that small changes to the model don't require full reevaluation, and hence can be done very quickly.

This repository contains the implementation of a  truly scalable distributed Rete network, that is able to split the memory requirement of the Rete nodes amongst multiple machines, by sharding them.
