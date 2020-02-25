package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.MatrixType;

import java.util.ArrayList;

public class Object {
    private int facesMount;
    private double vertices[];
    private double xVerts[];
    private double yVerts[];
    private double zVerts[];
    private double vertMatrix[][];
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

    private double y;
    private double z;
    private double sizeMult;
    private double a = 1;
    private double fov = 70;
    private double Zfar = 1000;
    private double Znear = .1;
    private double zp = Zfar + Znear;
    private double zm = Zfar + Znear;
    private  double angle = 45;


    public Object(double[] vertices, double x, double y, double z, double sizeMult){
        this.facesMount = vertices.length  / 3;
        this.vertices = vertices;
        this.x = x;
        this.y = y;
        this.z = z;
        this.sizeMult = sizeMult;


        xVerts = new double[vertices.length / 3];
        yVerts = new double[vertices.length / 3];
        zVerts = new double[vertices.length / 3];


        for (int i = 0; i < vertices.length / 3; i++) {
            xVerts[i] = vertices[i * 3 ];
            yVerts[i] = vertices[i * 3 + 1];
            zVerts[i] = vertices[i * 3 + 2];
        }
        printArray(xVerts);


        vertMatrix = new double[vertices.length / 3][4];
        for (int i = 0; i < vertices.length / 3 ; i++) {
            vertMatrix[i][0] = xVerts[i];
            vertMatrix[i][1] = yVerts[i];
            vertMatrix[i][2] = zVerts[i];
            vertMatrix[i][3] = 1;

        }
        transformationMatrix[0][0] = sizeMult;
        transformationMatrix[1][1] = sizeMult;
        transformationMatrix[2][2] = sizeMult;

        vertMatrix = multiply(vertMatrix, matrix);



        vertMatrix = multiply(vertMatrix, transformationMatrix);
        vertMatrix = multiply(vertMatrix, translationMatrix);

        vertMatrix = multiply(vertMatrix, rotZMatrix);
        vertMatrix = multiply(vertMatrix, rotXMatrix);
        for (int i = 0; i < vertMatrix.length; i++) {
            xVerts[i] = vertMatrix[i][0];
            yVerts[i] = vertMatrix[i][1];

        }


    }

    public void printMat(double[][] matrix){
        for (int k = 0; k < matrix.length; k++) {
            for (int f = 0; f < matrix[0].length; f++) {
                System.out.print(matrix[k][f] + ", ");
            }
            System.out.println("");
        }
    }

    public void printArray(double[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public double matrix[][] = {
        {(1/Math.tan(fov/2))/a, 0, 0, 0},
        {0, 1/Math.tan(fov/2), 0, 0},
        {0, 0, -zp/zm, -(2*Zfar*Znear)/zm},
        {0, 0, -1, 0}
    };

    public double transformationMatrix[][] = {
            {50, 0, 0, 1},
            {0, 50, 0, 1},
            {0, 0, 50, 1},
            {0, 0, 0, 1}
    };

    public double translationMatrix[][] = {
            {1, 0, 0, 2000},
            {0, 1, 0, 2000},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };

    public void calculateOrientation(){

    }

    public double rotXMatrix[][] = {
            {1, 0, 0, 0},
            {0, Math.cos(angle), -Math.sin(angle), 0},
            {0, Math.sin(angle), Math.cos(angle), 0},
            {0, 0, 0, 0}
    };

    public double rotYMatrix[][] = {
            {Math.cos(angle), 0, Math.sin(angle), 0},
            {0, 1, 0, 0},
            {-Math.sin(angle), 0, Math.cos(angle), 0},
            {0, 0, 0, 1}
    };

    public double rotZMatrix[][] = {
            {Math.cos(angle), -Math.sin(angle), 0, 0},
            {Math.sin(angle), Math.cos(angle), 0, 0},
            {0, 0 ,1, 0},
            {0, 0, 0, 0}

    };

    public double rotMatrix[][] = {
            {1/Math.sqrt(2), 1/Math.sqrt(6), 1/Math.sqrt(3), 0},
            {-Math.sqrt(3), 1/Math.sqrt(3), 0, 0},
            {-1/Math.sqrt(2), 1/Math.sqrt(6), 1/Math.sqrt(3), 0},
            {0, 0, 0, 0}

    };

    public double[][] multiply(double[][] a, double[][] b) {
        int rowsInA = a.length;
        int columnsInA = a[0].length; // same as rows in B
        int columnsInB = b[0].length;
        double[][] c = new double[rowsInA][columnsInB];
        for (int i = 0; i < rowsInA; i++) {
            for (int j = 0; j < columnsInB; j++) {
                for (int k = 0; k < columnsInA; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private  double offset = 1;
    public void draw(GraphicsContext gc){


        //y = Math.cos(offset) * 100 + 250;


        /*
        for (int i = 0; i < vertMatrix.length; i++) {
            gc.setStroke(Color.BLACK);
            gc.setFill(Color.GRAY);
            gc.fillOval(vertMatrix[i][0] + x, vertMatrix[i][1] + y, 10, 10);
        }

         */
        for (int i = 0; i < vertMatrix.length / 3; i++) {


            gc.strokeLine(vertMatrix[i * 3][0] + x, vertMatrix[i * 3][1]+ y, vertMatrix[i * 3 + 2][0]+ x, vertMatrix[i * 3 + 2][1]+ y);
            gc.strokeLine(vertMatrix[i * 3 + 2][0]+ x, vertMatrix[i * 3 + 2][1]+ y, vertMatrix[i * 3 + 1][0]+ x, vertMatrix[i * 3 + 1][1]+ y);
            gc.strokeLine(vertMatrix[i * 3 + 1][0]+ x, vertMatrix[i * 3 + 1][1]+ y, vertMatrix[i * 3][0]+ x, vertMatrix[i * 3][1]+ y);

        }

    }
}
