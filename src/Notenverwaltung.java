import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Notenverwaltung extends JFrame {
    private JTextField tfname;
    private JTextField tfnote;
    private JButton btnsubmit;
    private JButton btndelete;
    private JLabel lbldurchschnitt;
    private JTable table;
    private DefaultTableModel tblmodel;

    private ArrayList<Student> students;


    public Notenverwaltung() {
        setTitle("Notenverwaltung");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        students = new ArrayList<>();

        initgui();
    }

    public void initgui() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Name:"));
        tfname = new JTextField(15);
        inputPanel.add(tfname);

        inputPanel.add(new JLabel("Note:"));
        tfnote = new JTextField(5);
        inputPanel.add(tfnote);

        btnsubmit = new JButton("Hinzufügen");
        btnsubmit.addActionListener(e -> addStudent());
        inputPanel.add(btnsubmit);

        add(inputPanel, BorderLayout.NORTH);

        tblmodel = new DefaultTableModel(new String[]{"Name", "Note"}, 0);
        table = new JTable(tblmodel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        btndelete = new JButton("Löschen");
        btndelete.addActionListener(e -> deleteStudent());
        bottomPanel.add(btndelete, BorderLayout.WEST);

        lbldurchschnitt = new JLabel("Durchschnitt: ");
        bottomPanel.add(lbldurchschnitt, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addStudent() {
        String name = tfname.getText();
        String noteStr = tfnote.getText();

        if (name.isEmpty() || noteStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte alle Felder ausfüllen.");
            return;
        }

        double note;
        try {
            note = Double.parseDouble(noteStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bitte eine gültige Note eingeben.");
            return;
        }

        Student student = new Student(note, name);
        students.add(student);
        tblmodel.addRow(new Object[]{name, note});
        tfname.setText("");
        tfnote.setText("");

        updateAverage();
    }

    public void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            students.remove(selectedRow);
            tblmodel.removeRow(selectedRow);
            updateAverage();
        } else {
            JOptionPane.showMessageDialog(this, "Bitte einen Schüler auswählen.");
        }
    }

    public void updateAverage() {
        if (students.isEmpty()) {
            lbldurchschnitt.setText("Durchschnitt: ");
            return;
        }

        double sum = 0;
        for (Student student : students) {
            sum += student.getNote();
        }
        double average = sum / students.size();
        lbldurchschnitt.setText("Durchschnitt: " + String.format("%.2f", average));
    }
}
