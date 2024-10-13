package com.github.imhammer.ihnukkitutils.command.parent;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */ 
public class MultipleOptions extends Parent
{
    private final String[] options;

    /**
     * Constructor of Multiple Options
     * @param name multiple options name position in line
     * @param values options
     */
    public MultipleOptions(String name, String ...values)
    {
        super(name);
        this.options = values;
    }

    /**
     * 
     * @return the options
     */
    public String[] getValues()
    {
        return this.options;
    }
}
