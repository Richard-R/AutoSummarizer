
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.*;


/**
 * Created by Richard on 3/27/2018.
 */
public class Operations {
    int numOfSentences;
    int numOfParagraphs;
    double[][] scoreGraph;

    public Operations() {
        this.numOfParagraphs = 0;
        this.numOfSentences = 0;
    }

    public static void tokenize(String a) {
        StringTokenizer st = new StringTokenizer(a, " ");
        while (st.hasMoreElements()) {
            System.out.println(st.nextElement());
        }
    }

    public ArrayList<Paragraph> splitIntoParagraphs(File file) throws Exception {

        ArrayList<Paragraph> collection = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            if (!st.isEmpty()) {
                collection.add(new Paragraph(numOfParagraphs, splitIntoSentences(st)));
                //use split into sentence
                //create paragraph with a sentence as a constructor

                numOfParagraphs += 1;
            }
        }
        return collection;
    }

    public ArrayList<Sentence> splitIntoSentences(String toSplit){
        ArrayList<Sentence> toReturn = new ArrayList<>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        String source = toSplit;
        iterator.setText(source);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            String curr = source.substring(start,end).trim();
            String[] shortenedSentence = Stopwords.buildSortedArray(curr);
            int wordCount = curr.split("\\s+").length;
            toReturn.add(new Sentence(wordCount, numOfSentences, curr, shortenedSentence));
        }
        return toReturn;
    }

    public double sentenceScore(Sentence st1, Sentence st2){
        double score = 0;
        Set<String> common = new HashSet<>(Arrays.asList(st1.sortedValues));
        common.retainAll(new HashSet<>(Arrays.asList(st2.sortedValues)));
        score = common.size() / st1.sortedValues.length;
        return score;
    }


    public double[][] generateArray(){
        double[][] pizza = new double[1][1];
        return pizza;

    }
}
