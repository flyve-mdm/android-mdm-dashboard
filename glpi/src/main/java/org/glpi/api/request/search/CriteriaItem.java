package org.glpi.api.request.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CriteriaItem {

    @SerializedName("criteria")
    private List<CriteriaItem> criteria;

    @SerializedName("link")
    private String link;

    @SerializedName("field")
    private int field;

    @SerializedName("searchtype")
    private String searchtype;

    @SerializedName("value")
    private int value;

    @SerializedName("meta")
    private boolean meta;

    @SerializedName("itemtype")
    private String itemtype;

    public void setCriteria(List<CriteriaItem> criteria) {
        this.criteria = criteria;
    }

    public List<CriteriaItem> getCriteria() {
        return criteria;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean getMeta() {
        return meta;
    }

    public void setMeta(boolean meta) {
        this.meta = meta;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }
}