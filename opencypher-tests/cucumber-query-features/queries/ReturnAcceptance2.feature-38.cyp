
      MATCH (n)
      RETURN count(DISTINCT {foo: [[n.list, n.list], [n.list, n.list]]}) AS count