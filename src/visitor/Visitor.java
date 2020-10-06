package visitor;

import syntax.expression.binary.arithmetic.*;
import syntax.expression.binary.relation.*;
import syntax.expression.*;
import syntax.expression.constant.*;
import syntax.expression.unary.*;
import syntax.statements.*;
import syntax.*;
import syntax.types.ArrayFloatType;
import syntax.types.ArrayType;
import syntax.types.FunctionType;
import syntax.types.PrimitiveType;


public interface Visitor<T,P> {
    T visit(Function function, P arg);
    T visit(Id id, P arg);
    T visit(DivOperation divOperation, P arg);

    T visit(MinusOperation minusOperation, P arg);

    T visit(PlusOperation plusOperation, P arg);

    T visit(TimesOperation timesOperation, P arg);

    T visit(AndRelOperation andRelOperation, P arg);

    T visit(EqualsRelOperation equalsRelOperation, P arg);

    T visit(GreatThanEqualsRelOperation greatThanEqualsRelOperation, P arg);

    T visit(GreatThanRelOperation greatThanRelOperation, P arg);

    T visit(LessThanEqualsRelOperation lessThanEqualsRelOperation, P arg);

    T visit(LessThanRelOperation lessThanRelOperation, P arg);

    T visit(NotEqualsRelOperation notEqualsRelOperation, P arg);

    T visit(OrRelOperation orRelOperation, P arg);

    T visit(ArrayConst arrayConst, P arg);

    T visit(BooleanConst booleanConst, P arg);

    T visit(FloatConst floatConst, P arg);

    T visit(IntegerConst integerConst, P arg);

    T visit(NilConst nilConst, P arg);

    T visit(StringConst stringConst, P arg);

    T visit(ArrayReadElement arrayReadElement, P arg);

    T visit(FunctionCall functionCall, P arg);

    T visit(ArrayAssignStatement arrayAssignStatement, P arg);

    T visit(AssignStatement assignStatement, P arg);

    T visit(CallFunctionStatement callFunctionStatement, P arg);

    T visit(ForStatement forStatement, P arg);

    T visit(IfElseStatement ifElseStatement, P arg);

    T visit(IfStatement ifStatement, P arg);

    T visit(LocalStatement localStatement, P arg);

    T visit(NopStatement nopStatement, P arg);

    T visit(ReadStatement readStatement, P arg);

    T visit(ReturnStatement returnStatement, P arg);

    T visit(WhileStatement whileStatement, P arg);

    T visit(WriteStatement writeStatement, P arg);

    T visit(Global global, P arg);

    T visit(ParDecl parDecl, P arg);

    T visit(Program program, P arg);

    T visit(VarDecl varDecl, P arg);

    T visit(VarInitValue varInitValue, P arg);

    T visit(ArrayType arrayType, P arg);

    T visit(FunctionType functionType, P arg);

    T visit(PrimitiveType primitiveType, P arg);

    T visit(NotExpression notExpression, P arg);

    T visit(SharpExpression sharpExpression, P arg);

    T visit(UminusExpression uminusExpression, P arg);

    T visit( Variable variable, P arg);

    T visit(ArrayFloatType arrayType, P arg);

    T visit(AssignFloatArrayStatement assignFloatArrayStatement, P arg);
}
