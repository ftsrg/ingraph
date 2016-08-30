# Using cycli

[cycli](https://github.com/nicolewhite/cycli) is a nice command-line interface Neo4j's Cypher query language.

1. Disable authentication: create or edit the `conf/neo4j-server.properties` file with the following property:
  ```
  dbms.security.auth_enabled=false
  ```

1. `bin/neo4j restart`
1. Go to http://localhost:7474/browser/ and login with `neo4j`/`neo4j`. You have to change the password to something else (e.g. `admin`).
1. Install Cycli with `pip install cycli`
1. Connect to the server with cycli:
  ```
  cycli --username neo4j --password admin
  ```
