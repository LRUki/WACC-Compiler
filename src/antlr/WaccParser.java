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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, IS=3, SKIP_TOKEN=4, READ=5, FREE=6, RETURN=7, EXIT=8, 
		PRINT=9, PRINTLN=10, IF=11, THEN=12, ELSE=13, FI=14, WHILE=15, FOR=16, 
		DO=17, DONE=18, NULL=19, STRUCT=20, DOT=21, IMPORT=22, ASSIGN=23, SEMICOLON=24, 
		COMMA=25, NEWPAIR=26, CALL=27, FST=28, SND=29, PAIR=30, TRUE=31, FALSE=32, 
		INT=33, BOOL=34, CHAR=35, STRING=36, VAR=37, VOID=38, NOT=39, LEN=40, 
		ORD=41, CHR=42, REF=43, PLUS=44, MINUS=45, MULT=46, DIV=47, MOD=48, GTE=49, 
		GT=50, LTE=51, LT=52, EQ=53, NEQ=54, AND=55, OR=56, L_PAREN=57, R_PAREN=58, 
		L_SQUARE=59, R_SQUARE=60, L_CURLY=61, R_CURLY=62, NUMBER=63, CAPTIALISED_IDENT=64, 
		IDENT=65, WHITESPACE=66, ESCAPE_CHARACTER=67, CHARACTER=68, STR_LITER=69, 
		CHAR_LITER=70, COMMENT=71;
	public static final int
		RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4, 
		RULE_assignLhs = 5, RULE_assignRhs = 6, RULE_importStat = 7, RULE_structDeclare = 8, 
		RULE_structAssign = 9, RULE_structMember = 10, RULE_structAccess = 11, 
		RULE_argList = 12, RULE_pairElem = 13, RULE_pointerElem = 14, RULE_type = 15, 
		RULE_baseType = 16, RULE_arrayType = 17, RULE_pairType = 18, RULE_pairElemType = 19, 
		RULE_pointerType = 20, RULE_implicitType = 21, RULE_structType = 22, RULE_expr = 23, 
		RULE_unop = 24, RULE_binop1 = 25, RULE_binop2 = 26, RULE_binop3 = 27, 
		RULE_binop4 = 28, RULE_binop5 = 29, RULE_binop6 = 30, RULE_arrayElem = 31, 
		RULE_intLiter = 32, RULE_boolLiter = 33, RULE_strLiter = 34, RULE_charLiter = 35, 
		RULE_arrayLiter = 36, RULE_pairLiter = 37, RULE_ident = 38, RULE_capitalisedIdent = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "func", "paramList", "param", "stat", "assignLhs", "assignRhs", 
			"importStat", "structDeclare", "structAssign", "structMember", "structAccess", 
			"argList", "pairElem", "pointerElem", "type", "baseType", "arrayType", 
			"pairType", "pairElemType", "pointerType", "implicitType", "structType", 
			"expr", "unop", "binop1", "binop2", "binop3", "binop4", "binop5", "binop6", 
			"arrayElem", "intLiter", "boolLiter", "strLiter", "charLiter", "arrayLiter", 
			"pairLiter", "ident", "capitalisedIdent"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'", 
			"'while'", "'for'", "'do'", "'done'", "'null'", "'struct'", "'.'", "'import'", 
			"'='", "';'", "','", "'newpair'", "'call'", "'fst'", "'snd'", "'pair'", 
			"'true'", "'false'", "'int'", "'bool'", "'char'", "'string'", "'var'", 
			"'void'", "'!'", "'len'", "'ord'", "'chr'", "'&'", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'>='", "'>'", "'<='", "'<'", "'=='", "'!='", "'&&'", "'||'", 
			"'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "FOR", "DO", 
			"DONE", "NULL", "STRUCT", "DOT", "IMPORT", "ASSIGN", "SEMICOLON", "COMMA", 
			"NEWPAIR", "CALL", "FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", 
			"CHAR", "STRING", "VAR", "VOID", "NOT", "LEN", "ORD", "CHR", "REF", "PLUS", 
			"MINUS", "MULT", "DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", 
			"AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE", "R_SQUARE", "L_CURLY", 
			"R_CURLY", "NUMBER", "CAPTIALISED_IDENT", "IDENT", "WHITESPACE", "ESCAPE_CHARACTER", 
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
		public List<ImportStatContext> importStat() {
			return getRuleContexts(ImportStatContext.class);
		}
		public ImportStatContext importStat(int i) {
			return getRuleContext(ImportStatContext.class,i);
		}
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(80);
				importStat();
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			match(BEGIN);
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(87);
					func();
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(93);
			stat(0);
			setState(94);
			match(END);
			setState(95);
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
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(WaccParser.VOID, 0); }
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
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRUCT:
			case PAIR:
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case L_PAREN:
				{
				setState(97);
				type();
				}
				break;
			case VOID:
				{
				setState(98);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(101);
			ident();
			setState(102);
			match(L_PAREN);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << L_PAREN))) != 0)) {
				{
				setState(103);
				paramList();
				}
			}

			setState(106);
			match(R_PAREN);
			setState(107);
			match(IS);
			setState(108);
			stat(0);
			setState(109);
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
			setState(111);
			param();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(112);
				match(COMMA);
				setState(113);
				param();
				}
				}
				setState(118);
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
			setState(119);
			type();
			setState(120);
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
		public TerminalNode SEMICOLON() { return getToken(WaccParser.SEMICOLON, 0); }
		public MultiStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitMultiStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallStatContext extends StatContext {
		public TerminalNode CALL() { return getToken(WaccParser.CALL, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(WaccParser.L_PAREN, 0); }
		public TerminalNode R_PAREN() { return getToken(WaccParser.R_PAREN, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public CallStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCallStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StructDeclareStatContext extends StatContext {
		public StructDeclareContext structDeclare() {
			return getRuleContext(StructDeclareContext.class,0);
		}
		public StructDeclareStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructDeclareStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ActionStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FREE() { return getToken(WaccParser.FREE, 0); }
		public TerminalNode RETURN() { return getToken(WaccParser.RETURN, 0); }
		public TerminalNode EXIT() { return getToken(WaccParser.EXIT, 0); }
		public TerminalNode PRINT() { return getToken(WaccParser.PRINT, 0); }
		public TerminalNode PRINTLN() { return getToken(WaccParser.PRINTLN, 0); }
		public ActionStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitActionStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStatContext extends StatContext {
		public TerminalNode FOR() { return getToken(WaccParser.FOR, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(WaccParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(WaccParser.SEMICOLON, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WaccParser.DO, 0); }
		public TerminalNode DONE() { return getToken(WaccParser.DONE, 0); }
		public ForStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitForStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipStatContext extends StatContext {
		public TerminalNode SKIP_TOKEN() { return getToken(WaccParser.SKIP_TOKEN, 0); }
		public SkipStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitSkipStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VoidReturnStatContext extends StatContext {
		public TerminalNode RETURN() { return getToken(WaccParser.RETURN, 0); }
		public VoidReturnStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitVoidReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStatContext extends StatContext {
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
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
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new SkipStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(123);
				match(SKIP_TOKEN);
				}
				break;
			case 2:
				{
				_localctx = new CallStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				match(CALL);
				setState(125);
				ident();
				setState(126);
				match(L_PAREN);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 19)) & ~0x3f) == 0 && ((1L << (_la - 19)) & ((1L << (NULL - 19)) | (1L << (TRUE - 19)) | (1L << (FALSE - 19)) | (1L << (NOT - 19)) | (1L << (LEN - 19)) | (1L << (ORD - 19)) | (1L << (CHR - 19)) | (1L << (REF - 19)) | (1L << (PLUS - 19)) | (1L << (MINUS - 19)) | (1L << (MULT - 19)) | (1L << (L_PAREN - 19)) | (1L << (NUMBER - 19)) | (1L << (CAPTIALISED_IDENT - 19)) | (1L << (IDENT - 19)) | (1L << (STR_LITER - 19)) | (1L << (CHAR_LITER - 19)))) != 0)) {
					{
					setState(127);
					argList();
					}
				}

				setState(130);
				match(R_PAREN);
				}
				break;
			case 3:
				{
				_localctx = new DeclareStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRUCT:
				case PAIR:
				case INT:
				case BOOL:
				case CHAR:
				case STRING:
				case L_PAREN:
					{
					setState(132);
					type();
					}
					break;
				case VAR:
					{
					setState(133);
					implicitType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(136);
				ident();
				setState(137);
				match(ASSIGN);
				setState(138);
				assignRhs();
				}
				break;
			case 4:
				{
				_localctx = new AssignStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				assignLhs();
				setState(141);
				match(ASSIGN);
				setState(142);
				assignRhs();
				}
				break;
			case 5:
				{
				_localctx = new StructDeclareStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				structDeclare();
				}
				break;
			case 6:
				{
				_localctx = new ReadStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(READ);
				setState(146);
				assignLhs();
				}
				break;
			case 7:
				{
				_localctx = new ActionStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FREE) | (1L << RETURN) | (1L << EXIT) | (1L << PRINT) | (1L << PRINTLN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(148);
				expr(0);
				}
				break;
			case 8:
				{
				_localctx = new VoidReturnStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149);
				match(RETURN);
				}
				break;
			case 9:
				{
				_localctx = new IfStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(IF);
				setState(151);
				expr(0);
				setState(152);
				match(THEN);
				setState(153);
				stat(0);
				setState(154);
				match(ELSE);
				setState(155);
				stat(0);
				setState(156);
				match(FI);
				}
				break;
			case 10:
				{
				_localctx = new WhileStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(158);
				match(WHILE);
				setState(159);
				expr(0);
				setState(160);
				match(DO);
				setState(161);
				stat(0);
				setState(162);
				match(DONE);
				}
				break;
			case 11:
				{
				_localctx = new ForStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164);
				match(FOR);
				setState(165);
				stat(0);
				setState(166);
				match(SEMICOLON);
				setState(167);
				expr(0);
				setState(168);
				match(SEMICOLON);
				setState(169);
				stat(0);
				setState(170);
				match(DO);
				setState(171);
				stat(0);
				setState(172);
				match(DONE);
				}
				break;
			case 12:
				{
				_localctx = new BlockStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				match(BEGIN);
				setState(175);
				stat(0);
				setState(176);
				match(END);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(185);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultiStatContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(180);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(181);
					match(SEMICOLON);
					setState(182);
					stat(2);
					}
					} 
				}
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public PointerElemContext pointerElem() {
			return getRuleContext(PointerElemContext.class,0);
		}
		public StructAccessContext structAccess() {
			return getRuleContext(StructAccessContext.class,0);
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
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(190);
				pairElem();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(191);
				pointerElem();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(192);
				structAccess();
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
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public TerminalNode NEWPAIR() { return getToken(WaccParser.NEWPAIR, 0); }
		public TerminalNode L_PAREN() { return getToken(WaccParser.L_PAREN, 0); }
		public TerminalNode COMMA() { return getToken(WaccParser.COMMA, 0); }
		public TerminalNode R_PAREN() { return getToken(WaccParser.R_PAREN, 0); }
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public TerminalNode CALL() { return getToken(WaccParser.CALL, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public StructAssignContext structAssign() {
			return getRuleContext(StructAssignContext.class,0);
		}
		public AssignRhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRhs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAssignRhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRhsContext assignRhs() throws RecognitionException {
		AssignRhsContext _localctx = new AssignRhsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignRhs);
		int _la;
		try {
			setState(214);
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
			case CAPTIALISED_IDENT:
			case IDENT:
			case STR_LITER:
			case CHAR_LITER:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				expr(0);
				}
				break;
			case L_SQUARE:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				arrayLiter();
				}
				break;
			case NEWPAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				match(NEWPAIR);
				setState(198);
				match(L_PAREN);
				setState(199);
				expr(0);
				setState(200);
				match(COMMA);
				setState(201);
				expr(0);
				setState(202);
				match(R_PAREN);
				}
				break;
			case FST:
			case SND:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(205);
				match(CALL);
				setState(206);
				ident();
				setState(207);
				match(L_PAREN);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 19)) & ~0x3f) == 0 && ((1L << (_la - 19)) & ((1L << (NULL - 19)) | (1L << (TRUE - 19)) | (1L << (FALSE - 19)) | (1L << (NOT - 19)) | (1L << (LEN - 19)) | (1L << (ORD - 19)) | (1L << (CHR - 19)) | (1L << (REF - 19)) | (1L << (PLUS - 19)) | (1L << (MINUS - 19)) | (1L << (MULT - 19)) | (1L << (L_PAREN - 19)) | (1L << (NUMBER - 19)) | (1L << (CAPTIALISED_IDENT - 19)) | (1L << (IDENT - 19)) | (1L << (STR_LITER - 19)) | (1L << (CHAR_LITER - 19)))) != 0)) {
					{
					setState(208);
					argList();
					}
				}

				setState(211);
				match(R_PAREN);
				}
				break;
			case L_CURLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(213);
				structAssign();
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

	public static class ImportStatContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(WaccParser.IMPORT, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode DOT() { return getToken(WaccParser.DOT, 0); }
		public ImportStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitImportStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatContext importStat() throws RecognitionException {
		ImportStatContext _localctx = new ImportStatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_importStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(IMPORT);
			setState(217);
			ident();
			setState(218);
			match(DOT);
			setState(219);
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

	public static class StructDeclareContext extends ParserRuleContext {
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public TerminalNode L_CURLY() { return getToken(WaccParser.L_CURLY, 0); }
		public TerminalNode R_CURLY() { return getToken(WaccParser.R_CURLY, 0); }
		public List<StructMemberContext> structMember() {
			return getRuleContexts(StructMemberContext.class);
		}
		public StructMemberContext structMember(int i) {
			return getRuleContext(StructMemberContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(WaccParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(WaccParser.SEMICOLON, i);
		}
		public StructDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structDeclare; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructDeclareContext structDeclare() throws RecognitionException {
		StructDeclareContext _localctx = new StructDeclareContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_structDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			structType();
			setState(222);
			match(L_CURLY);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << L_PAREN))) != 0)) {
				{
				{
				setState(223);
				structMember();
				setState(224);
				match(SEMICOLON);
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
			match(R_CURLY);
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

	public static class StructAssignContext extends ParserRuleContext {
		public TerminalNode L_CURLY() { return getToken(WaccParser.L_CURLY, 0); }
		public TerminalNode R_CURLY() { return getToken(WaccParser.R_CURLY, 0); }
		public List<AssignRhsContext> assignRhs() {
			return getRuleContexts(AssignRhsContext.class);
		}
		public AssignRhsContext assignRhs(int i) {
			return getRuleContext(AssignRhsContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WaccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}
		public StructAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structAssign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructAssignContext structAssign() throws RecognitionException {
		StructAssignContext _localctx = new StructAssignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_structAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(L_CURLY);
			{
			setState(234);
			assignRhs();
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(235);
				match(COMMA);
				setState(236);
				assignRhs();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(242);
			match(R_CURLY);
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

	public static class StructMemberContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public StructMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structMember; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructMemberContext structMember() throws RecognitionException {
		StructMemberContext _localctx = new StructMemberContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_structMember);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			type();
			setState(245);
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

	public static class StructAccessContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode DOT() { return getToken(WaccParser.DOT, 0); }
		public StructAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structAccess; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructAccessContext structAccess() throws RecognitionException {
		StructAccessContext _localctx = new StructAccessContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_structAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			ident();
			setState(248);
			match(DOT);
			setState(249);
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

	public static class ArgListContext extends ParserRuleContext {
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
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			expr(0);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(252);
				match(COMMA);
				setState(253);
				expr(0);
				}
				}
				setState(258);
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
		enterRule(_localctx, 26, RULE_pairElem);
		try {
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(FST);
				setState(260);
				expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(SND);
				setState(262);
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

	public static class PointerElemContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(WaccParser.MULT, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public PointerElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPointerElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointerElemContext pointerElem() throws RecognitionException {
		PointerElemContext _localctx = new PointerElemContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pointerElem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(MULT);
			setState(266);
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

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_type);
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				pairType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				arrayType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(271);
				structType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(272);
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
		enterRule(_localctx, 32, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(WaccParser.L_PAREN, 0); }
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(WaccParser.R_PAREN, 0); }
		public List<TerminalNode> L_SQUARE() { return getTokens(WaccParser.L_SQUARE); }
		public TerminalNode L_SQUARE(int i) {
			return getToken(WaccParser.L_SQUARE, i);
		}
		public List<TerminalNode> R_SQUARE() { return getTokens(WaccParser.R_SQUARE); }
		public TerminalNode R_SQUARE(int i) {
			return getToken(WaccParser.R_SQUARE, i);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(277);
				baseType();
				}
				break;
			case PAIR:
				{
				setState(278);
				pairType();
				}
				break;
			case STRUCT:
				{
				setState(279);
				structType();
				}
				break;
			case L_PAREN:
				{
				setState(280);
				match(L_PAREN);
				setState(281);
				pointerType();
				setState(282);
				match(R_PAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(288); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				match(L_SQUARE);
				setState(287);
				match(R_SQUARE);
				}
				}
				setState(290); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==L_SQUARE );
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
		enterRule(_localctx, 36, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(PAIR);
			setState(293);
			match(L_PAREN);
			setState(294);
			pairElemType();
			setState(295);
			match(COMMA);
			setState(296);
			pairElemType();
			setState(297);
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
		enterRule(_localctx, 38, RULE_pairElemType);
		try {
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(299);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(300);
				arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(301);
				pairType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(302);
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

	public static class PointerTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public List<TerminalNode> MULT() { return getTokens(WaccParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(WaccParser.MULT, i);
		}
		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPointerType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_pointerType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(305);
				baseType();
				}
				break;
			case 2:
				{
				setState(306);
				pairType();
				}
				break;
			case 3:
				{
				setState(307);
				arrayType();
				}
				break;
			case 4:
				{
				setState(308);
				structType();
				}
				break;
			}
			setState(312); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(311);
				match(MULT);
				}
				}
				setState(314); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MULT );
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
		enterRule(_localctx, 42, RULE_implicitType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(VAR);
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

	public static class StructTypeContext extends ParserRuleContext {
		public TerminalNode STRUCT() { return getToken(WaccParser.STRUCT, 0); }
		public CapitalisedIdentContext capitalisedIdent() {
			return getRuleContext(CapitalisedIdentContext.class,0);
		}
		public StructTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_structType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(STRUCT);
			setState(319);
			capitalisedIdent();
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnopExprContext extends ExprContext {
		public UnopContext unop() {
			return getRuleContext(UnopContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
	public static class StructAccessExprContext extends ExprContext {
		public StructAccessContext structAccess() {
			return getRuleContext(StructAccessContext.class,0);
		}
		public StructAccessExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStructAccessExpr(this);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(322);
				intLiter();
				}
				break;
			case 2:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(323);
				boolLiter();
				}
				break;
			case 3:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(324);
				charLiter();
				}
				break;
			case 4:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(325);
				strLiter();
				}
				break;
			case 5:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(326);
				pairLiter();
				}
				break;
			case 6:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(327);
				ident();
				}
				break;
			case 7:
				{
				_localctx = new SingletonExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(328);
				arrayElem();
				}
				break;
			case 8:
				{
				_localctx = new UnopExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(329);
				unop();
				setState(330);
				expr(3);
				}
				break;
			case 9:
				{
				_localctx = new StructAccessExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(332);
				structAccess();
				}
				break;
			case 10:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(333);
				match(L_PAREN);
				setState(334);
				expr(0);
				setState(335);
				match(R_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(365);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(363);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(339);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(340);
						binop1();
						setState(341);
						expr(17);
						}
						break;
					case 2:
						{
						_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(343);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(344);
						binop2();
						setState(345);
						expr(16);
						}
						break;
					case 3:
						{
						_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(347);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(348);
						binop3();
						setState(349);
						expr(15);
						}
						break;
					case 4:
						{
						_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(351);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(352);
						binop4();
						setState(353);
						expr(14);
						}
						break;
					case 5:
						{
						_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(355);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(356);
						binop5();
						setState(357);
						expr(13);
						}
						break;
					case 6:
						{
						_localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(359);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(360);
						binop6();
						setState(361);
						expr(12);
						}
						break;
					}
					} 
				}
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
		public TerminalNode REF() { return getToken(WaccParser.REF, 0); }
		public TerminalNode MULT() { return getToken(WaccParser.MULT, 0); }
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
		enterRule(_localctx, 48, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << REF) | (1L << MINUS) | (1L << MULT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
		enterRule(_localctx, 50, RULE_binop1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT) | (1L << DIV) | (1L << MOD))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
		enterRule(_localctx, 52, RULE_binop2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
		enterRule(_localctx, 54, RULE_binop3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GTE) | (1L << GT) | (1L << LTE) | (1L << LT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
		enterRule(_localctx, 56, RULE_binop4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			_la = _input.LA(1);
			if ( !(_la==EQ || _la==NEQ) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
		enterRule(_localctx, 58, RULE_binop5);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(AND);
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
		enterRule(_localctx, 60, RULE_binop6);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(OR);
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
		enterRule(_localctx, 62, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			ident();
			setState(387); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(383);
					match(L_SQUARE);
					setState(384);
					expr(0);
					setState(385);
					match(R_SQUARE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(389); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		enterRule(_localctx, 64, RULE_intLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(391);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(394);
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
		enterRule(_localctx, 66, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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
		enterRule(_localctx, 68, RULE_strLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(STR_LITER);
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
		enterRule(_localctx, 70, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(CHAR_LITER);
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
		enterRule(_localctx, 72, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			match(L_SQUARE);
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 19)) & ~0x3f) == 0 && ((1L << (_la - 19)) & ((1L << (NULL - 19)) | (1L << (TRUE - 19)) | (1L << (FALSE - 19)) | (1L << (NOT - 19)) | (1L << (LEN - 19)) | (1L << (ORD - 19)) | (1L << (CHR - 19)) | (1L << (REF - 19)) | (1L << (PLUS - 19)) | (1L << (MINUS - 19)) | (1L << (MULT - 19)) | (1L << (L_PAREN - 19)) | (1L << (NUMBER - 19)) | (1L << (CAPTIALISED_IDENT - 19)) | (1L << (IDENT - 19)) | (1L << (STR_LITER - 19)) | (1L << (CHAR_LITER - 19)))) != 0)) {
				{
				setState(403);
				expr(0);
				setState(408);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(404);
					match(COMMA);
					setState(405);
					expr(0);
					}
					}
					setState(410);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(413);
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
		enterRule(_localctx, 74, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(NULL);
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

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode CAPTIALISED_IDENT() { return getToken(WaccParser.CAPTIALISED_IDENT, 0); }
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
		enterRule(_localctx, 76, RULE_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			_la = _input.LA(1);
			if ( !(_la==CAPTIALISED_IDENT || _la==IDENT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
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

	public static class CapitalisedIdentContext extends ParserRuleContext {
		public TerminalNode CAPTIALISED_IDENT() { return getToken(WaccParser.CAPTIALISED_IDENT, 0); }
		public CapitalisedIdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capitalisedIdent; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCapitalisedIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CapitalisedIdentContext capitalisedIdent() throws RecognitionException {
		CapitalisedIdentContext _localctx = new CapitalisedIdentContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_capitalisedIdent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(CAPTIALISED_IDENT);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 23:
			return expr_sempred((ExprContext)_localctx, predIndex);
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
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3I\u01a8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\7\2T\n\2\f"+
		"\2\16\2W\13\2\3\2\3\2\7\2[\n\2\f\2\16\2^\13\2\3\2\3\2\3\2\3\2\3\3\3\3"+
		"\5\3f\n\3\3\3\3\3\3\3\5\3k\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4u\n"+
		"\4\f\4\16\4x\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083\n\6\3"+
		"\6\3\6\3\6\3\6\5\6\u0089\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00b5\n\6"+
		"\3\6\3\6\3\6\7\6\u00ba\n\6\f\6\16\6\u00bd\13\6\3\7\3\7\3\7\3\7\3\7\5\7"+
		"\u00c4\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"\u00d4\n\b\3\b\3\b\3\b\5\b\u00d9\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\7\n\u00e5\n\n\f\n\16\n\u00e8\13\n\3\n\3\n\3\13\3\13\3\13\3\13\7"+
		"\13\u00f0\n\13\f\13\16\13\u00f3\13\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\7\16\u0101\n\16\f\16\16\16\u0104\13\16\3\17\3\17"+
		"\3\17\3\17\5\17\u010a\n\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u0114\n\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u011f\n"+
		"\23\3\23\3\23\6\23\u0123\n\23\r\23\16\23\u0124\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\5\25\u0132\n\25\3\26\3\26\3\26\3\26\5\26"+
		"\u0138\n\26\3\26\6\26\u013b\n\26\r\26\16\26\u013c\3\27\3\27\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\5\31\u0154\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\7\31\u016e\n\31\f\31\16\31\u0171\13\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\6!\u0186"+
		"\n!\r!\16!\u0187\3\"\5\"\u018b\n\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&"+
		"\3&\7&\u0199\n&\f&\16&\u019c\13&\5&\u019e\n&\3&\3&\3\'\3\'\3(\3(\3)\3"+
		")\3)\2\4\n\60*\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFHJLNP\2\13\3\2\b\f\3\2#&\4\2)-/\60\3\2\60\62\3\2./\3\2\63\66"+
		"\3\2\678\3\2!\"\3\2BC\2\u01c2\2U\3\2\2\2\4e\3\2\2\2\6q\3\2\2\2\by\3\2"+
		"\2\2\n\u00b4\3\2\2\2\f\u00c3\3\2\2\2\16\u00d8\3\2\2\2\20\u00da\3\2\2\2"+
		"\22\u00df\3\2\2\2\24\u00eb\3\2\2\2\26\u00f6\3\2\2\2\30\u00f9\3\2\2\2\32"+
		"\u00fd\3\2\2\2\34\u0109\3\2\2\2\36\u010b\3\2\2\2 \u0113\3\2\2\2\"\u0115"+
		"\3\2\2\2$\u011e\3\2\2\2&\u0126\3\2\2\2(\u0131\3\2\2\2*\u0137\3\2\2\2,"+
		"\u013e\3\2\2\2.\u0140\3\2\2\2\60\u0153\3\2\2\2\62\u0172\3\2\2\2\64\u0174"+
		"\3\2\2\2\66\u0176\3\2\2\28\u0178\3\2\2\2:\u017a\3\2\2\2<\u017c\3\2\2\2"+
		">\u017e\3\2\2\2@\u0180\3\2\2\2B\u018a\3\2\2\2D\u018e\3\2\2\2F\u0190\3"+
		"\2\2\2H\u0192\3\2\2\2J\u0194\3\2\2\2L\u01a1\3\2\2\2N\u01a3\3\2\2\2P\u01a5"+
		"\3\2\2\2RT\5\20\t\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2"+
		"WU\3\2\2\2X\\\7\3\2\2Y[\5\4\3\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2"+
		"\2\2]_\3\2\2\2^\\\3\2\2\2_`\5\n\6\2`a\7\4\2\2ab\7\2\2\3b\3\3\2\2\2cf\5"+
		" \21\2df\7(\2\2ec\3\2\2\2ed\3\2\2\2fg\3\2\2\2gh\5N(\2hj\7;\2\2ik\5\6\4"+
		"\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7<\2\2mn\7\5\2\2no\5\n\6\2op\7\4\2"+
		"\2p\5\3\2\2\2qv\5\b\5\2rs\7\33\2\2su\5\b\5\2tr\3\2\2\2ux\3\2\2\2vt\3\2"+
		"\2\2vw\3\2\2\2w\7\3\2\2\2xv\3\2\2\2yz\5 \21\2z{\5N(\2{\t\3\2\2\2|}\b\6"+
		"\1\2}\u00b5\7\6\2\2~\177\7\35\2\2\177\u0080\5N(\2\u0080\u0082\7;\2\2\u0081"+
		"\u0083\5\32\16\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3"+
		"\2\2\2\u0084\u0085\7<\2\2\u0085\u00b5\3\2\2\2\u0086\u0089\5 \21\2\u0087"+
		"\u0089\5,\27\2\u0088\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\u008b\5N(\2\u008b\u008c\7\31\2\2\u008c\u008d\5\16\b\2\u008d"+
		"\u00b5\3\2\2\2\u008e\u008f\5\f\7\2\u008f\u0090\7\31\2\2\u0090\u0091\5"+
		"\16\b\2\u0091\u00b5\3\2\2\2\u0092\u00b5\5\22\n\2\u0093\u0094\7\7\2\2\u0094"+
		"\u00b5\5\f\7\2\u0095\u0096\t\2\2\2\u0096\u00b5\5\60\31\2\u0097\u00b5\7"+
		"\t\2\2\u0098\u0099\7\r\2\2\u0099\u009a\5\60\31\2\u009a\u009b\7\16\2\2"+
		"\u009b\u009c\5\n\6\2\u009c\u009d\7\17\2\2\u009d\u009e\5\n\6\2\u009e\u009f"+
		"\7\20\2\2\u009f\u00b5\3\2\2\2\u00a0\u00a1\7\21\2\2\u00a1\u00a2\5\60\31"+
		"\2\u00a2\u00a3\7\23\2\2\u00a3\u00a4\5\n\6\2\u00a4\u00a5\7\24\2\2\u00a5"+
		"\u00b5\3\2\2\2\u00a6\u00a7\7\22\2\2\u00a7\u00a8\5\n\6\2\u00a8\u00a9\7"+
		"\32\2\2\u00a9\u00aa\5\60\31\2\u00aa\u00ab\7\32\2\2\u00ab\u00ac\5\n\6\2"+
		"\u00ac\u00ad\7\23\2\2\u00ad\u00ae\5\n\6\2\u00ae\u00af\7\24\2\2\u00af\u00b5"+
		"\3\2\2\2\u00b0\u00b1\7\3\2\2\u00b1\u00b2\5\n\6\2\u00b2\u00b3\7\4\2\2\u00b3"+
		"\u00b5\3\2\2\2\u00b4|\3\2\2\2\u00b4~\3\2\2\2\u00b4\u0088\3\2\2\2\u00b4"+
		"\u008e\3\2\2\2\u00b4\u0092\3\2\2\2\u00b4\u0093\3\2\2\2\u00b4\u0095\3\2"+
		"\2\2\u00b4\u0097\3\2\2\2\u00b4\u0098\3\2\2\2\u00b4\u00a0\3\2\2\2\u00b4"+
		"\u00a6\3\2\2\2\u00b4\u00b0\3\2\2\2\u00b5\u00bb\3\2\2\2\u00b6\u00b7\f\3"+
		"\2\2\u00b7\u00b8\7\32\2\2\u00b8\u00ba\5\n\6\4\u00b9\u00b6\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\13\3\2\2"+
		"\2\u00bd\u00bb\3\2\2\2\u00be\u00c4\5N(\2\u00bf\u00c4\5@!\2\u00c0\u00c4"+
		"\5\34\17\2\u00c1\u00c4\5\36\20\2\u00c2\u00c4\5\30\r\2\u00c3\u00be\3\2"+
		"\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c0\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3"+
		"\u00c2\3\2\2\2\u00c4\r\3\2\2\2\u00c5\u00d9\5\60\31\2\u00c6\u00d9\5J&\2"+
		"\u00c7\u00c8\7\34\2\2\u00c8\u00c9\7;\2\2\u00c9\u00ca\5\60\31\2\u00ca\u00cb"+
		"\7\33\2\2\u00cb\u00cc\5\60\31\2\u00cc\u00cd\7<\2\2\u00cd\u00d9\3\2\2\2"+
		"\u00ce\u00d9\5\34\17\2\u00cf\u00d0\7\35\2\2\u00d0\u00d1\5N(\2\u00d1\u00d3"+
		"\7;\2\2\u00d2\u00d4\5\32\16\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2"+
		"\u00d4\u00d5\3\2\2\2\u00d5\u00d6\7<\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d9"+
		"\5\24\13\2\u00d8\u00c5\3\2\2\2\u00d8\u00c6\3\2\2\2\u00d8\u00c7\3\2\2\2"+
		"\u00d8\u00ce\3\2\2\2\u00d8\u00cf\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9\17"+
		"\3\2\2\2\u00da\u00db\7\30\2\2\u00db\u00dc\5N(\2\u00dc\u00dd\7\27\2\2\u00dd"+
		"\u00de\5N(\2\u00de\21\3\2\2\2\u00df\u00e0\5.\30\2\u00e0\u00e6\7?\2\2\u00e1"+
		"\u00e2\5\26\f\2\u00e2\u00e3\7\32\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e1\3"+
		"\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7@\2\2\u00ea\23\3\2\2\2"+
		"\u00eb\u00ec\7?\2\2\u00ec\u00f1\5\16\b\2\u00ed\u00ee\7\33\2\2\u00ee\u00f0"+
		"\5\16\b\2\u00ef\u00ed\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2"+
		"\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5"+
		"\7@\2\2\u00f5\25\3\2\2\2\u00f6\u00f7\5 \21\2\u00f7\u00f8\5N(\2\u00f8\27"+
		"\3\2\2\2\u00f9\u00fa\5N(\2\u00fa\u00fb\7\27\2\2\u00fb\u00fc\5N(\2\u00fc"+
		"\31\3\2\2\2\u00fd\u0102\5\60\31\2\u00fe\u00ff\7\33\2\2\u00ff\u0101\5\60"+
		"\31\2\u0100\u00fe\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103\33\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0106\7\36\2"+
		"\2\u0106\u010a\5\60\31\2\u0107\u0108\7\37\2\2\u0108\u010a\5\60\31\2\u0109"+
		"\u0105\3\2\2\2\u0109\u0107\3\2\2\2\u010a\35\3\2\2\2\u010b\u010c\7\60\2"+
		"\2\u010c\u010d\5N(\2\u010d\37\3\2\2\2\u010e\u0114\5\"\22\2\u010f\u0114"+
		"\5&\24\2\u0110\u0114\5$\23\2\u0111\u0114\5.\30\2\u0112\u0114\5*\26\2\u0113"+
		"\u010e\3\2\2\2\u0113\u010f\3\2\2\2\u0113\u0110\3\2\2\2\u0113\u0111\3\2"+
		"\2\2\u0113\u0112\3\2\2\2\u0114!\3\2\2\2\u0115\u0116\t\3\2\2\u0116#\3\2"+
		"\2\2\u0117\u011f\5\"\22\2\u0118\u011f\5&\24\2\u0119\u011f\5.\30\2\u011a"+
		"\u011b\7;\2\2\u011b\u011c\5*\26\2\u011c\u011d\7<\2\2\u011d\u011f\3\2\2"+
		"\2\u011e\u0117\3\2\2\2\u011e\u0118\3\2\2\2\u011e\u0119\3\2\2\2\u011e\u011a"+
		"\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u0121\7=\2\2\u0121\u0123\7>\2\2\u0122"+
		"\u0120\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125%\3\2\2\2\u0126\u0127\7 \2\2\u0127\u0128\7;\2\2\u0128\u0129"+
		"\5(\25\2\u0129\u012a\7\33\2\2\u012a\u012b\5(\25\2\u012b\u012c\7<\2\2\u012c"+
		"\'\3\2\2\2\u012d\u0132\5\"\22\2\u012e\u0132\5$\23\2\u012f\u0132\5&\24"+
		"\2\u0130\u0132\7 \2\2\u0131\u012d\3\2\2\2\u0131\u012e\3\2\2\2\u0131\u012f"+
		"\3\2\2\2\u0131\u0130\3\2\2\2\u0132)\3\2\2\2\u0133\u0138\5\"\22\2\u0134"+
		"\u0138\5&\24\2\u0135\u0138\5$\23\2\u0136\u0138\5.\30\2\u0137\u0133\3\2"+
		"\2\2\u0137\u0134\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0136\3\2\2\2\u0138"+
		"\u013a\3\2\2\2\u0139\u013b\7\60\2\2\u013a\u0139\3\2\2\2\u013b\u013c\3"+
		"\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d+\3\2\2\2\u013e\u013f"+
		"\7\'\2\2\u013f-\3\2\2\2\u0140\u0141\7\26\2\2\u0141\u0142\5P)\2\u0142/"+
		"\3\2\2\2\u0143\u0144\b\31\1\2\u0144\u0154\5B\"\2\u0145\u0154\5D#\2\u0146"+
		"\u0154\5H%\2\u0147\u0154\5F$\2\u0148\u0154\5L\'\2\u0149\u0154\5N(\2\u014a"+
		"\u0154\5@!\2\u014b\u014c\5\62\32\2\u014c\u014d\5\60\31\5\u014d\u0154\3"+
		"\2\2\2\u014e\u0154\5\30\r\2\u014f\u0150\7;\2\2\u0150\u0151\5\60\31\2\u0151"+
		"\u0152\7<\2\2\u0152\u0154\3\2\2\2\u0153\u0143\3\2\2\2\u0153\u0145\3\2"+
		"\2\2\u0153\u0146\3\2\2\2\u0153\u0147\3\2\2\2\u0153\u0148\3\2\2\2\u0153"+
		"\u0149\3\2\2\2\u0153\u014a\3\2\2\2\u0153\u014b\3\2\2\2\u0153\u014e\3\2"+
		"\2\2\u0153\u014f\3\2\2\2\u0154\u016f\3\2\2\2\u0155\u0156\f\22\2\2\u0156"+
		"\u0157\5\64\33\2\u0157\u0158\5\60\31\23\u0158\u016e\3\2\2\2\u0159\u015a"+
		"\f\21\2\2\u015a\u015b\5\66\34\2\u015b\u015c\5\60\31\22\u015c\u016e\3\2"+
		"\2\2\u015d\u015e\f\20\2\2\u015e\u015f\58\35\2\u015f\u0160\5\60\31\21\u0160"+
		"\u016e\3\2\2\2\u0161\u0162\f\17\2\2\u0162\u0163\5:\36\2\u0163\u0164\5"+
		"\60\31\20\u0164\u016e\3\2\2\2\u0165\u0166\f\16\2\2\u0166\u0167\5<\37\2"+
		"\u0167\u0168\5\60\31\17\u0168\u016e\3\2\2\2\u0169\u016a\f\r\2\2\u016a"+
		"\u016b\5> \2\u016b\u016c\5\60\31\16\u016c\u016e\3\2\2\2\u016d\u0155\3"+
		"\2\2\2\u016d\u0159\3\2\2\2\u016d\u015d\3\2\2\2\u016d\u0161\3\2\2\2\u016d"+
		"\u0165\3\2\2\2\u016d\u0169\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2"+
		"\2\2\u016f\u0170\3\2\2\2\u0170\61\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173"+
		"\t\4\2\2\u0173\63\3\2\2\2\u0174\u0175\t\5\2\2\u0175\65\3\2\2\2\u0176\u0177"+
		"\t\6\2\2\u0177\67\3\2\2\2\u0178\u0179\t\7\2\2\u01799\3\2\2\2\u017a\u017b"+
		"\t\b\2\2\u017b;\3\2\2\2\u017c\u017d\79\2\2\u017d=\3\2\2\2\u017e\u017f"+
		"\7:\2\2\u017f?\3\2\2\2\u0180\u0185\5N(\2\u0181\u0182\7=\2\2\u0182\u0183"+
		"\5\60\31\2\u0183\u0184\7>\2\2\u0184\u0186\3\2\2\2\u0185\u0181\3\2\2\2"+
		"\u0186\u0187\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188A\3"+
		"\2\2\2\u0189\u018b\t\6\2\2\u018a\u0189\3\2\2\2\u018a\u018b\3\2\2\2\u018b"+
		"\u018c\3\2\2\2\u018c\u018d\7A\2\2\u018dC\3\2\2\2\u018e\u018f\t\t\2\2\u018f"+
		"E\3\2\2\2\u0190\u0191\7G\2\2\u0191G\3\2\2\2\u0192\u0193\7H\2\2\u0193I"+
		"\3\2\2\2\u0194\u019d\7=\2\2\u0195\u019a\5\60\31\2\u0196\u0197\7\33\2\2"+
		"\u0197\u0199\5\60\31\2\u0198\u0196\3\2\2\2\u0199\u019c\3\2\2\2\u019a\u0198"+
		"\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019e\3\2\2\2\u019c\u019a\3\2\2\2\u019d"+
		"\u0195\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\7>"+
		"\2\2\u01a0K\3\2\2\2\u01a1\u01a2\7\25\2\2\u01a2M\3\2\2\2\u01a3\u01a4\t"+
		"\n\2\2\u01a4O\3\2\2\2\u01a5\u01a6\7B\2\2\u01a6Q\3\2\2\2\37U\\ejv\u0082"+
		"\u0088\u00b4\u00bb\u00c3\u00d3\u00d8\u00e6\u00f1\u0102\u0109\u0113\u011e"+
		"\u0124\u0131\u0137\u013c\u0153\u016d\u016f\u0187\u018a\u019a\u019d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}