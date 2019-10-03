package com.example.demo.query;

public class FetchCardSummariesQuery {

    private final Integer size;
    private final Integer offset;

    public FetchCardSummariesQuery(Integer size, Integer offset) {
        this.size = size;
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getOffset() {
        return offset;
    }
}
