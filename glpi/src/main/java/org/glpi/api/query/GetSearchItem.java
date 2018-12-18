/* ---------------------------------------------------------------------
*
*  LICENSE
*
*  This file is part of the GLPI API Client Library for Java,
*  a subproject of GLPI. GLPI is a free IT Asset Management.
*
*  GLPI is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License
*  as published by the Free Software Foundation; either version 3
*  of the License, or (at your option) any later version.
*
*  GLPI is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  --------------------------------------------------------------------
*  @author    Ivan Del Pino - <idelpino@teclib.com>
*  @copyright (C) 2017 Teclib' and contributors.
*  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
*  @link      https://github.com/glpi-project/java-library-glpi
*  @link      http://www.glpi-project.org/
*  --------------------------------------------------------------------
*/

package org.glpi.api.query;

import org.glpi.api.request.search.CriteriaRequest;
import org.glpi.api.request.search.MetaCriteriaRequest;

import java.util.HashMap;
import java.util.Map;

public class GetSearchItem {

    private String link;
    private String field;
    private String meta;
    private String itemtype;
    private String searchtype;
    private String value;
    private CriteriaRequest criteria;
    private MetaCriteriaRequest metacriteria;
    private String sort;
    private String order;
    private String range;
    private String forcedisplay;
    private String rawdata;
    private String withindexes;
    private String uid_cols;
    private String giveItems;

    /**
     * Get a map with all the parameters available to work with the get search item
     * @return Map<String, String> with the parameters selected
     */
    public Map<String, String> getQuery() {

        Map<String, String> map = new HashMap<>();

        if (link != null) {
            map.put("link", link.toString());
        }

        if (field != null) {
            map.put("field", field.toString());
        }

        if (meta != null) {
            map.put("meta", meta.toString());
        }

        if (itemtype != null) {
            map.put("itemtype", itemtype.toString());
        }

        if (searchtype != null) {
            map.put("searchtype", searchtype.toString());
        }

        if (value != null) {
            map.put("value", value.toString());
        }

        if (criteria != null) {
            map.put("criteria", criteria.toString());
        }

        if (metacriteria != null) {
            map.put("metacriteria", metacriteria.toString());
        }

        if (sort != null) {
            map.put("with_infocoms", sort.toString());
        }

        if (order != null) {
            map.put("order", order.toString());
        }

        if (range != null) {
            map.put("range", range.toString());
        }

        if (forcedisplay != null) {
            map.put("forcedisplay", forcedisplay.toString());
        }

        if (rawdata != null) {
            map.put("rawdata", rawdata.toString());
        }

        if (withindexes != null) {
            map.put("withindexes", withindexes.toString());
        }

        if (uid_cols != null) {
            map.put("uid_cols", uid_cols.toString());
        }

        if (giveItems != null) {
            map.put("giveItems", giveItems.toString());
        }

        return map;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CriteriaRequest getCriteria() {
        return criteria;
    }

    public void setCriteria(CriteriaRequest criteria) {
        this.criteria = criteria;
    }

    public MetaCriteriaRequest getMetacriteria() {
        return metacriteria;
    }

    public void setMetacriteria(MetaCriteriaRequest metacriteria) {
        this.metacriteria = metacriteria;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getForcedisplay() {
        return forcedisplay;
    }

    public void setForcedisplay(String forcedisplay) {
        this.forcedisplay = forcedisplay;
    }

    public String getRawdata() {
        return rawdata;
    }

    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
    }

    public String getWithindexes() {
        return withindexes;
    }

    public void setWithindexes(String withindexes) {
        this.withindexes = withindexes;
    }

    public String getUid_cols() {
        return uid_cols;
    }

    public void setUid_cols(String uid_cols) {
        this.uid_cols = uid_cols;
    }

    public String getGiveItems() {
        return giveItems;
    }

    public void setGiveItems(String giveItems) {
        this.giveItems = giveItems;
    }
}
