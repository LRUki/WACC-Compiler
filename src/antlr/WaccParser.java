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
      DONE = 17, NULL = 18, STRUCT = 19, DOT = 20, ASSIGN = 21, SEMICOLON = 22, COMMA = 23,
      NEWPAIR = 24, CALL = 25, FST = 26, SND = 27, PAIR = 28, TRUE = 29, FALSE = 30, INT = 31,
      BOOL = 32, CHAR = 33, STRING = 34, VAR = 35, NOT = 36, LEN = 37, ORD = 38, CHR = 39, REF = 40,
      PLUS = 41, MINUS = 42, MULT = 43, DIV = 44, MOD = 45, GTE = 46, GT = 47, LTE = 48, LT = 49,
      EQ = 50, NEQ = 51, AND = 52, OR = 53, L_PAREN = 54, R_PAREN = 55, L_SQUARE = 56, R_SQUARE = 57,
      L_CURLY = 58, R_CURLY = 59, NUMBER = 60, CAPTIALISED_IDENT = 61, IDENT = 62, WHITESPACE = 63,
      ESCAPE_CHARACTER = 64, CHARACTER = 65, STR_LITER = 66, CHAR_LITER = 67, COMMENT = 68;
  public static final int
      RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4,
      RULE_assignLhs = 5, RULE_assignRhs = 6, RULE_structDeclare = 7, RULE_structAssign = 8,
      RULE_structMember = 9, RULE_structAccess = 10, RULE_argList = 11, RULE_pairElem = 12,
      RULE_pointerElem = 13, RULE_type = 14, RULE_baseType = 15, RULE_arrayType = 16,
      RULE_pairType = 17, RULE_pairElemType = 18, RULE_pointerType = 19, RULE_implicitType = 20,
      RULE_structType = 21, RULE_expr = 22, RULE_unop = 23, RULE_binop1 = 24,
      RULE_binop2 = 25, RULE_binop3 = 26, RULE_binop4 = 27, RULE_binop5 = 28,
      RULE_binop6 = 29, RULE_arrayElem = 30, RULE_intLiter = 31, RULE_boolLiter = 32,
      RULE_strLiter = 33, RULE_charLiter = 34, RULE_arrayLiter = 35, RULE_pairLiter = 36,
      RULE_ident = 37, RULE_capitalisedIdent = 38;

  private static String[] makeRuleNames() {
    return new String[]{
        "program", "func", "paramList", "param", "stat", "assignLhs", "assignRhs",
        "structDeclare", "structAssign", "structMember", "structAccess", "argList",
        "pairElem", "pointerElem", "type", "baseType", "arrayType", "pairType",
        "pairElemType", "pointerType", "implicitType", "structType", "expr",
        "unop", "binop1", "binop2", "binop3", "binop4", "binop5", "binop6", "arrayElem",
        "intLiter", "boolLiter", "strLiter", "charLiter", "arrayLiter", "pairLiter",
        "ident", "capitalisedIdent"
    };
  }

  public static final String[] ruleNames = makeRuleNames();

  private static String[] makeLiteralNames() {
    return new String[]{
        null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'",
        "'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'",
        "'while'", "'do'", "'done'", "'null'", "'struct'", "'.'", "'='", "';'",
        "','", "'newpair'", "'call'", "'fst'", "'snd'", "'pair'", "'true'", "'false'",
        "'int'", "'bool'", "'char'", "'string'", "'var'", "'!'", "'len'", "'ord'",
        "'chr'", "'&'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>='", "'>'", "'<='",
        "'<'", "'=='", "'!='", "'&&'", "'||'", "'('", "')'", "'['", "']'", "'{'",
        "'}'"
    };
  }

  private static final String[] _LITERAL_NAMES = makeLiteralNames();

  private static String[] makeSymbolicNames() {
    return new String[]{
        null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT",
        "PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE",
        "NULL", "STRUCT", "DOT", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL",
        "FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING",
        "VAR", "NOT", "LEN", "ORD", "CHR", "REF", "PLUS", "MINUS", "MULT", "DIV",
        "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR", "L_PAREN",
        "R_PAREN", "L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER", "CAPTIALISED_IDENT",
        "IDENT", "WHITESPACE", "ESCAPE_CHARACTER", "CHARACTER", "STR_LITER",
        "CHAR_LITER", "COMMENT"
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
        setState(78);
        match(BEGIN);
        setState(82);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            {
              {
                setState(79);
                func();
              }
            }
          }
          setState(84);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
        }
        setState(85);
        stat(0);
        setState(86);
        match(END);
        setState(87);
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
        setState(89);
        type();
        setState(90);
        ident();
        setState(91);
        match(L_PAREN);
        setState(93);
        _errHandler.sync(this);
        _la = _input.LA(1);
        if ((((_la) & ~0x3f) == 0 &&
            ((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L
                << CHAR) | (1L << STRING))) != 0)) {
          {
            setState(92);
            paramList();
          }
        }

        setState(95);
        match(R_PAREN);
        setState(96);
        match(IS);
        setState(97);
        stat(0);
        setState(98);
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
        setState(100);
        param();
        setState(105);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == COMMA) {
          {
            {
              setState(101);
              match(COMMA);
              setState(102);
              param();
            }
          }
          setState(107);
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
        setState(108);
        type();
        setState(109);
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
	public static class SkipStatContext extends StatContext {
		public TerminalNode SKIP_TOKEN() { return getToken(WaccParser.SKIP_TOKEN, 0); }
		public SkipStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitSkipStat(this);
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
        setState(148);
        _errHandler.sync(this);
        switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
          case 1: {
            _localctx = new SkipStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(112);
            match(SKIP_TOKEN);
          }
          break;
          case 2: {
            _localctx = new DeclareStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(115);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
              case STRUCT:
              case PAIR:
              case INT:
              case BOOL:
              case CHAR:
              case STRING: {
                setState(113);
                type();
              }
              break;
              case VAR: {
                setState(114);
                implicitType();
              }
              break;
              default:
                throw new NoViableAltException(this);
            }
            setState(117);
            ident();
            setState(118);
            match(ASSIGN);
            setState(119);
            assignRhs();
          }
          break;
          case 3: {
            _localctx = new AssignStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(121);
            assignLhs();
            setState(122);
            match(ASSIGN);
            setState(123);
            assignRhs();
          }
          break;
          case 4: {
            _localctx = new StructDeclareStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(125);
            structDeclare();
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
          _alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
        }
      }
		}
		catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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

    public StructAccessContext structAccess() {
      return getRuleContext(StructAccessContext.class, 0);
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
      setState(163);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
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
        case 4:
          enterOuterAlt(_localctx, 4);
        {
          setState(161);
          pointerElem();
        }
        break;
        case 5:
          enterOuterAlt(_localctx, 5);
        {
          setState(162);
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
      setState(184);
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
          setState(165);
          expr(0);
        }
        break;
        case L_SQUARE:
          enterOuterAlt(_localctx, 2);
        {
          setState(166);
          arrayLiter();
        }
        break;
        case NEWPAIR:
          enterOuterAlt(_localctx, 3);
        {
          setState(167);
          match(NEWPAIR);
          setState(168);
          match(L_PAREN);
          setState(169);
          expr(0);
          setState(170);
          match(COMMA);
          setState(171);
          expr(0);
          setState(172);
          match(R_PAREN);
        }
        break;
        case FST:
        case SND:
          enterOuterAlt(_localctx, 4);
        {
          setState(174);
          pairElem();
        }
        break;
        case CALL:
          enterOuterAlt(_localctx, 5);
        {
          setState(175);
          match(CALL);
          setState(176);
          ident();
          setState(177);
          match(L_PAREN);
          setState(179);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (((((_la - 18)) & ~0x3f) == 0 &&
              ((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (TRUE - 18)) | (1L << (FALSE
                  - 18)) | (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L << (
                  CHR - 18)) | (1L << (REF - 18)) | (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L
                  << (MULT - 18)) | (1L << (L_PAREN - 18)) | (1L << (NUMBER - 18)) | (1L << (
                  CAPTIALISED_IDENT - 18)) | (1L << (IDENT - 18)) | (1L << (STR_LITER - 18)) | (1L
                  << (CHAR_LITER - 18)))) != 0)) {
            {
              setState(178);
              argList();
            }
          }

          setState(181);
          match(R_PAREN);
        }
        break;
        case L_CURLY:
          enterOuterAlt(_localctx, 6);
        {
          setState(183);
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
		enterRule(_localctx, 14, RULE_structDeclare);
		int _la;
		try {
      enterOuterAlt(_localctx, 1);
      {
        setState(186);
        structType();
        setState(187);
        match(L_CURLY);
        setState(193);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while ((((_la) & ~0x3f) == 0 &&
            ((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L
                << CHAR) | (1L << STRING))) != 0)) {
          {
            {
              setState(188);
              structMember();
              setState(189);
              match(SEMICOLON);
            }
          }
          setState(195);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        setState(196);
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
		enterRule(_localctx, 16, RULE_structAssign);
		int _la;
		try {
      enterOuterAlt(_localctx, 1);
      {
        setState(198);
        match(L_CURLY);
        {
          setState(199);
          assignRhs();
          setState(204);
          _errHandler.sync(this);
          _la = _input.LA(1);
          while (_la == COMMA) {
            {
              {
                setState(200);
                match(COMMA);
                setState(201);
                assignRhs();
              }
            }
            setState(206);
            _errHandler.sync(this);
            _la = _input.LA(1);
          }
        }
        setState(207);
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

  public static class StructMemberContext extends ParserRuleContext {

    public TypeContext type() {
      return getRuleContext(TypeContext.class, 0);
    }

    public IdentContext ident() {
      return getRuleContext(IdentContext.class, 0);
    }

    public StructMemberContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_structMember;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitStructMember(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final StructMemberContext structMember() throws RecognitionException {
    StructMemberContext _localctx = new StructMemberContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_structMember);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(209);
        type();
        setState(210);
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

  public static class StructAccessContext extends ParserRuleContext {

    public List<IdentContext> ident() {
      return getRuleContexts(IdentContext.class);
    }

    public IdentContext ident(int i) {
      return getRuleContext(IdentContext.class, i);
    }

    public TerminalNode DOT() {
      return getToken(WaccParser.DOT, 0);
    }

    public StructAccessContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_structAccess;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitStructAccess(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final StructAccessContext structAccess() throws RecognitionException {
    StructAccessContext _localctx = new StructAccessContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_structAccess);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(212);
        ident();
        setState(213);
        match(DOT);
        setState(214);
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
    enterRule(_localctx, 22, RULE_argList);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(216);
        expr(0);
        setState(221);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == COMMA) {
          {
            {
              setState(217);
              match(COMMA);
              setState(218);
              expr(0);
            }
          }
          setState(223);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
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

  public static class PairElemContext extends ParserRuleContext {

    public TerminalNode FST() {
      return getToken(WaccParser.FST, 0);
    }

    public ExprContext expr() {
      return getRuleContext(ExprContext.class, 0);
    }

    public TerminalNode SND() {
      return getToken(WaccParser.SND, 0);
    }

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
    enterRule(_localctx, 24, RULE_pairElem);
    try {
      setState(228);
      _errHandler.sync(this);
      switch (_input.LA(1)) {
        case FST:
          enterOuterAlt(_localctx, 1);
        {
          setState(224);
          match(FST);
          setState(225);
          expr(0);
        }
        break;
        case SND:
          enterOuterAlt(_localctx, 2);
        {
          setState(226);
          match(SND);
          setState(227);
          expr(0);
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
    enterRule(_localctx, 26, RULE_pointerElem);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(230);
        match(MULT);
        setState(231);
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
    enterRule(_localctx, 28, RULE_type);
    try {
      setState(238);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
        case 1:
          enterOuterAlt(_localctx, 1);
        {
          setState(233);
          baseType();
        }
        break;
        case 2:
          enterOuterAlt(_localctx, 2);
        {
          setState(234);
          pairType();
        }
        break;
        case 3:
          enterOuterAlt(_localctx, 3);
        {
          setState(235);
          arrayType();
        }
        break;
        case 4:
          enterOuterAlt(_localctx, 4);
        {
          setState(236);
          structType();
        }
        break;
        case 5:
          enterOuterAlt(_localctx, 5);
        {
          setState(237);
          pointerType();
        }
        break;
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
    enterRule(_localctx, 30, RULE_baseType);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(240);
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
    } catch (RecognitionException re) {
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
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
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
    enterRule(_localctx, 32, RULE_arrayType);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(245);
        _errHandler.sync(this);
        switch (_input.LA(1)) {
          case INT:
          case BOOL:
          case CHAR:
          case STRING: {
            setState(242);
            baseType();
          }
          break;
          case PAIR: {
            setState(243);
            pairType();
          }
          break;
          case STRUCT: {
            setState(244);
            structType();
          }
          break;
          default:
            throw new NoViableAltException(this);
        }
        setState(249);
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
            {
              setState(247);
              match(L_SQUARE);
              setState(248);
              match(R_SQUARE);
            }
          }
          setState(251);
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while (_la == L_SQUARE);
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
    enterRule(_localctx, 34, RULE_pairType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(253);
        match(PAIR);
        setState(254);
        match(L_PAREN);
        setState(255);
        pairElemType();
        setState(256);
        match(COMMA);
        setState(257);
        pairElemType();
        setState(258);
        match(R_PAREN);
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
    enterRule(_localctx, 36, RULE_pairElemType);
    try {
      setState(264);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
        case 1:
          enterOuterAlt(_localctx, 1);
        {
          setState(260);
          baseType();
        }
        break;
        case 2:
          enterOuterAlt(_localctx, 2);
        {
          setState(261);
          arrayType();
        }
        break;
        case 3:
          enterOuterAlt(_localctx, 3);
        {
          setState(262);
          pairType();
        }
        break;
        case 4:
          enterOuterAlt(_localctx, 4);
        {
          setState(263);
          match(PAIR);
        }
        break;
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

	public static class PointerTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode MULT() { return getToken(WaccParser.MULT, 0); }
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
    enterRule(_localctx, 38, RULE_pointerType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(266);
        baseType();
        setState(267);
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
    enterRule(_localctx, 40, RULE_implicitType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(269);
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
    enterRule(_localctx, 42, RULE_structType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(271);
        match(STRUCT);
        setState(272);
        capitalisedIdent();
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
      return getRuleContext(BoolLiterContext.class, 0);
    }

    public CharLiterContext charLiter() {
      return getRuleContext(CharLiterContext.class, 0);
    }

    public StrLiterContext strLiter() {
      return getRuleContext(StrLiterContext.class, 0);
    }

    public PairLiterContext pairLiter() {
      return getRuleContext(PairLiterContext.class, 0);
    }

    public IdentContext ident() {
      return getRuleContext(IdentContext.class, 0);
    }

    public ArrayElemContext arrayElem() {
      return getRuleContext(ArrayElemContext.class, 0);
    }

    public SingletonExprContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitSingletonExpr(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class StructAccessExprContext extends ExprContext {

    public StructAccessContext structAccess() {
      return getRuleContext(StructAccessContext.class, 0);
    }

    public StructAccessExprContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitStructAccessExpr(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class BinopExprContext extends ExprContext {

    public List<ExprContext> expr() {
      return getRuleContexts(ExprContext.class);
    }

    public ExprContext expr(int i) {
      return getRuleContext(ExprContext.class, i);
    }

    public Binop1Context binop1() {
      return getRuleContext(Binop1Context.class, 0);
    }

    public Binop2Context binop2() {
      return getRuleContext(Binop2Context.class, 0);
    }

    public Binop3Context binop3() {
      return getRuleContext(Binop3Context.class, 0);
    }

    public Binop4Context binop4() {
      return getRuleContext(Binop4Context.class, 0);
    }

    public Binop5Context binop5() {
      return getRuleContext(Binop5Context.class, 0);
    }

    public Binop6Context binop6() {
      return getRuleContext(Binop6Context.class, 0);
    }

    public BinopExprContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitBinopExpr(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ParenExprContext extends ExprContext {

    public TerminalNode L_PAREN() {
      return getToken(WaccParser.L_PAREN, 0);
    }

    public ExprContext expr() {
      return getRuleContext(ExprContext.class, 0);
    }

    public TerminalNode R_PAREN() {
      return getToken(WaccParser.R_PAREN, 0);
    }

    public ParenExprContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitParenExpr(this);
      } else {
        return visitor.visitChildren(this);
      }
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
    int _startState = 44;
    enterRecursionRule(_localctx, 44, RULE_expr, _p);
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
        setState(290);
        _errHandler.sync(this);
        switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
          case 1: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(275);
            intLiter();
          }
          break;
          case 2: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(276);
            boolLiter();
          }
          break;
          case 3: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(277);
            charLiter();
          }
          break;
          case 4: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(278);
            strLiter();
          }
          break;
          case 5: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(279);
            pairLiter();
          }
          break;
          case 6: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(280);
            ident();
          }
          break;
          case 7: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(281);
            arrayElem();
          }
          break;
          case 8: {
            _localctx = new UnopExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(282);
            unop();
            setState(283);
            expr(3);
          }
          break;
          case 9: {
            _localctx = new StructAccessExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(285);
            structAccess();
          }
          break;
          case 10: {
            _localctx = new ParenExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(286);
            match(L_PAREN);
            setState(287);
            expr(0);
            setState(288);
            match(R_PAREN);
          }
          break;
        }
        _ctx.stop = _input.LT(-1);
        setState(318);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            if (_parseListeners != null) {
              triggerExitRuleEvent();
            }
            _prevctx = _localctx;
            {
              setState(316);
              _errHandler.sync(this);
              switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
                case 1: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(292);
                  if (!(precpred(_ctx, 16))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                  }
                  setState(293);
                  binop1();
                  setState(294);
                  expr(17);
                }
                break;
                case 2: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(296);
                  if (!(precpred(_ctx, 15))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                  }
                  setState(297);
                  binop2();
                  setState(298);
                  expr(16);
                }
                break;
                case 3: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(300);
                  if (!(precpred(_ctx, 14))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 14)");
                  }
                  setState(301);
                  binop3();
                  setState(302);
                  expr(15);
                }
                break;
                case 4: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(304);
                  if (!(precpred(_ctx, 13))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 13)");
                  }
                  setState(305);
                  binop4();
                  setState(306);
                  expr(14);
                }
                break;
                case 5: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(308);
                  if (!(precpred(_ctx, 12))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 12)");
                  }
                  setState(309);
                  binop5();
                  setState(310);
                  expr(13);
                }
                break;
                case 6: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(312);
                  if (!(precpred(_ctx, 11))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                  }
                  setState(313);
                  binop6();
                  setState(314);
                  expr(12);
                }
                break;
              }
            }
          }
          setState(320);
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
    enterRule(_localctx, 46, RULE_unop);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(321);
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
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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
    enterRule(_localctx, 48, RULE_binop1);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(323);
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
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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
    enterRule(_localctx, 50, RULE_binop2);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(325);
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
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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
    enterRule(_localctx, 52, RULE_binop3);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(327);
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
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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
    enterRule(_localctx, 54, RULE_binop4);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(329);
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
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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
    enterRule(_localctx, 56, RULE_binop5);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(331);
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
    enterRule(_localctx, 58, RULE_binop6);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(333);
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
    enterRule(_localctx, 60, RULE_arrayElem);
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
        setState(335);
        ident();
        setState(340);
        _errHandler.sync(this);
        _alt = 1;
        do {
          switch (_alt) {
            case 1: {
              {
                setState(336);
                match(L_SQUARE);
                setState(337);
                expr(0);
                setState(338);
                match(R_SQUARE);
              }
            }
            break;
            default:
              throw new NoViableAltException(this);
          }
          setState(342);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
        } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
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
    enterRule(_localctx, 62, RULE_intLiter);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(345);
        _errHandler.sync(this);
        _la = _input.LA(1);
        if (_la == PLUS || _la == MINUS) {
          {
            setState(344);
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

        setState(347);
        match(NUMBER);
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
    enterRule(_localctx, 64, RULE_boolLiter);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(349);
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
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
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
    enterRule(_localctx, 66, RULE_strLiter);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(351);
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
    enterRule(_localctx, 68, RULE_charLiter);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(353);
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
    enterRule(_localctx, 70, RULE_arrayLiter);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(355);
        match(L_SQUARE);
        setState(364);
        _errHandler.sync(this);
        _la = _input.LA(1);
        if (((((_la - 18)) & ~0x3f) == 0 &&
            ((1L << (_la - 18)) & ((1L << (NULL - 18)) | (1L << (TRUE - 18)) | (1L << (FALSE - 18))
                | (1L << (NOT - 18)) | (1L << (LEN - 18)) | (1L << (ORD - 18)) | (1L << (CHR - 18))
                | (1L << (REF - 18)) | (1L << (PLUS - 18)) | (1L << (MINUS - 18)) | (1L << (MULT
                - 18)) | (1L << (L_PAREN - 18)) | (1L << (NUMBER - 18)) | (1L << (CAPTIALISED_IDENT
                - 18)) | (1L << (IDENT - 18)) | (1L << (STR_LITER - 18)) | (1L << (CHAR_LITER
                - 18)))) != 0)) {
          {
            setState(356);
            expr(0);
            setState(361);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == COMMA) {
              {
                {
                  setState(357);
                  match(COMMA);
                  setState(358);
                  expr(0);
                }
              }
              setState(363);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

        setState(366);
        match(R_SQUARE);
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
    enterRule(_localctx, 72, RULE_pairLiter);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(368);
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
    enterRule(_localctx, 74, RULE_ident);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(370);
        _la = _input.LA(1);
        if (!(_la == CAPTIALISED_IDENT || _la == IDENT)) {
          _errHandler.recoverInline(this);
        } else {
          if (_input.LA(1) == Token.EOF) {
            matchedEOF = true;
          }
          _errHandler.reportMatch(this);
          consume();
        }
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
    enterRule(_localctx, 76, RULE_capitalisedIdent);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(372);
        match(CAPTIALISED_IDENT);
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
      case 22:
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
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3F\u0179\4\2\t\2\4" +
          "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
          "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
          "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
          "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
          "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\7\2S\n\2\f\2" +
          "\16\2V\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3`\n\3\3\3\3\3\3\3\3\3\3" +
          "\3\3\4\3\4\3\4\7\4j\n\4\f\4\16\4m\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6" +
          "v\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
          "\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0097" +
          "\n\6\3\6\3\6\3\6\7\6\u009c\n\6\f\6\16\6\u009f\13\6\3\7\3\7\3\7\3\7\3\7" +
          "\5\7\u00a6\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b" +
          "\5\b\u00b6\n\b\3\b\3\b\3\b\5\b\u00bb\n\b\3\t\3\t\3\t\3\t\3\t\7\t\u00c2" +
          "\n\t\f\t\16\t\u00c5\13\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00cd\n\n\f\n\16" +
          "\n\u00d0\13\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\7\r\u00de" +
          "\n\r\f\r\16\r\u00e1\13\r\3\16\3\16\3\16\3\16\5\16\u00e7\n\16\3\17\3\17" +
          "\3\17\3\20\3\20\3\20\3\20\3\20\5\20\u00f1\n\20\3\21\3\21\3\22\3\22\3\22" +
          "\5\22\u00f8\n\22\3\22\3\22\6\22\u00fc\n\22\r\22\16\22\u00fd\3\23\3\23" +
          "\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\5\24\u010b\n\24\3\25\3\25" +
          "\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30" +
          "\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0125\n\30\3\30\3\30\3\30" +
          "\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30" +
          "\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u013f\n\30\f\30\16\30\u0142\13" +
          "\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3" +
          "\37\3 \3 \3 \3 \3 \6 \u0157\n \r \16 \u0158\3!\5!\u015c\n!\3!\3!\3\"\3" +
          "\"\3#\3#\3$\3$\3%\3%\3%\3%\7%\u016a\n%\f%\16%\u016d\13%\5%\u016f\n%\3" +
          "%\3%\3&\3&\3\'\3\'\3(\3(\3(\2\4\n.)\2\4\6\b\n\f\16\20\22\24\26\30\32\34" +
          "\36 \"$&(*,.\60\62\64\668:<>@BDFHJLN\2\13\3\2\b\f\3\2!$\4\2&*,-\3\2-/" +
          "\3\2+,\3\2\60\63\3\2\64\65\3\2\37 \3\2?@\2\u0189\2P\3\2\2\2\4[\3\2\2\2" +
          "\6f\3\2\2\2\bn\3\2\2\2\n\u0096\3\2\2\2\f\u00a5\3\2\2\2\16\u00ba\3\2\2" +
          "\2\20\u00bc\3\2\2\2\22\u00c8\3\2\2\2\24\u00d3\3\2\2\2\26\u00d6\3\2\2\2" +
          "\30\u00da\3\2\2\2\32\u00e6\3\2\2\2\34\u00e8\3\2\2\2\36\u00f0\3\2\2\2 " +
          "\u00f2\3\2\2\2\"\u00f7\3\2\2\2$\u00ff\3\2\2\2&\u010a\3\2\2\2(\u010c\3" +
          "\2\2\2*\u010f\3\2\2\2,\u0111\3\2\2\2.\u0124\3\2\2\2\60\u0143\3\2\2\2\62" +
          "\u0145\3\2\2\2\64\u0147\3\2\2\2\66\u0149\3\2\2\28\u014b\3\2\2\2:\u014d" +
          "\3\2\2\2<\u014f\3\2\2\2>\u0151\3\2\2\2@\u015b\3\2\2\2B\u015f\3\2\2\2D" +
          "\u0161\3\2\2\2F\u0163\3\2\2\2H\u0165\3\2\2\2J\u0172\3\2\2\2L\u0174\3\2" +
          "\2\2N\u0176\3\2\2\2PT\7\3\2\2QS\5\4\3\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2" +
          "TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2WX\5\n\6\2XY\7\4\2\2YZ\7\2\2\3Z\3\3\2\2" +
          "\2[\\\5\36\20\2\\]\5L\'\2]_\78\2\2^`\5\6\4\2_^\3\2\2\2_`\3\2\2\2`a\3\2" +
          "\2\2ab\79\2\2bc\7\5\2\2cd\5\n\6\2de\7\4\2\2e\5\3\2\2\2fk\5\b\5\2gh\7\31" +
          "\2\2hj\5\b\5\2ig\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\7\3\2\2\2mk\3" +
          "\2\2\2no\5\36\20\2op\5L\'\2p\t\3\2\2\2qr\b\6\1\2r\u0097\7\6\2\2sv\5\36" +
          "\20\2tv\5*\26\2us\3\2\2\2ut\3\2\2\2vw\3\2\2\2wx\5L\'\2xy\7\27\2\2yz\5" +
          "\16\b\2z\u0097\3\2\2\2{|\5\f\7\2|}\7\27\2\2}~\5\16\b\2~\u0097\3\2\2\2" +
          "\177\u0097\5\20\t\2\u0080\u0081\7\7\2\2\u0081\u0097\5\f\7\2\u0082\u0083" +
          "\t\2\2\2\u0083\u0097\5.\30\2\u0084\u0085\7\r\2\2\u0085\u0086\5.\30\2\u0086" +
          "\u0087\7\16\2\2\u0087\u0088\5\n\6\2\u0088\u0089\7\17\2\2\u0089\u008a\5" +
          "\n\6\2\u008a\u008b\7\20\2\2\u008b\u0097\3\2\2\2\u008c\u008d\7\21\2\2\u008d" +
          "\u008e\5.\30\2\u008e\u008f\7\22\2\2\u008f\u0090\5\n\6\2\u0090\u0091\7" +
          "\23\2\2\u0091\u0097\3\2\2\2\u0092\u0093\7\3\2\2\u0093\u0094\5\n\6\2\u0094" +
          "\u0095\7\4\2\2\u0095\u0097\3\2\2\2\u0096q\3\2\2\2\u0096u\3\2\2\2\u0096" +
          "{\3\2\2\2\u0096\177\3\2\2\2\u0096\u0080\3\2\2\2\u0096\u0082\3\2\2\2\u0096" +
          "\u0084\3\2\2\2\u0096\u008c\3\2\2\2\u0096\u0092\3\2\2\2\u0097\u009d\3\2" +
          "\2\2\u0098\u0099\f\3\2\2\u0099\u009a\7\30\2\2\u009a\u009c\5\n\6\4\u009b" +
          "\u0098\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2" +
          "\2\2\u009e\13\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a6\5L\'\2\u00a1\u00a6" +
          "\5> \2\u00a2\u00a6\5\32\16\2\u00a3\u00a6\5\34\17\2\u00a4\u00a6\5\26\f" +
          "\2\u00a5\u00a0\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a3" +
          "\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\r\3\2\2\2\u00a7\u00bb\5.\30\2\u00a8" +
          "\u00bb\5H%\2\u00a9\u00aa\7\32\2\2\u00aa\u00ab\78\2\2\u00ab\u00ac\5.\30" +
          "\2\u00ac\u00ad\7\31\2\2\u00ad\u00ae\5.\30\2\u00ae\u00af\79\2\2\u00af\u00bb" +
          "\3\2\2\2\u00b0\u00bb\5\32\16\2\u00b1\u00b2\7\33\2\2\u00b2\u00b3\5L\'\2" +
          "\u00b3\u00b5\78\2\2\u00b4\u00b6\5\30\r\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6" +
          "\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\79\2\2\u00b8\u00bb\3\2\2\2\u00b9" +
          "\u00bb\5\22\n\2\u00ba\u00a7\3\2\2\2\u00ba\u00a8\3\2\2\2\u00ba\u00a9\3" +
          "\2\2\2\u00ba\u00b0\3\2\2\2\u00ba\u00b1\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb" +
          "\17\3\2\2\2\u00bc\u00bd\5,\27\2\u00bd\u00c3\7<\2\2\u00be\u00bf\5\24\13" +
          "\2\u00bf\u00c0\7\30\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00be\3\2\2\2\u00c2" +
          "\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2" +
          "\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\7=\2\2\u00c7\21\3\2\2\2\u00c8\u00c9" +
          "\7<\2\2\u00c9\u00ce\5\16\b\2\u00ca\u00cb\7\31\2\2\u00cb\u00cd\5\16\b\2" +
          "\u00cc\u00ca\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf" +
          "\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7=\2\2\u00d2" +
          "\23\3\2\2\2\u00d3\u00d4\5\36\20\2\u00d4\u00d5\5L\'\2\u00d5\25\3\2\2\2" +
          "\u00d6\u00d7\5L\'\2\u00d7\u00d8\7\26\2\2\u00d8\u00d9\5L\'\2\u00d9\27\3" +
          "\2\2\2\u00da\u00df\5.\30\2\u00db\u00dc\7\31\2\2\u00dc\u00de\5.\30\2\u00dd" +
          "\u00db\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2" +
          "\2\2\u00e0\31\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7\34\2\2\u00e3\u00e7" +
          "\5.\30\2\u00e4\u00e5\7\35\2\2\u00e5\u00e7\5.\30\2\u00e6\u00e2\3\2\2\2" +
          "\u00e6\u00e4\3\2\2\2\u00e7\33\3\2\2\2\u00e8\u00e9\7-\2\2\u00e9\u00ea\5" +
          "L\'\2\u00ea\35\3\2\2\2\u00eb\u00f1\5 \21\2\u00ec\u00f1\5$\23\2\u00ed\u00f1" +
          "\5\"\22\2\u00ee\u00f1\5,\27\2\u00ef\u00f1\5(\25\2\u00f0\u00eb\3\2\2\2" +
          "\u00f0\u00ec\3\2\2\2\u00f0\u00ed\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00ef" +
          "\3\2\2\2\u00f1\37\3\2\2\2\u00f2\u00f3\t\3\2\2\u00f3!\3\2\2\2\u00f4\u00f8" +
          "\5 \21\2\u00f5\u00f8\5$\23\2\u00f6\u00f8\5,\27\2\u00f7\u00f4\3\2\2\2\u00f7" +
          "\u00f5\3\2\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00fa\7:" +
          "\2\2\u00fa\u00fc\7;\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd" +
          "\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe#\3\2\2\2\u00ff\u0100\7\36\2\2" +
          "\u0100\u0101\78\2\2\u0101\u0102\5&\24\2\u0102\u0103\7\31\2\2\u0103\u0104" +
          "\5&\24\2\u0104\u0105\79\2\2\u0105%\3\2\2\2\u0106\u010b\5 \21\2\u0107\u010b" +
          "\5\"\22\2\u0108\u010b\5$\23\2\u0109\u010b\7\36\2\2\u010a\u0106\3\2\2\2" +
          "\u010a\u0107\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u0109\3\2\2\2\u010b\'\3" +
          "\2\2\2\u010c\u010d\5 \21\2\u010d\u010e\7-\2\2\u010e)\3\2\2\2\u010f\u0110" +
          "\7%\2\2\u0110+\3\2\2\2\u0111\u0112\7\25\2\2\u0112\u0113\5N(\2\u0113-\3" +
          "\2\2\2\u0114\u0115\b\30\1\2\u0115\u0125\5@!\2\u0116\u0125\5B\"\2\u0117" +
          "\u0125\5F$\2\u0118\u0125\5D#\2\u0119\u0125\5J&\2\u011a\u0125\5L\'\2\u011b" +
          "\u0125\5> \2\u011c\u011d\5\60\31\2\u011d\u011e\5.\30\5\u011e\u0125\3\2" +
          "\2\2\u011f\u0125\5\26\f\2\u0120\u0121\78\2\2\u0121\u0122\5.\30\2\u0122" +
          "\u0123\79\2\2\u0123\u0125\3\2\2\2\u0124\u0114\3\2\2\2\u0124\u0116\3\2" +
          "\2\2\u0124\u0117\3\2\2\2\u0124\u0118\3\2\2\2\u0124\u0119\3\2\2\2\u0124" +
          "\u011a\3\2\2\2\u0124\u011b\3\2\2\2\u0124\u011c\3\2\2\2\u0124\u011f\3\2" +
          "\2\2\u0124\u0120\3\2\2\2\u0125\u0140\3\2\2\2\u0126\u0127\f\22\2\2\u0127" +
          "\u0128\5\62\32\2\u0128\u0129\5.\30\23\u0129\u013f\3\2\2\2\u012a\u012b" +
          "\f\21\2\2\u012b\u012c\5\64\33\2\u012c\u012d\5.\30\22\u012d\u013f\3\2\2" +
          "\2\u012e\u012f\f\20\2\2\u012f\u0130\5\66\34\2\u0130\u0131\5.\30\21\u0131" +
          "\u013f\3\2\2\2\u0132\u0133\f\17\2\2\u0133\u0134\58\35\2\u0134\u0135\5" +
          ".\30\20\u0135\u013f\3\2\2\2\u0136\u0137\f\16\2\2\u0137\u0138\5:\36\2\u0138" +
          "\u0139\5.\30\17\u0139\u013f\3\2\2\2\u013a\u013b\f\r\2\2\u013b\u013c\5" +
          "<\37\2\u013c\u013d\5.\30\16\u013d\u013f\3\2\2\2\u013e\u0126\3\2\2\2\u013e" +
          "\u012a\3\2\2\2\u013e\u012e\3\2\2\2\u013e\u0132\3\2\2\2\u013e\u0136\3\2" +
          "\2\2\u013e\u013a\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140" +
          "\u0141\3\2\2\2\u0141/\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144\t\4\2\2" +
          "\u0144\61\3\2\2\2\u0145\u0146\t\5\2\2\u0146\63\3\2\2\2\u0147\u0148\t\6" +
          "\2\2\u0148\65\3\2\2\2\u0149\u014a\t\7\2\2\u014a\67\3\2\2\2\u014b\u014c" +
          "\t\b\2\2\u014c9\3\2\2\2\u014d\u014e\7\66\2\2\u014e;\3\2\2\2\u014f\u0150" +
          "\7\67\2\2\u0150=\3\2\2\2\u0151\u0156\5L\'\2\u0152\u0153\7:\2\2\u0153\u0154" +
          "\5.\30\2\u0154\u0155\7;\2\2\u0155\u0157\3\2\2\2\u0156\u0152\3\2\2\2\u0157" +
          "\u0158\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159?\3\2\2\2" +
          "\u015a\u015c\t\6\2\2\u015b\u015a\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d" +
          "\3\2\2\2\u015d\u015e\7>\2\2\u015eA\3\2\2\2\u015f\u0160\t\t\2\2\u0160C" +
          "\3\2\2\2\u0161\u0162\7D\2\2\u0162E\3\2\2\2\u0163\u0164\7E\2\2\u0164G\3" +
          "\2\2\2\u0165\u016e\7:\2\2\u0166\u016b\5.\30\2\u0167\u0168\7\31\2\2\u0168" +
          "\u016a\5.\30\2\u0169\u0167\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2" +
          "\2\2\u016b\u016c\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016e" +
          "\u0166\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\7;" +
          "\2\2\u0171I\3\2\2\2\u0172\u0173\7\24\2\2\u0173K\3\2\2\2\u0174\u0175\t" +
          "\n\2\2\u0175M\3\2\2\2\u0176\u0177\7?\2\2\u0177O\3\2\2\2\32T_ku\u0096\u009d" +
          "\u00a5\u00b5\u00ba\u00c3\u00ce\u00df\u00e6\u00f0\u00f7\u00fd\u010a\u0124" +
          "\u013e\u0140\u0158\u015b\u016b\u016e";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}