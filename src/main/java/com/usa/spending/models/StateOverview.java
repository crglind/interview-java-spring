package com.usa.spending.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StateOverview {
    private Object name;
    private Object code;
    private Object fips;
    private Object type;
    private Object population;
    private Object pop_year;
    private Object pop_source;
    private Object median_household_income;
    private Object mhi_year;
    private Object mhi_source;
    private Object total_prime_amount;
    private Object total_prime_awards;
    private Object total_face_value_loan_amount;
    private Object total_face_value_loan_prime_awards;
    private Object award_amount_per_capita;
}
