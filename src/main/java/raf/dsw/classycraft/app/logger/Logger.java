package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messagegen.Message;
import raf.dsw.classycraft.app.observer.ISubscriber;

public interface Logger extends ISubscriber {
    @Override
    default void update(Object message) {
    }
}
