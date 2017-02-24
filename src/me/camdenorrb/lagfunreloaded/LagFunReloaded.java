package me.camdenorrb.lagfunreloaded;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Pattern;

/**
 * Created by camdenorrb on 2/23/17.
 */

public class LagFunReloaded extends JavaPlugin implements Listener {

	private static final String DEFAULT_PATTERN     = "([Ll]+)(\\W*\\d*[_]*)*([Aa@4]+)(\\W*\\d*[_]*)*([Gg]+)";
	private static final String DEFAULT_REPLACEMENT = "fun";

	private static LagFunReloaded instance;

	private Pattern pattern;
	private String  replacement;


	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		saveDefaultConfig();

		pattern = Pattern.compile(getConfig().getString("regex", DEFAULT_PATTERN));
		replacement = getConfig().getString("replacement", DEFAULT_REPLACEMENT);

		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		instance = null;
		pattern = null;
		replacement = null;
	}


	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent event) {
		event.setMessage(pattern.matcher(event.getMessage()).replaceAll(replacement));
	}


	public Pattern getPattern() {
		return pattern;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}


	public static LagFunReloaded getInstance() {
		return instance;
	}

	public static String getDefaultPattern() {
		return DEFAULT_PATTERN;
	}

	public static String getDefaultReplacement() {
		return DEFAULT_REPLACEMENT;
	}

}
