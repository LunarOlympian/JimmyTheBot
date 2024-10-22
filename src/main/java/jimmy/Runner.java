package jimmy;

import general.internals.Documentation;
import general.internals.Help;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static internals.JimmyFiles.jimmyPath;

public class Runner extends ListenerAdapter {

    private static boolean hasExclusive;
    private static Map<String, Integer> serverRestrictions = buildRestrictions();

    /**
     * Builds a map of if a server is usage restricted (cannot use Jimmy) (0),
     * or has exclusive usage (the only servers able to use Jimmy) (1)
     * @return Map with the info for each server
     */
    private static HashMap<String, Integer> buildRestrictions() throws IOException {
        /*
        0 - Cannot send to
        1 - Exclusively send to
        */
        Map<String, Integer> returnMe = new HashMap<>();

        for(String server : Files.readString(
                new File(jimmyPath + "/Internals/ServerRestrictions.txt").toPath()).split("\n")
        ) {

        }
    }

    /*
    Manages the different classes and runs the correct classes.
    In theory this prevents multiple things being activated and keeps it all efficient
    */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {



        Message message = e.getMessage();
        String messageContent = e.getMessage().getContentRaw();

        // Assesses if the user is rate limited.

        // Jimmy command!!!
        if(messageContent.toLowerCase().startsWith("!j ")) {
            // This just lets other classes do the hard work. Message sending is handled elsewhere.
            if(messageContent.split(" ")[1].equalsIgnoreCase("help")) Help.help(message);

            if(messageContent.split(" ")[1].equalsIgnoreCase("documentation")) Documentation.Documentation(message);

            if(messageContent.split(" ")[1].equalsIgnoreCase("mc")) Help.help(message);

        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        // Tbh I kinda forgot how to do this, so not using it for now.
    }
}
