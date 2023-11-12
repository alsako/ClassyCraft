package raf.dsw.classycraft.app.messagegen;

import raf.dsw.classycraft.app.observer.IPublisher;

public interface MessageGenerator extends IPublisher {
    Message formulateMessage(Event event);
    void generateMessage(Event event);
}
