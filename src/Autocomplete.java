/**
 * Created by Richard on 8/15/2018.
 */

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

/**
 * Created by Richard on 3/26/2018.
 */
public class Autocomplete {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Richard\\Personal_Projects\\Autocomplete\\words.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            tokenize(st);
            System.out.println("1");

        }

    }

    public static void tokenize(String a) {
        StringTokenizer st = new StringTokenizer(a, ".");
        while (st.hasMoreElements()) {
            System.out.println(st.nextElement());
        }
    }
}