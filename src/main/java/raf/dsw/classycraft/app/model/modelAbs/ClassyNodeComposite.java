package raf.dsw.classycraft.app.model.modelAbs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Generalizacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Kompozicija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Zavisnost;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = raf.dsw.classycraft.app.model.modelImpl.Package.class, name = "Package"),
        @JsonSubTypes.Type(value = Diagram.class, name = "Diagram")
})
public abstract class ClassyNodeComposite extends ClassyNode{

    List<ClassyNode> children;


    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public ClassyNodeComposite() {
        this.children = new ArrayList<>();
    }




    public boolean childNameTaken(String name){
        for (ClassyNode child:this.getChildren()) {
            if (child.getName().trim().equals(name.trim()))
                return true;
        }
        return false;
    }

    public abstract void addChild(ClassyNode child);

    public abstract void removeChild(ClassyNode child);


}
