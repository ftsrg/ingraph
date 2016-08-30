MATCH p = (n)-[r:T]->()
RETURN [x IN [1.0, <invalid>] | toFloat(x) ] AS list
