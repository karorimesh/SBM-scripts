package sbmscripts.mvc;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class TestController {
    @GetMapping(value = "/results/{date}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String viewResults(@PathVariable String date){
        String resultsContent = null;
        File file =  new File(System.getProperty("user.dir")
                + "/sbm-scripts/src/main/resources/templates/results 24-05-2022.html");
        try {
            Document htmlDoc = Jsoup.parse(file);
            resultsContent = htmlDoc.outerHtml();
        } catch (IOException e) {
            resultsContent = e.getMessage();
        }

        return resultsContent;
    }
}
