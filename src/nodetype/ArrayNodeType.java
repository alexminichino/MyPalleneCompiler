package nodetype;

public class ArrayNodeType implements NodeType{
    private PrimitiveNodeType typeElement;


    public ArrayNodeType(PrimitiveNodeType typeElement) {
        this.typeElement = typeElement;
    }


    public PrimitiveNodeType getTypeElement() {
        return typeElement;
    }


    @Override
    public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
        return null;
    }
}
