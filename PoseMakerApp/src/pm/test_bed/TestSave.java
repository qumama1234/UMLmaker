/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.test_bed;

import java.io.IOException;
import java.util.ArrayList;
import pm.data.DataManager;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import pm.data.Class;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pm.data.Method;
import pm.data.Variable;
import pm.gui.Workspace;


/**
 *
 * @author Xiangbin
 */
public class TestSave {
    static final String JSON_CLASSES="Classes";
    static final String JSON_CLASS="Class";
    static final String JSON_X="x";
    static final String JSON_Y="y";
    static final String JSON_NAME="name";
    static final String JSON_PACKAGE="package";
    static final String JSON_VARIABLE="variable";
    static final String JSON_VARIABLE_NAME="variable_name";
    static final String JSON_METHOD="method";
    static final String JSON_IMPORT="improt";
    static final String JSON_VARIABLE_STATIC="static";
    static final String JSON_VARIABLE_TYPE="type";
    static final String JSON_VARIABLE_ACCESS="access";
    static final String JSON_METHOD_NAME="method_name";
    static final String JSON_METHOD_RETURN="method_return";
    static final String JSON_METHOD_ACCESS="method_access";
    static final String JSON_METHOD_ABSTRACT="method_abstract";
    static final String JSON_METHOD_ARG="method_arg";
    static final String JSON_METHOD_STATIC="method_static";
    static final String JSON_PARENT="parent";

    
     public static ArrayList<Class> testClass=new ArrayList();
     
     public TestSave(){}
     
     public static void inputData(){
      Class CounterTask=new Class("CounterTask"); 
      Class DataTask=new Class("DataTask");
      Class PauseHandler=new Class("PauseHandler");   
      Class StartHandler=new Class("StartHandler");
      Class ThreadExample=new Class("ThreadExample");
      
      CounterTask.x=1;
      CounterTask.y=1;
      DataTask.x=100;
      DataTask.y=100;
      PauseHandler.x=200;
      PauseHandler.y=200;
      StartHandler.x=300;
      StartHandler.y=300;
      ThreadExample.x=400;
      ThreadExample.y=400;
      ThreadExample.parent="Application";
      
      

      Method CounterTaskMethod1=new Method("call","Void",false,false,"protected");
      Method CounterTaskMethod2=new Method("CounterTask","null",false,false,"public");
      Variable  CounterTaskVariable1=new Variable("app","ThreadExample",false,"private");
      Variable CounterTaskVariable2=new Variable("counter","int",false,"private");
       CounterTask.methods.add(CounterTaskMethod1);
       CounterTask.methods.add(CounterTaskMethod2);
       CounterTask.variables.add(CounterTaskVariable2);
       CounterTask.variables.add(CounterTaskVariable1);
       CounterTask.improt.add("javafx.application.Platform");
       CounterTask.improt.add("javafx.concurrent.Task");
    
      DataTask.improt.add("javafx.application.Platform");
      DataTask.improt.add("javafx.concurrent.Task");
      Variable DataTaskVariable1=new Variable("app","ThreadExample",false,"private");
      Variable DataTaskVariable2=new Variable("counter","int",false,"private");
      Method DataTaskMethod1=new Method("call","Void",false,false,"protected");
      Method DataTaskMethod2=new Method("CounterTask","null",false,false,"public");
      DataTaskMethod2.arg.add("ThreadExample");
      CounterTask.methods.add(DataTaskMethod1);
      CounterTask.methods.add(DataTaskMethod2);
      CounterTask.variables.add(DataTaskVariable1);
      CounterTask.variables.add(DataTaskVariable2);
      
      PauseHandler.improt.add("javafx.event.Event");
      PauseHandler.improt.add("javafx.event.EventHandler");
      Method PauseHandlerMethod1=new Method("PauseHanler","null",false,false,"public");
      PauseHandlerMethod1.arg.add("ThreadExample");
      Method PauseHandlerMethod2=new Method("handle","void",false,false,"public");
      PauseHandlerMethod2.arg.add("Event");
      Variable PauseHandlerVariable1=new Variable("app","ThreadExample",false,"private");
      PauseHandler.methods.add(PauseHandlerMethod1);
      PauseHandler.methods.add(PauseHandlerMethod2);
      PauseHandler.variables.add( PauseHandlerVariable1);
      
     StartHandler.improt.add("javafx.event.Event");
     StartHandler.improt.add("javafx.event.EventHandler");
     Method StartHandlerMethod1=new Method("StartHandler","null",false,false,"public");
     StartHandlerMethod1.arg.add("ThreadExample");
     Method StartHandlerMethod2=new Method("handle","void",false,false,"public");
     StartHandlerMethod2.arg.add("Event");
     Variable StartHandlerVariable1=new Variable("app","ThreadExample",false,"private");
     StartHandler.methods.add(StartHandlerMethod1);
     StartHandler.methods.add(StartHandlerMethod2); 
     StartHandler.variables.add(StartHandlerVariable1);
     
     ThreadExample.improt.add("javafx.application.Application");
     ThreadExample.improt.add("javafx.concurrent.Task");
     ThreadExample.improt.add("javafx.geometry.Orientation");
     ThreadExample.improt.add("javafx.scene.Scene");
     ThreadExample.improt.add("javafx.scene.control.Button");
     ThreadExample.improt.add("javafx.scene.control.ScrollPane");
     ThreadExample.improt.add("javafx.scene.control.TextArea");
     ThreadExample.improt.add("javafx.scene.layout.BorderPane");
     ThreadExample.improt.add("javafx.scene.layout.FlowPane");
     ThreadExample.improt.add("javafx.stage.Stage");
     Variable ThreadExampleVariable1=new Variable("START_TEXT","String",true,"public");
     Variable ThreadExampleVariable2=new Variable("PAUSE_TEXT","String",true,"public");
     Variable ThreadExampleVariable3=new Variable("window","Stage",false,"private");
     Variable ThreadExampleVariable4=new Variable("appPane","BorderPane",false,"private");
     Variable ThreadExampleVariable5=new Variable("topPane","FlowPane",false,"private");
     Variable ThreadExampleVariable6=new Variable("startButton","Button",false,"private");
     Variable ThreadExampleVariable7=new Variable("pauseButton","Buttone",false,"private");
     Variable ThreadExampleVariable8=new Variable("scrollPane","ScrollPane",false,"private");
     Variable ThreadExampleVariable9=new Variable("textArea","TextArea",false,"private");
     Variable ThreadExampleVariable10=new Variable("dateThread","Thread",false,"private");
     Variable ThreadExampleVariable11=new Variable("dateTask","Task",false,"private");
     Variable ThreadExampleVariable12=new Variable("counterThread","Thread",false,"private");
     Variable ThreadExampleVariable13=new Variable("counterTask","Task",false,"private");
     Variable ThreadExampleVariable14=new Variable("work","boolean",false,"private");
     Method ThreadExampleMethod1=new Method("start","void",false,false,"public");
     ThreadExampleMethod1.arg.add("Stage");
     Method ThreadExampleMethod2=new Method("start","void",false,false,"public");
     Method ThreadExampleMethod3=new Method("startWork","void",false,false,"public");
     Method ThreadExampleMethod4=new Method("pauseWork","void",false,false,"public");
     Method ThreadExampleMethod5=new Method("doWork","boolean",false,false,"public");
     Method ThreadExampleMethod6=new Method("appendText","void",false,false,"public");
     ThreadExampleMethod6.arg.add("String");
     Method ThreadExampleMethod7=new Method("sleep","void",false,false,"public");
     ThreadExampleMethod6.arg.add("int");
     Method ThreadExampleMethod8=new Method("initLayout","void",false,false,"private");
     Method ThreadExampleMethod9=new Method("initHandlers","void",false,false,"private");
     Method ThreadExampleMethod10=new Method("initWindow","void",false,false,"private");
     ThreadExampleMethod10.arg.add("Stage");
     Method ThreadExampleMethod11=new Method("initThreads","void",false,false,"private");
     Method ThreadExampleMethod12=new Method("main","void",false,true,"public");
     ThreadExampleMethod12.arg.add("String[]");
     ThreadExample.methods.add(ThreadExampleMethod1);
     ThreadExample.methods.add(ThreadExampleMethod2);
     ThreadExample.methods.add(ThreadExampleMethod3);
     ThreadExample.methods.add(ThreadExampleMethod4);
     ThreadExample.methods.add(ThreadExampleMethod5);
     ThreadExample.methods.add(ThreadExampleMethod6);
     ThreadExample.methods.add(ThreadExampleMethod7);
     ThreadExample.methods.add(ThreadExampleMethod8);
     ThreadExample.methods.add(ThreadExampleMethod9);
     ThreadExample.methods.add(ThreadExampleMethod10);
     ThreadExample.methods.add(ThreadExampleMethod11);
     ThreadExample.methods.add(ThreadExampleMethod12);
     ThreadExample.variables.add(ThreadExampleVariable1);
     ThreadExample.variables.add(ThreadExampleVariable2);
     ThreadExample.variables.add(ThreadExampleVariable3);
     ThreadExample.variables.add(ThreadExampleVariable4);
     ThreadExample.variables.add(ThreadExampleVariable5);
     ThreadExample.variables.add(ThreadExampleVariable6);
     ThreadExample.variables.add(ThreadExampleVariable7);
     ThreadExample.variables.add(ThreadExampleVariable8);
     ThreadExample.variables.add(ThreadExampleVariable9);
     ThreadExample.variables.add(ThreadExampleVariable10);
     ThreadExample.variables.add(ThreadExampleVariable11);
     ThreadExample.variables.add(ThreadExampleVariable12);
     ThreadExample.variables.add(ThreadExampleVariable13);
     ThreadExample.variables.add(ThreadExampleVariable14);
     
     testClass.add(StartHandler);
     testClass.add(DataTask);
     testClass.add(PauseHandler);
     testClass.add(CounterTask);
     testClass.add(ThreadExample);
     }
     public static void JsonWriterTest(ArrayList<Class> Classes) throws IOException{
     
         JSONObject SavingJson=new JSONObject();
         JSONArray AllClasses=new JSONArray();
         
         //each Class
         for(int n=0;n<Classes.size();n++){
             //System.out.print(n);
             String name =Classes.get(n).getName();
             String parent=Classes.get(n).parent;
             double x=Classes.get(n).x;
             double y=Classes.get(n).y;
             String packet=Classes.get(n).getPackage();
             JSONObject currentClass=new JSONObject();
             currentClass.put(JSON_NAME,name);
             currentClass.put(JSON_X,x);
             currentClass.put(JSON_Y,y);
             currentClass.put(JSON_PACKAGE,packet);
             currentClass.put(JSON_PARENT,parent);
             JSONArray AllVariables=new JSONArray();
             for(int v=0;v<Classes.get(n).variables.size();v++){
                 JSONObject currentVariable=new JSONObject();    
                 currentVariable.put(JSON_VARIABLE_NAME,Classes.get(n).variables.get(v).name );
                 currentVariable.put(JSON_VARIABLE_STATIC,Classes.get(n).variables.get(v).Static );
                 currentVariable.put(JSON_VARIABLE_TYPE,Classes.get(n).variables.get(v).type );
                 currentVariable.put(JSON_VARIABLE_ACCESS,Classes.get(n).variables.get(v).Access );
                 AllVariables.add(currentVariable);
             }
             currentClass.put(JSON_VARIABLE,AllVariables);
             
             JSONArray Allmethoads=new JSONArray();
              for(int v=0;v<Classes.get(n).methods.size();v++){
                JSONObject currentMethod =new JSONObject();
                 currentMethod.put(JSON_METHOD_NAME,Classes.get(n).methods.get(v).Name);
                 currentMethod.put(JSON_METHOD_RETURN,Classes.get(n).methods.get(v).Return);
                 currentMethod.put(JSON_METHOD_ABSTRACT,Classes.get(n).methods.get(v).Abstract);
                 currentMethod.put(JSON_METHOD_STATIC,Classes.get(n).methods.get(v).Static);
                 currentMethod.put(JSON_METHOD_ACCESS,Classes.get(n).methods.get(v).Access);
                 JSONArray ALLargs= new JSONArray();
                 for(int a=0;a<Classes.get(n).methods.get(v).arg.size();a++){
                     ALLargs.add(Classes.get(n).methods.get(v).arg.get(a));
                    // currentMethod.put(JSON_METHOD_ARG,Classes.get(n).methods.get(v).arg.get(a));
                     
                 }
                currentMethod.put(JSON_METHOD_ARG,ALLargs);
                 Allmethoads.add(currentMethod);
             }
              currentClass.put(JSON_METHOD,Allmethoads);
              JSONArray Allimprots=new JSONArray();
              for(int v=0;v<Classes.get(n).improt.size();v++){
               Allimprots.add(Classes.get(n).improt.get(v));
             }
              currentClass.put(JSON_IMPORT, Allimprots);
              AllClasses.add(currentClass);
         }
         SavingJson.put(JSON_CLASSES, AllClasses);
//         System.out.print(AllClasses);
         try(FileWriter file=new FileWriter("DesignSaveTest.txt")){
                file.write(SavingJson.toJSONString());
                System.out.println("Successfully Copied JSON Object to File...");     
                file.flush();
                file.close();
                 }
     }

    
    
    
    
//  public static void main(String args[]) throws IOException{
//     inputData();
//     JsonWriterTest(testClass);
//     
//     //System.out.print("duawuidhawudhaid");
//  }
}
