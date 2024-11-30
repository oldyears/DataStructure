package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private HashMap<String, TimeSeries> wordHistory = new HashMap<>();
    private TimeSeries yearAmount = new TimeSeries();
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        In wordFile = new In(wordsFilename);
        In countsFile = new In(countsFilename);

        // wordFile data
        while (!wordFile.isEmpty()) {
            // read a line every time
            String data = wordFile.readLine();
            // split by '\t', other may be ','
            String[] wordData = data.split("\t");
            String word = wordData[0];
            int year = Integer.parseInt(wordData[1]);
            double time = Double.parseDouble(wordData[2]);
            // if the word's timeSeries has existed, then update it, or create one for it
            if (wordHistory.containsKey(word)) {
                wordHistory.get(word).put(year, time);
            } else {
                TimeSeries timeData = new TimeSeries();
                timeData.put(year, time);
                wordHistory.put(wordData[0], timeData);
            }
        }

        // countFile data
        while (!countsFile.isEmpty()) {
            String data = countsFile.readLine();
            String[] countData = data.split(",");
            int year = Integer.parseInt(countData[0]);
            double amount = Double.parseDouble(countData[1]);
            if (yearAmount.containsKey(year)) {
                yearAmount.put(year, yearAmount.get(year) + amount);
            } else {
                yearAmount.put(year, amount);
            }
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordHistory.containsKey(word)) {
            return new TimeSeries();
        }
        return new TimeSeries(wordHistory.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(yearAmount, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordHistory.containsKey(word)) {
            return new TimeSeries();
        }
        return new TimeSeries(wordHistory.get(word).dividedBy(yearAmount), startYear, endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return weightHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries summedWordHistory = new TimeSeries();
        for (String word : words) {
            if (wordHistory.containsKey(word)) {
                summedWordHistory = summedWordHistory.plus(wordHistory.get(word));
            }
        }
        return new TimeSeries(summedWordHistory.dividedBy(yearAmount), startYear, endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.

}
