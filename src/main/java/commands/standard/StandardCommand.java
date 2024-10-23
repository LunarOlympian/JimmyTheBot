package commands.standard;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

public abstract class StandardCommand {
    /**
     * This is the class that manages all standard commands and their permissions. It keeps track of the commands
     * and makes sure they are all properly managed and have sensible permissions.
     */

    private static Map<String, Pair<StandardCommand, Permission[]>> standardCommands = new HashMap<>();

    private static boolean built = false;


    // Only allowed to be run once! All commands not included are labeled at invalid.
    public static void buildStandardCommands(StandardCommand[] commands) {
        if(!built) {
            for (StandardCommand command : commands) {
                if (!standardCommands.containsKey(command.getID()))
                    standardCommands.put(command.getID(), new Pair<>(command, command.getPermissions()));
                else throw new RuntimeException("Multiple commands with name " + command.getID());
            }

            built = true;
        }
    }

    /**
     * A request for a command.
     * @param specifier A string that says what the user is trying to run. For example "help" for help
     * @param request The user's specific request. Sends what it returns (if it returns "" it sends nothing). This is for messages.
     */
    public static String request(String specifier, Message request) {
        if(standardCommands.containsKey(specifier.toLowerCase())) return "Error: Command doesn't exist. Please run " +
                "\"!j help\" to get a list of the commands.";
        if(assessUserPermissions(request.getMember(), standardCommands.get(specifier).getValue1()))
            return standardCommands.get(specifier).getValue0().run(request);

        return "Error: Invalid permissions to run this command.";
    }

    /**
     * A request for a command. TODO Not implemented yet!
     * @param specifier A string that says what the user is trying to run. For example "help" for help
     * @param request The user's specific request. This is for slash commands
     */
    public static void request(String specifier, SlashCommandData request) {

    }

    /**
     * Checks the user's permissions and sees if they're able to run the command.
     * @param user The user (member) that's running the command. Member as that contains the proper info.
     * @param permissions List of the permissions the command needs to run.
     * @return A boolean if they can run the command.
     */
    private static boolean assessUserPermissions(Member user, Permission[] permissions) {
        return false;
    }


    /**
     * Builds THE instance of a command. Technically there can be more than one instance of a command,
     * but I won't be writing that code.
     * @return The name of the command. This is used for permission management purposes.
     */
    protected abstract String getID();

    /**
     * The permissions for the command. This is best handled individually (To allow for easier default permissions)
     * and therefore is abstract.
     * @return Array of the permissions for the command.
     */
    protected abstract Permission[] getPermissions();


    /**
     * This is used to run the command. Has all the extra info needed to run it in the instance of the method.
     * Basically it's just a place for the StandardCommand class to forward requests to.
     * This one is used for messages
     */
    protected abstract String run(Message message);

    /**
     * This is used to run the command. Has all the extra info needed to run it in the instance of the method.
     * Basically it's just a place for the StandardCommand class to forward requests to.
     * This one is used for slash commands
     */
    protected abstract String run(SlashCommandData slashCommand);

}
