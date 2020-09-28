package visitor;

import errors.ErrorHandler;
import errors.ErrorItem;
import nodetype.FunctionNodeType;
import nodetype.NodeTipology;
import semantic.SymbolTable;
import semantic.SymbolTableEntry;
import syntax.*;
import syntax.expression.ArrayReadElement;
import syntax.expression.FunctionCall;
import syntax.expression.Id;
import syntax.expression.binary.arithmetic.DivOperation;
import syntax.expression.binary.arithmetic.MinusOperation;
import syntax.expression.binary.arithmetic.PlusOperation;
import syntax.expression.binary.arithmetic.TimesOperation;
import syntax.expression.binary.relation.*;
import syntax.expression.constant.*;
import syntax.expression.unary.NotExpression;
import syntax.expression.unary.SharpExpression;
import syntax.expression.unary.UminusExpression;
import syntax.statements.*;
import syntax.types.ArrayType;
import syntax.types.FunctionType;
import syntax.types.PrimitiveType;

import java.util.ArrayList;

public class NamingCheckVisitor implements Visitor<Boolean, SymbolTable> {
    private ErrorHandler errorHandler;
    private int mainMetdodsCounter =0;

    public NamingCheckVisitor(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    private boolean acceptList(ArrayList<? extends ASTNode> nodes, SymbolTable table){
        if(nodes.isEmpty()){
            return true;
        }
        for (ASTNode node : nodes){
            if(!node.accept(this,table)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean visit(Function function, SymbolTable arg) {
        if(function.getVariable().getName().equalsIgnoreCase("main")) {
            this.mainMetdodsCounter++;
        }

        boolean isOk = function.getVariable().accept(this, arg);
        if(!isOk) {
            errorHandler.adderror(new ErrorItem("Variable already defined!", function));
        } else {
            String name = function.getVariable().getValue();
            arg.addEntry(name, new SymbolTableEntry(NodeTipology.FUN, new FunctionNodeType(function.codomain(), function.domain()), function.getVariable().getName() ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(Id id, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(DivOperation divOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(MinusOperation minusOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(PlusOperation plusOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(TimesOperation timesOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(AndRelOperation andRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(EqualsRelOperation equalsRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(GreatThanEqualsRelOperation greatThanEqualsRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(GreatThanRelOperation greatThanRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(LessThanEqualsRelOperation lessThanEqualsRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(LessThanRelOperation lessThanRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(NotEqualsRelOperation notEqualsRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(OrRelOperation orRelOperation, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ArrayConst arrayConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(BooleanConst booleanConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(FloatConst floatConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(IntegerConst integerConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(NilConst nilConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(StringConst stringConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ArrayReadElement arrayReadElement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(FunctionCall functionCall, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ArrayAssignStatement arrayAssignStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(AssignStatement assignStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(CallFunctionStatement callFunctionStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ForStatement forStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(IfElseStatement ifElseStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(IfStatement ifStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(LocalStatement localStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(NopStatement nopStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ReadStatement readStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ReturnStatement returnStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(WhileStatement whileStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(WriteStatement writeStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(Global global, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ParDecl parDecl, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(Program program, SymbolTable arg) {
        arg.enterInScope();
        boolean areFunctionsOk = this.acceptList(program.getFunctions(), arg);
        arg.exitFromScope();

        if(this.mainMetdodsCounter > 1) {
            errorHandler.adderror(new ErrorItem("Multiple declaration of main metdod", program));
            return false;
        }
        return areFunctionsOk;
    }

    @Override
    public Boolean visit(VarDecl varDecl, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(VarInitValue varInitValue, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ArrayType arrayType, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(FunctionType functionType, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(PrimitiveType primitiveType, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(NotExpression notExpression, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(SharpExpression sharpExpression, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(UminusExpression uminusExpression, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(Variable variable, SymbolTable arg) {
        return !arg.containsLexeme(variable.getValue());
    }
}
