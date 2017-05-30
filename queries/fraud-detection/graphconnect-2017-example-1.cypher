MATCH (term:Terminal)<-[:IN_TERMINAL]-(t)-[n:NEXT*]->(:FraudTx)
WITH
  term,
  count(DISTINCT t.cardno) as ct,
  min(t.timestamp) AS mindate,
  max(t.timestamp) AS maxdate
WHERE ct > 1
MATCH (term)<-[:IN_TERMINAL]-(otherTx)
WHERE otherTx.timestamp < maxdate
  AND otherTx.timestamp > mindate
RETURN
  term.tid AS terminal,
  mindate,
  maxdate,
  100 * ct / count(DISTINCT otherTx.cardno) AS impact,
  (maxdate - mindate) / (24*3600000) AS timewindow
ORDER BY impact DESC, timewindow DESC
