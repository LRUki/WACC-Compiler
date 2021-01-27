// Generated from /home/nm219/wacc_23/antlr_config/BasicLexer.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, IS=3, SKIP_TOKEN=4, READ=5, FREE=6, RETURN=7, EXIT=8, 
		PRINT=9, PRINTLN=10, IF=11, THEN=12, ELSE=13, FI=14, WHILE=15, DO=16, 
		DONE=17, SEMICOLON=18, NEWPAIR=19, CALL=20, COMMA=21, FST=22, SND=23, 
		PAIR=24, INT=25, BOOL=26, CHAR=27, STRING=28, NOT=29, LEN=30, ORD=31, 
		CHR=32, ADD=33, SUB=34, MULT=35, DIV=36, MOD=37, GTE=38, GT=39, LTE=40, 
		LT=41, EQ=42, NEQ=43, AND=44, OR=45, OPEN_PARENTHESES=46, CLOSE_PARENTHESES=47, 
		L_SQUARE=48, R_SQUARE=49, L_CURLY=50, R_CURLY=51, INTEGER=52;
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
			"SEMICOLON", "NEWPAIR", "CALL", "COMMA", "FST", "SND", "PAIR", "INT", 
			"BOOL", "CHAR", "STRING", "NOT", "LEN", "ORD", "CHR", "ADD", "SUB", "MULT", 
			"DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "DIGIT", 
			"INTEGER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi; '", 
			"'while'", "'do'", "'done'", "';'", "'newpair'", "'call'", "','", "'fst'", 
			"'snd'", "'pair'", "'int'", "'bool'", "'char'", "'string'", "'!'", "'len'", 
			"'ord'", "'chr'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>='", "'>'", "'<='", 
			"'<'", "'=='", "'!='", "'&&'", "'||'", "'('", "')'", "'['", "']'", "'{'", 
			"'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"SEMICOLON", "NEWPAIR", "CALL", "COMMA", "FST", "SND", "PAIR", "INT", 
			"BOOL", "CHAR", "STRING", "NOT", "LEN", "ORD", "CHR", "ADD", "SUB", "MULT", 
			"DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", "AND", "OR", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "L_SQUARE", "R_SQUARE", "L_CURLY", "R_CURLY", "INTEGER"
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


	public BasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0137\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3"+
		" \3!\3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)"+
		"\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\6\66\u0134\n\66\r\66\16"+
		"\66\u0135\2\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\2k\66\3\2\2\2\u0136\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]"+
		"\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2k\3\2"+
		"\2\2\3m\3\2\2\2\5s\3\2\2\2\7w\3\2\2\2\tz\3\2\2\2\13\177\3\2\2\2\r\u0084"+
		"\3\2\2\2\17\u0089\3\2\2\2\21\u0090\3\2\2\2\23\u0095\3\2\2\2\25\u009b\3"+
		"\2\2\2\27\u00a3\3\2\2\2\31\u00a6\3\2\2\2\33\u00ab\3\2\2\2\35\u00b0\3\2"+
		"\2\2\37\u00b5\3\2\2\2!\u00bb\3\2\2\2#\u00be\3\2\2\2%\u00c3\3\2\2\2\'\u00c5"+
		"\3\2\2\2)\u00cd\3\2\2\2+\u00d2\3\2\2\2-\u00d4\3\2\2\2/\u00d8\3\2\2\2\61"+
		"\u00dc\3\2\2\2\63\u00e1\3\2\2\2\65\u00e5\3\2\2\2\67\u00ea\3\2\2\29\u00ef"+
		"\3\2\2\2;\u00f6\3\2\2\2=\u00f8\3\2\2\2?\u00fc\3\2\2\2A\u0100\3\2\2\2C"+
		"\u0104\3\2\2\2E\u0106\3\2\2\2G\u0108\3\2\2\2I\u010a\3\2\2\2K\u010c\3\2"+
		"\2\2M\u010e\3\2\2\2O\u0111\3\2\2\2Q\u0113\3\2\2\2S\u0116\3\2\2\2U\u0118"+
		"\3\2\2\2W\u011b\3\2\2\2Y\u011e\3\2\2\2[\u0121\3\2\2\2]\u0124\3\2\2\2_"+
		"\u0126\3\2\2\2a\u0128\3\2\2\2c\u012a\3\2\2\2e\u012c\3\2\2\2g\u012e\3\2"+
		"\2\2i\u0130\3\2\2\2k\u0133\3\2\2\2mn\7d\2\2no\7g\2\2op\7i\2\2pq\7k\2\2"+
		"qr\7p\2\2r\4\3\2\2\2st\7g\2\2tu\7p\2\2uv\7f\2\2v\6\3\2\2\2wx\7k\2\2xy"+
		"\7u\2\2y\b\3\2\2\2z{\7u\2\2{|\7m\2\2|}\7k\2\2}~\7r\2\2~\n\3\2\2\2\177"+
		"\u0080\7t\2\2\u0080\u0081\7g\2\2\u0081\u0082\7c\2\2\u0082\u0083\7f\2\2"+
		"\u0083\f\3\2\2\2\u0084\u0085\7h\2\2\u0085\u0086\7t\2\2\u0086\u0087\7g"+
		"\2\2\u0087\u0088\7g\2\2\u0088\16\3\2\2\2\u0089\u008a\7t\2\2\u008a\u008b"+
		"\7g\2\2\u008b\u008c\7v\2\2\u008c\u008d\7w\2\2\u008d\u008e\7t\2\2\u008e"+
		"\u008f\7p\2\2\u008f\20\3\2\2\2\u0090\u0091\7g\2\2\u0091\u0092\7z\2\2\u0092"+
		"\u0093\7k\2\2\u0093\u0094\7v\2\2\u0094\22\3\2\2\2\u0095\u0096\7r\2\2\u0096"+
		"\u0097\7t\2\2\u0097\u0098\7k\2\2\u0098\u0099\7p\2\2\u0099\u009a\7v\2\2"+
		"\u009a\24\3\2\2\2\u009b\u009c\7r\2\2\u009c\u009d\7t\2\2\u009d\u009e\7"+
		"k\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2"+
		"\7p\2\2\u00a2\26\3\2\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7h\2\2\u00a5\30"+
		"\3\2\2\2\u00a6\u00a7\7v\2\2\u00a7\u00a8\7j\2\2\u00a8\u00a9\7g\2\2\u00a9"+
		"\u00aa\7p\2\2\u00aa\32\3\2\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7n\2\2\u00ad"+
		"\u00ae\7u\2\2\u00ae\u00af\7g\2\2\u00af\34\3\2\2\2\u00b0\u00b1\7h\2\2\u00b1"+
		"\u00b2\7k\2\2\u00b2\u00b3\7=\2\2\u00b3\u00b4\7\"\2\2\u00b4\36\3\2\2\2"+
		"\u00b5\u00b6\7y\2\2\u00b6\u00b7\7j\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9"+
		"\7n\2\2\u00b9\u00ba\7g\2\2\u00ba \3\2\2\2\u00bb\u00bc\7f\2\2\u00bc\u00bd"+
		"\7q\2\2\u00bd\"\3\2\2\2\u00be\u00bf\7f\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1"+
		"\7p\2\2\u00c1\u00c2\7g\2\2\u00c2$\3\2\2\2\u00c3\u00c4\7=\2\2\u00c4&\3"+
		"\2\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7y\2\2\u00c8"+
		"\u00c9\7r\2\2\u00c9\u00ca\7c\2\2\u00ca\u00cb\7k\2\2\u00cb\u00cc\7t\2\2"+
		"\u00cc(\3\2\2\2\u00cd\u00ce\7e\2\2\u00ce\u00cf\7c\2\2\u00cf\u00d0\7n\2"+
		"\2\u00d0\u00d1\7n\2\2\u00d1*\3\2\2\2\u00d2\u00d3\7.\2\2\u00d3,\3\2\2\2"+
		"\u00d4\u00d5\7h\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7v\2\2\u00d7.\3\2\2"+
		"\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7f\2\2\u00db\60\3"+
		"\2\2\2\u00dc\u00dd\7r\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7k\2\2\u00df"+
		"\u00e0\7t\2\2\u00e0\62\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7p\2\2\u00e3"+
		"\u00e4\7v\2\2\u00e4\64\3\2\2\2\u00e5\u00e6\7d\2\2\u00e6\u00e7\7q\2\2\u00e7"+
		"\u00e8\7q\2\2\u00e8\u00e9\7n\2\2\u00e9\66\3\2\2\2\u00ea\u00eb\7e\2\2\u00eb"+
		"\u00ec\7j\2\2\u00ec\u00ed\7c\2\2\u00ed\u00ee\7t\2\2\u00ee8\3\2\2\2\u00ef"+
		"\u00f0\7u\2\2\u00f0\u00f1\7v\2\2\u00f1\u00f2\7t\2\2\u00f2\u00f3\7k\2\2"+
		"\u00f3\u00f4\7p\2\2\u00f4\u00f5\7i\2\2\u00f5:\3\2\2\2\u00f6\u00f7\7#\2"+
		"\2\u00f7<\3\2\2\2\u00f8\u00f9\7n\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7"+
		"p\2\2\u00fb>\3\2\2\2\u00fc\u00fd\7q\2\2\u00fd\u00fe\7t\2\2\u00fe\u00ff"+
		"\7f\2\2\u00ff@\3\2\2\2\u0100\u0101\7e\2\2\u0101\u0102\7j\2\2\u0102\u0103"+
		"\7t\2\2\u0103B\3\2\2\2\u0104\u0105\7-\2\2\u0105D\3\2\2\2\u0106\u0107\7"+
		"/\2\2\u0107F\3\2\2\2\u0108\u0109\7,\2\2\u0109H\3\2\2\2\u010a\u010b\7\61"+
		"\2\2\u010bJ\3\2\2\2\u010c\u010d\7\'\2\2\u010dL\3\2\2\2\u010e\u010f\7@"+
		"\2\2\u010f\u0110\7?\2\2\u0110N\3\2\2\2\u0111\u0112\7@\2\2\u0112P\3\2\2"+
		"\2\u0113\u0114\7>\2\2\u0114\u0115\7?\2\2\u0115R\3\2\2\2\u0116\u0117\7"+
		">\2\2\u0117T\3\2\2\2\u0118\u0119\7?\2\2\u0119\u011a\7?\2\2\u011aV\3\2"+
		"\2\2\u011b\u011c\7#\2\2\u011c\u011d\7?\2\2\u011dX\3\2\2\2\u011e\u011f"+
		"\7(\2\2\u011f\u0120\7(\2\2\u0120Z\3\2\2\2\u0121\u0122\7~\2\2\u0122\u0123"+
		"\7~\2\2\u0123\\\3\2\2\2\u0124\u0125\7*\2\2\u0125^\3\2\2\2\u0126\u0127"+
		"\7+\2\2\u0127`\3\2\2\2\u0128\u0129\7]\2\2\u0129b\3\2\2\2\u012a\u012b\7"+
		"_\2\2\u012bd\3\2\2\2\u012c\u012d\7}\2\2\u012df\3\2\2\2\u012e\u012f\7\177"+
		"\2\2\u012fh\3\2\2\2\u0130\u0131\4\62;\2\u0131j\3\2\2\2\u0132\u0134\5i"+
		"\65\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136l\3\2\2\2\4\2\u0135\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}