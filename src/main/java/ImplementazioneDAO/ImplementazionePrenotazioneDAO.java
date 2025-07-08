package ImplementazioneDAO;

import dao.PrenotazioneDAO;
import db.ConnessioneDatabase;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazionePrenotazioneDAO implements PrenotazioneDAO {

    private Connection connection;

    public ImplementazionePrenotazioneDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getPrenotazioniPerUtenteGenerico(int idUser, ArrayList<Prenotazione> prenotazioni) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_utente_generico = " + idUser + ";"
            );

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
                prenotazioni.add(p);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(ArrayList<Prenotazione> prenotazioni) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prenotazioni;");

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
                prenotazioni.add(p);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void create(String posto, String classe, int id_passeggero, String id_volo, int idUser, int numBagagli) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO prenotazioni " +
                            "(\"posto\", \"classe_volo\", \"id_passeggero\", \"codice_volo\", \"id_utente_generico\", \"stato\", \"num_bagagli\") " +
                            "VALUES ('" + posto + "', '" + classe + "', " +
                            id_passeggero + ", '" + id_volo + "', " + idUser + ", 'IN ATTESA', "+numBagagli+");"
            );
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Prenotazione getPerIdUtenteGenerico(int idUser, int codicePrenotazione) {
        Prenotazione p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_utente_generico = " + idUser +
                            " AND id = " + codicePrenotazione + ";"
            );

            if (rs.next()) {
                p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public Prenotazione getPerId(int codicePrenotazione) {
        Prenotazione p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id = " + codicePrenotazione + ";"
            );

            if (rs.next()) {
                p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public String getIdVolo(int idPrenotazione) {
        String idVolo="";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT codice_volo FROM prenotazioni WHERE id = " + idPrenotazione + ";"
            );

            if (rs.next())
                idVolo = rs.getString("codice_volo");

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return idVolo;
    }

    @Override
    public int getIdPasseggero(int idPrenotazione) {

        int idPasseggero=0;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_passeggero FROM prenotazioni WHERE id = " + idPrenotazione + ";"
            );

            if (rs.next())
                idPasseggero = rs.getInt("id_passeggero");

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return idPasseggero;
    }

    @Override
    public void checkIn(int idPrenotazione, int numBiglietto) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE prenotazioni SET stato = 'CONFERMATA', num_biglietto = " + numBiglietto +
                            " WHERE id = " + idPrenotazione + ";"
            );
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modifica(int codicePrenotazione, String posto, String classeVolo, int numBagagli) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE prenotazioni SET posto = '" + posto + "', classe_volo = '" + classeVolo + "', num_bagagli = " + numBagagli+
                            "WHERE id = " + codicePrenotazione + ";"
            );
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int getIdPerPasseggero(int idPasseggero){

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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return idPrenotazione;
    }
}
