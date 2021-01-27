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


//operators
PLUS: '+' ;
MINUS: '-' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;

//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;





