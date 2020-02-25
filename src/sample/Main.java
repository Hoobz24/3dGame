package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.EventHandler;

import java.util.ArrayList;


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
    public Object rect = new Object(vertsCube, 400 - 30, 400 - 30, 100, 50);
    //public Object prism = new Object(vertsPrism, 400, 200, 10, 30);
    private AnimationTimer timer;
    private double exactX;
    private double exactY;
    Canvas canvas = new Canvas(800, 800);
    private ArrayList<Player> pls = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game");
        Group root = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        canvas.setOnMouseClicked(mousePressed);

        for (int i = 0; i < 200; i++) {
            objs.add(new Object(vertsCube, 0, 0, 200, 10));
        }
        set();
        update(gc);

    }

    EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println(mouseEvent);
        }
    };

    public void update(GraphicsContext gc){
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                //movement
                if (pressed == true) {
                    switch (key) {
                        case 'a':
                            direction.set(0, 3);
                            break;
                        case 's':
                            direction.set(0, 2);
                            break;
                        case 'w':
                            direction.set(0, 4);
                            break;
                        case 'd':
                            direction.set(0, 1);
                            break;
                        case 'p':
                            alive = false;
                            break;
                    }
                }
                for (int i4 = 0; i4 < length; i4++) {
                    int cx = x.get(i4);
                    int cy = y.get(i4);
                    switch (direction.get(i4)) {
                        case 1:
                            cx += 1;
                            x.set(i4, cx);

                            break;
                        case 2:
                            cy += 1;
                            y.set(i4, cy);

                            break;
                        case 3:
                            cx -= 1;
                            x.set(i4, cx);

                            break;
                        case 4:
                            cy -= 1;
                            y.set(i4, cy);

                            break;
                    }
                }

                //shifts directional array
                for (int i5 = direction.size() - 1; i5 >= 1; i5--) {
                    direction.set(i5, direction.get(i5 - 1));

                }

                if (x.get(0) > 50 || x.get(0) < 0 || y.get(0) > 50 || y.get(0) < 0) {
                    alive = false;

                }

                for (int i7 = 0; i7 < length; i7++) {

                    for (int i8 = 0; i8 < length; i8++) {
                        if (x.get(i7) == x.get(i8) && y.get(i7) == y.get(i8) && i7 != i8) {
                            alive = false;

                        }
                    }

                    if (blength > 0) {
                        if (x.get(i7) == bx.get(0) && y.get(i7) == by.get(0)) {

                            System.out.println("bruh");
                            if (direction.get(direction.size() - 1) == 1) {
                                x.add(x.get(x.size() - 1) - 1);
                                y.add(y.get(y.size() - 1));
                                direction.add(1);
                                length += 1;
                                score += 1;

                            }

                            if (direction.get(direction.size() - 1) == 2) {
                                x.add(x.get(x.size() - 1));
                                y.add(y.get(y.size() - 1) - 1);
                                direction.add(2);
                                length += 1;
                                score += 1;

                            }
                            if (direction.get(direction.size() - 1) == 3) {
                                x.add(x.get(x.size() - 1) + 1);
                                y.add(y.get(y.size() - 1));
                                direction.add(3);
                                length += 1;
                                score += 1;

                            }
                            if (direction.get(direction.size() - 1) == 4) {
                                x.add(x.get(x.size() - 1));
                                y.add(y.get(y.size() - 1) +
                                        1);
                                direction.add(4);
                                length += 1;
                                score += 1;

                            }


                            bx.clear();
                            by.clear();
                            bx.add((int) Math.round(Math.random() * 50));
                            by.add((int) Math.round(Math.random() * 50));

                        }
                    }
                }

                double rand = Math.random() * 100;
                draw(gc);
            }

        };

        timer.start();
    }

    public void set(){
        //set starting snake parts
        x.add(8);
        y.add(7);
        direction.add(2);
        pd = direction.get(0);

        bx.add(9);
        by.add(7);

        length = 1;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.PAPAYAWHIP);
        gc.fillRect(0, 0, SCENE_WIDTH, SCENE_WIDTH);
        gc.setFill(Color.BLACK);
        rect.draw(gc);
        //prism.draw(gc);

        int pos = 0;

        for(int ix = 0; ix < 50; ix++){
            for (int iy = 0; iy < 50; iy++){
                for(int i3 = 0; i3 < length; i3++){
                    if(x.get(i3) == ix && y.get(i3) == iy){
                        objs.get(pos).setX(ix * 10);
                        objs.get(pos).setY(iy * 10);
                        objs.get(pos).draw(gc);
                        pos++;
                    }

                    if(blength > 0) {
                        for (int i6 = 0; i6 < blength; i6++) {
                            if (x.get(i6) == ix && y.get(i6) == iy) {

                            }
                        }
                    }
                }
            }
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
