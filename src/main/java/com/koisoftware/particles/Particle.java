package com.koisoftware.particles;

public class Particle {

    private ParticleDirection direction;
    private int startLocation;
    private int speed;

    public Particle(char direction, int startLocation, int speed) {
        if (!(direction == 'R' || direction == 'L')) {
            throw new RuntimeException("Can not determine direction of partical L=Left, R=Right");
        }
        if (direction == 'R') {
            this.direction = ParticleDirection.Right;
        } else {
            this.direction = ParticleDirection.Left;
        }
        this.speed = speed;
        this.startLocation = startLocation;
    }

    public final int locationAfterTime(int time) {
        if (direction == ParticleDirection.Right) {
            return (time * speed) + startLocation;
        } else {
            return startLocation - (time * speed);
        }
    }
}
