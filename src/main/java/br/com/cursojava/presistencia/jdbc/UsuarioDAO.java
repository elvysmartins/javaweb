package br.com.cursojava.presistencia.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			e.printStackTrace();
		} 
	}

	public void alterar(Usuario usu) {
		String sql = "update BancoJavaWeb.usuario set nome=?, login=?, senha=md5(?) where id=?";
		
		try {
			PreparedStatement preparador = (PreparedStatement) con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();
			
			preparador.close();
			
		} catch (SQLException e) {
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
			e.printStackTrace();
		} 
		
	}
	
	public void salvar (Usuario usu) {
		if (usu.getId()!=null && usu.getId()!=0){
			alterar(usu);
		} else{
			cadastrar(usu);
		}
	}
	
	public List<Usuario> buscarTodos(){
		
		String sql = "Select * from BancoJavaWeb.usuario order by id";
		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			PreparedStatement preparador = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				lista.add(usuario);
			}
			
            preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;	
	}

	public Usuario buscarPorId (Integer id){
		
		String sql = "Select * from BancoJavaWeb.usuario where id=?";
		Usuario usuario = null;

		try {
			PreparedStatement preparador = (PreparedStatement) con.prepareStatement(sql);
            preparador.setInt(1, id);
            ResultSet resultado = preparador.executeQuery();

            if (resultado.next()){
            	usuario = new Usuario();
    			usuario.setId(resultado.getInt("id"));
    			usuario.setNome(resultado.getString("nome"));
    			usuario.setLogin(resultado.getString("login"));
    			usuario.setSenha(resultado.getString("senha"));            
            }
            
            preparador.close();

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return usuario;
        
	}

	public List<Usuario> buscarPorNome (String nome){
		
		String sql = "Select * from BancoJavaWeb.usuario where nome like ?";
		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			java.sql.PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, "%" + nome + "%");
            ResultSet resultado = (ResultSet) preparador.executeQuery();

            while (resultado.next()){
            	Usuario usuario = new Usuario();
    			usuario.setId(resultado.getInt("id"));
    			usuario.setNome(resultado.getString("nome"));
    			usuario.setLogin(resultado.getString("login"));
    			usuario.setSenha(resultado.getString("senha"));
    			lista.add(usuario);
            }
            preparador.close();
            System.out.println("Consulta por Nome com sucesso!!!");
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        return lista;
        
	}

	/**
	 * Busca login e senha do usuário
	 * @param usuario - Objeto com login e senha a ser consultado
	 * @return null quando não encontra no banco ou um ponteiro a um usuário quando encontra
	 */
	public Usuario autenticar (Usuario usuario){
		
		String sql = "Select * from BancoJavaWeb.usuario where login=? and senha =?";
		Usuario usuarioRetorno = null;

		try {
			java.sql.PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, usuario.getLogin());
            preparador.setString(2, usuario.getSenha());
            ResultSet resultado = preparador.executeQuery();

            if (resultado.next()){
            	usuarioRetorno = new Usuario();
            	usuarioRetorno.setId(resultado.getInt("id"));
            	usuarioRetorno.setNome(resultado.getString("nome"));
            	usuarioRetorno.setLogin(resultado.getString("login"));
            	usuarioRetorno.setSenha(resultado.getString("senha"));            
                System.out.println("Autenticação com sucesso!!!");
            } else{
            	System.out.println("Usuário não encontrado!!!");
            }
            
            preparador.close();

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return usuarioRetorno;
        
	}

	public Boolean existeUsuario (Usuario usuario){
		
		String sql = "Select * from BancoJavaWeb.usuario where login=? and senha =?";
		boolean ret = false;

		try {
			java.sql.PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, usuario.getLogin());
            preparador.setString(2, usuario.getSenha());
            ResultSet resultado = preparador.executeQuery();
            ret = resultado.next();
            preparador.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        return ret;
        
	}
}
