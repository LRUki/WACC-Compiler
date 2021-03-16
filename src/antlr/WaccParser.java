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
      DONE = 17, NULL = 18, STRUCT = 19, DOT = 20, IMPORT = 21, ASSIGN = 22, SEMICOLON = 23,
      COMMA = 24, NEWPAIR = 25, CALL = 26, FST = 27, SND = 28, PAIR = 29, TRUE = 30, FALSE = 31,
      INT = 32, BOOL = 33, CHAR = 34, STRING = 35, VAR = 36, NOT = 37, LEN = 38, ORD = 39, CHR = 40,
      REF = 41, PLUS = 42, MINUS = 43, MULT = 44, DIV = 45, MOD = 46, GTE = 47, GT = 48, LTE = 49,
      LT = 50, EQ = 51, NEQ = 52, AND = 53, OR = 54, L_PAREN = 55, R_PAREN = 56, L_SQUARE = 57,
      R_SQUARE = 58, L_CURLY = 59, R_CURLY = 60, NUMBER = 61, CAPTIALISED_IDENT = 62,
      IDENT = 63, WHITESPACE = 64, ESCAPE_CHARACTER = 65, CHARACTER = 66, STR_LITER = 67,
      CHAR_LITER = 68, COMMENT = 69;
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
    return new String[]{
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
		return new String[]{
        null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'",
        "'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'",
        "'while'", "'do'", "'done'", "'null'", "'struct'", "'.'", "'import'",
        "'='", "';'", "','", "'newpair'", "'call'", "'fst'", "'snd'", "'pair'",
        "'true'", "'false'", "'int'", "'bool'", "'char'", "'string'", "'var'",
        "'!'", "'len'", "'ord'", "'chr'", "'&'", "'+'", "'-'", "'*'", "'/'",
        "'%'", "'>='", "'>'", "'<='", "'<'", "'=='", "'!='", "'&&'", "'||'",
        "'('", "')'", "'['", "']'", "'{'", "'}'"
    };
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
        null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT",
        "PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE",
        "NULL", "STRUCT", "DOT", "IMPORT", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR",
        "CALL", "FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR",
        "STRING", "VAR", "NOT", "LEN", "ORD", "CHR", "REF", "PLUS", "MINUS",
        "MULT", "DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR",
        "L_PAREN", "R_PAREN", "L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER",
        "CAPTIALISED_IDENT", "IDENT", "WHITESPACE", "ESCAPE_CHARACTER", "CHARACTER",
        "STR_LITER", "CHAR_LITER", "COMMENT"
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

    public TerminalNode BEGIN() {
      return getToken(WaccParser.BEGIN, 0);
    }

    public StatContext stat() {
      return getRuleContext(StatContext.class, 0);
    }

    public TerminalNode END() {
      return getToken(WaccParser.END, 0);
    }

    public TerminalNode EOF() {
      return getToken(WaccParser.EOF, 0);
    }

    public List<ImportStatContext> importStat() {
      return getRuleContexts(ImportStatContext.class);
    }

    public ImportStatContext importStat(int i) {
      return getRuleContext(ImportStatContext.class, i);
    }

    public List<FuncContext> func() {
      return getRuleContexts(FuncContext.class);
    }

    public FuncContext func(int i) {
      return getRuleContext(FuncContext.class, i);
    }

    public ProgramContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_program;
    }
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
        while (_la == IMPORT) {
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
        _alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            {
              {
                setState(87);
                func();
              }
            }
          }
          setState(92);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
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
        setState(97);
        type();
        setState(98);
        ident();
        setState(99);
        match(L_PAREN);
        setState(101);
        _errHandler.sync(this);
        _la = _input.LA(1);
        if ((((_la) & ~0x3f) == 0 &&
            ((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L
                << CHAR) | (1L << STRING) | (1L << L_PAREN))) != 0)) {
          {
            setState(100);
            paramList();
          }
        }

        setState(103);
        match(R_PAREN);
        setState(104);
        match(IS);
        setState(105);
        stat(0);
        setState(106);
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
        setState(108);
        param();
        setState(113);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == COMMA) {
          {
            {
              setState(109);
              match(COMMA);
              setState(110);
              param();
            }
          }
          setState(115);
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
        setState(116);
        type();
        setState(117);
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
      if (visitor instanceof WaccParserVisitor)
        return ((WaccParserVisitor<? extends T>) visitor).visitMultiStat(this);
      else {
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

    public StructDeclareContext structDeclare() {
      return getRuleContext(StructDeclareContext.class, 0);
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
      return getRuleContext(IdentContext.class, 0);
    }

    public TerminalNode ASSIGN() {
      return getToken(WaccParser.ASSIGN, 0);
    }

    public AssignRhsContext assignRhs() {
      return getRuleContext(AssignRhsContext.class, 0);
    }

    public TypeContext type() {
      return getRuleContext(TypeContext.class, 0);
    }

    public ImplicitTypeContext implicitType() {
      return getRuleContext(ImplicitTypeContext.class, 0);
    }

    public DeclareStatContext(StatContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitDeclareStat(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class WhileStatContext extends StatContext {

    public TerminalNode WHILE() {
      return getToken(WaccParser.WHILE, 0);
    }

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
        setState(156);
        _errHandler.sync(this);
        switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
          case 1: {
            _localctx = new SkipStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(120);
            match(SKIP_TOKEN);
          }
          break;
          case 2: {
            _localctx = new DeclareStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(123);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
              case STRUCT:
              case PAIR:
              case INT:
              case BOOL:
              case CHAR:
              case STRING:
              case L_PAREN: {
                setState(121);
                type();
              }
              break;
              case VAR: {
                setState(122);
                implicitType();
              }
              break;
              default:
                throw new NoViableAltException(this);
            }
            setState(125);
            ident();
            setState(126);
            match(ASSIGN);
            setState(127);
            assignRhs();
          }
          break;
          case 3: {
            _localctx = new AssignStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(129);
            assignLhs();
            setState(130);
            match(ASSIGN);
            setState(131);
            assignRhs();
          }
          break;
          case 4: {
            _localctx = new StructDeclareStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(133);
            structDeclare();
          }
          break;
          case 5: {
            _localctx = new ReadStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(134);
            match(READ);
            setState(135);
            assignLhs();
          }
          break;
          case 6: {
            _localctx = new ActionStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(136);
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
            setState(137);
            expr(0);
          }
          break;
          case 7: {
            _localctx = new IfStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(138);
            match(IF);
            setState(139);
            expr(0);
            setState(140);
            match(THEN);
            setState(141);
            stat(0);
            setState(142);
            match(ELSE);
            setState(143);
            stat(0);
            setState(144);
            match(FI);
          }
          break;
          case 8: {
            _localctx = new WhileStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(146);
            match(WHILE);
            setState(147);
            expr(0);
            setState(148);
            match(DO);
            setState(149);
            stat(0);
            setState(150);
            match(DONE);
          }
          break;
          case 9: {
            _localctx = new BlockStatContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(152);
            match(BEGIN);
            setState(153);
            stat(0);
            setState(154);
            match(END);
          }
          break;
        }
        _ctx.stop = _input.LT(-1);
        setState(163);
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
                setState(158);
                if (!(precpred(_ctx, 1))) {
                  throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                }
                setState(159);
                match(SEMICOLON);
                setState(160);
                stat(2);
              }
            }
          }
          setState(165);
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
      setState(171);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
        case 1:
          enterOuterAlt(_localctx, 1);
        {
          setState(166);
          ident();
        }
        break;
        case 2:
          enterOuterAlt(_localctx, 2);
        {
          setState(167);
          arrayElem();
        }
        break;
        case 3:
          enterOuterAlt(_localctx, 3);
        {
          setState(168);
          pairElem();
        }
        break;
        case 4:
          enterOuterAlt(_localctx, 4);
        {
          setState(169);
          pointerElem();
        }
        break;
        case 5:
          enterOuterAlt(_localctx, 5);
        {
          setState(170);
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
      setState(192);
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
          setState(173);
          expr(0);
        }
        break;
        case L_SQUARE:
				enterOuterAlt(_localctx, 2);
        {
          setState(174);
          arrayLiter();
        }
				break;
			case NEWPAIR:
				enterOuterAlt(_localctx, 3);
      {
        setState(175);
        match(NEWPAIR);
        setState(176);
        match(L_PAREN);
        setState(177);
        expr(0);
        setState(178);
        match(COMMA);
        setState(179);
        expr(0);
        setState(180);
        match(R_PAREN);
      }
				break;
			case FST:
			case SND:
				enterOuterAlt(_localctx, 4);
      {
        setState(182);
        pairElem();
      }
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
      {
        setState(183);
        match(CALL);
        setState(184);
        ident();
        setState(185);
        match(L_PAREN);
        setState(187);
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
            setState(186);
            argList();
          }
        }

        setState(189);
        match(R_PAREN);
      }
      break;
        case L_CURLY:
          enterOuterAlt(_localctx, 6);
        {
          setState(191);
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

  public static class ImportStatContext extends ParserRuleContext {

    public TerminalNode IMPORT() {
      return getToken(WaccParser.IMPORT, 0);
    }

    public List<IdentContext> ident() {
      return getRuleContexts(IdentContext.class);
    }

    public IdentContext ident(int i) {
      return getRuleContext(IdentContext.class, i);
    }

    public TerminalNode DOT() {
      return getToken(WaccParser.DOT, 0);
    }

    public ImportStatContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_importStat;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitImportStat(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final ImportStatContext importStat() throws RecognitionException {
    ImportStatContext _localctx = new ImportStatContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_importStat);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(194);
        match(IMPORT);
        setState(195);
        ident();
        setState(196);
        match(DOT);
        setState(197);
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

  public static class StructDeclareContext extends ParserRuleContext {

    public StructTypeContext structType() {
      return getRuleContext(StructTypeContext.class, 0);
    }

    public TerminalNode L_CURLY() {
      return getToken(WaccParser.L_CURLY, 0);
    }

    public TerminalNode R_CURLY() {
      return getToken(WaccParser.R_CURLY, 0);
    }

    public List<StructMemberContext> structMember() {
      return getRuleContexts(StructMemberContext.class);
    }

    public StructMemberContext structMember(int i) {
      return getRuleContext(StructMemberContext.class, i);
    }

    public List<TerminalNode> SEMICOLON() {
      return getTokens(WaccParser.SEMICOLON);
    }

    public TerminalNode SEMICOLON(int i) {
      return getToken(WaccParser.SEMICOLON, i);
    }

    public StructDeclareContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_structDeclare;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitStructDeclare(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final StructDeclareContext structDeclare() throws RecognitionException {
    StructDeclareContext _localctx = new StructDeclareContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_structDeclare);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(199);
        structType();
        setState(200);
        match(L_CURLY);
        setState(206);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while ((((_la) & ~0x3f) == 0 &&
            ((1L << _la) & ((1L << STRUCT) | (1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L
                << CHAR) | (1L << STRING) | (1L << L_PAREN))) != 0)) {
          {
            {
              setState(201);
              structMember();
              setState(202);
              match(SEMICOLON);
            }
          }
          setState(208);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        setState(209);
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

    public List<TerminalNode> COMMA() {
      return getTokens(WaccParser.COMMA);
    }

    public TerminalNode COMMA(int i) {
      return getToken(WaccParser.COMMA, i);
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
    enterRule(_localctx, 18, RULE_structAssign);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(211);
        match(L_CURLY);
        {
          setState(212);
          assignRhs();
          setState(217);
          _errHandler.sync(this);
          _la = _input.LA(1);
          while (_la == COMMA) {
            {
              {
                setState(213);
                match(COMMA);
                setState(214);
                assignRhs();
              }
            }
            setState(219);
            _errHandler.sync(this);
            _la = _input.LA(1);
          }
        }
        setState(220);
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
    enterRule(_localctx, 20, RULE_structMember);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(222);
        type();
        setState(223);
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
    enterRule(_localctx, 22, RULE_structAccess);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(225);
        ident();
        setState(226);
        match(DOT);
        setState(227);
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
        setState(229);
        expr(0);
        setState(234);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == COMMA) {
          {
            {
              setState(230);
              match(COMMA);
              setState(231);
              expr(0);
            }
          }
          setState(236);
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
      setState(241);
      _errHandler.sync(this);
      switch (_input.LA(1)) {
        case FST:
          enterOuterAlt(_localctx, 1);
        {
          setState(237);
          match(FST);
          setState(238);
          expr(0);
        }
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
      {
        setState(239);
        match(SND);
        setState(240);
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
    enterRule(_localctx, 28, RULE_pointerElem);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(243);
        match(MULT);
        setState(244);
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

    public StructTypeContext structType() {
      return getRuleContext(StructTypeContext.class, 0);
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
    enterRule(_localctx, 30, RULE_type);
    try {
      setState(251);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
        case 1:
          enterOuterAlt(_localctx, 1);
        {
          setState(246);
          baseType();
        }
        break;
        case 2:
          enterOuterAlt(_localctx, 2);
        {
          setState(247);
          pairType();
        }
        break;
        case 3:
          enterOuterAlt(_localctx, 3);
        {
          setState(248);
          arrayType();
        }
        break;
        case 4:
          enterOuterAlt(_localctx, 4);
        {
          setState(249);
          structType();
        }
        break;
        case 5:
          enterOuterAlt(_localctx, 5);
        {
          setState(250);
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
        setState(253);
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

    public TerminalNode L_PAREN() {
      return getToken(WaccParser.L_PAREN, 0);
    }

    public PointerTypeContext pointerType() {
      return getRuleContext(PointerTypeContext.class, 0);
    }

    public TerminalNode R_PAREN() {
      return getToken(WaccParser.R_PAREN, 0);
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
        setState(262);
        _errHandler.sync(this);
        switch (_input.LA(1)) {
          case INT:
          case BOOL:
          case CHAR:
          case STRING: {
            setState(255);
            baseType();
          }
          break;
          case PAIR: {
            setState(256);
            pairType();
          }
          break;
          case STRUCT: {
            setState(257);
            structType();
          }
          break;
          case L_PAREN: {
            setState(258);
            match(L_PAREN);
            setState(259);
            pointerType();
            setState(260);
            match(R_PAREN);
          }
          break;
          default:
            throw new NoViableAltException(this);
        }
        setState(266);
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
            {
              setState(264);
              match(L_SQUARE);
              setState(265);
              match(R_SQUARE);
            }
          }
          setState(268);
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
        setState(270);
        match(PAIR);
        setState(271);
        match(L_PAREN);
        setState(272);
        pairElemType();
        setState(273);
        match(COMMA);
        setState(274);
        pairElemType();
        setState(275);
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
      return getRuleContext(BaseTypeContext.class, 0);
    }

    public ArrayTypeContext arrayType() {
      return getRuleContext(ArrayTypeContext.class, 0);
    }

    public PairTypeContext pairType() {
      return getRuleContext(PairTypeContext.class, 0);
    }

    public TerminalNode PAIR() {
      return getToken(WaccParser.PAIR, 0);
    }

    public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_pairElemType;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitPairElemType(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
    PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
    enterRule(_localctx, 38, RULE_pairElemType);
    try {
      setState(281);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
        case 1:
          enterOuterAlt(_localctx, 1);
        {
          setState(277);
          baseType();
        }
        break;
        case 2:
          enterOuterAlt(_localctx, 2);
        {
          setState(278);
          arrayType();
        }
        break;
        case 3:
          enterOuterAlt(_localctx, 3);
        {
          setState(279);
          pairType();
        }
        break;
        case 4:
          enterOuterAlt(_localctx, 4);
        {
          setState(280);
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

    public PairTypeContext pairType() {
      return getRuleContext(PairTypeContext.class, 0);
    }

    public ArrayTypeContext arrayType() {
      return getRuleContext(ArrayTypeContext.class, 0);
    }

    public StructTypeContext structType() {
      return getRuleContext(StructTypeContext.class, 0);
    }

    public List<TerminalNode> MULT() {
      return getTokens(WaccParser.MULT);
    }

    public TerminalNode MULT(int i) {
      return getToken(WaccParser.MULT, i);
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
    enterRule(_localctx, 40, RULE_pointerType);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(287);
        _errHandler.sync(this);
        switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
          case 1: {
            setState(283);
            baseType();
          }
          break;
          case 2: {
            setState(284);
            pairType();
          }
          break;
          case 3: {
            setState(285);
            arrayType();
          }
          break;
          case 4: {
            setState(286);
            structType();
          }
          break;
        }
        setState(290);
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
            {
              setState(289);
              match(MULT);
            }
          }
          setState(292);
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while (_la == MULT);
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
    enterRule(_localctx, 42, RULE_implicitType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(294);
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

    public CapitalisedIdentContext capitalisedIdent() {
      return getRuleContext(CapitalisedIdentContext.class, 0);
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
    enterRule(_localctx, 44, RULE_structType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(296);
        match(STRUCT);
        setState(297);
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
        setState(315);
        _errHandler.sync(this);
        switch (getInterpreter().adaptivePredict(_input, 20, _ctx)) {
          case 1: {
            _localctx = new SingletonExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(300);
            intLiter();
          }
          break;
			case 2: {
        _localctx = new SingletonExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(301);
        boolLiter();
      }
				break;
			case 3: {
        _localctx = new SingletonExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(302);
        charLiter();
      }
				break;
			case 4: {
        _localctx = new SingletonExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(303);
        strLiter();
      }
				break;
			case 5: {
        _localctx = new SingletonExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(304);
        pairLiter();
      }
				break;
			case 6: {
        _localctx = new SingletonExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(305);
        ident();
      }
				break;
			case 7: {
        _localctx = new SingletonExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(306);
        arrayElem();
      }
				break;
			case 8: {
        _localctx = new UnopExprContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(307);
        unop();
        setState(308);
        expr(3);
      }
      break;
          case 9: {
            _localctx = new StructAccessExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(310);
            structAccess();
          }
          break;
          case 10: {
            _localctx = new ParenExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(311);
            match(L_PAREN);
            setState(312);
            expr(0);
            setState(313);
            match(R_PAREN);
          }
          break;
        }
        _ctx.stop = _input.LT(-1);
        setState(343);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input, 22, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            if (_parseListeners != null) {
              triggerExitRuleEvent();
            }
            _prevctx = _localctx;
            {
              setState(341);
              _errHandler.sync(this);
              switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
                case 1: {
                  _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(317);
                  if (!(precpred(_ctx, 16))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                  }
                  setState(318);
                  binop1();
                  setState(319);
                  expr(17);
                }
						break;
					case 2: {
            _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(321);
            if (!(precpred(_ctx, 15))) {
              throw new FailedPredicateException(this, "precpred(_ctx, 15)");
            }
            setState(322);
            binop2();
            setState(323);
            expr(16);
          }
						break;
					case 3: {
            _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(325);
            if (!(precpred(_ctx, 14))) {
              throw new FailedPredicateException(this, "precpred(_ctx, 14)");
            }
            setState(326);
            binop3();
            setState(327);
            expr(15);
          }
						break;
					case 4: {
            _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(329);
            if (!(precpred(_ctx, 13))) {
              throw new FailedPredicateException(this, "precpred(_ctx, 13)");
            }
            setState(330);
            binop4();
            setState(331);
            expr(14);
          }
						break;
					case 5: {
            _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(333);
            if (!(precpred(_ctx, 12))) {
              throw new FailedPredicateException(this, "precpred(_ctx, 12)");
            }
            setState(334);
            binop5();
            setState(335);
            expr(13);
          }
						break;
					case 6: {
            _localctx = new BinopExprContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(337);
            if (!(precpred(_ctx, 11))) {
              throw new FailedPredicateException(this, "precpred(_ctx, 11)");
            }
            setState(338);
            binop6();
            setState(339);
            expr(12);
          }
          break;
              }
            }
          }
          setState(345);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 22, _ctx);
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
    enterRule(_localctx, 48, RULE_unop);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(346);
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
    enterRule(_localctx, 50, RULE_binop1);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(348);
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
    enterRule(_localctx, 52, RULE_binop2);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(350);
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
    enterRule(_localctx, 54, RULE_binop3);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(352);
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
    enterRule(_localctx, 56, RULE_binop4);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(354);
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
    enterRule(_localctx, 58, RULE_binop5);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(356);
        match(AND);
      }
    } catch (RecognitionException re) {
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
        setState(358);
        match(OR);
      }
    } catch (RecognitionException re) {
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
        setState(360);
        ident();
        setState(365);
        _errHandler.sync(this);
        _alt = 1;
        do {
          switch (_alt) {
            case 1: {
              {
                setState(361);
                match(L_SQUARE);
                setState(362);
                expr(0);
                setState(363);
                match(R_SQUARE);
              }
            }
            break;
            default:
              throw new NoViableAltException(this);
          }
          setState(367);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 23, _ctx);
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
        setState(370);
        _errHandler.sync(this);
        _la = _input.LA(1);
        if (_la == PLUS || _la == MINUS) {
          {
            setState(369);
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

        setState(372);
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
        setState(374);
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
    enterRule(_localctx, 68, RULE_strLiter);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(376);
        match(STR_LITER);
      }
    } catch (RecognitionException re) {
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
        setState(378);
        match(CHAR_LITER);
      }
    } catch (RecognitionException re) {
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
        setState(380);
        match(L_SQUARE);
        setState(389);
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
            setState(381);
            expr(0);
            setState(386);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == COMMA) {
              {
                {
                  setState(382);
                  match(COMMA);
                  setState(383);
                  expr(0);
                }
              }
              setState(388);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

        setState(391);
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
        setState(393);
        match(NULL);
      }
    } catch (RecognitionException re) {
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

    public TerminalNode IDENT() {
      return getToken(WaccParser.IDENT, 0);
    }

    public TerminalNode CAPTIALISED_IDENT() {
      return getToken(WaccParser.CAPTIALISED_IDENT, 0);
    }

    public IdentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_ident;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitIdent(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final IdentContext ident() throws RecognitionException {
    IdentContext _localctx = new IdentContext(_ctx, getState());
    enterRule(_localctx, 76, RULE_ident);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(395);
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

    public TerminalNode CAPTIALISED_IDENT() {
      return getToken(WaccParser.CAPTIALISED_IDENT, 0);
    }

    public CapitalisedIdentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_capitalisedIdent;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof WaccParserVisitor) {
        return ((WaccParserVisitor<? extends T>) visitor).visitCapitalisedIdent(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final CapitalisedIdentContext capitalisedIdent() throws RecognitionException {
    CapitalisedIdentContext _localctx = new CapitalisedIdentContext(_ctx, getState());
    enterRule(_localctx, 78, RULE_capitalisedIdent);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(397);
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
      case 23:
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
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3G\u0192\4\2\t\2\4" +
          "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
          "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
          "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
          "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
          "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\7\2T\n\2\f" +
          "\2\16\2W\13\2\3\2\3\2\7\2[\n\2\f\2\16\2^\13\2\3\2\3\2\3\2\3\2\3\3\3\3" +
          "\3\3\3\3\5\3h\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4r\n\4\f\4\16\4u\13" +
          "\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6~\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3" +
          "\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
          "\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u009f\n\6\3\6\3\6\3\6\7\6\u00a4\n\6\f\6\16" +
          "\6\u00a7\13\6\3\7\3\7\3\7\3\7\3\7\5\7\u00ae\n\7\3\b\3\b\3\b\3\b\3\b\3" +
          "\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00be\n\b\3\b\3\b\3\b\5\b\u00c3" +
          "\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\n\u00cf\n\n\f\n\16\n\u00d2" +
          "\13\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u00da\n\13\f\13\16\13\u00dd\13" +
          "\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u00eb\n" +
          "\16\f\16\16\16\u00ee\13\16\3\17\3\17\3\17\3\17\5\17\u00f4\n\17\3\20\3" +
          "\20\3\20\3\21\3\21\3\21\3\21\3\21\5\21\u00fe\n\21\3\22\3\22\3\23\3\23" +
          "\3\23\3\23\3\23\3\23\3\23\5\23\u0109\n\23\3\23\3\23\6\23\u010d\n\23\r" +
          "\23\16\23\u010e\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25" +
          "\5\25\u011c\n\25\3\26\3\26\3\26\3\26\5\26\u0122\n\26\3\26\6\26\u0125\n" +
          "\26\r\26\16\26\u0126\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31" +
          "\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u013e\n\31" +
          "\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31" +
          "\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0158\n\31\f\31" +
          "\16\31\u015b\13\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3" +
          "\37\3\37\3 \3 \3!\3!\3!\3!\3!\6!\u0170\n!\r!\16!\u0171\3\"\5\"\u0175\n" +
          "\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3&\7&\u0183\n&\f&\16&\u0186\13&" +
          "\5&\u0188\n&\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\2\4\n\60*\2\4\6\b\n\f\16\20" +
          "\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP\2\13\3\2\b\f\3" +
          "\2\"%\4\2\'+-.\3\2.\60\3\2,-\3\2\61\64\3\2\65\66\3\2 !\3\2@A\2\u01a7\2" +
          "U\3\2\2\2\4c\3\2\2\2\6n\3\2\2\2\bv\3\2\2\2\n\u009e\3\2\2\2\f\u00ad\3\2" +
          "\2\2\16\u00c2\3\2\2\2\20\u00c4\3\2\2\2\22\u00c9\3\2\2\2\24\u00d5\3\2\2" +
          "\2\26\u00e0\3\2\2\2\30\u00e3\3\2\2\2\32\u00e7\3\2\2\2\34\u00f3\3\2\2\2" +
          "\36\u00f5\3\2\2\2 \u00fd\3\2\2\2\"\u00ff\3\2\2\2$\u0108\3\2\2\2&\u0110" +
          "\3\2\2\2(\u011b\3\2\2\2*\u0121\3\2\2\2,\u0128\3\2\2\2.\u012a\3\2\2\2\60" +
          "\u013d\3\2\2\2\62\u015c\3\2\2\2\64\u015e\3\2\2\2\66\u0160\3\2\2\28\u0162" +
          "\3\2\2\2:\u0164\3\2\2\2<\u0166\3\2\2\2>\u0168\3\2\2\2@\u016a\3\2\2\2B" +
          "\u0174\3\2\2\2D\u0178\3\2\2\2F\u017a\3\2\2\2H\u017c\3\2\2\2J\u017e\3\2" +
          "\2\2L\u018b\3\2\2\2N\u018d\3\2\2\2P\u018f\3\2\2\2RT\5\20\t\2SR\3\2\2\2" +
          "TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2X\\\7\3\2\2Y[\5\4\3" +
          "\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\5" +
          "\n\6\2`a\7\4\2\2ab\7\2\2\3b\3\3\2\2\2cd\5 \21\2de\5N(\2eg\79\2\2fh\5\6" +
          "\4\2gf\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7:\2\2jk\7\5\2\2kl\5\n\6\2lm\7\4" +
          "\2\2m\5\3\2\2\2ns\5\b\5\2op\7\32\2\2pr\5\b\5\2qo\3\2\2\2ru\3\2\2\2sq\3" +
          "\2\2\2st\3\2\2\2t\7\3\2\2\2us\3\2\2\2vw\5 \21\2wx\5N(\2x\t\3\2\2\2yz\b" +
          "\6\1\2z\u009f\7\6\2\2{~\5 \21\2|~\5,\27\2}{\3\2\2\2}|\3\2\2\2~\177\3\2" +
          "\2\2\177\u0080\5N(\2\u0080\u0081\7\30\2\2\u0081\u0082\5\16\b\2\u0082\u009f" +
          "\3\2\2\2\u0083\u0084\5\f\7\2\u0084\u0085\7\30\2\2\u0085\u0086\5\16\b\2" +
          "\u0086\u009f\3\2\2\2\u0087\u009f\5\22\n\2\u0088\u0089\7\7\2\2\u0089\u009f" +
          "\5\f\7\2\u008a\u008b\t\2\2\2\u008b\u009f\5\60\31\2\u008c\u008d\7\r\2\2" +
          "\u008d\u008e\5\60\31\2\u008e\u008f\7\16\2\2\u008f\u0090\5\n\6\2\u0090" +
          "\u0091\7\17\2\2\u0091\u0092\5\n\6\2\u0092\u0093\7\20\2\2\u0093\u009f\3" +
          "\2\2\2\u0094\u0095\7\21\2\2\u0095\u0096\5\60\31\2\u0096\u0097\7\22\2\2" +
          "\u0097\u0098\5\n\6\2\u0098\u0099\7\23\2\2\u0099\u009f\3\2\2\2\u009a\u009b" +
          "\7\3\2\2\u009b\u009c\5\n\6\2\u009c\u009d\7\4\2\2\u009d\u009f\3\2\2\2\u009e" +
          "y\3\2\2\2\u009e}\3\2\2\2\u009e\u0083\3\2\2\2\u009e\u0087\3\2\2\2\u009e" +
          "\u0088\3\2\2\2\u009e\u008a\3\2\2\2\u009e\u008c\3\2\2\2\u009e\u0094\3\2" +
          "\2\2\u009e\u009a\3\2\2\2\u009f\u00a5\3\2\2\2\u00a0\u00a1\f\3\2\2\u00a1" +
          "\u00a2\7\31\2\2\u00a2\u00a4\5\n\6\4\u00a3\u00a0\3\2\2\2\u00a4\u00a7\3" +
          "\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\13\3\2\2\2\u00a7" +
          "\u00a5\3\2\2\2\u00a8\u00ae\5N(\2\u00a9\u00ae\5@!\2\u00aa\u00ae\5\34\17" +
          "\2\u00ab\u00ae\5\36\20\2\u00ac\u00ae\5\30\r\2\u00ad\u00a8\3\2\2\2\u00ad" +
          "\u00a9\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2" +
          "\2\2\u00ae\r\3\2\2\2\u00af\u00c3\5\60\31\2\u00b0\u00c3\5J&\2\u00b1\u00b2" +
          "\7\33\2\2\u00b2\u00b3\79\2\2\u00b3\u00b4\5\60\31\2\u00b4\u00b5\7\32\2" +
          "\2\u00b5\u00b6\5\60\31\2\u00b6\u00b7\7:\2\2\u00b7\u00c3\3\2\2\2\u00b8" +
          "\u00c3\5\34\17\2\u00b9\u00ba\7\34\2\2\u00ba\u00bb\5N(\2\u00bb\u00bd\7" +
          "9\2\2\u00bc\u00be\5\32\16\2\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be" +
          "\u00bf\3\2\2\2\u00bf\u00c0\7:\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00c3\5\24" +
          "\13\2\u00c2\u00af\3\2\2\2\u00c2\u00b0\3\2\2\2\u00c2\u00b1\3\2\2\2\u00c2" +
          "\u00b8\3\2\2\2\u00c2\u00b9\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\17\3\2\2" +
          "\2\u00c4\u00c5\7\27\2\2\u00c5\u00c6\5N(\2\u00c6\u00c7\7\26\2\2\u00c7\u00c8" +
          "\5N(\2\u00c8\21\3\2\2\2\u00c9\u00ca\5.\30\2\u00ca\u00d0\7=\2\2\u00cb\u00cc" +
          "\5\26\f\2\u00cc\u00cd\7\31\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cb\3\2\2\2" +
          "\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3" +
          "\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d4\7>\2\2\u00d4\23\3\2\2\2\u00d5" +
          "\u00d6\7=\2\2\u00d6\u00db\5\16\b\2\u00d7\u00d8\7\32\2\2\u00d8\u00da\5" +
          "\16\b\2\u00d9\u00d7\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db" +
          "\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00df\7>" +
          "\2\2\u00df\25\3\2\2\2\u00e0\u00e1\5 \21\2\u00e1\u00e2\5N(\2\u00e2\27\3" +
          "\2\2\2\u00e3\u00e4\5N(\2\u00e4\u00e5\7\26\2\2\u00e5\u00e6\5N(\2\u00e6" +
          "\31\3\2\2\2\u00e7\u00ec\5\60\31\2\u00e8\u00e9\7\32\2\2\u00e9\u00eb\5\60" +
          "\31\2\u00ea\u00e8\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec" +
          "\u00ed\3\2\2\2\u00ed\33\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7\35\2" +
          "\2\u00f0\u00f4\5\60\31\2\u00f1\u00f2\7\36\2\2\u00f2\u00f4\5\60\31\2\u00f3" +
          "\u00ef\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\35\3\2\2\2\u00f5\u00f6\7.\2\2" +
          "\u00f6\u00f7\5N(\2\u00f7\37\3\2\2\2\u00f8\u00fe\5\"\22\2\u00f9\u00fe\5" +
          "&\24\2\u00fa\u00fe\5$\23\2\u00fb\u00fe\5.\30\2\u00fc\u00fe\5*\26\2\u00fd" +
          "\u00f8\3\2\2\2\u00fd\u00f9\3\2\2\2\u00fd\u00fa\3\2\2\2\u00fd\u00fb\3\2" +
          "\2\2\u00fd\u00fc\3\2\2\2\u00fe!\3\2\2\2\u00ff\u0100\t\3\2\2\u0100#\3\2" +
          "\2\2\u0101\u0109\5\"\22\2\u0102\u0109\5&\24\2\u0103\u0109\5.\30\2\u0104" +
          "\u0105\79\2\2\u0105\u0106\5*\26\2\u0106\u0107\7:\2\2\u0107\u0109\3\2\2" +
          "\2\u0108\u0101\3\2\2\2\u0108\u0102\3\2\2\2\u0108\u0103\3\2\2\2\u0108\u0104" +
          "\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u010b\7;\2\2\u010b\u010d\7<\2\2\u010c" +
          "\u010a\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2" +
          "\2\2\u010f%\3\2\2\2\u0110\u0111\7\37\2\2\u0111\u0112\79\2\2\u0112\u0113" +
          "\5(\25\2\u0113\u0114\7\32\2\2\u0114\u0115\5(\25\2\u0115\u0116\7:\2\2\u0116" +
          "\'\3\2\2\2\u0117\u011c\5\"\22\2\u0118\u011c\5$\23\2\u0119\u011c\5&\24" +
          "\2\u011a\u011c\7\37\2\2\u011b\u0117\3\2\2\2\u011b\u0118\3\2\2\2\u011b" +
          "\u0119\3\2\2\2\u011b\u011a\3\2\2\2\u011c)\3\2\2\2\u011d\u0122\5\"\22\2" +
          "\u011e\u0122\5&\24\2\u011f\u0122\5$\23\2\u0120\u0122\5.\30\2\u0121\u011d" +
          "\3\2\2\2\u0121\u011e\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122" +
          "\u0124\3\2\2\2\u0123\u0125\7.\2\2\u0124\u0123\3\2\2\2\u0125\u0126\3\2" +
          "\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127+\3\2\2\2\u0128\u0129" +
          "\7&\2\2\u0129-\3\2\2\2\u012a\u012b\7\25\2\2\u012b\u012c\5P)\2\u012c/\3" +
          "\2\2\2\u012d\u012e\b\31\1\2\u012e\u013e\5B\"\2\u012f\u013e\5D#\2\u0130" +
          "\u013e\5H%\2\u0131\u013e\5F$\2\u0132\u013e\5L\'\2\u0133\u013e\5N(\2\u0134" +
          "\u013e\5@!\2\u0135\u0136\5\62\32\2\u0136\u0137\5\60\31\5\u0137\u013e\3" +
          "\2\2\2\u0138\u013e\5\30\r\2\u0139\u013a\79\2\2\u013a\u013b\5\60\31\2\u013b" +
          "\u013c\7:\2\2\u013c\u013e\3\2\2\2\u013d\u012d\3\2\2\2\u013d\u012f\3\2" +
          "\2\2\u013d\u0130\3\2\2\2\u013d\u0131\3\2\2\2\u013d\u0132\3\2\2\2\u013d" +
          "\u0133\3\2\2\2\u013d\u0134\3\2\2\2\u013d\u0135\3\2\2\2\u013d\u0138\3\2" +
          "\2\2\u013d\u0139\3\2\2\2\u013e\u0159\3\2\2\2\u013f\u0140\f\22\2\2\u0140" +
          "\u0141\5\64\33\2\u0141\u0142\5\60\31\23\u0142\u0158\3\2\2\2\u0143\u0144" +
          "\f\21\2\2\u0144\u0145\5\66\34\2\u0145\u0146\5\60\31\22\u0146\u0158\3\2" +
          "\2\2\u0147\u0148\f\20\2\2\u0148\u0149\58\35\2\u0149\u014a\5\60\31\21\u014a" +
          "\u0158\3\2\2\2\u014b\u014c\f\17\2\2\u014c\u014d\5:\36\2\u014d\u014e\5" +
          "\60\31\20\u014e\u0158\3\2\2\2\u014f\u0150\f\16\2\2\u0150\u0151\5<\37\2" +
          "\u0151\u0152\5\60\31\17\u0152\u0158\3\2\2\2\u0153\u0154\f\r\2\2\u0154" +
          "\u0155\5> \2\u0155\u0156\5\60\31\16\u0156\u0158\3\2\2\2\u0157\u013f\3" +
          "\2\2\2\u0157\u0143\3\2\2\2\u0157\u0147\3\2\2\2\u0157\u014b\3\2\2\2\u0157" +
          "\u014f\3\2\2\2\u0157\u0153\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2" +
          "\2\2\u0159\u015a\3\2\2\2\u015a\61\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015d" +
          "\t\4\2\2\u015d\63\3\2\2\2\u015e\u015f\t\5\2\2\u015f\65\3\2\2\2\u0160\u0161" +
          "\t\6\2\2\u0161\67\3\2\2\2\u0162\u0163\t\7\2\2\u01639\3\2\2\2\u0164\u0165" +
          "\t\b\2\2\u0165;\3\2\2\2\u0166\u0167\7\67\2\2\u0167=\3\2\2\2\u0168\u0169" +
          "\78\2\2\u0169?\3\2\2\2\u016a\u016f\5N(\2\u016b\u016c\7;\2\2\u016c\u016d" +
          "\5\60\31\2\u016d\u016e\7<\2\2\u016e\u0170\3\2\2\2\u016f\u016b\3\2\2\2" +
          "\u0170\u0171\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172A\3" +
          "\2\2\2\u0173\u0175\t\6\2\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175" +
          "\u0176\3\2\2\2\u0176\u0177\7?\2\2\u0177C\3\2\2\2\u0178\u0179\t\t\2\2\u0179" +
          "E\3\2\2\2\u017a\u017b\7E\2\2\u017bG\3\2\2\2\u017c\u017d\7F\2\2\u017dI" +
          "\3\2\2\2\u017e\u0187\7;\2\2\u017f\u0184\5\60\31\2\u0180\u0181\7\32\2\2" +
          "\u0181\u0183\5\60\31\2\u0182\u0180\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182" +
          "\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0187" +
          "\u017f\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\7<" +
          "\2\2\u018aK\3\2\2\2\u018b\u018c\7\24\2\2\u018cM\3\2\2\2\u018d\u018e\t" +
          "\n\2\2\u018eO\3\2\2\2\u018f\u0190\7@\2\2\u0190Q\3\2\2\2\35U\\gs}\u009e" +
          "\u00a5\u00ad\u00bd\u00c2\u00d0\u00db\u00ec\u00f3\u00fd\u0108\u010e\u011b" +
          "\u0121\u0126\u013d\u0157\u0159\u0171\u0174\u0184\u0187";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}