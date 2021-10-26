package dev.elliotfrost.anarchybot;

import dev.elliotfrost.anarchybot.database.DatabaseManager;
import dev.elliotfrost.anarchybot.listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import javax.security.auth.login.LoginException;

public class Bot {
    private static DatabaseManager databaseManager = new DatabaseManager();
    private static JDA jda;


    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    private Bot() throws LoginException {
        JDA jda = JDABuilder.createLight(Config.getToken())
                .addEventListeners(new Autorole(), new Listener(), new CommandManager(), new Tickets(), new Suggestions(), new Roles(), new SlashCommands())
                .setActivity(Activity.playing("on anarchy.ciputin.cf"))
                .build();
        if (!jda.retrieveCommands().complete().contains("link")) {
            SubcommandData java = new SubcommandData("java", "Link your Java Minecraft Account!")
                    .addOption(OptionType.STRING, "username", "Your minecraft username", true);
            SubcommandData bedrock = new SubcommandData("bedrock", "Link your Bedrock Minecraft Account!")
                    .addOption(OptionType.STRING, "username", "Your minecraft username", true);
            CommandData link = new CommandData("link", "Link your minecraft accounts!")
                    .addSubcommands(java, bedrock);
            jda.updateCommands().complete();
            jda.updateCommands().addCommands(link).complete();
        }
        this.jda = jda;
    }
    public static void main(String[] args) throws LoginException { Bot bot = new Bot(); }

    public static JDA getJDA() { return jda; }
}
