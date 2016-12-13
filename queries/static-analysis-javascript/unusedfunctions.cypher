/**
 * Get not used FunctionDeclarations
 */
MATCH
    // Find the exported FunctionDeclaration that may be an entrance point
    p = (main)-[:items]->(:ExportDeclaration)-[:declaration]->(fd:FunctionDeclaration)

MATCH
    // Find every FunctionDeclaration that should be available through the
    // entrance points
    q = (dead:FunctionDeclaration)-[:location]->(span:SourceSpan),
        (start:SourceLocation)<-[:start]-(span)-[:end]->(end:SourceLocation)

WHERE
    // List the ones that are not available (Kleene closure) from the
    // entrance nodes (thus are not the entrance nodes "<>").
        ( NOT (fd)-[:calls*]->(dead) )
    AND ( dead <> fd )
    AND ( main:Script OR main:Module )
    AND ( ALL (
                x in (nodes(p) + nodes(q))
                WHERE NOT exists(x.session) OR x.session = {sessionid}
          ) )

RETURN DISTINCT
    ID(dead) as id, start.line, start.column, end.line, end.column, dead.session
