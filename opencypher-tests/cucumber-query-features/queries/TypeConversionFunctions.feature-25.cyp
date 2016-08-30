MATCH p = (n)-[r:T]->()
RETURN [x IN [1, '', <invalid>] | toString(x) ] AS list