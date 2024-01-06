package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.HighlightPainter;
import raf.dsw.classycraft.app.model.notifications.PackageNotification;
import raf.dsw.classycraft.app.model.notifications.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Setter
@Getter
public class PackageView extends JPanel implements ISubscriber {

    private String packageName = "";
    private String author = "";
    private Package pack;

    private StateManager stateManager;
    private List<DiagramView> tabs = new ArrayList<>();
    private Map<Diagram, List<ElementPainter>> diagramPainters = new HashMap<>();


    private JTabbedPane tabbedPane = new JTabbedPane();
    private JLabel packLabel = new JLabel();
    private JLabel authorLabel = new JLabel();

    public PackageView() {

        stateManager = new StateManager();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font customFont1 = new Font("Arial", Font.BOLD, 12);
        packLabel.setFont(customFont1);
        packLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Font customFont2 = new Font("Arial", Font.PLAIN, 10);
        authorLabel.setFont(customFont2);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(packLabel);
        add(authorLabel);
        add(tabbedPane);
    }



    public void updatePackageView(Package newPackage) {

        if (this.pack != null)
            this.pack.removeSubscriber(this);
        newPackage.addSubscriber(this);

        tabbedPane.removeAll();
        tabs.clear();

        this.setPack(newPackage);
        this.setAuthor(newPackage.getAuthor());
        this.setPackageName(newPackage.getName());
        this.packLabel.setText("package: " + this.packageName);
        if (this.author!=null)
            this.authorLabel.setText("author: " + this.author);
        else this.authorLabel.setText("");


        if (this.pack!=null){
            for (ClassyNode child:this.pack.getChildren()) {
                if (child instanceof Diagram){
                    DiagramView tab = new DiagramView((Diagram)child);
                    ((Diagram)child).addSubscriber(tab);
                    tabs.add(tab);
                    diagramPainters.putIfAbsent((Diagram)child, new ArrayList<>());
                }
            }
        }

        for (DiagramView tab:this.tabs) {
            tabbedPane.addTab(tab.getDiagram().getName(),new ImageIcon("src/main/resources/images/diagram.png"), tab);
        }

    }

    @Override
    public void update(Object notification) {
        if (notification instanceof PackageNtfType){
            this.pack = null;
            this.setPackageName("");
            this.packLabel.setText("");
            this.setAuthor("");
            this.authorLabel.setText("");
            this.tabbedPane.removeAll();
            this.tabs.clear();
            return;
        }
        String name = ((PackageNotification)notification).getName();
        switch (((PackageNotification)notification).getType()){
            case RENAME:
                this.setPackageName(name);
                this.packLabel.setText("package: " + name);
                break;
            case ADD_CHILD:
                Diagram addedDiagram = this.pack.getLastDiagramChild();
                DiagramView newTab = new DiagramView(addedDiagram);
                addedDiagram.addSubscriber(newTab);
                tabs.add(newTab);
                tabbedPane.addTab(newTab.getDiagram().getName(),new ImageIcon("src/main/resources/images/diagram.png"), newTab);
                diagramPainters.putIfAbsent(addedDiagram, new ArrayList<>());
                break;
            case AUTHOR_CHANGED:
                this.setAuthor(name);
                this.authorLabel.setText("author: " + name);
                break;
            default:
        }
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }


    public void startAddConnectionState(){
        this.stateManager.setAddConnectionState();
    }

    public void startAddInterclassState(){
        this.stateManager.setAddInterclassState();
    }

    public void startChangeContentState(){this.stateManager.setChangeContentState();}

    public void startDeleteElementState(){this.stateManager.setDeleteElementState();}

    public void startSelectState(){this.stateManager.setSelectState();}

    public void startMoveState(){this.stateManager.setMoveState();}
    public void startDuplicateState(){this.stateManager.setDuplicateState();}


    public void misKliknut(Point p){
        if (this.tabbedPane!=null) {
            DiagramView diagramView = (DiagramView) this.tabbedPane.getSelectedComponent();
            this.stateManager.getCurrentState().misKliknut(p, diagramView);
        }
    }

    public void misPrevucen(Point p){
        if (this.tabbedPane!=null) {
            DiagramView diagramView = (DiagramView) this.tabbedPane.getSelectedComponent();
            this.stateManager.getCurrentState().misPrevucen(p, diagramView);
        }
    }
    public void misOtpusten(Point p){
        if (this.tabbedPane!=null) {
            DiagramView diagramView = (DiagramView) this.tabbedPane.getSelectedComponent();
            this.stateManager.getCurrentState().misOtpusten(p, diagramView);
        }
    }

    public void addPainterToMap(ElementPainter painter){
        Diagram selectedDiagram = ((DiagramView)tabbedPane.getSelectedComponent()).getDiagram();
        diagramPainters.get(selectedDiagram).add(painter);
        selectedDiagram.addChild(painter.getElement());
        painter.getElement().addToTree((DiagramView)tabbedPane.getSelectedComponent());
        ((DiagramView)tabbedPane.getSelectedComponent()).repaint();
    }

    public void addPainterForDiagram(Diagram diagram, ElementPainter painter){
        diagramPainters.get(diagram).add(painter);
        diagram.addChild(painter.getElement());
        ((DiagramView)tabbedPane.getSelectedComponent()).repaint();
    }

    public void removePainterFromMap(ElementPainter painter){
        Diagram selectedDiagram = ((DiagramView)tabbedPane.getSelectedComponent()).getDiagram();
        diagramPainters.get(selectedDiagram).remove(painter);
        selectedDiagram.removeChild(painter.getElement());
        if (!(painter instanceof HighlightPainter))
            painter.getElement().removeFromTree();
        ((DiagramView)tabbedPane.getSelectedComponent()).repaint();
    }

    public DiagramView getCurrentDiagramView(){
        return ((DiagramView)tabbedPane.getSelectedComponent());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(tabbedPane == null || tabs == null || tabs.size() == 0) return;
        DiagramView selectedDiagram = (DiagramView) tabbedPane.getSelectedComponent();
        selectedDiagram.setPainters(diagramPainters.get(selectedDiagram.getDiagram()));
        selectedDiagram.subscribeToPainterElements();
        selectedDiagram.revalidate();
        selectedDiagram.repaint();
    }

}
