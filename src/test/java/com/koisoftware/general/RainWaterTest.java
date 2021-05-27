package com.koisoftware.general;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class RainWaterTest {

    @Test
    public void findAccumulatedWater_NoWaterAccumulated() {
        assertEquals(0L, RainWater.findAccumulatedWater(new int[]{2, 1}));
    }

    @Test
    public void findAccumulatedWater_SomeWaterAccumulated() {
        int[] i = new int[]{2, 0, 2, 1, 3, 2, 6,
                0, 5, 2, 10, 5, 5, 0, 0, 2, 4};
        System.out.println(RainWater.findAccumulatedWater(i));
        assertEquals(
                25L,
                RainWater.findAccumulatedWater(i));
    }
}
