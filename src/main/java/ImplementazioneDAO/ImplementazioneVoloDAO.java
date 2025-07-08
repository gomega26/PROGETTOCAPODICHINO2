package ImplementazioneDAO;

import dao.VoloDAO;
import db.ConnessioneDatabase;
import model.Volo;
import model.StatoVolo;
import model.VoloInArrivo;
import model.VoloInPartenza;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneVoloDAO implements VoloDAO {

    private Connection connection;

    public ImplementazioneVoloDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(ArrayList<Volo> voli) { //FATTO

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli;");

            while (rs.next()) {

                String tipologia = rs.getString("tipologia");
                String origine = rs.getString("origine");
                String destinazione = rs.getString("destinazione");

                Volo v = null;

                if ("InPartenza".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(origine)) {
                    v = new VoloInPartenza();
                } else if ("InArrivo".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(destinazione)) {
                    v = new VoloInArrivo();
                }

                if (v != null) {
                    v.setCodice(rs.getString("codice"));
                    v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                    v.setOrigine(origine);
                    v.setDestinazione(destinazione);
                    v.setDataPartenza(rs.getString("data"));
                    v.setOrarioPartenza(rs.getString("orario_partenza"));
                    v.setOrarioArrivo(rs.getString("orario_arrivo"));
                    v.setDurata(rs.getString("durata"));
                    v.setRitardo(rs.getInt("ritardo"));
                    v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                    voli.add(v);
                }
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getVoliInPartenza(ArrayList<VoloInPartenza> voli) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli WHERE tipologia = 'InPartenza';");

            while (rs.next()) {

                VoloInPartenza v=new VoloInPartenza();

                v.setCodice(rs.getString("codice"));
                v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                v.setOrigine(rs.getString("origine"));
                v.setDestinazione(rs.getString("destinazione"));
                v.setDataPartenza(rs.getString("data"));
                v.setOrarioPartenza(rs.getString("orario_partenza"));
                v.setOrarioArrivo(rs.getString("orario_arrivo"));
                v.setDurata(rs.getString("durata"));
                v.setRitardo(rs.getInt("ritardo"));
                v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                voli.add(v);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Volo getVoloPerId(String codiceVolo) { //FATTO
        Volo v = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli WHERE codice = '" + codiceVolo + "';");

            if (rs.next()) {
                String tipologia = rs.getString("tipologia");
                String origine = rs.getString("origine");
                String destinazione = rs.getString("destinazione");

                if ("IN PARTENZA".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(origine)) {
                    v = new VoloInPartenza();
                } else if ("IN ARRIVO".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(destinazione)) {
                    v = new VoloInArrivo();
                }

                if (v != null) {
                    v.setCodice(rs.getString("codice"));
                    v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                    v.setOrigine(origine);
                    v.setDestinazione(destinazione);
                    v.setDataPartenza(rs.getString("data"));
                    v.setOrarioPartenza(rs.getString("orario_partenza"));
                    v.setOrarioArrivo(rs.getString("orario_arrivo"));
                    v.setDurata(rs.getString("durata"));
                    v.setRitardo(rs.getInt("ritardo"));
                    v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                }
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void create(String compagniaAerea, String codice, String origine, String destinazione,
                       String orarioPartenza, String orarioArrivo, String dataPartenza,
                       String durata, int ritardo, String statoVoloString) { //FATTO


        try {
            Statement stmt = connection.createStatement();
            String tipologia = "";

            if (origine.equalsIgnoreCase("Napoli")) {
                tipologia = "InPartenza";
            } else if (destinazione.equalsIgnoreCase("Napoli")) {
                tipologia = "InArrivo";
            }

            String query = "INSERT INTO voli (codice, compagnia_aerea, origine, destinazione, orario_partenza, orario_arrivo, data, durata, ritardo, stato, tipologia) " +
                    "VALUES ('" + codice + "', '" + compagniaAerea + "', '" + origine + "', '" + destinazione + "', '" + orarioPartenza + "', '" + orarioArrivo + "', '" + dataPartenza + "', '" + durata + "', " + ritardo + ", '" + statoVoloString + "', '" + tipologia + "');";

            stmt.executeUpdate(query);
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getVoliPerAmministratore(int idUser, ArrayList<Volo> voli) { //FATTO
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM voli WHERE codice IN (SELECT id_volo FROM gestione WHERE id_amministratore = " + idUser + ");"
            );

            while (rs.next()) {
                String tipologia = rs.getString("tipologia");
                String origine = rs.getString("origine");
                String destinazione = rs.getString("destinazione");

                Volo v = null;

                if ("IN PARTENZA".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(origine)) {
                    v = new VoloInPartenza();
                } else if ("IN ARRIVO".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(destinazione)) {
                    v = new VoloInArrivo();
                }

                if (v != null) {
                    v.setCodice(rs.getString("codice"));
                    v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                    v.setOrigine(origine);
                    v.setDestinazione(destinazione);
                    v.setDataPartenza(rs.getString("data"));
                    v.setOrarioPartenza(rs.getString("orario_partenza"));
                    v.setOrarioArrivo(rs.getString("orario_arrivo"));
                    v.setDurata(rs.getString("durata"));
                    v.setRitardo(rs.getInt("ritardo"));
                    v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                    voli.add(v);
                }
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void setGate(String codiceVolo, int gate) { //FATTO
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE voli SET gate = " + gate + " WHERE codice = '" + codiceVolo + "';");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void aggiornaVolo(String tipologia, String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo,
                             String dataPartenza, String durata, int ritardo, String statoDelVolo) {
        try {

            Statement stmt = connection.createStatement();

            String colonna;

            if(tipologia.equals("InPartenza"))
                colonna = "destinazione";

            else
                colonna = "origine";

            stmt.executeQuery("UPDATE voli SET "
                    + colonna + " = '" + luogo + "', "
                    + "orario_partenza = '" + orarioPartenza + "', "
                    + "orario_arrivo = '" + orarioArrivo + "', "
                    + "data= '" + dataPartenza + "', "
                    + "durata = '" + durata + "', "
                    + "ritardo = " + ritardo + ", "
                    + "stato = '" + statoDelVolo+ "' "
                    + "WHERE codice = '" + codiceVolo + "';");

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}