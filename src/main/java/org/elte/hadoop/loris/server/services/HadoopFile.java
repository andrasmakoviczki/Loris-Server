package org.elte.hadoop.loris.server.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.mapreduce.Job;

import org.apache.log4j.Logger;

public class HadoopFile {
	
	final static Logger logger = Logger.getLogger(HadoopFile.class);
	
	public void hdfsls(ArrayList<String> al, String in, List<String> cont)
	{
		String line = new String();
		line = "OK";
		System.out.print(line);
		
		try{
			String ip = new String("172.31.14.253");
			Configuration conf = new Configuration();
			Job.getInstance(conf);
			conf.set("hadoop.job.ugi", "ubuntu");
			conf.set("fs.defaultFS", "hdfs://" + ip + ":8020");
			
			FileSystem fs = FileSystem.get(conf);
			
			//list
			RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs
					.listFiles(new Path(
							"hdfs://" + ip + ":8020/user/ubuntu"),
							true);
			
			while (fileStatusListIterator.hasNext()) {
				LocatedFileStatus fileStatus = fileStatusListIterator
						.next();
				al.add(fileStatus.getPath().toString());
			}
			
			//content
			/*Path pt=new Path(in);
	        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
	        
	        line=br.readLine();
	        cont.add(line);
	        
	        while (line != null){
	                System.out.println(line);
	                line=br.readLine();
	                cont.add(line);
	        } */

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	}
}
