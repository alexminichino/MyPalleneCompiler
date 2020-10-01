package syntax.statements;

import syntax.expression.Expr;
import syntax.expression.Id;
import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class CallFunctionStatement extends Statement {
    private Id id;
    private ArrayList<Expr> expressionsParams;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param id
     * @param expressionsParams
     */
    public CallFunctionStatement(Location leftPosition, Location rightPosition, Id id, ArrayList<Expr> expressionsParams) {
        super(leftPosition, rightPosition);
        this.id = id;
        this.expressionsParams = expressionsParams;
    }

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param id
     */
    public CallFunctionStatement(Location leftPosition, Location rightPosition, Id id) {
        super(leftPosition, rightPosition);
        this.id = id;
        this.expressionsParams = new ArrayList<>();
    }

    /**
     * @return the id
     */
    public Id getId() {
        return id;
    }

    /**
     * @return the expressions of params
     */
    public ArrayList<Expr> getExpressionsParams() {
        return expressionsParams;
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
