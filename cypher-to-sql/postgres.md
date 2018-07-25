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
