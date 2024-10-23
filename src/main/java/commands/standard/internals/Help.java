package commands.standard.internals;

import commands.standard.StandardCommand;
import net.dv8tion.jda.api.entities.Message;

public class Help extends StandardCommand {


    public static Help buildCommand() {
        return new Help();
    }
    // Separate from documentation. Just the less technical version of it.

    public void internalCommands_help(Message message) {

    }
}
