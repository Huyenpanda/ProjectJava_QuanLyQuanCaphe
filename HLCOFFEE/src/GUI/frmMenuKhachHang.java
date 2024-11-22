/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DatabaseConnect.Connect;
import Entity.SanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Admin
 */
public class frmMenuKhachHang extends javax.swing.JFrame {

    /**
     * Creates new form frmMenuKhachHang
     */
//    private List<SanPham> allSanPhams;       // Lưu toàn bộ sản phẩm
//    private List<SanPham> filteredSanPhams; // Lưu sản phẩm đã lọc
//    private boolean IsSearch = false;
//    // Lấy danh sách sản phẩm từ database
//// Lưu sản phẩm đã lọc
//      String[] imgPaths = {
//        "/Icon/img_cafe_den.jpg",        // maSanPham = 1
//        "/Icon/img_cafe_sua.jpg",        // maSanPham = 2
//        "/Icon/img_tra_xanh.jpg",        // maSanPham = 3
//        "/Icon/img_trasua.jpg",          // maSanPham = 4
//        "/Icon/img_sinhtobo.jpg",        // maSanPham = 5
//        "/Icon/img_sinhtoxoai.jpg",      // maSanPham = 6
//        "/Icon/img_banhminuong.jpg",     // maSanPham = 7
//        "/Icon/img_banhkem.jpg",         // maSanPham = 8
//        "/Icon/img_pepsi.jpg",           // maSanPham = 9
//        "/Icon/img_coca.jpg",            // maSanPham = 10
//        "/Icon/img_nuoccam.jpg",         // maSanPham = 11
//        "/Icon/img_tradao.jpg",          // maSanPham = 12
//        "/Icon/img_sinhtodau.jpg",       // maSanPham = 13
//        "/Icon/img_cupcake.jpg",         // maSanPham = 14
//        "/Icon/img_nuocsuoi.jpg",        // maSanPham = 15
//        "/Icon/img_espresso.jpg"         // maSanPham = 16
//        // Các sản phẩm khác không có ảnh: maSanPham = 17 đến maSanPham = 20
//    };
//
//    private Connect connect; 
//    public frmMenuKhachHang() {
//        initComponents();
//        this.setTitle("Bán hàng");
//        connect = Connect.getInstance();
//        hienThiSanPham();
//         // Thiết lập jPanelMenuSP để sử dụng GridLayout với 4 cột
//        // 0 hàng, 4 cột, khoảng cách giữa các phần tử là 10px
//    }
//    private void hienThiSanPham() {
//        allSanPhams = connect.getAllSanPham(); // Lưu tất cả sản phẩm từ database
//        filteredSanPhams = new ArrayList<>(allSanPhams); // Mặc định hiển thị tất cả
//        FoundProductDisplay(filteredSanPhams);
//        IsSearch = true;
//       
//    }
//    private void FoundProductDisplay(List<SanPham> sanPhams) {
//        jPanelMenuSPKhachHang.removeAll();
//        // Cài đặt FlowLayout cho jPanelMenuSP
//        jPanelMenuSPKhachHang.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30)); // Cách đều 15px
//// Đặt kích thước cụ thể cho jPanelMenuSP
//        jPanelMenuSPKhachHang.setPreferredSize(new Dimension(590, 1070));
//
//        // Cập nhật giao diện hiển thị sản phẩm dựa trên danh sách được truyền vào
//        // Danh sách các JLabel cho tên và giá sản phẩm
//        JLabel[] tenSpLabels = {jLabelTenSp_001, jLabelTenSp_002, jLabelTenSp_003, jLabelTenSp_004,
//            jLabelTenSp_005, jLabelTenSp_006, jLabelTenSp_007, jLabelTenSp_008,
//            jLabelTenSp_009, jLabelTenSp_010, jLabelTenSp_011, jLabelTenSp_012,
//            jLabelTenSp_013, jLabelTenSp_014, jLabelTenSp_015, jLabelTenSp_016};
//        JLabel[] giaSpLabels = {jLabelGiaSp_001, jLabelGiaSp_002, jLabelGiaSp_003, jLabelGiaSp_004,
//            jLabelGiaSp_005, jLabelGiaSp_006, jLabelGiaSp_007, jLabelGiaSp_008,
//            jLabelGiaSp_009, jLabelGiaSp_010, jLabelGiaSp_011, jLabelGiaSp_012,
//            jLabelGiaSp_013, jLabelGiaSp_014, jLabelGiaSp_015, jLabelGiaSp_016};
//
//        JLabel[] imgSpLabels = {jLabelSP_001, jLabelSP_002, jLabelSP_003, jLabelSP_004,
//            jLabelSP_005, jLabelSP_006, jLabelSP_007, jLabelSP_008,
//            jLabelSP_009, jLabelSP_010, jLabelSP_011, jLabelSP_012,
//            jLabelSP_013, jLabelSP_014, jLabelSP_015, jLabelSP_016};
//
//        JPanel[] panelSP = {jPanelSP_001, jPanelSP_002, jPanelSP_003, jPanelSP_004,
//            jPanelSP_005, jPanelSP_006, jPanelSP_007, jPanelSP_008,
//            jPanelSP_009, jPanelSP_010, jPanelSP_011, jPanelSP_012,
//            jPanelSP_013, jPanelSP_014, jPanelSP_015, jPanelSP_016};
//
//        // Thêm các panel theo đúng thứ tự
//        for (int i = 0; i < filteredSanPhams.size() && i < 16; i++) {
//            SanPham sp = filteredSanPhams.get(i);
//            panelSP[i].removeAll();
//            panelSP[i].setPreferredSize(new Dimension(135, 145)); // Kích thước cố định cho từng sản phẩm
//            panelSP[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//            panelSP[i].revalidate();
//            panelSP[i].repaint();
//            // Cập nhật thông tin sản phẩm
//            tenSpLabels[i].setText(sp.getTenSanPham());
//            System.out.println("1: " + tenSpLabels[i].getText());
//            giaSpLabels[i].setText(String.format("%.0f VND", sp.getDonGia()));
//            // Cập nhật ảnh
//            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imgPaths[i]));
//            Image resizedImage = imageIcon.getImage().getScaledInstance(150, 110, Image.SCALE_SMOOTH);
//
//            imgSpLabels[i].setIcon(new ImageIcon(resizedImage));
//            panelSP[i].revalidate();
//            panelSP[i].repaint();
//            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
//            spinner.setValue(0);
//            spinner.setPreferredSize(new java.awt.Dimension(60, 24));
//
//            // Cập nhật lại panel sản phẩm
//            panelSP[i].setVisible(true);
//            panelSP[i].removeAll();
//            // panelSP[i].setPreferredSize(new java.awt.Dimension(1500, 100));  // Tùy chỉnh kích thước panel nếu cần
//
//            //panelSP[i].setLayout(new FlowLayout(FlowLayout.LEFT));
//            //panelSP[i].setLayout(new BorderLayout());
//            panelSP[i].add(imgSpLabels[i], BorderLayout.NORTH);
//            JPanel bottomPanel = new JPanel(new FlowLayout());
//            bottomPanel.add(giaSpLabels[i]);
//            bottomPanel.add(spinner);
//
//            panelSP[i].add(bottomPanel, BorderLayout.SOUTH);
//            panelSP[i].revalidate();
//            panelSP[i].repaint();
//            bottomPanel.setSize(50, 100);
//
//            JPanel middlePanel = new JPanel(new FlowLayout());
//            panelSP[i].add(middlePanel, BorderLayout.CENTER);
//            //middlePanel.
//            // middlePanel.setBackground(Color.red);
//            middlePanel.add(tenSpLabels[i]);
//            if (!giaSpLabels[i].isVisible()) {
//                giaSpLabels[i].setVisible(true); // Hiển thị lại nếu bị ẩn
//            }
//
//            // Thêm panelSP vào jPanelMenuSP theo đúng thứ tự
//            jPanelMenuSPKhachHang.add(panelSP[i]);
//        }
//
//        // Cập nhật giao diện
//        jPanelMenuSPKhachHang.revalidate();
//        jPanelMenuSPKhachHang.repaint();
//    }
     private List<SanPham> allSanPhams;       // Lưu toàn bộ sản phẩm
    private List<SanPham> filteredSanPhams; // Lưu sản phẩm đã lọc
    private boolean IsSearch = false; // Lưu sản phẩm đã lọc
      String[] imgPaths = {
        "/Icon/img_cafe_den.jpg",        // maSanPham = 1
        "/Icon/img_cafe_sua.jpg",        // maSanPham = 2
        "/Icon/img_tra_xanh.jpg",        // maSanPham = 3
        "/Icon/img_trasua.jpg",          // maSanPham = 4
        "/Icon/img_sinhtobo.jpg",        // maSanPham = 5
        "/Icon/img_sinhtoxoai.jpg",      // maSanPham = 6
        "/Icon/img_banhminuong.jpg",     // maSanPham = 7
        "/Icon/img_banhkem.jpg",         // maSanPham = 8
        "/Icon/img_pepsi.jpg",           // maSanPham = 9
        "/Icon/img_coca.jpg",            // maSanPham = 10
        "/Icon/img_nuoccam.jpg",         // maSanPham = 11
        "/Icon/img_tradao.jpg",          // maSanPham = 12
        "/Icon/img_sinhtodau.jpg",       // maSanPham = 13
        "/Icon/img_cupcake.jpg",         // maSanPham = 14
        "/Icon/img_nuocsuoi.jpg",        // maSanPham = 15
        "/Icon/img_espresso.jpg"         // maSanPham = 16
        // Các sản phẩm khác không có ảnh: maSanPham = 17 đến maSanPham = 20
    };

    private Connect connect; 
    public frmMenuKhachHang() {
        initComponents();
        this.setTitle("Menu");
        connect = Connect.getInstance();
        hienThiSanPham();
         // Thiết lập jPanelMenuSP để sử dụng GridLayout với 4 cột
        //jPanelMenuSPKhachHang.setLayout(new GridLayout(0, 4, 10, 10));  // 0 hàng, 4 cột, khoảng cách giữa các phần tử là 10px
  }
    private void hienThiSanPham() {
        allSanPhams = connect.getAllSanPham(); // Lưu tất cả sản phẩm từ database
        filteredSanPhams = new ArrayList<>(allSanPhams); // Mặc định hiển thị tất cả
        updateProductDisplay(filteredSanPhams);
    }

    private void updateProductDisplay(List<SanPham> sanPhams) {
        jPanelMenuSPKhachHang.removeAll();

        // Cập nhật giao diện hiển thị sản phẩm dựa trên danh sách được truyền vào
        // Danh sách các JLabel cho tên và giá sản phẩm
        JLabel[] tenSpLabels = {jLabelTenSp_001, jLabelTenSp_002, jLabelTenSp_003, jLabelTenSp_004,
            jLabelTenSp_005, jLabelTenSp_006, jLabelTenSp_007, jLabelTenSp_008,
            jLabelTenSp_009, jLabelTenSp_010, jLabelTenSp_011, jLabelTenSp_012,
            jLabelTenSp_013, jLabelTenSp_014, jLabelTenSp_015, jLabelTenSp_016};
                JLabel[] giaSpLabels = {jLabelGiaSp_001, jLabelGiaSp_002, jLabelGiaSp_003, jLabelGiaSp_004,
            jLabelGiaSp_005, jLabelGiaSp_006, jLabelGiaSp_007, jLabelGiaSp_008,
            jLabelGiaSp_009, jLabelGiaSp_010, jLabelGiaSp_011, jLabelGiaSp_012,
            jLabelGiaSp_013, jLabelGiaSp_014, jLabelGiaSp_015, jLabelGiaSp_016};

        JLabel[] imgSpLabels = {jLabelSP_001, jLabelSP_002, jLabelSP_003, jLabelSP_004,
            jLabelSP_005, jLabelSP_006, jLabelSP_007, jLabelSP_008,
            jLabelSP_009, jLabelSP_010, jLabelSP_011, jLabelSP_012,
            jLabelSP_013, jLabelSP_014, jLabelSP_015, jLabelSP_016};

        JPanel[] panelSP = {jPanelSP_001, jPanelSP_002, jPanelSP_003, jPanelSP_004,
            jPanelSP_005, jPanelSP_006, jPanelSP_007, jPanelSP_008,
            jPanelSP_009, jPanelSP_010, jPanelSP_011, jPanelSP_012,
            jPanelSP_013, jPanelSP_014, jPanelSP_015, jPanelSP_016};
        

        // Thay đổi layout của jPanelMenuSP để hiển thị theo dạng 4 cột
        //jPanelMenuSPKhachHang.setLayout(new GridLayout(4, 4, 20, 20)); // 0 dòng và 4 cột
        jPanelMenuSPKhachHang.setPreferredSize(new java.awt.Dimension(590, 1070));

        // Xóa tất cả các thành phần trong jPanelMenuSP trước khi thêm mới
        jPanelMenuSPKhachHang.removeAll();

        // Thêm các panel theo đúng thứ tự
        for (int i = 0; i < allSanPhams.size() && i < 16; i++) {
            SanPham sp = allSanPhams.get(i);

            // Cập nhật thông tin sản phẩm
            tenSpLabels[i].setText(sp.getTenSanPham());
            giaSpLabels[i].setText(String.format("%.0f VND", sp.getDonGia()));

            // Cập nhật ảnh
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imgPaths[i]));
            Image resizedImage = imageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
            imgSpLabels[i].setIcon(new ImageIcon(resizedImage));
            imgSpLabels[i].setHorizontalAlignment(JLabel.CENTER); // Căn giữa ảnh
            imgSpLabels[i].setPreferredSize(new java.awt.Dimension(150, 100)); // Đặt kích thước cố định cho JLabel

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
            spinner.setValue(0);
            spinner.setPreferredSize(new java.awt.Dimension(60, 24));

            // Panel để chứa giá và spinner
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); // Thêm khoảng cách 10px giữa giaSpLabel và JSpinner
            bottomPanel.add(giaSpLabels[i], BorderLayout.CENTER);
            bottomPanel.add(spinner, BorderLayout.EAST);

            // Thiết lập layout và thêm các thành phần vào panelSP
            panelSP[i].setLayout(new BorderLayout(0, 0));
            panelSP[i].add(imgSpLabels[i], BorderLayout.NORTH);
            panelSP[i].add(tenSpLabels[i], BorderLayout.CENTER);
            panelSP[i].add(bottomPanel, BorderLayout.SOUTH);
            if (!giaSpLabels[i].isVisible()) {
                giaSpLabels[i].setVisible(true); // Hiển thị lại nếu bị ẩn
            }

            // Thêm panelSP vào jPanelMenuSP theo đúng thứ tự
            jPanelMenuSPKhachHang.add(panelSP[i]);
        }

        // Cập nhật giao diện
        jPanelMenuSPKhachHang.revalidate();
        jPanelMenuSPKhachHang.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldTimTen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTenLoai = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jScrollPaneMeuNuKhachHang = new javax.swing.JScrollPane();
        jPanelMenuSPKhachHang = new javax.swing.JPanel();
        jPanelSP_001 = new javax.swing.JPanel();
        jLabelSP_001 = new javax.swing.JLabel();
        jLabelTenSp_001 = new javax.swing.JLabel();
        jLabelGiaSp_001 = new javax.swing.JLabel();
        jPanelSP_002 = new javax.swing.JPanel();
        jLabelSP_002 = new javax.swing.JLabel();
        jLabelTenSp_002 = new javax.swing.JLabel();
        jLabelGiaSp_002 = new javax.swing.JLabel();
        jPanelSP_003 = new javax.swing.JPanel();
        jLabelSP_003 = new javax.swing.JLabel();
        jLabelTenSp_003 = new javax.swing.JLabel();
        jLabelGiaSp_003 = new javax.swing.JLabel();
        jPanelSP_004 = new javax.swing.JPanel();
        jLabelSP_004 = new javax.swing.JLabel();
        jLabelTenSp_004 = new javax.swing.JLabel();
        jLabelGiaSp_004 = new javax.swing.JLabel();
        jPanelSP_005 = new javax.swing.JPanel();
        jLabelSP_005 = new javax.swing.JLabel();
        jLabelTenSp_005 = new javax.swing.JLabel();
        jLabelGiaSp_005 = new javax.swing.JLabel();
        jPanelSP_006 = new javax.swing.JPanel();
        jLabelSP_006 = new javax.swing.JLabel();
        jLabelTenSp_006 = new javax.swing.JLabel();
        jLabelGiaSp_006 = new javax.swing.JLabel();
        jPanelSP_007 = new javax.swing.JPanel();
        jLabelSP_007 = new javax.swing.JLabel();
        jLabelTenSp_007 = new javax.swing.JLabel();
        jLabelGiaSp_007 = new javax.swing.JLabel();
        jPanelSP_008 = new javax.swing.JPanel();
        jLabelSP_008 = new javax.swing.JLabel();
        jLabelTenSp_008 = new javax.swing.JLabel();
        jLabelGiaSp_008 = new javax.swing.JLabel();
        jPanelSP_009 = new javax.swing.JPanel();
        jLabelSP_009 = new javax.swing.JLabel();
        jLabelTenSp_009 = new javax.swing.JLabel();
        jLabelGiaSp_009 = new javax.swing.JLabel();
        jPanelSP_010 = new javax.swing.JPanel();
        jLabelSP_010 = new javax.swing.JLabel();
        jLabelTenSp_010 = new javax.swing.JLabel();
        jLabelGiaSp_010 = new javax.swing.JLabel();
        jPanelSP_011 = new javax.swing.JPanel();
        jLabelSP_011 = new javax.swing.JLabel();
        jLabelTenSp_011 = new javax.swing.JLabel();
        jLabelGiaSp_011 = new javax.swing.JLabel();
        jPanelSP_012 = new javax.swing.JPanel();
        jLabelSP_012 = new javax.swing.JLabel();
        jLabelTenSp_012 = new javax.swing.JLabel();
        jLabelGiaSp_012 = new javax.swing.JLabel();
        jPanelSP_013 = new javax.swing.JPanel();
        jLabelSP_013 = new javax.swing.JLabel();
        jLabelTenSp_013 = new javax.swing.JLabel();
        jLabelGiaSp_013 = new javax.swing.JLabel();
        jPanelSP_014 = new javax.swing.JPanel();
        jLabelSP_014 = new javax.swing.JLabel();
        jLabelTenSp_014 = new javax.swing.JLabel();
        jLabelGiaSp_014 = new javax.swing.JLabel();
        jPanelSP_015 = new javax.swing.JPanel();
        jLabelSP_015 = new javax.swing.JLabel();
        jLabelTenSp_015 = new javax.swing.JLabel();
        jLabelGiaSp_015 = new javax.swing.JLabel();
        jPanelSP_016 = new javax.swing.JPanel();
        jLabelSP_016 = new javax.swing.JLabel();
        jLabelTenSp_016 = new javax.swing.JLabel();
        jLabelGiaSp_016 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Mã Bàn Của Bạn:");

        jTextFieldTimTen.setText("Tìm Kiếm Tên Món Ăn");

        jButton1.setText("Tìm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xem Hóa Đơn");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("LOGO COFFEE");

        jComboBoxTenLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Về Chúng Tôi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jComboBoxTenLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jComboBoxTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 199, Short.MAX_VALUE))
        );

        jScrollPaneMeuNuKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelSP_001.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_001.setText("jLabel3");

        jLabelGiaSp_001.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_001Layout = new javax.swing.GroupLayout(jPanelSP_001);
        jPanelSP_001.setLayout(jPanelSP_001Layout);
        jPanelSP_001Layout.setHorizontalGroup(
            jPanelSP_001Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_001Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_001Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTenSp_001, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_001Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_001, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addComponent(jLabelSP_001, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_001Layout.setVerticalGroup(
            jPanelSP_001Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_001Layout.createSequentialGroup()
                .addComponent(jLabelSP_001, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_001)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_001)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_002.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_002.setText("jLabel3");

        jLabelGiaSp_002.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_002Layout = new javax.swing.GroupLayout(jPanelSP_002);
        jPanelSP_002.setLayout(jPanelSP_002Layout);
        jPanelSP_002Layout.setHorizontalGroup(
            jPanelSP_002Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_002Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_002Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_002, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_002, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_002Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_002, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_002Layout.setVerticalGroup(
            jPanelSP_002Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_002Layout.createSequentialGroup()
                .addComponent(jLabelSP_002, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_002)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_002)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_003.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_003.setText("jLabel3");

        jLabelGiaSp_003.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_003Layout = new javax.swing.GroupLayout(jPanelSP_003);
        jPanelSP_003.setLayout(jPanelSP_003Layout);
        jPanelSP_003Layout.setHorizontalGroup(
            jPanelSP_003Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_003Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_003Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_003, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_003, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_003Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_003, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_003Layout.setVerticalGroup(
            jPanelSP_003Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_003Layout.createSequentialGroup()
                .addComponent(jLabelSP_003, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_003)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_003)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_004.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_004.setText("jLabel3");

        jLabelGiaSp_004.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_004Layout = new javax.swing.GroupLayout(jPanelSP_004);
        jPanelSP_004.setLayout(jPanelSP_004Layout);
        jPanelSP_004Layout.setHorizontalGroup(
            jPanelSP_004Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_004Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_004Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_004, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_004, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_004Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_004, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_004Layout.setVerticalGroup(
            jPanelSP_004Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_004Layout.createSequentialGroup()
                .addComponent(jLabelSP_004, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_004)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_004)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_005.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_005.setText("jLabel3");

        jLabelGiaSp_005.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_005Layout = new javax.swing.GroupLayout(jPanelSP_005);
        jPanelSP_005.setLayout(jPanelSP_005Layout);
        jPanelSP_005Layout.setHorizontalGroup(
            jPanelSP_005Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_005Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_005Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_005, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_005, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_005Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_005, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_005Layout.setVerticalGroup(
            jPanelSP_005Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_005Layout.createSequentialGroup()
                .addComponent(jLabelSP_005, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_005)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_005)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_006.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_006.setText("jLabel3");

        jLabelGiaSp_006.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_006Layout = new javax.swing.GroupLayout(jPanelSP_006);
        jPanelSP_006.setLayout(jPanelSP_006Layout);
        jPanelSP_006Layout.setHorizontalGroup(
            jPanelSP_006Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_006Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_006Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_006, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_006, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_006Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_006, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_006Layout.setVerticalGroup(
            jPanelSP_006Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_006Layout.createSequentialGroup()
                .addComponent(jLabelSP_006, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_006)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_006)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_007.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_007.setText("jLabel3");

        jLabelGiaSp_007.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_007Layout = new javax.swing.GroupLayout(jPanelSP_007);
        jPanelSP_007.setLayout(jPanelSP_007Layout);
        jPanelSP_007Layout.setHorizontalGroup(
            jPanelSP_007Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_007Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_007Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_007, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_007, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_007Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_007, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_007Layout.setVerticalGroup(
            jPanelSP_007Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_007Layout.createSequentialGroup()
                .addComponent(jLabelSP_007, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_007)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_007)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_008.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_008.setText("jLabel3");

        jLabelGiaSp_008.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_008Layout = new javax.swing.GroupLayout(jPanelSP_008);
        jPanelSP_008.setLayout(jPanelSP_008Layout);
        jPanelSP_008Layout.setHorizontalGroup(
            jPanelSP_008Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_008Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_008Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_008, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_008, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_008Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_008, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_008Layout.setVerticalGroup(
            jPanelSP_008Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_008Layout.createSequentialGroup()
                .addComponent(jLabelSP_008, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_008)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_008)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_009.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_009.setText("jLabel3");

        jLabelGiaSp_009.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_009Layout = new javax.swing.GroupLayout(jPanelSP_009);
        jPanelSP_009.setLayout(jPanelSP_009Layout);
        jPanelSP_009Layout.setHorizontalGroup(
            jPanelSP_009Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_009Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_009Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_009, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_009, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_009Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_009, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_009Layout.setVerticalGroup(
            jPanelSP_009Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_009Layout.createSequentialGroup()
                .addComponent(jLabelSP_009, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_009)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_009)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_010.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_010.setText("jLabel3");

        jLabelGiaSp_010.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_010Layout = new javax.swing.GroupLayout(jPanelSP_010);
        jPanelSP_010.setLayout(jPanelSP_010Layout);
        jPanelSP_010Layout.setHorizontalGroup(
            jPanelSP_010Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_010Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_010Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_010, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_010, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_010Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_010, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_010Layout.setVerticalGroup(
            jPanelSP_010Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_010Layout.createSequentialGroup()
                .addComponent(jLabelSP_010, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_010)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_010)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_011.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_011.setText("jLabel3");

        jLabelGiaSp_011.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_011Layout = new javax.swing.GroupLayout(jPanelSP_011);
        jPanelSP_011.setLayout(jPanelSP_011Layout);
        jPanelSP_011Layout.setHorizontalGroup(
            jPanelSP_011Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_011Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_011Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_011, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_011, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_011Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_011, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_011Layout.setVerticalGroup(
            jPanelSP_011Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_011Layout.createSequentialGroup()
                .addComponent(jLabelSP_011, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_011)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_011)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_012.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_012.setText("jLabel3");

        jLabelGiaSp_012.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_012Layout = new javax.swing.GroupLayout(jPanelSP_012);
        jPanelSP_012.setLayout(jPanelSP_012Layout);
        jPanelSP_012Layout.setHorizontalGroup(
            jPanelSP_012Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_012Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_012Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_012, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_012, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_012Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_012, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_012Layout.setVerticalGroup(
            jPanelSP_012Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_012Layout.createSequentialGroup()
                .addComponent(jLabelSP_012, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_012)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_012)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_013.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_013.setText("jLabel3");

        jLabelGiaSp_013.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_013Layout = new javax.swing.GroupLayout(jPanelSP_013);
        jPanelSP_013.setLayout(jPanelSP_013Layout);
        jPanelSP_013Layout.setHorizontalGroup(
            jPanelSP_013Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_013Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_013Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_013, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_013, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_013Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_013, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_013Layout.setVerticalGroup(
            jPanelSP_013Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_013Layout.createSequentialGroup()
                .addComponent(jLabelSP_013, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_013)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_013)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_014.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_014.setText("jLabel3");

        jLabelGiaSp_014.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_014Layout = new javax.swing.GroupLayout(jPanelSP_014);
        jPanelSP_014.setLayout(jPanelSP_014Layout);
        jPanelSP_014Layout.setHorizontalGroup(
            jPanelSP_014Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_014Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_014Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_014, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_014, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_014Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_014, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_014Layout.setVerticalGroup(
            jPanelSP_014Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_014Layout.createSequentialGroup()
                .addComponent(jLabelSP_014, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_014)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_014)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_015.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_015.setText("jLabel3");

        jLabelGiaSp_015.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_015Layout = new javax.swing.GroupLayout(jPanelSP_015);
        jPanelSP_015.setLayout(jPanelSP_015Layout);
        jPanelSP_015Layout.setHorizontalGroup(
            jPanelSP_015Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_015Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_015Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_015, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_015, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_015Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_015, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_015Layout.setVerticalGroup(
            jPanelSP_015Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_015Layout.createSequentialGroup()
                .addComponent(jLabelSP_015, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_015)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_015)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_016.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_016.setText("jLabel3");

        jLabelGiaSp_016.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_016Layout = new javax.swing.GroupLayout(jPanelSP_016);
        jPanelSP_016.setLayout(jPanelSP_016Layout);
        jPanelSP_016Layout.setHorizontalGroup(
            jPanelSP_016Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_016Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_016Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_016, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_016, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_016Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_016, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_016Layout.setVerticalGroup(
            jPanelSP_016Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_016Layout.createSequentialGroup()
                .addComponent(jLabelSP_016, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTenSp_016)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_016)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMenuSPKhachHangLayout = new javax.swing.GroupLayout(jPanelMenuSPKhachHang);
        jPanelMenuSPKhachHang.setLayout(jPanelMenuSPKhachHangLayout);
        jPanelMenuSPKhachHangLayout.setHorizontalGroup(
            jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuSPKhachHangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuSPKhachHangLayout.createSequentialGroup()
                        .addComponent(jPanelSP_001, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelSP_002, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelSP_005, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelSP_006, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelSP_003, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuSPKhachHangLayout.createSequentialGroup()
                        .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelSP_016, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelSP_007, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelSP_010, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelSP_013, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelSP_004, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelSP_011, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelSP_014, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelSP_008, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelSP_012, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelSP_009, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelSP_015, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanelMenuSPKhachHangLayout.setVerticalGroup(
            jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSPKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelSP_002, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_001, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_003, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSP_004, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_005, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_006, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelSP_008, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_007, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_009, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSP_010, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanelSP_011, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanelSP_012, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanelMenuSPKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSP_015, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSP_014, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSP_013, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanelSP_016, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPaneMeuNuKhachHang.setViewportView(jPanelMenuSPKhachHang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneMeuNuKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jScrollPaneMeuNuKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 55, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Lấy danh sách sản phẩm từ database
IsSearch = true;
        allSanPhams = connect.getAllSanPham();
        filteredSanPhams.clear();

        // Lấy thông tin từ các thành phần giao diện để tìm kiếm
        String tenSanPham = jTextFieldTimTen.getText().trim().toLowerCase();
        // Lọc sản phẩm theo tên
    for (SanPham sp : allSanPhams) {
        if (sp.getTenSanPham().toLowerCase().contains(tenSanPham)) {
            filteredSanPhams.add(sp);
        }
    }
        FoundProductDisplay(filteredSanPhams);
        // Nếu không có sản phẩm nào phù hợp, hiển thị thông báo
        if (filteredSanPhams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
private void FoundProductDisplay(List<SanPham> sanPhams) {
        jPanelMenuSPKhachHang.removeAll();
        // Cài đặt FlowLayout cho jPanelMenuSP
        jPanelMenuSPKhachHang.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15)); // Cách đều 15px
// Đặt kích thước cụ thể cho jPanelMenuSP
        jPanelMenuSPKhachHang.setPreferredSize(new Dimension(700, 1000));

        // Cập nhật giao diện hiển thị sản phẩm dựa trên danh sách được truyền vào
        // Danh sách các JLabel cho tên và giá sản phẩm
               JLabel[] tenSpLabels = {jLabelTenSp_001, jLabelTenSp_002, jLabelTenSp_003, jLabelTenSp_004,
            jLabelTenSp_005, jLabelTenSp_006, jLabelTenSp_007, jLabelTenSp_008,
            jLabelTenSp_009, jLabelTenSp_010, jLabelTenSp_011, jLabelTenSp_012,
            jLabelTenSp_013, jLabelTenSp_014, jLabelTenSp_015, jLabelTenSp_016};
                JLabel[] giaSpLabels = {jLabelGiaSp_001, jLabelGiaSp_002, jLabelGiaSp_003, jLabelGiaSp_004,
            jLabelGiaSp_005, jLabelGiaSp_006, jLabelGiaSp_007, jLabelGiaSp_008,
            jLabelGiaSp_009, jLabelGiaSp_010, jLabelGiaSp_011, jLabelGiaSp_012,
            jLabelGiaSp_013, jLabelGiaSp_014, jLabelGiaSp_015, jLabelGiaSp_016};

        JLabel[] imgSpLabels = {jLabelSP_001, jLabelSP_002, jLabelSP_003, jLabelSP_004,
            jLabelSP_005, jLabelSP_006, jLabelSP_007, jLabelSP_008,
            jLabelSP_009, jLabelSP_010, jLabelSP_011, jLabelSP_012,
            jLabelSP_013, jLabelSP_014, jLabelSP_015, jLabelSP_016};

        JPanel[] panelSP = {jPanelSP_001, jPanelSP_002, jPanelSP_003, jPanelSP_004,
            jPanelSP_005, jPanelSP_006, jPanelSP_007, jPanelSP_008,
            jPanelSP_009, jPanelSP_010, jPanelSP_011, jPanelSP_012,
            jPanelSP_013, jPanelSP_014, jPanelSP_015, jPanelSP_016};
        

        // Thêm các panel theo đúng thứ tự
        for (int i = 0; i < filteredSanPhams.size() && i < 16; i++) {
            SanPham sp = filteredSanPhams.get(i);
            panelSP[i].removeAll();
            panelSP[i].setPreferredSize(new Dimension(150, 160)); // Kích thước cố định cho từng sản phẩm
            panelSP[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));

            panelSP[i].revalidate();
            panelSP[i].repaint();
            // Cập nhật thông tin sản phẩm
            tenSpLabels[i].setText(sp.getTenSanPham());
            System.out.println("1: " + tenSpLabels[i].getText());
            giaSpLabels[i].setText(String.format("%.0f VND", sp.getDonGia()));
            // Cập nhật ảnh
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imgPaths[i]));
            Image resizedImage = imageIcon.getImage().getScaledInstance(150, 110, Image.SCALE_SMOOTH);

            imgSpLabels[i].setIcon(new ImageIcon(resizedImage));
            panelSP[i].revalidate();
            panelSP[i].repaint();
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
            spinner.setValue(0);
            spinner.setPreferredSize(new java.awt.Dimension(60, 24));

            // Cập nhật lại panel sản phẩm
            panelSP[i].setVisible(true);
            panelSP[i].removeAll();
            // panelSP[i].setPreferredSize(new java.awt.Dimension(1500, 100));  // Tùy chỉnh kích thước panel nếu cần

            //panelSP[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            //panelSP[i].setLayout(new BorderLayout());
            panelSP[i].add(imgSpLabels[i], BorderLayout.NORTH);
            JPanel bottomPanel = new JPanel(new FlowLayout());
            bottomPanel.add(giaSpLabels[i]);
            bottomPanel.add(spinner);

            panelSP[i].add(bottomPanel, BorderLayout.SOUTH);
            panelSP[i].revalidate();
            panelSP[i].repaint();
            bottomPanel.setSize(50, 100);

            JPanel middlePanel = new JPanel(new FlowLayout());
            panelSP[i].add(middlePanel, BorderLayout.CENTER);
            //middlePanel.
            // middlePanel.setBackground(Color.red);
            middlePanel.add(tenSpLabels[i]);
            if (!giaSpLabels[i].isVisible()) {
                giaSpLabels[i].setVisible(true); // Hiển thị lại nếu bị ẩn
            }

            // Thêm panelSP vào jPanelMenuSP theo đúng thứ tự
            jPanelMenuSPKhachHang.add(panelSP[i]);
        }

        // Cập nhật giao diện
        jPanelMenuSPKhachHang.revalidate();
        jPanelMenuSPKhachHang.repaint();
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
            java.util.logging.Logger.getLogger(frmMenuKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                frmMenuKhachHang form = new frmMenuKhachHang();
                form.setVisible(true);
                form.setResizable(false);
                form.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxTenLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelGiaSp_001;
    private javax.swing.JLabel jLabelGiaSp_002;
    private javax.swing.JLabel jLabelGiaSp_003;
    private javax.swing.JLabel jLabelGiaSp_004;
    private javax.swing.JLabel jLabelGiaSp_005;
    private javax.swing.JLabel jLabelGiaSp_006;
    private javax.swing.JLabel jLabelGiaSp_007;
    private javax.swing.JLabel jLabelGiaSp_008;
    private javax.swing.JLabel jLabelGiaSp_009;
    private javax.swing.JLabel jLabelGiaSp_010;
    private javax.swing.JLabel jLabelGiaSp_011;
    private javax.swing.JLabel jLabelGiaSp_012;
    private javax.swing.JLabel jLabelGiaSp_013;
    private javax.swing.JLabel jLabelGiaSp_014;
    private javax.swing.JLabel jLabelGiaSp_015;
    private javax.swing.JLabel jLabelGiaSp_016;
    private javax.swing.JLabel jLabelSP_001;
    private javax.swing.JLabel jLabelSP_002;
    private javax.swing.JLabel jLabelSP_003;
    private javax.swing.JLabel jLabelSP_004;
    private javax.swing.JLabel jLabelSP_005;
    private javax.swing.JLabel jLabelSP_006;
    private javax.swing.JLabel jLabelSP_007;
    private javax.swing.JLabel jLabelSP_008;
    private javax.swing.JLabel jLabelSP_009;
    private javax.swing.JLabel jLabelSP_010;
    private javax.swing.JLabel jLabelSP_011;
    private javax.swing.JLabel jLabelSP_012;
    private javax.swing.JLabel jLabelSP_013;
    private javax.swing.JLabel jLabelSP_014;
    private javax.swing.JLabel jLabelSP_015;
    private javax.swing.JLabel jLabelSP_016;
    private javax.swing.JLabel jLabelTenSp_001;
    private javax.swing.JLabel jLabelTenSp_002;
    private javax.swing.JLabel jLabelTenSp_003;
    private javax.swing.JLabel jLabelTenSp_004;
    private javax.swing.JLabel jLabelTenSp_005;
    private javax.swing.JLabel jLabelTenSp_006;
    private javax.swing.JLabel jLabelTenSp_007;
    private javax.swing.JLabel jLabelTenSp_008;
    private javax.swing.JLabel jLabelTenSp_009;
    private javax.swing.JLabel jLabelTenSp_010;
    private javax.swing.JLabel jLabelTenSp_011;
    private javax.swing.JLabel jLabelTenSp_012;
    private javax.swing.JLabel jLabelTenSp_013;
    private javax.swing.JLabel jLabelTenSp_014;
    private javax.swing.JLabel jLabelTenSp_015;
    private javax.swing.JLabel jLabelTenSp_016;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMenuSPKhachHang;
    private javax.swing.JPanel jPanelSP_001;
    private javax.swing.JPanel jPanelSP_002;
    private javax.swing.JPanel jPanelSP_003;
    private javax.swing.JPanel jPanelSP_004;
    private javax.swing.JPanel jPanelSP_005;
    private javax.swing.JPanel jPanelSP_006;
    private javax.swing.JPanel jPanelSP_007;
    private javax.swing.JPanel jPanelSP_008;
    private javax.swing.JPanel jPanelSP_009;
    private javax.swing.JPanel jPanelSP_010;
    private javax.swing.JPanel jPanelSP_011;
    private javax.swing.JPanel jPanelSP_012;
    private javax.swing.JPanel jPanelSP_013;
    private javax.swing.JPanel jPanelSP_014;
    private javax.swing.JPanel jPanelSP_015;
    private javax.swing.JPanel jPanelSP_016;
    private javax.swing.JScrollPane jScrollPaneMeuNuKhachHang;
    private javax.swing.JTextField jTextFieldTimTen;
    // End of variables declaration//GEN-END:variables
}
