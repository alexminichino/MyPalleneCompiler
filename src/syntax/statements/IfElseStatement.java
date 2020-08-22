package syntax.statements;

import syntax.expression.Expr;
import visitor.Visitor;

import java.util.ArrayList;

public class IfElseStatement extends Statement{
    private Expr condition;
    private ArrayList<Statement> thenStatement;
    private ArrayList<Statement> elseStatement;


    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param condition
     * @param thenStatement
     * @param elseStatement
     */
    public IfElseStatement(int leftPosition, int rightPosition, Expr condition, ArrayList<Statement> thenStatement, ArrayList<Statement> elseStatement) {
        super(leftPosition, rightPosition);
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
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
     * @return
     */
    public ArrayList<Statement> getElseStatement() {
        return elseStatement;
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
