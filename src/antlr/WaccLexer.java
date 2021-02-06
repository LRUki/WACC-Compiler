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
		STRING=32, NOT=33, LEN=34, ORD=35, CHR=36, PLUS=37, MINUS=38, MULT=39, 
		DIV=40, MOD=41, GTE=42, GT=43, LTE=44, LT=45, EQ=46, NEQ=47, AND=48, OR=49, 
		L_PAREN=50, R_PAREN=51, L_SQUARE=52, R_SQUARE=53, L_CURLY=54, R_CURLY=55, 
		NUMBER=56, IDENT=57, WHITESPACE=58, ESCAPE_CHARACTER=59, CHARACTER=60, 
		STR_LITER=61, CHAR_LITER=62, COMMENT=63;
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
			"PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING", "NOT", "LEN", 
			"ORD", "CHR", "PLUS", "MINUS", "MULT", "DIV", "MOD", "GTE", "GT", "LTE", 
			"LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE", "R_SQUARE", 
			"L_CURLY", "R_CURLY", "NUMBER", "DIGIT", "IDENT", "WHITESPACE", "ESCAPE_CHARACTER", 
			"CHARACTER", "STR_LITER", "CHAR_LITER", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'", 
			"'while'", "'do'", "'done'", "'null'", "'='", "';'", "','", "'newpair'", 
			"'call'", "'fst'", "'snd'", "'pair'", "'true'", "'false'", "'int'", "'bool'", 
			"'char'", "'string'", "'!'", "'len'", "'ord'", "'chr'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'>='", "'>'", "'<='", "'<'", "'=='", "'!='", "'&&'", 
			"'||'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"NULL", "ASSIGN", "SEMICOLON", "COMMA", "NEWPAIR", "CALL", "FST", "SND", 
			"PAIR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING", "NOT", "LEN", 
			"ORD", "CHR", "PLUS", "MINUS", "MULT", "DIV", "MOD", "GTE", "GT", "LTE", 
			"LT", "EQ", "NEQ", "AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE", "R_SQUARE", 
			"L_CURLY", "R_CURLY", "NUMBER", "IDENT", "WHITESPACE", "ESCAPE_CHARACTER", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2A\u018c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!"+
		"\3!\3!\3!\3!\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'"+
		"\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3-\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3"+
		"\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3"+
		"\66\3\67\3\67\38\38\39\69\u0158\n9\r9\169\u0159\3:\3:\3;\5;\u015f\n;\3"+
		";\7;\u0162\n;\f;\16;\u0165\13;\3<\6<\u0168\n<\r<\16<\u0169\3<\3<\3=\3"+
		"=\3>\3>\3>\5>\u0173\n>\3?\3?\7?\u0177\n?\f?\16?\u017a\13?\3?\3?\3@\3@"+
		"\3@\3@\3A\3A\7A\u0184\nA\fA\16A\u0187\13A\3A\3A\3A\3A\3\u0185\2B\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s"+
		"\2u;w<y={>}?\177@\u0081A\3\2\t\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13"+
		"\f\17\17\"\"\13\2$$))\62\62^^ddhhppttvv\5\2$$))^^\4\2\f\f\17\17\2\u0190"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2"+
		"}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\3\u0083\3\2\2\2\5\u0089\3\2\2"+
		"\2\7\u008d\3\2\2\2\t\u0090\3\2\2\2\13\u0095\3\2\2\2\r\u009a\3\2\2\2\17"+
		"\u009f\3\2\2\2\21\u00a6\3\2\2\2\23\u00ab\3\2\2\2\25\u00b1\3\2\2\2\27\u00b9"+
		"\3\2\2\2\31\u00bc\3\2\2\2\33\u00c1\3\2\2\2\35\u00c6\3\2\2\2\37\u00c9\3"+
		"\2\2\2!\u00cf\3\2\2\2#\u00d2\3\2\2\2%\u00d7\3\2\2\2\'\u00dc\3\2\2\2)\u00de"+
		"\3\2\2\2+\u00e0\3\2\2\2-\u00e2\3\2\2\2/\u00ea\3\2\2\2\61\u00ef\3\2\2\2"+
		"\63\u00f3\3\2\2\2\65\u00f7\3\2\2\2\67\u00fc\3\2\2\29\u0101\3\2\2\2;\u0107"+
		"\3\2\2\2=\u010b\3\2\2\2?\u0110\3\2\2\2A\u0115\3\2\2\2C\u011c\3\2\2\2E"+
		"\u011e\3\2\2\2G\u0122\3\2\2\2I\u0126\3\2\2\2K\u012a\3\2\2\2M\u012c\3\2"+
		"\2\2O\u012e\3\2\2\2Q\u0130\3\2\2\2S\u0132\3\2\2\2U\u0134\3\2\2\2W\u0137"+
		"\3\2\2\2Y\u0139\3\2\2\2[\u013c\3\2\2\2]\u013e\3\2\2\2_\u0141\3\2\2\2a"+
		"\u0144\3\2\2\2c\u0147\3\2\2\2e\u014a\3\2\2\2g\u014c\3\2\2\2i\u014e\3\2"+
		"\2\2k\u0150\3\2\2\2m\u0152\3\2\2\2o\u0154\3\2\2\2q\u0157\3\2\2\2s\u015b"+
		"\3\2\2\2u\u015e\3\2\2\2w\u0167\3\2\2\2y\u016d\3\2\2\2{\u0172\3\2\2\2}"+
		"\u0174\3\2\2\2\177\u017d\3\2\2\2\u0081\u0181\3\2\2\2\u0083\u0084\7d\2"+
		"\2\u0084\u0085\7g\2\2\u0085\u0086\7i\2\2\u0086\u0087\7k\2\2\u0087\u0088"+
		"\7p\2\2\u0088\4\3\2\2\2\u0089\u008a\7g\2\2\u008a\u008b\7p\2\2\u008b\u008c"+
		"\7f\2\2\u008c\6\3\2\2\2\u008d\u008e\7k\2\2\u008e\u008f\7u\2\2\u008f\b"+
		"\3\2\2\2\u0090\u0091\7u\2\2\u0091\u0092\7m\2\2\u0092\u0093\7k\2\2\u0093"+
		"\u0094\7r\2\2\u0094\n\3\2\2\2\u0095\u0096\7t\2\2\u0096\u0097\7g\2\2\u0097"+
		"\u0098\7c\2\2\u0098\u0099\7f\2\2\u0099\f\3\2\2\2\u009a\u009b\7h\2\2\u009b"+
		"\u009c\7t\2\2\u009c\u009d\7g\2\2\u009d\u009e\7g\2\2\u009e\16\3\2\2\2\u009f"+
		"\u00a0\7t\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7w\2\2"+
		"\u00a3\u00a4\7t\2\2\u00a4\u00a5\7p\2\2\u00a5\20\3\2\2\2\u00a6\u00a7\7"+
		"g\2\2\u00a7\u00a8\7z\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7v\2\2\u00aa\22"+
		"\3\2\2\2\u00ab\u00ac\7r\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7k\2\2\u00ae"+
		"\u00af\7p\2\2\u00af\u00b0\7v\2\2\u00b0\24\3\2\2\2\u00b1\u00b2\7r\2\2\u00b2"+
		"\u00b3\7t\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7v\2\2"+
		"\u00b6\u00b7\7n\2\2\u00b7\u00b8\7p\2\2\u00b8\26\3\2\2\2\u00b9\u00ba\7"+
		"k\2\2\u00ba\u00bb\7h\2\2\u00bb\30\3\2\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be"+
		"\7j\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7p\2\2\u00c0\32\3\2\2\2\u00c1\u00c2"+
		"\7g\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7g\2\2\u00c5"+
		"\34\3\2\2\2\u00c6\u00c7\7h\2\2\u00c7\u00c8\7k\2\2\u00c8\36\3\2\2\2\u00c9"+
		"\u00ca\7y\2\2\u00ca\u00cb\7j\2\2\u00cb\u00cc\7k\2\2\u00cc\u00cd\7n\2\2"+
		"\u00cd\u00ce\7g\2\2\u00ce \3\2\2\2\u00cf\u00d0\7f\2\2\u00d0\u00d1\7q\2"+
		"\2\u00d1\"\3\2\2\2\u00d2\u00d3\7f\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7"+
		"p\2\2\u00d5\u00d6\7g\2\2\u00d6$\3\2\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9"+
		"\7w\2\2\u00d9\u00da\7n\2\2\u00da\u00db\7n\2\2\u00db&\3\2\2\2\u00dc\u00dd"+
		"\7?\2\2\u00dd(\3\2\2\2\u00de\u00df\7=\2\2\u00df*\3\2\2\2\u00e0\u00e1\7"+
		".\2\2\u00e1,\3\2\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5"+
		"\7y\2\2\u00e5\u00e6\7r\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7k\2\2\u00e8"+
		"\u00e9\7t\2\2\u00e9.\3\2\2\2\u00ea\u00eb\7e\2\2\u00eb\u00ec\7c\2\2\u00ec"+
		"\u00ed\7n\2\2\u00ed\u00ee\7n\2\2\u00ee\60\3\2\2\2\u00ef\u00f0\7h\2\2\u00f0"+
		"\u00f1\7u\2\2\u00f1\u00f2\7v\2\2\u00f2\62\3\2\2\2\u00f3\u00f4\7u\2\2\u00f4"+
		"\u00f5\7p\2\2\u00f5\u00f6\7f\2\2\u00f6\64\3\2\2\2\u00f7\u00f8\7r\2\2\u00f8"+
		"\u00f9\7c\2\2\u00f9\u00fa\7k\2\2\u00fa\u00fb\7t\2\2\u00fb\66\3\2\2\2\u00fc"+
		"\u00fd\7v\2\2\u00fd\u00fe\7t\2\2\u00fe\u00ff\7w\2\2\u00ff\u0100\7g\2\2"+
		"\u01008\3\2\2\2\u0101\u0102\7h\2\2\u0102\u0103\7c\2\2\u0103\u0104\7n\2"+
		"\2\u0104\u0105\7u\2\2\u0105\u0106\7g\2\2\u0106:\3\2\2\2\u0107\u0108\7"+
		"k\2\2\u0108\u0109\7p\2\2\u0109\u010a\7v\2\2\u010a<\3\2\2\2\u010b\u010c"+
		"\7d\2\2\u010c\u010d\7q\2\2\u010d\u010e\7q\2\2\u010e\u010f\7n\2\2\u010f"+
		">\3\2\2\2\u0110\u0111\7e\2\2\u0111\u0112\7j\2\2\u0112\u0113\7c\2\2\u0113"+
		"\u0114\7t\2\2\u0114@\3\2\2\2\u0115\u0116\7u\2\2\u0116\u0117\7v\2\2\u0117"+
		"\u0118\7t\2\2\u0118\u0119\7k\2\2\u0119\u011a\7p\2\2\u011a\u011b\7i\2\2"+
		"\u011bB\3\2\2\2\u011c\u011d\7#\2\2\u011dD\3\2\2\2\u011e\u011f\7n\2\2\u011f"+
		"\u0120\7g\2\2\u0120\u0121\7p\2\2\u0121F\3\2\2\2\u0122\u0123\7q\2\2\u0123"+
		"\u0124\7t\2\2\u0124\u0125\7f\2\2\u0125H\3\2\2\2\u0126\u0127\7e\2\2\u0127"+
		"\u0128\7j\2\2\u0128\u0129\7t\2\2\u0129J\3\2\2\2\u012a\u012b\7-\2\2\u012b"+
		"L\3\2\2\2\u012c\u012d\7/\2\2\u012dN\3\2\2\2\u012e\u012f\7,\2\2\u012fP"+
		"\3\2\2\2\u0130\u0131\7\61\2\2\u0131R\3\2\2\2\u0132\u0133\7\'\2\2\u0133"+
		"T\3\2\2\2\u0134\u0135\7@\2\2\u0135\u0136\7?\2\2\u0136V\3\2\2\2\u0137\u0138"+
		"\7@\2\2\u0138X\3\2\2\2\u0139\u013a\7>\2\2\u013a\u013b\7?\2\2\u013bZ\3"+
		"\2\2\2\u013c\u013d\7>\2\2\u013d\\\3\2\2\2\u013e\u013f\7?\2\2\u013f\u0140"+
		"\7?\2\2\u0140^\3\2\2\2\u0141\u0142\7#\2\2\u0142\u0143\7?\2\2\u0143`\3"+
		"\2\2\2\u0144\u0145\7(\2\2\u0145\u0146\7(\2\2\u0146b\3\2\2\2\u0147\u0148"+
		"\7~\2\2\u0148\u0149\7~\2\2\u0149d\3\2\2\2\u014a\u014b\7*\2\2\u014bf\3"+
		"\2\2\2\u014c\u014d\7+\2\2\u014dh\3\2\2\2\u014e\u014f\7]\2\2\u014fj\3\2"+
		"\2\2\u0150\u0151\7_\2\2\u0151l\3\2\2\2\u0152\u0153\7}\2\2\u0153n\3\2\2"+
		"\2\u0154\u0155\7\177\2\2\u0155p\3\2\2\2\u0156\u0158\5s:\2\u0157\u0156"+
		"\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a"+
		"r\3\2\2\2\u015b\u015c\t\2\2\2\u015ct\3\2\2\2\u015d\u015f\t\3\2\2\u015e"+
		"\u015d\3\2\2\2\u015f\u0163\3\2\2\2\u0160\u0162\t\4\2\2\u0161\u0160\3\2"+
		"\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"v\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u0168\t\5\2\2\u0167\u0166\3\2\2\2"+
		"\u0168\u0169\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b"+
		"\3\2\2\2\u016b\u016c\b<\2\2\u016cx\3\2\2\2\u016d\u016e\t\6\2\2\u016ez"+
		"\3\2\2\2\u016f\u0173\n\7\2\2\u0170\u0171\7^\2\2\u0171\u0173\5y=\2\u0172"+
		"\u016f\3\2\2\2\u0172\u0170\3\2\2\2\u0173|\3\2\2\2\u0174\u0178\7$\2\2\u0175"+
		"\u0177\5{>\2\u0176\u0175\3\2\2\2\u0177\u017a\3\2\2\2\u0178\u0176\3\2\2"+
		"\2\u0178\u0179\3\2\2\2\u0179\u017b\3\2\2\2\u017a\u0178\3\2\2\2\u017b\u017c"+
		"\7$\2\2\u017c~\3\2\2\2\u017d\u017e\7)\2\2\u017e\u017f\5{>\2\u017f\u0180"+
		"\7)\2\2\u0180\u0080\3\2\2\2\u0181\u0185\7%\2\2\u0182\u0184\13\2\2\2\u0183"+
		"\u0182\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0186\3\2\2\2\u0185\u0183\3\2"+
		"\2\2\u0186\u0188\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189\t\b\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\u018b\bA\2\2\u018b\u0082\3\2\2\2\13\2\u0159\u015e"+
		"\u0161\u0163\u0169\u0172\u0178\u0185\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}