package starter;

import java.io.IOException;

public class MyPallene2Web extends MyPallene2C{

    public MyPallene2Web(String[] args) throws IOException {
        super(args);
    }

    public static void main(String[] args) throws IOException {
        new MyPallene2Web(args);
    }

    @Override
    protected void createCommands() {
        String newSource = sourceFile.replace("\\", "/").substring(0, sourceFile.lastIndexOf("."));
        switch (CURRENT_SYSTEM){
            case OTHER:
                commands = new String[]{"wsl", "./exec.sh", newSource };
                break;

            case WINDOWS:
                //commands = new String[]{"wsl", "./exec.sh", newSource };
                //Start with separate window
                commands = new String[]{"cmd", "/c" ,"start", "cmd", "/k", "wsl ./exec_web.sh '"+newSource+"'"};
                break;
            default:
                break;
        }
    }
}
