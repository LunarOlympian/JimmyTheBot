package globalchat.requests;

import java.util.ArrayList;

public class Message {

    private String username;
    private String userID;
    private String serverName;
    private String text;
    private String flags;

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
    public Message(String username, String userID, String serverName,
                   String rawText) {
        this.username = username;
        this.userID = userID;
        this.serverName = serverName;

    }

    /**
     * Sanitized the text so nothing breaks and pings don't work.
     * @param rawText Raw text from the request.
     */
    private void sanitizeText(String rawText) {
        String modifiedText = rawText;
        modifiedText = modifiedText.replaceAll("@", "");
        // Gets all the flags
    }
}
