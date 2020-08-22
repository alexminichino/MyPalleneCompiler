package lexical;

import java.util.ArrayList;

/**
 * String Table
 */
public class StringTable {
    private ArrayList<String> symbols;


    /**
     * Initialize new table
     */
    public StringTable() {
        this.symbols = new ArrayList<>();
    }

    /**
     * @param lexeme to install
     * @return success
     */
    public boolean installLexeme(String lexeme){
        if(!hasLexeme(lexeme)){
            return symbols.add(lexeme);
        }
        return false;
    }

    /**
     * @param lexeme of which to verify index
     * @return the index (-1 in case of absence)
     */
    public int getAddress(String lexeme){
        return symbols.lastIndexOf(lexeme);
    }

    /**
     * @param lexeme of which to verify presence
     * @return the presence
     */
    public boolean hasLexeme(String lexeme){
        return getAddress(lexeme) != -1;
    }

    /**
     * @param address the index
     * @return the lexeme
     */
    public String getLexeme(int address){
        return symbols.get(address);
    }



}
