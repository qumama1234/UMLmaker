/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.test_bed;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import org.json.simple.JSONObject;
import pm.data.Class;
import pm.data.Method;
import pm.data.Variable;

/**
 *
 * @author Xiangbin
 */
public class TestLoad {
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
        ArrayList<Class> loadClasses=new ArrayList();
//        public static void main(String[] args) throws Exception{
//         ArrayList<Class> newClass=new ArrayList();
//           ReadJasonTxt(newClass);
//           for(int i=0;i<newClass.size();i++){
//               System.out.println(newClass.get(i).toString());
//           }
//           
//    
//        }
        
        public static void ReadJasonTxt(ArrayList<Class> a) throws IOException{
             JsonObject json = loadJSONFile("DesignSaveTest.txt");
             JsonArray Classes =json.getJsonArray(JSON_CLASSES);
             
             for(int c=0;c<Classes.size();c++){
                 Class newClass=new Class();
                 JsonObject jsonClass = Classes.getJsonObject(c);
                 String Classname=jsonClass.getString(JSON_NAME);
                 String ClassParent=jsonClass.getString(JSON_PARENT);
                 double ClassX= getDataAsDouble(jsonClass, JSON_X);
                 double ClassY= getDataAsDouble(jsonClass, JSON_Y);
                 String ClassPackage=jsonClass.getString(JSON_PACKAGE);
                 newClass.x=ClassX;
                 newClass.y=ClassY;
                 newClass.setName(Classname);
                 newClass.parent=ClassParent;
                 newClass.setPackage(ClassPackage);
  
                 //load the method to the array
                 JsonArray Methods =jsonClass.getJsonArray(JSON_METHOD);
                 for(int m=0;m<Methods.size();m++){
                     JsonObject jsonMethod=(JsonObject) Methods.get(m);
                     Method newMethod=new Method();
                     newMethod.Name=jsonMethod.getString(JSON_METHOD_NAME);
                     newMethod.Access=jsonMethod.getString(JSON_METHOD_ACCESS);
                     newMethod.Abstract=jsonMethod.getBoolean(JSON_METHOD_ABSTRACT);
                     newMethod.Return=jsonMethod.getString(JSON_METHOD_RETURN);
                     newMethod.Static=jsonMethod.getBoolean(JSON_METHOD_STATIC);
                     JsonArray args=jsonMethod.getJsonArray(JSON_METHOD_ARG);
                     
                     for(int arg=0;arg<args.size();arg++){
                         newMethod.arg.add(args.getString(arg));
                     }                 
                     newClass.methods.add(newMethod);      
                 }
                 //load the all variable in to class
                 JsonArray Variables =jsonClass.getJsonArray(JSON_VARIABLE);
                 for(int v=0;v<Variables.size();v++){             
                     JsonObject jsonVariable=(JsonObject) Variables.get(v);
                    String variablesName=jsonVariable.getString(JSON_VARIABLE_NAME);
                    Variable newVariable=new Variable();
                    newVariable.name=variablesName;
                    newVariable.Static=jsonVariable.getBoolean(JSON_VARIABLE_STATIC);
                    newVariable.type=jsonVariable.getString(JSON_VARIABLE_TYPE);
                    newVariable.Access=jsonVariable.getString(JSON_VARIABLE_ACCESS);
                    newClass.variables.add(newVariable);
                 }
                 //load the all improt to the class
                 JsonArray imports=jsonClass.getJsonArray(JSON_IMPORT);
                 for(int i=0;i<imports.size();i++){
                    newClass.improt.add(imports.get(i).toString());
                 }


                 a.add(newClass);
                 
  
             }
             
             
             
            
            
            
        }
        
         private static JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
         
          public static double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
          

    
    
}
