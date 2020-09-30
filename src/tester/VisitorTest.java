package tester;

import generated.Lexer;
import generated.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.StringTable;
import org.w3c.dom.Document;
import syntax.Program;
import utils.*;
import visitor.ConcreteVisitor;

public class VisitorTest {
    public static void main(String[] args) throws Exception {
        StringTable table = new StringTable();
        ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
        Lexer lexer = new Lexer(table, complexSymbolFactory);
        if (lexer.initialize(args[0])){
            Parser parser = new Parser(lexer,complexSymbolFactory);
            Program program= (Program) parser.parse().value;

            //The document
            Document document = XMLUtils.createDocument();
            ConcreteVisitor visitor = new ConcreteVisitor();

            //Visit
            program.accept(visitor, document);

            //Save output
            XMLUtils.writeDocument(document, args[0]);
        }
        else {
            System.out.println("File not found");
        }
    }


}
