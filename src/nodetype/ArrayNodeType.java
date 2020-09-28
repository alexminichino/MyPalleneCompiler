package nodetype;

import java.util.Objects;

public class ArrayNodeType implements NodeType{
    private PrimitiveNodeType typeElement;


    public ArrayNodeType(PrimitiveNodeType typeElement) {
        this.typeElement = typeElement;
    }


    public PrimitiveNodeType getTypeElement() {
        return typeElement;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        ArrayNodeType other = (ArrayNodeType) obj;
        return Objects.equals(typeElement, other.typeElement);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + typeElement.toString() + "}");
        return sb.toString();
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
