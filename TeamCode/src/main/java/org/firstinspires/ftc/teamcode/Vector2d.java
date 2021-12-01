package org.firstinspires.ftc.teamcode;

// This class used to use vecmath.Vector2d by extending it,
// but since the vecmath package was having trouble integrating into
// the robotics code build environment, vecmath was just removed instead.

public class Vector2d /* extends javax.vecmath.Vector2d */ {
    public double x;
    public double y;

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d() {
    }
    public Vector2d(double x, double y) {
        set(x, y);
    }

    // Static helper functions

    public static Vector2d construct() {
        return new Vector2d();
    }
    public static Vector2d construct(double x, double y) {
        return new Vector2d(x, y);
    }
    public static Vector2d polar(double r, double theta) {
        return Vector2d.construct(r, 0).rotate(theta);
    }

    // Rotate vector in place
    // Positive angle is counterclockwise
    // Angle measured in radians
    public Vector2d rotate(double angle) {
        x = x*Math.cos(angle) - y*Math.sin(angle);
        y = y*Math.cos(angle) + x*Math.sin(angle);
        return this;
    }
}
