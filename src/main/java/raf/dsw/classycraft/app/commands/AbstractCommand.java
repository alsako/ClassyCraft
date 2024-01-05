package raf.dsw.classycraft.app.commands;

import java.awt.geom.NoninvertibleTransformException;

public abstract class AbstractCommand {

    public abstract void doCommand();

    public abstract void undoCommand() throws NoninvertibleTransformException;
}
