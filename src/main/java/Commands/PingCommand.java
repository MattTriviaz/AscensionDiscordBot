package Commands;

import Manager.CommandContext;
import Manager.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class PingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        EmbedBuilder success = new EmbedBuilder();
        success.setColor(0x66f542);
        success.setTitle("Ping");
        success.setDescription("Pong");
        ctx.getChannel().sendMessage(success.build()).queue();
}

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Pong";
    }
}
