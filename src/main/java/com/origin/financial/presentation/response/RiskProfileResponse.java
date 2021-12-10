package com.origin.financial.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RiskProfileResponse {
    private String auto;
    private String disability;
    private String home;
    private String life;
}