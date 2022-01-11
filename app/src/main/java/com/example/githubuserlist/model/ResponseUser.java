package com.example.githubuserlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUser {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incomplete_Results;

    @SerializedName("items")
    private List<UserModel> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncomplete_Results() {
        return incomplete_Results;
    }

    public void setIncomplete_Results(boolean incomplete_Results) {
        this.incomplete_Results = incomplete_Results;
    }

    public List<UserModel> getItems() {
        return items;
    }

    public void setItems(List<UserModel> items) {
        this.items = items;
    }
}
