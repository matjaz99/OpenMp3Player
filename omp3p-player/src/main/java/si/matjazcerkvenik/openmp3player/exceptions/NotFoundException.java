package si.matjazcerkvenik.openmp3player.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7820250372779505023L;

}
