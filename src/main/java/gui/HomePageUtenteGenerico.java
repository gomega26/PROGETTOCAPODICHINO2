package gui;

import controller.Controller;

import javax.swing.*;

public class HomePageUtenteGenerico {
    private JPanel panel1;
    private static JFrame frame;

    private JFrame frameChiamante;
    private Controller controller;

    public HomePageUtenteGenerico(JFrame frameChiamante, Controller controller){
        frame = new JFrame("HomePageUtenteGenerico");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
