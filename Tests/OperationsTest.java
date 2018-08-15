import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Richard on 8/15/2018.
 */
public class OperationsTest {
    @Test
    public void splitIntoParagraphsTest() throws Exception {
        Operations operations = new Operations();
        ArrayList<Paragraph> test = new ArrayList<>();
        File file = new File("C:\\Users\\Richard\\Personal_Project\\words.txt");
        test = operations.splitIntoParagraphs(file);
        System.out.println(test.get(3).storedSentences.get(0).value);
        assertEquals(test.size(), 4);
    }

    @Test
    public void splitIntoSentencesTest() throws Exception {
        Operations operations = new Operations();
        String testString = "Brad Evans and Sean Wilson, focus on Paulo Freire as the founder of critical pedagogy, a protest to traditional education that focuses on being aware of oppression and constantly challenging people’s beliefs and practices. Henry A Giroux, taking Freire’s ideas even further, illuminated the war going on right now waged against universities and students, the \"destruction of critical awareness\" (Evans, 56) through oppression from people on the top of social and economic ladders. Critical pedagogy, independent of militarism and corporate " +
                "interests, is the only way to effectively participate in the transformation of the world. ";
        ArrayList<Sentence> test = operations.splitIntoSentences(testString);
        assertEquals(3, test.size());
        assertEquals("Critical pedagogy, independent of militarism and corporate interests, is the only way to effectively participate in the transformation of the world.", test.get(2).value);
        assertEquals("Henry A Giroux, taking Freire’s ideas even further, illuminated the war going on right now waged against universities and students, the \"destruction of critical awareness\" (Evans, 56) through oppression from people on the top of social and economic ladders.", test.get(1).value);
    }

    @Test
    public void splitIntoSentencesTestSortedValue() throws Exception {
        Operations operations = new Operations();
        String testString = "Brad Evans and Sean Wilson, focus on Paulo Freire as the founder of critical pedagogy, a protest to traditional education that focuses on being aware of oppression and constantly challenging people’s beliefs and practices. Henry A Giroux, taking Freire’s ideas even further, illuminated the war going on right now waged against universities and students, the \"destruction of critical awareness\" (Evans, 56) through oppression from people on the top of social and economic ladders. Critical pedagogy, independent of militarism and corporate " +
                "interests, is the only way to effectively participate in the transformation of the world. ";
        ArrayList<Sentence> test = operations.splitIntoSentences(testString);
        for (int i = 0; i < test.get(2).sortedValues.length; i++) {
            System.out.println(test.get(2).sortedValues[i]);
        }
    }

    @Test
    public void sentenceScoreTest() throws Exception {
        Operations operations = new Operations();
        String test1 = "Pizza man i like chicken too. Pizza taste great man. Chicken is delicious";
        ArrayList<Sentence> sentenceMaker = operations.splitIntoSentences(test1);
        assertEquals(sentenceMaker.get(0).value, "Pizza man i like chicken too.");
        for (int i = 0; i < sentenceMaker.get(0).sortedValues.length; i++) {
            System.out.println(sentenceMaker.get(0).sortedValues[i]);
        }
        double score = operations.sentenceScore(sentenceMaker.get(1), sentenceMaker.get(2));
        System.out.println(score);


    }
}