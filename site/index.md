---
layout: default
---

# Project proposal

The aim of the [ingraph project](https://github.com/FTSRG/ingraph) is to evaluate [openCypher](http://www.opencypher.org/) graph queries incrementally. Our long-term goal is to provide a horizontally scalable graph query engine, which is able to perform complex graph queries on graphs with 100M+ elements.

## openCypher

The openCypher project is an initiative to standardize the [Cypher query language](https://neo4j.com/docs/developer-manual/current/cypher/) of [Neo4j](https://neo4j.com/).

## Technical goals

Understanding the [big data landscape](img/Big-Data-Landscape-2016-v18-FINAL.png) is a challenging task. Even for processing graphs, there are dozens of available technologies. For ingraph, we try to clarify the technical goals of the project, listing both the strengths and limitations of the project.

### Data model

ingraph operates on the [property graph data model](https://arxiv.org/abs/1006.2361). In computer science, property graphs are also known as [typed](http://link.springer.com/chapter/10.1007/3-540-45832-8_14)/[heterogeneous](http://www.sciencedirect.com/science/article/pii/S0957417412009657) [attributed](http://dl.acm.org/citation.cfm?id=1281271) graphs.

Typed graphs are also used in other disciplines, usually for analysis with the tools of [network theory](https://en.wikipedia.org/wiki/Network_theory). Related terms include multidimensional networks ([social network analysis](http://link.springer.com/article/10.1007/s11280-012-0190-4)), multiplex networks ([physics](https://arxiv.org/abs/1403.1546)) and multi[-]layer[ed] networks ([social network analysis](https://arxiv.org/abs/1207.4293) and [physics](https://arxiv.org/abs/1309.7233)).

### Suited for

ingraph is suited for the following technical challenges:

* Standing queries with a continuously changing graph
* Global analytical graph patterns
* Evaluating queries over a runtime (live) model

We also plan to include stream processing features (e.g. defining windows), but currently we focus on implementing basic pattern matching features.

### Not suited for

ingraph is not efficient/expressive enough for the following technical challenges:

* Queries that are evaluated only evaluated once or infrequently (i.e. batch processing, daily analysis) [our main goal for the summer of 2017 is to resolve this issue, so stay tuned]
* Graph analytics involving PageRank, community detection, etc.

Currently, the ingraph project is not mature enough for production use. Instead, it should be used in prototypes and performance experiments.

## Use cases

Candidates for primary use cases of incremental openCypher queries are:

* Model validation ([IncQuery-D paper](pub/models2014-incqueryd.pdf), [Train Benchmark paper](https://inf.mit.bme.hu/research/publications/train-benchmark-cross-technology-performance-evaluation-continuous-model-valid))
* Static analysis of source code repositories (thesis work [#1](pub/stein-daniel-msc.pdf), [#2](pub/lucz-soma-bsc.pdf))
* Fraud detection ([Neo4j white paper](https://neo4j.com/use-cases/fraud-detection/))
* Model simulation and analysis of runtime models

Incremental openCypher queries can also be beneficial for:

* Recommendation engines ([Neo4j white paper](https://neo4j.com/resources/recommendations-business-white-paper/))
* Stream processing ([real-time urban monitoring](http://link.springer.com/chapter/10.1007/978-3-642-41338-4_12))

# Publications

## Papers

* J. Marton, G. Szárnyas, D. Varró: [Formalising openCypher Graph Queries in Relational Algebra](https://arxiv.org/abs/1705.02844) (ADBIS 2017)
* G. Szárnyas, B. Izsó, I. Ráth, D. Varró: [The Train Benchmark: Cross-Technology Performance Evaluation of Continuous Model Validation](http://link.springer.com/article/10.1007/s10270-016-0571-8) (Software and Systems Modeling Journal, 2017)
* G. Szárnyas, B. Izsó, I. Ráth, D. Harmath, G. Bergmann, D. Varró: [IncQuery-D: A Distributed Incremental Model Query Framework in the Cloud](pub/models2014-incqueryd.pdf) (MODELS 2014)
* G. Szárnyas, J. Maginecz, D. Varró: [Evaluation of Optimization Strategies for Incremental Graph Queries](https://pp.bme.hu/eecs/article/view/9769) (Periodica Polytechnica 2017)
* J. Maginecz, G. Szárnyas, [Sharded Joins for Scalable Incremental Graph Queries](pub/minisy2016-sharded-joins-for-scalable-incremental-graph-queries.pdf) (PhD Minisymposium 2016)

## Talks

* [ingraph: Live Queries on Graphs](https://www.youtube.com/watch?v=uLu2w8JxMKo) ([slides](https://www.slideshare.net/neo4j/graphconnect-europe-2017-ingraph-live-queries-on-graphs)), GraphConnect 2017 lightning talk
* [The ingraph project and incremental evaluation of Cypher queries](https://s3.amazonaws.com/artifacts.opencypher.org/website/ocim2/slides/ocim2-ingraph.pdf), 2nd openCypher Implementers Meeting
* [Incremental Graph Queries for Cypher](https://s3.amazonaws.com/artifacts.opencypher.org/website/ocim1/slides/ocim2017-incremental-opencypher.pdf), 1st openCypher Implementers Meeting
* [Incremental Graph Queries with openCypher](https://fosdem.org/2017/schedule/event/graph_incremental_queries_open_cypher/), FOSDEM 2017, Graph devroom
* [The Train Benchmark: Cross-Technology Performance Evaluation of Continuous Model Queries](http://wiki.ldbcouncil.org/pages/viewpage.action?pageId=59277315), Linked Data Benchmark Council, 9th TUC meeting

# Related projects on GitHub

* [ingraph](https://github.com/FTSRG/ingraph)
* [Train Benchmark](https://github.com/FTSRG/trainbenchmark), a benchmark framework for continuous model validation
