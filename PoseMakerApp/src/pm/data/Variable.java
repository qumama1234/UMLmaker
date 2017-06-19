/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.data;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Xiangbin
 */

public class Variable {
    public String name;
    public Boolean Static;
    public String type;
    public String Access;
    public HBox VarHBox;
    public Boolean Selete=false;
    public Button VarButton;
    public Label VarInBox;
    
    public  Variable(){
         VarHBox=new HBox();
         VarInBox=new Label();
    }

   public  Variable(String name,String type,boolean Static,String Access){
        this.name=name;
        this.type=type;
        this.Static=Static;
        this.Access=Access;
        VarHBox=new HBox();
        VarInBox=new Label();
        
    }
   
public Button getHbox(){
                VarHBox.getChildren().clear();
                Label Staticc;               
                VarButton =new Button(name);
                Label Accesss=new Label(Access);       
                if(Static){Staticc  =new Label("       ✓           ");}
                else{Staticc =new Label("       X           ");}
                Label Type=new Label(type);
                VarHBox.getChildren().addAll(VarButton,Type,Staticc,Accesss);
                VarHBox.setMinSize(300, 30);
                Accesss.setMinSize(90, 30);
                Type.setMinSize(90,30);
                Staticc.setMinSize(50, 30);
                VarButton.setMinSize(170, 30);                
                //VariableCarryer.getChildren().add(newHbox);
                return VarButton;
}

public Label newVarInBox(){
    String A;
    if(Access.equalsIgnoreCase("private")){A="-";}
    else if(Access.equalsIgnoreCase("public")){A="+";}
    else{A="#";}
    VarInBox.setText(A+name+":"+type);
    return VarInBox;
}

   
   public String toString(){
       return "VariableName:"+name+" Type:"+type+" Static:"+Static+" Access"+Access;
   }
}