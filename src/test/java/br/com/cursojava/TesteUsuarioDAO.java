package br.com.cursojava;

import br.com.cursojava.persistencia.entidade.Usuario;
import br.com.cursojava.presistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeCadastrar();
		//testeAlterar();
		testeExcluir();
	}

	private static void testeCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Elvys");
		usu.setLogin("elvynho");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso!");		
		
	}

	
	private static void testeAlterar() {
		Usuario usu = new Usuario();
		usu.setId(3);
		usu.setNome("Elvys Alterado");
		usu.setLogin("elvyzão");
		usu.setSenha("567");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso!");	
		
	}

	private static void testeExcluir() {
		Usuario usu = new Usuario();
		usu.setId(3);
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		System.out.println("Excluído com sucesso!");	
	}

}
