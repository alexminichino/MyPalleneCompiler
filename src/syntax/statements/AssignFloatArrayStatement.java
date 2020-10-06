package syntax.statements;

import java_cup.runtime.ComplexSymbolFactory;
import syntax.expression.Id;
import visitor.Visitor;

public class AssignFloatArrayStatement extends Statement{
    private Id leftId;
    private Id rightId;

    public AssignFloatArrayStatement(ComplexSymbolFactory.Location leftPosition, ComplexSymbolFactory.Location rightPosition, Id leftId, Id rightId) {
        super(leftPosition, rightPosition);
        this.leftId = leftId;
        this.rightId = rightId;
    }

    public Id getLeftId() {
        return leftId;
    }

    public Id getRightId() {
        return rightId;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {        return visitor.visit(this, arg);

    }
}
