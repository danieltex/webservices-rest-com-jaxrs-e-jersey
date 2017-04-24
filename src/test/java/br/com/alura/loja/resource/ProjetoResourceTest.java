package br.com.alura.loja.resource;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
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
		URI uri = URI.create("http://localhost:8080");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		String content = target.path("/projetos/1").request().get(String.class);
		System.out.println(content);
		Projeto projeto = (Projeto) new XStream().fromXML(content);
		assertEquals("Minha loja", projeto.getNome());
	}
}
