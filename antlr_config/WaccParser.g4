parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

program: BEGIN func* stat END EOF;

func: type ident L_PAREN paramList? R_PAREN IS stat END;

paramList: param (COMMA param)*;

param: type ident;

stat: SKIP_TOKEN                                      #skipStat
      | (type | implicitType) ident ASSIGN assignRhs  #declareStat
      | assignLhs ASSIGN assignRhs                    #assignStat
      | structDeclare                                 #structDeclareStat
      | READ assignLhs                                #readStat
      | (FREE | RETURN | EXIT | PRINT | PRINTLN) expr #actionStat
      | IF expr THEN stat ELSE stat FI                #ifStat
      | WHILE expr DO stat DONE                       #whileStat
      | BEGIN stat END                                #blockStat
      | stat SEMICOLON stat                           #multiStat;

assignLhs: ident
         | arrayElem
         | pairElem
         | pointerElem;
//         | structAccess;

assignRhs: expr
         | arrayLiter
         | NEWPAIR L_PAREN expr COMMA expr R_PAREN
         | pairElem
         | CALL ident L_PAREN argList? R_PAREN
         | structAssign;

structDeclare: structType L_CURLY (structMember SEMICOLON)* R_CURLY;

structAssign: L_CURLY (assignRhs (COMMA assignRhs)*) R_CURLY;

structMember: type ident;

structAccess: ident DOT ident;

argList: expr (COMMA expr)* ;

pairElem: FST expr
        | SND expr ;

pointerElem: MULT ident ;

type: baseType | pairType | arrayType | structType | pointerType ;

baseType: INT | BOOL | CHAR | STRING ;

arrayType: (baseType | pairType | structType) (L_SQUARE R_SQUARE)+ ;

pairType: PAIR L_PAREN pairElemType COMMA pairElemType R_PAREN ;

pairElemType: baseType
            | arrayType
            | pairType
            | PAIR ;

pointerType: baseType MULT ;

implicitType: VAR ;

structType: STRUCT capitalisedIdent;

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
    | unop expr            #unopExpr
    | structAccess         #structAccessExpr
    | L_PAREN expr R_PAREN #parenExpr;

unop: NOT | MINUS | LEN | ORD | CHR | REF | MULT ;

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

ident: IDENT | CAPTIALISED_IDENT;

capitalisedIdent: CAPTIALISED_IDENT ;

