public class SymbolTable {
    private int registerIndex;
    private int blockIndex;
    private SymbolBlock block;

    public SymbolTable() {
        this.block = new SymbolBlock(null);
        this.registerIndex = 0;
        this.blockIndex = 0;
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

    public boolean checkName(String name) { return block.checkName(name);}

    public Symbol find(String name) { return block.findVariables(name); }
}
