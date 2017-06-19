package pm.file;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import pm.data.Class;
import pm.data.DataManager;
import pm.data.Method;
import pm.data.Variable;
import static pm.test_bed.TestLoad.getDataAsDouble;
import saf.AppTemplate;
import saf.ui.AppGUI;

/**
 * This class serves as the file management component for this application,
 * providing all I/O services.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class FileManager implements AppFileComponent {

    static final String JSON_CLASSES = "Classes";
    static final String JSON_CLASS = "Class";
    static final String JSON_X = "x";
    static final String JSON_Y = "y";
    static final String JSON_NAME = "name";
    static final String JSON_PACKAGE = "package";
    static final String JSON_VARIABLE = "variable";
    static final String JSON_VARIABLE_NAME = "variable_name";
    static final String JSON_METHOD = "method";
    static final String JSON_IMPORT = "improt";
    static final String JSON_VARIABLE_STATIC = "static";
    static final String JSON_VARIABLE_TYPE = "type";
    static final String JSON_VARIABLE_ACCESS = "access";
    static final String JSON_METHOD_NAME = "method_name";
    static final String JSON_METHOD_RETURN = "method_return";
    static final String JSON_METHOD_ACCESS = "method_access";
    static final String JSON_METHOD_ABSTRACT = "method_abstract";
    static final String JSON_METHOD_ARG = "method_arg";
    static final String JSON_METHOD_STATIC = "method_static";
    static final String JSON_PARENT = "parent";
    AppGUI gui;
    AppTemplate app;

    /**
     * This method is for saving user work, which in the case of this
     * application means the data that constitutes the page DOM.
     *
     * @param data The data management component for this application.
     *
     * @param filePath Path (including file name/extension) to where to save the
     * data to.
     *
     * @throws IOException Thrown should there be an error writing out data to
     * the file.
     */

    public void saveData(AppDataComponent data, String filePath) throws IOException {
        DataManager dataManager = (DataManager) data;
        ArrayList<Class> Classes = dataManager.classes;
//        for(int xixi=0;xixi<=Classes.size();xixi++){
//            System.out.print(xixi);
//        }
        JSONObject SavingJson = new JSONObject();
        JSONArray AllClasses = new JSONArray();

        //each Class
        for (int n = 0; n < Classes.size(); n++) {
            // System.out.print(n);
            //System.out.print(n);
            String name = Classes.get(n).getName();
            String parent = Classes.get(n).parent;
            double x = Classes.get(n).x;
            double y = Classes.get(n).y;
            String packet = Classes.get(n).getPackage();
            JSONObject currentClass = new JSONObject();
            currentClass.put(JSON_NAME, name);
            currentClass.put(JSON_X, x);
            currentClass.put(JSON_Y, y);
            currentClass.put(JSON_PACKAGE, packet);
            currentClass.put(JSON_PARENT, parent);
            JSONArray AllVariables = new JSONArray();
            for (int v = 0; v < Classes.get(n).variables.size(); v++) {
                JSONObject currentVariable = new JSONObject();
                currentVariable.put(JSON_VARIABLE_NAME, Classes.get(n).variables.get(v).name);
                currentVariable.put(JSON_VARIABLE_STATIC, Classes.get(n).variables.get(v).Static);
                currentVariable.put(JSON_VARIABLE_TYPE, Classes.get(n).variables.get(v).type);
                currentVariable.put(JSON_VARIABLE_ACCESS, Classes.get(n).variables.get(v).Access);
                AllVariables.add(currentVariable);
            }
            currentClass.put(JSON_VARIABLE, AllVariables);

            JSONArray Allmethoads = new JSONArray();
            for (int v = 0; v < Classes.get(n).methods.size(); v++) {
                JSONObject currentMethod = new JSONObject();
                currentMethod.put(JSON_METHOD_NAME, Classes.get(n).methods.get(v).Name);
                currentMethod.put(JSON_METHOD_RETURN, Classes.get(n).methods.get(v).Return);
                currentMethod.put(JSON_METHOD_ABSTRACT, Classes.get(n).methods.get(v).Abstract);
                currentMethod.put(JSON_METHOD_STATIC, Classes.get(n).methods.get(v).Static);
                currentMethod.put(JSON_METHOD_ACCESS, Classes.get(n).methods.get(v).Access);
                JSONArray ALLargs = new JSONArray();
                for (int a = 0; a < Classes.get(n).methods.get(v).arg.size(); a++) {
                    ALLargs.add(Classes.get(n).methods.get(v).arg.get(a));
                    // currentMethod.put(JSON_METHOD_ARG,Classes.get(n).methods.get(v).arg.get(a));

                }
                currentMethod.put(JSON_METHOD_ARG, ALLargs);
                Allmethoads.add(currentMethod);
            }
            currentClass.put(JSON_METHOD, Allmethoads);
            JSONArray Allimprots = new JSONArray();
            for (int v = 0; v < Classes.get(n).improt.size(); v++) {
                Allimprots.add(Classes.get(n).improt.get(v));
            }
            currentClass.put(JSON_IMPORT, Allimprots);
            AllClasses.add(currentClass);
        }
        SavingJson.put(JSON_CLASSES, AllClasses);
//         System.out.print(AllClasses);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(SavingJson.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            file.flush();
            file.close();
        }
    }

    /**
     * This method loads data from a JSON formatted file into the data
     * management component and then forces the updating of the workspace such
     * that the user may edit the data.
     *
     * @param data Data management component where we'll load the file into.
     *
     * @param filePath Path (including file name/extension) to where to load the
     * data from.
     *
     * @throws IOException Thrown should there be an error reading in data from
     * the file.
     */
    //@Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        
        DataManager dataManager = (DataManager) data;
        // ArrayList<Class> myclasses=dataManager.classes;
        dataManager.reloadWorkSpace();

        JsonObject json = loadJSONFile(filePath);
        JsonArray Classes = json.getJsonArray(JSON_CLASSES);
         Class newClass;
        for (int c = 0; c < Classes.size(); c++) {
            newClass = new Class();
            JsonObject jsonClass = Classes.getJsonObject(c);
            String Classname = jsonClass.getString(JSON_NAME);
            String ClassParent = jsonClass.getString(JSON_PARENT);
            double ClassX = getDataAsDouble(jsonClass, JSON_X);
            double ClassY = getDataAsDouble(jsonClass, JSON_Y);
            String ClassPackage = jsonClass.getString(JSON_PACKAGE);
            newClass.x = ClassX;
            newClass.y = ClassY;
            newClass.setName(Classname);
            newClass.parent = ClassParent;
            newClass.setPackage(ClassPackage);

            //load the method to the array
            JsonArray Methods = jsonClass.getJsonArray(JSON_METHOD);
            for (int m = 0; m < Methods.size(); m++) {
                
                JsonObject jsonMethod = (JsonObject) Methods.get(m);
                Method newMethod = new Method();
                newMethod.Name = jsonMethod.getString(JSON_METHOD_NAME);
                newMethod.Access = jsonMethod.getString(JSON_METHOD_ACCESS);

                newMethod.Abstract = jsonMethod.getBoolean(JSON_METHOD_ABSTRACT);
                newMethod.Return = jsonMethod.getString(JSON_METHOD_RETURN);
                newMethod.Static = jsonMethod.getBoolean(JSON_METHOD_STATIC);
                JsonArray args = jsonMethod.getJsonArray(JSON_METHOD_ARG);

                for (int arg = 0; arg < args.size(); arg++) {
                    newMethod.arg.add(args.getString(arg));
                }
                newClass.methods.add(newMethod);
            }
            //load the all variable in to class
            JsonArray Variables = jsonClass.getJsonArray(JSON_VARIABLE);
            for (int v = 0; v < Variables.size(); v++) {
                JsonObject jsonVariable = (JsonObject) Variables.get(v);
                String variablesName = jsonVariable.getString(JSON_VARIABLE_NAME);
                Variable newVariable = new Variable();
                newVariable.name = variablesName;
                newVariable.Static = jsonVariable.getBoolean(JSON_VARIABLE_STATIC);
                newVariable.type = jsonVariable.getString(JSON_VARIABLE_TYPE);
                newVariable.Access = jsonVariable.getString(JSON_VARIABLE_ACCESS);
                newClass.variables.add(newVariable);
            }
            //load the all improt to the class
            JsonArray imports = jsonClass.getJsonArray(JSON_IMPORT);
            for (int i = 0; i < imports.size(); i++) {
                newClass.improt.add(imports.get(i).toString());
            }
            dataManager.addClass(newClass);
            //System.out.print(newClass.getName());
            //dataManager.MakeTheShap(newClass);
        }
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }

    /**
     * This method exports the contents of the data manager to a Web page
     * including the html page, needed directories, and the CSS file.
     *
     * @param data The data management component.
     *
     * @param filePath Path (including file name/extension) to where to export
     * the page to.
     *
     * @throws IOException Thrown should there be an error writing out data to
     * the file.
     */
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {

    }

    /**
     * This method is provided to satisfy the compiler, but it is not used by
     * this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
	// NOTE THAT THE Web Page Maker APPLICATION MAKES
        // NO USE OF THIS METHOD SINCE IT NEVER IMPORTS
        // EXPORTED WEB PAGES
    }

}
