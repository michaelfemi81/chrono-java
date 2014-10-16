package com.wanasit.chrono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.wanasit.chrono.parser.Parser;
import com.wanasit.chrono.refiner.Refiner;

public class Chrono {

    public static final Chrono casual = new Chrono(ChronoOption.casualOptions());
    public static final Chrono standard = new Chrono(ChronoOption.standardOptions());


    public static List<ParsedResult> Parse(String text) {
        return standard.parse(text);
    }

    public static List<ParsedResult> Parse(String text, Date refDate) {
        return standard.parse(text, refDate);
    }


    public static List<ParsedResult> Parse(String text, Date refDate, ChronoOption option) {
        return standard.parse(text, refDate, option);
    }

    protected ChronoOption options = null;
    protected List<Parser> parsers = null;
    protected List<Refiner> refiners = null;

    public Chrono(ChronoOption options) {
        this.options = options;
        this.parsers = new ArrayList<Parser>(options.parsers);
        this.refiners = new ArrayList<Refiner>(options.refiners);
    }

    public List<ParsedResult> parse(String text) {
        return parse(text, new Date());
    }

    public List<ParsedResult> parse(String text, Date refDate) {
        return parse(text, refDate, this.options);
    }

    public List<ParsedResult> parse(String text, Date refDate, ChronoOption options) {

        List<ParsedResult> allResults = new LinkedList<ParsedResult>();

        for (Parser parser : this.parsers) {

            List<ParsedResult> results = parser.execute(text, refDate, options);
            allResults.addAll(results);
        }

        Collections.sort(allResults);
        allResults = refineWithAllRefiners(allResults, text, options);

        return allResults;
    }

    protected List<ParsedResult> refineWithAllRefiners(List<ParsedResult> results, String text,
                                                       ChronoOption options) {

        for (Refiner refiner : this.refiners) {

            results = refiner.refine(results, text, options);
        }

        return results;
    }

}