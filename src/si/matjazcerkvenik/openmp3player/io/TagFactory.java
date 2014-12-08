package si.matjazcerkvenik.openmp3player.io;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.player.Tags;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class TagFactory {

	private SimpleLogger logger = null;

	private static TagFactory factory = null;
	
	private Tags tags = null;

	private TagFactory() {
		logger = OContext.getInstance().getLogger();
	}

	public static TagFactory getInstance() {
		if (factory == null) {
			factory = new TagFactory();
		}
		return factory;
	}

	/**
	 * Read tags.xml
	 */
	public Tags getTags() {

		if (tags != null) {
			return tags;
		}

		try {

			File file = new File(OContext.CFG_DIR + "/tags.xml");
			if (!file.exists()) {
				logger.warn("TagFactory:getTags(): tags.xml not found; creating new");
				tags = new Tags();
				JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(tags, file);

			}
			logger.info("TagFactory:getTags(): tags.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			tags = (Tags) jaxbUnmarshaller.unmarshal(file);
			logger.debug("TagFactory:getTags(): " + tags.toString());

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}

		return tags;

	}

	/**
	 * Save tags.xml
	 */
	public void saveTags() {
		logger.info("TagFactory:saveTags(): saving...");
		try {

			File file = new File(OContext.CFG_DIR + "/tags.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(tags, file);

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}
	}
	
	/**
	 * Return tag according to given name
	 * @param name
	 * @return tag
	 */
	public Tag getTag(String name) {
		
		for (Tag t : tags.getTagList()) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

}
