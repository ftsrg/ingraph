PRAGMA foreign_keys = TRUE;

-- https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L52
-- Use multiple MATCH clauses to do a Cartesian product
-- CREATE ({value: 1}), ({value: 2}), ({value: 3})

INSERT INTO vertex DEFAULT VALUES;
INSERT INTO vertex_property(parent, key, value) VALUES (last_insert_rowid(), 'value', 1);

INSERT INTO vertex DEFAULT VALUES;
INSERT INTO vertex_property(parent, key, value) VALUES (last_insert_rowid(), 'value', 2);

INSERT INTO vertex DEFAULT VALUES;
INSERT INTO vertex_property(parent, key, value) VALUES (last_insert_rowid(), 'value', 3);
/*
Query:
------
MATCH (n), (m)
RETURN n.value AS n, m.value AS m
      
-----------------------------------------------------------------------------
QPlan:
------
'Production
+- 'Projection [ret(p(value, v(n, {}, false, (n#3)), (n$value#0)), (n), (n#4)), ret(p(value, v(m, {}, false, (m#1)), (m$value#0)), (m), (m#2))]
   +- 'AllDifferent
      +- 'Join
         :- 'GetVertices v(n, {}, false, (n#3))
         +- 'GetVertices v(m, {}, false, (m#1))

-----------------------------------------------------------------------------
JPlan:
------
'Production
+- 'Projection [ret(p(value, v(n, {}, false, (n#3)), (n$value#0)), (n), (n#4)), ret(p(value, v(m, {}, false, (m#1)), (m$value#0)), (m), (m#2))]
   +- 'AllDifferent
      +- 'Join Left()
         :- 'GetVertices v(n, {}, false, (n#3))
         +- 'GetVertices v(m, {}, false, (m#1))

-----------------------------------------------------------------------------
FPlan:
------
'Production
+- 'Projection [ret(p(value, v(n, {}, false, (n#3)), (n$value#0)), (n), (n#4)), ret(p(value, v(m, {}, false, (m#1)), (m$value#0)), (m), (m#2))]
   +- 'AllDifferent
      +- 'Join Left()
         :- 'GetVertices [p(value, v(n, {}, false, (n#3)), (n$value#0))], 'GetVertices v(n, {}, false, (n#3))
         +- 'GetVertices [p(value, v(m, {}, false, (m#1)), (m$value#0))], 'GetVertices v(m, {}, false, (m#1))

-----------------------------------------------------------------------------
*/

SELECT n_value AS n, m_value AS m FROM
  (
  SELECT * FROM
  (SELECT vertex_id AS n, (SELECT value FROM vertex_property WHERE parent = vertex_id AND key = 'value') AS n_value FROM vertex)
  ,
  (SELECT vertex_id AS m, (SELECT value FROM vertex_property WHERE parent = vertex_id AND key = 'value') AS m_value FROM vertex)
  )
;


-- https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L97
-- Filter out based on node prop name

-- CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})
-- CREATE (v1 {name: 'Someone'})<-[e1 :X]-(v2)-[e2 :X]->(v3 {name: 'Andres'})
DROP TABLE IF EXISTS _var;
CREATE TEMP TABLE _var (name TEXT PRIMARY KEY, value BLOB);

-- v1
INSERT INTO vertex DEFAULT VALUES;
INSERT INTO _var(name, value) VALUES ('v1', last_insert_rowid());
INSERT INTO vertex_property(parent, key, value) VALUES ((SELECT value FROM _var WHERE name = 'v1'), 'name', 'Someone');

-- v2
INSERT INTO vertex DEFAULT VALUES;
INSERT INTO _var(name, value) VALUES ('v2', last_insert_rowid());
-- v3
INSERT INTO vertex DEFAULT VALUES;
INSERT INTO _var(name, value) VALUES ('v3', last_insert_rowid());
INSERT INTO vertex_property(parent, key, value) VALUES ((SELECT value FROM _var WHERE name = 'v3'), 'name', 'Andres');

-- e1
INSERT INTO edge("from", "to", type) VALUES ((SELECT value FROM _var WHERE name = 'v2'), (SELECT value FROM _var WHERE name = 'v1'), 'X');
INSERT INTO _var(name, value) VALUES ('e1', last_insert_rowid());
-- e2
INSERT INTO edge("from", "to", type) VALUES ((SELECT value FROM _var WHERE name = 'v2'), (SELECT value FROM _var WHERE name = 'v3'), 'X');
INSERT INTO _var(name, value) VALUES ('e2', last_insert_rowid());


DROP TABLE IF EXISTS _var;

/*
=============================================================================
Query:
------
MATCH ()-[rel:X]-(a)
WHERE a.name = 'Andres'
RETURN a
      
-----------------------------------------------------------------------------
QPlan:
------
'Production
+- 'Projection [ret(v(a, {}, false, (a#0)), None, (a#0))]
   +- 'Selection (p(name, v(a, {}, false, (a#0)), (a$name#0)) = Andres)
      +- 'AllDifferent
         +- 'Expand v(_e4, {}, true, (_e4#0)), v(a, {}, false, (a#0)), e(rel, {X}, false, (rel#0)), Both
            +- 'GetVertices v(_e4, {}, true, (_e4#0))

-----------------------------------------------------------------------------
JPlan:
------
'Production
+- 'Projection [ret(v(a, {}, false, (a#0)), None, (a#0))]
   +- 'Selection (p(name, v(a, {}, false, (a#0)), (a$name#0)) = Andres)
      +- 'AllDifferent
         +- 'GetEdges v(_e4, {}, true, (_e4#0)), v(a, {}, false, (a#0)), e(rel, {X}, false, (rel#0)), false

-----------------------------------------------------------------------------
FPlan:
------
'Production
+- 'Projection [ret(v(a, {}, false, (a#0)), None, (a#0))]
   +- 'Selection (p(name, v(a, {}, false, (a#0)), (a$name#0)) = Andres)
      +- 'AllDifferent
         +- 'GetEdges [p(name, v(a, {}, false, (a#0)), (a$name#0))], 'GetEdges v(_e4, {}, true, (_e4#0)), v(a, {}, false, (a#0)), e(rel, {X}, false, (rel#0)), false

-----------------------------------------------------------------------------
*/

SELECT "a#0" FROM
  (
    SELECT * FROM
      (
        SELECT *, (SELECT value FROM vertex_property WHERE parent = "a#0" AND key = 'name') AS "a$name#0" FROM
          (
            SELECT "from" AS "a#0", edge_id AS "rel#0", "to" AS "_e4#0" FROM edge
            UNION ALL
            SELECT "to", edge_id, "from" FROM edge
          )
      )
    WHERE "a$name#0" = 'Andres'
  );
