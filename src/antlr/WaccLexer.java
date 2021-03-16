// Generated from ./WaccLexer.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WaccLexer extends Lexer {

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
  public static String[] channelNames = {
      "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
  };

  public static String[] modeNames = {
      "DEFAULT_MODE"
  };

  private static String[] makeRuleNames() {
    return new String[]{
        "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT",
        "PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE",
        "NULL", "STRUCT", "DOT", "IMPORT", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR",
        "CALL", "FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR",
        "STRING", "VAR", "NOT", "LEN", "ORD", "CHR", "REF", "PLUS", "MINUS",
        "MULT", "DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR",
        "L_PAREN", "R_PAREN", "L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER",
        "DIGIT", "CAPTIALISED_IDENT", "IDENT", "WHITESPACE", "ESCAPE_CHARACTER",
        "CHARACTER", "STR_LITER", "CHAR_LITER", "COMMENT"
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


	public WaccLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WaccLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2G\u01b7\b\1\4\2\t" +
          "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
          "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
          "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
          "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
          "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
          ",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
          "\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
          "\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\3\2\3\2\3" +
          "\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6" +
          "\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3" +
          "\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13" +
          "\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3" +
          "\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3" +
          "\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3" +
          "\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3" +
          "\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3" +
          "\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3" +
          "\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3" +
          "#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'\3\'\3\'\3" +
          "(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3" +
          "\60\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3" +
          "\66\3\66\3\66\3\67\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\6" +
          ">\u017a\n>\r>\16>\u017b\3?\3?\3@\5@\u0181\n@\3@\7@\u0184\n@\f@\16@\u0187" +
          "\13@\3A\5A\u018a\nA\3A\7A\u018d\nA\fA\16A\u0190\13A\3B\6B\u0193\nB\rB" +
          "\16B\u0194\3B\3B\3C\3C\3D\3D\3D\5D\u019e\nD\3E\3E\7E\u01a2\nE\fE\16E\u01a5" +
          "\13E\3E\3E\3F\3F\3F\3F\3G\3G\7G\u01af\nG\fG\16G\u01b2\13G\3G\3G\3G\3G" +
          "\3\u01b0\2H\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33" +
          "\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67" +
          "\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65" +
          "i\66k\67m8o9q:s;u<w=y>{?}\2\177@\u0081A\u0083B\u0085C\u0087D\u0089E\u008b" +
          "F\u008dG\3\2\n\3\2\62;\4\2C\\aa\6\2\62;C\\aac|\5\2C\\aac|\5\2\13\f\17" +
          "\17\"\"\13\2$$))\62\62^^ddhhppttvv\5\2$$))^^\4\2\f\f\17\17\2\u01bc\2\3" +
          "\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2" +
          "\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31" +
          "\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2" +
          "\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2" +
          "\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2" +
          "\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2" +
          "I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3" +
          "\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2" +
          "\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2" +
          "o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3" +
          "\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2" +
          "\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\3\u008f" +
          "\3\2\2\2\5\u0095\3\2\2\2\7\u0099\3\2\2\2\t\u009c\3\2\2\2\13\u00a1\3\2" +
          "\2\2\r\u00a6\3\2\2\2\17\u00ab\3\2\2\2\21\u00b2\3\2\2\2\23\u00b7\3\2\2" +
          "\2\25\u00bd\3\2\2\2\27\u00c5\3\2\2\2\31\u00c8\3\2\2\2\33\u00cd\3\2\2\2" +
          "\35\u00d2\3\2\2\2\37\u00d5\3\2\2\2!\u00db\3\2\2\2#\u00de\3\2\2\2%\u00e3" +
          "\3\2\2\2\'\u00e8\3\2\2\2)\u00ef\3\2\2\2+\u00f1\3\2\2\2-\u00f8\3\2\2\2" +
          "/\u00fa\3\2\2\2\61\u00fc\3\2\2\2\63\u00fe\3\2\2\2\65\u0106\3\2\2\2\67" +
          "\u010b\3\2\2\29\u010f\3\2\2\2;\u0113\3\2\2\2=\u0118\3\2\2\2?\u011d\3\2" +
          "\2\2A\u0123\3\2\2\2C\u0127\3\2\2\2E\u012c\3\2\2\2G\u0131\3\2\2\2I\u0138" +
          "\3\2\2\2K\u013c\3\2\2\2M\u013e\3\2\2\2O\u0142\3\2\2\2Q\u0146\3\2\2\2S" +
          "\u014a\3\2\2\2U\u014c\3\2\2\2W\u014e\3\2\2\2Y\u0150\3\2\2\2[\u0152\3\2" +
          "\2\2]\u0154\3\2\2\2_\u0156\3\2\2\2a\u0159\3\2\2\2c\u015b\3\2\2\2e\u015e" +
          "\3\2\2\2g\u0160\3\2\2\2i\u0163\3\2\2\2k\u0166\3\2\2\2m\u0169\3\2\2\2o" +
          "\u016c\3\2\2\2q\u016e\3\2\2\2s\u0170\3\2\2\2u\u0172\3\2\2\2w\u0174\3\2" +
          "\2\2y\u0176\3\2\2\2{\u0179\3\2\2\2}\u017d\3\2\2\2\177\u0180\3\2\2\2\u0081" +
          "\u0189\3\2\2\2\u0083\u0192\3\2\2\2\u0085\u0198\3\2\2\2\u0087\u019d\3\2" +
          "\2\2\u0089\u019f\3\2\2\2\u008b\u01a8\3\2\2\2\u008d\u01ac\3\2\2\2\u008f" +
          "\u0090\7d\2\2\u0090\u0091\7g\2\2\u0091\u0092\7i\2\2\u0092\u0093\7k\2\2" +
          "\u0093\u0094\7p\2\2\u0094\4\3\2\2\2\u0095\u0096\7g\2\2\u0096\u0097\7p" +
          "\2\2\u0097\u0098\7f\2\2\u0098\6\3\2\2\2\u0099\u009a\7k\2\2\u009a\u009b" +
          "\7u\2\2\u009b\b\3\2\2\2\u009c\u009d\7u\2\2\u009d\u009e\7m\2\2\u009e\u009f" +
          "\7k\2\2\u009f\u00a0\7r\2\2\u00a0\n\3\2\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3" +
          "\7g\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7f\2\2\u00a5\f\3\2\2\2\u00a6\u00a7" +
          "\7h\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7g\2\2\u00aa" +
          "\16\3\2\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7v\2\2\u00ae" +
          "\u00af\7w\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7p\2\2\u00b1\20\3\2\2\2\u00b2" +
          "\u00b3\7g\2\2\u00b3\u00b4\7z\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7v\2\2" +
          "\u00b6\22\3\2\2\2\u00b7\u00b8\7r\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7" +
          "k\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc\7v\2\2\u00bc\24\3\2\2\2\u00bd\u00be" +
          "\7r\2\2\u00be\u00bf\7t\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7p\2\2\u00c1" +
          "\u00c2\7v\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7p\2\2\u00c4\26\3\2\2\2\u00c5" +
          "\u00c6\7k\2\2\u00c6\u00c7\7h\2\2\u00c7\30\3\2\2\2\u00c8\u00c9\7v\2\2\u00c9" +
          "\u00ca\7j\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7p\2\2\u00cc\32\3\2\2\2\u00cd" +
          "\u00ce\7g\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0\7u\2\2\u00d0\u00d1\7g\2\2" +
          "\u00d1\34\3\2\2\2\u00d2\u00d3\7h\2\2\u00d3\u00d4\7k\2\2\u00d4\36\3\2\2" +
          "\2\u00d5\u00d6\7y\2\2\u00d6\u00d7\7j\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9" +
          "\7n\2\2\u00d9\u00da\7g\2\2\u00da \3\2\2\2\u00db\u00dc\7f\2\2\u00dc\u00dd" +
          "\7q\2\2\u00dd\"\3\2\2\2\u00de\u00df\7f\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1" +
          "\7p\2\2\u00e1\u00e2\7g\2\2\u00e2$\3\2\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5" +
          "\7w\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7n\2\2\u00e7&\3\2\2\2\u00e8\u00e9" +
          "\7u\2\2\u00e9\u00ea\7v\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7w\2\2\u00ec" +
          "\u00ed\7e\2\2\u00ed\u00ee\7v\2\2\u00ee(\3\2\2\2\u00ef\u00f0\7\60\2\2\u00f0" +
          "*\3\2\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7o\2\2\u00f3\u00f4\7r\2\2\u00f4" +
          "\u00f5\7q\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7v\2\2\u00f7,\3\2\2\2\u00f8" +
          "\u00f9\7?\2\2\u00f9.\3\2\2\2\u00fa\u00fb\7=\2\2\u00fb\60\3\2\2\2\u00fc" +
          "\u00fd\7.\2\2\u00fd\62\3\2\2\2\u00fe\u00ff\7p\2\2\u00ff\u0100\7g\2\2\u0100" +
          "\u0101\7y\2\2\u0101\u0102\7r\2\2\u0102\u0103\7c\2\2\u0103\u0104\7k\2\2" +
          "\u0104\u0105\7t\2\2\u0105\64\3\2\2\2\u0106\u0107\7e\2\2\u0107\u0108\7" +
          "c\2\2\u0108\u0109\7n\2\2\u0109\u010a\7n\2\2\u010a\66\3\2\2\2\u010b\u010c" +
          "\7h\2\2\u010c\u010d\7u\2\2\u010d\u010e\7v\2\2\u010e8\3\2\2\2\u010f\u0110" +
          "\7u\2\2\u0110\u0111\7p\2\2\u0111\u0112\7f\2\2\u0112:\3\2\2\2\u0113\u0114" +
          "\7r\2\2\u0114\u0115\7c\2\2\u0115\u0116\7k\2\2\u0116\u0117\7t\2\2\u0117" +
          "<\3\2\2\2\u0118\u0119\7v\2\2\u0119\u011a\7t\2\2\u011a\u011b\7w\2\2\u011b" +
          "\u011c\7g\2\2\u011c>\3\2\2\2\u011d\u011e\7h\2\2\u011e\u011f\7c\2\2\u011f" +
          "\u0120\7n\2\2\u0120\u0121\7u\2\2\u0121\u0122\7g\2\2\u0122@\3\2\2\2\u0123" +
          "\u0124\7k\2\2\u0124\u0125\7p\2\2\u0125\u0126\7v\2\2\u0126B\3\2\2\2\u0127" +
          "\u0128\7d\2\2\u0128\u0129\7q\2\2\u0129\u012a\7q\2\2\u012a\u012b\7n\2\2" +
          "\u012bD\3\2\2\2\u012c\u012d\7e\2\2\u012d\u012e\7j\2\2\u012e\u012f\7c\2" +
          "\2\u012f\u0130\7t\2\2\u0130F\3\2\2\2\u0131\u0132\7u\2\2\u0132\u0133\7" +
          "v\2\2\u0133\u0134\7t\2\2\u0134\u0135\7k\2\2\u0135\u0136\7p\2\2\u0136\u0137" +
          "\7i\2\2\u0137H\3\2\2\2\u0138\u0139\7x\2\2\u0139\u013a\7c\2\2\u013a\u013b" +
          "\7t\2\2\u013bJ\3\2\2\2\u013c\u013d\7#\2\2\u013dL\3\2\2\2\u013e\u013f\7" +
          "n\2\2\u013f\u0140\7g\2\2\u0140\u0141\7p\2\2\u0141N\3\2\2\2\u0142\u0143" +
          "\7q\2\2\u0143\u0144\7t\2\2\u0144\u0145\7f\2\2\u0145P\3\2\2\2\u0146\u0147" +
          "\7e\2\2\u0147\u0148\7j\2\2\u0148\u0149\7t\2\2\u0149R\3\2\2\2\u014a\u014b" +
          "\7(\2\2\u014bT\3\2\2\2\u014c\u014d\7-\2\2\u014dV\3\2\2\2\u014e\u014f\7" +
          "/\2\2\u014fX\3\2\2\2\u0150\u0151\7,\2\2\u0151Z\3\2\2\2\u0152\u0153\7\61" +
          "\2\2\u0153\\\3\2\2\2\u0154\u0155\7\'\2\2\u0155^\3\2\2\2\u0156\u0157\7" +
          "@\2\2\u0157\u0158\7?\2\2\u0158`\3\2\2\2\u0159\u015a\7@\2\2\u015ab\3\2" +
          "\2\2\u015b\u015c\7>\2\2\u015c\u015d\7?\2\2\u015dd\3\2\2\2\u015e\u015f" +
          "\7>\2\2\u015ff\3\2\2\2\u0160\u0161\7?\2\2\u0161\u0162\7?\2\2\u0162h\3" +
          "\2\2\2\u0163\u0164\7#\2\2\u0164\u0165\7?\2\2\u0165j\3\2\2\2\u0166\u0167" +
          "\7(\2\2\u0167\u0168\7(\2\2\u0168l\3\2\2\2\u0169\u016a\7~\2\2\u016a\u016b" +
          "\7~\2\2\u016bn\3\2\2\2\u016c\u016d\7*\2\2\u016dp\3\2\2\2\u016e\u016f\7" +
          "+\2\2\u016fr\3\2\2\2\u0170\u0171\7]\2\2\u0171t\3\2\2\2\u0172\u0173\7_" +
          "\2\2\u0173v\3\2\2\2\u0174\u0175\7}\2\2\u0175x\3\2\2\2\u0176\u0177\7\177" +
          "\2\2\u0177z\3\2\2\2\u0178\u017a\5}?\2\u0179\u0178\3\2\2\2\u017a\u017b" +
          "\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c|\3\2\2\2\u017d" +
          "\u017e\t\2\2\2\u017e~\3\2\2\2\u017f\u0181\t\3\2\2\u0180\u017f\3\2\2\2" +
          "\u0181\u0185\3\2\2\2\u0182\u0184\t\4\2\2\u0183\u0182\3\2\2\2\u0184\u0187" +
          "\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0080\3\2\2\2\u0187" +
          "\u0185\3\2\2\2\u0188\u018a\t\5\2\2\u0189\u0188\3\2\2\2\u018a\u018e\3\2" +
          "\2\2\u018b\u018d\t\4\2\2\u018c\u018b\3\2\2\2\u018d\u0190\3\2\2\2\u018e" +
          "\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0082\3\2\2\2\u0190\u018e\3\2" +
          "\2\2\u0191\u0193\t\6\2\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194" +
          "\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0197\bB" +
          "\2\2\u0197\u0084\3\2\2\2\u0198\u0199\t\7\2\2\u0199\u0086\3\2\2\2\u019a" +
          "\u019e\n\b\2\2\u019b\u019c\7^\2\2\u019c\u019e\5\u0085C\2\u019d\u019a\3" +
          "\2\2\2\u019d\u019b\3\2\2\2\u019e\u0088\3\2\2\2\u019f\u01a3\7$\2\2\u01a0" +
          "\u01a2\5\u0087D\2\u01a1\u01a0\3\2\2\2\u01a2\u01a5\3\2\2\2\u01a3\u01a1" +
          "\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a6\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a6" +
          "\u01a7\7$\2\2\u01a7\u008a\3\2\2\2\u01a8\u01a9\7)\2\2\u01a9\u01aa\5\u0087" +
          "D\2\u01aa\u01ab\7)\2\2\u01ab\u008c\3\2\2\2\u01ac\u01b0\7%\2\2\u01ad\u01af" +
          "\13\2\2\2\u01ae\u01ad\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0\u01b1\3\2\2\2" +
          "\u01b0\u01ae\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3\u01b4" +
          "\t\t\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\bG\2\2\u01b6\u008e\3\2\2\2\16" +
          "\2\u017b\u0180\u0183\u0185\u0189\u018c\u018e\u0194\u019d\u01a3\u01b0\3" +
          "\b\2\2";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}