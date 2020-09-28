package syntax;

import nodetype.CompositeNodeType;
import nodetype.NodeType;
import syntax.expression.Id;
import syntax.statements.Statement;
import syntax.types.Type;
import visitor.Visitor;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class Function extends ASTNode {
    private Type type;
    private Variable variable;
    private ArrayList<Statement> statements;
    private ArrayList<ParDecl> parDecls;

    /**
     * Initialize a new Function with params.
     *  @param leftPosition  the left position
     * @param rightPosition the right position
     * @param variable the variable
     * @param parDecls the pardecl
     * @param type the type
     * @param statements list of statement
     */
    public Function(Location leftPosition, Location rightPosition, Variable variable, ArrayList<ParDecl> parDecls, Type type, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.type = type;
        this.variable = variable;
        this.statements = statements;
        this.parDecls = parDecls;
    }

    /**
     * Initialize a new Function without params.
     *  @param leftPosition  the left position
     * @param rightPosition the right position
     * @param variable the variable
     * @param type the type
     * @param statements list of statement
     */
    public Function(Location leftPosition, Location rightPosition, Variable variable, Type type, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.type = type;
        this.variable = variable;
        this.statements = statements;
        this.parDecls = new ArrayList<ParDecl>();
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the variable
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * @return the statement list
     */
    public ArrayList<Statement> getStatements() {
        return statements;
    }

    /**
     * @return the parDecl
     */
    public ArrayList<ParDecl> getParDecls() {
        return parDecls;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {
        return visitor.visit(this, arg);
    }

    public CompositeNodeType domain() {
        CompositeNodeType compositeNodeType = new CompositeNodeType(new ArrayList<>());
        this.getParDecls().forEach(parDecl -> {
            compositeNodeType.addNodeType(parDecl.getType().typeFactory());
        });
        return compositeNodeType;
    }

    public NodeType codomain() {
        return this.getType().typeFactory();
    }

}
