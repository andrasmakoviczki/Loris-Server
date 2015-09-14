package edu.elte.hadoop.loris.server.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class AppConfig {
	private String filename = new String("config.properties");
	private InputStream input;
	private OutputStream output;
	private Properties prop;

	public AppConfig() {
		prop = new Properties();

		File f = new File(filename);
		if (!f.exists() && !f.isDirectory()) {
			try {
				PrintWriter writer = new PrintWriter(filename);
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		/*
		 * String tmpPath =
		 * Publisher.class.getProtectionDomain().getCodeSource()
		 * .getLocation().getPath(); if (tmpPath.contains(".")) { path =
		 * tmpPath.substring(0, tmpPath.lastIndexOf('.')) + "-"; filename =
		 * "resources.properties"; } else
		 */
	}

	public AppConfig(String _filename) {
		prop = new Properties();
		filename = _filename;
	}

	public void ChangeProperty(String key, String value) {
		try {
			input = new FileInputStream(filename);
			if (input == null) {
				// TODO: Log error
				System.err.println("Not found: " + filename);
				// return null;
			}
			prop.load(input);
			prop.setProperty(key, value);
			prop.store(new FileOutputStream(filename), null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void ListProperties() {
		Enumeration<?> properties = null;
		try {
			input = new FileInputStream(filename);
			if (input == null) {
				// TODO: Log error
				System.err.println("Not found: " + filename);
			}

			prop.load(input);

			properties = prop.propertyNames();
			while (properties.hasMoreElements()) {
				String key = (String) properties.nextElement();
				String value = prop.getProperty(key);
				System.out.println("Key : " + key + ", Value : " + value);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String ReadProperty(String key) {
		String value = new String();
		try {
			input = new FileInputStream(filename);
			if (input == null) {
				System.err.println("Not found: " + filename);
				return value;
			}
			prop.load(input);

			if (!prop.getProperty(key).equals(null)) {
				value = prop.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return value;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	// BUG: Felülírja az egész filet
	public void WriteProperty(String key, String value) {
		try {
			output = new FileOutputStream(filename);
			prop.setProperty(key, value);
			prop.store(output, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
