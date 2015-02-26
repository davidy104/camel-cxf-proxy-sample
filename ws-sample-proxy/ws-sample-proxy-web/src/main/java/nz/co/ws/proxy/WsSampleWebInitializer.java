package nz.co.ws.proxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WsSampleWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfiguration.class);
		ServletRegistration.Dynamic cxfServletDispatcher = servletContext
				.addServlet("CXFServlet", CXFServlet.class);
		cxfServletDispatcher.addMapping("/ws/*");
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}
}
