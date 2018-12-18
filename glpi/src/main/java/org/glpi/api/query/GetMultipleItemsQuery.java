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

import java.util.HashMap;
import java.util.Map;

public class GetMultipleItemsQuery {

    private String items;
    private String get_sha1;
    private String with_devices;
    private String with_disks;
    private String with_softwares;
    private String with_connections;
    private String with_networkports;
    private String with_infocoms;
    private String with_contracts;
    private String with_documents;
    private String with_tickets;
    private String with_problems;
    private String with_changes;
    private String with_notes;
    private String with_logs;
    private Boolean expandDropdowns;
    private Boolean getHateoas;
    private Context context;

    /**
     * Get all the parameters available to work with the all items endpoint
     * @param context
     */
    public GetMultipleItemsQuery(Context context) {
        this.context = context;
    }

    /**
     * Get a map with all the parameters available to work with the all items endpoint
     * @return Map<String, String> with the parameters selected
     */
    public Map<String, String> getQuery() {

        Map<String, String> map = new HashMap<>();

        if (items != null) {
            map.put("items", items);
        }

        if (get_sha1 != null) {
            map.put("get_sha1", get_sha1);
        }

        if (with_devices != null) {
            map.put("with_devices", with_devices);
        }

        if (with_disks != null) {
            map.put("with_disks", with_disks);
        }

        if (with_softwares != null) {
            map.put("with_softwares", with_softwares);
        }

        if (with_connections != null) {
            map.put("with_connections", with_connections);
        }

        if (with_networkports != null) {
            map.put("with_networkports", with_networkports);
        }

        if (with_infocoms != null) {
            map.put("with_infocoms", with_infocoms);
        }

        if (with_contracts != null) {
            map.put("with_contracts", with_contracts);
        }

        if (with_documents != null) {
            map.put("with_documents", with_documents);
        }

        if (with_tickets != null) {
            map.put("with_tickets", with_tickets);
        }

        if (with_problems != null) {
            map.put("with_problems", with_problems);
        }

        if (with_changes != null) {
            map.put("with_changes", with_changes);
        }

        if (with_notes != null) {
            map.put("with_notes", with_notes);
        }

        if (with_logs != null) {
            map.put("with_logs", with_logs);
        }

        if (expandDropdowns != null) {
            map.put("expand_dropdowns", expandDropdowns.toString());
        }

        if (getHateoas != null) {
            map.put("get_hateoas", getHateoas.toString());
        }

        return map;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getGet_sha1() {
        return get_sha1;
    }

    public void setGet_sha1(String get_sha1) {
        this.get_sha1 = get_sha1;
    }

    public String getWith_devices() {
        return with_devices;
    }

    public void setWith_devices(String with_devices) {
        this.with_devices = with_devices;
    }

    public String getWith_disks() {
        return with_disks;
    }

    public void setWith_disks(String with_disks) {
        this.with_disks = with_disks;
    }

    public String getWith_softwares() {
        return with_softwares;
    }

    public void setWith_softwares(String with_softwares) {
        this.with_softwares = with_softwares;
    }

    public String getWith_connections() {
        return with_connections;
    }

    public void setWith_connections(String with_connections) {
        this.with_connections = with_connections;
    }

    public String getWith_networkports() {
        return with_networkports;
    }

    public void setWith_networkports(String with_networkports) {
        this.with_networkports = with_networkports;
    }

    public String getWith_infocoms() {
        return with_infocoms;
    }

    public void setWith_infocoms(String with_infocoms) {
        this.with_infocoms = with_infocoms;
    }

    public String getWith_contracts() {
        return with_contracts;
    }

    public void setWith_contracts(String with_contracts) {
        this.with_contracts = with_contracts;
    }

    public String getWith_documents() {
        return with_documents;
    }

    public void setWith_documents(String with_documents) {
        this.with_documents = with_documents;
    }

    public String getWith_tickets() {
        return with_tickets;
    }

    public void setWith_tickets(String with_tickets) {
        this.with_tickets = with_tickets;
    }

    public String getWith_problems() {
        return with_problems;
    }

    public void setWith_problems(String with_problems) {
        this.with_problems = with_problems;
    }

    public String getWith_changes() {
        return with_changes;
    }

    public void setWith_changes(String with_changes) {
        this.with_changes = with_changes;
    }

    public String getWith_notes() {
        return with_notes;
    }

    public void setWith_notes(String with_notes) {
        this.with_notes = with_notes;
    }

    public String getWith_logs() {
        return with_logs;
    }

    public void setWith_logs(String with_logs) {
        this.with_logs = with_logs;
    }

    public Boolean getExpandDropdowns() {
        return expandDropdowns;
    }

    public void setExpandDropdowns(Boolean expandDropdowns) {
        this.expandDropdowns = expandDropdowns;
    }

    public Boolean getGetHateoas() {
        return getHateoas;
    }

    public void setGetHateoas(Boolean getHateoas) {
        this.getHateoas = getHateoas;
    }
}
