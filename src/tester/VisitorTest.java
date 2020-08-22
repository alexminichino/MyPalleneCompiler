package tester;

import generated.Lexer;
import generated.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.StringTable;
import org.w3c.dom.Document;
import syntax.Program;
import visitor.ConcreteVisitor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class VisitorTest {
    public static void main(String[] args) throws Exception {
        StringTable table = new StringTable();
        ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
        Lexer lexer = new Lexer(table, complexSymbolFactory);
        if (lexer.initialize(args[0])){
            Parser parser = new Parser(lexer);
            Program program= (Program) parser.parse().value;

            //The document
            Document document = createDocument();

            ConcreteVisitor visitor = new ConcreteVisitor();

            //Visit
            program.accept(visitor, document);

            //Save output
            writeDocument(document, args[0]);
        }
        else {
            System.out.println("File not found");
        }
    }

    private static Document createDocument(){
        //Create document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            System.err.println("Error in XML document creation"+ e.getMessage());
        }
        Document document = docBuilder.newDocument();
        return document;
    }

    private static void writeDocument(Document document, String fileName){
        //transform the DOM Object to an XML File

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName+".xml"));
            transformer.transform(domSource, streamResult);
        }
        catch (TransformerConfigurationException e) {
            System.err.println("Error in XML document transorm configuration"+ e.getMessage());
        }
        catch (TransformerException e) {
            System.err.println("Error in XML document transorm configuration"+ e.getMessage());
        }
    }
}
