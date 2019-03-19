package com.github.svettwer.citruscdc.contracts;

import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;

public class ContractBehaviorBuilder {

    private final TestRunner testRunner;
    private HttpClient httpClient;
    private HttpMethod httpMethod;
    private String endpoint;
    private Resource queryParameter;
    private Resource requestPayload;
    private Resource responsePayload;

    public ContractBehaviorBuilder(final TestRunner testRunner) {
        this.testRunner = testRunner;
    }

    public ContractBehaviorBuilder withClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public ContractBehaviorBuilder withHttpMethod(final HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public ContractBehaviorBuilder withEndpoint(final String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ContractBehaviorBuilder withQueryParameter(final Resource queryParameter) {
        this.queryParameter = queryParameter;
        return this;
    }

    public ContractBehaviorBuilder withRequestPayload(final Resource requestPayload) {
        this.requestPayload = requestPayload;
        return this;
    }

    public ContractBehaviorBuilder withResponsePayload(final Resource responsePayload) {
        this.responsePayload = responsePayload;
        return this;
    }

    public ContractBehavior build() {
        return new ContractBehavior(
                testRunner,
                httpClient,
                httpMethod,
                endpoint,
                queryParameter,
                requestPayload,
                responsePayload);
    }
}