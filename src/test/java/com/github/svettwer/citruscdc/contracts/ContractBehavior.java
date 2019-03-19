package com.github.svettwer.citruscdc.contracts;

import com.consol.citrus.dsl.builder.HttpActionBuilder;
import com.consol.citrus.dsl.builder.HttpClientActionBuilder;
import com.consol.citrus.dsl.builder.HttpClientRequestActionBuilder;
import com.consol.citrus.dsl.runner.AbstractTestBehavior;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.github.svettwer.citruscdc.contracts.utils.CsvReader;
import com.github.svettwer.citruscdc.contracts.utils.QueryParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;

import java.util.List;

public class ContractBehavior extends AbstractTestBehavior {

    private final HttpClient httpClient;
    private final TestRunner testRunner;
    private final HttpMethod httpMethod;
    private final String endpoint;
    private final Resource queryParameter;
    private final Resource requestPayload;
    private final Resource responsePayload;
    private final CsvReader csvReader = new CsvReader();

    ContractBehavior(final TestRunner testRunner,
                     final HttpClient httpClient,
                     final HttpMethod httpMethod,
                     final String endpoint,
                     final Resource queryParameter,
                     final Resource requestPayload,
                     final Resource responsePayload) {
        this.testRunner = testRunner;
        this.httpClient = httpClient;
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.queryParameter = queryParameter;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
    }

    @Override
    public void apply() {
        sendRequest();
        receiveResponse();
    }

    private void receiveResponse() {
        testRunner.http(builder -> builder.
                client(httpClient)
                .receive()
                .response()
                .payload(responsePayload)
                .messageType(MessageType.JSON));
    }

    private void sendRequest() {
        final HttpClientActionBuilder.HttpClientSendActionBuilder client =
                new HttpActionBuilder()
                        .client(httpClient)
                        .send();

        final HttpClientRequestActionBuilder httpActionBuilder = configureMethod(client);
        setQueryParameter(httpActionBuilder);
        setRequestPayload(httpActionBuilder);

        testRunner.run(httpActionBuilder.build());
    }

    private HttpClientRequestActionBuilder configureMethod(
            final HttpClientActionBuilder.HttpClientSendActionBuilder client) {
        switch (httpMethod){
            case GET:
                return client.get(endpoint);
            case HEAD:
                return client.head(endpoint);
            case POST:
                return client.post(endpoint);
            case PUT:
                return client.put(endpoint);
            case PATCH:
                return client.patch(endpoint);
            case DELETE:
                return client.delete(endpoint);
            case OPTIONS:
                return client.options(endpoint);
            case TRACE:
                return client.trace(endpoint);
        }
        return client.get(endpoint);
    }

    private void setQueryParameter(final HttpClientRequestActionBuilder httpClientRequestActionBuilder) {
        if(queryParameter != null){
            final List<QueryParameter> queryParameters = csvReader.loadObjectList(QueryParameter.class, queryParameter);
            for (final QueryParameter parameter : queryParameters){
                httpClientRequestActionBuilder.queryParam(parameter.getKey(), parameter.getValue());
            }
        }
    }

    private void setRequestPayload(final HttpClientRequestActionBuilder httpClientRequestActionBuilder) {
        if(requestPayload != null){
            httpClientRequestActionBuilder
                    .payload(requestPayload)
                    .messageType(MessageType.JSON);
        }
    }
}
