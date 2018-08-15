import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Richard on 8/15/2018.
 */
public class StopwordsTest {
    @Test
    public void stopwordsTest() throws Exception{
        Stopwords stopwords = new Stopwords();
        String[] test = stopwords.buildSortedArray("Hello john my name is john");
        String[] correct = {"hello", "john","john", "name"};
        Assert.assertArrayEquals(test, correct);

    }

}