package com.oftx.snowballparticles;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SnowballParticles extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 注册事件监听器
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // 插件禁用时的逻辑
    }

    @EventHandler
    public void onSnowballThrow(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getEntity();

            // 创建一个定时任务，持续生成粒子效果
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (snowball.isDead() || !snowball.isValid()) {
                        this.cancel();
                        return;
                    }

                    // 在雪球位置生成 snowflake 粒子
                    snowball.getWorld().spawnParticle(Particle.SNOWFLAKE, snowball.getLocation(), 1, 0, 0, 0, 0);
                }
            }.runTaskTimer(this, 0, 1); // 每 tick 运行一次
        }
    }
}
