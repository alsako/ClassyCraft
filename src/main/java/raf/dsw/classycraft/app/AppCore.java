package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.messagegen.Event;



public class AppCore {

    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.initialize();
        //TEST
        ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.PROBA);

    }

}
