package com.github.imhammer.ihnukkitutils.command.parent;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */ 
public abstract class Parent
{
    /** Identifier name of Parent */
    protected final String name;

    /**
     * Parent constructor
     * @param name name of parent
     */
    public Parent(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return the parent name
     */
    public String getName()
    {
        return this.name;
    }
}