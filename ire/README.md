# Scala implementation of the Rete algorithm

[![Build Status](https://api.travis-ci.org/FTSRG/incqueryd-core.png)](https://travis-ci.org/FTSRG/incqueryd-core)

The Rete algorithm is a way of doing incremental pattern matching. The point of it being incremental, is
that small changes to the model don't require full reevaluation, and hence can be done very quickly.

Each node is implemented as an Akka actor and banana-rdf is used for rdf parsing.

The implemented queries are for [trainbenchmark](https://github.com/FTSRG/trainbenchmark). They send 1/10th
of the result set to the input, negated. To run them the .ttc files need to be serialized first:

```
./serializer ~/trainbenchmark/models/*.ttc
```

To run the benchmark for serialized models  with a message size of 1 ```benchmark_all.sh``` can be used:

```
./benchmark_all.sh ~/trainbenchmark/models/*.bin
```

To run the benchmark to test the runtime difference with different message times ```benchmark_messagesize.sh``` can be used:

```
./benchmark_messageize.sh ~/trainbenchmark/models/railway-repair-1.bin 1 4 8 16 32 64
```

