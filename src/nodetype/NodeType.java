package nodetype;

public interface NodeType {
    PrimitiveNodeType verifyAddition(PrimitiveNodeType type);
    PrimitiveNodeType verifySubtraction(PrimitiveNodeType type);
    PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type);
    PrimitiveNodeType verifyDivision(PrimitiveNodeType type);
    PrimitiveNodeType verifyRelation(PrimitiveNodeType type);
}
