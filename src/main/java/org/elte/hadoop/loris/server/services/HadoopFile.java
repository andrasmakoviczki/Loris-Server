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
	Logger logger = Logger.getLogger(HadoopFile.class);
	public void hdfsls(ArrayList<String> al, String in, List<String> cont)
	{
		String line = new String();
		
		try{
			Configuration conf = new Configuration();
			Job.getInstance(conf);
			conf.set("hadoop.job.ugi", "ubuntu");
			conf.set("fs.defaultFS", "hdfs://localhost:9000");

			FileSystem fs = FileSystem.get(conf);

			RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs
					.listFiles(new Path(
							"hdfs://localhost:9000/user/ubuntu"),
							true);
			
			
			while (fileStatusListIterator.hasNext()) {
				LocatedFileStatus fileStatus = fileStatusListIterator
						.next();
				al.add(fileStatus.getPath().toString());
				//System.out.println(fileStatus.getPath());
			}
			
			Path pt=new Path(in);
	        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
	        
	        line=br.readLine();
	        cont.add(line);
	        
	        
	        while (line != null){
	                System.out.println(line);
	                line=br.readLine();
	                cont.add(line);
	        } 

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	}
}