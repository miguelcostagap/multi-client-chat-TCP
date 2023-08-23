package org.thestudio.server.commands;

public enum CommandsList {
    QUIT("/quit", "Exits the chat!\n") ,
    HELP("/help", "Lists all the available commands\n"),
    WHISPER("/whisper", "Lists all connected persons\n"),
    RENAME("/rename", "Change your displayed name\n"),
    LIST("/list","\"Send a direct message to another person\\n\"");

    private String command;
    private String description;

    CommandsList(String command, String description){
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
