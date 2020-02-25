package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Player {
    private double x;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getXv() {
        return xv;
    }

    public void setXv(double xv) {
        this.xv = xv;
    }

    public double getYv() {
        return yv;
    }

    public void setYv(double yv) {
        this.yv = yv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double y;
    private double xv;
    private double yv;
    private String name;
    private int health;
    private double speed;
    public ArrayList<Integer> ints = new ArrayList<Integer>();


    public Player(double x, double y, double xv, double yv, String name, int health, double speed){
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
        this.name = name;
        this.health = health;
        this.speed = speed;
        ints.add(5);
    }

    public void update(){
        x += xv;
        y += yv;
    }

    public void draw(GraphicsContext gc){
        gc.fillRect(x, y, 10, 10);

    }
}
