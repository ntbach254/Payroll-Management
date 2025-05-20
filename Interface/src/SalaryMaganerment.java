import DAO.Employee_AllowanceDao;
import model.Employee;
import model.Employee_Allowance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SalaryMaganerment extends JFrame {
    private JTextField eadIdField, hraField, taField, maField, daField, yearField, monthField, employeeIdField;
    private JButton addButton;
    private JButton backButton;

    private JLabel warningLabel;
    public SalaryMaganerment(Employee data){
        setTitle("EMPLOYEE ALLOWANCE");
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
        panel.add(new JLabel("EAD ID:"), gbc);
        eadIdField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(eadIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("HRA:"), gbc);
        hraField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(hraField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("TA:"), gbc);
        taField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(taField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("MA:"), gbc);
        maField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(maField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("DA:"), gbc);
        daField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(daField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("EFFECTIVE YEAR:"), gbc);
        yearField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("EFFECTIVE MONTH:"), gbc);
        monthField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(monthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("EMPLOYEE ID:"), gbc);
        employeeIdField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(employeeIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        addButton = new JButton("ADD");
        panel.add(addButton, gbc);

        add(panel);

        gbc.gridx = 1;
        gbc.gridy = 8;
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
                // Lấy giá trị từ các trường nhập liệu
                int eadId = Integer.parseInt(eadIdField.getText());
                double hra = Double.parseDouble(hraField.getText());
                double ta = Double.parseDouble(taField.getText());
                double ma = Double.parseDouble(maField.getText());
                double da = Double.parseDouble(daField.getText());
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int employeeId = Integer.parseInt(employeeIdField.getText());
                // Thêm dữ liệu vào cơ sở dữ liệu thông qua DAO của bạn
                Employee_Allowance employeeAllowance = new Employee_Allowance(eadId, hra, ta, ma, da, year, month, employeeId);
                Employee_AllowanceDao dao = new Employee_AllowanceDao();
                try {
                    dao.addEmployeeAllowance(employeeAllowance);
                    JOptionPane.showMessageDialog(SalaryMaganerment.this, "thêm trợ cấp nhân viên thành công", "xác nhận", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    JFrame fram = new QuanLyNhanVien();
                    fram.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Create and configure the warning label
        warningLabel = new JLabel("<html><font color='red'>Please check in payslip whether the payslip corresponding is already existed.<br>If so, allowances are already input.(*set EADID=0)</font></html>");
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setVerticalAlignment(SwingConstants.TOP);

        // Add the warning label to the frame
        GridBagConstraints gbcWarningLabel = new GridBagConstraints();
        gbcWarningLabel.gridx = 0;
        gbcWarningLabel.gridy = 9;
        gbcWarningLabel.gridwidth = 2;
        gbcWarningLabel.anchor = GridBagConstraints.CENTER;
        gbcWarningLabel.insets = new Insets(10, 0, 0, 0); // Add some top padding
        panel.add(warningLabel, gbcWarningLabel);

    }
}
