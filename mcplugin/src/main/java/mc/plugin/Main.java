package mc.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    ConsoleCommandSender log = getServer().getConsoleSender();

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(Objects.requireNonNull(this.getConfig().getString("enable")).isEmpty() || this.getConfig().getString("enable") == null){
            this.getConfig().addDefault("enable","[D0ANT_plugin] server start!");
            saveConfig();
        }
        if(Objects.requireNonNull(this.getConfig().getString("disable")).isEmpty() || this.getConfig().getString("disable") == null){
            this.getConfig().addDefault("disable","[D0ANT_plugin] server stop..");
            saveConfig();
        }
        if(Objects.requireNonNull(this.getConfig().getString("cmdmessage")).isEmpty() || this.getConfig().getString("cmdmessage") == null){
            this.getConfig().addDefault("cmdmessage","이서버의 서버장: 도개미, 플러그인 개발자: 도개미");
            saveConfig();
        }

        if(Objects.requireNonNull(this.getConfig().getString("join.message")).isEmpty() || this.getConfig().getString("join.message") == null){
            this.getConfig().addDefault("join.message","도개미 서버에 오신것을 환영합니다!");
            saveConfig();
        }
        if(!(this.getConfig().getBoolean("join.title", false))&& !(this.getConfig().getBoolean("join.title",true))){
            this.getConfig().addDefault("join.title","true");
            saveConfig();
        }

        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        log.sendMessage(ChatColor.AQUA + this.getConfig().getString("enable"));
        Bukkit.getPluginManager().registerEvents(new mc.plugin.Eventmanager(), this);
        Objects.requireNonNull(getCommand("explosion_protection")).setExecutor(new mc.plugin.Commandmanager());
        Objects.requireNonNull(getCommand("inventory")).setExecutor(new mc.plugin.Commandmanager());
        Objects.requireNonNull(getCommand("inventory")).setTabCompleter(new mc.plugin.Tabcomplete());
        Objects.requireNonNull(getCommand("random")).setExecutor(new mc.plugin.Commandmanager());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new mc.plugin.Commandmanager());
        Objects.requireNonNull(getCommand("fly")).setTabCompleter(new mc.plugin.Tabcomplete());
        Objects.requireNonNull(getCommand("do_config")).setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.sendMessage(ChatColor.RED + this.getConfig().getString("disable"));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender != null) {
            if(cmd.getName().equalsIgnoreCase("do_config")) {
                if(args.length > 0){
                    if (args[0].equalsIgnoreCase("reload")) {
                        this.reloadConfig();
                        this.saveConfig();
                    } else if (args[0].equalsIgnoreCase("say")) {
                        sender.sendMessage("컨피그 파일에 쓰여있는 내용은 다음과 같습니다 : " + this.getConfig().getString("cmdmessage"));
                    }
                }else{
                sender.sendMessage("do_config 커맨드의 args가 존재하지 않습니다.");}
            }
        }
        return false;
    }
}