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
      IDENT = 63, WHITESPACE = 64, ESCAPE_CHARACTER = 65, CHARACTER = 66, FILENAME = 67,
      STR_LITER = 68, CHAR_LITER = 69, COMMENT = 70;
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
        "CHARACTER", "FILENAME", "STR_LITER", "CHAR_LITER", "COMMENT"
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
        "FILENAME", "STR_LITER", "CHAR_LITER", "COMMENT"
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
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "WaccLexer.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getChannelNames() {
    return channelNames;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2H\u01c2\b\1\4\2\t" +
          "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
          "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
          "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
          "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
          "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
          ",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
          "\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
          "\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\3\2" +
          "\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3" +
          "\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t" +
          "\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13" +
          "\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3" +
          "\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3" +
          "\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3" +
          "\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3" +
          "\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3" +
          "\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3" +
          "\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"" +
          "\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'\3\'" +
          "\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60" +
          "\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65" +
          "\3\65\3\66\3\66\3\66\3\67\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3" +
          "=\3>\6>\u017c\n>\r>\16>\u017d\3?\3?\3@\5@\u0183\n@\3@\7@\u0186\n@\f@\16" +
          "@\u0189\13@\3A\5A\u018c\nA\3A\7A\u018f\nA\fA\16A\u0192\13A\3B\6B\u0195" +
          "\nB\rB\16B\u0196\3B\3B\3C\3C\3D\3D\3D\5D\u01a0\nD\3E\5E\u01a3\nE\3E\7" +
          "E\u01a6\nE\fE\16E\u01a9\13E\3F\3F\7F\u01ad\nF\fF\16F\u01b0\13F\3F\3F\3" +
          "G\3G\3G\3G\3H\3H\7H\u01ba\nH\fH\16H\u01bd\13H\3H\3H\3H\3H\3\u01bb\2I\3" +
          "\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37" +
          "\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37=" +
          " ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9" +
          "q:s;u<w=y>{?}\2\177@\u0081A\u0083B\u0085C\u0087D\u0089E\u008bF\u008dG" +
          "\u008fH\3\2\13\3\2\62;\4\2C\\aa\6\2\62;C\\aac|\5\2C\\aac|\5\2\13\f\17" +
          "\17\"\"\13\2$$))\62\62^^ddhhppttvv\5\2$$))^^\7\2\60\60\62;C\\aac|\4\2" +
          "\f\f\17\17\2\u01c8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13" +
          "\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2" +
          "\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2" +
          "!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3" +
          "\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2" +
          "\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E" +
          "\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2" +
          "\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2" +
          "\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k" +
          "\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2" +
          "\2\2\2y\3\2\2\2\2{\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2" +
          "\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d" +
          "\3\2\2\2\2\u008f\3\2\2\2\3\u0091\3\2\2\2\5\u0097\3\2\2\2\7\u009b\3\2\2" +
          "\2\t\u009e\3\2\2\2\13\u00a3\3\2\2\2\r\u00a8\3\2\2\2\17\u00ad\3\2\2\2\21" +
          "\u00b4\3\2\2\2\23\u00b9\3\2\2\2\25\u00bf\3\2\2\2\27\u00c7\3\2\2\2\31\u00ca" +
          "\3\2\2\2\33\u00cf\3\2\2\2\35\u00d4\3\2\2\2\37\u00d7\3\2\2\2!\u00dd\3\2" +
          "\2\2#\u00e0\3\2\2\2%\u00e5\3\2\2\2\'\u00ea\3\2\2\2)\u00f1\3\2\2\2+\u00f3" +
          "\3\2\2\2-\u00fa\3\2\2\2/\u00fc\3\2\2\2\61\u00fe\3\2\2\2\63\u0100\3\2\2" +
          "\2\65\u0108\3\2\2\2\67\u010d\3\2\2\29\u0111\3\2\2\2;\u0115\3\2\2\2=\u011a" +
          "\3\2\2\2?\u011f\3\2\2\2A\u0125\3\2\2\2C\u0129\3\2\2\2E\u012e\3\2\2\2G" +
          "\u0133\3\2\2\2I\u013a\3\2\2\2K\u013e\3\2\2\2M\u0140\3\2\2\2O\u0144\3\2" +
          "\2\2Q\u0148\3\2\2\2S\u014c\3\2\2\2U\u014e\3\2\2\2W\u0150\3\2\2\2Y\u0152" +
          "\3\2\2\2[\u0154\3\2\2\2]\u0156\3\2\2\2_\u0158\3\2\2\2a\u015b\3\2\2\2c" +
          "\u015d\3\2\2\2e\u0160\3\2\2\2g\u0162\3\2\2\2i\u0165\3\2\2\2k\u0168\3\2" +
          "\2\2m\u016b\3\2\2\2o\u016e\3\2\2\2q\u0170\3\2\2\2s\u0172\3\2\2\2u\u0174" +
          "\3\2\2\2w\u0176\3\2\2\2y\u0178\3\2\2\2{\u017b\3\2\2\2}\u017f\3\2\2\2\177" +
          "\u0182\3\2\2\2\u0081\u018b\3\2\2\2\u0083\u0194\3\2\2\2\u0085\u019a\3\2" +
          "\2\2\u0087\u019f\3\2\2\2\u0089\u01a2\3\2\2\2\u008b\u01aa\3\2\2\2\u008d" +
          "\u01b3\3\2\2\2\u008f\u01b7\3\2\2\2\u0091\u0092\7d\2\2\u0092\u0093\7g\2" +
          "\2\u0093\u0094\7i\2\2\u0094\u0095\7k\2\2\u0095\u0096\7p\2\2\u0096\4\3" +
          "\2\2\2\u0097\u0098\7g\2\2\u0098\u0099\7p\2\2\u0099\u009a\7f\2\2\u009a" +
          "\6\3\2\2\2\u009b\u009c\7k\2\2\u009c\u009d\7u\2\2\u009d\b\3\2\2\2\u009e" +
          "\u009f\7u\2\2\u009f\u00a0\7m\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7r\2\2" +
          "\u00a2\n\3\2\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7c" +
          "\2\2\u00a6\u00a7\7f\2\2\u00a7\f\3\2\2\2\u00a8\u00a9\7h\2\2\u00a9\u00aa" +
          "\7t\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7g\2\2\u00ac\16\3\2\2\2\u00ad\u00ae" +
          "\7t\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7w\2\2\u00b1" +
          "\u00b2\7t\2\2\u00b2\u00b3\7p\2\2\u00b3\20\3\2\2\2\u00b4\u00b5\7g\2\2\u00b5" +
          "\u00b6\7z\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7v\2\2\u00b8\22\3\2\2\2\u00b9" +
          "\u00ba\7r\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7p\2\2" +
          "\u00bd\u00be\7v\2\2\u00be\24\3\2\2\2\u00bf\u00c0\7r\2\2\u00c0\u00c1\7" +
          "t\2\2\u00c1\u00c2\7k\2\2\u00c2\u00c3\7p\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5" +
          "\7n\2\2\u00c5\u00c6\7p\2\2\u00c6\26\3\2\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9" +
          "\7h\2\2\u00c9\30\3\2\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc\7j\2\2\u00cc\u00cd" +
          "\7g\2\2\u00cd\u00ce\7p\2\2\u00ce\32\3\2\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1" +
          "\7n\2\2\u00d1\u00d2\7u\2\2\u00d2\u00d3\7g\2\2\u00d3\34\3\2\2\2\u00d4\u00d5" +
          "\7h\2\2\u00d5\u00d6\7k\2\2\u00d6\36\3\2\2\2\u00d7\u00d8\7y\2\2\u00d8\u00d9" +
          "\7j\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7n\2\2\u00db\u00dc\7g\2\2\u00dc" +
          " \3\2\2\2\u00dd\u00de\7f\2\2\u00de\u00df\7q\2\2\u00df\"\3\2\2\2\u00e0" +
          "\u00e1\7f\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7g\2\2" +
          "\u00e4$\3\2\2\2\u00e5\u00e6\7p\2\2\u00e6\u00e7\7w\2\2\u00e7\u00e8\7n\2" +
          "\2\u00e8\u00e9\7n\2\2\u00e9&\3\2\2\2\u00ea\u00eb\7u\2\2\u00eb\u00ec\7" +
          "v\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7w\2\2\u00ee\u00ef\7e\2\2\u00ef\u00f0" +
          "\7v\2\2\u00f0(\3\2\2\2\u00f1\u00f2\7\60\2\2\u00f2*\3\2\2\2\u00f3\u00f4" +
          "\7k\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7q\2\2\u00f7" +
          "\u00f8\7t\2\2\u00f8\u00f9\7v\2\2\u00f9,\3\2\2\2\u00fa\u00fb\7?\2\2\u00fb" +
          ".\3\2\2\2\u00fc\u00fd\7=\2\2\u00fd\60\3\2\2\2\u00fe\u00ff\7.\2\2\u00ff" +
          "\62\3\2\2\2\u0100\u0101\7p\2\2\u0101\u0102\7g\2\2\u0102\u0103\7y\2\2\u0103" +
          "\u0104\7r\2\2\u0104\u0105\7c\2\2\u0105\u0106\7k\2\2\u0106\u0107\7t\2\2" +
          "\u0107\64\3\2\2\2\u0108\u0109\7e\2\2\u0109\u010a\7c\2\2\u010a\u010b\7" +
          "n\2\2\u010b\u010c\7n\2\2\u010c\66\3\2\2\2\u010d\u010e\7h\2\2\u010e\u010f" +
          "\7u\2\2\u010f\u0110\7v\2\2\u01108\3\2\2\2\u0111\u0112\7u\2\2\u0112\u0113" +
          "\7p\2\2\u0113\u0114\7f\2\2\u0114:\3\2\2\2\u0115\u0116\7r\2\2\u0116\u0117" +
          "\7c\2\2\u0117\u0118\7k\2\2\u0118\u0119\7t\2\2\u0119<\3\2\2\2\u011a\u011b" +
          "\7v\2\2\u011b\u011c\7t\2\2\u011c\u011d\7w\2\2\u011d\u011e\7g\2\2\u011e" +
          ">\3\2\2\2\u011f\u0120\7h\2\2\u0120\u0121\7c\2\2\u0121\u0122\7n\2\2\u0122" +
          "\u0123\7u\2\2\u0123\u0124\7g\2\2\u0124@\3\2\2\2\u0125\u0126\7k\2\2\u0126" +
          "\u0127\7p\2\2\u0127\u0128\7v\2\2\u0128B\3\2\2\2\u0129\u012a\7d\2\2\u012a" +
          "\u012b\7q\2\2\u012b\u012c\7q\2\2\u012c\u012d\7n\2\2\u012dD\3\2\2\2\u012e" +
          "\u012f\7e\2\2\u012f\u0130\7j\2\2\u0130\u0131\7c\2\2\u0131\u0132\7t\2\2" +
          "\u0132F\3\2\2\2\u0133\u0134\7u\2\2\u0134\u0135\7v\2\2\u0135\u0136\7t\2" +
          "\2\u0136\u0137\7k\2\2\u0137\u0138\7p\2\2\u0138\u0139\7i\2\2\u0139H\3\2" +
          "\2\2\u013a\u013b\7x\2\2\u013b\u013c\7c\2\2\u013c\u013d\7t\2\2\u013dJ\3" +
          "\2\2\2\u013e\u013f\7#\2\2\u013fL\3\2\2\2\u0140\u0141\7n\2\2\u0141\u0142" +
          "\7g\2\2\u0142\u0143\7p\2\2\u0143N\3\2\2\2\u0144\u0145\7q\2\2\u0145\u0146" +
          "\7t\2\2\u0146\u0147\7f\2\2\u0147P\3\2\2\2\u0148\u0149\7e\2\2\u0149\u014a" +
          "\7j\2\2\u014a\u014b\7t\2\2\u014bR\3\2\2\2\u014c\u014d\7(\2\2\u014dT\3" +
          "\2\2\2\u014e\u014f\7-\2\2\u014fV\3\2\2\2\u0150\u0151\7/\2\2\u0151X\3\2" +
          "\2\2\u0152\u0153\7,\2\2\u0153Z\3\2\2\2\u0154\u0155\7\61\2\2\u0155\\\3" +
          "\2\2\2\u0156\u0157\7\'\2\2\u0157^\3\2\2\2\u0158\u0159\7@\2\2\u0159\u015a" +
          "\7?\2\2\u015a`\3\2\2\2\u015b\u015c\7@\2\2\u015cb\3\2\2\2\u015d\u015e\7" +
          ">\2\2\u015e\u015f\7?\2\2\u015fd\3\2\2\2\u0160\u0161\7>\2\2\u0161f\3\2" +
          "\2\2\u0162\u0163\7?\2\2\u0163\u0164\7?\2\2\u0164h\3\2\2\2\u0165\u0166" +
          "\7#\2\2\u0166\u0167\7?\2\2\u0167j\3\2\2\2\u0168\u0169\7(\2\2\u0169\u016a" +
          "\7(\2\2\u016al\3\2\2\2\u016b\u016c\7~\2\2\u016c\u016d\7~\2\2\u016dn\3" +
          "\2\2\2\u016e\u016f\7*\2\2\u016fp\3\2\2\2\u0170\u0171\7+\2\2\u0171r\3\2" +
          "\2\2\u0172\u0173\7]\2\2\u0173t\3\2\2\2\u0174\u0175\7_\2\2\u0175v\3\2\2" +
          "\2\u0176\u0177\7}\2\2\u0177x\3\2\2\2\u0178\u0179\7\177\2\2\u0179z\3\2" +
          "\2\2\u017a\u017c\5}?\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b" +
          "\3\2\2\2\u017d\u017e\3\2\2\2\u017e|\3\2\2\2\u017f\u0180\t\2\2\2\u0180" +
          "~\3\2\2\2\u0181\u0183\t\3\2\2\u0182\u0181\3\2\2\2\u0183\u0187\3\2\2\2" +
          "\u0184\u0186\t\4\2\2\u0185\u0184\3\2\2\2\u0186\u0189\3\2\2\2\u0187\u0185" +
          "\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0080\3\2\2\2\u0189\u0187\3\2\2\2\u018a" +
          "\u018c\t\5\2\2\u018b\u018a\3\2\2\2\u018c\u0190\3\2\2\2\u018d\u018f\t\4" +
          "\2\2\u018e\u018d\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190" +
          "\u0191\3\2\2\2\u0191\u0082\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0195\t\6" +
          "\2\2\u0194\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0194\3\2\2\2\u0196" +
          "\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\bB\2\2\u0199\u0084\3\2" +
          "\2\2\u019a\u019b\t\7\2\2\u019b\u0086\3\2\2\2\u019c\u01a0\n\b\2\2\u019d" +
          "\u019e\7^\2\2\u019e\u01a0\5\u0085C\2\u019f\u019c\3\2\2\2\u019f\u019d\3" +
          "\2\2\2\u01a0\u0088\3\2\2\2\u01a1\u01a3\t\5\2\2\u01a2\u01a1\3\2\2\2\u01a3" +
          "\u01a7\3\2\2\2\u01a4\u01a6\t\t\2\2\u01a5\u01a4\3\2\2\2\u01a6\u01a9\3\2" +
          "\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u008a\3\2\2\2\u01a9" +
          "\u01a7\3\2\2\2\u01aa\u01ae\7$\2\2\u01ab\u01ad\5\u0087D\2\u01ac\u01ab\3" +
          "\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af" +
          "\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b2\7$\2\2\u01b2\u008c\3\2" +
          "\2\2\u01b3\u01b4\7)\2\2\u01b4\u01b5\5\u0087D\2\u01b5\u01b6\7)\2\2\u01b6" +
          "\u008e\3\2\2\2\u01b7\u01bb\7%\2\2\u01b8\u01ba\13\2\2\2\u01b9\u01b8\3\2" +
          "\2\2\u01ba\u01bd\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc" +
          "\u01be\3\2\2\2\u01bd\u01bb\3\2\2\2\u01be\u01bf\t\n\2\2\u01bf\u01c0\3\2" +
          "\2\2\u01c0\u01c1\bH\2\2\u01c1\u0090\3\2\2\2\21\2\u017d\u0182\u0185\u0187" +
          "\u018b\u018e\u0190\u0196\u019f\u01a2\u01a5\u01a7\u01ae\u01bb\3\b\2\2";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}