package org.glpi.api.response;

import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("plugin_flyvemdm_guest_profiles_id")
    private String pluginFlyvemdmGuestProfilesId;

    public void setPluginFlyvemdmGuestProfilesId(String pluginFlyvemdmGuestProfilesId) {
        this.pluginFlyvemdmGuestProfilesId = pluginFlyvemdmGuestProfilesId;
    }

    public String getPluginFlyvemdmGuestProfilesId() {
        return pluginFlyvemdmGuestProfilesId;
    }
}