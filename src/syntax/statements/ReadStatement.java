package syntax.statements;

import syntax.expression.Id;
import visitor.Visitor;

import java.util.ArrayList;

public class ReadStatement extends Statement {
    private ArrayList<Id> variables;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param variables
     */
    public ReadStatement(int leftPosition, int rightPosition, ArrayList<Id> variables) {
        super(leftPosition, rightPosition);
        this.variables = variables;
    }

    /**
     * @return the variables
     */
    public ArrayList<Id> getVariables() {
        return variables;
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
