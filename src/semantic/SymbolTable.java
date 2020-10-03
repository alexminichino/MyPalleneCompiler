package semantic;

import lexical.StringTable;

import java.util.*;

public class SymbolTable {
    private Stack<Integer> scopeLevel;
    private StringTable stringTable;
    private static final int BEGIN = -1;
    private int currentScopeLevel;
    private LinkedHashMap<Integer, HashMap<Integer, SymbolTableEntry>> table;
    private HashMap<Integer, String> levelsNames;

    public SymbolTable(StringTable stringTable){
        this.stringTable = stringTable;
        currentScopeLevel = BEGIN;
        scopeLevel = new Stack<>();
        table = new LinkedHashMap<>();
        levelsNames = new HashMap<>();
    }

    public void enterInScope(String scopeDescription){
        scopeLevel.push(++currentScopeLevel);
        if (!table.containsKey(scopeLevel.peek())){
            table.put(scopeLevel.peek(), new HashMap<>());
        }
        if (!levelsNames.containsKey(scopeLevel.peek()))
            levelsNames.put(scopeLevel.peek(), scopeDescription);
    }

    public void exitFromScope(){
        scopeLevel.pop();
    }

    public int getCurrentScopeLevel() {
        return currentScopeLevel;
    }

    public boolean containsLexeme(String lexeme){
        return table.get(scopeLevel.peek()).
                containsKey(
                    stringTable.getAddress(lexeme)
                );
    }



    public Optional<SymbolTableEntry> getTableEntryIfExists(String lexeme) {
        int address = this.stringTable.getAddress(lexeme);
        int size = (this.scopeLevel.size() - 1);
        for (int i = size; i >= 0; i--) {
            int level = this.scopeLevel.elementAt(i);
            if (this.table.get(level).containsKey(address)) {
                return Optional.of(this.table.get(level).get(address));
            }
        }
        return Optional.empty();
    }


    public void addEntry(String lexeme, SymbolTableEntry entry){
        int address = stringTable.getAddress(lexeme);
        table.get(scopeLevel.peek()).put(address,entry);
    }

    public void reset () {
        this.currentScopeLevel = BEGIN;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        this.table.entrySet().forEach(entry -> {
            Integer level = entry.getKey();
            //String tabs = String.join("", Collections.nCopies(level, "\t"));
            out.append("Level: ").append(level).append("\t").append(levelsNames.get(level)).append('\n');
            Map<Integer, SymbolTableEntry> record = entry.getValue();
            record.entrySet().forEach(en -> {
                out.append("\t\t-> ");
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


