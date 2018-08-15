import java.util.ArrayList;

/**
 * Created by Richard on 8/15/2018.
 */
public class Paragraph {
    int paragraphNumber;
    ArrayList<Sentence> storedSentences;

    public Paragraph(int paragraphNumber, ArrayList<Sentence> container){
        this.paragraphNumber = paragraphNumber;
        this.storedSentences = container;
    }

}
