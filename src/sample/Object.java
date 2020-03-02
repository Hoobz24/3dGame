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
    private double renderer = 4;

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

    public void switchRenderType(){
        renderer++;
        if (renderer > 5) renderer = 1;
    }
    private double y;
    private double z;
    private int sizeMult;



    public Object(double[] vertices, double x, double y, double z, int sizeMult){
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
        //printArray(xVerts);

        vertMatrix = new double[vertices.length / 3][4];
        for (int i = 0; i < vertices.length / 3 ; i++) {
            vertMatrix[i][0] = xVerts[i];
            vertMatrix[i][1] = yVerts[i];
            vertMatrix[i][2] = zVerts[i];
            vertMatrix[i][3] = 1;

        }
        transformationMatrix[0][0] = 100;
        transformationMatrix[1][1] = 100;
        transformationMatrix[2][2] = 100;

        vertMatrix = matrixMath.multiply(vertMatrix, matrixMath.matrix);

        vertMatrix = matrixMath.multiply(vertMatrix, transformationMatrix);
        vertMatrix = matrixMath.multiply(vertMatrix, translationMatrix);


        for (int i = 0; i < vertMatrix.length; i++) {
            xVerts[i] = vertMatrix[i][0];
            yVerts[i] = vertMatrix[i][1];

        }


    }

    public double transformationMatrix[][] = {
            {50, 0, 0, 1},
            {0, 50, 0, 1},
            {0, 0, 50, 1},
            {0, 0, 0, 1}
    };

    public double translationMatrix[][] = {
            {1, 0, 0, x},
            {0, 1, 0, y},
            {0, 0, 1, z},
            {0, 0, 0, 1}
    };

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



    private  double offset = 1;
    public void draw(GraphicsContext gc){


        vertMatrix = matrixMath.multiply(vertMatrix, matrixMath.rotYMatrix);
        vertMatrix = matrixMath.multiply(vertMatrix, matrixMath.rotXMatrix);
        vertMatrix = matrixMath.multiply(vertMatrix, matrixMath.rotZMatrix);


        //y = Math.cos(offset) * 100 + 250;









        if(renderer == 3 || renderer == 4 || renderer == 5) {
            gc.setFill(Color.SANDYBROWN);
            for (int i = 0; i < vertMatrix.length / 3; i++) {
                double tempvertx[] = {vertMatrix[i * 3][0] + x,
                        vertMatrix[i * 3 + 2][0] + x,
                        vertMatrix[i * 3 + 1][0] + x,
                        vertMatrix[i * 3 + 2][0] + x,
                        vertMatrix[i * 3 + 1][0] + x,
                        vertMatrix[i * 3][0] + x};
                double tempverty[] = {
                        vertMatrix[i * 3][1] + y,
                        vertMatrix[i * 3 + 2][1] + y,
                        vertMatrix[i * 3 + 1][1] + y,
                        vertMatrix[i * 3 + 2][1] + y,
                        vertMatrix[i * 3 + 1][1] + y,
                        vertMatrix[i * 3][1] + y

                };

                gc.fillPolygon(tempvertx, tempverty, 3);
            }
        }

        if(renderer == 1 || renderer == 4 || renderer == 5) {
            for (int i = 0; i < vertMatrix.length / 3; i++) {


                gc.strokeLine(vertMatrix[i * 3][0] + x, vertMatrix[i * 3][1] + y, vertMatrix[i * 3 + 2][0] + x, vertMatrix[i * 3 + 2][1] + y);
                gc.strokeLine(vertMatrix[i * 3 + 2][0] + x, vertMatrix[i * 3 + 2][1] + y, vertMatrix[i * 3 + 1][0] + x, vertMatrix[i * 3 + 1][1] + y);
                gc.strokeLine(vertMatrix[i * 3 + 1][0] + x, vertMatrix[i * 3 + 1][1] + y, vertMatrix[i * 3][0] + x, vertMatrix[i * 3][1] + y);

            }

        }

        if(renderer == 2 || renderer == 5) {
            gc.setFill(Color.GRAY);

            for (int i = 0; i < vertMatrix.length; i++) {
                gc.setStroke(Color.BLACK);
                gc.setFill(Color.GRAY);
                gc.fillOval(vertMatrix[i][0] + x - 2.5, vertMatrix[i][1] + y - 2.5, 5, 5);
            }
        }

    }
}
