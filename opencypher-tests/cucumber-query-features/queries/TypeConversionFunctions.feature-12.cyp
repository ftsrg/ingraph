
      MATCH p = (n)-[r:T]->()
      RETURN [x IN [1, <invalid>] | toInteger(x) ] AS list