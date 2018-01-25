MATCH (exporter :CompilationUnit)-[:`contains`]->(expD :ExportDefault)-[:`body`]->(declaration),
      (impD :ImportDeclaration)-[:defaultBinding]->(importIdentifier :BindingIdentifier)<-[:`node`]-(importDeclaration:Declaration)

 WHERE (NOT (importDeclaration)-[:declaration]->())
   AND exporter.parsedFilePath CONTAINS impD.moduleSpecifier

CREATE (importDeclaration)-[:declaration]->(declaration)
