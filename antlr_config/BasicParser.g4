parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program: BEGIN (func)* stat END;

func: type ident L_PAREN (paramList)? R_PAREN IS stat END;

paramList: param (COMMA param)*;

param: type ident;

stat: SKIP_TOKEN | (FREE | RETURN  | EXIT | PRINT | PRINTLN) expr | stat SEMICOLON stat;

type: baseType;

baseType: INT | BOOL | CHAR | STRING;

expr: ident
| charLiter
| intLiter
| strLiter
| unaryOper expr
| expr binaryOper expr
| L_PAREN expr R_PAREN 
;


unaryOper: NOT | LEN  | ORD | CHR;

binaryOper: ADD | SUB | MULT | DIV |
            MOD | GTE | GT | LTE | LT |
            EQ | NEQ | AND | OR ;


ident: IDENTIFIER;

intLiter: (ADD | SUB)? (DIGIT)+ ;

strLiter: STR_LITER;

charLiter: CHAR_LITER;


// // EOF indicates that the program must consume to the end of the input.
// prog: (expr)*  EOF ;

