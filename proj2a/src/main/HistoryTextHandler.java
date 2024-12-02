package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private final NGramMap ngm;
    // constructor
    public HistoryTextHandler(NGramMap map) {
        this.ngm = map;
    }


    @Override
    public String handle(NgordnetQuery q) {
        // get the basic information
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        for (String word : words) {
            response += word + ": " + this.ngm.weightHistory(word, startYear, endYear).toString() + "\n";
        }

        return response;
    }
}
