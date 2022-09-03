package net.kynon.divonix;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.kynon.divonix.config.ConfigFile;
import net.kynon.divonix.plugins.DivonixPlugin;
import net.kynon.divonix.plugins.PluginLoader;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.*;;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static String version = "1.0.0";

    public static JDA jda;

    public static List<DivonixPlugin> plugins = new ArrayList<>();

    public static void main(String[] args) {

        new File("plugins").mkdirs();

        try {
            ConfigFile.loadConfig();

            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(new FileInputStream("divonix_config.yml"));
            JDABuilder jdaBuilder = JDABuilder.createDefault(data.get("bot-token").toString());
            jdaBuilder.setStatus(OnlineStatus.valueOf(data.get("bot-status").toString()));
            jdaBuilder.setChunkingFilter(ChunkingFilter.ALL);
            jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
            jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
            jdaBuilder.enableIntents(GatewayIntent.GUILD_MESSAGES);
            jdaBuilder.addEventListeners();
            jda = jdaBuilder.build();

            jda.upsertCommand("plugins", "Display a list of enabled Divonix plugins").queue();

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

            for (File f : new File("plugins").listFiles()) {
                if(f.isDirectory()) {
                    continue;
                }

                if(!f.getName().endsWith(".jar")) {
                    continue;
                }

                DivonixPlugin p = null;

                try {
                    p = PluginLoader.loadPlugin(f);
                } catch (Exception e) {
                    System.out.println("[!] Failed to load plugin!");

                    e.printStackTrace();
                }

                Main.plugins.add(p);
            }

            for (DivonixPlugin p : plugins) {
                p.onEnable();
                System.out.println("Plugin " + p.getProperty().getName() + " has been enabled (" + p.getProperty().getVersion() + ")");
            }

        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }
    }
}