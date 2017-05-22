package br.com.cursojava;

import br.com.cursojava.persistencia.entidade.Usuario;
import br.com.cursojava.presistencia.jdbc.UsuarioDAO;


public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeCadastrar();
		//testeAlterar();
		//testeExcluir();
		//testeSalvar();
		//testeBuscarPorId();
		testeBuscarTodos();
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

	private static void testeSalvar() {
		Usuario usu = new Usuario();
		usu.setId(2);
		usu.setNome("Nome alterado 2");
		usu.setLogin("loginalterado 2");
		usu.setSenha("senhanova2");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("Salvo com sucesso!");		

		Usuario usunovo = new Usuario();
		usunovo.setId(0);
		usunovo.setNome("Nome incluido");
		usunovo.setLogin("loginincluido");
		usunovo.setSenha("senhaincluida");
		
		UsuarioDAO usuDAOnovo = new UsuarioDAO();
		usuDAOnovo.salvar(usunovo);
		
		System.out.println("Salvo com sucesso!");		
		
	}
	
	private static void testeBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		java.util.List<Usuario> listaResultado = usuarioDAO.buscarTodos();

		if (listaResultado.isEmpty()){
			System.out.println("Não existem dados para listar!!!");	
		} else{ 		
			for (Usuario usuario: listaResultado){
				System.out.println("Consulta todos com sucesso!!!");			
				System.out.println(usuario);	

			}
		}
		
	}	
	
	private static void testeAutenticar () {
		Usuario usuario = new Usuario();
		usuario.setLogin("elvynho");
		usuario.setSenha("123");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
    	System.out.println(usuarioDAO.autenticar(usuario));
	}

	private static void testeBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(6);
		if  (usuario != null){
			System.out.println(usuario);	
		} else{
			System.out.println("Id não encontrado");
		}
		
	}

}
