import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    private int registerIndex;
    private int blockIndex;
    private SymbolBlock block;

    private final HashMap<String, ArrayList<Integer>> functions; // 0 refers to int, 1 refers to int*
    private final HashMap<String, Integer> functionReturnTypes; // 0 refers to void, 1 refers to int

    public SymbolTable() {
        this.block = new SymbolBlock(null);
        this.registerIndex = 0;
        this.blockIndex = 0;
        this.functions = new HashMap<>();
        this.functionReturnTypes = new HashMap<>();
    }

    public int getNewRegister() { return ++registerIndex;}
    public int getNewBlock() { return ++blockIndex;}

    public void allocNewBlock() { block = new SymbolBlock(block); }
    public void exitCurrentBlock() { block = block.getFather();}

    public Symbol allocInteger(String name, int type) { // 0 refers variables, 1 refers constant
        return switch (type) {
            case 0 -> block.addVariable(name, getNewRegister());
            case 1 -> block.addConstant(name, getNewRegister());
            default -> null;
        };
    }
    public void allocNewFunction(String name, ArrayList<Integer> params, int type) {
        if (type == 0) {
            System.out.printf("declare void @%s(", name);
        } else {
            System.out.printf("declare i32 @%s(", name);
        }
        int cnt = 0;
        for (Integer t : params) {
            if (cnt != 0) System.out.print(", "); cnt += 1;
            if (t == 1) System.out.print("i32");
        }
        System.out.println(")");
        functions.put(name, params);
        functionReturnTypes.put(name, type);
    }

    public boolean checkName(String name) { return block.checkName(name);}
    public boolean checkFunction(String name) { return functions.get(name) == null;}

    public Symbol find(String name) { return block.findVariables(name); }
    public ArrayList<Integer> getFunctionParams(String name) { return functions.get(name); }
    public int getFunctionType(String name) { return functionReturnTypes.get(name);}
}
