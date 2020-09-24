package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.interfaces.IEventoDAO;
import models.Evento;
import utils.conexoes.ConexaoMySql;
import utils.contants.Constants;

/**
 * @author Vitor Hainosz
 * apareceu de repente no inicio vou adedrir
 */
public class EventoDAO implements IEventoDAO {

	@Override
	public void inserir(Evento obj) {
		try {
			Connection connection = ConexaoMySql.getInstance().getConnection();

			String sql = "INSERT INTO " + Constants.NT_TABLE + " (" + Constants.COL1 + ", " + Constants.COL2 + ", "
					+ Constants.COL3 + ") VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getCalendarioString());
			statement.setFloat(3, obj.getValorEntrada());
			statement.execute();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void deletar(int id) {
		try {
			Connection connection = ConexaoMySql.getInstance().getConnection();

			String sql = "DELETE FROM " + Constants.NT_TABLE + " WHERE " + Constants.PK + " = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, id);
			statement.execute();
			statement.close();
			connection.close();

		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void editar(Evento obj) {
		try {
			Connection connection = ConexaoMySql.getInstance().getConnection();

			String sql = "UPDATE " + Constants.NT_TABLE + " SET " + Constants.COL1 + " = ?, " + Constants.COL2
					+ " = ?, " + Constants.COL3 + " = ? WHERE " + Constants.PK + " = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getCalendarioString());
			statement.setFloat(3, obj.getValorEntrada());
			statement.setInt(4, obj.getCod());

			statement.execute();
			statement.close();
			connection.close();

		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<Evento> buscarId(int id) {
		List<Evento> lista = new ArrayList<Evento>();
		try {
			Connection connection = ConexaoMySql.getInstance().getConnection();

			String sql = "SELECT * FROM " + Constants.NT_TABLE + " WHERE " + Constants.PK + " = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Evento evento = new Evento(
						resultSet.getInt(Constants.PK), 
						resultSet.getString(Constants.COL1),
						formatCalendar(resultSet.getDate(Constants.COL2)), 
						resultSet.getFloat(Constants.COL3));
				lista.add(evento);
			}

			statement.close();
			connection.close();
		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Evento> listar() {
		List<Evento> lista = new ArrayList<Evento>();
		try {
			Connection connection = ConexaoMySql.getInstance().getConnection();

			String sql = "SELECT * FROM " + Constants.NT_TABLE;

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Evento evento = new Evento(
						resultSet.getInt(Constants.PK), 
						resultSet.getString(Constants.COL1),
						formatCalendar(resultSet.getDate(Constants.COL2)), 
						resultSet.getFloat(Constants.COL3));
				lista.add(evento);
			}

			statement.close();
			connection.close();
		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return lista;
	}

	private Calendar formatCalendar(Date data) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat Y = new SimpleDateFormat("y");
		SimpleDateFormat M = new SimpleDateFormat("M");
		SimpleDateFormat D = new SimpleDateFormat("d");
		int intY = Integer.parseInt(Y.format(data));
		int intM = Integer.parseInt(M.format(data));
		int intD = Integer.parseInt(D.format(data));
		cal.set(intY, intM, intD);
		return cal;
	}

}
