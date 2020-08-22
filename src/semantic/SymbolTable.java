package semantic;

import lexical.StringTable;

import java.util.*;

public class SymbolTable extends LinkedHashMap<Integer, HashMap<Integer, SymbolTableEntry>> {
    private Stack<Integer> scopeLevel;
    private StringTable stringTable;
    private static final int BEGIN = -1;
    private int currentScopeLevel;

    public SymbolTable(StringTable stringTable){
        this.stringTable = stringTable;
        currentScopeLevel = BEGIN;
        scopeLevel = new Stack<>();
    }

    public void enterInScope(){
        scopeLevel.push(++currentScopeLevel);
        if (!containsKey(scopeLevel.peek())){
            put(scopeLevel.peek(), new HashMap<>());
        }
    }

    public void exitFromScope(){
        scopeLevel.pop();
    }

    public int getCurrentScopeLevel() {
        return currentScopeLevel;
    }

    public boolean containsLexeme(String lexeme){
        return get(scopeLevel.peek()).
                containsKey(
                    stringTable.getAddress(lexeme)
                );
    }

    public Optional<SymbolTableEntry> getEntry(String lexeme){
        int address = stringTable.getAddress(lexeme);
        for (int i = scopeLevel.size(); i >= 0 ; i --){
            int level = scopeLevel.elementAt(i);
            if(get(level).containsKey(address) ){
                return Optional.of(
                        get(level).get(address)
                );
            }
        }
        return Optional.empty();
    }

    public void addEntry(String lexeme, SymbolTableEntry entry){
        int address = stringTable.getAddress(lexeme);
        get(scopeLevel.peek()).put(address,entry);
    }

    public void reset () {
        this.currentScopeLevel = BEGIN;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        this.entrySet().forEach(entry -> {
            Integer level = entry.getKey();
            out.append("Level: ").append(level).append('\n');
            Map<Integer, SymbolTableEntry> record = entry.getValue();
            record.entrySet().forEach(en -> {
                out.append("-> ");
                out.append("Address: ").append(en.getKey());
                out.append("|");
                out.append("Records->[");
                out.append(en.getValue().toString());
                out.append("]");
                out.append("\n");
            });
        });
        return out.toString();
    }
}


