import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Notenverwaltung notenverwaltung = new Notenverwaltung();
            notenverwaltung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            notenverwaltung.setVisible(true);
        });
    }
}
