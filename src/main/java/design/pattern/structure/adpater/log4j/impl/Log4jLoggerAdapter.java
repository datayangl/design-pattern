package design.pattern.structure.adpater.log4j.impl;

import design.pattern.structure.adpater.log4j.Level;
import design.pattern.structure.adpater.log4j.FormattingTuple;
import design.pattern.structure.adpater.log4j.Logger;

import java.io.Serializable;

// log4j日志框架的适配器
public final class Log4jLoggerAdapter
        implements design.pattern.structure.adpater.slf4j.Logger, Serializable {
    final transient design.pattern.structure.adpater.log4j.Logger logger; //lo4j

    public Log4jLoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String msg) {
        logger.log(Level.DEBUG, msg, null);
    }

    public void debug(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = FormattingTuple.parse(format, arg);
            logger.log(Level.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = FormattingTuple.parse(format, arg1, arg2);
            logger.log(Level.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    public void debug(String format, Object[] argArray) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = FormattingTuple.parse(format, argArray);
            logger.log(Level.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    public void debug(String msg, Throwable t) {
        logger.log(Level.DEBUG, msg, t);
    }

    //...省略一堆接口的实现...
    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void trace(String msg) {

    }

    @Override
    public void trace(String format, Object arg) {

    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {

    }

    @Override
    public void trace(String format, Object[] argArray) {

    }

    @Override
    public void trace(String msg, Throwable t) {

    }

}
