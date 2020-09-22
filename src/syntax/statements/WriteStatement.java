package syntax.statements;

import syntax.expression.Expr;
import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class WriteStatement extends Statement{
    private ArrayList<Expr> expressions;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param expressions
     */
    public WriteStatement(Location leftPosition, Location rightPosition, ArrayList<Expr> expressions) {
        super(leftPosition, rightPosition);
        this.expressions = expressions;
    }

    /**
     * @return the expressions list
     */
    public ArrayList<Expr> getExpressions() {
        return expressions;
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
