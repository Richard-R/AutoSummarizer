import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static org.junit.Assert.*;

/**
 * Created by Richard on 8/15/2018.
 */
public class AutocompleteTest {
    public static void main(String[] args) {
        String s="Paulo Freire         writes about dehumanization, a \"distortion of the vocation of becoming more fully human\" (Freire, no page numbers) as not a set human historical reality but a transformable state";;
        System.out.println(s);
    }
    public static void tokenize(String a) {
        StringTokenizer st = new StringTokenizer(a, " ");
        while (st.hasMoreElements()) {
            System.out.println(st.nextElement());
        }
    }
    public int commonTwo(String[] a, String[] b) {
        Set<String> common = new HashSet<>(Arrays.asList(a));
        common.retainAll(new HashSet<>(Arrays.asList(b)));
        return common.size();
    }


}