MATCH (a:A), (b:B)
RETURN coalesce(a.prop, b.prop) AS foo,
b.prop AS bar,
{y: count(b)} AS baz