package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de conexão **/
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/" + "dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "1234567";

	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			;
			return null;
		}
	}

	// **CRUD CREATE**/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email)" + "values (?,?,?)";
		try {
			// abrir conexao com banco de dados
			Connection con = conectar();
			// preparar a query para a execuco no banco da dados
			PreparedStatement pst = con.prepareStatement(create);
			// subistituir os parametros(?)
			// pelo conteudo das variaveir JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// executar a query
			pst.executeUpdate();
			// encerrar conexao com o banco de dados
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUDE READ **/
	public ArrayList<JavaBeans> listarcontatos() {
		// criando obj para acessar JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// laco abaixo sera executado enquato haver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem dados de apoio
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// armazenar no vetor dinamico (ArrayList)
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/*
	 * Teste de conexão public void testeConexao() { try { Connection con =
	 * conectar(); System.out.println(con); con.close(); } catch (Exception e) {
	 * System.out.println(e);; } }
	 */
}
