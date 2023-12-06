package raf.dsw.classycraft.app.model.modelImpl.classcontent;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

@Getter
@Setter
public abstract class ClassContent {

    private String name;
    private VisibilityTypes visibility;
    private String type;
    private Interclass interclass;

    public ClassContent(String name, VisibilityTypes visibility, String type) {
        this.name = name;
        this.visibility = visibility;
        this.type = type;
    }
}
