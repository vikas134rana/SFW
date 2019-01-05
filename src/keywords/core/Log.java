package keywords.core;

import java.util.logging.Logger;

public class Log {

	static Logger log = Logger.getLogger(Log.class.getName());
	
	public static void debug(String msg) {
		log.info(msg);
	}
	
}
