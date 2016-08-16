# Legacy constructs in openCypher

The openCypher documentation defines [legacy grammar rules](
https://github.com/opencypher/openCypher/tree/master/grammar#legacy-grammar) as:

> Legacy grammar
>
> Not all grammar rules of Cypher the language will be standardised in their current form, meaning that they will not be part of openCypher as-is. Therefore, the openCypher grammar will not include some well-known Cypher constructs; these are called 'legacy'. To still enable tool authors or others interested in developing support for Cypher in its full current shape, these legacy rules are still present in the grammar specification. The artifacts that can be generated from the specification will by default ignore all legacy rules, but may optionally be instructed to include them.

The grammar rules are divided to 5 categories. The **basic grammar** and the **cypher** categories both have some legacy rules. The **commands**, **pre-parser** and **start** categories are considered completely legacy.

In this document, we collected the legacy grammar rules from all categories.

## Normal categories

### Basic grammar

```xml
<production name="ShortestPathPattern" rr:inline="true" oc:legacy="true">
  <alt>
    <seq>shortestPath ( <non-terminal ref="PatternElement"/> )</seq>
    <seq>allShortestPaths ( <non-terminal ref="PatternElement"/> )</seq>
  </alt>
</production>

<production name="RelType" rr:inline="true" oc:legacy="true">
  :<non-terminal ref="RelTypeName"/>
</production>

<production name="Reduce" rr:inline="true" oc:legacy="true">
  REDUCE &WS; ( &var; = &expr; , <non-terminal ref="IdInColl"/> | &expr; )
</production>

<production name="CaseExpression" oc:legacy="true">
  <alt>
    <seq>CASE <repeat min="1">&WS; <non-terminal ref="CaseAlternatives"/></repeat></seq>
    <seq>CASE &expr; <repeat min="1">&WS; <non-terminal ref="CaseAlternatives"/></repeat></seq>
  </alt>
  <opt>&WS; ELSE &WS; &expr;</opt>
  &WS; END
</production>

<production name="CaseAlternatives" rr:inline="true" oc:legacy="true">
  WHEN &WS; &expr; &WS; THEN &WS; &expr;
</production>

<production name="LegacyParameter" oc:legacy="true">
  { &WS;
  <alt>
    <non-terminal ref="SymbolicName"/>
    <non-terminal ref="DecimalInteger"/>
  </alt>
  &WS; }
</production>
```

### Cypher

```xml
<production name="BulkImportQuery" oc:legacy="true">
  <non-terminal ref="PeriodicCommitHint"/>
  &WS;
  <non-terminal ref="LoadCSVQuery"/>
</production>

<production name="PeriodicCommitHint" rr:inline="true" oc:legacy="true">
  USING &SP; PERIODIC &SP; COMMIT
  <opt>&SP;<non-terminal ref="SignedIntegerLiteral"/></opt>
</production>

<production name="LoadCSVQuery" rr:inline="true" oc:legacy="true">
  <non-terminal ref="LoadCSV"/>
  <repeat>&WS;<non-terminal ref="Clause"/></repeat>
</production>

<production name="LoadCSV" oc:legacy="true">
  LOAD &SP; CSV &SP;
  <opt>WITH &SP; HEADERS &SP;</opt>
  FROM &SP; &expr; &SP;
  AS &SP; &var; &SP;
  <opt>FIELDTERMINATOR &SP; <non-terminal ref="StringLiteral"/></opt>
</production>

<production name="CreateUnique" oc:legacy="true">
  <seq>CREATE &SP; UNIQUE &WS; <non-terminal ref="Pattern"/></seq>
</production>

<production name="Foreach" scope:rule="nested" oc:legacy="true">
  FOREACH &WS; ( &WS; &var; &SP; IN &SP; &expr; &WS; | <repeat min="1">&SP;<non-terminal ref="Clause"/></repeat> &WS; )
</production>

<production name="Hint" oc:legacy="true">
  &WS;
  <alt>
    <seq>USING &SP; INDEX &SP; &var; <non-terminal ref="NodeLabel"/>(<non-terminal ref="PropertyKeyName"/>)</seq>
    <seq>USING &SP; JOIN &SP; ON &SP; &var; <repeat>&WS;,&WS;&var;</repeat></seq>
    <seq>USING &SP; SCAN &SP; &var; <non-terminal ref="NodeLabel"/></seq>
  </alt>
</production>
```

## Completely legacy categories

All grammar rules in these categories are considered legacy.

### Commands

All commands are considered as legacy.

```xml
<production name="Command" rr:inline="true" oc:legacy="true">
  <alt>
    <non-terminal ref="CreateIndex"/>
    <non-terminal ref="DropIndex"/>

    <non-terminal ref="CreateUniqueConstraint"/>
    <non-terminal ref="DropUniqueConstraint"/>

    <non-terminal ref="CreateNodePropertyExistenceConstraint"/>
    <non-terminal ref="DropNodePropertyExistenceConstraint"/>

    <non-terminal ref="CreateRelationshipPropertyExistenceConstraint"/>
    <non-terminal ref="DropRelationshipPropertyExistenceConstraint"/>
  </alt>
</production>

<production name="CreateUniqueConstraint" rr:inline="true" oc:legacy="true">
  CREATE &SP; <non-terminal ref="UniqueConstraint"/>
</production>

<production name="CreateNodePropertyExistenceConstraint" rr:inline="true" oc:legacy="true">
  CREATE &SP; <non-terminal ref="NodePropertyExistenceConstraint"/>
</production>

<production name="CreateRelationshipPropertyExistenceConstraint" rr:inline="true" oc:legacy="true">
  CREATE &SP; <non-terminal ref="RelationshipPropertyExistenceConstraint"/>
</production>

<production name="CreateIndex" rr:inline="true" oc:legacy="true">
  CREATE &SP; <non-terminal ref="Index"/>
</production>

<production name="DropUniqueConstraint" rr:inline="true" oc:legacy="true">
  DROP &SP; <non-terminal ref="UniqueConstraint"/>
</production>

<production name="DropNodePropertyExistenceConstraint" rr:inline="true" oc:legacy="true">
  DROP &SP; <non-terminal ref="NodePropertyExistenceConstraint"/>
</production>

<production name="DropRelationshipPropertyExistenceConstraint" rr:inline="true" oc:legacy="true">
  DROP &SP; <non-terminal ref="RelationshipPropertyExistenceConstraint"/>
</production>

<production name="DropIndex" rr:inline="true" oc:legacy="true">
  DROP &SP; <non-terminal ref="Index"/>
</production>

<production name="Index" oc:legacy="true">
  INDEX &SP; ON &WS; &label; (<non-terminal ref="PropertyKeyName"/>)
</production>


<production name="UniqueConstraint" oc:legacy="true">
  CONSTRAINT &SP; ON &WS; ( &var; &label; ) &WS;
  ASSERT &SP; <non-terminal ref="PropertyExpression"/> &SP; IS &SP; UNIQUE
</production>

<production name="NodePropertyExistenceConstraint" oc:legacy="true">
  CONSTRAINT &SP; ON &WS; ( &var; &label; ) &WS;
  ASSERT &SP; EXISTS &WS; ( <non-terminal ref="PropertyExpression"/> )
</production>

<production name="RelationshipPropertyExistenceConstraint" oc:legacy="true">
  CONSTRAINT &SP; ON &WS; <non-terminal ref="RelationshipPatternSyntax"/> &WS;
  ASSERT &SP; EXISTS &WS; ( <non-terminal ref="PropertyExpression"/> )
</production>

<production name="RelationshipPatternSyntax" oc:legacy="true">
  <alt>
    <seq>&node;&dash;[&var;&type;]&dash;&node;</seq>
    <seq>&node;&dash;[&var;&type;]&rarrow;&node;</seq>
    <seq>&node;&larrow;[&var;&type;]&dash;&node;</seq>
  </alt>
</production>

```

## Pre-parser

Pre-parse rules are all considered legacy as they are not strictly required for query processing (e.g. `EXPLAIN`, `PROFILE`).

```xml
<production name="QueryOptions" oc:legacy="true">
  <repeat><non-terminal ref="AnyCypherOption"/> &WS;</repeat>
</production>

<production name="AnyCypherOption" rr:inline="true" oc:legacy="true">
  <alt>
    <non-terminal ref="CypherOption"/>
    <non-terminal ref="Explain"/>
    <non-terminal ref="Profile"/>
  </alt>
</production>

<production name="CypherOption" rr:inline="true" oc:legacy="true">
  CYPHER
  <opt>&SP;<non-terminal ref="VersionNumber"/></opt>
  <repeat>&SP;<non-terminal ref="ConfigurationOption"/></repeat>
</production>

<production name="VersionNumber" rr:inline="true" oc:legacy="true">
  <non-terminal ref="DecimalInteger"/>.<non-terminal ref="DecimalInteger"/>
</production>

<production name="Explain" rr:inline="true" oc:legacy="true">EXPLAIN</production>
<production name="Profile" rr:inline="true" oc:legacy="true">PROFILE</production>

<production name="ConfigurationOption" rr:inline="true" oc:legacy="true">
  <non-terminal ref="SymbolicName" ast:entry="key"/>
  &WS; = &WS;
  <non-terminal ref="SymbolicName" ast:entry="value"/>
</production>
```

## Start

Start rules are all considered legacy as they were used in Neo4j 1.x.

```xml
<production name="Start" oc:legacy="true">
  START &SP;
  <non-terminal ref="StartPoint"/>
  <repeat>&WS;,&WS;<non-terminal ref="StartPoint"/></repeat>
  <opt><non-terminal ref="Where"/></opt>
</production>

<production name="StartPoint" rr:inline="true" oc:legacy="true">
  &var; &WS; = &WS; <non-terminal ref="Lookup"/>
</production>

<production name="Lookup" rr:inline="true" oc:legacy="true">
  <alt>
    <non-terminal ref="NodeLookup"/>
    <non-terminal ref="RelationshipLookup"/>
  </alt>
</production>

<production name="NodeLookup" rr:inline="true" oc:legacy="true">
  NODE
  <alt>
    <non-terminal ref="IdentifiedIndexLookup"/>
    <non-terminal ref="IndexQuery"/>
    <non-terminal ref="IdLookup"/>
  </alt>
</production>

<production name="RelationshipLookup" rr:inline="true" oc:legacy="true">
  <alt>RELATIONSHIP REL</alt>
  <alt>
    <non-terminal ref="IdentifiedIndexLookup"/>
    <non-terminal ref="IndexQuery"/>
    <non-terminal ref="IdLookup"/>
  </alt>
</production>

<production name="IdentifiedIndexLookup" oc:legacy="true">
  : <non-terminal ref="SymbolicName" rr:title="index name"/>
  ( <non-terminal ref="SymbolicName" rr:title="property key"/> =
  <alt>
    <non-terminal ref="StringLiteral"/>
    <non-terminal ref="LegacyParameter"/>
  </alt>)
</production>

<production name="IndexQuery" oc:legacy="true">
  : <non-terminal ref="SymbolicName" rr:title="index name"/>
  (<alt>
    <non-terminal ref="StringLiteral"/>
    <non-terminal ref="LegacyParameter"/>
  </alt>)
</production>

<production name="IdLookup" oc:legacy="true">
  (<alt>
    <non-terminal ref="LiteralIds"/>
    <non-terminal ref="LegacyParameter"/>
    *
  </alt>)
</production>

<production name="LiteralIds" rr:inline="true" oc:legacy="true">
  <non-terminal ref="UnsignedIntegerLiteral"/>
  <repeat>
    &WS;,&WS;<non-terminal ref="UnsignedIntegerLiteral"/>
  </repeat>
</production>
```
