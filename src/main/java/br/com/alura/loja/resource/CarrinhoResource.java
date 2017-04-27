package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String payload) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(payload);
		new CarrinhoDAO().adiciona(carrinho);
		URI location = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(location).build();
	}

	@Path("{id}/produtos/{id_produto}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("id_produto") long idProduto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(idProduto);
		return Response.ok().build();
	}

	@Path("{id}/produtos/{id_produto}")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response alteraProduto(@PathParam("id") long id, @PathParam("id_produto") long idProduto, String payload) {
		Produto produto = (Produto) new XStream().fromXML(payload);
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.troca(produto);
		return Response.ok().build();
	}

	@Path("{id}/produtos/{id_produto}/quantidade")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response alteraQuantidadeProduto(@PathParam("id") long id, @PathParam("id_produto") long idProduto, String payload) {
		Produto produto = (Produto) new XStream().fromXML(payload);
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	}
}
