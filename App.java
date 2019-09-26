package sbBot.ServerBot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class App extends ListenerAdapter {
	public static void main(String[] args)		throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		
		// Initializes the bot
		JDA jdaBot = new JDABuilder(AccountType.BOT)
				.setToken("NDE1NjA1NTQ4Njg4NDc0MTIy.DW4Wbw.pSc8DWOIgJpiyRsfMtOCtACJ9uo").buildBlocking();
		jdaBot.addEventListener(new App());

	}

	public String Commands() {
		String sent = "The commands are : Hello, spotify, goodbye and roll.";
		return sent;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		// Obtains properties of the received message
		Message objMsg = e.getMessage();
		MessageChannel objChannel = e.getChannel();
		User objUser = e.getAuthor();
		String link = "https://open.spotify.com/embed/user/11162669754/playlist/3T16BYRndbLgTyIds158cz";

		if (objMsg.getContentRaw().charAt(0) == '!') {
			String[] strArgs = objMsg.getContentRaw().substring(1).split(" ");

			if (strArgs[0].equals("hello")) {
				// Responds to any user who says "hello"
				objChannel.sendMessage("Hello, " + objUser.getAsMention() + "!").queue();

			} else if (strArgs[0].equals("roll")) {
				int intSides = 6;
				if (strArgs.length > 1) {
					intSides = Integer.valueOf(strArgs[1]);
				}
				objChannel.sendMessage(objUser.getAsMention() + " rolls a " + (int) (Math.random() * intSides + 1))
						.queue();

			}

			if (objMsg.getContentRaw().charAt(0) == '!') {
				String[] sent = objMsg.getContentRaw().substring(1).split(" ");
				if (sent[0].equalsIgnoreCase("Spotify")) {
					objChannel.sendMessage("The spotify playlist is : " + link + "" + objUser.getAsMention()).queue();
					;
				}
			}

			if (objMsg.getContentRaw().charAt(0) == '!') {
				String[] sent = objMsg.getContentRaw().substring(1).split(" ");
				if (sent[0].equalsIgnoreCase("Commands") || sent[0].equalsIgnoreCase("Controls")
						|| sent[0].equalsIgnoreCase("help")) {
					objChannel.sendMessage(Commands()).queue();
				}
			}

			if (objMsg.getContentRaw().charAt(0) == '!') {
				String[] sent = objMsg.getContentRaw().substring(1).split(" ");
				if (sent[0].equalsIgnoreCase("Goodbye") || sent[0].equalsIgnoreCase("bye")) {
					objChannel.sendMessage("Goodbye, " + objUser.getAsMention()).queue();
				}
			}
		}
	}

}
