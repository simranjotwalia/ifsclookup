package simran.api.start;


import java.util.HashSet;
import java.util.Set;
 
import javax.ws.rs.core.Application;

import simran.api.ifscLookup.resources.BankNameResource;
 
public class ResourceLoader extends Application{
 
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        
        // register root resource
        classes.add(BankNameResource.class);
        return classes;
    }
}