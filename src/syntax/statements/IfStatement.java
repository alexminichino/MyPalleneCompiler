package syntax.statements;

import syntax.expression.Expr;
import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class IfStatement extends Statement{
    private Expr condition;
    private ArrayList<Statement> thenStatement;



    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param condition
     * @param thenStatement
     */
    public IfStatement(Location leftPosition, Location rightPosition, Expr condition, ArrayList<Statement> thenStatement) {
        super(leftPosition, rightPosition);
        this.condition = condition;
        this.thenStatement = thenStatement;
    }

    /**
     * @return
     */
    public Expr getCondition() {
        return condition;
    }

    /**
     * @return
     */
    public ArrayList<Statement> getThenStatement() {
        return thenStatement;
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
