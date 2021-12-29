package com.origin.financial.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiskProfileResponse {
    private String auto;
    private String disability;
    private String home;
    private String life;
    private String umbrella;
    private String rent;
}