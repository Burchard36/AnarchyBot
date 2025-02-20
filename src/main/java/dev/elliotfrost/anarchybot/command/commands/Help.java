package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.List;

public class Help implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        ctx.getChannel().sendMessage("This is a very unhelpful message").queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}