package raf.dsw.classycraft.app.model.modelImpl.classcontent;

import raf.dsw.classycraft.app.model.modelImpl.classcontent.ClassContent;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

public class Metoda extends ClassContent {

    public Metoda(String name, VisibilityTypes visibility, String type) {
        super(name, visibility, type);
    }

    @Override
    public String toString() {
        char typeSign;
        switch (getVisibility()){
            case PRIVATE:
                typeSign='-';
                break;
            case PUBLIC:
                typeSign='+';
                break;
            case PROTECTED:
                typeSign='#';
                break;
            default:
                typeSign=' ';
        }
        return typeSign + getName() + "(): " + getType();
    }
}
