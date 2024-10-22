package mirrorchats.requests;

import java.util.ArrayList;

public class Request {

    private String username;
    private String userID;
    private String serverName;
    private String serverID;
    private String text; // Action requests are formatted like /!delete (message id here)!/
    private Flags flags; // Modifications to the message such as a local message, a special format, non-reactable, etc.

    private ArrayList<String> servers = new ArrayList<>();

    private boolean halt; // A fallback if something is wrong and the message shouldn't be sent.
    /**
     * Message request. Instance of a message that is cleared when it's sent.
     * Last 1000 messages are tracked and able to be reacted to.
     * @param username The username/nickname of the user it was sent to.
     * @param userID The userID of the user that sent it.
     * @param serverName The name of the servers this was sent from.
     * @param rawText The text of the message. Sanitized and flags handled after it's sent.
     */
    public Request(String username, String userID, String serverName,
                   String rawText) {
        this.username = username;
        this.userID = userID;
        this.serverName = serverName;
        this.text = sanitizeText(rawText);
    }

    /**
     * Sanitized the text so nothing breaks and pings don't work.
     * @param rawText Raw text from the request.
     */
    private String sanitizeText(String rawText) {
        String modifiedText = rawText;
        modifiedText = modifiedText.replaceAll("@", "");


        // Handles all the flags
        String flagString = rawText.toLowerCase();
        ArrayList<String> flags = new ArrayList<>();

        if(flagString.contains("?local?")) flags.add("Local");
        if(flagString.contains("?noreactions?")) flags.add("No reactions");

        modifiedText = modifiedText.replaceAll("\\?\\S{3,20}\\?", "");

        this.flags = new Flags(flags);

        return modifiedText;
    }
}
