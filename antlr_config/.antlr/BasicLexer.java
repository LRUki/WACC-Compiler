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
		DONE=17, PLUS=18, MINUS=19, OPEN_PARENTHESES=20, CLOSE_PARENTHESES=21, 
		INTEGER=22;
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
			"PLUS", "MINUS", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "DIGIT", "INTEGER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi; '", 
			"'while'", "'do'", "'done'", "'+'", "'-'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP_TOKEN", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"PLUS", "MINUS", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "INTEGER"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u0096\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\6\30\u0093\n\30\r\30\16\30\u0094\2\2\31\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\2/\30\3\2\2\2\u0095\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\67\3\2\2\2\7;\3\2\2\2\t>\3\2\2\2\13"+
		"C\3\2\2\2\rH\3\2\2\2\17M\3\2\2\2\21T\3\2\2\2\23Y\3\2\2\2\25_\3\2\2\2\27"+
		"g\3\2\2\2\31j\3\2\2\2\33o\3\2\2\2\35t\3\2\2\2\37y\3\2\2\2!\177\3\2\2\2"+
		"#\u0082\3\2\2\2%\u0087\3\2\2\2\'\u0089\3\2\2\2)\u008b\3\2\2\2+\u008d\3"+
		"\2\2\2-\u008f\3\2\2\2/\u0092\3\2\2\2\61\62\7d\2\2\62\63\7g\2\2\63\64\7"+
		"i\2\2\64\65\7k\2\2\65\66\7p\2\2\66\4\3\2\2\2\678\7g\2\289\7p\2\29:\7f"+
		"\2\2:\6\3\2\2\2;<\7k\2\2<=\7u\2\2=\b\3\2\2\2>?\7u\2\2?@\7m\2\2@A\7k\2"+
		"\2AB\7r\2\2B\n\3\2\2\2CD\7t\2\2DE\7g\2\2EF\7c\2\2FG\7f\2\2G\f\3\2\2\2"+
		"HI\7h\2\2IJ\7t\2\2JK\7g\2\2KL\7g\2\2L\16\3\2\2\2MN\7t\2\2NO\7g\2\2OP\7"+
		"v\2\2PQ\7w\2\2QR\7t\2\2RS\7p\2\2S\20\3\2\2\2TU\7g\2\2UV\7z\2\2VW\7k\2"+
		"\2WX\7v\2\2X\22\3\2\2\2YZ\7r\2\2Z[\7t\2\2[\\\7k\2\2\\]\7p\2\2]^\7v\2\2"+
		"^\24\3\2\2\2_`\7r\2\2`a\7t\2\2ab\7k\2\2bc\7p\2\2cd\7v\2\2de\7n\2\2ef\7"+
		"p\2\2f\26\3\2\2\2gh\7k\2\2hi\7h\2\2i\30\3\2\2\2jk\7v\2\2kl\7j\2\2lm\7"+
		"g\2\2mn\7p\2\2n\32\3\2\2\2op\7g\2\2pq\7n\2\2qr\7u\2\2rs\7g\2\2s\34\3\2"+
		"\2\2tu\7h\2\2uv\7k\2\2vw\7=\2\2wx\7\"\2\2x\36\3\2\2\2yz\7y\2\2z{\7j\2"+
		"\2{|\7k\2\2|}\7n\2\2}~\7g\2\2~ \3\2\2\2\177\u0080\7f\2\2\u0080\u0081\7"+
		"q\2\2\u0081\"\3\2\2\2\u0082\u0083\7f\2\2\u0083\u0084\7q\2\2\u0084\u0085"+
		"\7p\2\2\u0085\u0086\7g\2\2\u0086$\3\2\2\2\u0087\u0088\7-\2\2\u0088&\3"+
		"\2\2\2\u0089\u008a\7/\2\2\u008a(\3\2\2\2\u008b\u008c\7*\2\2\u008c*\3\2"+
		"\2\2\u008d\u008e\7+\2\2\u008e,\3\2\2\2\u008f\u0090\4\62;\2\u0090.\3\2"+
		"\2\2\u0091\u0093\5-\27\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\60\3\2\2\2\4\2\u0094\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}