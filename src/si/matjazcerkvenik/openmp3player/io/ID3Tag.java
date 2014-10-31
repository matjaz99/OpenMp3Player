package si.matjazcerkvenik.openmp3player.io;

import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v1;
import org.farng.mp3.id3.AbstractID3v2;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3File;

public class ID3Tag {
		
	public static Mp3File getMetadata(Mp3File mp3) {
		
		try {
			File sourceFile = new File(mp3.getPath());
			MP3File mp3file = new MP3File(sourceFile);
			
			OContext.getInstance().getLogger().info("ID3Tag:getMetadata(): " + mp3.getPath());

//		ID3v1 tag = mp3file.getID3v1Tag();
//		System.out.println(tag.getAlbum());
//		System.out.println(tag.getArtist());
//		System.out.println(tag.getTitle());
			
			AbstractID3v1 tag = mp3file.getID3v1Tag();
			if (tag != null) {
				OContext.getInstance().getLogger().info("ID3Tag:getMetadata(): parsing ID3v1");
				if (tag.getSongTitle().trim().length() > 0) {
					mp3.setTitle(tag.getSongTitle());
				}
				mp3.setArtist(tag.getLeadArtist());
				mp3.setAlbum(tag.getAlbumTitle());
				mp3.setGenre(tag.getSongGenre());
				mp3.setYear(tag.getYearReleased());
				mp3.setSize(tag.getSize());
				return mp3;
			}
			
			
//		AbstractID3 tag22 = mp3file.getID3v2Tag();
//		System.out.println(tag22.getAlbumTitle());
//		System.out.println(tag22.getSongTitle());
//		System.out.println(tag22.getAuthorComposer());
			
			AbstractID3v2 tag2 = mp3file.getID3v2Tag();
			if (tag2 != null) {
				OContext.getInstance().getLogger().info("ID3Tag:getMetadata(): parsing ID3v2");
				if (tag2.getSongTitle().trim().length() > 0) {
					mp3.setTitle(tag2.getSongTitle());
				}
				mp3.setArtist(tag2.getLeadArtist());
				mp3.setAlbum(tag2.getAlbumTitle());
				mp3.setGenre(tag2.getSongGenre());
				mp3.setYear(tag2.getYearReleased());
				mp3.setSize(tag2.getSize());
				return mp3;
			}
			
			
		} catch (IOException e) {
			OContext.getInstance().getLogger().error("ID3Tag:getMetadata(): " , e);
		} catch (TagException e) {
			OContext.getInstance().getLogger().error("ID3Tag:getMetadata(): " , e);
		} catch (Exception e) {
			OContext.getInstance().getLogger().error("ID3Tag:getMetadata(): " , e);
		}
		
		return mp3;
	}
	
}