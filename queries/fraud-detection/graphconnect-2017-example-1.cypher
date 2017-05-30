MATCH (term:Terminal)<-[:IN_TERMINAL]-(t)-[n:NEXT*]->(:FraudTx)
WITH term , count(distinct t.cardno) as ct,
 min(t.timestamp) as mindate, max(t.timestamp) as maxdate
WHERE ct > 1
MATCH (term)<-[:IN_TERMINAL]-(otherTx)
WHERE otherTx.timestamp < maxdate and otherTx.timestamp > mindate
RETURN term.tid AS terminal,mindate,maxdate,
100 * ct / COUNT(DISTINCT otherTx.cardno) AS impact,
(maxdate - mindate)/(24*3600000) as timewindow
ORDER BY impact DESC, timewindow DESC
