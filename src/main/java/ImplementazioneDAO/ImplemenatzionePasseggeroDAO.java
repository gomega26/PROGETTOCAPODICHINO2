package ImplementazioneDAO;

import dao.PasseggeroDAO;
import db.ConnessioneDatabase;
import model.Passeggero;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazionePasseggeroDAO implements PasseggeroDAO {

    private Connection connection;
    private static int id = 0;

    public ImplementazionePasseggeroDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int create(String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO passeggeri " +
                            "(\"id\", \"nome\", \"cognome\", \"telefono\", \"documento\", \"sesso\", \"data_nascita\") " +
                            "VALUES (" + id + ", '" + nome + "', '" + cognome + "', '" + numTelefono + "', '" +
                            numDocumento + "', '" + sesso + "', '" + dataNascita + "');"
            );
            ps.executeUpdate();
            connection.close();
            id++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void modifica(int codicePrenotazione, String nome, String cognome, String numDocumentoPasseggero, char sesso) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE passeggeri SET nome = '" + nome + "', cognome = '" + cognome +
                            "', documento = '" + numDocumentoPasseggero + "', sesso = '" + sesso +
                            "' WHERE id IN (SELECT id_passeggero FROM prenotazioni WHERE id = " + codicePrenotazione + ");"
            );
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
    }

    @Override
    public int getIdPrenotazione(int idPasseggero) {
        int idPrenotazione = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id FROM prenotazioni WHERE id_passeggero = " + idPasseggero + ";"
            );

            if (rs.next()) {
                idPrenotazione = rs.getInt("id");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPrenotazione;
    }
}