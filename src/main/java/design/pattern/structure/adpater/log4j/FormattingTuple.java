package design.pattern.structure.adpater.log4j;

public class FormattingTuple {
    private String message;
    private Throwable throwable;

    public FormattingTuple(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public static FormattingTuple parse(String format, Object... argArray) {
        // parse
        return new FormattingTuple(format, null);
    }
}
