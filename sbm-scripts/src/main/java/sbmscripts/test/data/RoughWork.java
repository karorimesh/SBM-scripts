package sbmscripts.test.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sbmscripts.TestBase;

import java.io.File;
import java.io.IOException;

public class RoughWork {

    public static void main(String[] args) {
        String resultsContent = null;
        File file =  new File(System.getProperty("user.dir")
                + "/sbm-scripts/src/main/resources/templates/results 24-05-2022.html");
        try {
            Document htmlDoc = Jsoup.parse(file);
            resultsContent = htmlDoc.outerHtml();
        } catch (IOException e) {
            resultsContent = e.getMessage();
        }
        System.out.println(resultsContent);
    }
}
