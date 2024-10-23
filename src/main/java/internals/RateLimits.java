package internals;

import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimits {

    /** Keeps track of each rate limit start time.
     * UserID (String), (Millisecond start time, Breaks of rate limit) (Pair<\Long, Integer>)
     * Breaks of rate limit is how many times the user has broken the rate limit.
     * 0 breaks isn't rate limited, but has a cooldown of 5 seconds until their instance is deleted.
     * 1+ breaks sets the rate limit to 5 seconds
     */
    private static final Map<String, Pair<Long, Integer>> rateLimitStarts = new HashMap<>();
    private static final Queue<String> rateLimitedUsers = new LinkedList<>(); // Keeps track of the oldest user added and loops until all rate limited users are cleared.

    /**
     * If the user isn't rate limited it returns false and sets them to be rate limited.
     * @return true if they are, false if they aren't
     */
    public static boolean checkRateLimited(String userID) {
        clearQueue();
        boolean rateLimited = rateLimitStarts.getOrDefault(userID, new Pair<>(0L, 0)).getValue1() > 0;
        rateLimitUser(userID);
        return rateLimited;
    }

    private static void rateLimitUser(String userID) {
        rateLimitStarts.put(userID, new Pair<>(System.currentTimeMillis(),
                // Below gets the rate limit info or a generic one then updates the rate limit.
                rateLimitStarts.getOrDefault(userID, new Pair<>(0L, -1)).getValue1() + 1)
        );
        rateLimitedUsers.remove(userID); // Just in case they broke their rate limit :)
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
            if(System.currentTimeMillis() - rateLimitStarts.get(check).getValue0() >= 5000) {
                rateLimitedUsers.remove();
                rateLimitStarts.remove(check);
            }
            else break;
        }
    }

}
