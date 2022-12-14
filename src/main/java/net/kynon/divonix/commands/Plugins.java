package net.kynon.divonix.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kynon.divonix.Main;
import net.kynon.divonix.plugins.DivonixPlugin;

import java.awt.*;

public class Plugins extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Member m = event.getMember();

        if (event.getName().equals("plugins")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("All plugins used");
            eb.setColor(Color.WHITE);

            String plugins = "";
            for (DivonixPlugin plugin : Main.plugins) {
                plugins+=plugin.getProperty().getName() + "`(v" + plugin.getProperty().getVersion() + ")`" + "\n";
            }

            if (plugins.equals(""))
                eb.setDescription("There are no plugins added");
            else {
                eb.setDescription(plugins);
            }

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        }
    }
}
