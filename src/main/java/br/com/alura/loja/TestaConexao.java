package br.com.alura.loja;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory conexao = new ConnectionFactory();
		Connection connection = conexao.recuperarConexao();

		listaProdutos(connection);
		insereProduto(connection);

		System.out.println("Fechando conexão! !");

		connection.close();
	}

	private static void listaProdutos(Connection connection) throws SQLException {

		Statement stm = connection.createStatement();

		stm.execute(" SELECT ID, NOME, DESCRICAO FROM produto");

		ResultSet rst = stm.getResultSet();

		while (rst.next()) {
			Integer id = rst.getInt("ID");
			System.out.println(id);
			String nome = rst.getString("NOME");
			System.out.println(nome);
			String descricao = rst.getString("DESCRICAO");
			System.out.println(descricao);
		}
	}

	private static void insereProduto(Connection connection) throws SQLException {

		Statement stm = connection.createStatement();

		stm.execute(" INSERT INTO produto (nome, descricao) values ('Mouse','Mouse sem fio')",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet rst = stm.getGeneratedKeys();
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id retornado foi: " + id);
		}
	}

}
