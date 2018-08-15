import java.util.StringTokenizer;

/**
 * Created by Richard on 3/27/2018.
 */
public class Sentence {
    int numOfWords;
    double score;
    int sentenceNumber;
    //Actual sentence
    String value;
    //Sentence stripped of stopwords and alphabetized
    String[] sortedValues;

    public Sentence(int numOfWords, int sentenceNumber, String value, String[] sortedValues){
        this.numOfWords = numOfWords;
        this.score = 0.0;
        this.sentenceNumber = sentenceNumber;
        this.value = value;
        this.sortedValues = sortedValues;

    }

}