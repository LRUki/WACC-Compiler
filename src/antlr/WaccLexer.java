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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, IS=3, SKIP_TOKEN=4, READ=5, FREE=6, RETURN=7, EXIT=8, 
		PRINT=9, PRINTLN=10, IF=11, THEN=12, ELSE=13, FI=14, WHILE=15, DO=16, 
		DONE=17, NULL=18, ASSIGN=19, SEMICOLON=20, COMMA=21, NEWPAIR=22, CALL=23, 
		FST=24, SND=25, PAIR=26, TRUE=27, FALSE=28, INT=29, BOOL=30, CHAR=31, 
		STRING=32, VAR=33, NOT=34, LEN=35, ORD=36, CHR=37, PLUS=38, MINUS=39, 
		MULT=40, DIV=41, MOD=42, GTE=43, GT=44, LTE=45, LT=46, EQ=47, NEQ=48, 
		AND=49, OR=50, L_PAREN=51, R_PAREN=52, L_SQUARE=53, R_SQUARE=54, L_CURLY=55, 
		R_CURLY=56, NUMBER=57, IDENT=58, WHITESPACE=59, ESCAPE_CHARACTER=60, CHARACTER=61, 
		STR_LITER=62, CHAR_LITER=63, COMMENT=64;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"NULL", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL", "FST", "SND", 
			"PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING", "VAR", "NOT", 
			"LEN", "ORD", "CHR", "PLUS", "MINUS", "MULT", "DIV", "MOD", "GTE", "GT", 
			"LTE", "LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE", 
			"R_SQUARE", "L_CURLY", "R_CURLY", "NUMBER", "DIGIT", "IDENT", "WHITESPACE", 
			"ESCAPE_CHARACTER", "CHARACTER", "STR_LITER", "CHAR_LITER", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'", 
			"'while'", "'do'", "'done'", "'null'", "'='", "';'", "','", "'newpair'", 
			"'call'", "'fst'", "'snd'", "'pair'", "'true'", "'false'", "'int'", "'bool'", 
			"'char'", "'string'", "'var'", "'!'", "'len'", "'ord'", "'chr'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'>='", "'>'", "'<='", "'<'", "'=='", "'!='", 
			"'&&'", "'||'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"NULL", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL", "FST", "SND", 
			"PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING", "VAR", "NOT", 
			"LEN", "ORD", "CHR", "PLUS", "MINUS", "MULT", "DIV", "MOD", "GTE", "GT", 
			"LTE", "LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2B\u0192\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3"+
		"!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3"+
		"&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3\60"+
		"\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\6:\u015e\n:\r:\16:\u015f\3;\3"+
		";\3<\5<\u0165\n<\3<\7<\u0168\n<\f<\16<\u016b\13<\3=\6=\u016e\n=\r=\16"+
		"=\u016f\3=\3=\3>\3>\3?\3?\3?\5?\u0179\n?\3@\3@\7@\u017d\n@\f@\16@\u0180"+
		"\13@\3@\3@\3A\3A\3A\3A\3B\3B\7B\u018a\nB\fB\16B\u018d\13B\3B\3B\3B\3B"+
		"\3\u018b\2C\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65"+
		"i\66k\67m8o9q:s;u\2w<y={>}?\177@\u0081A\u0083B\3\2\t\3\2\62;\5\2C\\aa"+
		"c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\13\2$$))\62\62^^ddhhppttvv\5\2$$"+
		"))^^\4\2\f\f\17\17\2\u0196\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]"+
		"\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2"+
		"\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2w\3\2\2\2"+
		"\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083"+
		"\3\2\2\2\3\u0085\3\2\2\2\5\u008b\3\2\2\2\7\u008f\3\2\2\2\t\u0092\3\2\2"+
		"\2\13\u0097\3\2\2\2\r\u009c\3\2\2\2\17\u00a1\3\2\2\2\21\u00a8\3\2\2\2"+
		"\23\u00ad\3\2\2\2\25\u00b3\3\2\2\2\27\u00bb\3\2\2\2\31\u00be\3\2\2\2\33"+
		"\u00c3\3\2\2\2\35\u00c8\3\2\2\2\37\u00cb\3\2\2\2!\u00d1\3\2\2\2#\u00d4"+
		"\3\2\2\2%\u00d9\3\2\2\2\'\u00de\3\2\2\2)\u00e0\3\2\2\2+\u00e2\3\2\2\2"+
		"-\u00e4\3\2\2\2/\u00ec\3\2\2\2\61\u00f1\3\2\2\2\63\u00f5\3\2\2\2\65\u00f9"+
		"\3\2\2\2\67\u00fe\3\2\2\29\u0103\3\2\2\2;\u0109\3\2\2\2=\u010d\3\2\2\2"+
		"?\u0112\3\2\2\2A\u0117\3\2\2\2C\u011e\3\2\2\2E\u0122\3\2\2\2G\u0124\3"+
		"\2\2\2I\u0128\3\2\2\2K\u012c\3\2\2\2M\u0130\3\2\2\2O\u0132\3\2\2\2Q\u0134"+
		"\3\2\2\2S\u0136\3\2\2\2U\u0138\3\2\2\2W\u013a\3\2\2\2Y\u013d\3\2\2\2["+
		"\u013f\3\2\2\2]\u0142\3\2\2\2_\u0144\3\2\2\2a\u0147\3\2\2\2c\u014a\3\2"+
		"\2\2e\u014d\3\2\2\2g\u0150\3\2\2\2i\u0152\3\2\2\2k\u0154\3\2\2\2m\u0156"+
		"\3\2\2\2o\u0158\3\2\2\2q\u015a\3\2\2\2s\u015d\3\2\2\2u\u0161\3\2\2\2w"+
		"\u0164\3\2\2\2y\u016d\3\2\2\2{\u0173\3\2\2\2}\u0178\3\2\2\2\177\u017a"+
		"\3\2\2\2\u0081\u0183\3\2\2\2\u0083\u0187\3\2\2\2\u0085\u0086\7d\2\2\u0086"+
		"\u0087\7g\2\2\u0087\u0088\7i\2\2\u0088\u0089\7k\2\2\u0089\u008a\7p\2\2"+
		"\u008a\4\3\2\2\2\u008b\u008c\7g\2\2\u008c\u008d\7p\2\2\u008d\u008e\7f"+
		"\2\2\u008e\6\3\2\2\2\u008f\u0090\7k\2\2\u0090\u0091\7u\2\2\u0091\b\3\2"+
		"\2\2\u0092\u0093\7u\2\2\u0093\u0094\7m\2\2\u0094\u0095\7k\2\2\u0095\u0096"+
		"\7r\2\2\u0096\n\3\2\2\2\u0097\u0098\7t\2\2\u0098\u0099\7g\2\2\u0099\u009a"+
		"\7c\2\2\u009a\u009b\7f\2\2\u009b\f\3\2\2\2\u009c\u009d\7h\2\2\u009d\u009e"+
		"\7t\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7g\2\2\u00a0\16\3\2\2\2\u00a1\u00a2"+
		"\7t\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7w\2\2\u00a5"+
		"\u00a6\7t\2\2\u00a6\u00a7\7p\2\2\u00a7\20\3\2\2\2\u00a8\u00a9\7g\2\2\u00a9"+
		"\u00aa\7z\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7v\2\2\u00ac\22\3\2\2\2\u00ad"+
		"\u00ae\7r\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7p\2\2"+
		"\u00b1\u00b2\7v\2\2\u00b2\24\3\2\2\2\u00b3\u00b4\7r\2\2\u00b4\u00b5\7"+
		"t\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7p\2\2\u00b7\u00b8\7v\2\2\u00b8\u00b9"+
		"\7n\2\2\u00b9\u00ba\7p\2\2\u00ba\26\3\2\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd"+
		"\7h\2\2\u00bd\30\3\2\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7j\2\2\u00c0\u00c1"+
		"\7g\2\2\u00c1\u00c2\7p\2\2\u00c2\32\3\2\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5"+
		"\7n\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7g\2\2\u00c7\34\3\2\2\2\u00c8\u00c9"+
		"\7h\2\2\u00c9\u00ca\7k\2\2\u00ca\36\3\2\2\2\u00cb\u00cc\7y\2\2\u00cc\u00cd"+
		"\7j\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0\7g\2\2\u00d0"+
		" \3\2\2\2\u00d1\u00d2\7f\2\2\u00d2\u00d3\7q\2\2\u00d3\"\3\2\2\2\u00d4"+
		"\u00d5\7f\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7p\2\2\u00d7\u00d8\7g\2\2"+
		"\u00d8$\3\2\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7w\2\2\u00db\u00dc\7n\2"+
		"\2\u00dc\u00dd\7n\2\2\u00dd&\3\2\2\2\u00de\u00df\7?\2\2\u00df(\3\2\2\2"+
		"\u00e0\u00e1\7=\2\2\u00e1*\3\2\2\2\u00e2\u00e3\7.\2\2\u00e3,\3\2\2\2\u00e4"+
		"\u00e5\7p\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7y\2\2\u00e7\u00e8\7r\2\2"+
		"\u00e8\u00e9\7c\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7t\2\2\u00eb.\3\2\2"+
		"\2\u00ec\u00ed\7e\2\2\u00ed\u00ee\7c\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f0"+
		"\7n\2\2\u00f0\60\3\2\2\2\u00f1\u00f2\7h\2\2\u00f2\u00f3\7u\2\2\u00f3\u00f4"+
		"\7v\2\2\u00f4\62\3\2\2\2\u00f5\u00f6\7u\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8"+
		"\7f\2\2\u00f8\64\3\2\2\2\u00f9\u00fa\7r\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc"+
		"\7k\2\2\u00fc\u00fd\7t\2\2\u00fd\66\3\2\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100"+
		"\7t\2\2\u0100\u0101\7w\2\2\u0101\u0102\7g\2\2\u01028\3\2\2\2\u0103\u0104"+
		"\7h\2\2\u0104\u0105\7c\2\2\u0105\u0106\7n\2\2\u0106\u0107\7u\2\2\u0107"+
		"\u0108\7g\2\2\u0108:\3\2\2\2\u0109\u010a\7k\2\2\u010a\u010b\7p\2\2\u010b"+
		"\u010c\7v\2\2\u010c<\3\2\2\2\u010d\u010e\7d\2\2\u010e\u010f\7q\2\2\u010f"+
		"\u0110\7q\2\2\u0110\u0111\7n\2\2\u0111>\3\2\2\2\u0112\u0113\7e\2\2\u0113"+
		"\u0114\7j\2\2\u0114\u0115\7c\2\2\u0115\u0116\7t\2\2\u0116@\3\2\2\2\u0117"+
		"\u0118\7u\2\2\u0118\u0119\7v\2\2\u0119\u011a\7t\2\2\u011a\u011b\7k\2\2"+
		"\u011b\u011c\7p\2\2\u011c\u011d\7i\2\2\u011dB\3\2\2\2\u011e\u011f\7x\2"+
		"\2\u011f\u0120\7c\2\2\u0120\u0121\7t\2\2\u0121D\3\2\2\2\u0122\u0123\7"+
		"#\2\2\u0123F\3\2\2\2\u0124\u0125\7n\2\2\u0125\u0126\7g\2\2\u0126\u0127"+
		"\7p\2\2\u0127H\3\2\2\2\u0128\u0129\7q\2\2\u0129\u012a\7t\2\2\u012a\u012b"+
		"\7f\2\2\u012bJ\3\2\2\2\u012c\u012d\7e\2\2\u012d\u012e\7j\2\2\u012e\u012f"+
		"\7t\2\2\u012fL\3\2\2\2\u0130\u0131\7-\2\2\u0131N\3\2\2\2\u0132\u0133\7"+
		"/\2\2\u0133P\3\2\2\2\u0134\u0135\7,\2\2\u0135R\3\2\2\2\u0136\u0137\7\61"+
		"\2\2\u0137T\3\2\2\2\u0138\u0139\7\'\2\2\u0139V\3\2\2\2\u013a\u013b\7@"+
		"\2\2\u013b\u013c\7?\2\2\u013cX\3\2\2\2\u013d\u013e\7@\2\2\u013eZ\3\2\2"+
		"\2\u013f\u0140\7>\2\2\u0140\u0141\7?\2\2\u0141\\\3\2\2\2\u0142\u0143\7"+
		">\2\2\u0143^\3\2\2\2\u0144\u0145\7?\2\2\u0145\u0146\7?\2\2\u0146`\3\2"+
		"\2\2\u0147\u0148\7#\2\2\u0148\u0149\7?\2\2\u0149b\3\2\2\2\u014a\u014b"+
		"\7(\2\2\u014b\u014c\7(\2\2\u014cd\3\2\2\2\u014d\u014e\7~\2\2\u014e\u014f"+
		"\7~\2\2\u014ff\3\2\2\2\u0150\u0151\7*\2\2\u0151h\3\2\2\2\u0152\u0153\7"+
		"+\2\2\u0153j\3\2\2\2\u0154\u0155\7]\2\2\u0155l\3\2\2\2\u0156\u0157\7_"+
		"\2\2\u0157n\3\2\2\2\u0158\u0159\7}\2\2\u0159p\3\2\2\2\u015a\u015b\7\177"+
		"\2\2\u015br\3\2\2\2\u015c\u015e\5u;\2\u015d\u015c\3\2\2\2\u015e\u015f"+
		"\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160t\3\2\2\2\u0161"+
		"\u0162\t\2\2\2\u0162v\3\2\2\2\u0163\u0165\t\3\2\2\u0164\u0163\3\2\2\2"+
		"\u0165\u0169\3\2\2\2\u0166\u0168\t\4\2\2\u0167\u0166\3\2\2\2\u0168\u016b"+
		"\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016ax\3\2\2\2\u016b"+
		"\u0169\3\2\2\2\u016c\u016e\t\5\2\2\u016d\u016c\3\2\2\2\u016e\u016f\3\2"+
		"\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"\u0172\b=\2\2\u0172z\3\2\2\2\u0173\u0174\t\6\2\2\u0174|\3\2\2\2\u0175"+
		"\u0179\n\7\2\2\u0176\u0177\7^\2\2\u0177\u0179\5{>\2\u0178\u0175\3\2\2"+
		"\2\u0178\u0176\3\2\2\2\u0179~\3\2\2\2\u017a\u017e\7$\2\2\u017b\u017d\5"+
		"}?\2\u017c\u017b\3\2\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e"+
		"\u017f\3\2\2\2\u017f\u0181\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0182\7$"+
		"\2\2\u0182\u0080\3\2\2\2\u0183\u0184\7)\2\2\u0184\u0185\5}?\2\u0185\u0186"+
		"\7)\2\2\u0186\u0082\3\2\2\2\u0187\u018b\7%\2\2\u0188\u018a\13\2\2\2\u0189"+
		"\u0188\3\2\2\2\u018a\u018d\3\2\2\2\u018b\u018c\3\2\2\2\u018b\u0189\3\2"+
		"\2\2\u018c\u018e\3\2\2\2\u018d\u018b\3\2\2\2\u018e\u018f\t\b\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0191\bB\2\2\u0191\u0084\3\2\2\2\13\2\u015f\u0164"+
		"\u0167\u0169\u016f\u0178\u017e\u018b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}