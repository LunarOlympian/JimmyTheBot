package commands.standard.mirrorchats.requests;

import java.util.ArrayList;

public class Flags {

    private ArrayList<String> flags;
    public Flags(ArrayList<String> flags) {
        this.flags = new ArrayList<>();
        this.flags.addAll(flags);
    }
}
