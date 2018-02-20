MATCH
  (country:Country)<-[:IS_PART_OF]-(city:City)
WITH
  country.name AS cn,
  count(city) AS cc
WHERE cc > 123
RETURN cn, cc
