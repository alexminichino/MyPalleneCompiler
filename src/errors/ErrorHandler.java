package errors;

import generated.Parser;

import java.util.Stack;

public class ErrorHandler {
    private Stack<ErrorItem> errorItems;

    public ErrorHandler() {
        this.errorItems = new Stack<>();
    }

    public void adderror(ErrorItem errorItem){
        errorItems.push(errorItem);
    }

    public boolean isThereErrors(){
        return !errorItems.isEmpty();
    }

    public String getErrorStackTrace(){
        String out = "";
        for(ErrorItem error : errorItems ){
            out+="Error "+(errorItems.indexOf(error)+1)+" -> "+ error.getMessage();
            out+=" At LINE: "+ error.getErrorNode().getLeftPosition().getLine()+ " and COLUMN: " + error.getErrorNode().getRightPosition().getColumn();
            out+=" Related at "+ error.getErrorNode().getClass().getSimpleName().toString();
            out+=" Node \n";
        }
        return out;
    }
     public boolean hasErrors(){
        return errorItems.size() > 0;
     }
}
