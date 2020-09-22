package syntax.expression;

import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class FunctionCall extends Expr {
    private Id id;
    private ArrayList<Expr> exprs;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param id id
     * @param exprs  expressions list
     */
    public FunctionCall(Location leftPosition, Location rightPosition, Id id, ArrayList<Expr> exprs) {
        super(leftPosition, rightPosition);
        this.id = id;
        this.exprs = exprs;
    }

    /**
     * @return the id
     */
    public Id getId() {
        return id;
    }

    /**
     * @return the expression list
     */
    public ArrayList<Expr> getExprs() {
        return exprs;
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
