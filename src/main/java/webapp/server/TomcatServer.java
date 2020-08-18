package webapp.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class TomcatServer implements Server {


	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 8080;
	private static final String DEFAULT_CONTEXT_PATH = "/app";
	private static final String DOC_BASE = ".";
	private static final String ADDITION_WEB_INF_CLASSES = "target/classes";
	private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
	private static final String INTERNAL_PATH = "/";

	@Override
	public void run(String[] args) {
		int port = port(args);
		Tomcat tomcat = tomcat(port);

		try {
			tomcat.start();
		} catch (LifecycleException exception) {
			System.exit(1);
		}
		tomcat.getServer().await();
	}

	private int port(String[] args) {
		if (args.length > 0) {
			String port = args[0];
			try {
				return Integer.parseInt(port);
			} catch (NumberFormatException ignored) {
			}
		}

		return DEFAULT_PORT;
	}

	private Tomcat tomcat(int port) {
		Tomcat tomcat = new Tomcat();
		tomcat.setHostname(DEFAULT_HOST);
		tomcat.getHost().setAppBase(DOC_BASE);
		tomcat.setPort(port);
		tomcat.getConnector();
		context(tomcat);

		return tomcat;
	}

	private void context(Tomcat tomcat) {
		Context context = tomcat.addWebapp(DEFAULT_CONTEXT_PATH, DOC_BASE);
		File classes = new File(ADDITION_WEB_INF_CLASSES);
		String base = classes.getAbsolutePath();
		WebResourceRoot resources = new StandardRoot(context);
		resources.addPreResources(new DirResourceSet(resources, WEB_APP_MOUNT, base, INTERNAL_PATH));
		context.setResources(resources);

	}
}
