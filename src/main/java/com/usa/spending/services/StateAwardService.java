package com.usa.spending.services;

import com.usa.spending.models.StateListing;
import com.usa.spending.models.StateOverview;

import org.springdoc.core.converters.models.Sort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

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
    public List<StateListing> getAllSorted(Sort sort) {
        // hint use sort.getSort(), where .get(0) is the field and .get(1) is the direction (asc/desc)
        StateListing[] listings = this.getAll();
        return null;
    }

    // TODO #3 -- Get detailed spending information for state by FIPS code
    public StateOverview getForStateByFips(String fips) {
        String url = MessageFormat.format(this.STATE_DETAIL, fips);
        log.info("Fetching state spending detail from: URL={}, fips={}", url, fips);
        return null;
    }

    // TODO #4 -- Get all awards for a specific state by abbreviation
    //  Unfortunately, the public API does not have an endpoint for this directly
    public StateOverview getForStateCode(String code) {
        return null;
    }
}
