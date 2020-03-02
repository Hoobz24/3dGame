package sample;

public class matrixMath {
    private static double a = 1;
    private static double fov = 70;
    private static double Zfar = 1000;
    private static double Znear = .1;
    private static double zp = Zfar + Znear;
    private static double zm = Zfar + Znear;
    private static double angle = .01;

    public static double matrix[][] = {
            {(1/Math.tan(fov/2))/a, 0, 0, 0},
            {0, 1/Math.tan(fov/2), 0, 0},
            {0, 0, -zp/zm, -(2*Zfar*Znear)/zm},
            {0, 0, -1, 0}
    };

    public static double rotXMatrix[][] = {
            {1, 0, 0, 0},
            {0, Math.cos(angle), -Math.sin(angle), 0},
            {0, Math.sin(angle), Math.cos(angle), 0},
            {0, 0, 0, 0}
    };

    public static double rotYMatrix[][] = {
            {Math.cos(angle), 0, Math.sin(angle), 0},
            {0, 1, 0, 0},
            {-Math.sin(angle), 0, Math.cos(angle), 0},
            {0, 0, 0, 1}
    };

    public static double rotZMatrix[][] = {
            {Math.cos(angle), -Math.sin(angle), 0, 0},
            {Math.sin(angle), Math.cos(angle), 0, 0},
            {0, 0 ,1, 0},
            {0, 0, 0, 0}

    };

    public static double rotMatrix[][] = {
            {1/Math.sqrt(2), 1/Math.sqrt(6), 1/Math.sqrt(3), 0},
            {-Math.sqrt(3), 1/Math.sqrt(3), 0, 0},
            {-1/Math.sqrt(2), 1/Math.sqrt(6), 1/Math.sqrt(3), 0},
            {0, 0, 0, 0}

    };

    public static double[][] multiply(double[][] a, double[][] b) {
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
}
