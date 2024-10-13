package com.github.imhammer.ihnukkitutils.command;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.github.imhammer.ihnukkitutils.command.IHCommand.Builder;
import com.github.imhammer.ihnukkitutils.command.execute.RawCommandExecute;
import com.github.imhammer.ihnukkitutils.command.execute.SoftCommandExecute;
import com.github.imhammer.ihnukkitutils.command.parent.Field;
import com.github.imhammer.ihnukkitutils.command.parent.MultipleOptions;
import com.github.imhammer.ihnukkitutils.command.parent.Option;
import com.github.imhammer.ihnukkitutils.command.parent.Parent;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */ 
public class Line
{

    /**
     * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
     * @version 1.1.0
     * @since   1.1.0
     */ 
    public static enum StartLineType
    {
        /**
         * This type is used when you have only one option (as if it were a sub command),
         * when the command is executed the index 0 of the arguments becomes
         * the selectedOption of {@link RawCommandExecute#apply} 
         */
        OPTION,

        /**
         * This type is used when there is more than one option (as if it were a sub command),
         * when the command is executed the index 0 of the arguments also becomes
         * the selectedOption of {@link RawCommandExecute#apply}
         */
        OPTIONS,

        /**
         * This type is used when the argument is a field, text, numeric...
         * when the command is executed, all arguments are passed to args in {@link RawCommandExecute#apply}
         * and the selectedOption will be null
         */
        FIELD
    }

    private final Builder parent;
    private final String keyId;

    private StartLineType startLineType;
    private String[] startLineOptions = null;
    private String startLineOptionName = null;
    
    private RawCommandExecute rawExecute = null;
    private SoftCommandExecute softExecute = null;

    private final LinkedHashMap<String, Parent> options = new LinkedHashMap<>();

    /**
     * Constructor Line
     * @param parent the command builder
     * @param keyId identifier for line
     */
    public Line(Builder parent, String keyId)
    {
        this.parent = parent;
        this.keyId = keyId;
    }

    /**
     * First param of line
     * @return the start line type
     */
    public StartLineType getStartLineType()
    {
        return startLineType;
    }

    /**
     * @return The argument options if {@link #startLineType} is equal to {@link StartLineType#OPTIONS OPTIONS}
     */
    public String[] getStartLineOptions()
    {
        return this.startLineOptions;
    }

    /**
     * @return The line argument option name if {@link #startLineType} is equal to {@link StartLineType#OPTION OPTION}
     */
    public String getStartLineOptionName()
    {
        return startLineOptionName;
    }

    /**
     * Default executor style
     * @param execute executor
     */
    public void onRawExecute(RawCommandExecute execute)
    {
        this.rawExecute = execute;
    }

    /**
     * Comming soon...
     * @param execute Comming soon...
     */
    public void onSoftExecute(SoftCommandExecute execute)
    {
        this.softExecute = execute;
    }

    /**
     * Command line  execution handler
     * @param sender handler
     * @param args arguments
     * @return true or false
     */
    public boolean handleOptionExecute(CommandSender sender, String[] args)
    {
        if (this.rawExecute != null) {
            String selectedOption = null;
            if (args.length > 0) {
                if (this.startLineType == StartLineType.OPTION || this.startLineType == StartLineType.OPTIONS) {
                    selectedOption = args[0];
                    if (args.length > 1) {
                        args = Arrays.copyOfRange(args, 1, args.length - 1);
                    } else {
                        args = new String[0];
                    }
                }
            }
            return this.rawExecute.apply(sender, args, selectedOption);
        }

        // TODO: Em breve adicionar validação dos campos com seus respectivos
        // tipos para o softExecute

        return this.softExecute.apply(sender);
    }

    /**
     * Add option to line
     * @param name name of option
     * @return the line
     */
    public Line addOption(String name)
    {
        Option option = new Option(name);
        this.options.put(name + "_option", option);

        if (this.options.size() == 1) {
            this.startLineType = StartLineType.OPTION;
            this.startLineOptionName = name;
        }
        return this;
    }

    /**
     * Add options to line
     * @param name name of collection
     * @param options options array
     * @return the line
     */
    public Line addOptions(String name, String ...options)
    {
        MultipleOptions option = new MultipleOptions(name, options);
        this.options.put(name + "_mult", option);

        if (this.options.size() == 1) {
            this.startLineType = StartLineType.OPTIONS;
            this.startLineOptions = options;
        }
        return this;
    }

    /**
     * Add field to line
     * @param name name of field
     * @param type type of field
     * @return the line
     */
    public Line addField(String name, CommandParamType type)
    {
        Field option = new Field(name, type);
        this.options.put(name + "_field", option);

        if (this.options.size() == 1) {
            this.startLineType = StartLineType.FIELD;
        }
        return this;
    }

    /**
     * @return line identifier
     */
    public String getKeyId()
    {
        return keyId;
    }

    /**
     * @return line parents collection
     */
    public LinkedHashMap<String, Parent> getOptions()
    {
        return options;
    }

    /**
     * @return the command builder
     */
    public Builder build()
    {
        return this.parent;
    }
}
