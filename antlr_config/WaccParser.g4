parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

program: BEGIN func* stat END EOF;

func: type IDENT L_PAREN paramList? R_PAREN IS stat END;

paramList: param (COMMA param)*;

param: type IDENT;

stat: SKIP_TOKEN
      | type IDENT ASSIGN assignRhs
      | assignLhs ASSIGN assignRhs
      | READ assignLhs
      | (FREE | RETURN | EXIT | PRINT | PRINTLN) expr
      | IF expr THEN stat ELSE stat FI
      | WHILE expr DO stat DONE
      | BEGIN stat END
      | stat SEMICOLON stat;

assignLhs: IDENT
         | arrayElem
         | pairElem ;

assignRhs: expr
         | arrayLiter
         | NEWPAIR L_PAREN expr COMMA expr R_PAREN 
         | pairElem 
         | CALL IDENT L_PAREN argList? R_PAREN ;

argList: expr (COMMA expr)* ;

pairElem: FST expr
        | SND expr ;

type: baseType | pairType | arrayType ;

baseType: INT | BOOL | CHAR | STRING ;

arrayType: (baseType | pairType) (L_SQUARE R_SQUARE)+ ;

pairType: PAIR L_PAREN pairElemType COMMA pairElemType R_PAREN ;

pairElemType: baseType
            | arrayType
            | PAIR ;
            
expr: intLiter
    | boolLiter
    | charLiter
    | strLiter
    | pairLiter
    | IDENT
    | arrayElem
    | unaryOper expr
    | expr binaryOper expr
    | L_PAREN expr R_PAREN ;

unaryOper: NOT | MINUS | LEN | ORD | CHR;

binaryOper: MULT | DIV | MOD | PLUS
            | MINUS | GT | GTE | LT | LTE
            | EQ | NEQ | AND | OR ;

arrayElem: IDENT (L_SQUARE expr R_SQUARE)+ ;

intLiter: (PLUS | MINUS)? NUMBER ;

boolLiter: TRUE | FALSE ;

strLiter: STR_LITER;

charLiter: CHAR_LITER;

arrayLiter: L_SQUARE (expr (COMMA expr)*)? R_SQUARE ;

pairLiter: NULL ;

// // EOF indicates that the program must consume to the end of the input.
// prog: (expr)*  EOF ;

