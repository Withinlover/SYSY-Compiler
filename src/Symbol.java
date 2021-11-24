import java.util.ArrayList;

public class Symbol {
    private final String name;
    private final int kind; // variable, constant
    private final int index;
    private boolean isGlobal;
    private boolean isArray;
    private ArrayList<Integer> dims;
    private int constValue = 0;
    private ArrayList<Integer> constArrayValue;

    private Symbol(String name, int kind, int index) {
        this.name = name;
        this.kind = kind;
        this.index = index;
        this.isGlobal = false;
    }

    public static Symbol newVariable(String name, int index) {
        return new Symbol(name, 0, index);
    }

    public static Symbol newVariableArray(String name, int index) {
        Symbol symbol = new Symbol(name, 0, index);
        symbol.isArray = true;
        return symbol;
    }

    public static Symbol newConstant(String name, int index) {
        return new Symbol(name, 1, index);
    }

    public static Symbol newConstantArray(String name, int index) {
        Symbol symbol = new Symbol(name, 1, index);
        symbol.isArray = true;
        return symbol;
    }

    public void setConstValue(int value) { this.constValue = value; }
    public void setGlobal(boolean isGlobal) {this.isGlobal = isGlobal; }
    public void setDims(ArrayList<Integer> dims) {
        this.dims = dims;
        int res = 1;
        for (int i : dims) res = res * i;
        this.constArrayValue = new ArrayList<>();
        for (int i = 0;i < res; ++i) this.constArrayValue.add(0);
    }
    public void setConstArrayValue(ArrayList<Integer> param, int value) {
        int base = 1, pos = 0, sz = this.dims.size();
        if (param.size() != dims.size()) {
            System.out.println("调用的维度不匹配");
            System.exit(-1);
        }
        for (int i = 0;i < sz; ++i) {
            pos = pos + param.get(sz - i - 1) * base;
            base = base * dims.get(sz - i - 1);
        }
        if (pos >= this.constArrayValue.size()) {
            System.out.println("Undefined behavior");
            System.exit(-1);
        }
//        System.out.printf("Set pos = %d, value = %d\n", pos, value);
        this.constArrayValue.set(pos, value);
    }

    public boolean isVariable() { return kind == 0; }
    public boolean isConstant() { return kind == 1; }
    public boolean isGlobal() {return isGlobal;}
    public boolean isArray() {return isArray;}

    public int getIndex() { return index; }
    public int getConstValue() {
        assert (isConstant());
        return constValue;
    }
    public ArrayList<Integer> getDims() {return dims;}
    public int getArrayValue(ArrayList<Integer> param) {int base = 1, pos = 0, sz = this.dims.size();
        if (param.size() != dims.size()) {
            System.out.println("调用的维度不匹配");
            System.exit(-1);
        }
        for (int i = 0;i < sz; ++i) {
            pos = pos + param.get(sz - i - 1) * base;
            base = base * dims.get(sz - i - 1);
        }
        if (pos >= this.constArrayValue.size()) {
            System.out.println("Undefined behavior");
            System.exit(-1);
        }
//        System.out.printf("Query pos = %d\n", pos);
        return this.constArrayValue.get(pos);
    }

    public String getName() {return name;}

    public boolean equals(String name) {
        return this.name.equals(name);
    }
}
