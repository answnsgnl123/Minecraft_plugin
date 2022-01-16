package jun1.jun;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Jun2{
    private double TotalExp;


    private double SubjectExp = 5;
    private int ConfigBlock;
    public BossBar bb = Bukkit.createBossBar("경험치: "  + "1" + "/" + TotalExp , BarColor.YELLOW, BarStyle.SEGMENTED_20);







   public void BossbarGui(Player p) {
       bb.addPlayer(p);
   }
    public void BossbarPogrs(Player p) {
        TotalExp = Jun.Plugin.getConfig().getInt("total");


       if(TotalExp == SubjectExp) {

           p.sendMessage("[레벨업] + 1증가");

           SubjectExp += 5 ;

           Jun.Plugin.getConfig().set("level", Jun.Plugin.getConfig().getInt("level") + 1);
           Jun.Plugin.getConfig().set("total", 1);
           Jun.Plugin.saveConfig();
       }

        bb.setProgress(TotalExp / SubjectExp * 1);
    }


    public void reloadTitle() {
        TotalExp = Jun.Plugin.getConfig().getInt("total");
       bb.setTitle("경험치: "  + SubjectExp + "/" + TotalExp);
    }
    public void Level(Player e ,Block NowBlock){



        Jun.Plugin.getConfig().set("total", Jun.Plugin.getConfig().getInt("total") + 1);
        Jun.Plugin.saveConfig();
        e.sendMessage("경험치 :[수정]" + Jun.Plugin.getConfig().getInt("total"));

    }




}
