package nz.co.ws.sample.integration.ws;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "fault")
public class FaultMessage extends Exception {
	private Fault fault;

	public FaultMessage() {
		super();
	}

	public FaultMessage(String message) {
		super(message);
	}

	public FaultMessage(String message, Throwable cause) {
		super(message, cause);
	}

	public FaultMessage(String message, Fault fault) {
		super(message);
		this.fault = fault;
	}

	public FaultMessage(String message, Fault fault, Throwable cause) {
		super(message, cause);
		this.fault = fault;
	}

	public Fault getFaultInfo() {
		return this.fault;
	}
}
