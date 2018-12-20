package org.glpi.api.request.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CriteriaRequest {

    @SerializedName("criteria")
    private List<CriteriaItem> criteria;

    public void setCriteria(List<CriteriaItem> criteria) {
        this.criteria = criteria;
    }

    public List<CriteriaItem> getCriteria() {
        return criteria;
    }
}