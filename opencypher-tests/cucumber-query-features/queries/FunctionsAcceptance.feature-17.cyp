MATCH (n)
RETURN percentileCont(n.prop, $param)