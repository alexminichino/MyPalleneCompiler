package starter;

import errors.ErrorHandler;
import generated.Lexer;
import generated.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.StringTable;
import org.w3c.dom.Document;
import semantic.SymbolTable;
import syntax.Program;
import utils.CUtils;
import utils.XMLUtils;
import visitor.*;

public class MyPallene {

    private StringTable table;
    private ComplexSymbolFactory complexSymbolFactory ;
    private Lexer lexer ;
    private ErrorHandler errorHandler ;
    private SymbolTable symbolTable ;
    private String sourcePath;

    public MyPallene(String sourcePath) {
        table = new StringTable();
        complexSymbolFactory = new ComplexSymbolFactory();
        lexer = new Lexer(table, complexSymbolFactory);
        errorHandler =  new ErrorHandler();
        symbolTable = new SymbolTable(table);
        this.sourcePath = sourcePath;
    }

    public void runCompiler() throws Exception {
        if (lexer.initialize(sourcePath)) {
            Parser parser = new Parser(lexer, complexSymbolFactory);
            Program program = (Program) parser.parse().value;
            log("PARSING OK\n" + program);

            //Visitors
            ConcreteVisitor xmlVisitor = new ConcreteVisitor();
            NamingCheckVisitor namingCheckVisitor = new NamingCheckVisitor(errorHandler);
            ScopeConcreteVisitor scopeCheckerVisitor = new ScopeConcreteVisitor(errorHandler);
            TypeCheckerConcreteVisitor typeCheckerVisitor = new TypeCheckerConcreteVisitor(errorHandler);

            //C Program Template
            String cTemplate = CUtils.getTemplate();
            CodeGeneratorConcreteVisitor codeGeneratorConcreteVisitor = new CodeGeneratorConcreteVisitor(cTemplate);

            log("Create template XML");
            Document xmlTemplate = XMLUtils.createDocument();
            log("XML visit");
            program.accept(xmlVisitor, xmlTemplate);
            log("Save XML output");
            XMLUtils.writeDocument(xmlTemplate, sourcePath);

            log("Naming checking...");
            boolean namingCheck = program.accept(namingCheckVisitor, symbolTable);
            log("Result naming checking: " + ((namingCheck) ? "SUCCESS" : "ERROR"));
            log("Current Symbol Table: \n" + symbolTable);
            if (errorHandler.hasErrors()) {
                log("Errors:");
                log(errorHandler.getErrorStackTrace());
            }

            symbolTable.reset();

            log("Scope checking...");
            boolean scopeCheckingResult = program.accept(scopeCheckerVisitor, symbolTable);
            log("Result scope checking: " + ((scopeCheckingResult) ? "SUCCESS" : "ERROR"));
            log("Current Symbol Table: \n" + symbolTable);
            if (errorHandler.hasErrors()) {
                log("Errors:");
                log(errorHandler.getErrorStackTrace());
            }

            symbolTable.reset();

            log("Type checking...");
            program.accept(typeCheckerVisitor, symbolTable);
            log("Current Symbol Table: \n" + symbolTable);
            if (errorHandler.hasErrors()) {
                log("Errors:");
                log(errorHandler.getErrorStackTrace());
            }

            symbolTable.reset();
            log("TEMPLATE:\n\n" + cTemplate + "\n\n\n");
            log("Code generation...");
            String generated = program.accept(codeGeneratorConcreteVisitor, symbolTable);
            log("Current Symbol Table: \n" + symbolTable);
            if (errorHandler.hasErrors()) {
                log("Errors:");
                log(errorHandler.getErrorStackTrace());
            }
            log("Generated Code:\n\n" + generated);
            log("Saving file");
            CUtils.write(generated, sourcePath);
            symbolTable.reset();


        } else {
            log("File not found!");
        }
    }
    private void log(String message){
        System.out.println("\n"+message);
    }
}
