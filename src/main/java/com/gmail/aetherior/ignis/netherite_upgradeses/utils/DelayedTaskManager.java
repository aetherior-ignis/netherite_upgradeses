package com.gmail.aetherior.ignis.netherite_upgradeses.utils;

import com.gmail.aetherior.ignis.netherite_upgradeses.netherite_upgradeses;
import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = netherite_upgradeses.MODID)
public class DelayedTaskManager {
    private static final int TICKS_PER_SECOND = 20;
    private static final int DELAY_SECONDS = 5;
    private static final Logger LOGGER = LogUtils.getLogger();
    static {
        LOGGER.info("DelayedTaskManager class loaded!");
    }

    private static HashMap<Integer, List<Runnable>> tasks = new HashMap<>();

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            int currentTick = event.getServer().getTickCount();
            if (tasks.containsKey(currentTick)) {
                for (Runnable task : tasks.get(currentTick)) {
                    task.run();
                    LOGGER.info(task.toString() + "is comlited");
                }
                tasks.remove(currentTick);
            }
        }
    }

    public static void scheduleTask(int delayTicks, Runnable task) {
        throw new IllegalStateException("This method must be called with server tick");
    }

    public static void scheduleTask(ServerPlayer player, int delayTicks, Runnable task) {
        int tick = getCurrentServerTick(player.level()) + delayTicks;
//        tasks.computeIfAbsent(getCurrentServerTick() + delayTicks, k -> new ArrayList<>()).add(task);
        if (!tasks.containsKey(tick)) {
            tasks.put(tick, new ArrayList<>());
        }
        tasks.get(tick).add(task);
        LOGGER.info("New task was added: " + task + " delay: " + delayTicks);
    }

    private static int getCurrentServerTick(Level level) {
        return level.getServer().getTickCount();
    }
}