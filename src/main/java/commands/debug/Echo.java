package commands.debug;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static internals.Debug.debugMode;

public class Echo extends ListenerAdapter {

    // This is intended to be a basic command for testing. This is ONLY usable in debug mode.

    public void debugCommands_echo(Message message) {
        if(debugMode) message.getChannel().sendMessage(message.getContentRaw().substring(8)).queue();
    }
}
