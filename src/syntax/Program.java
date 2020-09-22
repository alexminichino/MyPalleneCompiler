package syntax;

import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class Program extends ASTNode {
    private Global global;
    private ArrayList<Function> functions;

    /**
     * Initialize a new Program AST node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param global the global
     * @param functions functions list
     */
    public Program(Location leftPosition, Location rightPosition, Global global, ArrayList<Function> functions) {
        super(leftPosition, rightPosition);
        this.global = global;
        this.functions = functions;
    }

    /**
     * @return the global
     */
    public Global getGlobal() {
        return global;
    }

    /**
     * @return the functions
     */
    public ArrayList<Function> getFunctions() {
        return functions;
    }

    /**
     * Implement interface method
     * @param visitor the visitor
     * @param arg argoment
     * @param <T> Visitor param T
     * @param <P> Visitor param P
     * @return the visited element
     */
    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {
        return visitor.visit(this, arg);
    }
}
