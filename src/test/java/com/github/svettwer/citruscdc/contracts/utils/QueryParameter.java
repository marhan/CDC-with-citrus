package com.github.svettwer.citruscdc.contracts.utils;

public class QueryParameter {

    private String key;
    private String value;

    public QueryParameter(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
