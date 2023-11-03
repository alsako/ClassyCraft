package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messagegen.Message;

public class ConsoleLogger implements Logger{


    public ConsoleLogger() {
    }

    @Override
    public void update(Object message) {
        System.out.println(((Message)message).toString());
    }
}
