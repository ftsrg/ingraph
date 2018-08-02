## Init and Start PostgreSQL database
```shell
pgsql/bin/initdb -D ~/db-data -U postgres --pwprompt
pgsql/bin/pg_ctl -D ~/db-data -l ~/logfile start
```

## Purge PostgreSQL database
https://stackoverflow.com/a/12072002

```sql
DROP SCHEMA IF EXISTS PUBLIC CASCADE;
CREATE SCHEMA PUBLIC AUTHORIZATION "postgres";
GRANT ALL ON SCHEMA public TO public;
```

## Dump and Load PostgreSQL database
### Dump
```shell
pgsql/bin/pg_dump --file=~/dump.sql --clean --create --if-exists --username=postgres --host=localhost --port=$PORT
```

### Load
1. [purge](#purge-postgresql-database)
2. `pgsql/bin/psql --file=~/dump.sql --username=postgres --host=localhost --port=5432`

## Show graph
```sql
SELECT vertex_id,
       (SELECT coalesce(array_agg(name), ARRAY[] :: text []) FROM label WHERE parent = vertex_id) AS labels,
       (SELECT coalesce(array_agg(key || ': ' || value), ARRAY[] :: text [])
        FROM vertex_property
        WHERE parent = vertex_id)                                                                 AS properties
FROM vertex;

SELECT edge_id,
       type,
       "from",
       "to",
       (SELECT coalesce(array_agg(key || ': ' || value), ARRAY[] :: text [])
        FROM edge_property
        WHERE parent = edge_id) AS properties
FROM edge;
```
