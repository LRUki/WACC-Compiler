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
		PRINT=9, PRINTLN=10, IF=11, THEN=12, ELSE=13, FI=14, WHILE=15, FOR=16, 
		DO=17, DONE=18, NULL=19, STRUCT=20, DOT=21, IMPORT=22, ASSIGN=23, SEMICOLON=24, 
		COMMA=25, NEWPAIR=26, CALL=27, FST=28, SND=29, PAIR=30, TRUE=31, FALSE=32, 
		INT=33, BOOL=34, CHAR=35, STRING=36, VAR=37, VOID=38, NOT=39, LEN=40, 
		ORD=41, CHR=42, REF=43, PLUS=44, MINUS=45, MULT=46, DIV=47, MOD=48, GTE=49, 
		GT=50, LTE=51, LT=52, EQ=53, NEQ=54, AND=55, OR=56, L_PAREN=57, R_PAREN=58, 
		L_SQUARE=59, R_SQUARE=60, L_CURLY=61, R_CURLY=62, NUMBER=63, CAPTIALISED_IDENT=64, 
		IDENT=65, WHITESPACE=66, ESCAPE_CHARACTER=67, CHARACTER=68, STR_LITER=69, 
		CHAR_LITER=70, COMMENT=71;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "FOR", "DO", 
			"DONE", "NULL", "STRUCT", "DOT", "IMPORT", "ASSIGN", "SEMICOLON", "COMMA", 
			"NEWPAIR", "CALL", "FST", "SND", "PAIR", "TRUE", "FALSE", "INT", "BOOL", 
			"CHAR", "STRING", "VAR", "VOID", "NOT", "LEN", "ORD", "CHR", "REF", "PLUS", 
			"MINUS", "MULT", "DIV", "MOD", "GTE", "GT", "LTE", "LT", "EQ", "NEQ", 
			"AND", "OR", "L_PAREN", "R_PAREN", "L_SQUARE", "R_SQUARE", "L_CURLY", 
			"R_CURLY", "NUMBER", "DIGIT", "CAPTIALISED_IDENT", "IDENT", "WHITESPACE", 
			"ESCAPE_CHARACTER", "CHARACTER", "STR_LITER", "CHAR_LITER", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2I\u01c4\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\""+
		"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3"+
		"\'\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\3+\3+\3,\3,\3-"+
		"\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\38\38\38\39\39\39\3:\3:"+
		"\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\6@\u0187\n@\r@\16@\u0188\3A\3A\3B\5"+
		"B\u018e\nB\3B\7B\u0191\nB\fB\16B\u0194\13B\3C\5C\u0197\nC\3C\7C\u019a"+
		"\nC\fC\16C\u019d\13C\3D\6D\u01a0\nD\rD\16D\u01a1\3D\3D\3E\3E\3F\3F\3F"+
		"\5F\u01ab\nF\3G\3G\7G\u01af\nG\fG\16G\u01b2\13G\3G\3G\3H\3H\3H\3H\3I\3"+
		"I\7I\u01bc\nI\fI\16I\u01bf\13I\3I\3I\3I\3I\3\u01bd\2J\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081\2\u0083B\u0085C\u0087D\u0089E\u008bF\u008dG\u008fH\u0091I\3\2"+
		"\n\3\2\62;\4\2C\\aa\6\2\62;C\\aac|\5\2C\\aac|\5\2\13\f\17\17\"\"\13\2"+
		"$$))\62\62^^ddhhppttvv\5\2$$))^^\4\2\f\f\17\17\2\u01c9\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2"+
		"\2\2\2\177\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089"+
		"\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2"+
		"\2\3\u0093\3\2\2\2\5\u0099\3\2\2\2\7\u009d\3\2\2\2\t\u00a0\3\2\2\2\13"+
		"\u00a5\3\2\2\2\r\u00aa\3\2\2\2\17\u00af\3\2\2\2\21\u00b6\3\2\2\2\23\u00bb"+
		"\3\2\2\2\25\u00c1\3\2\2\2\27\u00c9\3\2\2\2\31\u00cc\3\2\2\2\33\u00d1\3"+
		"\2\2\2\35\u00d6\3\2\2\2\37\u00d9\3\2\2\2!\u00df\3\2\2\2#\u00e3\3\2\2\2"+
		"%\u00e6\3\2\2\2\'\u00eb\3\2\2\2)\u00f0\3\2\2\2+\u00f7\3\2\2\2-\u00f9\3"+
		"\2\2\2/\u0100\3\2\2\2\61\u0102\3\2\2\2\63\u0104\3\2\2\2\65\u0106\3\2\2"+
		"\2\67\u010e\3\2\2\29\u0113\3\2\2\2;\u0117\3\2\2\2=\u011b\3\2\2\2?\u0120"+
		"\3\2\2\2A\u0125\3\2\2\2C\u012b\3\2\2\2E\u012f\3\2\2\2G\u0134\3\2\2\2I"+
		"\u0139\3\2\2\2K\u0140\3\2\2\2M\u0144\3\2\2\2O\u0149\3\2\2\2Q\u014b\3\2"+
		"\2\2S\u014f\3\2\2\2U\u0153\3\2\2\2W\u0157\3\2\2\2Y\u0159\3\2\2\2[\u015b"+
		"\3\2\2\2]\u015d\3\2\2\2_\u015f\3\2\2\2a\u0161\3\2\2\2c\u0163\3\2\2\2e"+
		"\u0166\3\2\2\2g\u0168\3\2\2\2i\u016b\3\2\2\2k\u016d\3\2\2\2m\u0170\3\2"+
		"\2\2o\u0173\3\2\2\2q\u0176\3\2\2\2s\u0179\3\2\2\2u\u017b\3\2\2\2w\u017d"+
		"\3\2\2\2y\u017f\3\2\2\2{\u0181\3\2\2\2}\u0183\3\2\2\2\177\u0186\3\2\2"+
		"\2\u0081\u018a\3\2\2\2\u0083\u018d\3\2\2\2\u0085\u0196\3\2\2\2\u0087\u019f"+
		"\3\2\2\2\u0089\u01a5\3\2\2\2\u008b\u01aa\3\2\2\2\u008d\u01ac\3\2\2\2\u008f"+
		"\u01b5\3\2\2\2\u0091\u01b9\3\2\2\2\u0093\u0094\7d\2\2\u0094\u0095\7g\2"+
		"\2\u0095\u0096\7i\2\2\u0096\u0097\7k\2\2\u0097\u0098\7p\2\2\u0098\4\3"+
		"\2\2\2\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2\u009b\u009c\7f\2\2\u009c"+
		"\6\3\2\2\2\u009d\u009e\7k\2\2\u009e\u009f\7u\2\2\u009f\b\3\2\2\2\u00a0"+
		"\u00a1\7u\2\2\u00a1\u00a2\7m\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7r\2\2"+
		"\u00a4\n\3\2\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7c"+
		"\2\2\u00a8\u00a9\7f\2\2\u00a9\f\3\2\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac"+
		"\7t\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7g\2\2\u00ae\16\3\2\2\2\u00af\u00b0"+
		"\7t\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7w\2\2\u00b3"+
		"\u00b4\7t\2\2\u00b4\u00b5\7p\2\2\u00b5\20\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7z\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7v\2\2\u00ba\22\3\2\2\2\u00bb"+
		"\u00bc\7r\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7p\2\2"+
		"\u00bf\u00c0\7v\2\2\u00c0\24\3\2\2\2\u00c1\u00c2\7r\2\2\u00c2\u00c3\7"+
		"t\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7"+
		"\7n\2\2\u00c7\u00c8\7p\2\2\u00c8\26\3\2\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb"+
		"\7h\2\2\u00cb\30\3\2\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7j\2\2\u00ce\u00cf"+
		"\7g\2\2\u00cf\u00d0\7p\2\2\u00d0\32\3\2\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3"+
		"\7n\2\2\u00d3\u00d4\7u\2\2\u00d4\u00d5\7g\2\2\u00d5\34\3\2\2\2\u00d6\u00d7"+
		"\7h\2\2\u00d7\u00d8\7k\2\2\u00d8\36\3\2\2\2\u00d9\u00da\7y\2\2\u00da\u00db"+
		"\7j\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7g\2\2\u00de"+
		" \3\2\2\2\u00df\u00e0\7h\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2\7t\2\2\u00e2"+
		"\"\3\2\2\2\u00e3\u00e4\7f\2\2\u00e4\u00e5\7q\2\2\u00e5$\3\2\2\2\u00e6"+
		"\u00e7\7f\2\2\u00e7\u00e8\7q\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7g\2\2"+
		"\u00ea&\3\2\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed\7w\2\2\u00ed\u00ee\7n\2"+
		"\2\u00ee\u00ef\7n\2\2\u00ef(\3\2\2\2\u00f0\u00f1\7u\2\2\u00f1\u00f2\7"+
		"v\2\2\u00f2\u00f3\7t\2\2\u00f3\u00f4\7w\2\2\u00f4\u00f5\7e\2\2\u00f5\u00f6"+
		"\7v\2\2\u00f6*\3\2\2\2\u00f7\u00f8\7\60\2\2\u00f8,\3\2\2\2\u00f9\u00fa"+
		"\7k\2\2\u00fa\u00fb\7o\2\2\u00fb\u00fc\7r\2\2\u00fc\u00fd\7q\2\2\u00fd"+
		"\u00fe\7t\2\2\u00fe\u00ff\7v\2\2\u00ff.\3\2\2\2\u0100\u0101\7?\2\2\u0101"+
		"\60\3\2\2\2\u0102\u0103\7=\2\2\u0103\62\3\2\2\2\u0104\u0105\7.\2\2\u0105"+
		"\64\3\2\2\2\u0106\u0107\7p\2\2\u0107\u0108\7g\2\2\u0108\u0109\7y\2\2\u0109"+
		"\u010a\7r\2\2\u010a\u010b\7c\2\2\u010b\u010c\7k\2\2\u010c\u010d\7t\2\2"+
		"\u010d\66\3\2\2\2\u010e\u010f\7e\2\2\u010f\u0110\7c\2\2\u0110\u0111\7"+
		"n\2\2\u0111\u0112\7n\2\2\u01128\3\2\2\2\u0113\u0114\7h\2\2\u0114\u0115"+
		"\7u\2\2\u0115\u0116\7v\2\2\u0116:\3\2\2\2\u0117\u0118\7u\2\2\u0118\u0119"+
		"\7p\2\2\u0119\u011a\7f\2\2\u011a<\3\2\2\2\u011b\u011c\7r\2\2\u011c\u011d"+
		"\7c\2\2\u011d\u011e\7k\2\2\u011e\u011f\7t\2\2\u011f>\3\2\2\2\u0120\u0121"+
		"\7v\2\2\u0121\u0122\7t\2\2\u0122\u0123\7w\2\2\u0123\u0124\7g\2\2\u0124"+
		"@\3\2\2\2\u0125\u0126\7h\2\2\u0126\u0127\7c\2\2\u0127\u0128\7n\2\2\u0128"+
		"\u0129\7u\2\2\u0129\u012a\7g\2\2\u012aB\3\2\2\2\u012b\u012c\7k\2\2\u012c"+
		"\u012d\7p\2\2\u012d\u012e\7v\2\2\u012eD\3\2\2\2\u012f\u0130\7d\2\2\u0130"+
		"\u0131\7q\2\2\u0131\u0132\7q\2\2\u0132\u0133\7n\2\2\u0133F\3\2\2\2\u0134"+
		"\u0135\7e\2\2\u0135\u0136\7j\2\2\u0136\u0137\7c\2\2\u0137\u0138\7t\2\2"+
		"\u0138H\3\2\2\2\u0139\u013a\7u\2\2\u013a\u013b\7v\2\2\u013b\u013c\7t\2"+
		"\2\u013c\u013d\7k\2\2\u013d\u013e\7p\2\2\u013e\u013f\7i\2\2\u013fJ\3\2"+
		"\2\2\u0140\u0141\7x\2\2\u0141\u0142\7c\2\2\u0142\u0143\7t\2\2\u0143L\3"+
		"\2\2\2\u0144\u0145\7x\2\2\u0145\u0146\7q\2\2\u0146\u0147\7k\2\2\u0147"+
		"\u0148\7f\2\2\u0148N\3\2\2\2\u0149\u014a\7#\2\2\u014aP\3\2\2\2\u014b\u014c"+
		"\7n\2\2\u014c\u014d\7g\2\2\u014d\u014e\7p\2\2\u014eR\3\2\2\2\u014f\u0150"+
		"\7q\2\2\u0150\u0151\7t\2\2\u0151\u0152\7f\2\2\u0152T\3\2\2\2\u0153\u0154"+
		"\7e\2\2\u0154\u0155\7j\2\2\u0155\u0156\7t\2\2\u0156V\3\2\2\2\u0157\u0158"+
		"\7(\2\2\u0158X\3\2\2\2\u0159\u015a\7-\2\2\u015aZ\3\2\2\2\u015b\u015c\7"+
		"/\2\2\u015c\\\3\2\2\2\u015d\u015e\7,\2\2\u015e^\3\2\2\2\u015f\u0160\7"+
		"\61\2\2\u0160`\3\2\2\2\u0161\u0162\7\'\2\2\u0162b\3\2\2\2\u0163\u0164"+
		"\7@\2\2\u0164\u0165\7?\2\2\u0165d\3\2\2\2\u0166\u0167\7@\2\2\u0167f\3"+
		"\2\2\2\u0168\u0169\7>\2\2\u0169\u016a\7?\2\2\u016ah\3\2\2\2\u016b\u016c"+
		"\7>\2\2\u016cj\3\2\2\2\u016d\u016e\7?\2\2\u016e\u016f\7?\2\2\u016fl\3"+
		"\2\2\2\u0170\u0171\7#\2\2\u0171\u0172\7?\2\2\u0172n\3\2\2\2\u0173\u0174"+
		"\7(\2\2\u0174\u0175\7(\2\2\u0175p\3\2\2\2\u0176\u0177\7~\2\2\u0177\u0178"+
		"\7~\2\2\u0178r\3\2\2\2\u0179\u017a\7*\2\2\u017at\3\2\2\2\u017b\u017c\7"+
		"+\2\2\u017cv\3\2\2\2\u017d\u017e\7]\2\2\u017ex\3\2\2\2\u017f\u0180\7_"+
		"\2\2\u0180z\3\2\2\2\u0181\u0182\7}\2\2\u0182|\3\2\2\2\u0183\u0184\7\177"+
		"\2\2\u0184~\3\2\2\2\u0185\u0187\5\u0081A\2\u0186\u0185\3\2\2\2\u0187\u0188"+
		"\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u0080\3\2\2\2\u018a"+
		"\u018b\t\2\2\2\u018b\u0082\3\2\2\2\u018c\u018e\t\3\2\2\u018d\u018c\3\2"+
		"\2\2\u018e\u0192\3\2\2\2\u018f\u0191\t\4\2\2\u0190\u018f\3\2\2\2\u0191"+
		"\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0084\3\2"+
		"\2\2\u0194\u0192\3\2\2\2\u0195\u0197\t\5\2\2\u0196\u0195\3\2\2\2\u0197"+
		"\u019b\3\2\2\2\u0198\u019a\t\4\2\2\u0199\u0198\3\2\2\2\u019a\u019d\3\2"+
		"\2\2\u019b\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u0086\3\2\2\2\u019d"+
		"\u019b\3\2\2\2\u019e\u01a0\t\6\2\2\u019f\u019e\3\2\2\2\u01a0\u01a1\3\2"+
		"\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3"+
		"\u01a4\bD\2\2\u01a4\u0088\3\2\2\2\u01a5\u01a6\t\7\2\2\u01a6\u008a\3\2"+
		"\2\2\u01a7\u01ab\n\b\2\2\u01a8\u01a9\7^\2\2\u01a9\u01ab\5\u0089E\2\u01aa"+
		"\u01a7\3\2\2\2\u01aa\u01a8\3\2\2\2\u01ab\u008c\3\2\2\2\u01ac\u01b0\7$"+
		"\2\2\u01ad\u01af\5\u008bF\2\u01ae\u01ad\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0"+
		"\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01b0\3\2"+
		"\2\2\u01b3\u01b4\7$\2\2\u01b4\u008e\3\2\2\2\u01b5\u01b6\7)\2\2\u01b6\u01b7"+
		"\5\u008bF\2\u01b7\u01b8\7)\2\2\u01b8\u0090\3\2\2\2\u01b9\u01bd\7%\2\2"+
		"\u01ba\u01bc\13\2\2\2\u01bb\u01ba\3\2\2\2\u01bc\u01bf\3\2\2\2\u01bd\u01be"+
		"\3\2\2\2\u01bd\u01bb\3\2\2\2\u01be\u01c0\3\2\2\2\u01bf\u01bd\3\2\2\2\u01c0"+
		"\u01c1\t\t\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\bI\2\2\u01c3\u0092\3\2"+
		"\2\2\16\2\u0188\u018d\u0190\u0192\u0196\u0199\u019b\u01a1\u01aa\u01b0"+
		"\u01bd\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}