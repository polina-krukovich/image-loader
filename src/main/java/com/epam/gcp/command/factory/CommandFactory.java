package com.epam.gcp.command.factory;

import com.epam.gcp.command.Command;
import com.epam.gcp.command.impl.CreateCommand;
import com.epam.gcp.command.impl.DeleteCommand;
import com.epam.gcp.command.impl.ListCommand;
import com.epam.gcp.command.type.CommandType;

public class CommandFactory {

    public static Command createCommand(String action) {
        if (CommandType.CREATE.getAction().equals(action)) {
            return new CreateCommand();
        }
        if (CommandType.LIST.getAction().equals(action)) {
            return new ListCommand();
        }
        if (CommandType.DELETE.getAction().equals(action)) {
            return new DeleteCommand();
        }
        return null;
    }
}
