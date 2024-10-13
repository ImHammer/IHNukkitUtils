package com.github.imhammer.ihnukkitutils.command.execute;

import cn.nukkit.command.CommandSender;

import com.github.imhammer.ihnukkitutils.command.IHCommand;
import com.github.imhammer.ihnukkitutils.command.Line;
import com.github.imhammer.ihnukkitutils.command.parent.Option;
import com.github.imhammer.ihnukkitutils.command.parent.MultipleOptions;

/**
 * @author  ImHammer DEV &lt;undefined@undefined.com&gt;
 * @version 1.1.0
 * @since   1.1.0
 */ 
@FunctionalInterface
public interface RawCommandExecute
{
    /**
     * Execute command
     * @param sender Command handler
     * @param args command arguments
     * @param selectedOption Selected option, will only work if the first argument of the {@link Line} is an argument of type {@link Option} or of type {@link MultipleOptions}
     * @return True when the command was executed successfully, or false when there were exceptions.
     * in case of false, the API will send the {@link IHCommand.Builder#usage}
     */
    boolean apply(CommandSender sender, String[] args, String selectedOption);
}
