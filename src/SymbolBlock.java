import java.util.HashMap;

public class SymbolBlock {
    private SymbolBlock father;
    private HashMap<String, Symbol> symbolSet;

    public SymbolBlock(SymbolBlock father) {
        this.father = father;
        this.symbolSet = new HashMap<>();
    }

    public SymbolBlock getFather() { return father; }
    public Symbol addVariable(String name, int index) {
        if (!checkName(name)) return null;
        symbolSet.put(name, Symbol.newVariable(name, index));
        return symbolSet.get(name);
    }
    public Symbol addConstant(String name, int index) {
        if (!checkName(name)) return null;
        symbolSet.put(name, Symbol.newConstant(name, index));
        return symbolSet.get(name);
    }
    public Symbol findVariables(String name) {
        Symbol res = symbolSet.get(name);
        if (res == null) {
            if (father == null)
                return null;
            else
                return father.findVariables(name);
        } else {
            return res;
        }
    }
    public boolean checkName(String name) {
        Symbol res = symbolSet.get(name);
        return (res == null);
    }
}
