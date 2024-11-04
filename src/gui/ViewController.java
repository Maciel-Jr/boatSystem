package gui;

import java.sql.Date;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import application.conexao;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.PasswordField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.Connection;
import java.sql.DriverManager;


public class ViewController {

	@FXML
	private TextField txtLogin, txtLoginCadastro ;
	
	@FXML
	private TextField  txtIntemDataEntrega;

	@FXML
	private TextField  txtIntemCpf_cnpj;

	
	@FXML
	private TextField  txtIntemDescricao;
	
	
	@FXML
	private TextField  txtIntemDestino;
	
	@FXML
	private TextField txtIntemRemetente, txtIntemDestinatario;
	
	@FXML
	private TextField txtIntemDataRecebimento;

	@FXML
	private PasswordField txtSenha, txtSenhaCadastro;

	@FXML
	private Button btTest, btCadastro, btCadastroVW, btCadastroIntem;

	@FXML
	private Label lblLoginInvalido, lblCadastroInvalido;

	@FXML
	private MenuItem menuItemListagem;

	@FXML
	private MenuItem menuItemExcluir;

	@FXML
	private MenuItem menuItemEditar;
	

	@FXML
	private MenuItem menuItemCadastrar;

	@FXML
	private VBox mainVBox;
	
	@FXML
	private CheckBox cbItenAtivoPago;
	

	@FXML
	private ObservableList<itens> usuarioList = FXCollections.observableArrayList();

	public static application.conexao Login;
	public static application.conexao cadastro;
	public static application.conexao CadastrarItem;
	
	public class ConexaoBD {
		public static Connection conectar() throws Exception {
			String url = "jdbc:mysql://localhost/boatsytem";
			String usuario = "root";
			String senha = "";

			return DriverManager.getConnection(url, usuario, senha);
		}
	}

	

//	public static application.conexao Login;

	/*
	 * private void loadView(String absoluteName) { try { FXMLLoader loader = new
	 * FXMLLoader(getClass().getResource(absoluteName)); VBox newBox =
	 * loader.load(); } catch(IOException e) { Alert.showAlert("IO Exception",
	 * "Error Load View", e.getMessage(), A); }
	 * 
	 * }
	 */

	@FXML
	public void onBtTestAction() {
		application.conexao conect = new application.conexao();
		conect.Login(txtLogin.getText(), txtSenha.getText());
		if (conect.id == 0) {
			lblLoginInvalido.setText("Login ou Senha Inválidos");
		} else {
			lblLoginInvalido.setText("");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/almoxorifado.fxml"));
				Parent root = loader.load();

				// Obtém a Stage atual e define a nova cena
				Stage stage = (Stage) btTest.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();

				// Opcional: exibir uma mensagem de erro em um alerta se ocorrer um problema
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Não foi possível carregar a nova tela");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}

		}

	}

	@FXML
	public void onBtCadastroAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Cadastro.fxml"));
			Parent root = loader.load();

			// Obtém a Stage atual e define a nova cena
			Stage stage = (Stage) btTest.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();

			// Opcional: exibir uma mensagem de erro em um alerta se ocorrer um problema
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Não foi possível carregar a nova tela");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

	}

	@FXML
	public void onBtCadastrarAction() {
		application.conexao conect = new application.conexao();
		conect.cadastro(txtLoginCadastro.getText(), txtSenhaCadastro.getText());
		System.out.println(conect.nome);
		if (conect.nome == null) {
			lblCadastroInvalido.setText("nome de Login já cadastrado");
		} else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/View.fxml"));
				Parent root = loader.load();

				// Obtém a Stage atual e define a nova cena
				Stage stage = (Stage) btCadastroVW.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();

				// Opcional: exibir uma mensagem de erro em um alerta se ocorrer um problema
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Não foi possível carregar a nova tela");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}

	}

	public void onMenuItemListagemAction() {
		try {
			// Carrega o novo conteúdo para o VBox
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainViewController.fxml"));
			Parent listagemContent = loader.load(); // Use Parent em vez de VBox

			// Limpa o conteúdo existente e adiciona o novo conteúdo
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(listagemContent);
			
		} catch (IOException e) {
			e.printStackTrace();

			// Exibe uma mensagem de erro caso ocorra uma exceção
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Não foi possível carregar a tela de listagem");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	public void onMenuItemExcluirAction() {
		System.out.println("Excluir");
	}

	public void onMenuItemEditarAction() {
		System.out.println("editar");
	}

	public void onMenuItemCadastrarAction() {
		try {
			// Carrega o novo conteúdo para o VBox
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Inserir.fxml"));
			Parent listagemContent = loader.load(); // Use Parent em vez de VBox
			// Limpa o conteúdo existente e adiciona o novo conteúdo
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(listagemContent);
			
		} catch (IOException e) {
			e.printStackTrace();

			// Exibe uma mensagem de erro caso ocorra uma exceção
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Não foi possível carregar a tela de listagem");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	public void onbtCadastroIntemAction() {	
		

		application.conexao conect = new application.conexao();
		System.out.println(txtIntemRemetente.getText());
		//conect.CadastrarItem(txtIntemRemetente.getText(),txtIntemDestinatario.getText(),txtIntemCpf_cnpj.getText(),txtIntemDestino.getText(),txtIntemDescricao.getText(),txtIntemDataRecebimento.getText(), txtIntemDataEntrega.getText(),false);	
		
		
		
	}
	public class itens {
		
	}
}
