package webapp;


import webapp.server.Server;
import webapp.server.TomcatServer;

public class Main {
	public static void main(String[] args) {
		Server app = new TomcatServer();
		app.run(args);
	}
}
