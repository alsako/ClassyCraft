package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.modelImpl.ProjectExplorer;

public class ClassyRepositoryImpl implements ClassyRepository{

    private ProjectExplorer root;

    public ClassyRepositoryImpl() {
       root = new ProjectExplorer("My Project Explotrer");
    }


    @Override
    public ProjectExplorer getRoot() {
        return root;
    }
}
