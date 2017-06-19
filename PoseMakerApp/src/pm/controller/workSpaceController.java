/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.controller;

import pm.data.DataManager;
import saf.AppTemplate;

/**
 *
 * @author Xiangbin
 */
public class workSpaceController {
         AppTemplate app;
         DataManager dataManager;
         
         
     public workSpaceController(AppTemplate initApp) {
	app = initApp;
	dataManager = (DataManager)app.getDataComponent();
    }
       
     
     
    
}
