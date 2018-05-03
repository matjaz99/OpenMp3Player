package si.matjazcerkvenik.openmp3player;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import si.matjazcerkvenik.simplelogger.SimpleLogger;

@Component
public class LogUtil {
	
	private SimpleLogger logger;
	
	@Value("${simplelogger.filename}")
    private String logFile;
	
	@Value("${simplelogger.level}")
    private String logLevel;
	
	public void initLogger() {
		
		logger = new SimpleLogger(logFile);
		
	}

	public SimpleLogger getLogger() {
		return logger;
	}
	
	
	
}
