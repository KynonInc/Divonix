package net.kynon.divonix;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.kynon.divonix.config.ConfigFile;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static String version = "1.0.0";

    public static JDA jda;

    public static void main(String[] args) {

        try {
            ConfigFile.loadConfig();

            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(new FileInputStream("Divonix/config.yml"));
            JDABuilder jdaBuilder = JDABuilder.createDefault(data.get("bot-token").toString());
            jdaBuilder.setStatus(OnlineStatus.valueOf(data.get("bot-status").toString()));
            jdaBuilder.setChunkingFilter(ChunkingFilter.ALL);
            jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
            jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
            jdaBuilder.enableIntents(GatewayIntent.GUILD_MESSAGES);
            jdaBuilder.addEventListeners();
            jda = jdaBuilder.build();

            System.out.println("\n\n\n\n\n\n\n");
            System.out.println("" +
                    "           ██████╗░██╗██╗░░░██╗░█████╗░███╗░░██╗██╗██╗░░██╗\n" +
                    "           ██╔══██╗██║██║░░░██║██╔══██╗████╗░██║██║╚██╗██╔╝\n" +
                    "           ██║░░██║██║╚██╗░██╔╝██║░░██║██╔██╗██║██║░╚███╔╝░\n" +
                    "           ██║░░██║██║░╚████╔╝░██║░░██║██║╚████║██║░██╔██╗░\n" +
                    "           ██████╔╝██║░░╚██╔╝░░╚█████╔╝██║░╚███║██║██╔╝╚██╗\n" +
                    "           ╚═════╝░╚═╝░░░╚═╝░░░░╚════╝░╚═╝░░╚══╝╚═╝╚═╝░░╚═╝\n\n" +
                    "                            version: v" + version + "\n\n\n" +
                    "                  the bot has successfully started");
            System.out.println("\n\n\n\n\n\n\n");

        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }
    }
}