package com.koisoftware.particles;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SkytreeParticleAnimation {

    @Test
    public void test() {
        ParticleAnimation obj = new ParticleAnimation();
        List<String> expected = new ArrayList<String>();
        expected.add("..X....");
        expected.add("....X..");
        expected.add("......X");
        expected.add(".......");
        List<String> animated = obj.animateParticle(2, "..R....");
        for (int i = 0; i < expected.size(); i++) {
            System.out.println("expected=" + expected.get(i));
            System.out.println("animated=" + animated.get(i));
        }
        assertEquals(expected, animated);
        expected.clear();
        expected.add("XXXX.XX.XXX.X.XXXX.");
        expected.add("..XXX..X..XX.X..XX.");
        expected.add(".X.XX.X.X..XX.XX.XX");
        expected.add("X.X.XX...X.XXXXX..X");
        expected.add(".X..XXX...X..XX.X..");
        expected.add("X..X..XX.X.XX.XX.X.");
        expected.add("..X....XX..XX..XX.X");
        expected.add(".X.....XXXX..X..XX.");
        expected.add("X.....X..XX...X..XX");
        expected.add(".....X..X.XX...X..X");
        expected.add("....X..X...XX...X..");
        expected.add("...X..X.....XX...X.");
        expected.add("..X..X.......XX...X");
        expected.add(".X..X.........XX...");
        expected.add("X..X...........XX..");
        expected.add("..X.............XX.");
        expected.add(".X...............XX");
        expected.add("X.................X");
        expected.add("...................");
        animated = obj.animateParticle(1, "LRRL.LR.LRR.R.LRRL.");
        for (int i = 0; i < expected.size(); i++) {
            System.out.println("expected=" + expected.get(i));
            System.out.println("animated=" + animated.get(i));
        }
    }
}
