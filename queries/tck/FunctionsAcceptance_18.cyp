MATCH (n)
RETURN percentileDisc(n.prop, $param)
