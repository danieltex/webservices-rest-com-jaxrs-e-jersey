package br.com.alura.loja.resource;

import static org.junit.Assert.assertTrue;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;


public class ProjetoResourceTest {
	@Test
	public void testCanAccessProjetos() {
		URI uri = URI.create("http://localhost:8080");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		String projeto = target.path("/projetos").request().get(String.class);
		System.out.println(projeto);
		assertTrue(projeto.contains("<nome>Minha loja"));
	}
}
