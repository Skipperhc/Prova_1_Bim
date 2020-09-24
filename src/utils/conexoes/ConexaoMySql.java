package utils.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {

	//aqui estão as variaveis que iremos apontar
	/*url é o local do banco junto ao nome lá, não cria automaticamente 
	 * e a partir do ? é um problema com timezone que estava tendo, talvez 
	 * vcs tbm tivessem a mesma parada então já deixei ali
	 * root é o nome do usuário, para facilitar a produção, criem um usuário no mysql
	 * tem de ser DBA e ter a mesma senha que a key
	*/
	private String url = "jdbc:mysql://localhost:3306/pv?useTimezone=true&serverTimezone=UTC";
	private String root = "usuario";
	private String key = "123123@senha";
	
	
	private static ConexaoMySql conexaoMySql;
	
	//implementando um singleton basicão, isso é padrão
	public static ConexaoMySql getInstance() {
		if(conexaoMySql == null) conexaoMySql = new ConexaoMySql();
		return conexaoMySql;
	}
	
	//Class.forName é onde aponto para o JDBC, o conector do mysql, deixei ele no libs
	//depois cliquei em cima e fiz build path, virou a conexão
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, root, key);
	}
	
	//teste rápido para ver se cnseguiram se conectar, se aparecer no console a conection, deu certo :D
	public static void main(String[] args) {
		try {
			System.out.println(getInstance().getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
