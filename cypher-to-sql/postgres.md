# Purge PostgreSQL database
* https://stackoverflow.com/a/12072002 *

```sql
DROP SCHEMA PUBLIC CASCADE;
CREATE SCHEMA PUBLIC AUTHORIZATION "user";
GRANT ALL ON SCHEMA public TO public;
```