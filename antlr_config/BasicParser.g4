parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

binaryOper: ADD | SUB ;

expr: expr binaryOper expr
| INTEGER
| L_PAREN expr R_PAREN EOF
;

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
