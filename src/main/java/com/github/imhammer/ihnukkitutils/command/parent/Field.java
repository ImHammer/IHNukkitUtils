package com.github.imhammer.ihnukkitutils.command.parent;

import cn.nukkit.command.data.CommandParamType;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */ 
public class Field extends Parent
{   
    private final CommandParamType type;

    /**
     * Construct a field for command line
     * @param name name of field
     * @param type type of filed
     */
    public Field(String name, CommandParamType type)
    {
        super(name);
        this.type = type;
    }

    /**
     * 
     * @return the field type 
     */
    public CommandParamType getType()
    {
        return type;
    }
}
