SELECT t_tagid, tc_tagclassid, t_name, tc_name
FROM tag
       JOIN tagclass ON t_tagclassid = tc_tagclassid
WHERE t_tagid = 1
UNION ALL
SELECT tc_tagclassid, t_tagid, tc_name, t_name
FROM tag
       JOIN tagclass ON t_tagclassid = tc_tagclassid
WHERE tc_tagclassid = 1;
