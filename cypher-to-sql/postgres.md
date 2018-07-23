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
