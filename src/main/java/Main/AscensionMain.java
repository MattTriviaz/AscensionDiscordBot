package Main;

import Manager.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class AscensionMain {
    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("ODc3OTY3MTgzMDUxOTExMjEx.YR6UqA.um88P7Q5TGJ99Bn05KyJQCS-bF8",
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_VOICE_STATES).disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.EMOTE
                )).enableCache(CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY).addEventListeners(new Listener())
                .setActivity(Activity.playing("Ascension | !help"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }
}