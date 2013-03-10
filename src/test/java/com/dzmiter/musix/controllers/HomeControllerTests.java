package com.dzmiter.musix.controllers; 

import com.dzmiter.musix.controllers.HomeController; 
import static org.junit.Assert.*; 
import org.junit.Test; 

public class HomeControllerTests { 

    @Test 
    public void testhome() throws Exception { 
    	HomeController controller = new HomeController(); 
        String view = controller.home(null); 
        assertEquals("home", view); 
    } 
}