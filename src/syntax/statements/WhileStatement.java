package syntax.statements;

import syntax.expression.Expr;
import visitor.Visitor;

import java.util.ArrayList;

public class WhileStatement extends Statement {
    private Expr conditionExpression;
    private ArrayList<Statement> statements;


    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param conditionExpression
     * @param statements
     */
    public WhileStatement(int leftPosition, int rightPosition, Expr conditionExpression, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.conditionExpression = conditionExpression;
        this.statements = statements;
    }

    /**
     * @return the condition
     */
    public Expr getConditionExpression() {
        return conditionExpression;
    }

    /**
     * @return the statements list
     */
    public ArrayList<Statement> getStatements() {
        return statements;
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
