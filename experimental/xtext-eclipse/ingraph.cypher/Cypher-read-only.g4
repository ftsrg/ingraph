grammar Cypher;

cypher : statement ( ';' )? ;

statement : query ;

query : regularQuery ;

regularQuery : singleQuery ( union )* ;

singleQuery : clause ( clause )* ;

union : ( UNION ALL singleQuery )
      | ( UNION singleQuery )
      ;

clause : match
       | unwind
       | with
       | return
       ;

match : ( OPTIONAL )? MATCH pattern ( where )? ;

unwind : UNWIND expression AS variable ;

with : ( WITH DISTINCT returnBody where? )
     | ( WITH returnBody where? )
     ;

return : ( RETURN DISTINCT returnBody )
       | ( RETURN returnBody )
       ;

returnBody : returnItems ( order )? ( skip )? ( limit )? ;

returnItems : ( '*' ( ',' returnItem )* )
            | ( returnItem ( ',' returnItem )* )
            ;

returnItem : ( expression AS variable )
           | expression
           ;

order : ORDER BY sortItem ( ',' sortItem )* ;

skip : L_SKIP expression ;

limit : LIMIT expression ;

sortItem : ( expression ( DESCENDING | DESC ) )
         | ( expression ( ASCENDING | ASC )? )
         ;

where : WHERE expression ;

pattern : patternPart ( ',' patternPart )* ;

patternPart : ( variable '=' anonymousPatternPart )
            | anonymousPatternPart
            ;

anonymousPatternPart : patternElement ;

patternElement : ( nodePattern ( patternElementChain )* )
               | ( '(' patternElement ')' )
               ;

nodePattern : '(' ( variable )? ( nodeLabels )? ( properties )? ')' ;

patternElementChain : relationshipPattern nodePattern ;

relationshipPattern : ( leftArrowHead dash relationshipDetail? dash rightArrowHead )
                    | ( leftArrowHead dash relationshipDetail? dash )
                    | ( dash relationshipDetail? dash rightArrowHead )
                    | ( dash relationshipDetail? dash )
                    ;

relationshipDetail : '[' variable? '?'? relationshipTypes? rangeLiteral? properties? ']' ;

properties : mapLiteral
           | parameter
           ;

relationshipTypes : ':' relTypeName ( '|' ':'? relTypeName )* ;

nodeLabels : nodeLabel ( nodeLabel )* ;

nodeLabel : ':' labelName ;

rangeLiteral : '*' ( integerLiteral )? ( '..' ( integerLiteral )? )? ;

labelName : symbolicName ;

relTypeName : symbolicName ;

expression : expression12 ;

expression12 : expression11 ( OR expression11 )* ;

expression11 : expression10 ( XOR expression10 )* ;

expression10 : expression9 ( AND expression9 )* ;

expression9 : ( NOT )* expression8 ;

expression8 : expression7 ( partialComparisonExpression )* ;

expression7 : expression6 ( ( '+' expression6 ) | ( '-' expression6 ) )* ;

expression6 : expression5 ( ( '*' expression5 ) | ( '/' expression5 ) | ( '%' expression5 ) )* ;

expression5 : expression4 ( '^' expression4 )* ;

expression4 : ( ( '+' | '-' ) )* expression3 ;

expression3 : expression2 ( ( '[' expression ']' ) | ( '[' expression? '..' expression? ']' ) | ( ( ( '=~' ) | ( IN ) | ( STARTS WITH ) | ( ENDS WITH ) | ( CONTAINS ) ) expression2 ) | ( IS NULL ) | ( IS NOT NULL ) )* ;

expression2 : atom ( propertyLookup | nodeLabels )* ;

atom : numberLiteral
     | StringLiteral
     | parameter
     | TRUE
     | FALSE
     | NULL
     | ( COUNT '(' '*' ')' )
     | mapLiteral
     | listComprehension
     | ( '[' expression ( ',' expression )* ']' )
     | ( FILTER '(' filterExpression ')' )
     | ( EXTRACT '(' filterExpression ( '|' expression )? ')' )
     | ( ALL '(' filterExpression ')' )
     | ( ANY '(' filterExpression ')' )
     | ( NONE '(' filterExpression ')' )
     | ( SINGLE '(' filterExpression ')' )
     | relationshipsPattern
     | parenthesizedExpression
     | functionInvocation
     | variable
     ;

partialComparisonExpression : ( '=' expression7 )
                            | ( '<>' expression7 )
                            | ( '!=' expression7 )
                            | ( '<' expression7 )
                            | ( '>' expression7 )
                            | ( '<=' expression7 )
                            | ( '>=' expression7 )
                            ;

parenthesizedExpression : '(' expression ')' ;

relationshipsPattern : nodePattern ( patternElementChain )+ ;

filterExpression : idInColl ( where )? ;

idInColl : variable IN expression ;

functionInvocation : functionName '(' ( DISTINCT )? ( expression ( ',' expression )* )? ')' ;

functionName : symbolicName ;

listComprehension : '[' filterExpression ( '|' expression )? ']' ;

propertyLookup : '.' ( ( propertyKeyName ( '?' | '!' ) ) | propertyKeyName ) ;

variable : symbolicName ;

StringLiteral : ( '"' ( StringLiteral_0 | EscapedChar )* '"' )
              | ( '\'' ( StringLiteral_1 | EscapedChar )* '\'' )
              ;

EscapedChar : '\\' ( '\\' | '\'' | '"' | ( 'B' | 'b' ) | ( 'F' | 'f' ) | ( 'N' | 'n' ) | ( 'R' | 'r' ) | ( 'T' | 't' ) | '_' | '%' | ( ( 'U' | 'u' ) ( HexDigit HexDigit HexDigit HexDigit ) ) | ( ( 'U' | 'u' ) ( HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit ) ) ) ;

numberLiteral : doubleLiteral
              | integerLiteral
              ;

mapLiteral : '{' ( propertyKeyName ':' expression ( ',' propertyKeyName ':' expression )* )? '}' ;

parameter : '$' ( symbolicName | DecimalInteger ) ;

propertyExpression : atom ( propertyLookup )+ ;

propertyKeyName : symbolicName ;

integerLiteral : HexInteger
               | OctalInteger
               | DecimalInteger
               ;

HexInteger : L_0X HexString ;

DecimalInteger : ( ( '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' ) DigitString? )
               | '0'
               ;

OctalInteger : '0' OctalString ;

HexString : ( HexDigit )+ ;

DigitString : ( Digit )+ ;

OctalString : ( OctDigit )+ ;

doubleLiteral : exponentDecimalReal
              | regularDecimalReal
              ;

exponentDecimalReal : ( ( Digit | '.' )+ | DecimalInteger ) ( ( 'E' | 'e' ) | ( 'E' | 'e' ) ) ( DigitString | DecimalInteger ) ;

regularDecimalReal : ( ( Digit )* | DecimalInteger ) '.' ( DigitString | DecimalInteger ) ;

symbolicName : UnescapedSymbolicName
             | EscapedSymbolicName
             | UNION
             | ALL
             | OPTIONAL
             | MATCH
             | UNWIND
             | AS
             | MERGE
             | ON
             | WITH
             | DISTINCT
             | RETURN
             | ORDER
             | BY
             | L_SKIP
             | LIMIT
             | DESCENDING
             | DESC
             | ASCENDING
             | ASC
             | WHERE
             | OR
             | XOR
             | AND
             | NOT
             | IN
             | STARTS
             | ENDS
             | CONTAINS
             | IS
             | NULL
             | TRUE
             | FALSE
             | COUNT
             | FILTER
             | EXTRACT
             | ANY
             | NONE
             | SINGLE
             | HexString
             ;

