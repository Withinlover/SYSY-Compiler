public class Symbol {
    private final String name;
    private final int kind; // variable, constant
    private final int index;
    private int constValue = 0;

    private Symbol(String name, int kind, int index) {
        this.name = name;
        this.kind = kind;
        this.index = index;
    }

    public static Symbol newVariable(String name, int index) {
        return new Symbol(name, 0, index);
    }

    public static Symbol newConstant(String name, int index) {
        Symbol res = new Symbol(name, 1, index);
        return res;
    }

    public void setConstValue(int value) { constValue = value; }

    public boolean isVariable() { return kind == 0; }
    public boolean isConstant() { return kind == 1; }

    public int getIndex() { return index; }
    public int getConstValue() {
        assert (isConstant());
        return constValue;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }
}