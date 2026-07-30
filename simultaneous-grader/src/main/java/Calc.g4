grammar Calc;

// Parser rules
prog   : stat+ ;
stat   : expr NEWLINE ;
expr   : INT ('+' INT | '-' INT)* ;

// Lexer rules
INT    : [0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS     : [ \t]+ -> skip ; // Skip whitespace
