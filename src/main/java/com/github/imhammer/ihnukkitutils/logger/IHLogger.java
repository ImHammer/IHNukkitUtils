package com.github.imhammer.ihnukkitutils.logger;

import cn.nukkit.Server;
import cn.nukkit.utils.LogLevel;
import cn.nukkit.utils.Logger;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */
public class IHLogger implements Logger
{
    /**
     * @param prefix prefix console name
     * @param identifier log name
     * @return instance of logger
     */
    public static IHLogger getLogger(String prefix, String identifier)
    {
        return new IHLogger(prefix, identifier);
    }

    /** Pre-formatted message for console */
    private final String formatedMessage;

    /**
     * @param prefix console name 
     * @param identifier log name
     */
    protected IHLogger(String prefix, String identifier)
    {
        this.formatedMessage = "[%s] %s: ".formatted(prefix, identifier);
    }
    
    /**
     * Send's a log of emergency
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void emergency(String message)
    {
        this.log(LogLevel.EMERGENCY, message);
    }

    /**
     * Send's a log of alert
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void alert(String message)
    {
        this.log(LogLevel.ALERT, message);
    }

    /**
     * Envia um log critical
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void critical(String message)
    {
        this.log(LogLevel.CRITICAL, message);
    }

    /**
     * Send's a log of error
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void error(String message)
    {
        this.log(LogLevel.ERROR, message);
    }

    /**
     * Send's a log of warning
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void warning(String message)
    {
        this.log(LogLevel.WARNING, message);
    }

    /**
     * Send's a log of notice
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void notice(String message)
    {
        this.log(LogLevel.NOTICE, message);
    }

    /**
     * Send's a log of information
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void info(String message)
    {
        this.log(LogLevel.INFO, message);
    }

    /**
     * Send's a log of development
     * @param message string message to send
     * @see #log(LogLevel, String)
     */
    public void debug(String message)
    {
        this.log(LogLevel.DEBUG, message);
    }

    /**
     * Send's a console log
     * @param level log level enum
     * @param message string message
     */
    public void log(LogLevel level, String message)
    {
        Server.getInstance().getLogger().log(level, this.formatedMessage + message);
    }

    /**
     * Send's a log of emergency exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void emergency(String message, Throwable t)
    {
        this.log(LogLevel.EMERGENCY, message, t);
    }

    /**
     * Send's a log of alert exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void alert(String message, Throwable t)
    {
        this.log(LogLevel.ALERT, message, t);
    }

    /**
     * Send's a log of critical exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void critical(String message, Throwable t)
    {
        this.log(LogLevel.CRITICAL, message, t);
    }

    /**
     * Send's a log of error exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void error(String message, Throwable t)
    {
        this.log(LogLevel.ERROR, message, t);
    }

    /**
     * Send's a log of alert exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void warning(String message, Throwable t)
    {
        this.log(LogLevel.WARNING, message, t);
    }

    /**
     * Send's a log of notice exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void notice(String message, Throwable t)
    {
        this.log(LogLevel.NOTICE, message, t);
    }

    /**
     * Send's a log of info exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void info(String message, Throwable t)
    {
        this.log(LogLevel.INFO, message, t);
    }

    /**
     * Send's a log of development exception
     * @param message string message to send
     * @param t exception to send
     * @see #log(LogLevel, String, Throwable)
     */
    public void debug(String message, Throwable t)
    {
        this.log(LogLevel.DEBUG, message, t);
    }

    /**
     * Send's a exception log
     * @param level log level enum
     * @param message string message to send
     * @param t exception to send
     */
    public void log(LogLevel level, String message, Throwable t)
    {
        Server.getInstance().getLogger().log(level, this.formatedMessage + message, t);
    }
}
