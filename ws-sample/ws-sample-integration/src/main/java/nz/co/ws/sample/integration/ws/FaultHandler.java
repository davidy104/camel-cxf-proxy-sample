package nz.co.ws.sample.integration.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FaultHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(FaultHandler.class);

	public FaultMessage createFault(final Exception exception) {
		LOGGER.debug("createFault start:{}", exception.getMessage());
		LOGGER.debug("exception:{}", exception);
		FaultMessage fault = new FaultMessage(exception.getMessage());
		fault.setStackTrace(exception.getStackTrace());
		return fault;
	}
}
