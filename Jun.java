package jun1.jun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public final class Jun extends JavaPlugin implements Listener {


    public static Jun Plugin;
    private Scoreboard board;
    private Objective obj ;
    private Score sre, sre2 , sre3 ,sre4;

    private int block;
    private int Ste,Dlt,cdt,Co;

    Jun2 Level1;


    @Override
    public void onEnable(){

        getServer().getPluginManager().registerEvents(this, this);
        Plugin = this;

        Level1 = new Jun2();
    }
        // Plugin startup logic


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        Level1.BossbarGui(p);


        scboard(p);
        p.sendMessage("debug test1");
    }


    public void scboard(Player player){
        ScoreboardManager Sm = Bukkit.getScoreboardManager();

        board = Sm.getNewScoreboard();
        obj = board.registerNewObjective("Example", "jun");

        sre = obj.getScore("레벨 :" + getConfig().getInt("level"));
        sre2 = obj.getScore("등급 :");
        sre3 = obj.getScore("머니 :");
        sre4 = obj.getScore("블럭 :" +getConfig().getInt("Block"));

        obj.setDisplayName(ChatColor.AQUA + "---내정보---");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        sre.setScore(1);
        sre2.setScore(2);
        sre3.setScore(3);
        sre4.setScore(4);

        player.setScoreboard(board);
    }
    public void BlockKind(Player player, Block NowBlock ){

        Ste = 2;
        Dlt = 3;
        cdt = 4;
        Co = 5;

        if(NowBlock.getType() == Material.STONE){
            getConfig().set("Block",  getConfig().getInt("Block") + Ste);
            saveConfig();
            player.sendMessage("스톤 :" + getConfig().getInt("Block"));



        }



    }
    //레벨 관리
    @EventHandler
    public void PlayerBlock(BlockBreakEvent e) {

        Player p = e.getPlayer();
        BlockKind(p,e.getBlock());

        Level1.Level(p,e.getBlock());

        Level1.reloadTitle();
        Level1.BossbarPogrs(p);



        scboard(p);


    }






}
