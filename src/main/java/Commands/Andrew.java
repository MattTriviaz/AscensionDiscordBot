package Commands;

import Manager.CommandContext;
import Manager.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class Andrew implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        EmbedBuilder success = new EmbedBuilder();
        success.setColor(0x75a832);
        success.setTitle("Andrew?");
        success.setDescription("Andrew is kinda omega?");
        ctx.getChannel().sendMessage(success.build()).queue();

    }

    @Override
    public String getName() {
        return "andrew";
    }

    @Override
    public String getHelp() {
        return "Says Andrew sucks";
    }
}
