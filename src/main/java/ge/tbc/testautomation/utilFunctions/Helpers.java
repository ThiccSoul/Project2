package ge.tbc.testautomation.utilFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Helpers {


    public static boolean isUniqueTextList(List<String> list) {
        Set<String> trimmedSet = list.stream()
                .map(String::trim)
                .collect(Collectors.toSet());

        return trimmedSet.size() == list.size();
    }

    public static List<String> extractOffersInRange(List<String> input, int lowerBound, int upperBound) {
        List<String> validPrices = new ArrayList<>();

        for (String element : input) {
            element = element.trim();

            Pattern pattern = Pattern.compile("(\\d+)(\\s\\d+)?");
            Matcher matcher = pattern.matcher(element);

            if (matcher.matches()) {
                if (element.contains(" ")) {
                    String[] rangeParts = element.split("\\s");
                    int start = Integer.parseInt(rangeParts[0]);
                    int end = Integer.parseInt(rangeParts[1]);

                    if (start > end) {
                        int temp = start;
                        start = end;
                        end = temp;
                    }

                    if (rangeOverlaps(start, end, lowerBound, upperBound)) {
                        validPrices.add(element);
                    }
                } else {
                    int number = Integer.parseInt(element);
                    if (number >= lowerBound && number <= upperBound) {
                        validPrices.add(element);
                    }
                }
            }
        }

        return validPrices;
    }

    public static boolean rangeOverlaps(int start, int end, int lowerBound, int upperBound) {
        return (start <= upperBound && end >= lowerBound);
    }

    public static boolean isTextInEnglish(String text) {
        Pattern englishPattern = Pattern.compile("^[A-Za-z0-9\\\\s.,!?&'\"+/%₾@\\-]+$");
        return englishPattern.matcher(text.replaceAll(" ", "")).find();
    }

    public static boolean isTextInGeorgian(String text) {
        Pattern georgianPattern = Pattern.compile("[Ⴀ-ჿ]+");
        return georgianPattern.matcher(text.replaceAll(" ", "")).find();
    }


}
