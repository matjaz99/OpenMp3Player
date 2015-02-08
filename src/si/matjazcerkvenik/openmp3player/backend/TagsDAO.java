package si.matjazcerkvenik.openmp3player.backend;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.player.Tags;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class TagsDAO {

	private SimpleLogger logger = null;

	private static TagsDAO dao = null;
	
	private Tags tags = null;

	private TagsDAO() {
		logger = OContext.getInstance().getLogger();
		// load tags
		getTags();
	}

	public static TagsDAO getInstance() {
		if (dao == null) {
			dao = new TagsDAO();
		}
		return dao;
	}

	/**
	 * Return all tags
	 */
	public Tags getTags() {

		if (tags == null) {
			loadTags();
		}
		
		return tags;

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
	
	/**
	 * Add new tag and save tags.xml
	 * @param t
	 */
	public void addTag(Tag t) {
		tags.addTag(t);
		saveTags();
	}
	
	/**
	 * Delete tag and save tags.xml
	 * @param t
	 */
	public void deleteTag(Tag t) {
		tags.removeTag(t);
		saveTags();
		logger.info("AddTagBean:deleteTag(): delete " + t.getName());
	}
	
	
	
	
	/**
	 * Read from tags.xml file
	 */
	private void loadTags() {
		try {

			File file = new File(OContext.CFG_DIR + "/tags.xml");
			if (!file.exists()) {
				logger.warn("TagsDAO:loadTags(): tags.xml not found; creating new");
				tags = new Tags();
				JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(tags, file);

			}
			logger.info("TagsDAO:loadTags(): tags.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			tags = (Tags) jaxbUnmarshaller.unmarshal(file);
			logger.trace("TagsDAO:loadTags(): " + tags.toString());

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}
	}
	

	/**
	 * Save to file tags.xml
	 */
	private void saveTags() {
		logger.info("TagsDAO:saveTags(): saving...");
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
	
	

}
