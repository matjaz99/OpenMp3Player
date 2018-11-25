package si.matjazcerkvenik.openmp3player.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "invalid id")
public class InvalidIdException extends RuntimeException {
	
	private static final long serialVersionUID = 7170924675232439023L;

}
