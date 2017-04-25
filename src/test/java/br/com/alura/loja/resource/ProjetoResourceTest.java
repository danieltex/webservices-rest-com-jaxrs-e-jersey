package br.com.alura.loja.resource;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Projeto;

public class ProjetoResourceTest {
	private HttpServer server;

	@Before
	public void setup() {
		server = Servidor.start();
	}

	@After
	public void stop() {
		server.stop();
	}

	@Test
	public void testCanAccessProjetos() {
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(URI.create("http://localhost:8080"));
		String content = target.path("/projetos/1").request().get(String.class);
		Projeto projeto = (Projeto) new XStream().fromXML(content);
		assertEquals("Minha loja", projeto.getNome());
	}
}
