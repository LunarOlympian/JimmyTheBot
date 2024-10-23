package commands.standard.mirrorchats;

import java.util.ArrayList;

public class MirrorChat {
    // Global chat object! Stores the info about the chat.
    private String ID;
    private String name;
    private String owner;

    private ArrayList<String> mods;


    private ArrayList<String> servers = new ArrayList<>();


    public MirrorChat(String name, String owner) {
        this.name = name;
        this.owner = owner;
        generateID();
    }

    private void generateID() {
        // Format is Colour:3-digit number:Colour:3-digit number
        String[] colours = new String[]{"red", "orange", "yellow", "green", "blue", "indigo",
                "violet", "purple", "pink", "cyan", "grey", "black", "white", "aqua"};

        // 14 * 1000 * 14 * 1000 = 196,000,000
        // So the IDs should be good for a while...

        // Checks if it's a duplicate ID


        this.ID = colours[(int) Math.round(Math.random() * (colours.length - 1))] + ":" +
                Math.round(Math.random() * 1000) + ":" + colours[(int) Math.round(Math.random() * (colours.length - 1))] +
                ":" + Math.round(Math.random() * 1000);
    }
    // --------------------------------------------------


    // Mod stuff
    // --------------------------------------------------
    public boolean checkModerator(String id) {
        return mods.contains(id);
    }

    public void addModerator(String id) {
        if(!mods.contains(id)) mods.add(id);
    }

    public void removeModerator(String id) {
        mods.remove(id);
    }
    // --------------------------------------------------


    // Chat management
    // --------------------------------------------------

    // --------------------------------------------------


    // Commands
    // --------------------------------------------------

    // --------------------------------------------------

}
