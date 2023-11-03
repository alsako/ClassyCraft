package raf.dsw.classycraft.app.logger;

public class LoggerFactory {

    public LoggerFactory() {
    }

    public Logger createLogger(LoggerType type){
        switch (type){
            case FILE:
                return new FileLogger();
            case CONSOLE:
                return new ConsoleLogger();
            default:
                return null;
        }
    }

}
