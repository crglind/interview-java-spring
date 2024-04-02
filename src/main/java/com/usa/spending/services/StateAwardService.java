package com.usa.spending.services;

import com.usa.spending.models.StateListing;
import com.usa.spending.models.StateOverview;

import org.springdoc.core.converters.models.Sort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class StateAwardService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String STATE_SEARCH = "https://api.usaspending.gov/api/v2/recipient/state/";
    private final String STATE_DETAIL = "https://api.usaspending.gov/api/v2/recipient/state/{0}/";

    public StateAwardService() {
    }

    /**
     * Get all basic spending information for each state.
     * @return Array of StateListing objects
     */
    public StateListing[] getAll() {
        log.info("Fetching all state spending overview from: URL={}", STATE_SEARCH);
        return this.restTemplate.getForObject(STATE_SEARCH, StateListing[].class);
    }

    // TODO #1 -- Get all basic spending information for each state, ordered by "amount".
    // TODO #2 -- Implement generic sorting by client-requested fields using "Sort" object
    public StateListing[] getAllSorted(Sort sort) {
        // hint use sort.getSort(), where .get(0) is the field and .get(1) is the direction (asc/desc)
        List<StateListing> listings = Arrays.asList(this.getAll());
        String fieldToSortBy = sort.getSort().get(0);
        log.info("FIELD = " + fieldToSortBy);
        String direction = sort.getSort().get(1);
        log.info("DIRECTION = " + direction);
        Collections.sort(listings, new StateListingComparator(fieldToSortBy));
        return (StateListing[]) listings.toArray();
    }

    // TODO #3 -- Get detailed spending information for state by FIPS code
    public StateOverview getForStateByFips(String fips) {
        String url = MessageFormat.format(this.STATE_DETAIL, fips);
        log.info("Fetching state spending detail from: URL={}, fips={}", url, fips);
        return this.restTemplate.getForObject(url, StateOverview.class);
    }

    // TODO #4 -- Get all awards for a specific state by abbreviation
    //  Unfortunately, the public API does not have an endpoint for this directly
    public StateOverview getForStateCode(String code) {
        log.info("CODE = " + code);
        List<StateListing> allListings = Arrays.asList(getAll());
        Optional<StateListing> resultsFromListings = allListings.stream().filter(state -> state.getCode().equals(code)).findFirst();
        if (resultsFromListings.isPresent()) return getForStateByFips(resultsFromListings.get().getFips());
        return null;
    }
}

class StateListingComparator implements Comparator<StateListing> {

    private final String type;

    public StateListingComparator(String type) {
        this.type = type;
    }

    @Override
    public int compare(StateListing arg0, StateListing arg1) {
        if (type.equals("fips")) {
            return arg0.getFips().compareTo(arg1.getFips());
        } else if (type.equals("code")) {
            return arg0.getCode().compareTo(arg1.getCode());
        } else if (type.equals("name")) {
            return arg0.getName().compareTo(arg1.getName());
        } else if (type.equals("type")) {
            return arg0.getType().compareTo(arg1.getType());
        } else if (type.equals("amount")) {
            return arg0.getAmount().compareTo(arg1.getAmount());
        } else if (type.equals("count")) {
            return arg0.getCount().compareTo(arg1.getCount());
        } else {
            return 0;
        }
            
    }
    
}