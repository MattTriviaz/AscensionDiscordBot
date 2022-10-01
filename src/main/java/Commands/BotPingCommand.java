package Commands;


import Manager.CommandContext;
import Manager.ICommand;
import net.dv8tion.jda.api.JDA;

public class BotPingCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue((ping) -> ctx.getChannel().sendMessageFormat("Reset ping: %sms\nWS ping: %sms",
                ping, jda.getGatewayPing()).queue()
        );
    }

    @Override
    public String getName() {
        return "botping";
    }

    @Override
    public String getHelp() {
        return "Shows current ping of bot to discord servers";
    }
}
