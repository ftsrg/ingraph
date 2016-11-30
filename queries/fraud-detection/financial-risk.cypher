MATCH (accountHolder:AccountHolder)-[]->(contactInformation)
WITH
  contactInformation,
  count(accountHolder) AS RingSize

MATCH
  (contactInformation)<-[]-(accountHolder),
  (accountHolder)-[r:HAS_CREDITCARD|HAS_UNSECUREDLOAN]->(unsecuredAccount)
WITH
  collect(DISTINCT accountHolder.UniqueId) AS AccountHolders,
  contactInformation, RingSize,
  SUM(
    CASE type(r)
      WHEN 'HAS_CREDITCARD' THEN unsecuredAccount.LIMIT
      WHEN 'HAS_UNSECUREDLOAN' THEN unsecuredAccount.Balance
    ELSE 0
  END) AS FinancialRisk
WHERE RingSize > 1

RETURN
  AccountHolders AS FraudRing,
  labels(contactInformation) AS ContactType,
  RingSize,
  round(FinancialRisk) AS FinancialRisk

ORDER BY FinancialRisk DESC
