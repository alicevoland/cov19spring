package com.mvoland.cov19api.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PercentCounter {
    private final int size;
    private final int steps;
    private final PercentLogger logger;
    Set<Integer> selectedTicks;
    private int ticks;

    public PercentCounter(int size, int steps, PercentLogger logger) {
        this.size = size;
        this.steps = steps;
        this.logger = logger;
        this.ticks = 0;
        selectedTicks = new HashSet<>();
        selectedTicks.add(1);
        selectedTicks.add(size);
        IntStream.range(1, steps).forEach(step -> {
            selectedTicks.add(Math.round(1f * step * size / steps));
        });
    }

    public synchronized void tick() {
        ticks++;
        if (selectedTicks.contains(ticks)) {
            logger.log(ticks, size, Math.round(100f * ticks / size));
        }
    }

}
