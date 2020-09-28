package tester;
import generated.Lexer;
import generated.ParserSym;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import lexical.StringTable;

import java.io.IOException;

public class LexerTest {
    public static void main(String[] args) throws IOException {
        StringTable table = new StringTable();
        ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
        Lexer lexicalAnalyzer = new Lexer(table, complexSymbolFactory);
        String filePath = args[0];

        if (lexicalAnalyzer.initialize(filePath)) {
            Symbol token;
            try {
                while ((token = lexicalAnalyzer.next_token()) != null) {
                    if(token.sym == ParserSym.EOF) {
                        break;
                    }
                    System.out.println("<" + ParserSym.terminalNames[token.sym] + (token.value == null ? "" : ", "+token.value) + ">");
                }
            } catch (Exception e) {
                System.out.println("Parsing process ended!!");
            }

        } else {
            System.out.println("File not found!!");
        }
        System.out.println("Lexical analysis complete!");
    }
}
