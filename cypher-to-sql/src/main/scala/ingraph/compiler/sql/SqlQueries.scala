package ingraph.compiler.sql

object SqlQueries {

  val purge =
    """DROP SCHEMA IF EXISTS PUBLIC CASCADE;
      |CREATE SCHEMA PUBLIC AUTHORIZATION "postgres";
      |GRANT ALL ON SCHEMA public TO public;""".stripMargin

  val createTables =
    """-- createTables
      |CREATE SEQUENCE vertex_seq;
      |CREATE SEQUENCE edge_seq;
      |
      |CREATE TABLE vertex
      |(
      |  vertex_id INTEGER PRIMARY KEY
      |);
      |
      |CREATE TABLE edge
      |(
      |  edge_id INTEGER PRIMARY KEY,
      |  "from"  INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |  "to"    INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |  type    TEXT    NOT NULL
      |);
      |
      |CREATE TABLE label
      |(
      |  parent INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |  name   TEXT    NOT NULL,
      |  UNIQUE (parent, name)
      |);
      |
      |CREATE TABLE vertex_property
      |(
      |  parent INTEGER NOT NULL REFERENCES vertex (vertex_id),
      |  key    TEXT    NOT NULL,
      |  value  jsonb   NOT NULL,
      |  UNIQUE (parent, key)
      |);
      |
      |CREATE TABLE edge_property
      |(
      |  parent INTEGER NOT NULL REFERENCES edge (edge_id),
      |  key    TEXT    NOT NULL,
      |  value  jsonb   NOT NULL,
      |  UNIQUE (parent, key)
      |);
      |
      |CREATE FUNCTION array_length(ANYARRAY)
      |  RETURNS INTEGER
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(array_length($1, 1), 0) AS array_length;';
      |
      |CREATE FUNCTION labels(INTEGER)
      |  RETURNS TEXT []
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(array_agg(name), ARRAY[] :: text [])
      | FROM label
      | WHERE parent = $1';
      |
      |CREATE FUNCTION vertex_properties(INTEGER)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(jsonb_object_agg(key, value), ''{}'')
      | FROM vertex_property
      | WHERE parent = $1';
      |
      |CREATE FUNCTION edge_properties(INTEGER)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(jsonb_object_agg(key, value), ''{}'')
      | FROM edge_property
      | WHERE parent = $1';
      |
      |CREATE FUNCTION to_vertex(INTEGER)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT jsonb_build_object(''type'', ''NodeValue'',
      |                           ''value'', jsonb_build_object(
      |                                        ''adapted'', jsonb_build_object(
      |                                                       ''type'', ''InternalNode'',
      |                                                       ''value'', jsonb_build_object(
      |                                                                    ''id'', $1,
      |                                                                    ''labels'', labels($1),
      |                                                                    ''properties'', vertex_properties($1)))))';
      |
      |CREATE FUNCTION startNode(INTEGER)
      |  RETURNS INTEGER
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT "from"
      | FROM edge
      | WHERE edge_id = $1';
      |
      |CREATE FUNCTION endNode(INTEGER)
      |  RETURNS INTEGER
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT "to"
      | FROM edge
      | WHERE edge_id = $1';
      |
      |CREATE FUNCTION type_string(INTEGER)
      |  RETURNS TEXT
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT type
      | FROM edge
      | WHERE edge_id = $1';
      |
      |CREATE FUNCTION type(INTEGER)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT jsonb_build_object(''type'', ''StringValue'',
      |                           ''value'', jsonb_build_object(
      |                                        ''val'', type_string($1)))';
      |
      |CREATE FUNCTION to_edge(INTEGER)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT jsonb_build_object(''type'', ''RelationshipValue'',
      |                           ''value'', jsonb_build_object(
      |                                        ''adapted'', jsonb_build_object(
      |                                                       ''type'', ''InternalRelationship'',
      |                                                       ''value'', jsonb_build_object(
      |                                                                    ''id'', $1,
      |                                                                    ''start'', startNode($1),
      |                                                                    ''end'', endNode($1),
      |                                                                    ''type'', type_string($1),
      |                                                                    ''properties'', edge_properties($1)))))';
      |
      |CREATE EXTENSION intarray;
      |
      |-- if needed for other types, check https://stackoverflow.com/q/3994556
      |CREATE FUNCTION is_unique(INTEGER [])
      |  RETURNS BOOLEAN
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'WITH arr AS (SELECT $1 AS arr),
      |      orig_length AS (SELECT array_length(arr) AS orig_length FROM arr)
      | SELECT (orig_length <= 1 OR array_length(uniq(sort(arr))) = orig_length) AS is_unique
      | FROM arr,
      |      orig_length;';
    """.stripMargin
}