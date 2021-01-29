parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program: BEGIN (func)* stat END;

func: type IDENTIFIER L_PAREN (paramList)? R_PAREN IS stat 'end';

paramList: param (COMMA param)*;

param: type IDENTIFIER;

stat: SKIP_TOKEN | (FREE | RETURN  | EXIT | PRINT | PRINTLN) expr | stat SEMICOLON stat;

type: BASE_TYPE;


expr: IDENTIFIER
| INTEGER_LIT
| UNARY_OP expr
|expr BINARY_OP expr
| L_PAREN expr R_PAREN 
;



// // EOF indicates that the program must consume to the end of the input.
// prog: (expr)*  EOF ;

