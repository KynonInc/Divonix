package net.kynon.divonix.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kynon.divonix.Main;

import java.awt.*;

public class Ping extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Member m = event.getMember();

        if (event.getName().equals("ping")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Bot's ping");
            eb.setColor(Color.WHITE);

            long ping = Main.jda.getGatewayPing();

            eb.setDescription("Latency: `" + ping + "ms`");

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        }
    }
}
