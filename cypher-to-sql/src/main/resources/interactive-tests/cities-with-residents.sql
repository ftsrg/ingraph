-- Persons live in cities
SELECT pl_placeid FROM place JOIN person ON (p_placeid = pl_placeid)
WHERE pl_type='city'
ORDER BY pl_placeid;
