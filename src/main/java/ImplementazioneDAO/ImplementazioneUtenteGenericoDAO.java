package ImplementazioneDAO;

import dao.UtenteGenericoDAO;
import db.ConnessioneDatabase;

import model.Utente;
import model.UtenteGenerico;

import java.sql.*;

public class ImplementazioneUtenteGenericoDAO implements UtenteGenericoDAO {

    private Connection connection;

    public ImplementazioneUtenteGenericoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void signIn(String login, String password, String email) {
        try {
            PreparedStatement saveUtentePS = connection.prepareStatement(
                    "INSERT INTO \"utenti_generici\" " +
                            "(\"login\", \"email\", \"password\") " +
                            "VALUES ('" +
                            login + "', '" +
                            email + "', '" +
                            password + "');"
            );
            saveUtentePS.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public UtenteGenerico logIn(String login, String password){

        UtenteGenerico user=null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM utenti_generici " +
                            "WHERE \"login\" = '" + login + "' " +
                            "AND \"password\" = '" + password + "';"
            );

            if (rs.next())
                user = new UtenteGenerico(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("email"));

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

}
