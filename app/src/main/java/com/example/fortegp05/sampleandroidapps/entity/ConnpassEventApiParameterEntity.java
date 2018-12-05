package com.example.fortegp05.sampleandroidapps.entity;

public class ConnpassEventApiParameterEntity {

    private Integer start;
    private Integer count;
    private Integer order;

    public Integer getStart() {
        return start;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getOrder() {
        return order;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getParameter() {
        StringBuilder sb = new StringBuilder();
        String[][] params = {
                {"start", (this.start == null ? null : this.start.toString())},
                {"count", (this.count == null ? null : this.count.toString())},
                {"order", (this.order == null ? null : this.order.toString())}
        };
        final int INDEX_NAME = 0;
        final int INDEX_VALUE = 1;

        for (int i = 0; i < params.length; i++) {
            if (params[i][INDEX_VALUE] == null) {
                continue;
            }
            sb.append("&").append(params[i][INDEX_NAME]).append("=").append(params[i][INDEX_VALUE]);
        }

        return (sb.length() > 0) ? "?" + sb.substring(1) : "";
    }
}
