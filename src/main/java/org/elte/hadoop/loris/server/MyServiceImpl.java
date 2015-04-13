package org.elte.hadoop.loris.server;


import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.elte.hadoop.loris.server.services.HadoopFile;
import org.elte.hadoop.loris.server.services.WordCount2;

@WebService(endpointInterface = "org.elte.hadoop.loris.server.MyService")
public class MyServiceImpl implements MyService {
	
    public String reverser(String value) {
        if (value == null) return null;
        return new StringBuffer(value).reverse().toString();
    }
    
    public ArrayList<String> hadoop()
    {
    	ArrayList<String> al = new ArrayList<String>();
    	String s = null;
    	List<String> cont = null;
    	HadoopFile h = new HadoopFile();
    	
    	h.hdfsls(al,s,cont);
    	
    	return al;
    }
    
    public List<String> hadoopContent()
    {
    	ArrayList<String> al = new ArrayList<String>();
    	
    	String filename = new String("hello_text");
    	String appender = new String("/part-r-00000");
    	
    	String in = new String("hdfs://localhost:9000/user/ubuntu/" + filename);
    	String out = new String("hdfs://localhost:9000/user/ubuntu/" + filename + "_out");

    	List<String> cont = new ArrayList<String>();
    	HadoopFile h = new HadoopFile();
    	WordCount2 wc = new WordCount2();
    	
    	
    	try {
			wc.WordCount(in, out);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
    	
    	out = out + appender;
    		
    	h.hdfsls(al,out,cont);
    	
    	return cont;
    }
}

