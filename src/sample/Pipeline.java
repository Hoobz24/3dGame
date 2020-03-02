package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Pipeline {

    public static double[] unwrapContent(String filename) throws Exception{
        Scanner sf = new Scanner(new File(filename));

        ArrayList<String> content = new ArrayList<String>();
        ArrayList<ArrayList<String>> vertices = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> faces = new ArrayList<ArrayList<String>>();
        while(sf.hasNextLine()){
            ArrayList<String> temp = new ArrayList<String>();
            String value = sf.nextLine();
            char cvals[] = value.toCharArray();
            String vals[] = new String[4];
            if(cvals[0] == 'v' && cvals[1] != 't' && cvals[1] != 'n'){
                vals = value.split(" ");
                for (int i = 1; i < vals.length; i++) {
                    temp.add(vals[i]);
                }
                vertices.add(temp);
            }

            ArrayList<String> tempFaces = new ArrayList<String>();

            String cvalsFacesS[] = new String[4];
            if(cvals[0] == 'f'){
                vals = value.split(" ");
                for (int i = 1; i < cvalsFacesS.length; i++) {
                    tempFaces.add(vals[i]);
                }
                faces.add(tempFaces);

            }

        }
        sf.close();


        double temp[] = new double[faces.size() * 3 * 3];

        int count = 0;
        ArrayList<Integer> places = new ArrayList<Integer>();
        for (int i = 0; i < faces.size(); i++) {
            for (int j = 0; j < faces.get(i).size(); j++) {
                Scanner sf2 = new Scanner(faces.get(i).get(j));
                String vs = faces.get(i).get(j);
                String vsa[] = vs.split("/");

                int place = Integer.valueOf(vsa[0]);
                places.add(place);



            }
        }


        for (int i = 0; i < places.size(); i++) {
            for (int j = 0; j < 3; j++) {
                temp[count] = Double.valueOf(vertices.get(places.get(i) - 1).get(j));
                //System.out.println(Double.valueOf(vertices.get(places.get(i) - 1).get(j)));
                count++;
            }
        }
        System.out.println(temp.length);
        System.out.println(places.size());

        return temp;
    }


}
