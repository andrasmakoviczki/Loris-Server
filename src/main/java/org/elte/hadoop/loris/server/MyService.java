package org.elte.hadoop.loris.server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MyService {
    @WebMethod
    String reverser(String value);
    @WebMethod
    ArrayList<String> hadoop();
    @WebMethod
    List<String> hadoopContent();
    @WebMethod
    void LDA();
    @WebMethod
    void SparkSQL();
    
    @WebMethod
    String GetConfigProperty(String key);
    @WebMethod
    void SetConfigProperty(String key, String value);
}
