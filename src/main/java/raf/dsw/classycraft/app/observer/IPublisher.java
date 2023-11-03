package raf.dsw.classycraft.app.observer;


import raf.dsw.classycraft.app.messagegen.Event;

import java.time.LocalDateTime;

public interface IPublisher {

    void addSubscriber(ISubscriber sub);
    void removeSubscriber(ISubscriber sub);
    void notifySubscribers(Object notification);
}
