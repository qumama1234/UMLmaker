/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pm.data.Class;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;


/**
 *
 * @author Xiangbin
 */
public class myLine {
    public Line line;
    public Class Starting;
    public Class Ending;
    public  Polygon ending;
    public Boolean isParents=false;
                double orgX;
        double orgY;
        double tranX;
        double tranY;

    
    public myLine(Class Starting,Class Ending){
        this.Starting=Starting;
        this.Ending=Ending;
        line=new Line();
        
        ending=new Polygon();
       
        linkthem();
    } 
    public Line linkthem(){
          double sx=  Starting.x;
          double sy=  Starting.y;
          double ex=  Ending.x;
          double ey=  Ending.y;
          
          //System.out.print(sx+" "+sy+" "+ex+" "+ey);
          line.setStartX(sx);
          line.setStartY(sy);
          line.setEndX(ex);
          line.setEndY(ey);
          ending.minHeight(5);
          ending.minWidth(5);
          ending.setLayoutX(ex);
          ending.setLayoutY(ey);
          return line;
    }
    
    public void linefix(){
        line.setOnMousePressed(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent event) {
                    
               orgX=event.getScreenX();
               orgY=event.getScreenY();
               tranX=((Line)event.getSource()).getTranslateX();
               tranY=((Line)event.getSource()).getTranslateY();
                }
         });
    }
    
    
    
    
//    public Line(){
//
//    }

    
}
