import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TodoApp extends JFrame implements ActionListener {

    private JTextField taskField;
    private JButton addButton, deleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    public TodoApp() {

        setTitle("To-Do List App");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // ===== Top Panel =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        taskField = new JTextField();
        addButton = new JButton("Add Task");

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // ===== Center List =====
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(taskList);

        // ===== Bottom Panel =====
        JPanel bottomPanel = new JPanel();
        deleteButton = new JButton("Delete Selected");
        bottomPanel.add(deleteButton);

        // Add to Frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Events
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            String task = taskField.getText().trim();

            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");
            }
        }

        if (e.getSource() == deleteButton) {
            int selectedIndex = taskList.getSelectedIndex();

            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoApp::new);
    }
}