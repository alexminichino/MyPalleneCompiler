package syntax.types;

import java_cup.runtime.ComplexSymbolFactory;
import nodetype.ArrayFloatNodeType;
import nodetype.ArrayNodeType;
import nodetype.NodeType;
import nodetype.PrimitiveNodeType;

public class ArrayFloatType extends ArrayType{
    private int size;

    public ArrayFloatType(ComplexSymbolFactory.Location leftPosition, ComplexSymbolFactory.Location rightPosition, Type type, int size) {
        super(leftPosition, rightPosition, type);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public NodeType typeFactory() {
        return new ArrayFloatNodeType((PrimitiveNodeType) this.getType().typeFactory(), size);
    }
}
