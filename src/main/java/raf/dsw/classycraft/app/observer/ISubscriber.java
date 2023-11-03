package raf.dsw.classycraft.app.observer;

import raf.dsw.classycraft.app.messagegen.Message;

public interface ISubscriber {
    void update(Object notification);
}
