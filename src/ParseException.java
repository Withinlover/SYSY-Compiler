public class ParseException extends Exception {
    private final String msg;

    public ParseException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Error occurs while parsing: " + this.msg;
    }
}
