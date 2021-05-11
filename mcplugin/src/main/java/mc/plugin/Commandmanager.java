package mc.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandmanager implements CommandExecutor {
    public static int e_p = 1;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (sender != null) {
            if(cmd.getName().equalsIgnoreCase("inventory")){
                inventory screen = new inventory();
                if(args[0].equalsIgnoreCase("open")){
                    p.openInventory(screen.getInventory());
                }
            } else if(cmd.getName().equalsIgnoreCase("fly")){
                if(p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
                    if (args[0].equalsIgnoreCase("enable")) {
                        p.setAllowFlight(true);
                        p.sendMessage(ChatColor.GREEN + "[D0ANT plugin] fly를 활성화 했습니다.");
                    } else if (args[0].equalsIgnoreCase("disable")) {
                        p.setAllowFlight(false);
                        p.sendMessage(ChatColor.GREEN + "[D0ANT plugin] fly를 비활성화 했습니다.");
                    }
                } else{
                    p.sendMessage(ChatColor.RED + "[D0ANT plugin] fly 명령어는 adventure 또는 survival 모드에서 작동합니다.");
                }
            } else if(cmd.getName().equalsIgnoreCase("random")){
                int max = Integer.parseInt(args[1]);
                int min = Integer.parseInt(args[0]);
                int random;
                if(args.length == 2){
                    random = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
                    Bukkit.broadcastMessage("[DOANT plugin] " + "최솟값은 "+ min + "최댓값은 " + max + "일때 생성된 랜덤 값 : " + random);
                    
                } else {
                    sender.sendMessage("args 입력값은 2개이지만 현재 입력된 args 수는 " + args.length + "개 입니다.");
                }

            } else if(cmd.getName().equalsIgnoreCase("explosion_protection") || cmd.getName().equalsIgnoreCase("ep")){
                if(e_p == 1){
                    e_p = 0;
                    sender.sendMessage("[DOANT plugin]" + ChatColor.AQUA + "폭발방지가 비허용 되었습니다.");
                } else if(e_p == 0){
                    e_p = 1;
                    sender.sendMessage("[DOANT plugin]" + ChatColor.AQUA + "폭발방지가 허용 되었습니다.");
                }
            }
        } else {
            assert false;
            sender.sendMessage("[DOANT plugin]" + "사람이 아니면 이 명령어를 사용하지 못합니다!");
        }
        return false;
    }
}
