package raf.dsw.classycraft.app.model.modelAbs;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
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

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClassyNodeComposite.class, name = "ClassyNodeComposite"),
        @JsonSubTypes.Type(value = DiagramElement.class, name = "DiagramElement")
})
public abstract class ClassyNode {

    private String name;
    @JsonIgnore
    private transient ClassyNode parent;

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public ClassyNode() {
    }

    public String getName() {
        return name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

}
