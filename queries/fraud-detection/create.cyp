// Create account holders
CREATE (accountHolder1:AccountHolder {
  FirstName: "John",
  LastName: "Doe",
  UniqueId: "JohnDoe" })

CREATE (accountHolder2:AccountHolder {
  FirstName: "Jane",
  LastName: "Appleseed",
  UniqueId: "JaneAppleseed" })

CREATE (accountHolder3:AccountHolder {
  FirstName: "Matt",
  LastName: "Smith",
  UniqueId: "MattSmith" })

// Create Address
CREATE (address1:Address {
  Street: "123 NW 1st Street",
  City: "San Francisco",
  State: "California",
  ZipCode: "94101" })

// Connect 3 account holders to 1 address
CREATE
  (accountHolder1)-[:HAS_ADDRESS]->(address1),
  (accountHolder2)-[:HAS_ADDRESS]->(address1),
  (accountHolder3)-[:HAS_ADDRESS]->(address1)

// Create Phone Number
CREATE (phoneNumber1:PhoneNumber { PhoneNumber: "555-555-5555" })

// Connect 2 account holders to 1 phone number
CREATE
  (accountHolder1)-[:HAS_PHONENUMBER]->(phoneNumber1),
  (accountHolder2)-[:HAS_PHONENUMBER]->(phoneNumber1)

// Create SSN
CREATE (ssn1:SSN { SSN: "241-23-1234" })

// Connect 2 account holders to 1 SSN
CREATE
  (accountHolder2)-[:HAS_SSN]->(ssn1),
  (accountHolder3)-[:HAS_SSN]->(ssn1)

// Create SSN and connect 1 account holder
CREATE (ssn2:SSN { SSN: "241-23-4567" })<-[:HAS_SSN]-(accountHolder1)

// Create Credit Card and connect 1 account holder
CREATE (creditCard1:CreditCard {
  AccountNumber: "1234567890123456",
  Limit: 5000, Balance: 1442.23,
  ExpirationDate: "01-20",
  SecurityCode: "123" })<-[:HAS_CREDITCARD]-(accountHolder1)

// Create Bank Account and connect 1 account holder
CREATE (bankAccount1:BankAccount {
  AccountNumber: "2345678901234567",
  Balance: 7054.43 })<-[:HAS_BANKACCOUNT]-(accountHolder1)

// Create Credit Card and connect 1 account holder
CREATE (creditCard2:CreditCard {
  AccountNumber: "1234567890123456",
  Limit: 4000, Balance: 2345.56,
  ExpirationDate: "02-20",
  SecurityCode: "456" })<-[:HAS_CREDITCARD]-(accountHolder2)

// Create Bank Account and connect 1 account holder
CREATE (bankAccount2:BankAccount {
  AccountNumber: "3456789012345678",
  Balance: 4231.12 })<-[:HAS_BANKACCOUNT]-(accountHolder2)

// Create Unsecured Loan and connect 1 account holder
CREATE (unsecuredLoan2:UnsecuredLoan {
  AccountNumber: "4567890123456789-0",
  Balance: 9045.53,
  APR: .0541,
  LoanAmount: 12000.00 })<-[:HAS_UNSECUREDLOAN]-(accountHolder2)

// Create Bank Account and connect 1 account holder
CREATE (bankAccount3:BankAccount {
  AccountNumber: "4567890123456789",
  Balance: 12345.45 })<-[:HAS_BANKACCOUNT]-(accountHolder3)

// Create Unsecured Loan and connect 1 account holder
CREATE (unsecuredLoan3:UnsecuredLoan {
  AccountNumber: "5678901234567890-0",
  Balance: 16341.95, APR: .0341,
  LoanAmount: 22000.00 })<-[:HAS_UNSECUREDLOAN]-(accountHolder3)

// Create Phone Number and connect 1 account holder
CREATE (phoneNumber2:PhoneNumber {
  PhoneNumber: "555-555-1234" })<-[:HAS_PHONENUMBER]-(accountHolder3)

RETURN *
