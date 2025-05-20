import DAO.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class EmployeeForm extends JFrame {
private JTextField idField, lastNameField, firstNameField, middleNameField, serviceField, baseSalaryField, workField, departmentIdField;
    private JButton addButton , editbutton;
    private JButton backButton;
    private JPanel panel1;
    private JTextField demoTextField;

    public void SetText(Employee data){
        lastNameField.setText(data.getLName());
        firstNameField.setText(data.getFName());
        middleNameField.setText(data.getMName());
        serviceField.setText(data.getService()+"");
        baseSalaryField.setText(data.getBaseSalary()+"");
        workField.setText(data.getWork());
        departmentIdField.setText(data.getDepartmentID()+"");
    }
    public EmployeeForm(Employee data) {
        setTitle("Employee Management");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        BackgroundPanel backgroundPanel = new BackgroundPanel("picture/360_F_514951224_2dxMLbIw5qNRdPGD003chpbVcxWtcp7K.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        setContentPane(backgroundPanel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("FirstName:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        firstNameField = new JTextField(10);
        panel.add(firstNameField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("LastName:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        lastNameField = new JTextField(10);
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("MiddleName:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        middleNameField = new JTextField(10);
        panel.add(middleNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Service:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        serviceField = new JTextField(10);
        panel.add(serviceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("BaseSalary:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        baseSalaryField = new JTextField(20);
        panel.add(baseSalaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Job:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        workField = new JTextField(20);
        panel.add(workField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Department ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        departmentIdField = new JTextField(20);


        panel.add(departmentIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        addButton = new JButton("ADD");
        panel.add(addButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 8;
        editbutton = new JButton("MODIFY");
        panel.add(editbutton, gbc);
        add(panel);


        gbc.gridx = 2;
        gbc.gridy = 8;
        backButton = new JButton("MENU");
        panel.add(backButton, gbc);
        add(panel);

        if(data!=null) SetText(data);
        addButton.setEnabled(data == null);
        editbutton.setEnabled(data != null);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lastNameField.getText().isEmpty() || firstNameField.getText().isEmpty() || middleNameField.getText().isEmpty() ||
                            serviceField.getText().isEmpty() || baseSalaryField.getText().isEmpty() || workField.getText().isEmpty() ||
                            departmentIdField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(EmployeeForm.this, "Please provide full information", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                Employee employees = new Employee(
                        lastNameField.getText() ,
                        firstNameField.getText() ,
                        middleNameField.getText() , Integer.parseInt(serviceField.getText()) ,
                        Double.parseDouble(baseSalaryField.getText()) , workField.getText() , Integer.parseInt(departmentIdField.getText()));
                EmployeeDAO dao = new EmployeeDAO();
                System.out.println(employees.toString());
                try {
                    Boolean checkadd = dao.AddnewEmployee(employees);
                    if (checkadd) {
                        JFrame quanLyNhanVienForm = new QuanLyNhanVien();
                        quanLyNhanVienForm.setVisible(true);
                        setVisible(false);
                    }else {
                        System.out.println("false!!!!!!!!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(EmployeeForm.this, "Adding Employee ERROR ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
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



        editbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lastNameField.getText().isEmpty() || firstNameField.getText().isEmpty() || middleNameField.getText().isEmpty() ||
                            serviceField.getText().isEmpty() || baseSalaryField.getText().isEmpty() || workField.getText().isEmpty() ||
                            departmentIdField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(EmployeeForm.this, "Please provide full information", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                Employee employees = new Employee(
                        lastNameField.getText() ,
                        firstNameField.getText() ,
                        middleNameField.getText() , Integer.parseInt(serviceField.getText()) ,
                        Double.parseDouble(baseSalaryField.getText()) , workField.getText() , Integer.parseInt(departmentIdField.getText()));
                EmployeeDAO dao = new EmployeeDAO();
                System.out.println(employees.toString());
                try {
                    Boolean checkadd = dao.updateEmployee(employees);
                    if (checkadd) {
                        JFrame quanLyNhanVienForm = new QuanLyNhanVien();
                        quanLyNhanVienForm.setVisible(true);
                        setVisible(false);
                    }else {
                        JFrame quanLyNhanVienForm = new QuanLyNhanVien();
                        quanLyNhanVienForm.setVisible(true);
                        setVisible(false);
                        System.out.println("false!!!!!!!!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(EmployeeForm.this, "Adding Employee ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
