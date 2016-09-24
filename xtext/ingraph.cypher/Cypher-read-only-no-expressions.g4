grammar Cypher;

cypher : statement ( ';' )? ;

statement : query ;

query : regularQuery ;

regularQuery : singleQuery ( union )* ;

singleQuery : clause ( clause )* ;

union : ( UNION sp ALL singleQuery )
      | ( UNION singleQuery )
      ;

clause : match
       | unwind
       | with
       | return
       ;

match : ( OPTIONAL sp )? MATCH pattern ( where )? ;

with : ( WITH DISTINCT sp returnBody where? )
     | ( WITH sp returnBody where? )
     ;

return : ( RETURN sp DISTINCT sp returnBody )
       | ( RETURN sp returnBody )
       ;

returnBody : returnItems ( sp order )? ( sp skip )? ( sp limit )? ;

returnItems : ( '*' ( ',' returnItem )* )
            | ( returnItem ( ',' returnItem )* )
            ;

returnItem : ( expression sp AS sp variable )
           | expression
           ;

order : ORDER sp BY sp sortItem ( ',' sortItem )* ;

skip : L_SKIP sp expression ;

limit : LIMIT sp expression ;

sortItem : ( expression ( DESCENDING | DESC ) )
         | ( expression ( ASCENDING | ASC )? )
         ;

where : WHERE sp expression ;

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

parenthesizedExpression : '(' expression ')' ;

relationshipsPattern : nodePattern ( patternElementChain )+ ;

filterExpression : idInColl ( where )? ;

idInColl : variable sp IN sp expression ;

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
