package br.com.cursojava.presistencia.jdbc;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import br.com.cursojava.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection(); 
	
	public void cadastrar(Usuario usu) {
		String sql = "insert into BancoJavaWeb.usuario (nome, login, senha) values (?, ?, md5(?))";
		
		try {
			PreparedStatement preparador = (PreparedStatement) con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void alterar(Usuario usu) {
		String sql = "update BancoJavaWeb.usuario set nome=?, login=?, senha=? where id=?";
		
		try {
			PreparedStatement preparador = (PreparedStatement) con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();
			
			preparador.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public void excluir(Usuario usu) {
		String sql = "delete from BancoJavaWeb.usuario where id=?";
		
		try {
			PreparedStatement preparador = (PreparedStatement) con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());
			
			preparador.execute();
			
			preparador.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
