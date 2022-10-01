package Commands;

import Manager.CommandContext;
import Manager.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public class ClearCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        String[] args = ctx.getMessage().getContentRaw().split("\\s+");

        if (args.length < 2) {
            EmbedBuilder success = new EmbedBuilder();
            success.setColor(0xa11b1b);
            success.setTitle("Specify the amount to delete");
            success.setDescription("Usage: !clear [# of messages]");
            ctx.getChannel().sendMessage(success.build()).queue();
        } else {
            try {
                List<Message> messages = ctx.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                ctx.getChannel().deleteMessages(messages).queue();

                // Success
                EmbedBuilder success = new EmbedBuilder();
                success.setColor(0x4bb031);
                success.setTitle("✅ Success");
                success.setDescription("Deleted " + args[1] + " messages");
                ctx.getChannel().sendMessage(success.build()).queue();
            } catch (IllegalArgumentException e) {
                if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                    // Too Many Messages
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(0xa11b1b);
                    error.setTitle("😡 Too Many Messages Selected");
                    error.setDescription("Between 1 - 100 Messages can be deleted at one time");
                    ctx.getChannel().sendMessage(error.build()).queue();
                } else {
                    // Messages Too Old
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(0xa11b1b);
                    error.setTitle("😡 Selected messages are older than two weeks");
                    error.setDescription("Messages that are two weeks or older cannot be deleted");
                    ctx.getChannel().sendMessage(error.build()).queue();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Clears the chat for # amount";
    }
}
