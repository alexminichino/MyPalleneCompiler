package tester;

import generated.Lexer;
import generated.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.StringTable;

public class ParserTest {

    public static void main(String[] args) throws Exception {
        StringTable table = new StringTable();
        ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
        Lexer lexer = new Lexer(table,complexSymbolFactory);
        if (lexer.initialize(args[0])){
            Parser parser = new Parser(lexer,complexSymbolFactory);
            System.out.println(parser.parse().value);
        }
        else {
            System.out.println("File not found");
        }
    }
}
