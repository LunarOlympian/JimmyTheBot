package commands.privileged;

import net.dv8tion.jda.api.entities.Message;

import static internals.PrivilegedUsers.getPrivilegeLevel;

public class IDSpoof {

    public static String privilegedCommands_idSpoof(Message message) {
        // This is a user ID spoof. Requires privilege 1 or 3 as it's extremely risky otherwise.
        if((getPrivilegeLevel(message.getAuthor().getId()) == 1 || getPrivilegeLevel(message.getAuthor().getId()) == 3) &&
                message.getContentRaw().toLowerCase().startsWith("!j-debug spoof user id "))
            return message.getContentRaw().split(" ")[4];
        return message.getAuthor().getId();
    }
}
