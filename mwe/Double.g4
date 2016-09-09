grammar Double;

// try to parse a simple exponent decimal number, e.g. 1E2
// it will produce an error:
// line 1:0 mismatched input '1' expecting DigitString

exponentDecimalReal : DigitString 'E' DigitString ;

// removing this rule or moving this rule after DigitString resolves the error
DecimalInteger : DigitString ;

DigitString : ( Digit )+ ;

Digit : '0'
      | '1'
      | '2'
      | '3'
      | '4'
      | '5'
      | '6'
      | '7'
      | '8'
      | '9'
      ;


