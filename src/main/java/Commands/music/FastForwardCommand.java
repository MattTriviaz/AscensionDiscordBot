package Commands.music;

import Manager.CommandContext;
import Manager.GuildMusicManager;
import Manager.ICommand;
import Manager.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class FastForwardCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Correct usage is `!ff [seconds]`").queue();
            return;
        }
        Integer integer;
        try {
            integer = Integer.parseInt(ctx.getArgs().get(0));
        } catch (NumberFormatException e) {
            integer = null;
        }
        if (integer == null) {
            channel.sendMessage("Correct usage is `!ff [seconds]`").queue();
            return;
        }

        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("Bot needs to be in voice channel").queue();
            return;
        }

        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        AudioTrack playingTrack = musicManager.audioPlayer.getPlayingTrack();
        if (playingTrack == null) {
            channel.sendMessage("You're currently not playing a song").queue();
            return;
        }
        long newPosition = Math.min(playingTrack.getDuration(), musicManager.audioPlayer.getPlayingTrack().getPosition() + integer * 1000);

        playingTrack.setPosition(newPosition);
        channel.sendMessage("You have fast forwarded " + integer + " seconds").queue();

    }

    @Override
    public String getName() {
        return "ff";
    }

    @Override
    public String getHelp() {
        return "Fast forwards the song\nUsage: `!ff [seconds]`";
    }
}
