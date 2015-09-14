package edu.elte.hadoop.loris.server;

import javax.xml.ws.Endpoint;

import edu.elte.hadoop.loris.server.services.AppConfig;

public class Publisher {
	public static void main(String[] args) {
		System.out.println("---SERVER REMOTE---");

		String ip = new String(new AppConfig().ReadProperty("serveripaddress"));
		String port = new String(new AppConfig().ReadProperty("serverport"));

		if (ip.isEmpty() || ip.equals(null)) {
			 new AppConfig().ChangeProperty("serveripaddress", "localhost");
			//new AppConfig().ChangeProperty("serveripaddress", "172.31.14.253");
			ip = new String(new AppConfig().ReadProperty("serveripaddress"));
		}
		if (port.isEmpty() || ip.equals(null)) {
			new AppConfig().ChangeProperty("serverport", "13500");
			port = new String(new AppConfig().ReadProperty("serverport"));
		}
		ip = "localhost";

		Endpoint.publish("http://" + ip + ":" + port + "/WS/MyService",
				new MyServiceImpl());

		/*
		 * MyServiceImpl m = new MyServiceImpl();
		 * System.out.println(m.getRSS("http://index.hu/24ora/rss"));
		 */
	}
}