package pm.gui;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import pm.PropertyType;
import pm.data.Class;
import saf.ui.AppGUI;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import pm.data.DataManager;
import pm.data.Method;
import pm.data.Variable;
import static saf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static saf.settings.AppStartupConstants.PATH_WORK;

/**
 * This class serves as the workspace component for this application, providing
 * the user interface controls for editing work.
 *
 * @author Richard McKenna
 * @author XiangbinZeng
 * @version 1.0
 *
 */
public class Workspace extends AppWorkspaceComponent {

    //statc final string

    static final String BORDERED_PANE = "color_chooser_pane";
    static final String BUTTON_STYLE = "tag_button";
    static final String SHOW_PANE = "edit_toolbar";
    static final String NAME = "heading_label";
    static final String OTHER_SUBHEADING = "prompt_text_field";
    public static final String PATH_WORK = "./work/";

    //All panes
    HBox TopPane;
    HBox filePane;
    HBox ToolPane;
    HBox SizePane;
    HBox botPane;
    GridPane classPane;
    ScrollPane classShowPaneCarryer;
    public Pane classShowPane;
    VBox workspaceBorderPane;

    //Variable for WorkSpace 
//    Button Save;
//    Button Load;
//    Button SaveAs;
    Button Photo;
    Button Code;
//    Button Exit;
    Button Select;
    Button Resize;
    Button AddClass;
    Button AddInterface;
    Button Remove;
    Button Undo;
    Button Redo;

    Button ZoomIn;
    Button ZoomOut;
    Button Bigger;
    Button Smaller;

    CheckBox Gird;
    CheckBox Snap;

//List of Class
    Class newClass;
//    public ArrayList<Class> classes;
    Label Name;
    Label Package;
    Label Parent;
    Label Varibale;
    Label Method;

    Button addVar;
    Button delVar;
    Button EdVar;
    Button addMet;
    Button EdMet;
    Button delMet;

    HBox varBox;
    HBox metBox;

    //Class SelectedClass;
    TextField NameTextField;
    TextField NameOfPackage;
    ComboBox parents;
    //Canvas
    public Canvas canvas;
    Group group;

    //TWO PEANS
    ScrollPane VariablePlace;

    ScrollPane MethodsPlace;


    // HERE'S THE APP
    AppTemplate app;

    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;
    DataManager dataManager;
            double orgX;
        double orgY;
        double tranX;
        double tranY;
        double layoutX;
        double layoutY;
        double offsetX;
        double offsetY;

    /**
     * Constructor for initializing the workspace, note that this constructor
     * will fully setup the workspace user interface for use.
     *
     * @param initApp The application this workspace is part of.
     *
     * @throws IOException Thrown should there be an error loading application
     * data for setting up the user interface.
     */
    public Workspace(AppTemplate initApp) throws IOException {
        // KEEP THIS FOR LATER
        app = initApp;

        // KEEP THE GUI FOR LATER
        gui = app.getGUI();
        dataManager = (DataManager) app.getDataComponent();

        filePane = new HBox();
        ToolPane = new HBox();
        TopPane = new HBox();
        SizePane = new HBox();
        classShowPane = new Pane();
        classPane = new GridPane();
        botPane = new HBox();

        //set the size of the pane 
        
        classShowPane.setPrefSize(1200, 900);
        classPane.setPrefSize(600, 900);
        filePane.setPrefWidth(300);
        ToolPane.setPrefWidth(900);
        SizePane.setPrefWidth(600);

        //put panes to top pane
        TopPane.getChildren().addAll(filePane, ToolPane, SizePane);
        botPane.getChildren().addAll(classShowPane, classPane);

        //add the pane to the border
        workspaceBorderPane = new VBox();
        workspaceBorderPane.getChildren().addAll(TopPane, botPane);

        workspace = new Pane();
        workspace.getChildren().add(workspaceBorderPane);

        //add Button to the pane
        Photo = new Button("Photo");
        Code = new Button("Code");
        filePane.getChildren().addAll(Photo, Code);

        Select = new Button("Select");
        Resize = new Button("Resize");
        AddClass = new Button("AddClass");
        AddInterface=new Button("AddInterface");
        Remove = new Button("Remove");
        Undo = new Button("Undo");
        Redo = new Button("Redo");
        ToolPane.getChildren().addAll(Select, Resize, AddClass, AddInterface,Remove, Undo, Redo);

        ZoomIn = new Button("ZoomIn");
        ZoomOut = new Button("ZoomOut");
        Bigger=new Button("Bigger");
        Smaller=new Button("Smaller");
        Gird = new CheckBox("Gird");
        Snap = new CheckBox("Snap");
        SizePane.getChildren().addAll(ZoomIn, ZoomOut, Bigger,Smaller,Gird, Snap);
        
        Name = new Label("Class Name:    ");
        NameTextField = new TextField();
        Name.setMinSize(250, 80);
        Package = new Label("Package:    ");
        Parent = new Label("Parent:    ");
        Parent.setMinSize(200, 80);
        parents = new ComboBox();
        parents.setDisable(false);
        parents.setEditable(true);
        parents.setPromptText("Parent of Class name");

        parents.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (dataManager.SelectedClass != null) {
                    dataManager.SelectedClass.parent=newValue;
                    for(int n=0;n<dataManager.classes.size();n++){
                        if(dataManager.classes.get(n).getName().equalsIgnoreCase(newValue)){
                            dataManager.SelectedClass.Parent=dataManager.classes.get(n);
                        }
                    }
                      typeChecker(dataManager.SelectedClass);
                }        
            }
        });

        NameOfPackage = new TextField();
        Package.setMinSize(200, 80);
        Name.getStyleClass().add(NAME);
        Package.getStyleClass().add(OTHER_SUBHEADING);

        Varibale = new Label("Variable ");
        Method = new Label("Method ");
        addVar = new Button("+");
        delVar = new Button("-");
        EdVar=new Button("Edit");
        addMet = new Button("+");
        delMet = new Button("-");
        EdMet=new Button("Edit");
        

        VariablePlace = new ScrollPane();
        VariablePlace.autosize();
        VariablePlace.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VariablePlace.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        
        
        MethodsPlace = new ScrollPane();
//        VariablePlace.setMinSize(200, 200);
//        MethodsPlace.setMinSize(200,100);

        varBox = new HBox();
        metBox = new HBox();
        varBox.setMinSize(300, 40);
        metBox.setMinSize(300, 40);
        varBox.getChildren().addAll(Varibale, addVar, delVar);
        metBox.getChildren().addAll(Method, addMet, delMet);
   
        classPane.add(Name, 0, 0);
        //classPane.add
        classPane.add(NameTextField, 1, 0);
        classPane.add(Package, 0, 1);
        classPane.add(NameOfPackage, 1, 1);
        classPane.add(Parent, 0, 2);
        classPane.add(parents, 1, 2);
        classPane.add(varBox, 0, 3);
        classPane.add(EdVar,1,3);

        classPane.add(VariablePlace, 0, 4, 2, 8);
        VariablePlace.setMinSize(300, 220);
        classPane.add(metBox, 0, 12);
        classPane.add(EdMet,1,12);

        classPane.add(MethodsPlace, 0, 13, 2, 8);
        MethodsPlace.setMinSize(300, 220);

        NameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    if (dataManager.SelectedClass != null) {
                        dataManager.getSelectedClass().setName(NameTextField.getText());
                        dataManager.getSelectedClass().DrawIt();
                    }
                }
            }
        });
        NameOfPackage.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    if (dataManager.SelectedClass != null) {
                        dataManager.getSelectedClass().setPackage(NameTextField.getText());
                    }
                }
            }
        });

        

            AddClass.setOnAction(e -> {
                disSelectedMoel();
            AddInterface.setDisable(false);
            AddClass.setDisable(true);
            Select.setDisable(false);
            for (int n = 0; n < dataManager.classes.size(); n++) {
                dataManager.classes.get(n).layer.setOnMouseDragged(null);
            }
            classShowPane.setOnMouseClicked((MouseEvent event) -> {
                VBox a;
                newClass = new Class();
//             classes.add(newClass);
                dataManager.addClass(newClass);
                // System.out.print(dataManager.selectedClassChecker()); 
                if (dataManager.SelectedClass != null) {
                    //System.out.println(dataManager.getSelectedClass().x);
                    dataManager.getSelectedClass().loseEffect();
                }
                ChangeSelectedClass(newClass);
                UpdataText();
                dataManager.getSelectedClass().getEffect();
                a = newClass.DrawIt();
                a.setLayoutX(event.getX());
                a.setLayoutY(event.getY());
                newClass.y = a.getLayoutY();
                newClass.x = a.getLayoutX();
                classShowPane.getChildren().add(a);
                //System.out.print(newClass.x);     
            });
        });

            Select.setOnAction(e->{
            selecteModel();
            });
        
             
        Remove.setOnAction(e -> {
            if (dataManager.SelectedClass != null) {
                dataManager.delectClass(newClass);
                classShowPane.getChildren().remove(dataManager.getSelectedClass().classPlace);
                dataManager.classes.remove(dataManager.SelectedClass);
                dataManager.SelectedClass=null;
            }
        });

        addVar.setOnAction(e -> {
            if (dataManager.SelectedClass != null) {
                Variable newVar = VarableCofirmBox.disPlay(dataManager.SelectedClass);
                dataManager.SelectedClass.variables.add(newVar);
                newVar.getHbox().setOnAction(c->{
                 if( dataManager.SelectedClass.selectedVar!=null){
                 dataManager.SelectedClass.selectedVar.VarButton.setDisable(false);}
                 dataManager.SelectedClass.selectedVar=newVar;
                 newVar.VarButton.setDisable(true);
                });
                dataManager.SelectedClass.VarsPlace.getChildren().add(newVar.VarHBox);
                dataManager.SelectedClass.DrawIt();
                typeChecker(dataManager.SelectedClass);
            }
        });
        
        addMet.setOnAction(e->{
         if (dataManager.SelectedClass != null) {
             Method newMethod=VarableCofirmBox.disPlayMth(dataManager.SelectedClass);
             dataManager.SelectedClass.methods.add(newMethod);
             newMethod.getHbox().setOnAction(c->{
                 if( dataManager.SelectedClass.selectedMet!=null){
                     dataManager.SelectedClass.selectedMet.MetButton.setDisable(false);
                 }
                 dataManager.SelectedClass.selectedMet=newMethod;
                 newMethod.MetButton.setDisable(true); });
                 dataManager.SelectedClass.MetsPlace.getChildren().add( newMethod.MetHBox);
                dataManager.SelectedClass.DrawIt();
                typeChecker(dataManager.SelectedClass);
         }
        });
        
        delVar.setOnAction(e->{
              if (dataManager.SelectedClass != null) {
                  if(dataManager.SelectedClass.selectedVar!=null){
               dataManager.SelectedClass.variables.remove(dataManager.SelectedClass.selectedVar);
               dataManager.SelectedClass.VarsPlace.getChildren().remove(dataManager.SelectedClass.selectedVar.VarHBox);
               dataManager.SelectedClass.selectedVar=null;
               dataManager.SelectedClass.DrawIt();
                  }
                  typeChecker(dataManager.SelectedClass);
              }
        });
        
        
        delMet.setOnAction(e->{
        if(dataManager.SelectedClass != null) {
                  if(dataManager.SelectedClass.selectedMet!=null){
                      dataManager.SelectedClass.methods.remove(dataManager.SelectedClass.selectedMet);
                        dataManager.SelectedClass.MetsPlace.getChildren().remove(dataManager.SelectedClass.selectedMet.MetHBox);
                         dataManager.SelectedClass.selectedMet=null;
                         dataManager.SelectedClass.DrawIt();
                  }
                  typeChecker(dataManager.SelectedClass);
        }
        });
        

        EdVar.setOnAction(e->{
             if(dataManager.SelectedClass != null) {
                 if(dataManager.SelectedClass.selectedVar!=null){
                      dataManager.SelectedClass.selectedVar=VarableCofirmBox.EditVar(dataManager.SelectedClass ,dataManager.SelectedClass.selectedVar);
                      dataManager.SelectedClass.selectedVar.getHbox().setOnAction(c->{
                 if( dataManager.SelectedClass.selectedVar!=null){
                 dataManager.SelectedClass.selectedVar.VarButton.setDisable(false);}
                 dataManager.SelectedClass.selectedVar=dataManager.SelectedClass.selectedVar;
                });
                      dataManager.SelectedClass.selectedVar.VarButton.setDisable(true);
                      dataManager.SelectedClass.DrawIt();
                      typeChecker(dataManager.SelectedClass);
                 }
            }
        });
        
        
        EdMet.setOnAction(e->{
                    if(dataManager.SelectedClass != null) {
                 if(dataManager.SelectedClass.selectedMet!=null){
                    dataManager.SelectedClass.selectedMet=VarableCofirmBox.EditPlayMth(dataManager.SelectedClass,dataManager.SelectedClass.selectedMet);
                  System.out.print(dataManager.SelectedClass.selectedMet.Name);  
                      dataManager.SelectedClass.selectedMet.getHbox().setOnAction(c->{
                 if( dataManager.SelectedClass.selectedMet!=null){
                 dataManager.SelectedClass.selectedMet.MetButton.setDisable(false);}
                 dataManager.SelectedClass.selectedMet=dataManager.SelectedClass.selectedMet;
                });
                       dataManager.SelectedClass.selectedMet.MetButton.setDisable(true);
                      dataManager.SelectedClass.DrawIt();   
                      typeChecker(dataManager.SelectedClass);
                 }
             }
        });
        
        
        Bigger.setOnAction(e->{
         if( dataManager.SelectedClass!=null){
             dataManager.SelectedClass.SizeX+=10;
             dataManager.SelectedClass.SizeY+=10;
             dataManager.SelectedClass.DrawIt();
         }});
              Smaller.setOnAction(e->{
         if( dataManager.SelectedClass!=null){
             dataManager.SelectedClass.SizeX-=10;
             dataManager.SelectedClass.SizeY-=10;
             dataManager.SelectedClass.DrawIt();
         } });
        
             AddInterface.setOnAction(e -> {
            disSelectedMoel();
            AddClass.setDisable(false);
            Select.setDisable(false);
            AddInterface.setDisable(true);
            for (int n = 0; n < dataManager.classes.size(); n++) {
                dataManager.classes.get(n).layer.setOnMouseDragged(null);
            }
            classShowPane.setOnMouseClicked((MouseEvent event) -> {
                VBox a;
                newClass = new Class();
                newClass.Isabstract=true;
                newClass.Isinterface=true;
                dataManager.addClass(newClass);
                // System.out.print(dataManager.selectedClassChecker()); 
                if (dataManager.SelectedClass != null) {
                    //System.out.println(dataManager.getSelectedClass().x);
                    dataManager.getSelectedClass().loseEffect();
                }
                ChangeSelectedClass(newClass);
                UpdataText();
                dataManager.getSelectedClass().getEffect();
                a = newClass.DrawIt();
                a.setLayoutX(event.getX());
                a.setLayoutY(event.getY());
                newClass.y = a.getLayoutY();
                newClass.x = a.getLayoutX();
                classShowPane.getChildren().add(a);
                //System.out.print(newClass.x);     
            });
        });
             
                     
        Photo.setOnAction(e -> {
            FileChooser fc=new FileChooser();
            fc.setInitialDirectory(new File(PATH_WORK));
            fc.setTitle("Where Do you want save your UML Photo?");
            fc.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Photo File","*.png")
            );
            File selectedFile = fc.showSaveDialog(app.getGUI().getWindow());
            
            System.out.print("Am i here?");
            double a;
            WritableImage Wimage = new WritableImage(1575, 900);
            SnapshotParameters snap = new SnapshotParameters();
            Wimage = classShowPane.snapshot(snap, Wimage);
            BufferedImage bImage = SwingFXUtils.fromFXImage(Wimage, null);
//            a = (int) (Math.random() * 1000);
//            File imgFile = new File("./image/" + a + ".png");
           selectedFile.getParentFile().mkdir();
            try {
                ImageIO.write(bImage, "png", selectedFile);
            } catch (IOException ex) {
                Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Code.setOnAction(e->{
            try {
                ExportingScrCode(dataManager);
            } catch (IOException ex) {
                Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
            
        ZoomIn.setOnAction(e->{
        System.out.print(dataManager.classes.size());  
        });
    }
//    public void DelVar(Var){  
//        classShowPane.getChildren().removeAll();
//    }
    public void initStyle() {
	// NOTE THAT EACH CLASS SHOULD CORRESPOND TO
        // A STYLE CLASS SPECIFIED IN THIS APPLICATION'S
        // CSS FILE
        filePane.getStyleClass().add(BORDERED_PANE);
        ToolPane.getStyleClass().add(BORDERED_PANE);
        SizePane.getStyleClass().add(BORDERED_PANE);
        classPane.getStyleClass().add(BORDERED_PANE);
//        classShowPane.getStyleClass().add(SHOW_PANE);
    }
    public void addAllStyleToButton() {
        Photo.getStyleClass().add(BUTTON_STYLE);
        Code.getStyleClass().add(BUTTON_STYLE);
        Select.getStyleClass().add(BUTTON_STYLE);
        Resize.getStyleClass().add(BUTTON_STYLE);
        AddClass.getStyleClass().add(BUTTON_STYLE);
        Remove.getStyleClass().add(BUTTON_STYLE);
        Undo.getStyleClass().add(BUTTON_STYLE);
        Redo.getStyleClass().add(BUTTON_STYLE);
        ZoomIn.getStyleClass().add(BUTTON_STYLE);
        ZoomOut.getStyleClass().add(BUTTON_STYLE);
    }
    public void reloadWorkspace() {
       // dataManager.classes = new ArrayList<Class>();
        classShowPane.getChildren().clear();
        VBox a;
        for(int n=0;n<dataManager.classes.size();n++){     

                 a = dataManager.classes.get(n).DrawIt();
                 a.setLayoutX(dataManager.classes.get(n).x);
                 a.setLayoutY(dataManager.classes.get(n).y);
                 classShowPane.getChildren().add(a);
                dataManager.SelectedClass=dataManager.classes.get(n);
                //System.out.print( dataManager.SelectedClass.parent);
           for(int p=0;p<dataManager.classes.size();p++){
                if(dataManager.SelectedClass.getName().equalsIgnoreCase(dataManager.classes.get(p).getName())){
                    //System.out.print("YEs");
                    dataManager.SelectedClass.Parent=dataManager.classes.get(p);
                }
            }
                
                for(int m=0;m<dataManager.classes.get(n).methods.size();m++){
                    Method met=dataManager.classes.get(n).methods.get(m);
                     met.getHbox().setOnAction(c->{
                 if( dataManager.SelectedClass.selectedMet!=null){
                     dataManager.SelectedClass.selectedMet.MetButton.setDisable(false);
                 }
                 dataManager.SelectedClass.selectedMet= met;
                 met.MetButton.setDisable(true); });
                 dataManager.SelectedClass.MetsPlace.getChildren().add( met.MetHBox);
                }
             for(int v=0;v<dataManager.classes.get(n).variables.size();v++){
                  Variable var=dataManager.classes.get(n).variables.get(v);
                     var.getHbox().setOnAction(c->{
                 if( dataManager.SelectedClass.selectedVar!=null){
                     dataManager.SelectedClass.selectedVar.VarButton.setDisable(false);
                 }
                 dataManager.SelectedClass.selectedVar=var ;
                 var.VarButton.setDisable(true); });
                 dataManager.SelectedClass.VarsPlace.getChildren().add( var.VarHBox);
             }
             typeChecker(dataManager.SelectedClass);
        }
    }
    public void cleanEveryThing(){
         dataManager.classes = new ArrayList<Class>();
        classShowPane.getChildren().clear();
    }
    public void UpdataText() {
        NameTextField.setText(dataManager.getSelectedClass().getName());
        NameOfPackage.setText(dataManager.getSelectedClass().getPackage());
    }
    public void ReLoadAllShap() {
        for (int a = 0; a < dataManager.classes.size(); a++) {
            VBox c;
            c = dataManager.classes.get(a).DrawIt();
            c.setLayoutX(dataManager.classes.get(a).x);
            c.setLayoutX(dataManager.classes.get(a).y);
            classShowPane.getChildren().add(c);
            
        }
    }
    public void ChangeSelectedClass(Class NewSelected){
      if(dataManager.SelectedClass!=null){
          //Lost Effect
          dataManager.SelectedClass.loseEffect();
      }     
      //get Effect

      VariablePlace.setContent(NewSelected.VarsPlace);
      MethodsPlace.setContent(NewSelected.MetsPlace);
      dataManager.SelectedClass=NewSelected;
      dataManager.SelectedClass.getEffect();
      parents.setItems(getAccesses( dataManager.SelectedClass));
    }
    public ObservableList<String>getAccesses(Class a){
        ObservableList<String>data=FXCollections.observableArrayList();
        for(int n=0;n<dataManager.classes.size();n++){
            if(dataManager.classes.get(n)!=a){
            data.add(dataManager.classes.get(n).getName());
                    }
        }
       return data;
    } 
    public void typeChecker(Class a){
        
        //parent checker
        if(a.Parent!=null){
            //System.out.print("2222222222");
             boolean parentsChecker=false;
            for(int n=0;n<a.myLines.size();n++){          
            if(a.myLines.get(n).Ending==a.Parent&&a.myLines.get(n).isParents){
                //System.out.print("YES?");
                parentsChecker=true;
            }
            }
              if(parentsChecker==false){
                   //System.out.print("YES?2");
                    myLine newLine=new myLine(a,a.Parent);   
                    newLine.isParents=true;
                    a.myLines.add(newLine);
                    classShowPane.getChildren().add(newLine.line);     
                    newLine.line.toBack();
                    System.out.print(newLine.line.toString());
                }
        }


           //Var checker
        for(int n=0;n<a.variables.size();n++){
            Boolean varChacker=false;
            Class EndClass;
           for(int x=0;x<a.myLines.size();x++){
            if(a.myLines.get(x).Ending.getName().equalsIgnoreCase(a.variables.get(n).type)){
                varChacker=true;
            }
        }
          System.out.print(varChacker);
           if(varChacker==false){
               for(int x=0;x<dataManager.classes.size();x++){
                   if(a.variables.get(n).type.equalsIgnoreCase(dataManager.classes.get(x).getName())){
                    myLine newLine=new myLine(a,dataManager.classes.get(x));
                    a.myLines.add(newLine);
                   newLine. line.setStyle("-fx-stroke-line-cap: butt;-fx-stroke-dash-offset: 6;-fx-stroke-dash-array: 12 2 4 2; -fx-stroke: green;"); 
                    classShowPane.getChildren().add(newLine.line);     
                    newLine.line.toBack();
                   }
                   }
           }
        }
        
        for(int n=0;n<a.methods.size();n++){
            for(int x=0;x<dataManager.classes.size();x++){
            if(a.methods.get(n).Return.equalsIgnoreCase(dataManager.classes.get(n).getName())){
                Boolean metChecker=false;
                for(int y=0;y<a.myLines.size();y++){
                    if(a.myLines.get(y).Ending.getName().equalsIgnoreCase(dataManager.classes.get(n).getName())){
                        metChecker=true;
                    }
                }
                if(metChecker==false){
                     myLine newLine=new myLine(a,(dataManager.classes.get(n)));
                     newLine. line.setStyle("-fx-stroke-line-cap: butt;-fx-stroke-dash-offset: 6;-fx-stroke-dash-array: 12 2 4 2; -fx-stroke: green;"); 
                    a.myLines.add(newLine);
                    classShowPane.getChildren().add(newLine.line);    
                    newLine.line.toBack();
                }
            }
            
            
            for(int z=0;z<a.methods.get(n).arg.size();z++){
                if(a.methods.get(n).arg.get(z).equalsIgnoreCase(dataManager.classes.get(n).getName())){
                    Boolean metChecker=false;
                    for(int y=0;y<a.myLines.size();y++){
                    if(a.myLines.get(y).Ending.getName().equalsIgnoreCase(dataManager.classes.get(n).getName())){
                        metChecker=true;
                    }
                }
                  if(metChecker==false){
                     myLine newLine=new myLine(a,(dataManager.classes.get(n)));
                     newLine. line.setStyle("-fx-stroke-line-cap: butt;-fx-stroke-dash-offset: 6;-fx-stroke-dash-array: 12 2 4 2; -fx-stroke: green;"); 
                    a.myLines.add(newLine);
                    classShowPane.getChildren().add(newLine.line);      
                    newLine.line.toBack();
                }            
            }
                }
        }
    }
    }  
    public void selecteModel(){
        AddClass.setDisable(false);
        classShowPane.setOnMouseClicked(null);

        for(int n=0;n<dataManager.classes.size();n++){
            layoutX=dataManager.classes.get(n).x;
            layoutY=dataManager.classes.get(n).y;
            System.out.println("X:"+layoutX);
             System.out.println("Y:"+layoutY);
            Class a=dataManager.classes.get(n);
        dataManager.classes.get(n).classPlace.setOnMouseClicked(e->{          
                ChangeSelectedClass(a);
        });
        
                dataManager.classes.get(n).classPlace.setOnMousePressed(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent event) {
                    orgX=event.getScreenX();
                    orgY=event.getScreenY();
                   layoutX=a.x;
                   layoutY=a.y;
                    
                    tranX=((VBox)event.getSource()).getTranslateX();
                    tranY=((VBox)event.getSource()).getTranslateY();
                     
                     
                }
         });
         dataManager.classes.get(n).classPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double offsetX = event.getSceneX() - orgX;
                    double offsetY = event.getSceneY() - orgY;
                    double newTranX = tranX + offsetX;
                    double newTranY = tranY + offsetY;
                    
                    ((VBox) event.getSource()).setTranslateX(newTranX);
                    ((VBox) event.getSource()).setTranslateY(newTranY);                    
                    
                    
                    double offsetLayoutX = event.getSceneX() - orgX;
                    double offsetLayoutY = event.getSceneY() - orgY;
                    double newTranLayoutX = layoutX + offsetLayoutX;
                    double newTranLayoutY = layoutY + offsetLayoutY;
                    
                    shiyishi(a, newTranLayoutX, newTranLayoutY);
                    updataAllLines();

//                  layoutX=((VBox)event.getSource()).getScaleX();
//                  layoutY=((VBox)event.getSource()).getScaleY();
//                   System.out.println("X:!!!!"+layoutX);
//                   System.out.println("Y:!!!!"+layoutY);
                }
         });
     }
    }
    public void disSelectedMoel(){
               for(int n=0;n<dataManager.classes.size();n++){
                   dataManager.classes.get(n).classPlace.setOnMouseClicked(null);
                    dataManager.classes.get(n).classPlace.setOnMousePressed(null);
                    dataManager.classes.get(n).classPlace.setOnMouseDragged(null);
               }
    }  
    public void updataAllLines(){
        for(int n=0;n<dataManager.classes.size();n++){
            //System.out.print("iam here");
            for(int x=0;x<dataManager.classes.get(n).myLines.size();x++){
                            double sx= dataManager.classes.get(n).myLines.get(x). Starting.x;
                              double sy=  dataManager.classes.get(n).myLines.get(x).Starting.y;
                              double ex=  dataManager.classes.get(n).myLines.get(x).Ending.x;
                             double ey=  dataManager.classes.get(n).myLines.get(x).Ending.y;
                             dataManager.classes.get(n).myLines.get(x).line.setStartX(sx);
                              dataManager.classes.get(n).myLines.get(x).line.setStartY(sy);
                              dataManager.classes.get(n).myLines.get(x). line.setEndX(ex);
                             dataManager.classes.get(n).myLines.get(x). line.setEndY(ey);
                           dataManager.classes.get(n).myLines.get(x).line.toBack();
            }
        }
    }
    public void shiyishi(Class a,double x,double y){
        a.x=x;
        a.y=y;
    }
    public void   ExportingScrCode( DataManager a) throws IOException{
       for(int n=0;n<a.classes.size();n++){
           String s="";
          
           Class thisclass=a.classes.get(n);
           s+="package "+thisclass.getPackage()+";\n";
           s+=("\n");
           for(int lines=0;lines< thisclass.myLines.size();lines++){
           s+=("import "+ thisclass.myLines.get(lines).Ending.getPackage()+"."
                   +thisclass.myLines.get(lines).Ending.getName());
           }
           s+=("\n");
           s+=("\n");
           s+=("\n");
             if(thisclass.Parent==null){
             s+="public class "+thisclass.getName()+" {\n";
             }
             else{
                 s+="public class "+thisclass.getName()+" exdents " +thisclass.parent+" {\n";
             }
             for(int vars=0;vars<thisclass.variables.size();vars++){
                 Variable v=thisclass.variables.get(vars);               
                 String sta="";
                 if(v.Static==true){sta+="static";}
                 String type=v.type;
                 if(v.type.equalsIgnoreCase("String")){
                     type="String";
                 }
                 else if(v.type.equalsIgnoreCase("Boolean")){
                 type="Boolean";
             }
                 s+=v.Access+" "+sta+" "+v.type+" "+type+" "+v.name+";\n";
             }
             s+="\n";
             s+="\n";
             
             for(int mets=0; mets<thisclass.methods.size();mets++){
              Method m=thisclass.methods.get(mets);
                String sta="";
                 if(m.Static==true){sta+="static";}
                 String rt=typeChanger(m.Return);
                 String args="";
                 for(int arg=0;arg<m.arg.size();arg++){
                     args+=typeChanger(m.arg.get(arg));
                     if(arg+1<m.arg.size()){
                         args+=",";
                     }
                 }
              s+=m.Access+" "+sta+" "+rt+" "+m.Name+" "+"("+args+")"+"{\n";
              s+="}\n";

       }         
           s+="}\n";
           
           
                File filep=new File("./ExPcode/"+"/"+thisclass.getPackage()+"/"+thisclass.getName()+".java");
      filep.getParentFile().mkdir();
//      if(!filep.exists()){
//         filep.createNewFile();
//      }
      Writer writer =new FileWriter(filep);
      BufferedWriter bw=new BufferedWriter(writer);
      bw.write(s);
      writer.flush();
      bw.flush();
           
           
       }
    }
    public String typeChanger(String a){
        String rt= a;
                 if(rt.equalsIgnoreCase("String")){
                     rt="String";
                 }
                 else if(rt.equalsIgnoreCase("Boolean")){
                 rt="Boolean";
             }
                 else if(rt.equalsIgnoreCase("null")){
                     rt="void";
                 }
                 else{
                     return a;
                 }
                 return a;
    }
}
