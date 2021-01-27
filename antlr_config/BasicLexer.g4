lexer grammar BasicLexer;

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
FI: 'fi; ';
WHILE: 'while' ;
DO: 'do' ;
DONE: 'done' ;



SEMICOLON: ';' ;
NEWPAIR: 'newpair' ;
CALL: 'call' ;
COMMA: ',' ;
FST: 'fst' ;
SND: 'snd' ;
PAIR: 'pair' ;


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
ADD: '+' ;
SUB: '-' ;
MULT: '*' ;
DIV: '/' ;
MOD: '%' ;
GTE: '>=';
GT: '>' ;
LTE: '<=';
LT: '<' ;
EQ: '==';
NEQ: '!=';
AND: '&&';
OR: '||';

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
L_SQUARE: '[' ;
R_SQUARE: ']' ;
L_CURLY: '{' ;
R_CURLY: '}' ;


//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;





