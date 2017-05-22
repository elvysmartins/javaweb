package br.com.cursojava.presistencia.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoFactory {

    public static String status = "STATUS ---> N�o conectado";
    
    public ConexaoFactory(){}
    
	public static Connection getConnection() {
		Connection conexao = null;

        try {
		    Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/BancoJavaWeb";
			String usuario = "root";
			String senha = "1234";
			conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
			
	        if (conexao != null){
	        	System.out.println("Conex�o OK!!!");
	            status = ("STATUS ---> Conectado com sucesso");
	         } else{
	            //JOptionPane.showMessageDialog(null, "N�o foi poss�vel realizar conex�o!");  
	            status = ("STATUS ---> N�o foi poss�vel realizar conex�o");
	         }		
	        
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel conectar: " + e.getMessage());
			status = e.getMessage();
			throw new RuntimeException();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver n�o encontrado: " + e.getMessage());
			status = e.getMessage();
			e.printStackTrace();
		}
       
       return conexao;
	}

}
