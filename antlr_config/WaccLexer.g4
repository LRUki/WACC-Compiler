lexer grammar WaccLexer;

//keywords
BEGIN: 'begin' ;
END: 'end' ;
IS: 'is' ;
SKIP_TOKEN: 'skip' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINT: 'print' ;
PRINTLN: 'println' ;
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
FI: 'fi' ;
WHILE: 'while' ;
DO: 'do' ;
DONE: 'done' ;
NULL: 'null' ;

ASSIGN: '=' ;
SEMICOLON: ';' ;
COMMA: ',' ;

NEWPAIR: 'newpair' ;
CALL: 'call' ;
FST: 'fst' ;
SND: 'snd' ;
PAIR: 'pair' ;
TRUE: 'true' ;
FALSE: 'false' ;

//base types
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;

//unary operators
NOT: '!' ;
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;

//binary operators
PLUS: '+' ;
MINUS: '-' ;
MULT: '*' ;
DIV: '/' ;
MOD: '%' ;
GTE: '>=' ;
GT: '>' ;
LTE: '<=' ;
LT: '<' ;
EQ: '==' ;
NEQ: '!=' ;
AND: '&&' ;
OR: '||';

//brackets
L_PAREN: '(' ;
R_PAREN: ')' ;
L_SQUARE: '[' ;
R_SQUARE: ']' ;
L_CURLY: '{' ;
R_CURLY: '}' ;

//numbers
NUMBER : DIGIT+ ;
fragment DIGIT: [0-9] ; 

//identifier
IDENT: ([a-zA-Z] | '_')(([a-zA-Z0-9]) | '_')* ;

WHITESPACE: [ \n\t\r]+ -> skip ;
ESCAPE_CHARACTER: [0btnfr"'\\] ;
CHARACTER: ~[\\'"] | '\\' ESCAPE_CHARACTER ;

//string literal
STR_LITER: '"' (CHARACTER)* '"' ;
CHAR_LITER: '\'' CHARACTER '\'' ;

//comments
COMMENT: '#' .*? [\n\r] -> skip;

