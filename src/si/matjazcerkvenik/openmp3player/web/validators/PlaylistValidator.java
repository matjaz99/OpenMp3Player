package si.matjazcerkvenik.openmp3player.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.PlaylistDAO;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.openmp3player.player.Playlists;

@FacesValidator(value="playlistValidator")
public class PlaylistValidator implements Validator {
	
	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object value)
			throws ValidatorException {
		
		String pName = (String) value;
		OContext.getInstance().getLogger().info("PlaylistValidator:validate(): new name: " + pName);
		
		Playlists playlists = PlaylistDAO.getInstance().getPlaylists();
		
		for (Playlist p : playlists.getPlist()) {
			if (p.getName().equals(pName) || p.getName().equals("Queue")) {
				
				OContext.getInstance().getLogger().warn("PlaylistValidator:validate(): already exist!");
				
				FacesMessage message = new FacesMessage();
				message.setDetail("Playlist with name " + pName + " already exist");
				message.setSummary("Forbidden");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
				
			}
		}
		
	}
	
}
