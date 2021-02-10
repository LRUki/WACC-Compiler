package wacc.frontend.ast.assign

import wacc.frontend.ast.AST
import wacc.frontend.ast.type.Typed

/**
 * Implemented by ASTs possible of being on the left-hand side of an assignment statement
 * Implements Typed to get check types during declare and assign statements
 */
interface LhsAST : AST, Typed

