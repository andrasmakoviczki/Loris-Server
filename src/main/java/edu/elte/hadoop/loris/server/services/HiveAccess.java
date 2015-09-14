package edu.elte.hadoop.loris.server.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class HiveAccess {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	Logger logger = Logger.getLogger(HiveAccess.class);

	public HiveAccess() {
		String myHadoopIp = new String(
				new AppConfig().ReadProperty("hiveipAddress"));
		String myHadoopPort = new String(new AppConfig().ReadProperty("port"));

		try {
			Class.forName(driverName);

			Connection con = DriverManager.getConnection("jdbc:hive2://"
					+ myHadoopIp + ":10000/default", "hive", "");
			Statement stmt = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
