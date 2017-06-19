package pm.data;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import pm.ClassDesignerMaker;
import pm.data.Class;
import pm.gui.Workspace;
import saf.components.AppDataComponent;
import saf.AppTemplate;

/**
 * This class serves as the data management component for this application.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class DataManager implements AppDataComponent {
    // THIS IS A SHARED REFERENCE TO THE APPLICATION
    AppTemplate app;
    
    //There are the classes to draw
   public  ArrayList<Class> classes;
   public Class SelectedClass;

    public Class getSelectedClass() {
        return SelectedClass;
    }

    public void setSelectedClass(Class SelectedClass) {
        this.SelectedClass = SelectedClass;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
    
    //This is the class being sized but not yet added
    Class newClass;
    
    //SlectedClass
    Class seletedClass;
       
    //x,y where you are
    double x;
    double y;
    
    //Current state of the app
    ClassDesignerMaker state;
    
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
                      classes=new ArrayList<Class>();
    }
    public void reloadWorkSpace(){
//         Workspace workspace = (Workspace)app.getWorkspaceComponent();
//         workspace.reloadWorkspace();
    }

    public void addClass(Class a){
        classes.add(a);
    }
    public void delectClass(Class a){
        classes.remove(a);
    }

        public void reset() {
       //  classes=new ArrayList<Class>();
    }
        
        
        
        
        public void lineTester(){
            
            
            
        }
    }
    

   


