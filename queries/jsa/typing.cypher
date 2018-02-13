/**
 * Type inferencing
 *
 * Repeat the following steps until either no change occurs in the database or a
 * given repeat limit is reached.
 *
 *  1. Find the literal values and assign type tags to them.
 *  2. Propagate the type information to the Variables in the given Scope for
 *     every VariableDeclaration with initial value. Handle the corner cases.
 *  3. Handle built-in Unary and Binary Expressions.
 *  4. Propagate type information into function calls taking care of in-function
 *     type differentiation based on input types.
 */

/**
 *  1. Find the literal values and assign type tags to them.
 *     - LiteralNumericExpression -> NumberTag
 *     - LiteralStringExpression  -> StringTag
 *     - LiteralBooleanExpression -> BooleanTag
 *     - LiteralNullExpression    -> NullTag
 */

// LiteralNumericExpression -> NumberTag
MATCH (exp:LiteralNumericExpression)
MERGE (exp)-[:type]->(tag:Tag:NumberTag)
SET tag.session = exp.session
;

// LiteralStringExpression  -> StringTag
MATCH (exp:LiteralStringExpression)
MERGE (exp)-[:type]->(tag:Tag:StringTag)
SET tag.session = exp.session
;

// LiteralBooleanExpression -> BooleanTag
MATCH (exp:LiteralBooleanExpression)
MERGE (exp)-[:type]->(tag:Tag:BooleanTag)
SET tag.session = exp.session
;

// LiteralNullExpression    -> NullTag
MATCH (exp:LiteralNullExpression)
MERGE (exp)-[:type]->(tag:Tag:NullTag)
SET tag.session = exp.session
;

/**
 *  2. Propagate the type information to Variables from their VariableDeclaration.
 */

MATCH
    //(vds:VariableDeclarationStatement)-[:declaration]->
    //(vdion:VariableDeclaration)-[declarators]->(vdor)
    (var:Variable)-[:declarations]->
    (dec:Declaration)-[:node]->
    (bi:BindingIdentifier)<-[:binding]-
    (vdor:VariableDeclarator)-[:init]->
    (exp:Expression)-[:type]->
    (type:Tag)
//MERGE
//    (var)-[:type]->(tag:Tag)
//CALL apoc.create.addLabels(tag, labels(type))
WHERE
    NOT (var)-[:type]-(:Tag)-[:from]->(type)
CALL
    apoc.refactor.cloneNodes([type]) YIELD input, output as tag, error
MERGE
    (var)-[:type]->(tag)-[:from]->(type)
//MERGE
//    (tag)-[:from]->(type)
MERGE
    (tag)-[:through]->(vdor)
;


/**
 * Find the VariableDeclarators without initial value and mark them as
 * undefined.
 */

 MATCH
     (var:Variable)-[:declarations]->
     (dec:Declaration)-[:node]->
     (bi:BindingIdentifier)<-[:binding]-
     (vdor:VariableDeclarator)
 WHERE
     NOT (vdor)-[:init]->(:Expression)
 MERGE
     (var)-[:type]->(tag:Tag:UndefinedTag)
 SET
     tag.session = var.session
 MERGE
     (tag)-[:through]->(vdor)
 ;

/**
 * Find ArrayExpressions and tag them.
 */
 // TODO

/**
 * Find ObjectExpressions and tag them.
 */
 // TODO


/**
 *  3. Handle built-in Unary and Binary Expressions.
 */

/**
 * Propagate the Variable type information to the appropriate
 * IdentifierExpression.
 */

MATCH
    (type:Tag)<-[:type]-
    (var:Variable)-[:references]->
    (ref:Reference)-[:node]->
    (exp:Expression)
WHERE
    NOT (exp)-[:type]-(:Tag)-[:from]->(type)
CALL
    apoc.refactor.cloneNodes([type]) YIELD input, output as tag, error
MERGE
    (exp)-[:type]->(tag)-[:from]->(type)
;

/**
 * PLUS binary operator.
 */
MATCH
    (ltag:Tag)<-[:type]-(left:Expression)
    <-[:left]-(exp:BinaryExpression)-[:right]->
    (right:Expression)-[:type]->(rtag:Tag)
WHERE
        exp.operator = 'Plus'
    AND ltag :NumberTag
    AND rtag :NumberTag
MERGE
    (exp)-[:type]->(tag:Tag:NumberTag)
MERGE
    (ltag)<-[:from]-(tag)-[:from]->(rtag)
SET
    tag.session = ltag.session
;
