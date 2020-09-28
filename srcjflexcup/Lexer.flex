//User code section
package generated;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.Symbol;
import lexical.StringTable;
/**
 * @author Alexander Minichino
 * Lexical specification of myPallene
 */
%%
//Options and declarations section
/*tells JFlex to give the generated class the name Lexer and to write the code to a file Lexer.java*/
%class Lexer
/*Makes the generated class public (the class is only accessible in its own package by default)*/
%public
/*customises the name of the CUP generated class or interface containing the names of terminal tokens*/
%cupsym ParserSym
/*defines the set of characters the scanner will work on*/
%unicode
/*switches to CUP compatibility mode to interface with a CUP generated parser*/
%cup
/*switches line counting on*/
%line
/*switches column counting on*/
%column

/*The code included in %{...%} is copied verbatim into the generated lexer class source*/
%{
    private StringBuffer string;
    private StringTable stringTable;
    private ComplexSymbolFactory complexSymbolFactory;

    private Symbol createToken(String name, int type) {
       String name = ParserSym.terminalNames[type];
       return complexSymbolFactory.newSymbol(name, type, new Location(yyline + 1, yycolumn + 1 - yylength()), new Location(yyline + 1, yycolumn + 1));
    }
    private Symbol createToken(int type, Object value) {
        String name = ParserSym.terminalNames[type];
        stringTable.installLexeme(value.toString());
        return complexSymbolFactory.newSymbol(name, type, new Location(yyline + 1, yycolumn + 1 - yylength()), new Location(yyline + 1, yycolumn + 1), value);
    }

    /* Initilizes the reader to reading and checks errors */
    public boolean initialize(String filePath) {
        try {
            /* the input device */
            this.zzReader = new java.io.FileReader(filePath);
            return true;
        } catch (java.io.FileNotFoundException e) {
            return false;
        }
    }

    public  Lexer(StringTable stringTable, ComplexSymbolFactory complexSymbolFactory) {
        this.stringTable = stringTable;
        this.complexSymbolFactory = complexSymbolFactory;
        this.string = new StringBuffer();
    }
%}

%eofval{
	return createToken("EOF",ParserSym.EOF);
%eofval}
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

IntegerLiteral = 0 | [1-9][0-9]*
FloatLiteral = (0|[1-9][0-9]*)(\.[0-9]*)?([eE][+\-]?[0-9]+)?

/*Declares a lexical state STRING */
%state STRING

%%
// Lexical rules section

// The lexical state YYINITIAL is predefined and is also the state in which the lexer begins scanning
<YYINITIAL> {
  /* keywords */
  "if"		        { return createToken(ParserSym.IF); }
  "then"		    { return createToken(ParserSym.THEN); }
  "else"		    { return createToken(ParserSym.ELSE); }
  "function"        { return createToken(ParserSym.FUNCTION); }
  "end"		        { return createToken(ParserSym.END); }
  "global"		    { return createToken(ParserSym.GLOBAL); }
  "local"		    { return createToken(ParserSym.LOCAL); }
  "for"		        { return createToken(ParserSym.FOR); }
  "while"		    { return createToken(ParserSym.WHILE); }
  "do"		        { return createToken(ParserSym.DO); }
  "return"		    { return createToken(ParserSym.RETURN); }
  "true"		    { return createToken(ParserSym.TRUE); }
  "false"		    { return createToken(ParserSym.FALSE); }
  "#"		        { return createToken(ParserSym.SHARP); }
  "<=="		        { return createToken(ParserSym.READ); }
  "==>"		        { return createToken(ParserSym.WRITE); }



  /*Types keyword*/
  "nil"		        { return createToken(ParserSym.NIL); }
  "int"		        { return createToken(ParserSym.INT); }
  "float"		    { return createToken(ParserSym.FLOAT); }
  "bool"		    { return createToken(ParserSym.BOOL); }
  "string"		    { return createToken(ParserSym.STRING); }

  /* literals */
  {IntegerLiteral}  { return createToken(ParserSym.INT_CONST,Integer.parseInt(yytext())); }
  {FloatLiteral}    { return createToken(ParserSym.FLOAT_CONST,Float.parseFloat(yytext())); }
  \"                { string.setLength(0); yybegin(STRING); }

  /*Relation op*/
  "<"		        { return createToken(ParserSym.LT); }
  "<="		        { return createToken(ParserSym.LE); }
  "=="		        { return createToken(ParserSym.EQ); }
  "!="		        { return createToken(ParserSym.NE); }
  ">"		        { return createToken(ParserSym.GT); }
  ">="		        { return createToken(ParserSym.GE); }
  "="		        { return createToken(ParserSym.ASSIGN); }
  "->"		        { return createToken(ParserSym.ARROW); }
  "and"		        { return createToken(ParserSym.AND); }
  "or"		        { return createToken(ParserSym.OR); }
  "not"		        { return createToken(ParserSym.NOT); }
  "nop"		        { return createToken(ParserSym.NOP); }

  /*Arithmetic op */
  "+"		        { return createToken(ParserSym.PLUS); }
  "-"		        { return createToken(ParserSym.MINUS); }
  "*"		        { return createToken(ParserSym.TIMES); }
  "/"		        { return createToken(ParserSym.DIV); }

  /*Separators*/
  "("		        { return createToken(ParserSym.LPAR); }
  ")"		        { return createToken(ParserSym.RPAR); }
  "{"		        { return createToken(ParserSym.BLPAR); }
  "}"		        { return createToken(ParserSym.BRPAR); }
  "["		        { return createToken(ParserSym.SLPAR); }
  "]"		        { return createToken(ParserSym.SRPAR); }
  ","		        { return createToken(ParserSym.COMMA); }
  ";"		        { return createToken(ParserSym.SEMI); }
  ":"		        { return createToken(ParserSym.COLON); }

  /* identifiers */
  {Identifier}      { return createToken(ParserSym.ID, yytext()); } //yytext is used to get the next token from the input

  /* comments */
  {Comment}         { /* ignore */ }

  /* whitespace */
  {WhiteSpace}      { /* ignore */ }

}

/*If the scanner is in lexical state STRING, only
  expressions that are preceded by the start condition <STRING> can be matched*/
<STRING> {
  \" {
    yybegin(YYINITIAL);
    return createToken(ParserSym.STRING_CONST,
    string.toString());
  }
  [^\n\r\"\\]+ { string.append( yytext() ); }
  \\t { string.append('\t'); }
  \\n { string.append('\n'); }
  \\r { string.append('\r'); }
  \\\" { string.append('\"'); }
  \\  { string.append('\\'); }
}


/* error fallback */
[^] {
    throw new RuntimeException("Invalid input <" + yytext() + "> on LINE: " + yyline + " COLUMN: " + yycolumn);
}