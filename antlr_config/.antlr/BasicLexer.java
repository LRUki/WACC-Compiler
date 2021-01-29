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
		PAIR=24, TRUE=25, FALSE=26, INT=27, BOOL=28, CHAR=29, STRING=30, NOT=31, 
		LEN=32, ORD=33, CHR=34, ADD=35, SUB=36, MULT=37, DIV=38, MOD=39, GTE=40, 
		GT=41, LTE=42, LT=43, EQ=44, NEQ=45, AND=46, OR=47, OPEN_PARENTHESES=48, 
		CLOSE_PARENTHESES=49, L_SQUARE=50, R_SQUARE=51, L_CURLY=52, R_CURLY=53, 
		INTEGER=54, IDENT=55, ESCAPE_CHARACTER=56, CHARACTER=57, STRING_LIT=58, 
		CHARACTER_LIT=59;
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
			"SEMICOLON", "NEWPAIR", "CALL", "COMMA", "FST", "SND", "PAIR", "TRUE", 
			"FALSE", "INT", "BOOL", "CHAR", "STRING", "NOT", "LEN", "ORD", "CHR", 
			"ADD", "SUB", "MULT", "DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", 
			"AND", "OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "L_SQUARE", "R_SQUARE", 
			"L_CURLY", "R_CURLY", "DIGIT", "INTEGER", "IDENTIFIER_FIRST", "IDENTIFER_REST", 
			"IDENT", "ESCAPE_CHARACTER", "CHARACTER", "STRING_LIT", "CHARACTER_LIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi; '", 
			"'while'", "'do'", "'done'", "';'", "'newpair'", "'call'", "','", "'fst'", 
			"'snd'", "'pair'", "'true'", "'false'", "'int'", "'bool'", "'char'", 
			"'string'", "'!'", "'len'", "'ord'", "'chr'", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'>='", "'>'", "'<='", "'<'", "'=='", "'!='", "'&&'", "'||'", 
			"'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"SEMICOLON", "NEWPAIR", "CALL", "COMMA", "FST", "SND", "PAIR", "TRUE", 
			"FALSE", "INT", "BOOL", "CHAR", "STRING", "NOT", "LEN", "ORD", "CHR", 
			"ADD", "SUB", "MULT", "DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", 
			"AND", "OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "L_SQUARE", "R_SQUARE", 
			"L_CURLY", "R_CURLY", "INTEGER", "IDENT", "ESCAPE_CHARACTER", "CHARACTER", 
			"STRING_LIT", "CHARACTER_LIT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u0174\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3"+
		"\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'"+
		"\3(\3(\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3-\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3"+
		"\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3"+
		"\67\3\67\38\68\u0151\n8\r8\168\u0152\39\39\3:\3:\7:\u0159\n:\f:\16:\u015c"+
		"\13:\3;\3;\3;\3<\3<\3=\3=\3=\5=\u0166\n=\3>\3>\7>\u016a\n>\f>\16>\u016d"+
		"\13>\3>\3>\3?\3?\3?\3?\2\2@\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m\2o8q\2s\2u9w:y;{<}=\3\2\6\4\2aac|\5\2C\\aac|"+
		"\13\2$$))\62\62^^ddhhppttvv\5\2$$))^^\2\u0175\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2"+
		"\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y"+
		"\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2"+
		"\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2o\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2"+
		"\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\3\177\3\2\2\2\5\u0085\3\2\2\2\7\u0089"+
		"\3\2\2\2\t\u008c\3\2\2\2\13\u0091\3\2\2\2\r\u0096\3\2\2\2\17\u009b\3\2"+
		"\2\2\21\u00a2\3\2\2\2\23\u00a7\3\2\2\2\25\u00ad\3\2\2\2\27\u00b5\3\2\2"+
		"\2\31\u00b8\3\2\2\2\33\u00bd\3\2\2\2\35\u00c2\3\2\2\2\37\u00c7\3\2\2\2"+
		"!\u00cd\3\2\2\2#\u00d0\3\2\2\2%\u00d5\3\2\2\2\'\u00d7\3\2\2\2)\u00df\3"+
		"\2\2\2+\u00e4\3\2\2\2-\u00e6\3\2\2\2/\u00ea\3\2\2\2\61\u00ee\3\2\2\2\63"+
		"\u00f3\3\2\2\2\65\u00f8\3\2\2\2\67\u00fe\3\2\2\29\u0102\3\2\2\2;\u0107"+
		"\3\2\2\2=\u010c\3\2\2\2?\u0113\3\2\2\2A\u0115\3\2\2\2C\u0119\3\2\2\2E"+
		"\u011d\3\2\2\2G\u0121\3\2\2\2I\u0123\3\2\2\2K\u0125\3\2\2\2M\u0127\3\2"+
		"\2\2O\u0129\3\2\2\2Q\u012b\3\2\2\2S\u012e\3\2\2\2U\u0130\3\2\2\2W\u0133"+
		"\3\2\2\2Y\u0135\3\2\2\2[\u0138\3\2\2\2]\u013b\3\2\2\2_\u013e\3\2\2\2a"+
		"\u0141\3\2\2\2c\u0143\3\2\2\2e\u0145\3\2\2\2g\u0147\3\2\2\2i\u0149\3\2"+
		"\2\2k\u014b\3\2\2\2m\u014d\3\2\2\2o\u0150\3\2\2\2q\u0154\3\2\2\2s\u015a"+
		"\3\2\2\2u\u015d\3\2\2\2w\u0160\3\2\2\2y\u0165\3\2\2\2{\u0167\3\2\2\2}"+
		"\u0170\3\2\2\2\177\u0080\7d\2\2\u0080\u0081\7g\2\2\u0081\u0082\7i\2\2"+
		"\u0082\u0083\7k\2\2\u0083\u0084\7p\2\2\u0084\4\3\2\2\2\u0085\u0086\7g"+
		"\2\2\u0086\u0087\7p\2\2\u0087\u0088\7f\2\2\u0088\6\3\2\2\2\u0089\u008a"+
		"\7k\2\2\u008a\u008b\7u\2\2\u008b\b\3\2\2\2\u008c\u008d\7u\2\2\u008d\u008e"+
		"\7m\2\2\u008e\u008f\7k\2\2\u008f\u0090\7r\2\2\u0090\n\3\2\2\2\u0091\u0092"+
		"\7t\2\2\u0092\u0093\7g\2\2\u0093\u0094\7c\2\2\u0094\u0095\7f\2\2\u0095"+
		"\f\3\2\2\2\u0096\u0097\7h\2\2\u0097\u0098\7t\2\2\u0098\u0099\7g\2\2\u0099"+
		"\u009a\7g\2\2\u009a\16\3\2\2\2\u009b\u009c\7t\2\2\u009c\u009d\7g\2\2\u009d"+
		"\u009e\7v\2\2\u009e\u009f\7w\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7p\2\2"+
		"\u00a1\20\3\2\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7z\2\2\u00a4\u00a5\7"+
		"k\2\2\u00a5\u00a6\7v\2\2\u00a6\22\3\2\2\2\u00a7\u00a8\7r\2\2\u00a8\u00a9"+
		"\7t\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7v\2\2\u00ac"+
		"\24\3\2\2\2\u00ad\u00ae\7r\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7p\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7p\2\2"+
		"\u00b4\26\3\2\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7h\2\2\u00b7\30\3\2\2"+
		"\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7j\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc"+
		"\7p\2\2\u00bc\32\3\2\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7n\2\2\u00bf\u00c0"+
		"\7u\2\2\u00c0\u00c1\7g\2\2\u00c1\34\3\2\2\2\u00c2\u00c3\7h\2\2\u00c3\u00c4"+
		"\7k\2\2\u00c4\u00c5\7=\2\2\u00c5\u00c6\7\"\2\2\u00c6\36\3\2\2\2\u00c7"+
		"\u00c8\7y\2\2\u00c8\u00c9\7j\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7n\2\2"+
		"\u00cb\u00cc\7g\2\2\u00cc \3\2\2\2\u00cd\u00ce\7f\2\2\u00ce\u00cf\7q\2"+
		"\2\u00cf\"\3\2\2\2\u00d0\u00d1\7f\2\2\u00d1\u00d2\7q\2\2\u00d2\u00d3\7"+
		"p\2\2\u00d3\u00d4\7g\2\2\u00d4$\3\2\2\2\u00d5\u00d6\7=\2\2\u00d6&\3\2"+
		"\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7y\2\2\u00da\u00db"+
		"\7r\2\2\u00db\u00dc\7c\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de\7t\2\2\u00de"+
		"(\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2\7n\2\2\u00e2"+
		"\u00e3\7n\2\2\u00e3*\3\2\2\2\u00e4\u00e5\7.\2\2\u00e5,\3\2\2\2\u00e6\u00e7"+
		"\7h\2\2\u00e7\u00e8\7u\2\2\u00e8\u00e9\7v\2\2\u00e9.\3\2\2\2\u00ea\u00eb"+
		"\7u\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed\7f\2\2\u00ed\60\3\2\2\2\u00ee\u00ef"+
		"\7r\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7t\2\2\u00f2"+
		"\62\3\2\2\2\u00f3\u00f4\7v\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6\7w\2\2\u00f6"+
		"\u00f7\7g\2\2\u00f7\64\3\2\2\2\u00f8\u00f9\7h\2\2\u00f9\u00fa\7c\2\2\u00fa"+
		"\u00fb\7n\2\2\u00fb\u00fc\7u\2\2\u00fc\u00fd\7g\2\2\u00fd\66\3\2\2\2\u00fe"+
		"\u00ff\7k\2\2\u00ff\u0100\7p\2\2\u0100\u0101\7v\2\2\u01018\3\2\2\2\u0102"+
		"\u0103\7d\2\2\u0103\u0104\7q\2\2\u0104\u0105\7q\2\2\u0105\u0106\7n\2\2"+
		"\u0106:\3\2\2\2\u0107\u0108\7e\2\2\u0108\u0109\7j\2\2\u0109\u010a\7c\2"+
		"\2\u010a\u010b\7t\2\2\u010b<\3\2\2\2\u010c\u010d\7u\2\2\u010d\u010e\7"+
		"v\2\2\u010e\u010f\7t\2\2\u010f\u0110\7k\2\2\u0110\u0111\7p\2\2\u0111\u0112"+
		"\7i\2\2\u0112>\3\2\2\2\u0113\u0114\7#\2\2\u0114@\3\2\2\2\u0115\u0116\7"+
		"n\2\2\u0116\u0117\7g\2\2\u0117\u0118\7p\2\2\u0118B\3\2\2\2\u0119\u011a"+
		"\7q\2\2\u011a\u011b\7t\2\2\u011b\u011c\7f\2\2\u011cD\3\2\2\2\u011d\u011e"+
		"\7e\2\2\u011e\u011f\7j\2\2\u011f\u0120\7t\2\2\u0120F\3\2\2\2\u0121\u0122"+
		"\7-\2\2\u0122H\3\2\2\2\u0123\u0124\7/\2\2\u0124J\3\2\2\2\u0125\u0126\7"+
		",\2\2\u0126L\3\2\2\2\u0127\u0128\7\61\2\2\u0128N\3\2\2\2\u0129\u012a\7"+
		"\'\2\2\u012aP\3\2\2\2\u012b\u012c\7@\2\2\u012c\u012d\7?\2\2\u012dR\3\2"+
		"\2\2\u012e\u012f\7@\2\2\u012fT\3\2\2\2\u0130\u0131\7>\2\2\u0131\u0132"+
		"\7?\2\2\u0132V\3\2\2\2\u0133\u0134\7>\2\2\u0134X\3\2\2\2\u0135\u0136\7"+
		"?\2\2\u0136\u0137\7?\2\2\u0137Z\3\2\2\2\u0138\u0139\7#\2\2\u0139\u013a"+
		"\7?\2\2\u013a\\\3\2\2\2\u013b\u013c\7(\2\2\u013c\u013d\7(\2\2\u013d^\3"+
		"\2\2\2\u013e\u013f\7~\2\2\u013f\u0140\7~\2\2\u0140`\3\2\2\2\u0141\u0142"+
		"\7*\2\2\u0142b\3\2\2\2\u0143\u0144\7+\2\2\u0144d\3\2\2\2\u0145\u0146\7"+
		"]\2\2\u0146f\3\2\2\2\u0147\u0148\7_\2\2\u0148h\3\2\2\2\u0149\u014a\7}"+
		"\2\2\u014aj\3\2\2\2\u014b\u014c\7\177\2\2\u014cl\3\2\2\2\u014d\u014e\4"+
		"\62;\2\u014en\3\2\2\2\u014f\u0151\5m\67\2\u0150\u014f\3\2\2\2\u0151\u0152"+
		"\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153p\3\2\2\2\u0154"+
		"\u0155\t\2\2\2\u0155r\3\2\2\2\u0156\u0159\t\3\2\2\u0157\u0159\5m\67\2"+
		"\u0158\u0156\3\2\2\2\u0158\u0157\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158"+
		"\3\2\2\2\u015a\u015b\3\2\2\2\u015bt\3\2\2\2\u015c\u015a\3\2\2\2\u015d"+
		"\u015e\5q9\2\u015e\u015f\5s:\2\u015fv\3\2\2\2\u0160\u0161\t\4\2\2\u0161"+
		"x\3\2\2\2\u0162\u0166\n\5\2\2\u0163\u0164\7^\2\2\u0164\u0166\5w<\2\u0165"+
		"\u0162\3\2\2\2\u0165\u0163\3\2\2\2\u0166z\3\2\2\2\u0167\u016b\7$\2\2\u0168"+
		"\u016a\5y=\2\u0169\u0168\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2\2"+
		"\2\u016b\u016c\3\2\2\2\u016c\u016e\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u016f"+
		"\7$\2\2\u016f|\3\2\2\2\u0170\u0171\7)\2\2\u0171\u0172\5y=\2\u0172\u0173"+
		"\7)\2\2\u0173~\3\2\2\2\b\2\u0152\u0158\u015a\u0165\u016b\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}