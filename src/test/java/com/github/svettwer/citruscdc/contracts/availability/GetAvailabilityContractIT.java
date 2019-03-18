package com.github.svettwer.citruscdc.contracts.availability;


import com.consol.citrus.annotations.CitrusEndpoint;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.jupiter.CitrusExtension;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.github.svettwer.citruscdc.contracts.ContractBehaviorBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;

@ExtendWith(CitrusExtension.class)
class GetAvailabilityContractIT {

    @CitrusEndpoint
    private HttpClient httpClient;

    @Test
    @CitrusTest
    void searchHotelContract(@CitrusResource final TestRunner runner) {
        runner.variable("correlationId", "citrus:randomUUID()");
        runner.applyBehavior(
                new ContractBehaviorBuilder(runner)
                        .withClient(httpClient)
                        .withEndpoint("/availability")
                        .withHttpMethod(HttpMethod.GET)
                        .withQueryParameter(new ClassPathResource("contracts/availability/queryParameter.csv"))
                        .withResponsePayload(new ClassPathResource("contracts/availability/responsePayload.json"))
                        .build());
    }
}