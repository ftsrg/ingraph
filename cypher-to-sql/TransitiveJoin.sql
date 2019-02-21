WITH RECURSIVE recursive_table AS (
  (
    WITH start AS (
        SELECT unnest(ARRAY [0]) AS origFrom
    )
    SELECT
      *,
      ARRAY [] :: integer [] AS edgeList,
      origFrom               AS next_from,
      origFrom               AS to
    FROM start
  )
  UNION ALL
  SELECT
    origFrom,
    (edgeList || id) AS edgeList,
    edges."to"       AS next_from,
    edges."to"
  FROM
    (-- GetEdges
      SELECT *
      FROM
        (
          SELECT
            "from"  AS "from",
            edge_id AS id,
            "to"    AS "to"
          FROM edge
          WHERE type IN ('CONTAINS')
          UNION ALL
          SELECT
            "to",
            edge_id,
            "from"
          FROM edge
          WHERE type IN ('CONTAINS')
        ) subquery) edges
    INNER JOIN recursive_table
      ON id <> ALL (edgeList) -- edge uniqueness
         AND next_from = "from"
         AND coalesce(array_length(edgeList, 1), 0) < 1
)
SELECT
  origFrom,
  edgeList,
  "to"
FROM recursive_table
WHERE coalesce(array_length(edgeList, 1), 0) >= 1