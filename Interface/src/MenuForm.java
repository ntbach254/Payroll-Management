
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuForm extends JFrame{

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MenuForm(){
        setTitle("EMPLOYEE AND PAYROLL MANAGEMENT");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chứa các nút chức năng
        JPanel menuPanel = new JPanel(new GridLayout(3, 1, 0, 10)); // 3 hàng, 1 cột, khoảng cách giữa các nút là 10
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tạo khoảng cách giữa nút và viền của JFrame

        JButton buttonNhanVien = new JButton("EMPLOYEE");
        buttonNhanVien.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(53, 152, 219), 2), // Outer line border
                BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Inner empty border for padding

        JButton buttonPhongBan = new JButton("DEPARTMENT");
        buttonPhongBan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(39, 174, 96), 2), // Outer line border
                BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Inner empty border for padding

        JButton BtnThongKeLuong = new JButton("PAYROLL");
        BtnThongKeLuong.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(231, 76, 60), 2), // Outer line border
                BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Inner empty border for padding
        BackgroundPanel backgroundPanel = new BackgroundPanel("picture/360_F_514951224_2dxMLbIw5qNRdPGD003chpbVcxWtcp7K.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        setContentPane(backgroundPanel);

        // Thiết lập kích thước cho các nút
        Dimension buttonSize = new Dimension(100, 50);
        buttonNhanVien.setPreferredSize(buttonSize);
        buttonPhongBan.setPreferredSize(buttonSize);
        BtnThongKeLuong.setPreferredSize(buttonSize);
        Font buttonFont = new Font("Roboto", Font.BOLD, 30);
        buttonNhanVien.setFont(buttonFont);
        buttonPhongBan.setFont(buttonFont);
        BtnThongKeLuong.setFont(buttonFont);
        buttonNhanVien.setIcon(new ImageIcon("picture/Solid_black.png"));
        buttonPhongBan.setIcon(new ImageIcon("picture/Solid_black.png"));
        BtnThongKeLuong .setIcon(new ImageIcon("picture/Solid_black.png"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các nút
        menuPanel.add(buttonNhanVien, gbc);

        gbc.gridy = 1;
        menuPanel.add(buttonPhongBan, gbc);




        // Thêm các nút vào panel
        menuPanel.add(buttonNhanVien);
        menuPanel.add(buttonPhongBan);
        menuPanel.add(BtnThongKeLuong);

        // Tạo CardLayout cho các panel chức năng
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Thêm các form vào cardPanel
        //cardPanel.add(new QuanLyNhanVien(), "QuanLyNhanVien");
//        cardPanel.add(new QuanLyTinhLuongPanel(), "QuanLyTinhLuong");

        // Thêm action listener cho các nút
        buttonNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("da log vao duoc ham click");
//                cardLayout.show(cardPanel, "QuanLyNhanVien");
                JFrame quanLyNhanVienForm = new QuanLyNhanVien();
                quanLyNhanVienForm.setVisible(true);
                setVisible(false);
            }
        });

        buttonPhongBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JFrame quanLyNhanVienForm = new DepartmentForm();
                    quanLyNhanVienForm.setVisible(true);
                    setVisible(false);
//                cardLayout.show(cardPanel, "QuanLyTinhLuong");
            }
        });

        BtnThongKeLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame quanLyNhanVienForm = null;
                try {
                    quanLyNhanVienForm = new ThongKeLuong();
                    quanLyNhanVienForm.setVisible(true);
                    setVisible(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                quanLyNhanVienForm.setVisible(true);
                    setVisible(false);
//                cardLayout.show(cardPanel, "QuanLyTinhLuong");
            }
        });




        // Thêm các panel vào JFrame
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.CENTER);
        add(cardPanel, BorderLayout.SOUTH); // Đưa cardPanel xuống dưới cùng để không bị che phủ bởi menuPanel

        // Hiển thị panel đầu tiên
        cardLayout.show(cardPanel, "QuanLyNhanVien");
    }



}
