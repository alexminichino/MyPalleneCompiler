package syntax;

import java_cup.runtime.ComplexSymbolFactory;
import visitor.Visitor;

public class Variable extends ASTNode implements Leaf<String>{

    private String name;

    public Variable(ComplexSymbolFactory.Location leftPosition, ComplexSymbolFactory.Location rightPosition, String name) {
        super(leftPosition, rightPosition);
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }

    public String getName(){
        return name;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {
        return visitor.visit(this, arg);
    }
}
