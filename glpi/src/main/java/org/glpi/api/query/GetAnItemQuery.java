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

import java.util.HashMap;
import java.util.Map;

public class GetAnItemQuery {

    private Boolean expandDropdowns;
    private Boolean getHateoas;
    private Boolean getSha1;
    private Boolean withDevices;
    private Boolean withDisks;
    private Boolean withSoftwares;
    private Boolean withConnections;
    private Boolean withNetworkports;
    private Boolean withInfocoms;
    private Boolean withContracts;
    private Boolean withDocuments;
    private Boolean withTickets;
    private Boolean withProblems;
    private Boolean withChanges;
    private Boolean withNotes;
    private Boolean withLogs;

    /**
     * Get a map with all the parameters available to work with the get an item endpoint
     * @return Map<String, String> with the parameters selected
     */
    public Map<String, String> getQuery() {

        Map<String, String> map = new HashMap<>();

        if (expandDropdowns != null) {
            map.put("expand_dropdowns", expandDropdowns.toString());
        }

        if (getHateoas != null) {
            map.put("get_hateoas", getHateoas.toString());
        }

        if (getSha1 != null) {
            map.put("get_sha1", getSha1.toString());
        }

        if (withDevices != null) {
            map.put("with_devices", withDevices.toString());
        }

        if (withDisks != null) {
            map.put("with_disks", withDisks.toString());
        }

        if (withSoftwares != null) {
            map.put("with_softwares", withSoftwares.toString());
        }

        if (withConnections != null) {
            map.put("with_connections", withConnections.toString());
        }

        if (withNetworkports != null) {
            map.put("with_networkports", withNetworkports.toString());
        }

        if (withInfocoms != null) {
            map.put("with_infocoms", withInfocoms.toString());
        }

        if (withContracts != null) {
            map.put("with_contracts", withContracts.toString());
        }

        if (withDocuments != null) {
            map.put("with_documents", withDocuments.toString());
        }

        if (withTickets != null) {
            map.put("with_tickets", withTickets.toString());
        }

        if (withProblems != null) {
            map.put("with_problems", withProblems.toString());
        }

        if (withChanges != null) {
            map.put("with_changes", withChanges.toString());
        }

        if (withNotes != null) {
            map.put("with_notes", withNotes.toString());
        }

        if (withLogs != null) {
            map.put("with_logs", withLogs.toString());
        }

        return map;
    }

    /**
     * (default: false): show dropdown name instead of id.
     *
     * @param expandDropdowns
     */
    public void setExpandDropdowns(Boolean expandDropdowns) {
        this.expandDropdowns = expandDropdowns;
    }

    /**
     * (default: true): Show relations of the item in a links attribute.
     *
     * @param getHateoas
     */
    public void setGetHateoas(Boolean getHateoas) {
        this.getHateoas = getHateoas;
    }

    /**
     * (default: false): Get a sha1 signature instead of the full answer.
     *
     * @param getSha1
     */
    public void setGetSha1(Boolean getSha1) {
        this.getSha1 = getSha1;
    }

    /**
     * Only for [Computer, NetworkEquipment, Peripheral, Phone, Printer], retrieve the associated components.
     *
     * @param withDevices
     */
    public void setWithDevices(Boolean withDevices) {
        this.withDevices = withDevices;
    }

    /**
     * Only for Computer, retrieve the associated file-systems.
     *
     * @param withDisks
     */
    public void setWithDisks(Boolean withDisks) {
        this.withDisks = withDisks;
    }

    /**
     * Only for Computer, retrieve the associated software's installations.
     *
     * @param withSoftwares
     */
    public void setWithSoftwares(Boolean withSoftwares) {
        this.withSoftwares = withSoftwares;
    }

    /**
     * Only for Computer, retrieve the associated direct connections
     *
     * @param withConnections
     */
    public void setWithConnections(Boolean withConnections) {
        this.withConnections = withConnections;
    }

    /**
     * Retrieve all network's connections and advanced network's informations.
     *
     * @param withNetworkports
     */
    public void setWithNetworkports(Boolean withNetworkports) {
        this.withNetworkports = withNetworkports;
    }

    /**
     * Retrieve financial and administrative informations.
     *
     * @param withInfocoms
     */
    public void setWithInfocoms(Boolean withInfocoms) {
        this.withInfocoms = withInfocoms;
    }

    /**
     * Retrieve associated contracts.
     *
     * @param withContracts
     */
    public void setWithContracts(Boolean withContracts) {
        this.withContracts = withContracts;
    }

    /**
     * Retrieve associated external documents.
     *
     * @param withDocuments
     */
    public void setWithDocuments(Boolean withDocuments) {
        this.withDocuments = withDocuments;
    }

    /**
     * Retrieve associated itil tickets.
     *
     * @param withTickets
     */
    public void setWithTickets(Boolean withTickets) {
        this.withTickets = withTickets;
    }

    /**
     * Retrieve associated itil problems.
     *
     * @param withProblems
     */
    public void setWithProblems(Boolean withProblems) {
        this.withProblems = withProblems;
    }

    /**
     * Retrieve associated itil changes.
     *
     * @param withChanges
     */
    public void setWithChanges(Boolean withChanges) {
        this.withChanges = withChanges;
    }

    /**
     * Retrieve Notes.
     *
     * @param withNotes
     */
    public void setWithNotes(Boolean withNotes) {
        this.withNotes = withNotes;
    }

    /**
     * Retrieve historical.
     *
     * @param withLogs
     */
    public void setWithLogs(Boolean withLogs) {
        this.withLogs = withLogs;
    }
}
