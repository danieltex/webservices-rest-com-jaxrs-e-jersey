package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	public static void main(String[] args) throws IOException {
		HttpServer server = start();
		System.in.read();
		server.stop();
	}

	public static HttpServer start() {
		URI uri = URI.create("http://localhost:8080");
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Rodando em " + uri);
		return server;
	}
}
