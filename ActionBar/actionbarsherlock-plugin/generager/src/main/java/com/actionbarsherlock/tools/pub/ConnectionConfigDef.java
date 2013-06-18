package com.actionbarsherlock.tools.pub;

import java.util.HashMap;
import java.util.Map;

public class ConnectionConfigDef {
    private String defaultName;
    private Map connections = new HashMap();

    public Map getConnections() {
        return connections;
    }

    public void setConnections(Map connections) {
        this.connections = connections;
    }

    public void addConnection(ConnectionDef connDef) {
        this.connections.put(connDef.getName(), connDef);
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
