package Commands;

import Manager.CommandContext;
import Manager.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.format.DateTimeFormatter;

public class UserInfoCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        final TextChannel channel = ctx.getChannel();
        Member member = null;

        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessage("Correct Usage: ?userinfo @user").queue();
        } else {
            member = ctx.getMessage().getMentionedMembers().get(0);
        }

        assert member != null;
        String NAME = member.getEffectiveName();
        String TAG = member.getUser().getName() + "#" + member.getUser().getDiscriminator();
        String GUILD_JOIN_DATE = member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String DISCORD_JOINED_DATE = member.getUser().getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String ID = member.getUser().getId();
        String STATUS = member.getOnlineStatus().getKey();
        StringBuilder ROLES = new StringBuilder();
        String GAME;
        String AVATAR = member.getUser().getAvatarUrl();

        try {
            GAME = member.getActivities().toString();
        } catch (Exception e) {
            GAME = "-/-";
        }

        for (Role r : member.getRoles()) {
            ROLES.append(r.getName()).append(", ");
        }
        if (ROLES.length() > 0)
            ROLES = new StringBuilder(ROLES.substring(0, ROLES.length() - 2));
        else
            ROLES = new StringBuilder("No roles on this server.");

        if (AVATAR == null) {
            AVATAR = "No Avatar";
        }

        EmbedBuilder builder = new EmbedBuilder().setColor(0x2fb62f);
        builder.setDescription("**User information for " + member.getUser().getName() + ":**")
                .addField("Name / Nickname", NAME, false)
                .addField("User Tag", TAG, false)
                .addField("ID", ID, false)
                .addField("Current Status", STATUS, false)
                .addField("Current Game", GAME, false)
                .addField("Roles", ROLES.toString(), false)
                .addField("Guild Joined", GUILD_JOIN_DATE, false)
                .addField("Discord Joined", DISCORD_JOINED_DATE, false)
                .addField("Avatar-URL", AVATAR, false);

        if (!AVATAR.equals("No Avatar")) {
            builder.setThumbnail(AVATAR);
        }

        channel.sendMessage(builder.build()).queue();

    }

    @Override
    public String getName() {
        return "userinfo";
    }

    @Override
    public String getHelp() {
        return "Gets the users infomation.\nCorrect usage: ?userinfo @user";
    }
}

