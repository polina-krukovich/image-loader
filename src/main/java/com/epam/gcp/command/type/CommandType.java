package com.epam.gcp.command.type;

public enum  CommandType {
    CREATE("/create"),
    LIST("/list"),
    DELETE("/delete");

    String action;

    CommandType(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
