# Spark Catalyst

## Docs and posts

* [Blog post on Catalsyst](https://databricks.com/blog/2015/04/13/deep-dive-into-spark-sqls-catalyst-optimizer.html)
* [TreeNode](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-sql-catalyst-TreeNode.html)

## Presentations

* [A Deep Dive into Spark SQL's Catalyst Optimizer with Yin Huai](https://www.slideshare.net/databricks/a-deep-dive-into-spark-sqls-catalyst-optimizer-with-yin-huai)
* [Adding Native SQL Support to Spark with Catalyst](https://www.youtube.com/watch?v=ju2OQEXqONU&list=PL-x35fyliRwiP3YteXbnhk0QGOtYLBT3a) ([slides](http://files.meetup.com/3138542/Spark%20SQL%20Meetup%20-%204-8-2012.pdf)) by Michael Armbrust, at Tagged in SF, 2014-04-08

## Operators of Catalyst

Some of our operators for the basic (non-incremental) query plan are similar to the operators of Spark SQL:

| qplan | Catalyst |
| ----- | -------- |
| `AntiJoin` | `plans.LeftAnti` |
| `Dual` | `OneRowRelation` |
| `Projection` | `plans.logical.Project` |
| `Selection` | `plans.logical.Filter` |
| `Selection` | `plans.logical.Filter` |
| `Top` | `plans.logical.GlobalLimit` |
