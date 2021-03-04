package wacc.frontend.ast.assign

import wacc.frontend.ast.AST
import wacc.frontend.ast.type.Typed

/**
 * Implemented by AST nodes that can be the left-hand side of an assignment statement
 * Implements the Typed interface to get underlying types during declare and assign statements
 */
interface LhsAST : AST, Typed

