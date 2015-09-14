package edu.elte.hadoop.loris.server.downloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ChannelConfig {

	// private String path = null;
	private String filename = new String("channel.xml");

	public ChannelConfig() {

		File f = new File(filename);
		if (!f.exists()) {
			try {
				PrintWriter w = new PrintWriter(filename);
				w.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void DeleteChannelFile(String channelAddress) {
		RSSChannels config = new RSSChannels();
		File file = new File(filename);
		JAXBContext jaxbContext = null;
		Marshaller jaxbMarshaller = null;

		try {
			jaxbContext = JAXBContext.newInstance(RSSChannels.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			config = (RSSChannels) jaxbContext.createUnmarshaller().unmarshal(
					file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			config.getRssChannels().remove(
					config.getRssChannels().stream()
							.filter(x -> x.getAddress().equals(channelAddress))
							.findFirst().get());
		} catch (NoSuchElementException e1) {
			return;
			// e1.printStackTrace();
		}

		try {
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// jaxbMarshaller.marshal(config, System.out);
			jaxbMarshaller.marshal(config, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public RSSChannels ListChannels() {
		File file = new File(filename);
		JAXBContext jaxbContext = null;

		try {
			jaxbContext = JAXBContext.newInstance(RSSChannels.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			return (RSSChannels) jaxbContext.createUnmarshaller().unmarshal(
					file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}

	}

	public RSSChannel ReadChannelFile(String channelAddress) {
		RSSChannels config;
		File file = new File(filename);
		JAXBContext jaxbContext = null;

		try {
			jaxbContext = JAXBContext.newInstance(RSSChannels.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			config = (RSSChannels) jaxbContext.createUnmarshaller().unmarshal(
					file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}

		try {
			return config.getRssChannels().stream()
					.filter(x -> x.getAddress().equals(channelAddress))
					.findFirst().get();
		} catch (NoSuchElementException e1) {
			return null;
			// e1.printStackTrace();
		}

	}

	public void UpdateChannelFile(RSSChannel channel) {
		RSSChannels config = new RSSChannels();
		File file = new File(filename);
		JAXBContext jaxbContext = null;
		Marshaller jaxbMarshaller = null;

		try {
			jaxbContext = JAXBContext.newInstance(RSSChannels.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			config = (RSSChannels) jaxbContext.createUnmarshaller().unmarshal(
					file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			config.getRssChannels().stream()
					.filter(x -> x.getAddress().equals(channel.getAddress()))
					.findFirst().get().setRSSChannel(channel);
		} catch (NoSuchElementException e1) {
			return;
			// e1.printStackTrace();
		}

		try {
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// jaxbMarshaller.marshal(config, System.out);
			jaxbMarshaller.marshal(config, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void WriteChannelFile(String RSSChannelName) {
		RSSChannelBuild channelBuild = new RSSChannelBuild(RSSChannelName);
		RSSChannels config = null;
		RSSChannel channel = channelBuild.getChannel();

		File file = new File(filename);
		JAXBContext jaxbContext = null;
		Marshaller jaxbMarshaller = null;

		try {
			jaxbContext = JAXBContext.newInstance(RSSChannels.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			config = (RSSChannels) jaxbContext.createUnmarshaller().unmarshal(
					file);
		} catch (JAXBException e) {
			config = new RSSChannels();
		}
		try {
			config.getRssChannels().add(channel);
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// jaxbMarshaller.marshal(config, System.out);
			jaxbMarshaller.marshal(config, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
