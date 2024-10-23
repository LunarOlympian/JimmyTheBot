package internals;

import java.util.HashMap;
import java.util.Map;

import static internals.tools.FileRead.readFile;

public class PrivilegedUsers {
    /*
    -1 - A normal user. No privileged access.
    0 - A privileged user with normal levels of access.
    1 - Full access to everything. No rate limits, full access to developer commands.
    2 - Rate limited but full access to everything else.
    */

    private static Map<String, Integer> privilegedUsers = new HashMap<>();

    private static HashMap<String, Integer> getPrivilegedUsers() {
        HashMap<String, Integer> users = new HashMap<>();
        for(String user : readFile("internals/PrivilegedUsers.txt").split("\n")) {
            users.put(user.split(" ")[0], Integer.parseInt(user.split(" ")[1]));
        }
        return users;
    }

    /**
     * This checks if a user is registered as a developer. This gives them permissions to do whatever with Jimmy.
     * This functionality can be toggled with a command.
     * TO IMPROVE SECURITY PRIVILEGED USERS CAN ONLY BE ADDED BY DIRECTLY EDITING THE FILES.
     */

    public static int getPrivilegeLevel(String userID) {
        return privilegedUsers.getOrDefault(userID, -1);
    }

    /**
     * This data isn't saved. It can be
     * @param userRunningCommand User that runs the command
     * @param userToUpdate User being updated
     * @return Successful
     */
    public static boolean privilegedUserSetLevel(String userRunningCommand, String userToUpdate, int level) {
        if(privilegedUsers.containsKey(userRunningCommand) && privilegedUsers.containsKey(userToUpdate)) {
            if (level <= -1)
                return false; // This remove their privileged access until a reset (not ideal). So a failsafe prevents it.
            privilegedUsers.put(userToUpdate, level);
            return true;
        }
        return false;
    }
}
