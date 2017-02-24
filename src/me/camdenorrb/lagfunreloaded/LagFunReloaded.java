package me.camdenorrb.lagfunreloaded;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Pattern;

/**
 * Created by camdenorrb on 2/23/17.
 */

public class LagFunReloaded extends JavaPlugin implements Listener {

	private String replacement;
	private Pattern lagPattern;

	public static LagFunReloaded instance;


	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		saveDefaultConfig();

		replacement = getConfig().getString("replacement", "fun");
		lagPattern = Pattern.compile(getConfig().getString("regex", "([Ll]+)(\\W*\\d*[_]*)*([Aa@4]+)(\\W*\\d*[_]*)*([Gg]+)"));

		getServer().getPluginManager().registerEvents(this, this);
	}


	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setMessage(lagPattern.matcher(event.getMessage()).replaceAll(replacement));
	}


	public Pattern lagPattern() {
		return lagPattern;
	}

	public String replacement() {
		return replacement;
	}

	public void setLagPattern(Pattern lagPattern) {
		this.lagPattern = lagPattern;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}


}
