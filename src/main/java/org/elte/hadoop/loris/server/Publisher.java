package org.elte.hadoop.loris.server;

import javax.xml.ws.Endpoint;

public class Publisher {
   public static void main(String[] args) {
	   System.out.println("---SERVER REMOTE---");
	   String ip = new String("172.31.14.253");
	   ip = "localhost";
   Endpoint.publish("http://"+ ip +":13000/WS/MyService",new MyServiceImpl());
   }
}