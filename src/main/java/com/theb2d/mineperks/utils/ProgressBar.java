package com.theb2d.mineperks.utils;



import com.google.common.base.Strings;
import org.bukkit.ChatColor;

public class ProgressBar {

    public static String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
                                 ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        String bar = Strings.repeat("" + completedColor + symbol, progressBars) + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
        int half = bar.length() % 2 == 0 ? bar.length()/2 : bar.length()/2 + 1;
        String first = bar.substring(0, half);
        String second = bar.substring(half);
        return first + ChatColor.translateAlternateColorCodes('&', " &l&7Mining Perk " + (int)Math.floor(percent*100) + "% ") + second;
    }
}
