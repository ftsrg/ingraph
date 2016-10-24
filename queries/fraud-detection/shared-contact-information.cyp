MATCH (accountHolder:AccountHolder)-[]->(contactInformation)
WITH
  contactInformation,
  count(accountHolder) AS RingSize

MATCH (contactInformation)<-[]-(accountHolder)
WITH
  collect(accountHolder.UniqueId) AS AccountHolders,
  contactInformation, RingSize
WHERE RingSize > 1

RETURN
  AccountHolders AS FraudRing,
  labels(contactInformation) AS ContactType,
  RingSize
ORDER BY
  RingSize DESC
