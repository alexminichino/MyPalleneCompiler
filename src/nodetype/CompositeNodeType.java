package nodetype;

import java.util.List;

public class CompositeNodeType  implements  NodeType{
    private final List<NodeType> types;

    public CompositeNodeType(List<NodeType> types) {
        this.types = types;
    }

    @Override
    public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    public void addNodeType(NodeType type) {
        types.add(type);
    }
}
