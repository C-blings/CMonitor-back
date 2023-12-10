package models;

enum LogStatus {
    DEBUG,
    WARNING,
    ERROR
}

public class Log {

    public String projectName;
    public LogStatus logStatus;
    public String text;
}
