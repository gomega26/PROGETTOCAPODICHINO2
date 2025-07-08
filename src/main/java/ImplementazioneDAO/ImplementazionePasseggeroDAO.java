package ImplementazioneDAO;

import dao.PasseggeroDAO;
import db.ConnessioneDatabase;
import model.Passeggero;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazionePasseggeroDAO implements PasseggeroDAO {

    private Connection connection;

    public ImplementazionePasseggeroDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int create(String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO passeggeri " +
                            "(\"nome\", \"cognome\", \"num_telefono\", \"num_documento\", \"sesso\", \"data_nascita\") " +
                            "VALUES ('" + nome + "', '" + cognome + "', '" + numTelefono + "', '" +
                            numDocumento + "', '" + sesso + "', '" + dataNascita + "');",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                rs.close();
                ps.close();
                connection.close();
                return id;
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public void modifica(int codicePrenotazione, String nome, String cognome, String numDocumentoPasseggero, char sesso) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE passeggeri SET nome = '" + nome + "', cognome = '" + cognome +
                            "', num_documento = '" + numDocumentoPasseggero + "', sesso = '" + sesso +
                            "' WHERE id IN (SELECT id_passeggero FROM prenotazioni WHERE id = " + codicePrenotazione + ");"
            );
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(ArrayList<Passeggero> passeggeri) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM passeggeri;");

            while (rs.next()) {
                Passeggero p = new Passeggero(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("num_telefono"),
                        rs.getString("num_documento"),
                        rs.getString("sesso").charAt(0),
                        rs.getString("data_nascita")
                );

                passeggeri.add(p);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Passeggero getPerId(int id) {
        Passeggero p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id = " + id + ";"
            );

            if (rs.next()) {
                p = new Passeggero(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("num_telefono"),
                        rs.getString("num_documento"),
                        rs.getString("sesso").charAt(0),
                        rs.getString("data_nascita")
                );
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return p;
    }

}