MATCH
	( cu:CompilationUnit
		{
			path: {path}
		}
	)-[:contains]-(el)
WHERE
  // iff the provided sessionid parameter is NULL, then delete the fix graph of
  // the CompilationUnit; else delete the temporal one with the given sessionid
     ( {sessionid} IS NULL AND NOT exists(cu.sessionid) )
  OR ( cu.sessionid = {sessionid} )
DETACH DELETE
	cu, el
