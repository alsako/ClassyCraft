package raf.dsw.classycraft.app.messagegen;

import raf.dsw.classycraft.app.observer.IPublisher;

import java.time.LocalDateTime;

public interface MessageGenerator extends IPublisher {
    Message generateMessage(Event event);
}
