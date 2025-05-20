import DAO.ExtraLeavesDAO;
import model.Employee;
import model.ExtraLeavess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ExtraLeaves extends JFrame {
    private JTextField amountField, yearField, monthField, fromDateField, toDateField, employeeIdField;
    private JButton addButton;
    private JButton backButton;

    public ExtraLeaves(Employee data){
        setTitle("EXTRA LEAVES");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        BackgroundPanel backgroundPanel = new BackgroundPanel("picture/360_F_514951224_2dxMLbIw5qNRdPGD003chpbVcxWtcp7K.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        setContentPane(backgroundPanel);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("DEDUCTION AMOUNT:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        amountField = new JTextField(10);
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("YEAR:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        yearField = new JTextField(10);
        panel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("MONTH:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        monthField = new JTextField(10);
        panel.add(monthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("FROM DATE:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        fromDateField = new JTextField(10);
        panel.add(fromDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("TO DATE:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        toDateField = new JTextField(10);
        panel.add(toDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("EMPLOYEE ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        employeeIdField = new JTextField(10);
        panel.add(employeeIdField, gbc);

        addButton = new JButton("ADD");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);
        add(panel);

        gbc.gridx = 1;
        gbc.gridy = 6;
        backButton = new JButton("MENU");
        panel.add(backButton, gbc);
        add(panel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action when the back button is clicked
                // For example, go back to the previous frame or form
                JFrame menuForm = new QuanLyNhanVien(); // Assuming MenuForm is the previous form
                menuForm.setVisible(true);
                setVisible(false); // Hide the current frame
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                double amount = Double.parseDouble(amountField.getText());
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                String fromDate = fromDateField.getText();
                String toDate = toDateField.getText();
                int employeeId = Integer.parseInt(employeeIdField.getText());
                ExtraLeavesDAO dao = new ExtraLeavesDAO();
                ExtraLeavess extraLeaves = new ExtraLeavess(amount, year, month, fromDate, toDate, employeeId);
                try {
                    dao.addExtraLeaves(extraLeaves);
                    JOptionPane.showMessageDialog(ExtraLeaves.this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(ExtraLeaves.this, "Lỗi khi thêm dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }
}
