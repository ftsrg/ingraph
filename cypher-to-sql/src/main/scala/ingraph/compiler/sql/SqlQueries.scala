package ingraph.compiler.sql

object SqlQueries {

  val purge =
    """DROP SCHEMA IF EXISTS PUBLIC CASCADE;
      |CREATE SCHEMA PUBLIC AUTHORIZATION "postgres";
      |GRANT ALL ON SCHEMA public TO public;""".stripMargin

  val createTables =
    """CREATE SEQUENCE vertex_seq;
      |CREATE SEQUENCE edge_seq;
      |
      |CREATE TABLE vertex
      |(
      |  vertex_id BIGINT PRIMARY KEY
      |);
      |
      |CREATE TABLE edge
      |(
      |  edge_id BIGINT PRIMARY KEY,
      |  "from"  BIGINT NOT NULL REFERENCES vertex (vertex_id),
      |  "to"    BIGINT NOT NULL REFERENCES vertex (vertex_id),
      |  type    TEXT    NOT NULL
      |);
      |
      |CREATE TABLE label
      |(
      |  parent BIGINT NOT NULL REFERENCES vertex (vertex_id),
      |  name   TEXT    NOT NULL,
      |  UNIQUE (parent, name)
      |);
      |
      |CREATE TABLE vertex_property
      |(
      |  parent BIGINT NOT NULL REFERENCES vertex (vertex_id),
      |  key    TEXT    NOT NULL,
      |  value  jsonb   NOT NULL,
      |  UNIQUE (parent, key)
      |);
      |
      |CREATE TABLE edge_property
      |(
      |  parent BIGINT NOT NULL REFERENCES edge (edge_id),
      |  key    TEXT    NOT NULL,
      |  value  jsonb   NOT NULL,
      |  UNIQUE (parent, key)
      |);
      |
      |CREATE FUNCTION labels(BIGINT)
      |  RETURNS TEXT []
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(array_agg(name), ARRAY[] :: text [])
      | FROM label
      | WHERE parent = $1';
      |
      |CREATE FUNCTION vertex_properties(BIGINT)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(jsonb_object_agg(key, value), ''{}'')
      | FROM vertex_property
      | WHERE parent = $1';
      |
      |CREATE FUNCTION edge_properties(BIGINT)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(jsonb_object_agg(key, value), ''{}'')
      | FROM edge_property
      | WHERE parent = $1';
      |
      |CREATE FUNCTION to_vertex(BIGINT)
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
      |CREATE FUNCTION startNode(BIGINT)
      |  RETURNS BIGINT
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT "from"
      | FROM edge
      | WHERE edge_id = $1';
      |
      |CREATE FUNCTION endNode(BIGINT)
      |  RETURNS BIGINT
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT "to"
      | FROM edge
      | WHERE edge_id = $1';
      |
      |CREATE FUNCTION type_string(BIGINT)
      |  RETURNS TEXT
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT type
      | FROM edge
      | WHERE edge_id = $1';
      |
      |CREATE FUNCTION type(BIGINT)
      |  RETURNS jsonb
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT jsonb_build_object(''type'', ''StringValue'',
      |                           ''value'', jsonb_build_object(
      |                                        ''val'', type_string($1)))';
      |
      |CREATE FUNCTION to_edge(BIGINT)
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
    """.stripMargin

  val utilityFunctions =
    """CREATE OR REPLACE FUNCTION array_length(ANYARRAY)
      |  RETURNS INTEGER
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT coalesce(array_length($1, 1), 0) AS array_length;';
      |
      |-- NULL values are filtered by count()
      |CREATE OR REPLACE FUNCTION is_unique(ANYARRAY)
      |  RETURNS BOOLEAN
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT count(val) = count(DISTINCT val)
      | FROM unnest($1) AS vals (val);';
      |
      |DROP TYPE IF EXISTS edge_type;
      |CREATE TYPE edge_type AS (
      |  src BIGINT,
      |  trg BIGINT
      |);
      |
      |CREATE OR REPLACE FUNCTION TO_TIMESTAMP(BIGINT)
      |  RETURNS TIMESTAMP WITHOUT TIME ZONE
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT TO_TIMESTAMP($1 :: text, ''YYYYMMDDHH24MISSMS'') :: TIMESTAMP WITHOUT TIME ZONE';
      |
      |CREATE OR REPLACE FUNCTION TO_BIGINT(TIMESTAMP WITHOUT TIME ZONE)
      |  RETURNS BIGINT
      |STRICT
      |IMMUTABLE
      |LANGUAGE SQL AS
      |'SELECT to_char($1, ''YYYYMMDDHH24MISSMS'') :: BIGINT';
    """.stripMargin
}
