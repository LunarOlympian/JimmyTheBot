package internals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimits {

    private static Map<String, Long> rateLimitStarts = new HashMap<>(); // Keeps track of each rate limit start time.
    private static Queue<String> rateLimitedUsers = new LinkedList<>(); // Keeps track of the oldest user added and loops until all rate limited users are cleared.

    /**
     * If the user isn't rate limited it returns false and sets them to be rate limited.
     * @return true if they are, false if they aren't
     */
    public static boolean checkRateLimited(String userID) {
        clearQueue();
        if(!rateLimitStarts.containsKey(userID)) rateLimitUser(userID);
        return rateLimitStarts.containsKey(userID);
    }

    private static void rateLimitUser(String userID) {
        rateLimitStarts.put(userID, System.currentTimeMillis());
        rateLimitedUsers.add(userID);
    }

    /**
     * Clears the
     */
    private static void clearQueue() {
        // Has a failsafe that it only runs 5 times.
        // It breaking would require a LOT of people to send a message within 5 seconds
        for(int i = 0; i < 5; i++) {
            if(rateLimitedUsers.isEmpty()) break; // Breaks if no users are rate limited!
            String check = rateLimitedUsers.peek();
            if(System.currentTimeMillis() - rateLimitStarts.get(check) >= 5000) {
                rateLimitedUsers.remove();
                rateLimitStarts.remove(check);
            }
            else break;
        }
    }

}
