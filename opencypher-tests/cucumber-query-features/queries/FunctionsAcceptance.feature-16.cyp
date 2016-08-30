
      MATCH (n)
      RETURN percentileCont(n.prop, $percentile) AS p