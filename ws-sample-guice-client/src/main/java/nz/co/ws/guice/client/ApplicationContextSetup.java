package nz.co.ws.guice.client;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApplicationContextSetup extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return null;
	}

}
