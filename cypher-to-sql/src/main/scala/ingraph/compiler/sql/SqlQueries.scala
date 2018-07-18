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
      |    value TEXT NOT NULL,
      |    UNIQUE(parent, key)
      |);
      |
      |CREATE TABLE edge_property
      |(
      |    parent INTEGER NOT NULL REFERENCES edge (edge_id),
      |    key TEXT NOT NULL,
      |    value TEXT NOT NULL,
      |    UNIQUE(parent, key)
      |);
    """.stripMargin
}
