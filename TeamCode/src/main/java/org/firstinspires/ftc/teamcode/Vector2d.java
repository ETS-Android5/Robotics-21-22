package org.firstinspires.ftc.teamcode;

public class Vector2d extends javax.vecmath.Vector2d {

    public Vector2d() {
        super();
    }
    public Vector2d(double x, double y) {
        super(x, y);
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
