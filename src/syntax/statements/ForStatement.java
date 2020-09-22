package syntax.statements;

import syntax.Variable;
import syntax.expression.Expr;
import syntax.expression.Id;
import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ForStatement  extends  Statement{

    private Variable variable;
    private Expr initialConditionExpression;
    private Expr loopConditionExpression;
    private ArrayList<Statement> statements;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param variable the id
     * @param initialConditionExpression
     * @param loopConditionExpression
     * @param statements
     */
    public ForStatement(Location leftPosition, Location rightPosition, Variable variable, Expr initialConditionExpression, Expr loopConditionExpression, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.variable = variable;
        this.initialConditionExpression = initialConditionExpression;
        this.loopConditionExpression = loopConditionExpression;
        this.statements = statements;
    }

    /**
     * @return
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * @return
     */
    public Expr getInitialConditionExpression() {
        return initialConditionExpression;
    }

    /**
     * @return
     */
    public Expr getLoopConditionExpression() {
        return loopConditionExpression;
    }

    /**
     * @return
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
