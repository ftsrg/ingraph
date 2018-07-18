package ingraph.compiler.sql

object SqlQueries {
  val createTables =
    """-- createTables
      |CREATE TYPE variant AS (
      |  text  TEXT,
      |  int   INTEGER
      |);
      |
      |CREATE FUNCTION to_variant(INTEGER)
      |  RETURNS variant
      |STRICT IMMUTABLE LANGUAGE SQL AS
      |'SELECT ROW(NULL, $1)::variant;';
      |
      |CREATE FUNCTION to_variant(TEXT)
      |  RETURNS variant
      |STRICT IMMUTABLE LANGUAGE SQL AS
      |'SELECT ROW($1, NULL)::variant;';
      |
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
      |    value variant NOT NULL,
      |    UNIQUE(parent, key)
      |);
      |
      |CREATE TABLE edge_property
      |(
      |    parent INTEGER NOT NULL REFERENCES edge (edge_id),
      |    key TEXT NOT NULL,
      |    value variant NOT NULL,
      |    UNIQUE(parent, key)
      |);
    """.stripMargin
}
