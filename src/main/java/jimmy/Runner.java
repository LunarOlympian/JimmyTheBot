package jimmy;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static commands.privileged.IDSpoof.privilegedCommands_idSpoof;
import static commands.debug.Echo.debugCommands_echo;
import static commands.standard.internals.Documentation.internalCommands_documentation;
import static commands.standard.internals.Help.internalCommands_help;
import static internals.RateLimits.checkRateLimited;
import static internals.tools.FileRead.readFile;

public class Runner extends ListenerAdapter {

    private static boolean hasExclusive;
    private static Map<String, Integer> serverRestrictions = buildRestrictions();

    /**
     * Builds a map of if a server is usage restricted (cannot use Jimmy) (0),
     * or has exclusive usage (the only servers able to use Jimmy) (1)
     * @return Map with the info for each server
     */
    private static Map<String, Integer> buildRestrictions() {
        /*
        0 - Cannot send to
        1 - Exclusively send to
        */
        Map<String, Integer> returnMe = new HashMap<>();

        boolean hasExclusiveSend = false;
        for (String server : readFile("Internals/ServerRestrictions.txt").split("\n"))
        {
            if(server.startsWith("!")) {
                returnMe.put(server.substring(1), 1); // ! denotes exclusive
                hasExclusiveSend = true;
            }
            else returnMe.put(server, 0);
        }
        hasExclusive = hasExclusiveSend;
        return returnMe;
    }

    /*
    Manages the different classes and runs the correct classes.
    In theory this prevents multiple things being activated and keeps it all efficient
    */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        Message message = e.getMessage();
        String messageContent = e.getMessage().getContentRaw();

        // Assess if server is restricted, or if there are exclusive(s) it's not one
        if(hasExclusive && serverRestrictions.getOrDefault(e.getGuild().getId(), 0) == 0) return;
        else if(serverRestrictions.getOrDefault(e.getGuild().getId(), -1) == 0) return;

        // Handles ID spoof
        String userID = privilegedCommands_idSpoof(e.getMessage());


        // Checks if the user is rate limited.
        if(checkRateLimited(userID)) return;


        // Jimmy command!!!
        if(messageContent.toLowerCase().startsWith("!j ")) {
            // This just lets other classes do the hard work. Message sending is handled elsewhere.
            String[] messageSplit = messageContent.split(" ");

            if(messageSplit[1].equalsIgnoreCase("help")) internalCommands_help(message);

            if(messageSplit[1].equalsIgnoreCase("documentation")) internalCommands_documentation(message);

            // if(messageSplit[1].equalsIgnoreCase("mc")) internalCommands_help(message);

            if(messageSplit[1].equalsIgnoreCase("echo")) debugCommands_echo(message);

        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        // Tbh I kinda forgot how to do this, so not using it for now.
    }
}
