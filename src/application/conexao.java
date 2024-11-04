package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;




public class conexao {
	 String url = "jdbc:mysql://localhost/boatsytem";
     String username = "root";
     String password = "";
     //
     public String nome;
     public String senhaUsuario;
     public int id;
     
     
     public void consultarUsuario(String nomeConsulta) 
     {
		 try {
			 	
			 
	            // Carregar o driver MySQL JDBC (não é necessário nas versões mais recentes)
			 // Carregar o driver MySQL JDBC (não é necessário nas versões mais recentes)
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	            // Conectar ao banco de dados
	            Connection connection = DriverManager.getConnection(url, username, password);
	     
	            
	            Statement statement = connection.createStatement();
	            String sql = "SELECT idUsuario, Nome, senhaUsuario FROM usuario where nome = '"+nomeConsulta+"'";
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            // Fechar a conexão
	            
	            while (resultSet.next()) {
	                int id = resultSet.getInt("idUsuario");        // Obtém o valor da coluna 'id'
	                String nome = resultSet.getString("nome");  // Obtém o valor da coluna 'nome'
	                String email = resultSet.getString("senhaUsuario"); // Obtém o valor da coluna 'email'

	                // Exibe os dados
	                this.nome = nome;
	            }
	            
	            resultSet.close();
	            statement.close();
	            connection.close();
	
	        } catch (ClassNotFoundException e) {
	            System.out.println("Driver não encontrado: " + e.getMessage());
	        } catch (SQLException e) {
	            System.out.println("Erro ao conectar: " + e.getMessage());
	        }
	}
     public void Login(String usuario,String senha) 
     {
		 try {
			 	
			 
	            // Carregar o driver MySQL JDBC (não é necessário nas versões mais recentes)
			 // Carregar o driver MySQL JDBC (não é necessário nas versões mais recentes)
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	            // Conectar ao banco de dados
	            Connection connection = DriverManager.getConnection(url, username, password);
	            
	            Statement statement = connection.createStatement();
	            String sql = "SELECT idUsuario, Nome, senhaUsuario FROM usuario where nome = '"+usuario+"' and senhaUsuario='"+senha+"'";
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            // Fechar a conexão
	            
	            while (resultSet.next()) {
	                int id = resultSet.getInt("idUsuario");        // Obtém o valor da coluna 'id'
	                String nome = resultSet.getString("nome");  // Obtém o valor da coluna 'nome'
	                String senhaUsuario = resultSet.getString("senhaUsuario"); // Obtém o valor da coluna 'email'

	                // Exibe os dados
	                this.id = id;
	                this.nome = nome;
	                this.senhaUsuario = senhaUsuario;
	            }
	            
	            resultSet.close();
	            statement.close();
	            connection.close();
	
	        } catch (ClassNotFoundException e) {
	            System.out.println("Driver não encontrado: " + e.getMessage());
	        } catch (SQLException e) {
	            System.out.println("Erro ao conectar: " + e.getMessage());
	        }
	}
     public void cadastro(String usuario,String senha) 
     {	
    	 
		conexao conect = new conexao();
	      
	   conect.consultarUsuario(usuario);
	   	     
	   if (conect.nome != null) {
		   System.out.println("Nome de Usuario já Cadastrado");
	   }
	   else {
	       
    	 String sql = "INSERT INTO usuario (nome, senhaUsuario) VALUES (?, ?)";

         try {
             // Estabelece a conexão com o banco de dados
             Connection connection = DriverManager.getConnection(url, username, password);

             // Cria um PreparedStatement para executar o comando SQL
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

             // Define os valores a serem inseridos
             preparedStatement.setString(1, usuario);  
             preparedStatement.setString(2, senha); 

             // Executa o comando de inserção
             int linhasAfetadas = preparedStatement.executeUpdate();

             // Exibe o número de linhas afetadas
             System.out.println("Linhas inseridas: " + linhasAfetadas);
             
             conect.Login(usuario,senha);
             //
             this.id = conect.id;
             this.nome = conect.nome;
             this.senhaUsuario = conect.senhaUsuario;
             
             
             // Fecha os recursos
             preparedStatement.close();
             connection.close();

         } catch (SQLException e) {
             System.out.println("Erro ao inserir dados: " + e.getMessage());
         }
	}}
     
     public void CadastrarItem(String remetente,String destinatario,String cpf_cnpj,String Destino,String Descricao,String dataRecebimento,String dataEntreda,Boolean Pago) 
     {	
    	
		conexao conect = new conexao();
	      

    	 String sql = "INSERT INTO estoque (remetente, cpf_cnpj, destinatario,Descricao,  Destino,dataRecebimento,dataEntrega,   ativoPagamento) VALUES (?,?,?,?,?,?,?,?)";

         try {
            
        	 Connection connection = DriverManager.getConnection(url, username, password);

        
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
             preparedStatement.setString(1, remetente); 
             preparedStatement.setString(2, cpf_cnpj);
             preparedStatement.setString(3, destinatario); 
             preparedStatement.setString(4, Descricao);   
             preparedStatement.setString(5, Destino);
             preparedStatement.setString(6, dataRecebimento); 
             preparedStatement.setString(7, dataEntreda); 
             preparedStatement.setBoolean(8, Pago); 

             int linhasAfetadas = preparedStatement.executeUpdate();

             
             System.out.println("Linhas inseridas: " + linhasAfetadas);
                       
             preparedStatement.close();
             connection.close();

         } catch (SQLException e) {
             System.out.println("Erro ao inserir dados: " + e.getMessage());
         }
	}
     
}
