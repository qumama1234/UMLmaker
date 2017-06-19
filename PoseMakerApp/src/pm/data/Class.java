/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.data;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import pm.data.DataManager;
import pm.file.FileManager;
import pm.gui.Workspace;
import pm.gui.myLine;
import saf.AppTemplate;
import saf.ui.AppGUI;


/**
 * @author Xiangbin
 */
public class Class { 
    AppTemplate app;
    AppGUI gui;
    public String parent="null"; 
    public Class Parent=null;
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    
    
    
    //Vars
    String name="null";
    String Package="default";
    public double x;
    public double y;
    public ArrayList<myLine> myLines=new ArrayList();
    public ArrayList<Method> methods=new  ArrayList();
    public ArrayList<Variable> variables=new  ArrayList();
    public ArrayList<String> improt=new ArrayList();
    public  GraphicsContext gc;
    public  Canvas layer=new Canvas(120,180);
    public Variable selectedVar=null;
    public VBox VarsPlace;
    public VBox MetsPlace;
    public VBox classPlace;
    public Boolean Isinterface=false;
    public Boolean Isabstract=false;
    public Method selectedMet=null;
    DropShadow ds = new DropShadow();
    public int SizeX=120;
    public int SizeY=150;
    
    public Class() {
        VarsPlace=new VBox();
         VarsPlace.setMinSize(500, 500);
               VarsPlace.getChildren().add(TiTleForVarablePlace());
        MetsPlace=new VBox();
        MetsPlace.setMinSize(500, 500);
                MetsPlace.getChildren().add(TiTleForMethodsPlace());
        classPlace=new VBox();
        classPlace.setMinSize(SizeX,90);
       classPlace.setStyle("-fx-background-color: #a6a6a6;");

    }
    public   Class(String name){this.name=name;
        VarsPlace=new VBox();
        VarsPlace.setMinSize(500, 500);
        VarsPlace.getChildren().add(TiTleForVarablePlace());
        MetsPlace=new VBox();
        MetsPlace.setMinSize(500, 500);
        MetsPlace.getChildren().add(TiTleForMethodsPlace());
        classPlace=new VBox();
        classPlace.setMinSize(SizeX,SizeY);
       classPlace.setStyle("-fx-background-color: #a6a6a6");
    }

    public Variable getSelectedVar() {
        return selectedVar;
    }

    public void setSelectedVar(Variable selectedVar) {
        this.selectedVar = selectedVar;
    }

    public Method getSelectedMet() {
        return selectedMet;
    }

    public void setSelectedMet(Method selectedMet) {
        this.selectedMet = selectedMet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String Package) {
        this.Package = Package;
    } 
    
    public void getEffect(){
        ds.setOffsetY(7.5);
        ds.setOffsetX(7.5);
        ds.setColor(Color.GRAY);
         classPlace.setEffect(ds);
    }
    
    public void loseEffect(){
         classPlace.setEffect(null);
    }
    
    public void rewrite(){
     gc.beginPath();
     gc.clearRect(3, 3, 113, 58);
     gc.fillText(name,0+5,0+20);
    }
    
    public void Selected(){
        
    }
    
//    public void setItLayer(){
//        
//        layer.setOnMouseDragged(e->{
//        System.out.println(layer.getLayoutX());   
//        layer.setTranslateX(e.getX());
//        layer.setTranslateY(e.getY());
//        layer.setLayoutY(e.getX());
//        layer.setLayoutY(e.getY());
//       // System.out.println(layer.getLayoutX());
//        x=layer.getLayoutX();
//        y=layer.getLayoutX();
//        });
//    }
    
    
//       public SetVarPlace(){
//           
//       }
    
    public String toString(){
           String a="name:"+name+ " Package:"+getPackage()+" X:"+x+" Y:"+y+ "Variables: ";
           for(int v=0;v< variables.size();v++){
               a+= variables.get(v).toString()+" ";
           }
           a+=" Methods:";
           for(int v=0;v< methods.size();v++){
               a+= methods.get(v).toString()+" ";
           }
           a+=" improts:";
           for(int v=0;v<improt.size();v++){
                a+= improt.get(v);
           }
              return a;
          }  
    public VBox DrawIt(){        
           classPlace.getChildren().clear();
           classPlace.setMinSize(SizeX, SizeY);
           VBox putName=new VBox();
           putName.minWidth(SizeX);
           putName.minHeight(50);
            putName.setStyle(" -fx-border-width: 3");
            putName.setStyle("border-style: solid;");
            putName.setStyle("-fx-background-color: #ff0fff;");
           //putName.fillWidthProperty();
           VBox putVarable=new VBox();
           putVarable.setStyle("-fx-background-color: #CCFF66;");
           //putVarable.fillWidthProperty();
           VBox putMethod=new VBox();
           putMethod.setStyle("-fx-background-color: #33FFFF;");
           //putMethod.setFillWidth(true);
          // putMethod
          // putMethod.fillWidthProperty();
           System.out.print(name);
           Label Name1=new Label(name);
           if(this.Isabstract){
               Label Name0=new Label("<<Interface>>");
               putName.getChildren().add(Name0);
           }
           putName.getChildren().add(Name1);
           if(Isabstract){
                      Label Name2=new Label("{abstract}");
                      putName.getChildren().add(Name2);
           }

           for(int v=0;v<this.variables.size();v++){
               putVarable.getChildren().add(variables.get(v).newVarInBox());
           }
           for(int v=0;v<this.methods.size();v++){
               putMethod.getChildren().add(methods.get(v).newMetInBox());
           }  
         classPlace.getChildren().add(putName);
         classPlace.getChildren().add(putVarable);
         classPlace.getChildren().add(putMethod);
        return classPlace;
       }
    public HBox TiTleForVarablePlace(){
        Label name=new Label("▼Variable Name");
        Label Type=new Label("▼Type");
        Label Stact=new Label("▼Static");
        Label Access=new Label("▼ Access");
        Access.setMinSize(100, 40);
        Type.setMinSize(100,40);
        Stact.setMinSize(60, 40);
        name.setMinSize(130, 40);
        HBox a =new HBox();
        a.setStyle("-fx-background-color: #99ff99;");
        a.getChildren().addAll(name,Type,Stact,Access);
        return a;
    }
    public HBox TiTleForMethodsPlace(){
             Label name=new Label("▼Method Name");
        Label Type=new Label("▼Return");
        Label Stact=new Label("▼Static");
        Label ab=new Label("▼Abstract");
        Label Access=new Label("▼ Access");
        Label args=new Label("▼ Arguments");
        Access.setMinSize(80, 40);
        ab.setMinSize(50, 40);
        Type.setMinSize(90,40);
        Stact.setMinSize(50, 40);
        name.setMinSize(100, 40);
        args.setMinSize(30, 40);
    HBox a=new HBox();
    a.getChildren().addAll(name,Type,Stact,ab,Access,args);
    a.setStyle("-fx-background-color: #99ff99;");
    return a;
    }
    }
