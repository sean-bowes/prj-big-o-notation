package com.koisoftware.particles;

import java.util.ArrayList;
import java.util.List;

public class ParticleAnimation {

    public List<String> animateParticle(int speed, String init) {
        int containerSize = init.length();
        int time = 0;
        List<Particle> particleList = initializeParticles(init, speed);
        List<String> containerOverTimeList = new ArrayList<>();
        while (true) {
            List<Integer> particlePositionList = calculateParticlePosition(particleList, time);
            containerOverTimeList.add(paintContainer(particlePositionList, containerSize));
            if (!hasParticlesInContainer(particlePositionList, containerSize)) {
                break;
            }
            time = time + 1;
        }
        return containerOverTimeList;
    }

    private final boolean hasParticlesInContainer(List<Integer> positionList, int containerSize) {
        for (Integer particlePosition : positionList) {
            if (0 <= particlePosition && particlePosition <= containerSize) {
                return true;
            }
        }
        return false;
    }

    private final List<Integer> calculateParticlePosition(List<Particle> particleList, int time) {
        List<Integer> positionList = new ArrayList<>();
        for (Particle particle : particleList) {
            positionList.add(particle.locationAfterTime(time));
        }
        return positionList;
    }

    private final String paintContainer(List<Integer> positionList, int containerSize) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < containerSize; i++) {
            if (positionList.contains(i)) {
                builder.append("X");
            } else {
                builder.append(".");
            }
        }
        return builder.toString();
    }

    private final List<Particle> initializeParticles(String init, int speed) {
        List<Particle> particleList = new ArrayList<>();
        int containerSize = init.length();
        for (int i = 0; i < containerSize; i++) {
            char current = init.charAt(i);
            if (current != '.') {
                particleList.add(new Particle(current, i, speed));
            }
        }
        return particleList;
    }

    public static void main(String[] args) {
        ParticleAnimation obj = new ParticleAnimation();
        List<String> expected = new ArrayList<String>();
        expected.add("..X....");
        expected.add("....X..");
        expected.add("......X");
        expected.add(".......");
        List<String> animateParticled = obj.animateParticle(2, "..R....");
        for (int i = 0; i < expected.size(); i++) {
            System.out.println("expected=" + expected.get(i));
            System.out.println("animated=" + animateParticled.get(i));
        }
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
        animateParticled = obj.animateParticle(1, "LRRL.LR.LRR.R.LRRL.");
        for (int i = 0; i < expected.size(); i++) {
            System.out.println("expected=" + expected.get(i));
            System.out.println("animated=" + animateParticled.get(i));
        }
    }
}