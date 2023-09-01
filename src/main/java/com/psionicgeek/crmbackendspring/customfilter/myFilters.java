package com.psionicgeek.crmbackendspring.customfilter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

public class myFilters {

    public static FilterProvider createCustomFilterProvider(String name,String propertyToExclude){

        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.serializeAllExcept(propertyToExclude);
        FilterProvider provider =new SimpleFilterProvider().addFilter(name,filter);
        return provider;
    }
}
