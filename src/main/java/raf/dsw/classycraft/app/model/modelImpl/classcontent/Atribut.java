package raf.dsw.classycraft.app.model.modelImpl.classcontent;

import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;
@NoArgsConstructor
public class Atribut extends ClassContent {

    public Atribut(String name, VisibilityTypes visibility, String type) {
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
        return typeSign + getName() + ": " + getContentType();

    }
}
