import internals.JimmyFiles;
import jimmy.Runner;
import mirrorchats.MirrorChatRunner;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

import static internals.JimmyFiles.*;

public class JimmyMain {



    public static void main(String[] args) {
        // Runs Jimmy.
        JDABuilder.createLight(jimmySecret,
                EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))

                .addEventListeners(new Runner())

                .build();

        // Starts the mirror chat runner.
        new MirrorChatRunner();
    }
}
