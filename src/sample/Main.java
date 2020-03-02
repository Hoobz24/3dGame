package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.EventHandler;

import java.io.File;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends Application {

    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    ArrayList<Integer> direction = new ArrayList<>();
    ArrayList<Integer> bx = new ArrayList<>();
    ArrayList<Integer> by = new ArrayList<>();
    ArrayList<Object> objs = new ArrayList<>();
    ArrayList<Object> berries = new ArrayList<>();

    int pd;
    int length = 1;
    int blength = 1;
    int score = 0;
    char key;
    boolean pressed;
    boolean alive = true;
    double fps;


    private final double SCENE_WIDTH = 800;
    ArrayList<Double> temp = new ArrayList<Double>();

    public double vertsCube[] = {
            -1.0f,-1.0f,-1.0f, // triangle 1 : begin
            -1.0f,-1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f, // triangle 1 : end
            1.0f, 1.0f,-1.0f, // triangle 2 : begin
            -1.0f,-1.0f,-1.0f,
            -1.0f, 1.0f,-1.0f, // triangle 2 : end
            1.0f,-1.0f, 1.0f,
            -1.0f,-1.0f,-1.0f,
            1.0f,-1.0f,-1.0f,
            1.0f, 1.0f,-1.0f,
            1.0f,-1.0f,-1.0f,
            -1.0f,-1.0f,-1.0f,
            -1.0f,-1.0f,-1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f,-1.0f,
            1.0f,-1.0f, 1.0f,
            -1.0f,-1.0f, 1.0f,
            -1.0f,-1.0f,-1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f,-1.0f, 1.0f,
            1.0f,-1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f,-1.0f,-1.0f,
            1.0f, 1.0f,-1.0f,
            1.0f,-1.0f,-1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f,-1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f,-1.0f,
            -1.0f, 1.0f,-1.0f,
            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f,-1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f,-1.0f, 1.0f

    };
    public double vertsPrism[] = {
            1, 1, 1,
            -1, -1, 1,
            1, -1, 1
    };

    public Object rect = new Object(vertsCube, 500, 400, 100, 40);
    public Object monk;
    public Object cube;
    //public Object prism = new Object(vertsPrism, 400, 200, 10, 30);
    private AnimationTimer timer;
    private double exactX;
    private double exactY;
    Canvas canvas = new Canvas(800, 800);
    private ArrayList<Player> pls = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{




        double tempVerts[] = new double[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            tempVerts[i] = temp.get(i);
        }
        monk = new Object(Pipeline.unwrapContent("monk.obj"), 400, 400, 100, 5);

        primaryStage.setTitle("Game");
        Group root = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        canvas.setOnMouseClicked(mousePressed);


        update(gc);

    }

    EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            monk.switchRenderType();
        }
    };

    public void update(GraphicsContext gc){
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                draw(gc);
            }
        };

        timer.start();
    }



    public void draw(GraphicsContext gc){
        gc.setFill(Color.PAPAYAWHIP);
        gc.fillRect(0, 0, SCENE_WIDTH, SCENE_WIDTH);
        gc.setFill(Color.BLACK);
        //rect.draw(gc);
        //prism.draw(gc);
        monk.draw(gc);
        //cube.draw(gc);
    }


    public static void main(String[] args) {
        launch(args);
    }
}