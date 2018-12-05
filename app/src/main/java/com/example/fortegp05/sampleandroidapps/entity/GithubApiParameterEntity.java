package com.example.fortegp05.sampleandroidapps.entity;

public class GithubApiParameterEntity {

    private String q = null;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getRepositoryParameter() {
        StringBuilder sb = new StringBuilder();
        String[][] params = {
                {"q", (this.q == null ? null : this.q.toString())}
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
