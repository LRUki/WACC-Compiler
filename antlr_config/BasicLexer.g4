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
BASE_TYPE: INT | BOOL | CHAR | STRING;
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;

//unary operators
UNARY_OP: NOT | LEN  | ORD | CHR;
NOT: '!' ;
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;

//binary operators
BINARY_OP: ADD | SUB | MULT | DIV |
           MOD | GTE | GT | LTE | LT |
           EQ | NEQ | AND | OR ;
ADD: '+' ;
SUB: '-' ;
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
fragment DIGIT: '0'..'9' ; 
INTEGER: DIGIT+ ;

//identifiers
fragment IDENTIFIER_FIRST: 'a'..'z' | '_' | 'A' .. 'Z' ;
fragment IDENTIFER_REST: (IDENTIFIER_FIRST | DIGIT)*;
IDENTIFIER:   IDENTIFIER_FIRST IDENTIFER_REST;

ESCAPE_CHARACTER: ('0' | 'b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\') ;
CHARACTER:~('\\' | '\'' | '"') | '\\' ESCAPE_CHARACTER;

//string literal
STRING_LIT: '"' (CHARACTER)* '"';
CHARACTER_LIT: '\'' CHARACTER '\'';

//comments
COMMENT: '#' ~('\r' | '\n')*;