package org.glpi.api.response;

import com.google.gson.annotations.SerializedName;

public class FullSessionModel {

    @SerializedName("session")
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}