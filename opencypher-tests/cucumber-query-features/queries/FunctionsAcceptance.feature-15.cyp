MATCH (n)
RETURN percentileDisc(n.prop, $percentile) AS p