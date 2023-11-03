package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.logger.Logger;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.logger.LoggerType;
import raf.dsw.classycraft.app.messagegen.MessageGenerator;
import raf.dsw.classycraft.app.messagegen.MessageGeneratorImpl;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;
    private MessageGenerator messageGenerator;
    private Logger consoleLogger;
    private Logger fileLogger;

    public ApplicationFramework() {
    }

    public void initialize(){
        MainFrame.getInstance().setVisible(true);
        classyRepository = new ClassyRepositoryImpl();
        messageGenerator = new MessageGeneratorImpl();
        messageGenerator.addSubscriber(MainFrame.getInstance());
        LoggerFactory loggerFactory = new LoggerFactory();
        consoleLogger = loggerFactory.createLogger(LoggerType.CONSOLE);
        fileLogger = loggerFactory.createLogger(LoggerType.FILE);
        messageGenerator.addSubscriber(fileLogger);
        messageGenerator.addSubscriber(consoleLogger);
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public static ApplicationFramework getInstance(){
        if (instance==null)
            instance = new ApplicationFramework();
        return instance;
    }

}
