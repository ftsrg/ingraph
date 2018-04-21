PRAGMA foreign_keys = FALSE;

DROP TABLE IF EXISTS edge;
DROP TABLE IF EXISTS label;
DROP TABLE IF EXISTS vertex;
DROP TABLE IF EXISTS vertex_property;
DROP TABLE IF EXISTS edge_property;

PRAGMA foreign_keys = TRUE;

CREATE TABLE vertex
(
  vertex_id INTEGER PRIMARY KEY
);

CREATE TABLE edge
(
	edge_id INTEGER PRIMARY KEY,
	"from" INTEGER NOT NULL REFERENCES vertex (vertex_id),
	"to" INTEGER NOT NULL REFERENCES vertex (vertex_id),
	type TEXT NOT NULL
);

CREATE TABLE label
(
    parent INTEGER NOT NULL REFERENCES vertex (vertex_id),
    name TEXT NOT NULL,
    UNIQUE(parent, name)
);

CREATE TABLE vertex_property
(
    parent INTEGER NOT NULL REFERENCES vertex (vertex_id),
    key TEXT NOT NULL,
    value BLOB NOT NULL,
    UNIQUE(parent, key)
);

CREATE TABLE edge_property
(
    parent INTEGER NOT NULL REFERENCES edge (edge_id),
    key TEXT NOT NULL,
    value BLOB NOT NULL,
    UNIQUE(parent, key)
);