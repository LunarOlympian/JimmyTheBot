package jimmy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Runner extends ListenerAdapter {

    /*
    Manages the different classes and runs the correct classes.
    In theory this prevents multiple things being activated and keeps it all efficient
    */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {

    }
}
