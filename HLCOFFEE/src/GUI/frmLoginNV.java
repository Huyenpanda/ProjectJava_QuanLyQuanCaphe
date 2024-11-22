/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DatabaseConnect.Connect;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Admin
 */
public class frmLoginNV extends javax.swing.JFrame {

    /**
     * Creates new form frmLoginNV
     */
    public frmLoginNV() {
        initComponents();
        this.setTitle("FORM ĐĂNG NHẬP CỦA NHÂN VIÊN");
        // Load ảnh từ package Icon và thiết lập lại kích thước cho phù hợp với jLabel1
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/LGCFCUT.png"));
        Image img = icon.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(img));
        // Đảm bảo jTextFieldTaiKhoan và jPasswordField có thể nhập liệu
    jTextFieldTaiKhoan.setEditable(true);
    jTextFieldTaiKhoan.setEnabled(true);
    jPasswordField.setEditable(true);
    jPasswordField.setEnabled(true);
        setRoundedBorders();

    }
private void setRoundedBorders() {
        // Bo tròn cho JTextField
        jTextFieldTaiKhoan.setBorder(new RoundedBorder(15));
        jPasswordField.setBorder(new RoundedBorder(15));

        // Bo tròn cho các JButton
        jButtonDangNhap.setBorder(new RoundedBorder(15));
        jButtonTroLai.setBorder(new RoundedBorder(15));
        jButtonThoat.setBorder(new RoundedBorder(15));
    }
      class RoundedBorder extends LineBorder {
        private int radius;

        public RoundedBorder(int radius) {
            super(Color.GRAY, 1, true);
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(lineColor);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelTaiKhoan = new javax.swing.JLabel();
        jLabelMatKhau = new javax.swing.JLabel();
        jButtonDangNhap = new javax.swing.JButton();
        jButtonTroLai = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();
        jTextFieldTaiKhoan = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTaiKhoan.setText("Tài Khoản:");

        jLabelMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMatKhau.setText("Mật Khẩu:");

        jButtonDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonDangNhap.setText("ĐĂNG NHẬP");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
            }
        });

        jButtonTroLai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonTroLai.setText("TRỞ LẠI");
        jButtonTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTroLaiActionPerformed(evt);
            }
        });

        jButtonThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonThoat.setText("THOÁT");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                        .addComponent(jPasswordField))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonTroLai)
                                .addGap(106, 106, 106)
                                .addComponent(jButtonThoat)))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jPasswordField))
                .addGap(18, 18, 18)
                .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTroLai)
                    .addComponent(jButtonThoat))
                .addGap(40, 40, 40))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Background_loginNV.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -4, 890, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        // TODO add your handling code here:
        String taiKhoan = jTextFieldTaiKhoan.getText();
        String matKhau = new String(jPasswordField.getPassword());

        // Kiểm tra tài khoản và mật khẩu
        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
        } else {
            // Gọi phương thức kiểm tra đăng nhập
            if (checkLogin(taiKhoan, matKhau)) {
                // Nếu đăng nhập thành công
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                // Truyền tài khoản vào frmQuanLyBanHang
                // Chuyển tài khoản vào frmQuanLyBanHang
//                frmQuanLyBanHang quanLyBanHang = new frmQuanLyBanHang(taiKhoan);
//                quanLyBanHang.setVisible(true);
                // Đóng form đăng nhập
                dispose();
                frmQuanLyBanHang mainFrame = new frmQuanLyBanHang();
                mainFrame.setVisible(true);
                mainFrame.showDefaultPanel(taiKhoan); // Hiển thị jPanelRight với ảnh nền
            } else {
                // Nếu đăng nhập thất bại
                JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
            }
        }
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

    private void jButtonTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTroLaiActionPerformed
        // TODO add your handling code here:
        frmLoginVaitro loginVaitro = new frmLoginVaitro();
        loginVaitro.setVisible(true);  // Mở form frmLoginVaitro
        dispose();  // Đóng form hiện tại (frmLoginNhanVien)
    }//GEN-LAST:event_jButtonTroLaiActionPerformed

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButtonThoatActionPerformed
    private boolean checkLogin(String taiKhoan, String matKhau) {
        Connect connect = new Connect();
        ResultSet rs = null;
        boolean isValid = false;

        try {
            String sql = "SELECT * FROM NhanVien WHERE tenTaiKhoan = ? AND matKhau = ?";
            PreparedStatement pstmt = connect.getConnection().prepareStatement(sql);
            pstmt.setString(1, taiKhoan);
            pstmt.setString(2, matKhau);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                isValid = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi kiểm tra đăng nhập: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đóng kết nối: " + ex.getMessage());
            }
        }

        return isValid;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmLoginNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLoginNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLoginNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLoginNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLoginNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDangNhap;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JButton jButtonTroLai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelMatKhau;
    private javax.swing.JLabel jLabelTaiKhoan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
