// Generated from ./WaccParser.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WaccParser extends Parser {

	static {
		RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			BEGIN = 1, END = 2, IS = 3, SKIP_TOKEN = 4, READ = 5, FREE = 6, RETURN = 7, EXIT = 8,
			PRINT = 9, PRINTLN = 10, IF = 11, THEN = 12, ELSE = 13, FI = 14, WHILE = 15, DO = 16,
			DONE = 17, NULL = 18, STRUCT = 19, ASSIGN = 20, SEMICOLON = 21, COMMA = 22, NEWPAIR = 23,
			CALL = 24, FST = 25, SND = 26, PAIR = 27, TRUE = 28, FALSE = 29, INT = 30, BOOL = 31,
			CHAR = 32, STRING = 33, VAR = 34, NOT = 35, LEN = 36, ORD = 37, CHR = 38, PLUS = 39, MINUS = 40,
			MULT = 41, DIV = 42, MOD = 43, GTE = 44, GT = 45, LTE = 46, LT = 47, EQ = 48, NEQ = 49,
			AND = 50, OR = 51, L_PAREN = 52, R_PAREN = 53, L_SQUARE = 54, R_SQUARE = 55, L_CURLY = 56,
			R_CURLY = 57, NUMBER = 58, IDENT = 59, WHITESPACE = 60, ESCAPE_CHARACTER = 61, CHARACTER = 62,
			STR_LITER = 63, CHAR_LITER = 64, COMMENT = 65;
	public static final int
			RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4,
			RULE_assignLhs = 5, RULE_assignRhs = 6, RULE_structField = 7, RULE_structAssign = 8,
			RULE_argList = 9, RULE_pairElem = 10, RULE_type = 11, RULE_baseType = 12,
			RULE_arrayType = 13, RULE_pairType = 14, RULE_pairElemType = 15, RULE_implicitType = 16,
			RULE_structType = 17, RULE_expr = 18, RULE_unop = 19, RULE_binop1 = 20,
			RULE_binop2 = 21, RULE_binop3 = 22, RULE_binop4 = 23, RULE_binop5 = 24,
			RULE_binop6 = 25, RULE_arrayElem = 26, RULE_intLiter = 27, RULE_boolLiter = 28,
			RULE_strLiter = 29, RULE_charLiter = 30, RULE_arrayLiter = 31, RULE_pairLiter = 32,
			RULE_ident = 33;

	private static String[] makeRuleNames() {
		return new String[]{
				"program", "func", "paramList", "param", "stat", "assignLhs", "assignRhs",
				"structField", "structAssign", "argList", "pairElem", "type", "baseType",
				"arrayType", "pairType", "pairElemType", "implicitType", "structType",
				"expr", "unop", "binop1", "binop2", "binop3", "binop4", "binop5", "binop6",
				"arrayElem", "intLiter", "boolLiter", "strLiter", "charLiter", "arrayLiter",
				"pairLiter", "ident"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'",
				"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'",
				"'while'", "'do'", "'done'", "'null'", "'struct'", "'='", "';'", "','",
				"'newpair'", "'call'", "'fst'", "'snd'", "'pair'", "'true'", "'false'",
				"'int'", "'bool'", "'char'", "'string'", "'var'", "'!'", "'len'", "'ord'",
				"'chr'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>='", "'>'", "'<='", "'<'",
				"'=='", "'!='", "'&&'", "'||'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT",
				"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE",
				"NULL", "STRUCT", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL",
				"FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING",
				"VAR", "NOT", "LEN", "ORD", "CHR", "PLUS", "MINUS", "MULT", "DIV", "MOD",
				"GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN",
				"L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER", "IDENT", "WHITESPACE",
				"ESCAPE_CHARACTER", "CHARACTER", "STR_LITER", "CHAR_LITER", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "WaccParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WaccParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(WaccParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public TerminalNode EOF() { return getToken(WaccParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(68);
				match(BEGIN);
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(69);
								func();
							}
						}
					}
					setState(74);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
				}
				setState(75);
				stat(0);
				setState(76);
				match(END);
				setState(77);
				match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(WaccParser.L_PAREN, 0); }
		public TerminalNode R_PAREN() { return getToken(WaccParser.R_PAREN, 0); }
		public TerminalNode IS() { return getToken(WaccParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(79);
				type();
				setState(80);
				ident();
				setState(81);
				match(L_PAREN);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 &&
						((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L
								<< CHAR) | (1L << STRING))) != 0)) {
					{
						setState(82);
						paramList();
					}
				}

				setState(85);
				match(R_PAREN);
				setState(86);
				match(IS);
				setState(87);
				stat(0);
				setState(88);
				match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WaccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(90);
				param();
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(91);
							match(COMMA);
							setState(92);
							param();
						}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(98);
				type();
				setState(99);
				ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReadStatContext extends StatContext {
		public TerminalNode READ() { return getToken(WaccParser.READ, 0); }
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
		}
		public ReadStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitReadStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatContext extends StatContext {
		public TerminalNode IF() { return getToken(WaccParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(WaccParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(WaccParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(WaccParser.FI, 0); }
		public IfStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStatContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(WaccParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public BlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiStatContext extends StatContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}

		public TerminalNode SEMICOLON() {
			return getToken(WaccParser.SEMICOLON, 0);
		}

		public MultiStatContext(StatContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitMultiStat(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class SkipStatContext extends StatContext {

		public TerminalNode SKIP_TOKEN() {
			return getToken(WaccParser.SKIP_TOKEN, 0);
		}

		public SkipStatContext(StatContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitSkipStat(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class StructDeclareStatContext extends StatContext {

		public TerminalNode STRUCT() {
			return getToken(WaccParser.STRUCT, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public TerminalNode L_CURLY() {
			return getToken(WaccParser.L_CURLY, 0);
		}

		public TerminalNode R_CURLY() {
			return getToken(WaccParser.R_CURLY, 0);
		}

		public List<StructFieldContext> structField() {
			return getRuleContexts(StructFieldContext.class);
		}

		public StructFieldContext structField(int i) {
			return getRuleContext(StructFieldContext.class, i);
		}

		public StructDeclareStatContext(StatContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitStructDeclareStat(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class ActionStatContext extends StatContext {

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode FREE() {
			return getToken(WaccParser.FREE, 0);
		}

		public TerminalNode RETURN() {
			return getToken(WaccParser.RETURN, 0);
		}

		public TerminalNode EXIT() {
			return getToken(WaccParser.EXIT, 0);
		}

		public TerminalNode PRINT() {
			return getToken(WaccParser.PRINT, 0);
		}

		public TerminalNode PRINTLN() {
			return getToken(WaccParser.PRINTLN, 0);
		}

		public ActionStatContext(StatContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitActionStat(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class AssignStatContext extends StatContext {

		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class, 0);
		}
		public TerminalNode ASSIGN() { return getToken(WaccParser.ASSIGN, 0); }
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public AssignStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclareStatContext extends StatContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(WaccParser.ASSIGN, 0); }
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ImplicitTypeContext implicitType() {
			return getRuleContext(ImplicitTypeContext.class,0);
		}
		public DeclareStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitDeclareStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatContext extends StatContext {
		public TerminalNode WHILE() { return getToken(WaccParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WaccParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode DONE() { return getToken(WaccParser.DONE, 0); }
		public WhileStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_stat, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(148);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
					case 1: {
						_localctx = new SkipStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(102);
						match(SKIP_TOKEN);
					}
					break;
					case 2: {
						_localctx = new DeclareStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(105);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case STRUCT:
							case PAIR:
							case INT:
							case BOOL:
							case CHAR:
							case STRING: {
								setState(103);
								type();
							}
							break;
							case VAR: {
								setState(104);
								implicitType();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(107);
						ident();
						setState(108);
						match(ASSIGN);
						setState(109);
						assignRhs();
					}
					break;
					case 3: {
						_localctx = new AssignStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(111);
						assignLhs();
						setState(112);
						match(ASSIGN);
						setState(113);
						assignRhs();
					}
					break;
					case 4: {
						_localctx = new StructDeclareStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(115);
						match(STRUCT);
						setState(116);
						ident();
						setState(117);
						match(L_CURLY);
						setState(121);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 &&
								((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L
										<< CHAR) | (1L << STRING))) != 0)) {
							{
								{
									setState(118);
									structField();
								}
							}
							setState(123);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(124);
						match(R_CURLY);
					}
					break;
					case 5: {
						_localctx = new ReadStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(126);
						match(READ);
						setState(127);
						assignLhs();
					}
					break;
					case 6: {
						_localctx = new ActionStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(128);
						_la = _input.LA(1);
						if (!((((_la) & ~0x3f) == 0 &&
								((1L << _la) & ((1L << FREE) | (1L << RETURN) | (1L << EXIT) | (1L << PRINT) | (1L
										<< PRINTLN))) != 0))) {
							_errHandler.recoverInline(this);
						} else {
							if (_input.LA(1) == Token.EOF) {
								matchedEOF = true;
							}
							_errHandler.reportMatch(this);
							consume();
						}
						setState(129);
						expr(0);
					}
					break;
					case 7: {
						_localctx = new IfStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(130);
						match(IF);
						setState(131);
						expr(0);
						setState(132);
						match(THEN);
						setState(133);
						stat(0);
						setState(134);
						match(ELSE);
						setState(135);
						stat(0);
						setState(136);
						match(FI);
					}
					break;
					case 8: {
						_localctx = new WhileStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(138);
						match(WHILE);
						setState(139);
						expr(0);
						setState(140);
						match(DO);
						setState(141);
						stat(0);
						setState(142);
						match(DONE);
					}
					break;
					case 9: {
						_localctx = new BlockStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(144);
						match(BEGIN);
						setState(145);
						stat(0);
						setState(146);
						match(END);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) {
							triggerExitRuleEvent();
						}
						_prevctx = _localctx;
						{
							{
								_localctx = new MultiStatContext(new StatContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_stat);
								setState(150);
								if (!(precpred(_ctx, 1))) {
									throw new FailedPredicateException(this, "precpred(_ctx, 1)");
								}
								setState(151);
								match(SEMICOLON);
								setState(152);
								stat(2);
							}
						}
					}
					setState(157);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLhsContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLhs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAssignLhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLhsContext assignLhs() throws RecognitionException {
		AssignLhsContext _localctx = new AssignLhsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignLhs);
		try {
			setState(161);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(158);
					ident();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(159);
					arrayElem();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(160);
					pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRhsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}

		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class, 0);
		}

		public TerminalNode NEWPAIR() {
			return getToken(WaccParser.NEWPAIR, 0);
		}

		public TerminalNode L_PAREN() {
			return getToken(WaccParser.L_PAREN, 0);
		}

		public TerminalNode COMMA() {
			return getToken(WaccParser.COMMA, 0);
		}

		public TerminalNode R_PAREN() {
			return getToken(WaccParser.R_PAREN, 0);
		}

		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class, 0);
		}

		public TerminalNode CALL() {
			return getToken(WaccParser.CALL, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class, 0);
		}

		public StructAssignContext structAssign() {
			return getRuleContext(StructAssignContext.class, 0);
		}

		public AssignRhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assignRhs;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitAssignRhs(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final AssignRhsContext assignRhs() throws RecognitionException {
		AssignRhsContext _localctx = new AssignRhsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignRhs);
		int _la;
		try {
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NULL:
				case TRUE:
				case FALSE:
				case NOT:
				case LEN:
				case ORD:
				case CHR:
				case PLUS:
				case MINUS:
				case L_PAREN:
				case NUMBER:
				case IDENT:
				case STR_LITER:
				case CHAR_LITER:
					enterOuterAlt(_localctx, 1);
				{
					setState(163);
					expr(0);
				}
				break;
				case L_SQUARE:
					enterOuterAlt(_localctx, 2);
				{
					setState(164);
					arrayLiter();
				}
				break;
				case NEWPAIR:
					enterOuterAlt(_localctx, 3);
				{
					setState(165);
					match(NEWPAIR);
					setState(166);
					match(L_PAREN);
					setState(167);
					expr(0);
					setState(168);
					match(COMMA);
					setState(169);
					expr(0);
					setState(170);
					match(R_PAREN);
				}
				break;
				case FST:
				case SND:
					enterOuterAlt(_localctx, 4);
				{
					setState(172);
					pairElem();
				}
				break;
				case CALL:
					enterOuterAlt(_localctx, 5);
				{
					setState(173);
					match(CALL);
					setState(174);
					ident();
					setState(175);
					match(L_PAREN);
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 18)) & ~0x3f) == 0 &&
							((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (TRUE - 18)) | (1L << (FALSE
									- 18)) | (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L << (
									CHR - 18)) | (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L << (L_PAREN - 18))
									| (1L << (NUMBER - 18)) | (1L << (IDENT - 18)) | (1L << (STR_LITER - 18)) | (1L
									<< (CHAR_LITER - 18)))) != 0)) {
						{
							setState(176);
							argList();
						}
					}

					setState(179);
					match(R_PAREN);
				}
				break;
				case L_CURLY:
					enterOuterAlt(_localctx, 6);
				{
					setState(181);
					structAssign();
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructFieldContext extends ParserRuleContext {

		public TypeContext type() {
			return getRuleContext(TypeContext.class, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(WaccParser.SEMICOLON, 0);
		}

		public StructFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_structField;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitStructField(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final StructFieldContext structField() throws RecognitionException {
		StructFieldContext _localctx = new StructFieldContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_structField);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(184);
				type();
				setState(185);
				ident();
				setState(186);
				match(SEMICOLON);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructAssignContext extends ParserRuleContext {

		public TerminalNode L_CURLY() {
			return getToken(WaccParser.L_CURLY, 0);
		}

		public TerminalNode R_CURLY() {
			return getToken(WaccParser.R_CURLY, 0);
		}

		public List<AssignRhsContext> assignRhs() {
			return getRuleContexts(AssignRhsContext.class);
		}

		public AssignRhsContext assignRhs(int i) {
			return getRuleContext(AssignRhsContext.class, i);
		}

		public StructAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_structAssign;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitStructAssign(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final StructAssignContext structAssign() throws RecognitionException {
		StructAssignContext _localctx = new StructAssignContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_structAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(188);
				match(L_CURLY);
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 18)) & ~0x3f) == 0 &&
						((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (NEWPAIR - 18)) | (1L << (CALL
								- 18)) | (1L << (FST - 18)) | (1L << (SND - 18)) | (1L << (TRUE - 18)) | (1L << (
								FALSE - 18)) | (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L
								<< (CHR - 18)) | (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L << (L_PAREN - 18))
								| (1L << (L_SQUARE - 18)) | (1L << (L_CURLY - 18)) | (1L << (NUMBER - 18)) | (1L
								<< (IDENT - 18)) | (1L << (STR_LITER - 18)) | (1L << (CHAR_LITER - 18)))) != 0)) {
					{
						{
							setState(189);
							assignRhs();
						}
					}
					setState(194);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(195);
				match(R_CURLY);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(WaccParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}

		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_argList;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitArgList(this);
			} else
				return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(197);
				expr(0);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(198);
							match(COMMA);
							setState(199);
							expr(0);
						}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode FST() { return getToken(WaccParser.FST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(WaccParser.SND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pairElem);
		try {
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case FST:
					enterOuterAlt(_localctx, 1);
				{
					setState(205);
					match(FST);
					setState(206);
					expr(0);
				}
				break;
				case SND:
					enterOuterAlt(_localctx, 2);
				{
					setState(207);
					match(SND);
					setState(208);
					expr(0);
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class, 0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class, 0);
		}

		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class, 0);
		}

		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class, 0);
		}

		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_type;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitType(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_type);
		try {
			setState(215);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(211);
					baseType();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(212);
					pairType();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(213);
					arrayType();
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(214);
					structType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(WaccParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(WaccParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(WaccParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(WaccParser.STRING, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(217);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0
						&& ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING)))
						!= 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {

		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class, 0);
		}

		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class, 0);
		}

		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class, 0);
		}

		public List<TerminalNode> L_SQUARE() {
			return getTokens(WaccParser.L_SQUARE);
		}

		public TerminalNode L_SQUARE(int i) {
			return getToken(WaccParser.L_SQUARE, i);
		}

		public List<TerminalNode> R_SQUARE() {
			return getTokens(WaccParser.R_SQUARE);
		}

		public TerminalNode R_SQUARE(int i) {
			return getToken(WaccParser.R_SQUARE, i);
		}

		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayType;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitArrayType(this);
			} else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(222);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case INT:
					case BOOL:
					case CHAR:
					case STRING: {
						setState(219);
						baseType();
					}
					break;
					case PAIR: {
						setState(220);
						pairType();
					}
					break;
					case STRUCT: {
						setState(221);
						structType();
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(224);
							match(L_SQUARE);
							setState(225);
							match(R_SQUARE);
						}
					}
					setState(228);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == L_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR() { return getToken(WaccParser.PAIR, 0); }
		public TerminalNode L_PAREN() { return getToken(WaccParser.L_PAREN, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WaccParser.COMMA, 0); }
		public TerminalNode R_PAREN() { return getToken(WaccParser.R_PAREN, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(230);
				match(PAIR);
				setState(231);
				match(L_PAREN);
				setState(232);
				pairElemType();
				setState(233);
				match(COMMA);
				setState(234);
				pairElemType();
				setState(235);
				match(R_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(WaccParser.PAIR, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairElemType);
		try {
			setState(241);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(237);
					baseType();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(238);
					arrayType();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(239);
					pairType();
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(240);
					match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImplicitTypeContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(WaccParser.VAR, 0); }
		public ImplicitTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicitType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitImplicitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplicitTypeContext implicitType() throws RecognitionException {
		ImplicitTypeContext _localctx = new ImplicitTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_implicitType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(243);
				match(VAR);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructTypeContext extends ParserRuleContext {

		public TerminalNode STRUCT() {
			return getToken(WaccParser.STRUCT, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public StructTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_structType;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitStructType(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_structType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(245);
				match(STRUCT);
				setState(246);
				ident();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {

		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expr;
		}

		public ExprContext() {
		}

		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class UnopExprContext extends ExprContext {

		public UnopContext unop() {
			return getRuleContext(UnopContext.class, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}
		public UnopExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitUnopExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingletonExprContext extends ExprContext {
		public IntLiterContext intLiter() {
			return getRuleContext(IntLiterContext.class,0);
		}
		public BoolLiterContext boolLiter() {
			return getRuleContext(BoolLiterContext.class,0);
		}
		public CharLiterContext charLiter() {
			return getRuleContext(CharLiterContext.class,0);
		}
		public StrLiterContext strLiter() {
			return getRuleContext(StrLiterContext.class,0);
		}
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public SingletonExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitSingletonExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinopExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Binop1Context binop1() {
			return getRuleContext(Binop1Context.class,0);
		}
		public Binop2Context binop2() {
			return getRuleContext(Binop2Context.class,0);
		}
		public Binop3Context binop3() {
			return getRuleContext(Binop3Context.class,0);
		}
		public Binop4Context binop4() {
			return getRuleContext(Binop4Context.class,0);
		}
		public Binop5Context binop5() {
			return getRuleContext(Binop5Context.class,0);
		}
		public Binop6Context binop6() {
			return getRuleContext(Binop6Context.class,0);
		}
		public BinopExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinopExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExprContext {
		public TerminalNode L_PAREN() { return getToken(WaccParser.L_PAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(WaccParser.R_PAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(263);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
					case 1: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(249);
						intLiter();
					}
					break;
					case 2: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(250);
						boolLiter();
					}
					break;
					case 3: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(251);
						charLiter();
					}
					break;
					case 4: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(252);
						strLiter();
					}
					break;
					case 5: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(253);
						pairLiter();
					}
					break;
					case 6: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(254);
						ident();
					}
					break;
					case 7: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(255);
						arrayElem();
					}
					break;
					case 8: {
						_localctx = new UnopExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(256);
						unop();
						setState(257);
						expr(2);
					}
					break;
					case 9: {
						_localctx = new ParenExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(259);
						match(L_PAREN);
						setState(260);
						expr(0);
						setState(261);
						match(R_PAREN);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(291);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) {
							triggerExitRuleEvent();
						}
						_prevctx = _localctx;
						{
							setState(289);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
								case 1: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(265);
									if (!(precpred(_ctx, 15))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 15)");
									}
									setState(266);
									binop1();
									setState(267);
									expr(16);
								}
								break;
								case 2: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(269);
									if (!(precpred(_ctx, 14))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 14)");
									}
									setState(270);
									binop2();
									setState(271);
									expr(15);
								}
								break;
								case 3: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(273);
									if (!(precpred(_ctx, 13))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 13)");
									}
									setState(274);
									binop3();
									setState(275);
									expr(14);
								}
								break;
								case 4: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(277);
									if (!(precpred(_ctx, 12))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 12)");
									}
									setState(278);
									binop4();
									setState(279);
									expr(13);
								}
								break;
								case 5: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(281);
									if (!(precpred(_ctx, 11))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									}
									setState(282);
									binop5();
									setState(283);
									expr(12);
								}
								break;
								case 6: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(285);
									if (!(precpred(_ctx, 10))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									}
									setState(286);
									binop6();
									setState(287);
									expr(11);
								}
								break;
							}
						}
					}
					setState(293);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnopContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(WaccParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public TerminalNode LEN() { return getToken(WaccParser.LEN, 0); }
		public TerminalNode ORD() { return getToken(WaccParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(WaccParser.CHR, 0); }
		public UnopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitUnop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnopContext unop() throws RecognitionException {
		UnopContext _localctx = new UnopContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(294);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 &&
						((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << MINUS)))
								!= 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binop1Context extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(WaccParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(WaccParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(WaccParser.MOD, 0); }
		public Binop1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinop1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binop1Context binop1() throws RecognitionException {
		Binop1Context _localctx = new Binop1Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_binop1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(296);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0
						&& ((1L << _la) & ((1L << MULT) | (1L << DIV) | (1L << MOD))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binop2Context extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public Binop2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinop2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binop2Context binop2() throws RecognitionException {
		Binop2Context _localctx = new Binop2Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_binop2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(298);
				_la = _input.LA(1);
				if (!(_la == PLUS || _la == MINUS)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binop3Context extends ParserRuleContext {
		public TerminalNode LTE() { return getToken(WaccParser.LTE, 0); }
		public TerminalNode LT() { return getToken(WaccParser.LT, 0); }
		public TerminalNode GTE() { return getToken(WaccParser.GTE, 0); }
		public TerminalNode GT() { return getToken(WaccParser.GT, 0); }
		public Binop3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinop3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binop3Context binop3() throws RecognitionException {
		Binop3Context _localctx = new Binop3Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_binop3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(300);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0
						&& ((1L << _la) & ((1L << GTE) | (1L << GT) | (1L << LTE) | (1L << LT))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binop4Context extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(WaccParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(WaccParser.NEQ, 0); }
		public Binop4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop4; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinop4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binop4Context binop4() throws RecognitionException {
		Binop4Context _localctx = new Binop4Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_binop4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(302);
				_la = _input.LA(1);
				if (!(_la == EQ || _la == NEQ)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binop5Context extends ParserRuleContext {
		public TerminalNode AND() { return getToken(WaccParser.AND, 0); }
		public Binop5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop5; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinop5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binop5Context binop5() throws RecognitionException {
		Binop5Context _localctx = new Binop5Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_binop5);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(304);
				match(AND);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binop6Context extends ParserRuleContext {
		public TerminalNode OR() { return getToken(WaccParser.OR, 0); }
		public Binop6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop6; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinop6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binop6Context binop6() throws RecognitionException {
		Binop6Context _localctx = new Binop6Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_binop6);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(306);
				match(OR);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<TerminalNode> L_SQUARE() { return getTokens(WaccParser.L_SQUARE); }
		public TerminalNode L_SQUARE(int i) {
			return getToken(WaccParser.L_SQUARE, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> R_SQUARE() { return getTokens(WaccParser.R_SQUARE); }
		public TerminalNode R_SQUARE(int i) {
			return getToken(WaccParser.R_SQUARE, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(308);
				ident();
				setState(313);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							{
								setState(309);
								match(L_SQUARE);
								setState(310);
								expr(0);
								setState(311);
								match(R_SQUARE);
							}
						}
						break;
						default:
							throw new NoViableAltException(this);
					}
					setState(315);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
				} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntLiterContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(WaccParser.NUMBER, 0); }
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public IntLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiterContext intLiter() throws RecognitionException {
		IntLiterContext _localctx = new IntLiterContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_intLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == PLUS || _la == MINUS) {
					{
						setState(317);
						_la = _input.LA(1);
						if (!(_la == PLUS || _la == MINUS)) {
							_errHandler.recoverInline(this);
						} else {
							if (_input.LA(1) == Token.EOF) {
								matchedEOF = true;
							}
							_errHandler.reportMatch(this);
							consume();
						}
					}
				}

				setState(320);
				match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiterContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(WaccParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(WaccParser.FALSE, 0); }
		public BoolLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBoolLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiterContext boolLiter() throws RecognitionException {
		BoolLiterContext _localctx = new BoolLiterContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(322);
				_la = _input.LA(1);
				if (!(_la == TRUE || _la == FALSE)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrLiterContext extends ParserRuleContext {
		public TerminalNode STR_LITER() { return getToken(WaccParser.STR_LITER, 0); }
		public StrLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStrLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrLiterContext strLiter() throws RecognitionException {
		StrLiterContext _localctx = new StrLiterContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_strLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(324);
				match(STR_LITER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharLiterContext extends ParserRuleContext {
		public TerminalNode CHAR_LITER() { return getToken(WaccParser.CHAR_LITER, 0); }
		public CharLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCharLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLiterContext charLiter() throws RecognitionException {
		CharLiterContext _localctx = new CharLiterContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(326);
				match(CHAR_LITER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiterContext extends ParserRuleContext {
		public TerminalNode L_SQUARE() { return getToken(WaccParser.L_SQUARE, 0); }
		public TerminalNode R_SQUARE() { return getToken(WaccParser.R_SQUARE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WaccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(328);
				match(L_SQUARE);
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 18)) & ~0x3f) == 0 &&
						((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (TRUE - 18)) | (1L << (FALSE - 18))
								| (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L << (CHR - 18))
								| (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L << (L_PAREN - 18)) | (1L << (
								NUMBER - 18)) | (1L << (IDENT - 18)) | (1L << (STR_LITER - 18)) | (1L << (CHAR_LITER
								- 18)))) != 0)) {
					{
						setState(329);
						expr(0);
						setState(334);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == COMMA) {
							{
								{
									setState(330);
									match(COMMA);
									setState(331);
									expr(0);
								}
							}
							setState(336);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(339);
				match(R_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairLiterContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(WaccParser.NULL, 0); }
		public PairLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairLiterContext pairLiter() throws RecognitionException {
		PairLiterContext _localctx = new PairLiterContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(341);
				match(NULL);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(343);
				match(IDENT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
			case 4:
				return stat_sempred((StatContext) _localctx, predIndex);
			case 18:
				return expr_sempred((ExprContext) _localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3C\u015c\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\3\2\3\2\7\2I\n\2\f\2\16\2L\13\2\3\2\3\2\3\2\3\2\3\3" +
					"\3\3\3\3\3\3\5\3V\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4`\n\4\f\4\16" +
					"\4c\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6l\n\6\3\6\3\6\3\6\3\6\3\6\3\6" +
					"\3\6\3\6\3\6\3\6\3\6\3\6\7\6z\n\6\f\6\16\6}\13\6\3\6\3\6\3\6\3\6\3\6\3" +
					"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
					"\3\6\5\6\u0097\n\6\3\6\3\6\3\6\7\6\u009c\n\6\f\6\16\6\u009f\13\6\3\7\3" +
					"\7\3\7\5\7\u00a4\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3" +
					"\b\3\b\5\b\u00b4\n\b\3\b\3\b\3\b\5\b\u00b9\n\b\3\t\3\t\3\t\3\t\3\n\3\n" +
					"\7\n\u00c1\n\n\f\n\16\n\u00c4\13\n\3\n\3\n\3\13\3\13\3\13\7\13\u00cb\n" +
					"\13\f\13\16\13\u00ce\13\13\3\f\3\f\3\f\3\f\5\f\u00d4\n\f\3\r\3\r\3\r\3" +
					"\r\5\r\u00da\n\r\3\16\3\16\3\17\3\17\3\17\5\17\u00e1\n\17\3\17\3\17\6" +
					"\17\u00e5\n\17\r\17\16\17\u00e6\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21" +
					"\3\21\3\21\3\21\5\21\u00f4\n\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24" +
					"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u010a" +
					"\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24" +
					"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0124\n\24" +
					"\f\24\16\24\u0127\13\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3" +
					"\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\6\34\u013c\n\34\r\34" +
					"\16\34\u013d\3\35\5\35\u0141\n\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 " +
					"\3!\3!\3!\3!\7!\u014f\n!\f!\16!\u0152\13!\5!\u0154\n!\3!\3!\3\"\3\"\3" +
					"#\3#\3#\2\4\n&$\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62" +
					"\64\668:<>@BD\2\n\3\2\b\f\3\2 #\4\2%(**\3\2+-\3\2)*\3\2.\61\3\2\62\63" +
					"\3\2\36\37\2\u016d\2F\3\2\2\2\4Q\3\2\2\2\6\\\3\2\2\2\bd\3\2\2\2\n\u0096" +
					"\3\2\2\2\f\u00a3\3\2\2\2\16\u00b8\3\2\2\2\20\u00ba\3\2\2\2\22\u00be\3" +
					"\2\2\2\24\u00c7\3\2\2\2\26\u00d3\3\2\2\2\30\u00d9\3\2\2\2\32\u00db\3\2" +
					"\2\2\34\u00e0\3\2\2\2\36\u00e8\3\2\2\2 \u00f3\3\2\2\2\"\u00f5\3\2\2\2" +
					"$\u00f7\3\2\2\2&\u0109\3\2\2\2(\u0128\3\2\2\2*\u012a\3\2\2\2,\u012c\3" +
					"\2\2\2.\u012e\3\2\2\2\60\u0130\3\2\2\2\62\u0132\3\2\2\2\64\u0134\3\2\2" +
					"\2\66\u0136\3\2\2\28\u0140\3\2\2\2:\u0144\3\2\2\2<\u0146\3\2\2\2>\u0148" +
					"\3\2\2\2@\u014a\3\2\2\2B\u0157\3\2\2\2D\u0159\3\2\2\2FJ\7\3\2\2GI\5\4" +
					"\3\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\5\n" +
					"\6\2NO\7\4\2\2OP\7\2\2\3P\3\3\2\2\2QR\5\30\r\2RS\5D#\2SU\7\66\2\2TV\5" +
					"\6\4\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\67\2\2XY\7\5\2\2YZ\5\n\6\2Z[" +
					"\7\4\2\2[\5\3\2\2\2\\a\5\b\5\2]^\7\30\2\2^`\5\b\5\2_]\3\2\2\2`c\3\2\2" +
					"\2a_\3\2\2\2ab\3\2\2\2b\7\3\2\2\2ca\3\2\2\2de\5\30\r\2ef\5D#\2f\t\3\2" +
					"\2\2gh\b\6\1\2h\u0097\7\6\2\2il\5\30\r\2jl\5\"\22\2ki\3\2\2\2kj\3\2\2" +
					"\2lm\3\2\2\2mn\5D#\2no\7\26\2\2op\5\16\b\2p\u0097\3\2\2\2qr\5\f\7\2rs" +
					"\7\26\2\2st\5\16\b\2t\u0097\3\2\2\2uv\7\25\2\2vw\5D#\2w{\7:\2\2xz\5\20" +
					"\t\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177" +
					"\7;\2\2\177\u0097\3\2\2\2\u0080\u0081\7\7\2\2\u0081\u0097\5\f\7\2\u0082" +
					"\u0083\t\2\2\2\u0083\u0097\5&\24\2\u0084\u0085\7\r\2\2\u0085\u0086\5&" +
					"\24\2\u0086\u0087\7\16\2\2\u0087\u0088\5\n\6\2\u0088\u0089\7\17\2\2\u0089" +
					"\u008a\5\n\6\2\u008a\u008b\7\20\2\2\u008b\u0097\3\2\2\2\u008c\u008d\7" +
					"\21\2\2\u008d\u008e\5&\24\2\u008e\u008f\7\22\2\2\u008f\u0090\5\n\6\2\u0090" +
					"\u0091\7\23\2\2\u0091\u0097\3\2\2\2\u0092\u0093\7\3\2\2\u0093\u0094\5" +
					"\n\6\2\u0094\u0095\7\4\2\2\u0095\u0097\3\2\2\2\u0096g\3\2\2\2\u0096k\3" +
					"\2\2\2\u0096q\3\2\2\2\u0096u\3\2\2\2\u0096\u0080\3\2\2\2\u0096\u0082\3" +
					"\2\2\2\u0096\u0084\3\2\2\2\u0096\u008c\3\2\2\2\u0096\u0092\3\2\2\2\u0097" +
					"\u009d\3\2\2\2\u0098\u0099\f\3\2\2\u0099\u009a\7\27\2\2\u009a\u009c\5" +
					"\n\6\4\u009b\u0098\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d" +
					"\u009e\3\2\2\2\u009e\13\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a4\5D#\2" +
					"\u00a1\u00a4\5\66\34\2\u00a2\u00a4\5\26\f\2\u00a3\u00a0\3\2\2\2\u00a3" +
					"\u00a1\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\r\3\2\2\2\u00a5\u00b9\5&\24\2" +
					"\u00a6\u00b9\5@!\2\u00a7\u00a8\7\31\2\2\u00a8\u00a9\7\66\2\2\u00a9\u00aa" +
					"\5&\24\2\u00aa\u00ab\7\30\2\2\u00ab\u00ac\5&\24\2\u00ac\u00ad\7\67\2\2" +
					"\u00ad\u00b9\3\2\2\2\u00ae\u00b9\5\26\f\2\u00af\u00b0\7\32\2\2\u00b0\u00b1" +
					"\5D#\2\u00b1\u00b3\7\66\2\2\u00b2\u00b4\5\24\13\2\u00b3\u00b2\3\2\2\2" +
					"\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\7\67\2\2\u00b6\u00b9" +
					"\3\2\2\2\u00b7\u00b9\5\22\n\2\u00b8\u00a5\3\2\2\2\u00b8\u00a6\3\2\2\2" +
					"\u00b8\u00a7\3\2\2\2\u00b8\u00ae\3\2\2\2\u00b8\u00af\3\2\2\2\u00b8\u00b7" +
					"\3\2\2\2\u00b9\17\3\2\2\2\u00ba\u00bb\5\30\r\2\u00bb\u00bc\5D#\2\u00bc" +
					"\u00bd\7\27\2\2\u00bd\21\3\2\2\2\u00be\u00c2\7:\2\2\u00bf\u00c1\5\16\b" +
					"\2\u00c0\u00bf\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3" +
					"\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7;\2\2\u00c6" +
					"\23\3\2\2\2\u00c7\u00cc\5&\24\2\u00c8\u00c9\7\30\2\2\u00c9\u00cb\5&\24" +
					"\2\u00ca\u00c8\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd" +
					"\3\2\2\2\u00cd\25\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7\33\2\2\u00d0" +
					"\u00d4\5&\24\2\u00d1\u00d2\7\34\2\2\u00d2\u00d4\5&\24\2\u00d3\u00cf\3" +
					"\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\27\3\2\2\2\u00d5\u00da\5\32\16\2\u00d6" +
					"\u00da\5\36\20\2\u00d7\u00da\5\34\17\2\u00d8\u00da\5$\23\2\u00d9\u00d5" +
					"\3\2\2\2\u00d9\u00d6\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da" +
					"\31\3\2\2\2\u00db\u00dc\t\3\2\2\u00dc\33\3\2\2\2\u00dd\u00e1\5\32\16\2" +
					"\u00de\u00e1\5\36\20\2\u00df\u00e1\5$\23\2\u00e0\u00dd\3\2\2\2\u00e0\u00de" +
					"\3\2\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e3\78\2\2\u00e3" +
					"\u00e5\79\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2" +
					"\2\2\u00e6\u00e7\3\2\2\2\u00e7\35\3\2\2\2\u00e8\u00e9\7\35\2\2\u00e9\u00ea" +
					"\7\66\2\2\u00ea\u00eb\5 \21\2\u00eb\u00ec\7\30\2\2\u00ec\u00ed\5 \21\2" +
					"\u00ed\u00ee\7\67\2\2\u00ee\37\3\2\2\2\u00ef\u00f4\5\32\16\2\u00f0\u00f4" +
					"\5\34\17\2\u00f1\u00f4\5\36\20\2\u00f2\u00f4\7\35\2\2\u00f3\u00ef\3\2" +
					"\2\2\u00f3\u00f0\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4" +
					"!\3\2\2\2\u00f5\u00f6\7$\2\2\u00f6#\3\2\2\2\u00f7\u00f8\7\25\2\2\u00f8" +
					"\u00f9\5D#\2\u00f9%\3\2\2\2\u00fa\u00fb\b\24\1\2\u00fb\u010a\58\35\2\u00fc" +
					"\u010a\5:\36\2\u00fd\u010a\5> \2\u00fe\u010a\5<\37\2\u00ff\u010a\5B\"" +
					"\2\u0100\u010a\5D#\2\u0101\u010a\5\66\34\2\u0102\u0103\5(\25\2\u0103\u0104" +
					"\5&\24\4\u0104\u010a\3\2\2\2\u0105\u0106\7\66\2\2\u0106\u0107\5&\24\2" +
					"\u0107\u0108\7\67\2\2\u0108\u010a\3\2\2\2\u0109\u00fa\3\2\2\2\u0109\u00fc" +
					"\3\2\2\2\u0109\u00fd\3\2\2\2\u0109\u00fe\3\2\2\2\u0109\u00ff\3\2\2\2\u0109" +
					"\u0100\3\2\2\2\u0109\u0101\3\2\2\2\u0109\u0102\3\2\2\2\u0109\u0105\3\2" +
					"\2\2\u010a\u0125\3\2\2\2\u010b\u010c\f\21\2\2\u010c\u010d\5*\26\2\u010d" +
					"\u010e\5&\24\22\u010e\u0124\3\2\2\2\u010f\u0110\f\20\2\2\u0110\u0111\5" +
					",\27\2\u0111\u0112\5&\24\21\u0112\u0124\3\2\2\2\u0113\u0114\f\17\2\2\u0114" +
					"\u0115\5.\30\2\u0115\u0116\5&\24\20\u0116\u0124\3\2\2\2\u0117\u0118\f" +
					"\16\2\2\u0118\u0119\5\60\31\2\u0119\u011a\5&\24\17\u011a\u0124\3\2\2\2" +
					"\u011b\u011c\f\r\2\2\u011c\u011d\5\62\32\2\u011d\u011e\5&\24\16\u011e" +
					"\u0124\3\2\2\2\u011f\u0120\f\f\2\2\u0120\u0121\5\64\33\2\u0121\u0122\5" +
					"&\24\r\u0122\u0124\3\2\2\2\u0123\u010b\3\2\2\2\u0123\u010f\3\2\2\2\u0123" +
					"\u0113\3\2\2\2\u0123\u0117\3\2\2\2\u0123\u011b\3\2\2\2\u0123\u011f\3\2" +
					"\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126" +
					"\'\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\t\4\2\2\u0129)\3\2\2\2\u012a" +
					"\u012b\t\5\2\2\u012b+\3\2\2\2\u012c\u012d\t\6\2\2\u012d-\3\2\2\2\u012e" +
					"\u012f\t\7\2\2\u012f/\3\2\2\2\u0130\u0131\t\b\2\2\u0131\61\3\2\2\2\u0132" +
					"\u0133\7\64\2\2\u0133\63\3\2\2\2\u0134\u0135\7\65\2\2\u0135\65\3\2\2\2" +
					"\u0136\u013b\5D#\2\u0137\u0138\78\2\2\u0138\u0139\5&\24\2\u0139\u013a" +
					"\79\2\2\u013a\u013c\3\2\2\2\u013b\u0137\3\2\2\2\u013c\u013d\3\2\2\2\u013d" +
					"\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\67\3\2\2\2\u013f\u0141\t\6\2" +
					"\2\u0140\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143" +
					"\7<\2\2\u01439\3\2\2\2\u0144\u0145\t\t\2\2\u0145;\3\2\2\2\u0146\u0147" +
					"\7A\2\2\u0147=\3\2\2\2\u0148\u0149\7B\2\2\u0149?\3\2\2\2\u014a\u0153\7" +
					"8\2\2\u014b\u0150\5&\24\2\u014c\u014d\7\30\2\2\u014d\u014f\5&\24\2\u014e" +
					"\u014c\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2" +
					"\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u014b\3\2\2\2\u0153" +
					"\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\79\2\2\u0156A\3\2\2\2\u0157" +
					"\u0158\7\24\2\2\u0158C\3\2\2\2\u0159\u015a\7=\2\2\u015aE\3\2\2\2\32JU" +
					"ak{\u0096\u009d\u00a3\u00b3\u00b8\u00c2\u00cc\u00d3\u00d9\u00e0\u00e6" +
					"\u00f3\u0109\u0123\u0125\u013d\u0140\u0150\u0153";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}