// Generated from ./WaccParser.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WaccParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WaccParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WaccParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(WaccParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(WaccParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(WaccParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(WaccParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStat(WaccParser.ReadStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(WaccParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(WaccParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiStat(WaccParser.MultiStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skipStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipStat(WaccParser.SkipStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code actionStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionStat(WaccParser.ActionStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(WaccParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declareStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareStat(WaccParser.DeclareStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(WaccParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#assignLhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignLhs(WaccParser.AssignLhsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignRhs(WaccParser.AssignRhsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(WaccParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem(WaccParser.PairElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(WaccParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(WaccParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(WaccParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairType(WaccParser.PairTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemType(WaccParser.PairElemTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(WaccParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOper(WaccParser.UnaryOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binop1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop1(WaccParser.Binop1Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binop2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop2(WaccParser.Binop2Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binop3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop3(WaccParser.Binop3Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binop4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop4(WaccParser.Binop4Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binop5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop5(WaccParser.Binop5Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binop6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop6(WaccParser.Binop6Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(WaccParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#intLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiter(WaccParser.IntLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#boolLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiter(WaccParser.BoolLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#strLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrLiter(WaccParser.StrLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#charLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiter(WaccParser.CharLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arrayLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter(WaccParser.ArrayLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairLiter(WaccParser.PairLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(WaccParser.IdentContext ctx);
}