/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.data;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Xiangbin
 */
public class Method {
      public String Name;
      public String Return="Void";
      public Boolean Abstract;
      public Boolean Static;
      public String Access;
      public ArrayList<String> arg=new ArrayList();
      public Label MetInBox;
      public HBox MetHBox;
      public Button MetButton;
      
      public Method(){
              MetInBox=new Label();
              MetHBox=new HBox();
      }
      
    public Method(String name,String Return,Boolean Abstract,Boolean Static,String Access){
        Name=name;
        this.Return=Return;
        this.Abstract=Abstract;
        this.Static=Static;
        this.Access=Access;
        MetInBox=new Label();
        MetHBox=new HBox();
    }
    public String toString(){
        String a="Method Name:"+Name+" Abstract:"+Abstract+" Static:"+Static+" Access"+Access+" argment:";
        for(int i=0;i<arg.size();i++){
            a+=arg.get(i)+" ";
        }
        return a;
    }
    
    public Label newMetInBox(){
    String A;
    if(Access.equalsIgnoreCase("private")){A="-";}
    else if(Access.equalsIgnoreCase("public")){A="+";}
    else{A="#";}
    String B="";
    for(int n=0;n<arg.size();n++){
        B+="arg"+(n+1)+" :";
        B+=arg.get(n)+",  ";
    }
    B+=")";
    MetInBox.setText(A+Name+" ("+B+" : "+Return);
//    MetInBox.minHeightProperty();
//    MetInBox.minWidthProperty();
    return MetInBox;
}
    
        public Button getHbox(){
            MetHBox.getChildren().clear();
               Label Staticc;   
               Label ab;
                MetButton =new Button(Name);
                Label Accesss=new Label(Access);       
                if(Static){Staticc  =new Label("       ✓         ");}
                else{Staticc =new Label("     X         ");} 
    
                if(Abstract){ab  =new Label("       ✓         ");}
                else{ab =new Label("     X         ");} 
                Label returnType=new Label(Return);
                String a="";
                for(int n=0;n<arg.size();n++){
                    a+=arg.get(n)+"    ";
                }
                Label args=new Label(a);
                MetHBox.getChildren().addAll(MetButton,returnType,Staticc,ab,Accesss,args);
                MetHBox.setMinSize(300,30);
                MetButton.setMinSize(100, 30);
                returnType.setMinSize(100, 30);
                Staticc.setMinSize(50,30);
                ab.setMinSize(50, 30);
                args.setMinSize(50, 30);
                    return MetButton;
    }
}
