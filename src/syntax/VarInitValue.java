package syntax;

import syntax.expression.Expr;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class VarInitValue extends ASTNode{
    private Expr expression;
    /**
     * Initialize a new generic AST node.
     *  @param leftPosition  the left position
     * @param rightPosition the right position
     * @param expression the expression
     */
    public VarInitValue(Location leftPosition, Location rightPosition, Expr expression) {
        super(leftPosition, rightPosition);
        this.expression = expression;
    }

    /**
     * @return the expression
     */
    public Expr getExpression() {
        return expression;
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
