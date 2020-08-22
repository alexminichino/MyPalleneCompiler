package errors;

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
            out+="Error -> "+ error.getMessage();
            out+="Related at "+ error.getErrorNode().getClass().getSimpleName().toString();
            out+=" Node \n";
        }
        return out;
    }
}
