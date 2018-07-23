package ingraph.compiler.sql

object SqlQueries {
  val createTables =
    """-- createTables
      |CREATE TABLE vertex
      |(
      |  vertex_id INTEGER PRIMARY KEY
      |);
      |
      |CREATE TABLE edge
      |(
      |	edge_id INTEGER PRIMARY KEY,
      |	"from" INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |	"to" INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |	type TEXT NOT NULL
      |);
      |
      |CREATE TABLE label
      |(
      |    parent INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |    name TEXT NOT NULL,
      |    UNIQUE(parent, name)
      |);
      |
      |CREATE TABLE vertex_property
      |(
      |    parent INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |    key TEXT NOT NULL,
      |    value jsonb NOT NULL,
      |    UNIQUE(parent, key)
      |);
      |
      |CREATE TABLE edge_property
      |(
      |    parent INTEGER NOT NULL REFERENCES edge (edge_id),
      |    key TEXT NOT NULL,
      |    value jsonb NOT NULL,
      |    UNIQUE(parent, key)
      |);
      |
      |CREATE EXTENSION intarray;
      |
      |-- if needed for other types, check https://stackoverflow.com/q/3994556
      |CREATE FUNCTION is_unique(INTEGER [])
      |  RETURNS BOOLEAN
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'WITH arr AS (SELECT $1 AS arr)
      |SELECT array_length(uniq(sort(arr)), 1) = array_length(arr, 1) as is_unique
      |FROM arr;';
    """.stripMargin
}
