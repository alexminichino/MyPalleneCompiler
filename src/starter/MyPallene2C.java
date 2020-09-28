package starter;

import java.io.*;
import java.util.*;

enum OS{
    WINDOWS,
    OTHER
}

public class MyPallene2C {
    protected static File projectDir = new File(System.getProperty("user.dir"));
    protected static OS CURRENT_SYSTEM = (System.getProperty("os.name").toLowerCase().contains("windows")) ? OS.WINDOWS : OS.OTHER;
    protected static String sourceFile;
    protected static String[] commands;
    protected static MyPallene compiler;
    protected static File SourcesDir= new File ("test_files");

    public static void main(String[] args) throws IOException {
       new MyPallene2C(args);
    }

    public MyPallene2C(String[] args) throws IOException {
        if (args.length >0 ){
            sourceFile = args[0];
        }
        else {
            sourceFile = selectFileToCompile();
        }
        compiler = new MyPallene(sourceFile);
        try{
            compiler.runCompiler();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        createCommands();
        executeCommands();
    }

    protected void createCommands() {
        String newSource = sourceFile.replace("\\", "/").substring(0, sourceFile.lastIndexOf("."));
        switch (CURRENT_SYSTEM){

            case OTHER:
                commands = new String[]{"wsl", "./exec.sh", newSource };
                break;

            case WINDOWS:
                //commands = new String[]{"wsl", "./exec.sh", newSource };
                //Start with separate window
                commands = new String[]{"cmd", "/c" ,"start", "cmd", "/k", "wsl ./exec.sh '"+newSource+"' -exec"};
                break;
            default:
                break;
        }
    }


    private String selectFileToCompile() {
        String[] files = SourcesDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".mp"))
                    return true;
                return false;
            }
        });
        int count=0;
        String stringFiles = "***Please select one of listed files***\n\n";
        for (String file : files){
            stringFiles+=(count+1)+" - "+file.toString()+"\n";
            count++;
        }
        System.out.println(stringFiles);
        int choice = takeChoice();
        while (choice < 1 || choice > files.length +1 ){
            System.out.println("Invalid input\n");
            System.out.println(stringFiles);
            choice = takeChoice();
        }

        return new File(SourcesDir, files[choice-1]).toString();
    }

    private int takeChoice(){
        int choice;
        try {
            choice = new Scanner(System.in).nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input\nPlease insert a number!");
            choice = new Scanner(System.in).nextInt();
        }
        return choice;
    }

    private void executeCommands() throws IOException {
        Process process = Runtime.getRuntime().exec(commands);
        printResults(process.getInputStream());
        printResults(process.getErrorStream());
    }

    private void printResults(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
