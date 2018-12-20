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
*  @author    Rafael Hernandez - <rhernandez@teclib.com>
*  @copyright (C) 2017 Teclib' and contributors.
*  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
*  @link      https://github.com/glpi-project/java-library-glpi
*  @link      http://www.glpi-project.org/
*  --------------------------------------------------------------------
*/

package org.glpi.api.query;

import android.content.Context;

import org.glpi.api.R;

import java.util.HashMap;
import java.util.Map;

public class GetSubItemQuery {

    private Boolean expandDropdowns;
    private Boolean getHateoas;
    private Boolean onlyId;
    private String range;
    private String sort;
    private String order;
    private Context context;

    /**
     * Get all the parameters available to work with the sub item endpoint
     * @param context
     */
    public GetSubItemQuery(Context context) {
        this.context = context;
    }

    /**
     * Get a map with all the parameters available to work with the all items endpoint
     * @return Map<String, String> with the parameters selected
     */
    public Map<String, String> getQuery() {

        Map<String, String> map = new HashMap<>();

        if(expandDropdowns!=null) {
            map.put("expand_dropdowns", expandDropdowns.toString());
        }

        if(getHateoas!=null) {
            map.put("get_hateoas", getHateoas.toString());
        }

        if(onlyId!=null) {
            map.put("only_id", onlyId.toString());
        }

        if(range!=null) {
            map.put("range", range);
        }

        if(sort!=null) {
            map.put("sort", sort);
        }

        if(order!=null) {
            map.put("order", order);
        }

        return map;
    }

    /**
     * (default: false): show dropdown name instead of id.
     * @param expandDropdowns boolean
     */
    public void setExpandDropdowns(Boolean expandDropdowns) {
        this.expandDropdowns = expandDropdowns;
    }

    /**
     * (default: true): Show relation of item in a links attribute.
     * @param getHateoas boolean
     */
    public void setGetHateoas(Boolean getHateoas) {
        this.getHateoas = getHateoas;
    }

    /**
     * (default: false): keep only id keys in returned data.
     * @param onlyId boolean
     */
    public void setOnlyId(Boolean onlyId) {
        this.onlyId = onlyId;
    }

    /**
     * (default: 0-50): a range with a couple of number for start and end of pagination
     * @param min
     * @param max
     */
    public void setRange(int min, int max) {
        if(max>=min) {
            throw new RuntimeException(context.getResources().getString(R.string.error_range));
        }

        this.range = min + "-" + max;
    }

    /**
     * (default 1): id of the searchoption to sort by.
     * @param sort int
     */
    public void setSort(int sort) {
        this.sort = String.valueOf(sort);
    }

    /**
     * (default ASC): ASC - Ascending sort / DESC Descending sort.
     * @param order Order type with ASC or DESC values
     */
    public void setOrder(Order order) {
        this.order = order.name();
    }

    /**
     * Enum definition with sort order possibilities
     */
    public enum Order {
        DESC,
        ASC
    }
}
