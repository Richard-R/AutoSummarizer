
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
    //double[][] scoreGraph;

    public Operations() {
        this.numOfParagraphs = 0;
        this.numOfSentences = 0;
    }

    //tokenize string
    public static void tokenize(String a) {
        StringTokenizer st = new StringTokenizer(a, " ");
        while (st.hasMoreElements()) {
            System.out.println(st.nextElement());
        }
    }

    //split the file into paragraphs, creating Paragraph objects for each one
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
            //curr = curr.replaceAll("�", "\"");
            curr = curr.replaceAll("“", "\"");
            curr = curr.replaceAll("’", "\'");
            curr = curr.replaceAll("”", "\"");
            String[] shortenedSentence = Stopwords.buildSortedArray(curr);
            int wordCount = curr.split("\\s+").length;
            toReturn.add(new Sentence(wordCount, numOfSentences, curr, shortenedSentence));
            numOfSentences++;
        }
        return toReturn;
    }

    public double sentenceScore(Sentence st1, Sentence st2){
        double score = 0;
        Set<String> common = new HashSet<>(Arrays.asList(st1.sortedValues));
        common.retainAll(new HashSet<>(Arrays.asList(st2.sortedValues)));
        score = (double)(common.size()) / (double)(st1.sortedValues.length + st2.sortedValues.length) / 2.0;
        return score;
    }


    public double[][] generateArray(Paragraph toMatrix){
        int sentenceSize = toMatrix.storedSentences.size();
        double[][] matrix = new double[sentenceSize][sentenceSize];
        for (int i = 0; i < sentenceSize; i++){
            for (int j = i; j < sentenceSize; j++){
                if (i == j){
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = sentenceScore(toMatrix.storedSentences.get(i),toMatrix.storedSentences.get(j));
                }
            }
        }
        return matrix;
    }


    public Sentence pickSentence(double[][] scores, ArrayList<Sentence> storedSen){
        double[] totalScore = new double[scores.length];
        for (int i = 0; i < scores.length; i++){
            for (int j = 0; j < scores.length; j++){
                if (scores[i][j] == 0){
                    totalScore[i] += scores[j][i];
                } else {
                    totalScore[i] += scores[i][j];
                }
            }
        }
        int index = 0;
        double maxCount = 0;
        for (int i = 0; i < totalScore.length; i++){
            if (totalScore[i] > maxCount){
                index = i;
                maxCount = totalScore[i];
            }
        }

        return storedSen.get(index);
    }
}
