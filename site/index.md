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

### Not suited for

ingraph is not efficient/expressive enough for the following technical challenges:

* Queries that are evaluated only once (i.e. batch processing, daily analysis)
* Graph analytics involving PageRank, community detection, etc.

Currently, the ingraph project is not mature enough for production use. Instead, it should be used in prototypes and performance experiments.

## Use cases

Candidates for primary use cases of incremental openCypher queries are:

* Model validation ([IncQuery-D paper](pub/models2014-incqueryd.pdf))
* Static analysis of source code repositories ([technical report](pub/stein-daniel-static-analysis.pdf))
* Fraud detection ([Neo4j white paper](https://neo4j.com/use-cases/fraud-detection/))
* Model simulation and analysis of runtime models

Incremental openCypher queries could also be beneficial for:

* Recommendation engines ([Neo4j white paper](https://neo4j.com/resources/recommendations-business-white-paper/))

# Publications

* G. Szárnyas, J. Marton: [Formalizing openCypher Graph Queries in Relational Algebra](pub/btw2017-opencypher.pdf) (submitted to BTW 2017)
* G. Szárnyas, J. Maginecz, D. Varró: Evaluation of Optimization Strategies for Incremental Graph Queries (Periodica Polytechnica 2017, accepted)
* J. Maginecz, G. Szárnyas, [Sharded Joins for Scalable Incremental Graph Queries](pub/minisy2016-sharded-joins-for-scalable-incremental-graph-queries.pdf) (PhD Minisymposium 2016)
* G. Szárnyas, B. Izsó, I. Ráth, D. Harmath, G. Bergmann, D. Varró: [IncQuery-D: A Distributed Incremental Model Query Framework in the Cloud](pub/models2014-incqueryd.pdf) (MODELS 2014)

# Related projects

* [ingraph on GitHub](https://github.com/FTSRG/ingraph)
* ingraph uses [ire](https://github.com/FTSRG/ire), an incremental relational query engine