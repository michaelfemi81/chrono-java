package com.wanasit.chrono;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.wanasit.chrono.parser.en.*;
import com.wanasit.chrono.refiner.MissingTimeRefiner;

public class ChronoOptions {
    
    
    
    public static final ChronoOptions sharedOptions = new ChronoOptions();
    
    
    
    
    public List< Class<? extends Parser > > parserClasses = null;
    public List< Class<? extends Refiner> > refinerClasses = null;
    
    public Integer timezoneOffset = null;
    public Map<String, Integer> timezoneMap = null;
    
    
    public ChronoOptions() {
        
        this.parserClasses = new LinkedList<Class<? extends Parser>>();
        this.parserClasses.add(ENInternationalStandardParser.class);
        this.parserClasses.add(ENMonthNameLittleEndianParser.class);
        this.parserClasses.add(ENMonthNameMiddleEndianParser.class);
        this.parserClasses.add(ENSlashBesedParser.class);
        
        
        this.refinerClasses = new LinkedList<Class<? extends Refiner>>();
        this.refinerClasses.add(MissingTimeRefiner.class);
        
        this.timezoneOffset = Calendar.getInstance().getTimeZone().getOffset(0);
        
        this.timezoneMap = new HashMap<String, Integer>();
    }
    
    
    
}