package nodetype;

public class ArrayFloatNodeType extends ArrayNodeType{

    private int size;

    public ArrayFloatNodeType(PrimitiveNodeType typeElement, int size) {
        super(typeElement);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FloatArray: {" + getTypeElement().toString() + "}");
        return sb.toString();
    }
}
