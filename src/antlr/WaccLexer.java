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
			DONE = 17, NULL = 18, STRUCT = 19, ASSIGN = 20, SEMICOLON = 21, COMMA = 22, NEWPAIR = 23,
			CALL = 24, FST = 25, SND = 26, PAIR = 27, TRUE = 28, FALSE = 29, INT = 30, BOOL = 31,
			CHAR = 32, STRING = 33, VAR = 34, NOT = 35, LEN = 36, ORD = 37, CHR = 38, PLUS = 39, MINUS = 40,
			MULT = 41, DIV = 42, MOD = 43, GTE = 44, GT = 45, LTE = 46, LT = 47, EQ = 48, NEQ = 49,
			AND = 50, OR = 51, L_PAREN = 52, R_PAREN = 53, L_SQUARE = 54, R_SQUARE = 55, L_CURLY = 56,
			R_CURLY = 57, NUMBER = 58, IDENT = 59, WHITESPACE = 60, ESCAPE_CHARACTER = 61, CHARACTER = 62,
			STR_LITER = 63, CHAR_LITER = 64, COMMENT = 65;
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
				"NULL", "STRUCT", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL",
				"FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING",
				"VAR", "NOT", "LEN", "ORD", "CHR", "PLUS", "MINUS", "MULT", "DIV", "MOD",
				"GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN",
				"L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER", "DIGIT", "IDENT",
				"WHITESPACE", "ESCAPE_CHARACTER", "CHARACTER", "STR_LITER", "CHAR_LITER",
				"COMMENT"
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2C\u019b\b\1\4\2\t" +
					"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
					"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
					"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
					"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3" +
					"\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7" +
					"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3" +
					"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r" +
					"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3" +
					"\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3" +
					"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3" +
					"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3" +
					"\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3" +
					"\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3" +
					" \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3" +
					"$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3" +
					",\3,\3-\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3" +
					"\63\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\3" +
					"9\3:\3:\3;\6;\u0167\n;\r;\16;\u0168\3<\3<\3=\5=\u016e\n=\3=\7=\u0171\n" +
					"=\f=\16=\u0174\13=\3>\6>\u0177\n>\r>\16>\u0178\3>\3>\3?\3?\3@\3@\3@\5" +
					"@\u0182\n@\3A\3A\7A\u0186\nA\fA\16A\u0189\13A\3A\3A\3B\3B\3B\3B\3C\3C" +
					"\7C\u0193\nC\fC\16C\u0196\13C\3C\3C\3C\3C\3\u0194\2D\3\3\5\4\7\5\t\6\13" +
					"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'" +
					"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'" +
					"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w\2y={>}?\177" +
					"@\u0081A\u0083B\u0085C\3\2\t\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13" +
					"\f\17\17\"\"\13\2$$))\62\62^^ddhhppttvv\5\2$$))^^\4\2\f\f\17\17\2\u019f" +
					"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2" +
					"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2" +
					"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2" +
					"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2" +
					"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3" +
					"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2" +
					"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2" +
					"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3" +
					"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2" +
					"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2" +
					"}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2" +
					"\2\3\u0087\3\2\2\2\5\u008d\3\2\2\2\7\u0091\3\2\2\2\t\u0094\3\2\2\2\13" +
					"\u0099\3\2\2\2\r\u009e\3\2\2\2\17\u00a3\3\2\2\2\21\u00aa\3\2\2\2\23\u00af" +
					"\3\2\2\2\25\u00b5\3\2\2\2\27\u00bd\3\2\2\2\31\u00c0\3\2\2\2\33\u00c5\3" +
					"\2\2\2\35\u00ca\3\2\2\2\37\u00cd\3\2\2\2!\u00d3\3\2\2\2#\u00d6\3\2\2\2" +
					"%\u00db\3\2\2\2\'\u00e0\3\2\2\2)\u00e7\3\2\2\2+\u00e9\3\2\2\2-\u00eb\3" +
					"\2\2\2/\u00ed\3\2\2\2\61\u00f5\3\2\2\2\63\u00fa\3\2\2\2\65\u00fe\3\2\2" +
					"\2\67\u0102\3\2\2\29\u0107\3\2\2\2;\u010c\3\2\2\2=\u0112\3\2\2\2?\u0116" +
					"\3\2\2\2A\u011b\3\2\2\2C\u0120\3\2\2\2E\u0127\3\2\2\2G\u012b\3\2\2\2I" +
					"\u012d\3\2\2\2K\u0131\3\2\2\2M\u0135\3\2\2\2O\u0139\3\2\2\2Q\u013b\3\2" +
					"\2\2S\u013d\3\2\2\2U\u013f\3\2\2\2W\u0141\3\2\2\2Y\u0143\3\2\2\2[\u0146" +
					"\3\2\2\2]\u0148\3\2\2\2_\u014b\3\2\2\2a\u014d\3\2\2\2c\u0150\3\2\2\2e" +
					"\u0153\3\2\2\2g\u0156\3\2\2\2i\u0159\3\2\2\2k\u015b\3\2\2\2m\u015d\3\2" +
					"\2\2o\u015f\3\2\2\2q\u0161\3\2\2\2s\u0163\3\2\2\2u\u0166\3\2\2\2w\u016a" +
					"\3\2\2\2y\u016d\3\2\2\2{\u0176\3\2\2\2}\u017c\3\2\2\2\177\u0181\3\2\2" +
					"\2\u0081\u0183\3\2\2\2\u0083\u018c\3\2\2\2\u0085\u0190\3\2\2\2\u0087\u0088" +
					"\7d\2\2\u0088\u0089\7g\2\2\u0089\u008a\7i\2\2\u008a\u008b\7k\2\2\u008b" +
					"\u008c\7p\2\2\u008c\4\3\2\2\2\u008d\u008e\7g\2\2\u008e\u008f\7p\2\2\u008f" +
					"\u0090\7f\2\2\u0090\6\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7u\2\2\u0093" +
					"\b\3\2\2\2\u0094\u0095\7u\2\2\u0095\u0096\7m\2\2\u0096\u0097\7k\2\2\u0097" +
					"\u0098\7r\2\2\u0098\n\3\2\2\2\u0099\u009a\7t\2\2\u009a\u009b\7g\2\2\u009b" +
					"\u009c\7c\2\2\u009c\u009d\7f\2\2\u009d\f\3\2\2\2\u009e\u009f\7h\2\2\u009f" +
					"\u00a0\7t\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7g\2\2\u00a2\16\3\2\2\2\u00a3" +
					"\u00a4\7t\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7w\2\2" +
					"\u00a7\u00a8\7t\2\2\u00a8\u00a9\7p\2\2\u00a9\20\3\2\2\2\u00aa\u00ab\7" +
					"g\2\2\u00ab\u00ac\7z\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7v\2\2\u00ae\22" +
					"\3\2\2\2\u00af\u00b0\7r\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7k\2\2\u00b2" +
					"\u00b3\7p\2\2\u00b3\u00b4\7v\2\2\u00b4\24\3\2\2\2\u00b5\u00b6\7r\2\2\u00b6" +
					"\u00b7\7t\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7p\2\2\u00b9\u00ba\7v\2\2" +
					"\u00ba\u00bb\7n\2\2\u00bb\u00bc\7p\2\2\u00bc\26\3\2\2\2\u00bd\u00be\7" +
					"k\2\2\u00be\u00bf\7h\2\2\u00bf\30\3\2\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2" +
					"\7j\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7p\2\2\u00c4\32\3\2\2\2\u00c5\u00c6" +
					"\7g\2\2\u00c6\u00c7\7n\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7g\2\2\u00c9" +
					"\34\3\2\2\2\u00ca\u00cb\7h\2\2\u00cb\u00cc\7k\2\2\u00cc\36\3\2\2\2\u00cd" +
					"\u00ce\7y\2\2\u00ce\u00cf\7j\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7n\2\2" +
					"\u00d1\u00d2\7g\2\2\u00d2 \3\2\2\2\u00d3\u00d4\7f\2\2\u00d4\u00d5\7q\2" +
					"\2\u00d5\"\3\2\2\2\u00d6\u00d7\7f\2\2\u00d7\u00d8\7q\2\2\u00d8\u00d9\7" +
					"p\2\2\u00d9\u00da\7g\2\2\u00da$\3\2\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd" +
					"\7w\2\2\u00dd\u00de\7n\2\2\u00de\u00df\7n\2\2\u00df&\3\2\2\2\u00e0\u00e1" +
					"\7u\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\7w\2\2\u00e4" +
					"\u00e5\7e\2\2\u00e5\u00e6\7v\2\2\u00e6(\3\2\2\2\u00e7\u00e8\7?\2\2\u00e8" +
					"*\3\2\2\2\u00e9\u00ea\7=\2\2\u00ea,\3\2\2\2\u00eb\u00ec\7.\2\2\u00ec." +
					"\3\2\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7y\2\2\u00f0" +
					"\u00f1\7r\2\2\u00f1\u00f2\7c\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7t\2\2" +
					"\u00f4\60\3\2\2\2\u00f5\u00f6\7e\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7" +
					"n\2\2\u00f8\u00f9\7n\2\2\u00f9\62\3\2\2\2\u00fa\u00fb\7h\2\2\u00fb\u00fc" +
					"\7u\2\2\u00fc\u00fd\7v\2\2\u00fd\64\3\2\2\2\u00fe\u00ff\7u\2\2\u00ff\u0100" +
					"\7p\2\2\u0100\u0101\7f\2\2\u0101\66\3\2\2\2\u0102\u0103\7r\2\2\u0103\u0104" +
					"\7c\2\2\u0104\u0105\7k\2\2\u0105\u0106\7t\2\2\u01068\3\2\2\2\u0107\u0108" +
					"\7v\2\2\u0108\u0109\7t\2\2\u0109\u010a\7w\2\2\u010a\u010b\7g\2\2\u010b" +
					":\3\2\2\2\u010c\u010d\7h\2\2\u010d\u010e\7c\2\2\u010e\u010f\7n\2\2\u010f" +
					"\u0110\7u\2\2\u0110\u0111\7g\2\2\u0111<\3\2\2\2\u0112\u0113\7k\2\2\u0113" +
					"\u0114\7p\2\2\u0114\u0115\7v\2\2\u0115>\3\2\2\2\u0116\u0117\7d\2\2\u0117" +
					"\u0118\7q\2\2\u0118\u0119\7q\2\2\u0119\u011a\7n\2\2\u011a@\3\2\2\2\u011b" +
					"\u011c\7e\2\2\u011c\u011d\7j\2\2\u011d\u011e\7c\2\2\u011e\u011f\7t\2\2" +
					"\u011fB\3\2\2\2\u0120\u0121\7u\2\2\u0121\u0122\7v\2\2\u0122\u0123\7t\2" +
					"\2\u0123\u0124\7k\2\2\u0124\u0125\7p\2\2\u0125\u0126\7i\2\2\u0126D\3\2" +
					"\2\2\u0127\u0128\7x\2\2\u0128\u0129\7c\2\2\u0129\u012a\7t\2\2\u012aF\3" +
					"\2\2\2\u012b\u012c\7#\2\2\u012cH\3\2\2\2\u012d\u012e\7n\2\2\u012e\u012f" +
					"\7g\2\2\u012f\u0130\7p\2\2\u0130J\3\2\2\2\u0131\u0132\7q\2\2\u0132\u0133" +
					"\7t\2\2\u0133\u0134\7f\2\2\u0134L\3\2\2\2\u0135\u0136\7e\2\2\u0136\u0137" +
					"\7j\2\2\u0137\u0138\7t\2\2\u0138N\3\2\2\2\u0139\u013a\7-\2\2\u013aP\3" +
					"\2\2\2\u013b\u013c\7/\2\2\u013cR\3\2\2\2\u013d\u013e\7,\2\2\u013eT\3\2" +
					"\2\2\u013f\u0140\7\61\2\2\u0140V\3\2\2\2\u0141\u0142\7\'\2\2\u0142X\3" +
					"\2\2\2\u0143\u0144\7@\2\2\u0144\u0145\7?\2\2\u0145Z\3\2\2\2\u0146\u0147" +
					"\7@\2\2\u0147\\\3\2\2\2\u0148\u0149\7>\2\2\u0149\u014a\7?\2\2\u014a^\3" +
					"\2\2\2\u014b\u014c\7>\2\2\u014c`\3\2\2\2\u014d\u014e\7?\2\2\u014e\u014f" +
					"\7?\2\2\u014fb\3\2\2\2\u0150\u0151\7#\2\2\u0151\u0152\7?\2\2\u0152d\3" +
					"\2\2\2\u0153\u0154\7(\2\2\u0154\u0155\7(\2\2\u0155f\3\2\2\2\u0156\u0157" +
					"\7~\2\2\u0157\u0158\7~\2\2\u0158h\3\2\2\2\u0159\u015a\7*\2\2\u015aj\3" +
					"\2\2\2\u015b\u015c\7+\2\2\u015cl\3\2\2\2\u015d\u015e\7]\2\2\u015en\3\2" +
					"\2\2\u015f\u0160\7_\2\2\u0160p\3\2\2\2\u0161\u0162\7}\2\2\u0162r\3\2\2" +
					"\2\u0163\u0164\7\177\2\2\u0164t\3\2\2\2\u0165\u0167\5w<\2\u0166\u0165" +
					"\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169" +
					"v\3\2\2\2\u016a\u016b\t\2\2\2\u016bx\3\2\2\2\u016c\u016e\t\3\2\2\u016d" +
					"\u016c\3\2\2\2\u016e\u0172\3\2\2\2\u016f\u0171\t\4\2\2\u0170\u016f\3\2" +
					"\2\2\u0171\u0174\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173" +
					"z\3\2\2\2\u0174\u0172\3\2\2\2\u0175\u0177\t\5\2\2\u0176\u0175\3\2\2\2" +
					"\u0177\u0178\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a" +
					"\3\2\2\2\u017a\u017b\b>\2\2\u017b|\3\2\2\2\u017c\u017d\t\6\2\2\u017d~" +
					"\3\2\2\2\u017e\u0182\n\7\2\2\u017f\u0180\7^\2\2\u0180\u0182\5}?\2\u0181" +
					"\u017e\3\2\2\2\u0181\u017f\3\2\2\2\u0182\u0080\3\2\2\2\u0183\u0187\7$" +
					"\2\2\u0184\u0186\5\177@\2\u0185\u0184\3\2\2\2\u0186\u0189\3\2\2\2\u0187" +
					"\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018a\3\2\2\2\u0189\u0187\3\2" +
					"\2\2\u018a\u018b\7$\2\2\u018b\u0082\3\2\2\2\u018c\u018d\7)\2\2\u018d\u018e" +
					"\5\177@\2\u018e\u018f\7)\2\2\u018f\u0084\3\2\2\2\u0190\u0194\7%\2\2\u0191" +
					"\u0193\13\2\2\2\u0192\u0191\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0195\3" +
					"\2\2\2\u0194\u0192\3\2\2\2\u0195\u0197\3\2\2\2\u0196\u0194\3\2\2\2\u0197" +
					"\u0198\t\b\2\2\u0198\u0199\3\2\2\2\u0199\u019a\bC\2\2\u019a\u0086\3\2" +
					"\2\2\13\2\u0168\u016d\u0170\u0172\u0178\u0181\u0187\u0194\3\b\2\2";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}