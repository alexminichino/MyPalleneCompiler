package syntax;

import visitor.Visitor;

import java.util.ArrayList;

public class Global extends ASTNode{
    private ArrayList<VarDecl> varDecls;
    /**
     * Initialize a new Global with var decls.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param varDecls the variable declaration list
     */
    public Global(int leftPosition, int rightPosition, ArrayList<VarDecl> varDecls) {
        super(leftPosition, rightPosition);
        this.varDecls = varDecls;
    }

    /**
     * Initialize a new Global without var decls.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public Global(int leftPosition, int rightPosition) {
        super(leftPosition, rightPosition);
    }

    /**
     * @return the variable declaration list
     */
    public ArrayList<VarDecl> getVarDecls() {
        return varDecls;
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
