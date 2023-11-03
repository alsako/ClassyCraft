package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messagegen.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLogger implements Logger{

    public FileLogger() {
    }

    @Override
    public void update(Object message) {
        try{
            String filePath = "src/main/resources/log.txt";
            FileWriter writer = new FileWriter(filePath);
            writer.write(((Message)message).toString());
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
