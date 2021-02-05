parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

program: BEGIN func* stat END EOF;

func: type ident L_PAREN paramList? R_PAREN IS stat END;

paramList: param (COMMA param)*;

param: type ident;

stat: SKIP_TOKEN                                      #skipStat
      | type ident ASSIGN assignRhs                   #declareStat
      | assignLhs ASSIGN assignRhs                    #assignStat
      | READ assignLhs                                #readStat
      | (FREE | RETURN | EXIT | PRINT | PRINTLN) expr #actionStat
      | IF expr THEN stat ELSE stat FI                #ifStat
      | WHILE expr DO stat DONE                       #whileStat
      | BEGIN stat END                                #blockStat
      | stat SEMICOLON stat                           #multiStat;

assignLhs: ident
         | arrayElem
         | pairElem ;

assignRhs: expr
         | arrayLiter
         | NEWPAIR L_PAREN expr COMMA expr R_PAREN
         | pairElem
         | CALL ident L_PAREN argList? R_PAREN;

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
            
expr: expr binop1 expr     #binopExpr
    | expr binop2 expr     #binopExpr
    | expr binop3 expr     #binopExpr
    | expr binop4 expr     #binopExpr
    | expr binop5 expr     #binopExpr
    | expr binop6 expr     #binopExpr
    | intLiter             #singletonExpr
    | boolLiter            #singletonExpr
    | charLiter            #singletonExpr
    | strLiter             #singletonExpr
    | pairLiter            #singletonExpr
    | ident                #singletonExpr
    | arrayElem            #singletonExpr
    | unaryOper expr       #unopExpr
    | L_PAREN expr R_PAREN #parenExpr;

unaryOper: NOT | MINUS | LEN | ORD | CHR;

binop1: MULT | DIV | MOD;
binop2: PLUS | MINUS;
binop3: LTE | LT | GTE | GT;
binop4: EQ  | NEQ;
binop5: AND;
binop6: OR;

arrayElem: ident (L_SQUARE expr R_SQUARE)+ ;

intLiter: (PLUS | MINUS)? NUMBER ;

boolLiter: TRUE | FALSE ;

strLiter: STR_LITER;

charLiter: CHAR_LITER;

arrayLiter: L_SQUARE (expr (COMMA expr)*)? R_SQUARE ;

pairLiter: NULL ;

ident: IDENT ;

