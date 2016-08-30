MATCH p = (n)-[r:T]->()
RETURN [x IN [r, <invalid>] | type(x) ] AS list