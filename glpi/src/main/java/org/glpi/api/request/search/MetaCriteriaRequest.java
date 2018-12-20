package org.glpi.api.request.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MetaCriteriaRequest {

    @SerializedName("metacriteria")
    private List<MetacriteriaItem> metacriteria;

    public void setMetacriteria(List<MetacriteriaItem> metacriteria) {
        this.metacriteria = metacriteria;
    }

    public List<MetacriteriaItem> getMetacriteria() {
        return metacriteria;
    }
}