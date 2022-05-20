package sbmscripts.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtil {
    public static boolean stringMatcher(String text){
        String regex = "\bSuccessfully\b";
        Pattern successPattern = Pattern.compile(regex);
        return successPattern.matcher(text).find();
    }
}
