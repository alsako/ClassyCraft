package raf.dsw.classycraft.app.core;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.tree.controller.TreeMouseListener;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.logger.Logger;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.logger.LoggerType;
import raf.dsw.classycraft.app.messagegen.MessageGenerator;
import raf.dsw.classycraft.app.messagegen.MessageGeneratorImpl;
import raf.dsw.classycraft.app.serializer.JacksonSerializer;
import raf.dsw.classycraft.app.serializer.Serializer;

import java.io.FileWriter;

@Getter
public class ApplicationFramework {


    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;
    private MessageGenerator messageGenerator;
    private Logger consoleLogger;
    private Logger fileLogger;
    private Serializer serializer;

    public ApplicationFramework() {
    }

    public void initialize(){
        classyRepository = new ClassyRepositoryImpl();
        MainFrame.getInstance().setVisible(true);
        //message generator and loggers
        messageGenerator = new MessageGeneratorImpl();
        messageGenerator.addSubscriber(MainFrame.getInstance());
        LoggerFactory loggerFactory = new LoggerFactory();
        consoleLogger = loggerFactory.createLogger(LoggerType.CONSOLE);
        fileLogger = loggerFactory.createLogger(LoggerType.FILE);
        serializer = new JacksonSerializer();
        //restartuje log.txt
        try {
            String filePath = "src/main/resources/log.txt";
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        messageGenerator.addSubscriber(fileLogger);
        messageGenerator.addSubscriber(consoleLogger);
        //tree action listener
        ClassyTreeImpl tree = (ClassyTreeImpl) MainFrame.getInstance().getClassyTree();
        tree.getTreeView().addMouseListener(new TreeMouseListener());

    }



    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public ClassyRepository getClassyRepository() {
        return classyRepository;
    }

    public static ApplicationFramework getInstance(){
        if (instance==null)
            instance = new ApplicationFramework();
        return instance;
    }

}
