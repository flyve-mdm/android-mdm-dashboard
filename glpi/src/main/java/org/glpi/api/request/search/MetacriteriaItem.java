package org.glpi.api.request.search;

import com.google.gson.annotations.SerializedName;

public class MetacriteriaItem {

    @SerializedName("itemtype")
    private String itemtype;

    @SerializedName("field")
    private int field;

    @SerializedName("searchtype")
    private String searchtype;

    @SerializedName("link")
    private String link;

    @SerializedName("value")
    private String value;

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemtype() {
        return itemtype;
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

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}