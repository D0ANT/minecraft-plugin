package mc.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

import static mc.plugin.Commandmanager.e_p;
import static org.bukkit.Bukkit.getServer;

public class Eventmanager implements Listener {

    ConsoleCommandSender log = getServer().getConsoleSender();

    Main plugin;

    public void plug(Main instance){
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent j){
        log.sendMessage("enable 111111111111111111");
        j.setJoinMessage(j.getPlayer().getName() + plugin.getConfig().getString("join.message")); ///////////////////////plugin.getConfig().getString()부분에 널포인터 익셉션
        log.sendMessage("enable 22222222222222");
        if(plugin.getConfig().getBoolean("join.title",true)){
            log.sendMessage("enable 33333333333333333");
            j.getPlayer().sendTitle(plugin.getConfig().getString("join.message"), plugin.getConfig().getString("join.message"),20,60,20);
            log.sendMessage("enable 4444444444444444444");
        }
    }
    @EventHandler
    public void onExplosive(EntityExplodeEvent e){
        if(e_p == 1){
            e.setCancelled(true);
        } else if(e_p == 0){
            e.setCancelled(false);
        }
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void interaction(PlayerInteractEvent e) {
        Player p=e.getPlayer();
        if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK) {
            //p.sendMessage("test1");
            ItemMeta mt=p.getItemInHand().getItemMeta();
            if(p.getItemInHand().getType().equals(Material.APPLE)) {
                assert mt != null;
                if (mt.getDisplayName().equals("apple")) {
                    p.sendMessage("특별한 사과를 먹으려고 시도 했다!");
                    if((p.getFoodLevel()+3) > 20) {
                        p.sendMessage(ChatColor.RED + "이미 배불러서 몸이 사과먹기를 거부한다!");
                    }else if ((p.getFoodLevel() + 3) <= 20) {
                        p.setFoodLevel(p.getFoodLevel() + 3);
                        p.sendMessage(ChatColor.AQUA + "사과를 먹었다!\n배고픔이 3만큼 찼다!");
                        p.performCommand("clear @s apple 1");
                    }
                }
            }
        }
    }
    @EventHandler
    public void inv_click(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(Objects.requireNonNull(e.getClickedInventory()).getHolder() instanceof inventory){
            System.out.println("invclick 22222222222222");
            e.setCancelled(true);
            System.out.println("invclick 333333333333333");
            if(e.getRawSlot() == 13){
                p.closeInventory();
                p.sendTitle("당신은","다검을 클릭했다",20,40,20);
            }else if(e.getRawSlot() == 22){
                p.closeInventory();
                p.sendTitle("당신은","금 주괴를 클릭했다",20,40,20);
            }
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        e.setCancelled(true);
        Player p = e.getPlayer();
        if(!p.isOp()){
            e.setCancelled(true);
            p.sendMessage("명령권한이 없는 플레이어는 채팅을 칠수 없으니 도개미에게 연락하세요.");
        } else if(p.isOp()){
            Bukkit.broadcastMessage(ChatColor.BLUE + "<"+ChatColor.GREEN + p.getName() + ChatColor.BLUE + ">" + " " + ChatColor.YELLOW +  e.getMessage());
        }
    }
}
