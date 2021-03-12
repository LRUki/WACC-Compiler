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
			DONE = 17, NULL = 18, ASSIGN = 19, SEMICOLON = 20, COMMA = 21, NEWPAIR = 22, CALL = 23,
			FST = 24, SND = 25, PAIR = 26, TRUE = 27, FALSE = 28, INT = 29, BOOL = 30, CHAR = 31,
			STRING = 32, VAR = 33, NOT = 34, LEN = 35, ORD = 36, CHR = 37, REF = 38, PLUS = 39, MINUS = 40,
			MULT = 41, DIV = 42, MOD = 43, GTE = 44, GT = 45, LTE = 46, LT = 47, EQ = 48, NEQ = 49,
			AND = 50, OR = 51, L_PAREN = 52, R_PAREN = 53, L_SQUARE = 54, R_SQUARE = 55, L_CURLY = 56,
			R_CURLY = 57, NUMBER = 58, IDENT = 59, WHITESPACE = 60, ESCAPE_CHARACTER = 61, CHARACTER = 62,
			STR_LITER = 63, CHAR_LITER = 64, COMMENT = 65;
	public static final int
			RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4,
			RULE_assignLhs = 5, RULE_assignRhs = 6, RULE_argList = 7, RULE_pairElem = 8,
			RULE_pointerElem = 9, RULE_type = 10, RULE_baseType = 11, RULE_arrayType = 12,
			RULE_pairType = 13, RULE_pairElemType = 14, RULE_pointerType = 15, RULE_implicitType = 16,
			RULE_expr = 17, RULE_unop = 18, RULE_binop1 = 19, RULE_binop2 = 20, RULE_binop3 = 21,
			RULE_binop4 = 22, RULE_binop5 = 23, RULE_binop6 = 24, RULE_arrayElem = 25,
			RULE_intLiter = 26, RULE_boolLiter = 27, RULE_strLiter = 28, RULE_charLiter = 29,
			RULE_arrayLiter = 30, RULE_pairLiter = 31, RULE_ident = 32;

	private static String[] makeRuleNames() {
		return new String[]{
				"program", "func", "paramList", "param", "stat", "assignLhs", "assignRhs",
				"argList", "pairElem", "pointerElem", "type", "baseType", "arrayType",
				"pairType", "pairElemType", "pointerType", "implicitType", "expr", "unop",
				"binop1", "binop2", "binop3", "binop4", "binop5", "binop6", "arrayElem",
				"intLiter", "boolLiter", "strLiter", "charLiter", "arrayLiter", "pairLiter",
				"ident"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'",
				"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'",
				"'while'", "'do'", "'done'", "'null'", "'='", "';'", "','", "'newpair'",
				"'call'", "'fst'", "'snd'", "'pair'", "'true'", "'false'", "'int'", "'bool'",
				"'char'", "'string'", "'var'", "'!'", "'len'", "'ord'", "'chr'", "'&'",
				"'+'", "'-'", "'*'", "'/'", "'%'", "'>='", "'>'", "'<='", "'<'", "'=='",
				"'!='", "'&&'", "'||'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT",
				"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE",
				"NULL", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL", "FST", "SND",
				"PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING", "VAR", "NOT",
				"LEN", "ORD", "CHR", "REF", "PLUS", "MINUS", "MULT", "DIV", "MOD", "GTE",
				"GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE",
				"R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER", "IDENT", "WHITESPACE", "ESCAPE_CHARACTER",
				"CHARACTER", "STR_LITER", "CHAR_LITER", "COMMENT"
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
				setState(66);
				match(BEGIN);
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(67);
								func();
							}
						}
					}
					setState(72);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
				}
				setState(73);
				stat(0);
				setState(74);
				match(END);
				setState(75);
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
				setState(77);
				type();
				setState(78);
				ident();
				setState(79);
				match(L_PAREN);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 &&
						((1L << _la) & ((1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L
								<< STRING))) != 0)) {
					{
						setState(80);
						paramList();
					}
				}

				setState(83);
				match(R_PAREN);
				setState(84);
				match(IS);
				setState(85);
				stat(0);
				setState(86);
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
				setState(88);
				param();
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(89);
							match(COMMA);
							setState(90);
							param();
						}
					}
					setState(95);
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
				setState(96);
				type();
				setState(97);
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

		public TerminalNode BEGIN() {
			return getToken(WaccParser.BEGIN, 0);
		}

		public StatContext stat() {
			return getRuleContext(StatContext.class, 0);
		}

		public TerminalNode END() {
			return getToken(WaccParser.END, 0);
		}

		public BlockStatContext(StatContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitBlockStat(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class MultiStatContext extends StatContext {

		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}

		public StatContext stat(int i) {
			return getRuleContext(StatContext.class, i);
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

		public TerminalNode ASSIGN() {
			return getToken(WaccParser.ASSIGN, 0);
		}

		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class, 0);
		}

		public AssignStatContext(StatContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitAssignStat(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class DeclareStatContext extends StatContext {

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(WaccParser.ASSIGN, 0);
		}
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
				setState(135);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case SKIP_TOKEN: {
						_localctx = new SkipStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(100);
						match(SKIP_TOKEN);
					}
					break;
					case PAIR:
					case INT:
					case BOOL:
					case CHAR:
					case STRING:
					case VAR: {
						_localctx = new DeclareStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(103);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case PAIR:
							case INT:
							case BOOL:
							case CHAR:
							case STRING: {
								setState(101);
								type();
							}
							break;
							case VAR: {
								setState(102);
								implicitType();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(105);
						ident();
						setState(106);
						match(ASSIGN);
						setState(107);
						assignRhs();
					}
					break;
					case FST:
					case SND:
					case MULT:
					case IDENT: {
						_localctx = new AssignStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(109);
						assignLhs();
						setState(110);
						match(ASSIGN);
						setState(111);
						assignRhs();
					}
					break;
					case READ: {
						_localctx = new ReadStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(113);
						match(READ);
						setState(114);
						assignLhs();
					}
					break;
					case FREE:
					case RETURN:
					case EXIT:
					case PRINT:
					case PRINTLN: {
						_localctx = new ActionStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(115);
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
						setState(116);
						expr(0);
					}
					break;
					case IF: {
						_localctx = new IfStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(117);
						match(IF);
						setState(118);
						expr(0);
						setState(119);
						match(THEN);
						setState(120);
						stat(0);
						setState(121);
						match(ELSE);
						setState(122);
						stat(0);
						setState(123);
						match(FI);
					}
					break;
					case WHILE: {
						_localctx = new WhileStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(125);
						match(WHILE);
						setState(126);
						expr(0);
						setState(127);
						match(DO);
						setState(128);
						stat(0);
						setState(129);
						match(DONE);
					}
					break;
					case BEGIN: {
						_localctx = new BlockStatContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(131);
						match(BEGIN);
						setState(132);
						stat(0);
						setState(133);
						match(END);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
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
								setState(137);
								if (!(precpred(_ctx, 1))) {
									throw new FailedPredicateException(this, "precpred(_ctx, 1)");
								}
								setState(138);
								match(SEMICOLON);
								setState(139);
								stat(2);
							}
						}
					}
					setState(144);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
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
			return getRuleContext(IdentContext.class, 0);
		}

		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class, 0);
		}

		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class, 0);
		}

		public PointerElemContext pointerElem() {
			return getRuleContext(PointerElemContext.class, 0);
		}

		public AssignLhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assignLhs;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitAssignLhs(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final AssignLhsContext assignLhs() throws RecognitionException {
		AssignLhsContext _localctx = new AssignLhsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignLhs);
		try {
			setState(149);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(145);
					ident();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(146);
					arrayElem();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(147);
					pairElem();
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(148);
					pointerElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRhsContext extends ParserRuleContext {

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
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
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NULL:
				case TRUE:
				case FALSE:
				case NOT:
				case LEN:
				case ORD:
				case CHR:
				case REF:
				case PLUS:
				case MINUS:
				case MULT:
				case L_PAREN:
				case NUMBER:
				case IDENT:
				case STR_LITER:
				case CHAR_LITER:
					enterOuterAlt(_localctx, 1);
				{
					setState(151);
					expr(0);
				}
				break;
				case L_SQUARE:
					enterOuterAlt(_localctx, 2);
				{
					setState(152);
					arrayLiter();
				}
				break;
				case NEWPAIR:
					enterOuterAlt(_localctx, 3);
				{
					setState(153);
					match(NEWPAIR);
					setState(154);
					match(L_PAREN);
					setState(155);
					expr(0);
					setState(156);
					match(COMMA);
					setState(157);
					expr(0);
					setState(158);
					match(R_PAREN);
				}
				break;
				case FST:
				case SND:
					enterOuterAlt(_localctx, 4);
				{
					setState(160);
					pairElem();
				}
				break;
				case CALL:
					enterOuterAlt(_localctx, 5);
				{
					setState(161);
					match(CALL);
					setState(162);
					ident();
					setState(163);
					match(L_PAREN);
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 18)) & ~0x3f) == 0 &&
							((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (TRUE - 18)) | (1L << (FALSE
									- 18)) | (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L << (
									CHR - 18)) | (1L << (REF - 18)) | (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L
									<< (MULT - 18)) | (1L << (L_PAREN - 18)) | (1L << (NUMBER - 18)) | (1L << (IDENT
									- 18)) | (1L << (STR_LITER - 18)) | (1L << (CHAR_LITER - 18)))) != 0)) {
						{
							setState(164);
							argList();
						}
					}

					setState(167);
					match(R_PAREN);
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
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(171);
				expr(0);
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(172);
							match(COMMA);
							setState(173);
							expr(0);
						}
					}
					setState(178);
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
		enterRule(_localctx, 16, RULE_pairElem);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case FST:
					enterOuterAlt(_localctx, 1);
				{
					setState(179);
					match(FST);
					setState(180);
					expr(0);
				}
				break;
				case SND:
					enterOuterAlt(_localctx, 2);
				{
					setState(181);
					match(SND);
					setState(182);
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
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerElemContext extends ParserRuleContext {

		public TerminalNode MULT() {
			return getToken(WaccParser.MULT, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public PointerElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_pointerElem;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitPointerElem(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final PointerElemContext pointerElem() throws RecognitionException {
		PointerElemContext _localctx = new PointerElemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pointerElem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(185);
				match(MULT);
				setState(186);
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

		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class, 0);
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
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(192);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(188);
					baseType();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(189);
					pairType();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(190);
					arrayType();
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(191);
					pointerType();
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
		enterRule(_localctx, 22, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(194);
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
		} finally {
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
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(198);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case INT:
					case BOOL:
					case CHAR:
					case STRING: {
						setState(196);
						baseType();
					}
					break;
					case PAIR: {
						setState(197);
						pairType();
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(200);
							match(L_SQUARE);
							setState(201);
							match(R_SQUARE);
						}
					}
					setState(204);
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
		enterRule(_localctx, 26, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(206);
				match(PAIR);
				setState(207);
				match(L_PAREN);
				setState(208);
				pairElemType();
				setState(209);
				match(COMMA);
				setState(210);
				pairElemType();
				setState(211);
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
		enterRule(_localctx, 28, RULE_pairElemType);
		try {
			setState(217);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(213);
					baseType();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(214);
					arrayType();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(215);
					pairType();
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(216);
					match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerTypeContext extends ParserRuleContext {

		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class, 0);
		}

		public TerminalNode MULT() {
			return getToken(WaccParser.MULT, 0);
		}

		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_pointerType;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitPointerType(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(219);
				baseType();
				setState(220);
				match(MULT);
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

	public static class ImplicitTypeContext extends ParserRuleContext {

		public TerminalNode VAR() {
			return getToken(WaccParser.VAR, 0);
		}

		public ImplicitTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_implicitType;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitImplicitType(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final ImplicitTypeContext implicitType() throws RecognitionException {
		ImplicitTypeContext _localctx = new ImplicitTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_implicitType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(222);
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

		public UnopExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitUnopExpr(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public static class SingletonExprContext extends ExprContext {

		public IntLiterContext intLiter() {
			return getRuleContext(IntLiterContext.class, 0);
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(239);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 15, _ctx)) {
					case 1: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(225);
						intLiter();
					}
					break;
					case 2: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(226);
						boolLiter();
					}
					break;
					case 3: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(227);
						charLiter();
					}
					break;
					case 4: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(228);
						strLiter();
					}
					break;
					case 5: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(229);
						pairLiter();
					}
					break;
					case 6: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(230);
						ident();
					}
					break;
					case 7: {
						_localctx = new SingletonExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(231);
						arrayElem();
					}
					break;
					case 8: {
						_localctx = new UnopExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(232);
						unop();
						setState(233);
						expr(2);
					}
					break;
					case 9: {
						_localctx = new ParenExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(235);
						match(L_PAREN);
						setState(236);
						expr(0);
						setState(237);
						match(R_PAREN);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) {
							triggerExitRuleEvent();
						}
						_prevctx = _localctx;
						{
							setState(265);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
								case 1: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(241);
									if (!(precpred(_ctx, 15))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 15)");
									}
									setState(242);
									binop1();
									setState(243);
									expr(16);
								}
								break;
								case 2: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(245);
									if (!(precpred(_ctx, 14))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 14)");
									}
									setState(246);
									binop2();
									setState(247);
									expr(15);
								}
								break;
								case 3: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(249);
									if (!(precpred(_ctx, 13))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 13)");
									}
									setState(250);
									binop3();
									setState(251);
									expr(14);
								}
								break;
								case 4: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(253);
									if (!(precpred(_ctx, 12))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 12)");
									}
									setState(254);
									binop4();
									setState(255);
									expr(13);
								}
								break;
								case 5: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(257);
									if (!(precpred(_ctx, 11))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									}
									setState(258);
									binop5();
									setState(259);
									expr(12);
								}
								break;
								case 6: {
									_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expr);
									setState(261);
									if (!(precpred(_ctx, 10))) {
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									}
									setState(262);
									binop6();
									setState(263);
									expr(11);
								}
								break;
							}
						}
					}
					setState(269);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
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

		public TerminalNode NOT() {
			return getToken(WaccParser.NOT, 0);
		}

		public TerminalNode MINUS() {
			return getToken(WaccParser.MINUS, 0);
		}

		public TerminalNode LEN() {
			return getToken(WaccParser.LEN, 0);
		}

		public TerminalNode ORD() {
			return getToken(WaccParser.ORD, 0);
		}

		public TerminalNode CHR() {
			return getToken(WaccParser.CHR, 0);
		}

		public TerminalNode REF() {
			return getToken(WaccParser.REF, 0);
		}

		public TerminalNode MULT() {
			return getToken(WaccParser.MULT, 0);
		}

		public UnopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_unop;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof WaccParserVisitor) {
				return ((WaccParserVisitor<? extends T>) visitor).visitUnop(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final UnopContext unop() throws RecognitionException {
		UnopContext _localctx = new UnopContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(270);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 &&
						((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << REF) | (
								1L << MINUS) | (1L << MULT))) != 0))) {
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
		enterRule(_localctx, 38, RULE_binop1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(272);
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
		enterRule(_localctx, 40, RULE_binop2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(274);
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
		enterRule(_localctx, 42, RULE_binop3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(276);
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
		enterRule(_localctx, 44, RULE_binop4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(278);
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
		enterRule(_localctx, 46, RULE_binop5);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(280);
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
		enterRule(_localctx, 48, RULE_binop6);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(282);
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
		enterRule(_localctx, 50, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(284);
				ident();
				setState(289);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							{
								setState(285);
								match(L_SQUARE);
								setState(286);
								expr(0);
								setState(287);
								match(R_SQUARE);
							}
						}
						break;
						default:
							throw new NoViableAltException(this);
					}
					setState(291);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
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
		enterRule(_localctx, 52, RULE_intLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == PLUS || _la == MINUS) {
					{
						setState(293);
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

				setState(296);
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
		enterRule(_localctx, 54, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(298);
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
		enterRule(_localctx, 56, RULE_strLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(300);
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
		enterRule(_localctx, 58, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(302);
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
		enterRule(_localctx, 60, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(304);
				match(L_SQUARE);
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 18)) & ~0x3f) == 0 &&
						((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (TRUE - 18)) | (1L << (FALSE - 18))
								| (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L << (CHR - 18))
								| (1L << (REF - 18)) | (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L << (MULT
								- 18)) | (1L << (L_PAREN - 18)) | (1L << (NUMBER - 18)) | (1L << (IDENT - 18)) | (1L
								<< (STR_LITER - 18)) | (1L << (CHAR_LITER - 18)))) != 0)) {
					{
						setState(305);
						expr(0);
						setState(310);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == COMMA) {
							{
								{
									setState(306);
									match(COMMA);
									setState(307);
									expr(0);
								}
							}
							setState(312);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(315);
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
		enterRule(_localctx, 62, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(317);
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
		enterRule(_localctx, 64, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(319);
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
			case 17:
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3C\u0144\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\3\2\3\2\7\2G\n\2\f\2\16\2J\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3" +
					"\3\3\3\5\3T\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13" +
					"\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6j\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3" +
					"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
					"\3\6\3\6\3\6\3\6\3\6\5\6\u008a\n\6\3\6\3\6\3\6\7\6\u008f\n\6\f\6\16\6" +
					"\u0092\13\6\3\7\3\7\3\7\3\7\5\7\u0098\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b" +
					"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a8\n\b\3\b\3\b\5\b\u00ac\n\b\3\t\3" +
					"\t\3\t\7\t\u00b1\n\t\f\t\16\t\u00b4\13\t\3\n\3\n\3\n\3\n\5\n\u00ba\n\n" +
					"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u00c3\n\f\3\r\3\r\3\16\3\16\5\16\u00c9" +
					"\n\16\3\16\3\16\6\16\u00cd\n\16\r\16\16\16\u00ce\3\17\3\17\3\17\3\17\3" +
					"\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u00dc\n\20\3\21\3\21\3\21\3\22" +
					"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\5\23\u00f2\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\7\23\u010c\n\23\f\23\16\23\u010f\13\23\3\24\3\24\3\25\3\25\3\26" +
					"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33" +
					"\6\33\u0124\n\33\r\33\16\33\u0125\3\34\5\34\u0129\n\34\3\34\3\34\3\35" +
					"\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \7 \u0137\n \f \16 \u013a\13 \5 " +
					"\u013c\n \3 \3 \3!\3!\3\"\3\"\3\"\2\4\n$#\2\4\6\b\n\f\16\20\22\24\26\30" +
					"\32\34\36 \"$&(*,.\60\62\64\668:<>@B\2\n\3\2\b\f\3\2\37\"\4\2$(*+\3\2" +
					"+-\3\2)*\3\2.\61\3\2\62\63\3\2\35\36\2\u0152\2D\3\2\2\2\4O\3\2\2\2\6Z" +
					"\3\2\2\2\bb\3\2\2\2\n\u0089\3\2\2\2\f\u0097\3\2\2\2\16\u00ab\3\2\2\2\20" +
					"\u00ad\3\2\2\2\22\u00b9\3\2\2\2\24\u00bb\3\2\2\2\26\u00c2\3\2\2\2\30\u00c4" +
					"\3\2\2\2\32\u00c8\3\2\2\2\34\u00d0\3\2\2\2\36\u00db\3\2\2\2 \u00dd\3\2" +
					"\2\2\"\u00e0\3\2\2\2$\u00f1\3\2\2\2&\u0110\3\2\2\2(\u0112\3\2\2\2*\u0114" +
					"\3\2\2\2,\u0116\3\2\2\2.\u0118\3\2\2\2\60\u011a\3\2\2\2\62\u011c\3\2\2" +
					"\2\64\u011e\3\2\2\2\66\u0128\3\2\2\28\u012c\3\2\2\2:\u012e\3\2\2\2<\u0130" +
					"\3\2\2\2>\u0132\3\2\2\2@\u013f\3\2\2\2B\u0141\3\2\2\2DH\7\3\2\2EG\5\4" +
					"\3\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\5\n" +
					"\6\2LM\7\4\2\2MN\7\2\2\3N\3\3\2\2\2OP\5\26\f\2PQ\5B\"\2QS\7\66\2\2RT\5" +
					"\6\4\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\67\2\2VW\7\5\2\2WX\5\n\6\2XY" +
					"\7\4\2\2Y\5\3\2\2\2Z_\5\b\5\2[\\\7\27\2\2\\^\5\b\5\2][\3\2\2\2^a\3\2\2" +
					"\2_]\3\2\2\2_`\3\2\2\2`\7\3\2\2\2a_\3\2\2\2bc\5\26\f\2cd\5B\"\2d\t\3\2" +
					"\2\2ef\b\6\1\2f\u008a\7\6\2\2gj\5\26\f\2hj\5\"\22\2ig\3\2\2\2ih\3\2\2" +
					"\2jk\3\2\2\2kl\5B\"\2lm\7\25\2\2mn\5\16\b\2n\u008a\3\2\2\2op\5\f\7\2p" +
					"q\7\25\2\2qr\5\16\b\2r\u008a\3\2\2\2st\7\7\2\2t\u008a\5\f\7\2uv\t\2\2" +
					"\2v\u008a\5$\23\2wx\7\r\2\2xy\5$\23\2yz\7\16\2\2z{\5\n\6\2{|\7\17\2\2" +
					"|}\5\n\6\2}~\7\20\2\2~\u008a\3\2\2\2\177\u0080\7\21\2\2\u0080\u0081\5" +
					"$\23\2\u0081\u0082\7\22\2\2\u0082\u0083\5\n\6\2\u0083\u0084\7\23\2\2\u0084" +
					"\u008a\3\2\2\2\u0085\u0086\7\3\2\2\u0086\u0087\5\n\6\2\u0087\u0088\7\4" +
					"\2\2\u0088\u008a\3\2\2\2\u0089e\3\2\2\2\u0089i\3\2\2\2\u0089o\3\2\2\2" +
					"\u0089s\3\2\2\2\u0089u\3\2\2\2\u0089w\3\2\2\2\u0089\177\3\2\2\2\u0089" +
					"\u0085\3\2\2\2\u008a\u0090\3\2\2\2\u008b\u008c\f\3\2\2\u008c\u008d\7\26" +
					"\2\2\u008d\u008f\5\n\6\4\u008e\u008b\3\2\2\2\u008f\u0092\3\2\2\2\u0090" +
					"\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\13\3\2\2\2\u0092\u0090\3\2\2" +
					"\2\u0093\u0098\5B\"\2\u0094\u0098\5\64\33\2\u0095\u0098\5\22\n\2\u0096" +
					"\u0098\5\24\13\2\u0097\u0093\3\2\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3" +
					"\2\2\2\u0097\u0096\3\2\2\2\u0098\r\3\2\2\2\u0099\u00ac\5$\23\2\u009a\u00ac" +
					"\5> \2\u009b\u009c\7\30\2\2\u009c\u009d\7\66\2\2\u009d\u009e\5$\23\2\u009e" +
					"\u009f\7\27\2\2\u009f\u00a0\5$\23\2\u00a0\u00a1\7\67\2\2\u00a1\u00ac\3" +
					"\2\2\2\u00a2\u00ac\5\22\n\2\u00a3\u00a4\7\31\2\2\u00a4\u00a5\5B\"\2\u00a5" +
					"\u00a7\7\66\2\2\u00a6\u00a8\5\20\t\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3" +
					"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\7\67\2\2\u00aa\u00ac\3\2\2\2\u00ab" +
					"\u0099\3\2\2\2\u00ab\u009a\3\2\2\2\u00ab\u009b\3\2\2\2\u00ab\u00a2\3\2" +
					"\2\2\u00ab\u00a3\3\2\2\2\u00ac\17\3\2\2\2\u00ad\u00b2\5$\23\2\u00ae\u00af" +
					"\7\27\2\2\u00af\u00b1\5$\23\2\u00b0\u00ae\3\2\2\2\u00b1\u00b4\3\2\2\2" +
					"\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\21\3\2\2\2\u00b4\u00b2" +
					"\3\2\2\2\u00b5\u00b6\7\32\2\2\u00b6\u00ba\5$\23\2\u00b7\u00b8\7\33\2\2" +
					"\u00b8\u00ba\5$\23\2\u00b9\u00b5\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\23" +
					"\3\2\2\2\u00bb\u00bc\7+\2\2\u00bc\u00bd\5B\"\2\u00bd\25\3\2\2\2\u00be" +
					"\u00c3\5\30\r\2\u00bf\u00c3\5\34\17\2\u00c0\u00c3\5\32\16\2\u00c1\u00c3" +
					"\5 \21\2\u00c2\u00be\3\2\2\2\u00c2\u00bf\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2" +
					"\u00c1\3\2\2\2\u00c3\27\3\2\2\2\u00c4\u00c5\t\3\2\2\u00c5\31\3\2\2\2\u00c6" +
					"\u00c9\5\30\r\2\u00c7\u00c9\5\34\17\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7" +
					"\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00cb\78\2\2\u00cb\u00cd\79\2\2\u00cc" +
					"\u00ca\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2" +
					"\2\2\u00cf\33\3\2\2\2\u00d0\u00d1\7\34\2\2\u00d1\u00d2\7\66\2\2\u00d2" +
					"\u00d3\5\36\20\2\u00d3\u00d4\7\27\2\2\u00d4\u00d5\5\36\20\2\u00d5\u00d6" +
					"\7\67\2\2\u00d6\35\3\2\2\2\u00d7\u00dc\5\30\r\2\u00d8\u00dc\5\32\16\2" +
					"\u00d9\u00dc\5\34\17\2\u00da\u00dc\7\34\2\2\u00db\u00d7\3\2\2\2\u00db" +
					"\u00d8\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00da\3\2\2\2\u00dc\37\3\2\2" +
					"\2\u00dd\u00de\5\30\r\2\u00de\u00df\7+\2\2\u00df!\3\2\2\2\u00e0\u00e1" +
					"\7#\2\2\u00e1#\3\2\2\2\u00e2\u00e3\b\23\1\2\u00e3\u00f2\5\66\34\2\u00e4" +
					"\u00f2\58\35\2\u00e5\u00f2\5<\37\2\u00e6\u00f2\5:\36\2\u00e7\u00f2\5@" +
					"!\2\u00e8\u00f2\5B\"\2\u00e9\u00f2\5\64\33\2\u00ea\u00eb\5&\24\2\u00eb" +
					"\u00ec\5$\23\4\u00ec\u00f2\3\2\2\2\u00ed\u00ee\7\66\2\2\u00ee\u00ef\5" +
					"$\23\2\u00ef\u00f0\7\67\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00e2\3\2\2\2\u00f1" +
					"\u00e4\3\2\2\2\u00f1\u00e5\3\2\2\2\u00f1\u00e6\3\2\2\2\u00f1\u00e7\3\2" +
					"\2\2\u00f1\u00e8\3\2\2\2\u00f1\u00e9\3\2\2\2\u00f1\u00ea\3\2\2\2\u00f1" +
					"\u00ed\3\2\2\2\u00f2\u010d\3\2\2\2\u00f3\u00f4\f\21\2\2\u00f4\u00f5\5" +
					"(\25\2\u00f5\u00f6\5$\23\22\u00f6\u010c\3\2\2\2\u00f7\u00f8\f\20\2\2\u00f8" +
					"\u00f9\5*\26\2\u00f9\u00fa\5$\23\21\u00fa\u010c\3\2\2\2\u00fb\u00fc\f" +
					"\17\2\2\u00fc\u00fd\5,\27\2\u00fd\u00fe\5$\23\20\u00fe\u010c\3\2\2\2\u00ff" +
					"\u0100\f\16\2\2\u0100\u0101\5.\30\2\u0101\u0102\5$\23\17\u0102\u010c\3" +
					"\2\2\2\u0103\u0104\f\r\2\2\u0104\u0105\5\60\31\2\u0105\u0106\5$\23\16" +
					"\u0106\u010c\3\2\2\2\u0107\u0108\f\f\2\2\u0108\u0109\5\62\32\2\u0109\u010a" +
					"\5$\23\r\u010a\u010c\3\2\2\2\u010b\u00f3\3\2\2\2\u010b\u00f7\3\2\2\2\u010b" +
					"\u00fb\3\2\2\2\u010b\u00ff\3\2\2\2\u010b\u0103\3\2\2\2\u010b\u0107\3\2" +
					"\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e" +
					"%\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\t\4\2\2\u0111\'\3\2\2\2\u0112" +
					"\u0113\t\5\2\2\u0113)\3\2\2\2\u0114\u0115\t\6\2\2\u0115+\3\2\2\2\u0116" +
					"\u0117\t\7\2\2\u0117-\3\2\2\2\u0118\u0119\t\b\2\2\u0119/\3\2\2\2\u011a" +
					"\u011b\7\64\2\2\u011b\61\3\2\2\2\u011c\u011d\7\65\2\2\u011d\63\3\2\2\2" +
					"\u011e\u0123\5B\"\2\u011f\u0120\78\2\2\u0120\u0121\5$\23\2\u0121\u0122" +
					"\79\2\2\u0122\u0124\3\2\2\2\u0123\u011f\3\2\2\2\u0124\u0125\3\2\2\2\u0125" +
					"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\65\3\2\2\2\u0127\u0129\t\6\2" +
					"\2\u0128\u0127\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b" +
					"\7<\2\2\u012b\67\3\2\2\2\u012c\u012d\t\t\2\2\u012d9\3\2\2\2\u012e\u012f" +
					"\7A\2\2\u012f;\3\2\2\2\u0130\u0131\7B\2\2\u0131=\3\2\2\2\u0132\u013b\7" +
					"8\2\2\u0133\u0138\5$\23\2\u0134\u0135\7\27\2\2\u0135\u0137\5$\23\2\u0136" +
					"\u0134\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2" +
					"\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u0133\3\2\2\2\u013b" +
					"\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\79\2\2\u013e?\3\2\2\2\u013f" +
					"\u0140\7\24\2\2\u0140A\3\2\2\2\u0141\u0142\7=\2\2\u0142C\3\2\2\2\30HS" +
					"_i\u0089\u0090\u0097\u00a7\u00ab\u00b2\u00b9\u00c2\u00c8\u00ce\u00db\u00f1" +
					"\u010b\u010d\u0125\u0128\u0138\u013b";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}