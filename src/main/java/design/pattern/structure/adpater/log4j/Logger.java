package design.pattern.structure.adpater.log4j;

public interface Logger {
    public boolean isTraceEnabled();
    public void trace(String msg);
    public void trace(String format, Object arg);
    public void trace(String format, Object arg1, Object arg2);
    public void trace(String format, Object[] argArray);
    public void trace(String msg, Throwable t);

    public boolean isDebugEnabled();
    public void debug(String msg);
    public void debug(String format, Object arg);
    public void debug(String format, Object arg1, Object arg2);
    public void debug(String format, Object[] argArray);
    public void debug(String msg, Throwable t);

    public void log(Level level, String msg, Object... args);

}
