package org.elte.hadoop.loris.server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MyService {
    @WebMethod
    String reverser(String value);
    
    ArrayList<String> hadoop();
    
    List<String> hadoopContent();
}
