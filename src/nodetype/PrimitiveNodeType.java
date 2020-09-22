package nodetype;

public enum PrimitiveNodeType implements NodeType{

    INT {
        @Override
        public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
            switch(type) {
                case INT:
                    return INT;
                case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
            switch(type) {
                case INT:
                    return INT;
                case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
            switch(type){
                case INT:
                    return INT;
                case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
            switch(type){
                case INT:
                    return INT;
                case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
            switch(type) {
                case INT: case FLOAT:
                    return BOOL;
                default:
                    return NIL;
            }
        }

    },



    FLOAT {
        @Override
        public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
            switch(type){
                case INT: case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
            switch(type){
                case INT: case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
            switch(type){
                case INT: case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
            switch(type) {
                case INT: case FLOAT:
                    return FLOAT;
                default:
                    return NIL;
            }
        }

        @Override
        public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
            switch(type){
                case INT: case FLOAT:
                    return BOOL;
                default:
                    return NIL;
            }
        }

    },

    BOOL {
        @Override
        public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
            switch(type){
                case BOOL:
                    return BOOL;
                default:
                    return NIL;
            }
        }
    },



    NIL {
        @Override
        public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
            return NIL;
        }

    },

    STRING {
        @Override
        public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
            return NIL;
        }

        @Override
        public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
            switch(type){
                case STRING:
                    return BOOL;
                default:
                    return NIL;
            }
        }
    };

    public String getCType(){
        switch(this){
            case STRING:
                return "char *";
            case FLOAT:
                return "float";
            case BOOL:
                return "bool";
            case INT:
                return "int";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        switch(this){
            case BOOL:
                return "bool";
            case INT:
                return "int";
            case FLOAT:
                return "float";
            case STRING:
                return "string";
            default:
                return "void";
        }
    }
}
