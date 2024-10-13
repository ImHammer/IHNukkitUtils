package com.github.imhammer.ihnukkitutils.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.github.imhammer.ihnukkitutils.command.Line.StartLineType;
import com.github.imhammer.ihnukkitutils.command.execute.RawCommandExecute;
import com.github.imhammer.ihnukkitutils.command.parent.Field;
import com.github.imhammer.ihnukkitutils.command.parent.MultipleOptions;
import com.github.imhammer.ihnukkitutils.command.parent.Option;
import com.github.imhammer.ihnukkitutils.command.parent.Parent;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParameter;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */ 
public class IHCommand extends Command
{
    /**
     * Creates a new command builder
     * @param commandName name of command
     * @return builder for command constructor
     */
    public static Builder newCommand(String commandName)
    {
        return new Builder(commandName, "", null, new String[0]);
    } 

    /**
     * Lines array
     */
    private final Line[] lines;

    /**
     * Global command executor
     */
    private RawCommandExecute globalExecute = null;

    /**
     * Constructor of command
     * @param commandName the command name
     * @param lines command lines
     */
    protected IHCommand(String commandName, Line[] lines)
    {
        super(commandName);
        this.lines = lines;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args)
    {
        if (!testPermissionSilent(sender)) {
            sender.sendMessage("Você não tem permissão para executar este comando!");
            return true;
        }

        if (args.length <= 0) {
            sender.sendMessage("Invalid command arguments!");
            return false;
        }

        if (this.globalExecute != null) {
            if (this.globalExecute.apply(sender, args, null)) {
                return true;
            }
        }

        String firstArgument = args[0];

        Line commandLine = null;
        for (Line line : this.lines) {
            if (line.getStartLineType() == StartLineType.OPTION) {
                if (line.getStartLineOptionName().equals(firstArgument)) {
                    commandLine = line;
                }
                continue;
            }
            if (line.getStartLineType() == StartLineType.OPTIONS) {
                if (Arrays.asList(line.getStartLineOptions()).contains(firstArgument)) {
                    commandLine = line;
                }
            }
            if (line.getStartLineType() == StartLineType.FIELD) {
                commandLine = line;
            }
        }

        if (commandLine != null) {
            return commandLine.handleOptionExecute(sender, args);
        }

        return true;
    }

    /**
     * Set an executor {@link RawCommandExecute} that works for all types of lines
     * @param globalExecute Command executor
     */
    public void setGlobalExecute(RawCommandExecute globalExecute)
    {
        this.globalExecute = globalExecute;
    }

    /**
     * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
     * @version 1.1.0
     * @since   1.1.0
     */ 
    public static class Builder
    {
        /** Name of command */
        protected final String commandName;
        /** Description of command */
        protected String description;
        /** Permission of command */
        protected String permission;
        /** Usage of command */
        protected String usage = null;
        /** Short names list of command */
        protected String[] aliases;
        /** Global command executor */
        protected RawCommandExecute execute = null;
        /** Command lines */
        protected final LinkedList<Line> lines = new LinkedList<>();

        /**
         * Command builder for construct a expert commands
         * @param commandName name of command
         * @param description description of command
         * @param permission permission of command
         * @param aliases command short names
         */
        protected Builder(String commandName, String description, String permission, String[] aliases)
        {
            this.commandName = commandName;
            this.description = description;
            this.permission = permission;
            this.aliases = aliases;
        }

        /**
         * Start a build command line
         * @return line for build
         */
        public Line line()
        {
            Line line = new Line(this, "line_" + lines.size());
            lines.add(line);

            return line;
        }

        /**
         * Set's a global execute for construct command
         * @param execute command executor
         * @return the command builder
         */
        public Builder globalExecute(RawCommandExecute execute)
        {
            this.execute = execute;
            return this;
        }

        /**
         * Set's description for construct command
         * @param description description for command
         * @return the command builder
         */
        public Builder description(String description)
        {
            this.description = description;
            return this;
        }

        /**
         * Set's permission for construct command
         * @param permission permission for command
         * @return the command builder
         */
        public Builder permission(String permission)
        {
            this.permission = permission;
            return this;
        }

        /**
         * Set's usage for construct command
         * @param usage usage text
         * @return the command builder
         */
        public Builder usage(String usage)
        {
            this.usage = usage;
            return this;
        }

        /**
         * Set's short names for construct command
         * @param aliases short names array
         * @return the command builder
         */
        public Builder aliases(String[] aliases)
        {
            this.aliases = aliases;
            return this;
        }

        /**
         * Final function for build a complete command
         * @return Returns the constructed command
         */
        public Command build()
        {
            IHCommand command = new IHCommand(this.commandName, this.lines.toArray(new Line[this.lines.size()]));

            command.getCommandParameters().clear();

            for (Line line : this.lines) {
                String argName = line.getKeyId();
                LinkedList<CommandParameter> parametersList = new LinkedList<>();

                for (Entry<String, Parent> entryOption : line.getOptions().entrySet()) {
                    String key = entryOption.getKey();
                    Parent parent = entryOption.getValue();

                    if (parent instanceof Option) {
                        parametersList.add(CommandParameter.newEnum(parent.getName() + "_option", new CommandEnum(
                                "%s_%s_%s".formatted(this.commandName, "option", key), parent.getName())));
                        continue;
                    }
                    if (parent instanceof MultipleOptions) {
                        parametersList.add(CommandParameter.newEnum("options", new CommandEnum(
                                "%s_%s".formatted(key, "action"), ((MultipleOptions) parent).getValues())));
                        continue;
                    }
                    if (parent instanceof Field) {
                        parametersList.add(CommandParameter.newType(parent.getName(), ((Field) parent).getType()));
                        continue;
                    }
                }
                command.getCommandParameters().put(argName,
                        parametersList.toArray(new CommandParameter[parametersList.size()]));
            }

            if (this.description != null) {
                command.setDescription(this.description);
            }

            if (this.permission != null) {
                command.setPermission(this.permission);
            }

            if (this.aliases != null) {
                command.setAliases(this.aliases);
            }

            if (this.execute != null) {
                command.setGlobalExecute(this.execute);
            }

            if (this.usage != null) {
                command.setUsage(this.usage);
            }

            return command;
        }
    }
}
