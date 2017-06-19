/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pm.data.Variable;
import pm.PropertyType;
import pm.data.Method;
import pm.data.Class;

/**
 *
 * @author Xiangbin
 */
public class VarableCofirmBox {
    static final String BORDERED_PANE = "color_chooser_pane";
    public static String name=null;
    public  static Boolean Static=false;
    public static Boolean Isabstract=false;
    public static String type;
    public  static String AccessType="Public";
    public static Variable newVariable;
    public static Method newMethod;
    
    public static Variable disPlay(Class b){
        
            int sizeOfWindowX=300;
            int sizeOfWindowY=200;
            
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Add new Varable");
            window.setMinHeight(sizeOfWindowX);
            window.setMinWidth(sizeOfWindowY);
            VBox windowPlace=new VBox();
            windowPlace.setMinSize(sizeOfWindowX, sizeOfWindowY);
            
            
            //HBOXS
            HBox namePlace=new HBox();
            namePlace.setMinSize(300, 50); 
            namePlace.getStyleClass().add(BORDERED_PANE);
            HBox staitcPlace=new HBox();
            staitcPlace.setMinSize(300, 50);
            staitcPlace.getStyleClass().add(BORDERED_PANE);
            HBox typePlace=new HBox();
            typePlace.setMinSize(300, 50);
            typePlace.getStyleClass().add(BORDERED_PANE);
            HBox AccessPlace =new HBox();
            AccessPlace.setMinSize(300, 50);
            AccessPlace.getStyleClass().add(BORDERED_PANE);
            
            //Buttons and TextFile
            Label StringName=new Label("Varable Name: ");
            TextField name=new TextField();
            Label StringType=new Label("Type of Varable: ");
            TextField type=new TextField();
            ComboBox Access=new ComboBox();
            Label StringAccess=new Label("Access Type: ");
            if(b.Isinterface){
                Access.setItems(getAccesses2());
            }else{
            Access.setItems(getAccesses());}
            Access.getSelectionModel().select(0);
            Access.valueProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(oldValue!=null){
                        AccessType=newValue;
                    }
                }
                
            });
            CheckBox staticOrNot=new CheckBox("Stiatc");
            staticOrNot.setSelected(false);           

            Button finished=new Button("Enter");
            finished.setOnAction(e->{
            if(staticOrNot.isSelected()){
                Static=true;}
            
            
                newVariable=new Variable(name.getText(),type.getText(),staticOrNot.isSelected(),AccessType);
                window.close(); 
            });
            
            namePlace.getChildren().addAll(StringName,name);
            staitcPlace.getChildren().addAll(staticOrNot);
            typePlace.getChildren().addAll(StringType,type);
            AccessPlace.getChildren().addAll(StringAccess,Access);
            windowPlace.getChildren().addAll(namePlace,staitcPlace,typePlace,AccessPlace,finished);
            
            
         
           Scene scene=new Scene(windowPlace);
           window.setScene(scene);
           window.showAndWait();
           return newVariable;           
    }
    
    
    public static Variable EditVar(Class b,Variable a){
            int sizeOfWindowX=300;
            int sizeOfWindowY=200;
            
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Add new Varable");
            window.setMinHeight(sizeOfWindowX);
            window.setMinWidth(sizeOfWindowY);
            VBox windowPlace=new VBox();
            windowPlace.setMinSize(sizeOfWindowX, sizeOfWindowY);
            
            //HBOXS
            HBox namePlace=new HBox();
            namePlace.setMinSize(300, 50); 
            namePlace.getStyleClass().add(BORDERED_PANE);
            HBox staitcPlace=new HBox();
            staitcPlace.setMinSize(300, 50);
            staitcPlace.getStyleClass().add(BORDERED_PANE);
            HBox typePlace=new HBox();
            typePlace.setMinSize(300, 50);
            typePlace.getStyleClass().add(BORDERED_PANE);
            HBox AccessPlace =new HBox();
            AccessPlace.setMinSize(300, 50);
            AccessPlace.getStyleClass().add(BORDERED_PANE);
            
            
            
            //Buttons and TextFile
            Label StringName=new Label("Varable Name: ");
            TextField name=new TextField(a.name);
            Label StringType=new Label("Type of Varable: ");
            TextField type=new TextField(a.type);
            ComboBox Access=new ComboBox();
            Label StringAccess=new Label("Access Type: ");
             if(b.Isinterface){
                Access.setItems(getAccesses2());
            }else{
            Access.setItems(getAccesses());}      
            if(a.Access.equalsIgnoreCase("public")){
                   Access.getSelectionModel().select(0);
            }
            else if(a.Access.equalsIgnoreCase("private")){
                Access.getSelectionModel().select(1);
            }
            else{
                Access.getSelectionModel().select(2);
            }
            
            
            
            Access.valueProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(oldValue!=null){
                        AccessType=newValue;
                    }
                }
                
            });
            
            
            CheckBox staticOrNot=new CheckBox("Stiatc");
            
            if(a.Static){
                staticOrNot.setSelected(true); 
            }
            else{
            staticOrNot.setSelected(false);           }
            Button finished=new Button("Enter");
            finished.setOnAction(e->{
            if(staticOrNot.isSelected()){
                Static=true;}
                a.name=name.getText();
                a.Static=Static;
                a.Access=AccessType;
                a.type=type.getText();
                
                window.close(); 
            });
            
            namePlace.getChildren().addAll(StringName,name);
            staitcPlace.getChildren().addAll(staticOrNot);
            typePlace.getChildren().addAll(StringType,type);
            AccessPlace.getChildren().addAll(StringAccess,Access);
            windowPlace.getChildren().addAll(namePlace,staitcPlace,typePlace,AccessPlace,finished);
            
            
            
         
           Scene scene=new Scene(windowPlace);
           window.setScene(scene);
           window.showAndWait();
           return a;           
    }
    
            
    
        public static Method disPlayMth(Class b){
            int sizeOfWindowX=300;
            int sizeOfWindowY=350;
             ArrayList<String> arg=new ArrayList();
            
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Add new Varable");
            window.setMinHeight(sizeOfWindowX);
            window.setMinWidth(sizeOfWindowY);
            VBox windowPlace=new VBox();
            windowPlace.setMinSize(sizeOfWindowX, sizeOfWindowY);
            
            
            //HBOXS
            HBox namePlace=new HBox();
            namePlace.setMinSize(300, 50); 

            HBox staitcPlace=new HBox();
            staitcPlace.setMinSize(300, 50);

            HBox typePlace=new HBox();
            typePlace.setMinSize(300, 50);

            HBox AccessPlace =new HBox();
            AccessPlace.setMinSize(300, 50);
            
            HBox RturnPlace=new HBox();
            RturnPlace.setMinSize(300, 50);
            
            HBox arg1Place=new HBox();
            arg1Place.setMinSize(300, 50);
            

            
            HBox abstractPlace=new HBox();
            abstractPlace.setMinSize(300, 50);
            
            
            
            //Buttons and TextFile
            Label StringName=new Label("Method Name: ");
            TextField name=new TextField();
            Label StringType=new Label("ReturnType: ");
            TextField returnType=new TextField("Void");
            ComboBox Access=new ComboBox();
            Label StringAccess=new Label("Access Type: ");
             if(b.Isinterface){
                Access.setItems(getAccesses2());
            }else{
            Access.setItems(getAccesses());}
            Access.getSelectionModel().select(0);
            Label StringRetur=new Label("Return Type");
            TextField ReturnType=new TextField("void");
            Label StringArg1=new Label("Argument");
            TextField Arg1=new TextField("null");
            
            
            Arg1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                      arg.add(Arg1.getText());
                       Arg1.clear();
                }
            }
        });
            
            

            
            
            
            Access.valueProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(oldValue!=null){
                        AccessType=newValue;
                    }
                }
                
            });
            CheckBox staticOrNot=new CheckBox("Stiatc");
            staticOrNot.setSelected(false);    
            
            CheckBox abstractOrNot=new CheckBox("Abstract");
            abstractOrNot.setSelected(false);
            
            Button finished=new Button("Enter");
            finished.setOnAction(e->{
            if(staticOrNot.isSelected()){
                Static=true;}
            if(abstractOrNot.isSelected()){
                Isabstract=true;}
                newMethod=new Method(name.getText(),ReturnType.getText(),abstractOrNot.isSelected(),staticOrNot.isSelected(),AccessType);
                newMethod.arg=arg;
                window.close(); 
            });
            
            namePlace.getChildren().addAll(StringName,name);
            staitcPlace.getChildren().addAll(staticOrNot);
            typePlace.getChildren().addAll(StringType,returnType);
            AccessPlace.getChildren().addAll(StringAccess,Access);
            RturnPlace.getChildren().addAll(StringRetur,ReturnType);
            arg1Place.getChildren().addAll(StringArg1,Arg1);
            abstractPlace.getChildren().add(abstractOrNot);
            
            
            windowPlace.getChildren().addAll(namePlace,staitcPlace,typePlace,AccessPlace,abstractPlace,RturnPlace,arg1Place,finished);
            
            
         
           Scene scene=new Scene(windowPlace);
           window.setScene(scene);
           window.showAndWait();
           return newMethod;           
    }
        
        public static Method EditPlayMth(Class b,Method a){
            int sizeOfWindowX=300;
            int sizeOfWindowY=350;
             ArrayList<String> arg=new ArrayList();
            
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Add new Varable");
            window.setMinHeight(sizeOfWindowX);
            window.setMinWidth(sizeOfWindowY);
            VBox windowPlace=new VBox();
            windowPlace.setMinSize(sizeOfWindowX, sizeOfWindowY);
            
            
            //HBOXS
            HBox namePlace=new HBox();
            namePlace.setMinSize(300, 50); 

            HBox staitcPlace=new HBox();
            staitcPlace.setMinSize(300, 50);

            HBox typePlace=new HBox();
            typePlace.setMinSize(300, 50);

            HBox AccessPlace =new HBox();
            AccessPlace.setMinSize(300, 50);
            
            HBox RturnPlace=new HBox();
            RturnPlace.setMinSize(300, 50);
            
            HBox arg1Place=new HBox();
            arg1Place.setMinSize(300, 50);
            
            HBox abstractPlace=new HBox();
            abstractPlace.setMinSize(300, 50);
            
            
            
            //Buttons and TextFile
            Label StringName=new Label("Method Name: ");
            TextField name=new TextField(a.Name);
            Label StringType=new Label("ReturnType: ");
            
            TextField returnType=new TextField(a.Return);
            ComboBox Access=new ComboBox();
            Label StringAccess=new Label("Access Type: ");
             if(b.Isinterface){
                Access.setItems(getAccesses2());
            }else{
            Access.setItems(getAccesses());}
                        if(a.Access.equalsIgnoreCase("public")){
                   Access.getSelectionModel().select(0);
            }
            else if(a.Access.equalsIgnoreCase("private")){
                Access.getSelectionModel().select(1);
            }
            else{
                Access.getSelectionModel().select(2);
            }
            Label StringRetur=new Label("Return Type");
            TextField ReturnType=new TextField(a.Return);
            Label StringArg1=new Label("Argument");
            TextField Arg1=new TextField("null");
            
            
            Arg1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                      arg.add(Arg1.getText());
                       Arg1.clear();
                }
            }
        });

            
            
            Access.valueProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(oldValue!=null){
                        AccessType=newValue;
                    }
                }    
            });
            CheckBox staticOrNot=new CheckBox("Stiatc");
            staticOrNot.setSelected(false);    
            
            CheckBox abstractOrNot=new CheckBox("Abstract");
            abstractOrNot.setSelected(false);
            
            Button finished=new Button("Enter");
            finished.setOnAction(e->{
                a.Name=name.getText();
               a.Access=AccessType;
                a.Return=returnType.getText();
            if(staticOrNot.isSelected()){
                Static=true;}
           a.Static=Static;
            if(abstractOrNot.isSelected()){
                Isabstract=true;}
             a.Abstract=Isabstract;
                newMethod.arg=arg;
                window.close(); 
            });
            
            namePlace.getChildren().addAll(StringName,name);
            staitcPlace.getChildren().addAll(staticOrNot);
            typePlace.getChildren().addAll(StringType,returnType);
            AccessPlace.getChildren().addAll(StringAccess,Access);
            RturnPlace.getChildren().addAll(StringRetur,ReturnType);
            arg1Place.getChildren().addAll(StringArg1,Arg1);
            abstractPlace.getChildren().add(abstractOrNot);
            
            
            windowPlace.getChildren().addAll(namePlace,staitcPlace,typePlace,AccessPlace,abstractPlace,RturnPlace,finished);
            
            
         
           Scene scene=new Scene(windowPlace);
           window.setScene(scene);
           window.showAndWait();
           return a;           
    }
    
    
    
    
    
       static ObservableList<String>getAccesses(){
        ObservableList<String>data=FXCollections.observableArrayList();
        data.add("public");
        data.add("private");
        data.add("protected");
        return data;
    }
              static ObservableList<String>getAccesses2(){
        ObservableList<String>data=FXCollections.observableArrayList();
        data.add("public");
        return data;
    }
       
       
    
}
