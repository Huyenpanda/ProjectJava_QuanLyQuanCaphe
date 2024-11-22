/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package GUI;
import DatabaseConnect.Connect;
import Entity.SanPham;

import GUI.frmDatBan;
import GUI.frmQuanLyBan;
import GUI.frmQuanLyHoaDon;
import GUI.frmTrangChu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class frmQuanLyBanHang extends javax.swing.JFrame {

    /**
     * Creates new form frmQuanLyBanHang
     */
    private String tenTaiKhoan; // Lưu tên tài khoản đăng nhập
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
    public frmQuanLyBanHang() {
        initComponents();
        this.setTitle("Bán hàng");
        showDefaultPanel(tenTaiKhoan);
        hienThiSanPham();
         setupRadioButtons();
         // Thiết lập jPanelMenuSP để sử dụng GridLayout với 4 cột
        jPanelMenuSP.setLayout(new GridLayout(0, 4, 10, 10));  // 0 hàng, 4 cột, khoảng cách giữa các phần tử là 10px

       
        }
    public void showDefaultPanel(String tenTaiKhoan) {
        
        this.tenTaiKhoan = tenTaiKhoan;
        
        loadTenNhanVien();
         startClock();
    // Xóa mọi thành phần hiện tại trong jPanelRight
    jPanelRight.removeAll();
    
        
        //Sử dụng BorderLayout để BackgroundPanel tự động lấp đầy jPanelRight
        jPanelRight.setLayout(new BorderLayout());
        // Thêm BackgroundPanel vào jPanelRight để lấp đầy toàn bộ
        BackgroundPanel backgroundPanel = new BackgroundPanel("/Icon/backgroundNau.jpg");
        jPanelRight.add(backgroundPanel, BorderLayout.CENTER);
        // Thu nhỏ chiều rộng các cột
        jTableHoaDonDat.getColumnModel().getColumn(0).setPreferredWidth(20); // Cột Mã SP
        jTableHoaDonDat.getColumnModel().getColumn(3).setPreferredWidth(20);  // Cột Số Lượng
        jTableHoaDonDat.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        this.setLayout(new BorderLayout());
        this.add(jPanelThanhQuanLy, BorderLayout.WEST);  // jPanelThanhQuanLy cố định bên trái
        this.add(jPanelRight, BorderLayout.CENTER);

        // Ẩn jPanelBanHang và jPanelHoaDon khi khởi động
        jPanelBanHang.setVisible(false);
        jPanelHoaDon.setVisible(false);
       connect = new Connect();
        // Trong constructor của frmQuanLyBanHang
        jPanelMenuSP.setLayout(new GridLayout(4, 4, 18, 18)); // 4x4 Grid, khoảng cách 18px giữa các panel
    // Cập nhật giao diện
    jPanelRight.revalidate();
    jPanelRight.repaint();
}

        // Phương thức load tên nhân viên
        private void loadTenNhanVien() {
            try {
                Connect connect = new Connect();
                String tenNhanVien = connect.getTenNhanVien(tenTaiKhoan);
                if (tenNhanVien != null) {
                    jLabelTenNV.setText("Nhân viên: "+ tenNhanVien); // Cập nhật tên nhân viên lên JLabel
                } else {
                    jLabelTenNV.setText("Không tìm thấy tên nhân viên!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải tên nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        private void startClock() {
            // Định dạng ngày giờ
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            // Tạo Timer để cập nhật mỗi giây
            Timer timer = new Timer(1000, e -> {
                String currentDateTime = dateTimeFormat.format(new Date());
                jLabelNgayGio.setText(currentDateTime); // Cập nhật JLabel
            });

            // Bắt đầu Timer
            timer.start();
        }

        private void setupRadioButtons() {
        buttonGroup1.add(jRadioButtonTheoTen);
        buttonGroup1.add(jRadioButtonTheoLoai);
        buttonGroup1.add(jRadioButtonTheoGia);
        buttonGroup1.add(jRadioButtonTatCa);

        jRadioButtonTheoTen.addActionListener(e -> toggleSearchFields());
        jRadioButtonTheoLoai.addActionListener(e -> toggleSearchFields());
        jRadioButtonTheoGia.addActionListener(e -> toggleSearchFields());
        jRadioButtonTatCa.addActionListener(e -> toggleSearchFields());

        toggleSearchFields(); // Thiết lập trạng thái ban đầu
    }

    private void toggleSearchFields() {
        jTextFieldTenSP.setEnabled(jRadioButtonTheoTen.isSelected() || jRadioButtonTatCa.isSelected());
        jComboBoxLoaiSP.setEnabled(jRadioButtonTheoLoai.isSelected() || jRadioButtonTatCa.isSelected());
        jTextFieldGiaTu.setEnabled(jRadioButtonTheoGia.isSelected() || jRadioButtonTatCa.isSelected());
        jTextFieldGiaDen.setEnabled(jRadioButtonTheoGia.isSelected() || jRadioButtonTatCa.isSelected());
    }

  private void hienThiSanPham() {
        allSanPhams = connect.getAllSanPham(); // Lưu tất cả sản phẩm từ database
        filteredSanPhams = new ArrayList<>(allSanPhams); // Mặc định hiển thị tất cả
        updateProductDisplay(filteredSanPhams);
    }

    private void updateProductDisplay(List<SanPham> sanPhams) {
        jPanelMenuSP.removeAll();

        // Cập nhật giao diện hiển thị sản phẩm dựa trên danh sách được truyền vào
        // Danh sách các JLabel cho tên và giá sản phẩm
        JLabel[] tenSpLabels = {jLabelTenSp_01, jLabelTenSp_02, jLabelTenSp_03, jLabelTenSp_04,
            jLabelTenSp_05, jLabelTenSp_06, jLabelTenSp_07, jLabelTenSp_08,
            jLabelTenSp_09, jLabelTenSp_10, jLabelTenSp_11, jLabelTenSp_12,
            jLabelTenSp_13, jLabelTenSp_14, jLabelTenSp_15, jLabelTenSp_16};
        JLabel[] giaSpLabels = {jLabelGiaSp_01, jLabelGiaSp_02, jLabelGiaSp_03, jLabelGiaSp_04,
            jLabelGiaSp_05, jLabelGiaSp_06, jLabelGiaSp_07, jLabelGiaSp_08,
            jLabelGiaSp_09, jLabelGiaSp_10, jLabelGiaSp_11, jLabelGiaSp_12,
            jLabelGiaSp_13, jLabelGiaSp_14, jLabelGiaSp_15, jLabelGiaSp_16};

        JLabel[] imgSpLabels = {jLabelSP_img1, jLabelSP_img2, jLabel_img3, jLabelSP_img4,
            jLabelSP_img5, jLabelSP_img6, jLabelSP_img7, jLabelSP_img8,
            jLabelSP_img9, jLabelSP_img10, jLabelSP_img11, jLabelSP_img12,
            jLabelSP_img13, jLabelSP_img14, jLabelSP_img15, jLabelSP_img16};

        JPanel[] panelSP = {jPanelSP_1, jPanelSP_2, jPanelSP_3, jPanelSP_4,
            jPanelSP_5, jPanelSP_6, jPanelSP_7, jPanelSP_8,
            jPanelSP_9, jPanelSP_10, jPanelSP_11, jPanelSP_12,
            jPanelSP_13, jPanelSP_14, jPanelSP_15, jPanelSP_16};

        // Thay đổi layout của jPanelMenuSP để hiển thị theo dạng 4 cột
        jPanelMenuSP.setLayout(new GridLayout(4, 4, 20, 20)); // 0 dòng và 4 cột
        jPanelMenuSP.setPreferredSize(new java.awt.Dimension(650, 700));

        // Xóa tất cả các thành phần trong jPanelMenuSP trước khi thêm mới
        jPanelMenuSP.removeAll();

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
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Thêm khoảng cách 10px giữa giaSpLabel và JSpinner
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
            jPanelMenuSP.add(panelSP[i]);
        }

        // Cập nhật giao diện
        jPanelMenuSP.revalidate();
        jPanelMenuSP.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelThanhQuanLy = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JButton();
        btnTrangChu = new javax.swing.JButton();
        btnDatBan = new javax.swing.JButton();
        btnQuanLyBan = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        jPanelRight = new javax.swing.JPanel();
        jPanelBanHang = new javax.swing.JPanel();
        jLabelTenSP = new javax.swing.JLabel();
        jTextFieldTenSP = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jLabelTenSP1 = new javax.swing.JLabel();
        jComboBoxLoaiSP = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelMenuSP = new javax.swing.JPanel();
        jPanelSP_2 = new javax.swing.JPanel();
        jLabelSP_img2 = new javax.swing.JLabel();
        jLabelTenSp_02 = new javax.swing.JLabel();
        jLabelGiaSp_02 = new javax.swing.JLabel();
        jPanelSP_3 = new javax.swing.JPanel();
        jLabel_img3 = new javax.swing.JLabel();
        jLabelTenSp_03 = new javax.swing.JLabel();
        jLabelGiaSp_03 = new javax.swing.JLabel();
        jPanelSP_4 = new javax.swing.JPanel();
        jLabelSP_img4 = new javax.swing.JLabel();
        jLabelTenSp_04 = new javax.swing.JLabel();
        jLabelGiaSp_04 = new javax.swing.JLabel();
        jPanelSP_6 = new javax.swing.JPanel();
        jLabelSP_img6 = new javax.swing.JLabel();
        jLabelTenSp_06 = new javax.swing.JLabel();
        jLabelGiaSp_06 = new javax.swing.JLabel();
        jPanelSP_8 = new javax.swing.JPanel();
        jLabelSP_img8 = new javax.swing.JLabel();
        jLabelTenSp_08 = new javax.swing.JLabel();
        jLabelGiaSp_08 = new javax.swing.JLabel();
        jPanelSP_7 = new javax.swing.JPanel();
        jLabelSP_img7 = new javax.swing.JLabel();
        jLabelTenSp_07 = new javax.swing.JLabel();
        jLabelGiaSp_07 = new javax.swing.JLabel();
        jPanelSP_1 = new javax.swing.JPanel();
        jLabelSP_img1 = new javax.swing.JLabel();
        jLabelTenSp_01 = new javax.swing.JLabel();
        jLabelGiaSp_01 = new javax.swing.JLabel();
        jPanelSP_5 = new javax.swing.JPanel();
        jLabelSP_img5 = new javax.swing.JLabel();
        jLabelTenSp_05 = new javax.swing.JLabel();
        jLabelGiaSp_05 = new javax.swing.JLabel();
        jPanelSP_9 = new javax.swing.JPanel();
        jLabelSP_img9 = new javax.swing.JLabel();
        jLabelTenSp_09 = new javax.swing.JLabel();
        jLabelGiaSp_09 = new javax.swing.JLabel();
        jPanelSP_10 = new javax.swing.JPanel();
        jLabelSP_img10 = new javax.swing.JLabel();
        jLabelTenSp_10 = new javax.swing.JLabel();
        jLabelGiaSp_10 = new javax.swing.JLabel();
        jPanelSP_12 = new javax.swing.JPanel();
        jLabelSP_img12 = new javax.swing.JLabel();
        jLabelTenSp_12 = new javax.swing.JLabel();
        jLabelGiaSp_12 = new javax.swing.JLabel();
        jPanelSP_11 = new javax.swing.JPanel();
        jLabelSP_img11 = new javax.swing.JLabel();
        jLabelTenSp_11 = new javax.swing.JLabel();
        jLabelGiaSp_11 = new javax.swing.JLabel();
        jPanelSP_13 = new javax.swing.JPanel();
        jLabelSP_img13 = new javax.swing.JLabel();
        jLabelTenSp_13 = new javax.swing.JLabel();
        jLabelGiaSp_13 = new javax.swing.JLabel();
        jPanelSP_14 = new javax.swing.JPanel();
        jLabelSP_img14 = new javax.swing.JLabel();
        jLabelTenSp_14 = new javax.swing.JLabel();
        jLabelGiaSp_14 = new javax.swing.JLabel();
        jPanelSP_15 = new javax.swing.JPanel();
        jLabelSP_img15 = new javax.swing.JLabel();
        jLabelTenSp_15 = new javax.swing.JLabel();
        jLabelGiaSp_15 = new javax.swing.JLabel();
        jPanelSP_16 = new javax.swing.JPanel();
        jLabelSP_img16 = new javax.swing.JLabel();
        jLabelTenSp_16 = new javax.swing.JLabel();
        jLabelGiaSp_16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHoaDonCho = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabelTenSP2 = new javax.swing.JLabel();
        jTextFieldGiaTu = new javax.swing.JTextField();
        jLabelTenSP3 = new javax.swing.JLabel();
        jTextFieldGiaDen = new javax.swing.JTextField();
        jRadioButtonTheoTen = new javax.swing.JRadioButton();
        jRadioButtonTheoLoai = new javax.swing.JRadioButton();
        jRadioButtonTheoGia = new javax.swing.JRadioButton();
        jRadioButtonTatCa = new javax.swing.JRadioButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jPanelHoaDon = new javax.swing.JPanel();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jTabbedPaneHoaDon = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHoaDonDat = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabelSoDienThoai = new javax.swing.JLabel();
        jLabelHoTenKhachHang = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSoDienThoai = new javax.swing.JTextField();
        jTextFieldHoTenKhachHang = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaGhiChu = new javax.swing.JTextArea();
        jPanel21 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelTrangThai = new javax.swing.JLabel();
        jTextFieldTongTienSP = new javax.swing.JTextField();
        jTextFieldTongTienHoaDon = new javax.swing.JTextField();
        jTextFieldTienKhachTra = new javax.swing.JTextField();
        jTextFieldTienThua = new javax.swing.JTextField();
        jComboBoxLoaiThanhToan = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaDiaChi = new javax.swing.JTextArea();
        jButtonHuyHD = new javax.swing.JButton();
        jButtonTaoHoaDonCho = new javax.swing.JButton();
        jButtonThanhToan = new javax.swing.JButton();
        jLabelTenNV = new javax.swing.JLabel();
        jButtonDangXuat = new javax.swing.JButton();
        jLabelNgayGio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelThanhQuanLy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("LOGO COFFEE");

        btnBanHang.setText("QUẢN LÝ BÁN HÀNG");
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnTrangChu.setText("TRANG CHỦ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnDatBan.setText("QUẢN LÝ ĐẶT BÀN");
        btnDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatBanActionPerformed(evt);
            }
        });

        btnQuanLyBan.setText("QUẢN LÝ BÀN");
        btnQuanLyBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyBanActionPerformed(evt);
            }
        });

        btnHoaDon.setText("HÓA ĐƠN");
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelThanhQuanLyLayout = new javax.swing.GroupLayout(jPanelThanhQuanLy);
        jPanelThanhQuanLy.setLayout(jPanelThanhQuanLyLayout);
        jPanelThanhQuanLyLayout.setHorizontalGroup(
            jPanelThanhQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
            .addComponent(btnDatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnQuanLyBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelThanhQuanLyLayout.setVerticalGroup(
            jPanelThanhQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThanhQuanLyLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnQuanLyBan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelThanhQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 880));

        jPanelRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSP.setText("LOẠI SẢN PHẨM:");

        jTextFieldTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenSPActionPerformed(evt);
            }
        });

        jButtonTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonTimKiem.setText("TÌM");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jLabelTenSP1.setText("TÊN SẢN PHẨM");

        jComboBoxLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Cà phê", "Trà", "Sinh tố", "Nước ngọt", "Bánh ngọt" }));

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelSP_2.setBackground(new java.awt.Color(207, 139, 106));
        jPanelSP_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSP_2.setPreferredSize(new java.awt.Dimension(135, 145));

        jLabelSP_img2.setPreferredSize(new java.awt.Dimension(120, 70));

        jLabelTenSp_02.setText("jLabel3");

        jLabelGiaSp_02.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_2Layout = new javax.swing.GroupLayout(jPanelSP_2);
        jPanelSP_2.setLayout(jPanelSP_2Layout);
        jPanelSP_2Layout.setHorizontalGroup(
            jPanelSP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_02, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_2Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_02, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_2Layout.setVerticalGroup(
            jPanelSP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_2Layout.createSequentialGroup()
                .addComponent(jLabelSP_img2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTenSp_02)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_02)
                .addGap(25, 25, 25))
        );

        jPanelSP_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSP_3.setPreferredSize(new java.awt.Dimension(135, 145));

        jLabel_img3.setPreferredSize(new java.awt.Dimension(120, 70));

        jLabelTenSp_03.setText("jLabel3");

        jLabelGiaSp_03.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_3Layout = new javax.swing.GroupLayout(jPanelSP_3);
        jPanelSP_3.setLayout(jPanelSP_3Layout);
        jPanelSP_3Layout.setHorizontalGroup(
            jPanelSP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_img3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_03, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_3Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_03, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_3Layout.setVerticalGroup(
            jPanelSP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_3Layout.createSequentialGroup()
                .addComponent(jLabel_img3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTenSp_03)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_03)
                .addGap(22, 22, 22))
        );

        jPanelSP_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSP_4.setPreferredSize(new java.awt.Dimension(135, 145));

        jLabelSP_img4.setPreferredSize(new java.awt.Dimension(120, 70));

        jLabelTenSp_04.setText("jLabel3");

        jLabelGiaSp_04.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_4Layout = new javax.swing.GroupLayout(jPanelSP_4);
        jPanelSP_4.setLayout(jPanelSP_4Layout);
        jPanelSP_4Layout.setHorizontalGroup(
            jPanelSP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_04, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_4Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_04, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_4Layout.setVerticalGroup(
            jPanelSP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_4Layout.createSequentialGroup()
                .addComponent(jLabelSP_img4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTenSp_04)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_04)
                .addGap(23, 23, 23))
        );

        jPanelSP_6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_06.setText("jLabel3");

        jLabelGiaSp_06.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_6Layout = new javax.swing.GroupLayout(jPanelSP_6);
        jPanelSP_6.setLayout(jPanelSP_6Layout);
        jPanelSP_6Layout.setHorizontalGroup(
            jPanelSP_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_06, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addGroup(jPanelSP_6Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_06, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_6Layout.setVerticalGroup(
            jPanelSP_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_6Layout.createSequentialGroup()
                .addComponent(jLabelSP_img6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_06)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_06)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_08.setText("jLabel3");

        jLabelGiaSp_08.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_8Layout = new javax.swing.GroupLayout(jPanelSP_8);
        jPanelSP_8.setLayout(jPanelSP_8Layout);
        jPanelSP_8Layout.setHorizontalGroup(
            jPanelSP_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_08, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addGroup(jPanelSP_8Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_08, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_8Layout.setVerticalGroup(
            jPanelSP_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_8Layout.createSequentialGroup()
                .addComponent(jLabelSP_img8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_08)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_08)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_07.setText("jLabel3");

        jLabelGiaSp_07.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_7Layout = new javax.swing.GroupLayout(jPanelSP_7);
        jPanelSP_7.setLayout(jPanelSP_7Layout);
        jPanelSP_7Layout.setHorizontalGroup(
            jPanelSP_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_07, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addGroup(jPanelSP_7Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_07, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_7Layout.setVerticalGroup(
            jPanelSP_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_7Layout.createSequentialGroup()
                .addComponent(jLabelSP_img7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_07)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_07)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_1.setBackground(new java.awt.Color(207, 139, 106));
        jPanelSP_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSP_1.setPreferredSize(new java.awt.Dimension(135, 145));

        jLabelSP_img1.setAlignmentY(0.0F);
        jLabelSP_img1.setPreferredSize(new java.awt.Dimension(150, 90));

        jLabelTenSp_01.setText("jLabel3");

        jLabelGiaSp_01.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_1Layout = new javax.swing.GroupLayout(jPanelSP_1);
        jPanelSP_1.setLayout(jPanelSP_1Layout);
        jPanelSP_1Layout.setHorizontalGroup(
            jPanelSP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSP_1Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86))
                    .addComponent(jLabelSP_img1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_1Layout.setVerticalGroup(
            jPanelSP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_1Layout.createSequentialGroup()
                .addComponent(jLabelSP_img1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_01)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_05.setText("jLabel3");

        jLabelGiaSp_05.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_5Layout = new javax.swing.GroupLayout(jPanelSP_5);
        jPanelSP_5.setLayout(jPanelSP_5Layout);
        jPanelSP_5Layout.setHorizontalGroup(
            jPanelSP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_05, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSP_5Layout.createSequentialGroup()
                        .addComponent(jLabelGiaSp_05, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSP_5Layout.setVerticalGroup(
            jPanelSP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_5Layout.createSequentialGroup()
                .addComponent(jLabelSP_img5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_05)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_05)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_09.setText("jLabel3");

        jLabelGiaSp_09.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_9Layout = new javax.swing.GroupLayout(jPanelSP_9);
        jPanelSP_9.setLayout(jPanelSP_9Layout);
        jPanelSP_9Layout.setHorizontalGroup(
            jPanelSP_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_09, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_09, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_9Layout.setVerticalGroup(
            jPanelSP_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_9Layout.createSequentialGroup()
                .addComponent(jLabelSP_img9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_09)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_09)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_10.setText("jLabel3");

        jLabelGiaSp_10.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_10Layout = new javax.swing.GroupLayout(jPanelSP_10);
        jPanelSP_10.setLayout(jPanelSP_10Layout);
        jPanelSP_10Layout.setHorizontalGroup(
            jPanelSP_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_10Layout.setVerticalGroup(
            jPanelSP_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_10Layout.createSequentialGroup()
                .addComponent(jLabelSP_img10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_12.setText("jLabel3");

        jLabelGiaSp_12.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_12Layout = new javax.swing.GroupLayout(jPanelSP_12);
        jPanelSP_12.setLayout(jPanelSP_12Layout);
        jPanelSP_12Layout.setHorizontalGroup(
            jPanelSP_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img12, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_12Layout.setVerticalGroup(
            jPanelSP_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_12Layout.createSequentialGroup()
                .addComponent(jLabelSP_img12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_11.setText("jLabel3");

        jLabelGiaSp_11.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_11Layout = new javax.swing.GroupLayout(jPanelSP_11);
        jPanelSP_11.setLayout(jPanelSP_11Layout);
        jPanelSP_11Layout.setHorizontalGroup(
            jPanelSP_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img11, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_11Layout.setVerticalGroup(
            jPanelSP_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_11Layout.createSequentialGroup()
                .addComponent(jLabelSP_img11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_11)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_13.setBackground(new java.awt.Color(102, 255, 102));
        jPanelSP_13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_13.setText("jLabel3");

        jLabelGiaSp_13.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_13Layout = new javax.swing.GroupLayout(jPanelSP_13);
        jPanelSP_13.setLayout(jPanelSP_13Layout);
        jPanelSP_13Layout.setHorizontalGroup(
            jPanelSP_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_13Layout.setVerticalGroup(
            jPanelSP_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_13Layout.createSequentialGroup()
                .addComponent(jLabelSP_img13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_14.setText("jLabel3");

        jLabelGiaSp_14.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_14Layout = new javax.swing.GroupLayout(jPanelSP_14);
        jPanelSP_14.setLayout(jPanelSP_14Layout);
        jPanelSP_14Layout.setHorizontalGroup(
            jPanelSP_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img14, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_14Layout.setVerticalGroup(
            jPanelSP_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_14Layout.createSequentialGroup()
                .addComponent(jLabelSP_img14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSP_15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_15.setText("jLabel3");

        jLabelGiaSp_15.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_15Layout = new javax.swing.GroupLayout(jPanelSP_15);
        jPanelSP_15.setLayout(jPanelSP_15Layout);
        jPanelSP_15Layout.setHorizontalGroup(
            jPanelSP_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img15, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_15Layout.setVerticalGroup(
            jPanelSP_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_15Layout.createSequentialGroup()
                .addComponent(jLabelSP_img15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_15)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelSP_16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSp_16.setText("jLabel3");

        jLabelGiaSp_16.setText("jLabel3");

        javax.swing.GroupLayout jPanelSP_16Layout = new javax.swing.GroupLayout(jPanelSP_16);
        jPanelSP_16.setLayout(jPanelSP_16Layout);
        jPanelSP_16Layout.setHorizontalGroup(
            jPanelSP_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSP_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSP_img16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTenSp_16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGiaSp_16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSP_16Layout.setVerticalGroup(
            jPanelSP_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSP_16Layout.createSequentialGroup()
                .addComponent(jLabelSP_img16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTenSp_16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGiaSp_16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMenuSPLayout = new javax.swing.GroupLayout(jPanelMenuSP);
        jPanelMenuSP.setLayout(jPanelMenuSPLayout);
        jPanelMenuSPLayout.setHorizontalGroup(
            jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSP_13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSPLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jPanelSP_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanelSP_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelSP_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanelSP_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelSP_7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanelSP_14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelSP_10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanelSP_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                        .addComponent(jPanelSP_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(jPanelSP_8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelSP_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelSP_16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanelMenuSPLayout.setVerticalGroup(
            jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSP_2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jPanelSP_4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jPanelSP_3, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jPanelSP_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSP_7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSP_11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSP_15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSP_16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(222, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanelMenuSP);

        jTableHoaDonCho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Người Tạo", "Khách Hàng", "TG Tạo", "Trạng Thái", "Ghi Chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableHoaDonCho.setGridColor(new java.awt.Color(0, 0, 0));
        jTableHoaDonCho.setShowGrid(true);
        jScrollPane2.setViewportView(jTableHoaDonCho);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("HÓA ĐƠN CHỜ");

        jLabelTenSP2.setText("GIÁ TỪ:");

        jTextFieldGiaTu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGiaTuActionPerformed(evt);
            }
        });

        jLabelTenSP3.setText("GIÁ ĐẾN:");

        jTextFieldGiaDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGiaDenActionPerformed(evt);
            }
        });

        jRadioButtonTheoTen.setText("THEO TÊN");

        jRadioButtonTheoLoai.setText("THEO LOẠI");
        jRadioButtonTheoLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTheoLoaiActionPerformed(evt);
            }
        });

        jRadioButtonTheoGia.setText("THEO GIÁ");
        jRadioButtonTheoGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTheoGiaActionPerformed(evt);
            }
        });

        jRadioButtonTatCa.setText("TẤT CẢ");

        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTaoHoaDon.setText("TẠO HÓA ĐƠN");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBanHangLayout = new javax.swing.GroupLayout(jPanelBanHang);
        jPanelBanHang.setLayout(jPanelBanHangLayout);
        jPanelBanHangLayout.setHorizontalGroup(
            jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBanHangLayout.createSequentialGroup()
                .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanelBanHangLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelBanHangLayout.createSequentialGroup()
                                    .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelTenSP1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldGiaTu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelTenSP2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(50, 50, 50)
                                    .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelTenSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelBanHangLayout.createSequentialGroup()
                                    .addComponent(jRadioButtonTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonTheoLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelBanHangLayout.createSequentialGroup()
                                    .addComponent(jRadioButtonTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButtonTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(48, 48, 48))
                        .addGroup(jPanelBanHangLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBanHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBanHangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );
        jPanelBanHangLayout.setVerticalGroup(
            jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBanHangLayout.createSequentialGroup()
                .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelBanHangLayout.createSequentialGroup()
                        .addComponent(jLabelTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonTheoTen)
                            .addComponent(jRadioButtonTheoLoai))
                        .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBanHangLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabelTenSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBanHangLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonTheoGia)
                                    .addComponent(jRadioButtonTatCa)))))
                    .addGroup(jPanelBanHangLayout.createSequentialGroup()
                        .addGroup(jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTenSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(381, 381, 381))
        );

        jPanelHoaDon.setBorder(new javax.swing.border.MatteBorder(null));

        jButtonThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonThem.setText("THÊM");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSua.setText("SỬA");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonXoa.setText("XÓA");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        jTabbedPaneHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableHoaDonDat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn Giá", "Số Lượng", "Thành Tiền", "Ghi Chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHoaDonDat.setGridColor(new java.awt.Color(0, 0, 0));
        jTableHoaDonDat.setShowGrid(true);
        jScrollPane3.setViewportView(jTableHoaDonDat);
        if (jTableHoaDonDat.getColumnModel().getColumnCount() > 0) {
            jTableHoaDonDat.getColumnModel().getColumn(0).setResizable(false);
            jTableHoaDonDat.getColumnModel().getColumn(1).setResizable(false);
            jTableHoaDonDat.getColumnModel().getColumn(2).setResizable(false);
            jTableHoaDonDat.getColumnModel().getColumn(3).setResizable(false);
            jTableHoaDonDat.getColumnModel().getColumn(4).setResizable(false);
            jTableHoaDonDat.getColumnModel().getColumn(5).setResizable(false);
        }

        jTabbedPaneHoaDon.addTab("tab1", jScrollPane3);

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelSoDienThoai.setText("SĐT:");

        jLabelHoTenKhachHang.setText("HỌ TÊN:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("GHI CHÚ:");

        jTextFieldHoTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHoTenKhachHangActionPerformed(evt);
            }
        });

        jTextAreaGhiChu.setColumns(20);
        jTextAreaGhiChu.setRows(5);
        jScrollPane4.setViewportView(jTextAreaGhiChu);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHoTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldHoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelSoDienThoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelHoTenKhachHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldHoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("TỔNG TIỀN SP:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("TỔNG TIỀN HD:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("TIỀN KHÁCH TRẢ:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("TIỀN THỪA:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("LOẠI TT:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("TRẠNG THÁI:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("ĐỊA CHỈ:");

        jLabelTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTrangThai.setText("X");

        jComboBoxLoaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản", "Quẹt Thẻ" }));

        jTextAreaDiaChi.setColumns(20);
        jTextAreaDiaChi.setRows(5);
        jScrollPane5.setViewportView(jTextAreaDiaChi);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldTongTienHoaDon)
                            .addComponent(jTextFieldTongTienSP)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldTienKhachTra)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10)
                                    .addComponent(jComboBoxLoaiThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabelTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTienThua))))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTongTienSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTongTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTrangThai)
                    .addComponent(jComboBoxLoaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jButtonHuyHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonHuyHD.setText("HỦY");

        jButtonTaoHoaDonCho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonTaoHoaDonCho.setText("TẠO HD CHỜ");
        jButtonTaoHoaDonCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaoHoaDonChoActionPerformed(evt);
            }
        });

        jButtonThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonThanhToan.setText("THANH TOÁN");

        jLabelTenNV.setText("Tên NV");

        jButtonDangXuat.setText("Đăng xuất");
        jButtonDangXuat.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangXuatActionPerformed(evt);
            }
        });

        jLabelNgayGio.setText("Ngày");
        jLabelNgayGio.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanelHoaDonLayout = new javax.swing.GroupLayout(jPanelHoaDon);
        jPanelHoaDon.setLayout(jPanelHoaDonLayout);
        jPanelHoaDonLayout.setHorizontalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jButtonHuyHD)
                .addGap(18, 18, 18)
                .addComponent(jButtonTaoHoaDonCho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonThanhToan)
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHoaDonLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addComponent(jLabelNgayGio, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jLabelTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPaneHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addComponent(jButtonThem)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSua)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonXoa)))
                .addGap(20, 20, 20))
        );
        jPanelHoaDonLayout.setVerticalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNgayGio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThem)
                    .addComponent(jButtonSua)
                    .addComponent(jButtonXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPaneHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHuyHD)
                    .addComponent(jButtonTaoHoaDonCho)
                    .addComponent(jButtonThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelRightLayout = new javax.swing.GroupLayout(jPanelRight);
        jPanelRight.setLayout(jPanelRightLayout);
        jPanelRightLayout.setHorizontalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelRightLayout.setVerticalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1350, 880));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
    
        jPanelRight.removeAll();
        jPanelRight.setLayout(new GridLayout(1, 2));
        jPanelRight.add(jPanelBanHang);
        jPanelRight.add(new JPanel());  // Phần bên phải trống
        jPanelBanHang.setVisible(true);
        jPanelRight.revalidate();
        jPanelRight.repaint();
// // Hiển thị panel Bán Hàng
//    jPanelRight.removeAll();
//    jPanelRight.setLayout(new BorderLayout());
//    jPanelRight.add(jPanelBanHang, BorderLayout.CENTER);
//    
//    // Cập nhật giao diện
//    jPanelRight.revalidate();
//    jPanelRight.repaint();
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
         new frmTrangChu().setVisible(true); // Hiển thị form khách hàng
        this.dispose(); // Đóng form hiện tại
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:

        jPanelRight.removeAll();  // Xóa các thành phần hiện tại trong jPanelRight
        jPanelRight.setLayout(new GridLayout(1, 2));
        
        // Thêm cả hai panel jPanelBanHang và jPanelHoaDon vào jPanelRight
        jPanelRight.add(jPanelBanHang);    // Nửa trái là jPanelBanHang
        jPanelRight.add(jPanelHoaDon);     // Nửa phải là jPanelHoaDon
        
        jPanelBanHang.setVisible(true);    // Giữ jPanelBanHang hiện diện
        jPanelHoaDon.setVisible(true);     // Hiển thị jPanelHoaDon
        jPanelRight.revalidate();
        jPanelRight.repaint();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
        // TODO add your handling code here:
        new frmDatBan().setVisible(true); // Hiển thị form khách hàng
        this.dispose(); // Đóng form hiện tại
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void btnQuanLyBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyBanActionPerformed
        // TODO add your handling code here:
        new frmQuanLyBan().setVisible(true); // Hiển thị form khách hàng
        this.dispose(); // Đóng form hiện tại
    }//GEN-LAST:event_btnQuanLyBanActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
     
// Lấy danh sách sản phẩm từ database
IsSearch = true;
        allSanPhams = connect.getAllSanPham();
        filteredSanPhams.clear();

        // Lấy thông tin từ các thành phần giao diện để tìm kiếm
        String tenSanPham = jTextFieldTenSP.getText().trim().toLowerCase();
        String selectedLoai = (String) jComboBoxLoaiSP.getSelectedItem();
        double giaTu = 0, giaDen = Double.MAX_VALUE;
        if (jRadioButtonTheoGia.isSelected()) {
            try {
                giaTu = Double.parseDouble(jTextFieldGiaTu.getText());
                giaDen = Double.parseDouble(jTextFieldGiaDen.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giá hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Lọc danh sách sản phẩm dựa vào tiêu chí tìm kiếm
        for (SanPham sp : allSanPhams) {
            boolean matches = false;
            if (jRadioButtonTheoTen.isSelected()) {
                matches = sp.getTenSanPham().toLowerCase().contains(tenSanPham);
            } else if (jRadioButtonTheoLoai.isSelected()) {
                matches = sp.getTenLoai().equalsIgnoreCase(selectedLoai);
            } else if (jRadioButtonTheoGia.isSelected()) {
                matches = sp.getDonGia() >= giaTu && sp.getDonGia() <= giaDen;
            } else if (jRadioButtonTatCa.isSelected()) {
                matches = true;
            }

            if (matches) {
                filteredSanPhams.add(sp);
            }
        }
        FoundProductDisplay(filteredSanPhams);
        // Nếu không có sản phẩm nào phù hợp, hiển thị thông báo
        if (filteredSanPhams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButtonTimKiemActionPerformed
private void FoundProductDisplay(List<SanPham> sanPhams) {
        jPanelMenuSP.removeAll();
        // Cài đặt FlowLayout cho jPanelMenuSP
        jPanelMenuSP.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15)); // Cách đều 15px
// Đặt kích thước cụ thể cho jPanelMenuSP
        jPanelMenuSP.setPreferredSize(new Dimension(700, 1000));

        // Cập nhật giao diện hiển thị sản phẩm dựa trên danh sách được truyền vào
        // Danh sách các JLabel cho tên và giá sản phẩm
        JLabel[] tenSpLabels = {jLabelTenSp_01, jLabelTenSp_02, jLabelTenSp_03, jLabelTenSp_04,
            jLabelTenSp_05, jLabelTenSp_06, jLabelTenSp_07, jLabelTenSp_08,
            jLabelTenSp_09, jLabelTenSp_10, jLabelTenSp_11, jLabelTenSp_12,
            jLabelTenSp_13, jLabelTenSp_14, jLabelTenSp_15, jLabelTenSp_16};
        JLabel[] giaSpLabels = {jLabelGiaSp_01, jLabelGiaSp_02, jLabelGiaSp_03, jLabelGiaSp_04,
            jLabelGiaSp_05, jLabelGiaSp_06, jLabelGiaSp_07, jLabelGiaSp_08,
            jLabelGiaSp_09, jLabelGiaSp_10, jLabelGiaSp_11, jLabelGiaSp_12,
            jLabelGiaSp_13, jLabelGiaSp_14, jLabelGiaSp_15, jLabelGiaSp_16};

        JLabel[] imgSpLabels = {jLabelSP_img1, jLabelSP_img2, jLabel_img3, jLabelSP_img4,
            jLabelSP_img5, jLabelSP_img6, jLabelSP_img7, jLabelSP_img8,
            jLabelSP_img9, jLabelSP_img10, jLabelSP_img11, jLabelSP_img12,
            jLabelSP_img13, jLabelSP_img14, jLabelSP_img15, jLabelSP_img16};

        JPanel[] panelSP = {jPanelSP_1, jPanelSP_2, jPanelSP_3, jPanelSP_4,
            jPanelSP_5, jPanelSP_6, jPanelSP_7, jPanelSP_8,
            jPanelSP_9, jPanelSP_10, jPanelSP_11, jPanelSP_12,
            jPanelSP_13, jPanelSP_14, jPanelSP_15, jPanelSP_16};

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
            jPanelMenuSP.add(panelSP[i]);
        }

        // Cập nhật giao diện
        jPanelMenuSP.revalidate();
        jPanelMenuSP.repaint();
    }
    private void jTextFieldTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenSPActionPerformed

    private void jTextFieldGiaTuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGiaTuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGiaTuActionPerformed

    private void jTextFieldGiaDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGiaDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGiaDenActionPerformed

    private void jRadioButtonTheoLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTheoLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonTheoLoaiActionPerformed

    private void jRadioButtonTheoGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTheoGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonTheoGiaActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        new frmQuanLyHoaDon().setVisible(true); // Hiển thị form khách hàng
        this.dispose(); // Đóng form hiện tại
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        JPanel[] panelSP = {jPanelSP_1, jPanelSP_2, jPanelSP_3, jPanelSP_4,
            jPanelSP_5, jPanelSP_6, jPanelSP_7, jPanelSP_8,
            jPanelSP_9, jPanelSP_10, jPanelSP_11, jPanelSP_12,
            jPanelSP_13, jPanelSP_14, jPanelSP_15, jPanelSP_16};
        if (IsSearch == false) {
    for (int i = 0; i < allSanPhams.size(); i++) {
            JPanel panel = panelSP[i];
            JSpinner spinner = (JSpinner) ((JPanel) panel.getComponent(2)).getComponent(1); // Lấy spinner từ panel
            int soLuong = (int) spinner.getValue();

            if (soLuong > 0) {
                SanPham sp = allSanPhams.get(i);

                // Thêm sản phẩm vào jTableHoaDonDat
                DefaultTableModel model = (DefaultTableModel) jTableHoaDonDat.getModel();
                model.addRow(new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.getDonGia(), soLuong, sp.getDonGia() * soLuong});

                // Reset giá trị spinner về 0
                spinner.setValue(0);
            }
        }
} else {
    for (int i = 0; i < filteredSanPhams.size() && i < 16; i++) {
            JPanel panel = panelSP[i];

            // Kiểm tra nếu panel có đủ thành phần
            if (panel.getComponentCount() > 0) {
                // Lấy thành phần `bottomPanel` tại vị trí BorderLayout.SOUTH
                Component bottomComponent = panel.getComponent(1); // 1 vì BorderLayout.SOUTH được thêm cuối
                if (bottomComponent instanceof JPanel) {
                    JPanel bottomPanel = (JPanel) bottomComponent;

                    // Kiểm tra và tìm `JSpinner` trong bottomPanel
                    for (Component comp : bottomPanel.getComponents()) {
                        if (comp instanceof JSpinner) {
                            JSpinner spinner = (JSpinner) comp;
                            int soLuong = (int) spinner.getValue();

                            if (soLuong > 0) {
                                SanPham sp = filteredSanPhams.get(i);

                                // Thêm sản phẩm vào bảng hóa đơn
                                DefaultTableModel model = (DefaultTableModel) jTableHoaDonDat.getModel();
                                model.addRow(new Object[]{
                                    sp.getMaSanPham(),
                                    sp.getTenSanPham(),
                                    sp.getDonGia(),
                                    soLuong,
                                    sp.getDonGia() * soLuong
                                });

                                // Reset giá trị spinner về 0
                                spinner.setValue(0);
                            }
                        }
                    }
                }
            }
        }
}
        
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
            try {
            // Lấy mô hình dữ liệu của bảng jTableHoaDonDat
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableHoaDonDat.getModel();
             Connect connect = new Connect();
            List<SanPham> sanPhams = connect.getAllSanPham();
            // Tạo danh sách các Spinner tương ứng với sản phẩm
            JSpinner[] spinners = {
                (JSpinner) ((JPanel) jPanelSP_1.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_2.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_3.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_4.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_5.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_6.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_7.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_8.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_9.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_10.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_11.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_12.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_13.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_14.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_15.getComponent(2)).getComponent(1),
                (JSpinner) ((JPanel) jPanelSP_16.getComponent(2)).getComponent(1),
            };
            // Lấy chỉ số của dòng được chọn trong bảng
            int selectedRow = jTableHoaDonDat.getSelectedRow();

            // Kiểm tra nếu không có dòng nào được chọn
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin sản phẩm từ dòng đã chọn
            String maSanPham = model.getValueAt(selectedRow, 0).toString();
            String tenSanPham = model.getValueAt(selectedRow, 1).toString();
            double donGia = (double) model.getValueAt(selectedRow, 2);
            int soLuongHienTai = (int) model.getValueAt(selectedRow, 3);
            double thanhTienHienTai = (double) model.getValueAt(selectedRow, 4);

            // Tạo cửa sổ nhập liệu để người dùng sửa số lượng và ghi chú
            JPanel inputPanel = new JPanel(new GridLayout(2, 2));

            // Cung cấp các trường nhập liệu cho số lượng và ghi chú
            JTextField soLuongField = new JTextField(String.valueOf(soLuongHienTai));
            JTextField ghiChuField = new JTextField(""); // Để trống ghi chú ban đầu

            inputPanel.add(new JLabel("Số Lượng:"));
            inputPanel.add(soLuongField);
            inputPanel.add(new JLabel("Ghi Chú:"));
            inputPanel.add(ghiChuField);

            // Hiển thị hộp thoại nhập liệu
            int option = JOptionPane.showConfirmDialog(this, inputPanel, "Sửa thông tin sản phẩm", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // Lấy số lượng và ghi chú mới từ các trường nhập liệu
                int soLuongMoi = Integer.parseInt(soLuongField.getText());
                String ghiChuMoi = ghiChuField.getText();

                // Kiểm tra số lượng phải lớn hơn 0
                if (soLuongMoi <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Cập nhật lại thông tin trong bảng
                double thanhTienMoi = donGia * soLuongMoi;
                model.setValueAt(soLuongMoi, selectedRow, 3); // Cập nhật số lượng
                model.setValueAt(thanhTienMoi, selectedRow, 4); // Cập nhật thành tiền
                // Nếu bạn có cột ghi chú, bạn có thể cập nhật ghi chú vào cột mới, ví dụ ở cột 5
                model.setValueAt(ghiChuMoi, selectedRow, 5); // Cập nhật ghi chú

                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(this, "Thông tin sản phẩm đã được cập nhật!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            // Xử lý lỗi
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        try {
           // Lấy mô hình dữ liệu của bảng jTableHoaDonDat
           javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableHoaDonDat.getModel();

           // Lấy chỉ số của các dòng được chọn trong bảng
           int[] selectedRows = jTableHoaDonDat.getSelectedRows();

           // Kiểm tra nếu không có dòng nào được chọn
           if (selectedRows.length == 0) {
               JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
               return;
           }

           // Xác nhận xóa các sản phẩm được chọn
           int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các sản phẩm này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
           if (confirm == JOptionPane.YES_OPTION) {
               // Lặp qua các dòng đã chọn và xóa từng dòng (xóa từ cuối lên để tránh thay đổi chỉ số dòng)
               for (int i = selectedRows.length - 1; i >= 0; i--) {
                   int row = selectedRows[i];
                   model.removeRow(row);
               }

               // Hiển thị thông báo thành công
               JOptionPane.showMessageDialog(this, "Các sản phẩm đã được xóa khỏi bảng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
           }
       } catch (Exception ex) {
           // Xử lý lỗi
           JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
       }
    
    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jTextFieldHoTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHoTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHoTenKhachHangActionPerformed

    private void jButtonTaoHoaDonChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaoHoaDonChoActionPerformed
        // TODO add your handling code here:
        try {
        // Kết nối tới cơ sở dữ liệu
        Connect connect = new Connect();
        
        // Lấy số lượng hóa đơn hiện tại để tạo mã hóa đơn mới
        int soLuongHoaDon = connect.getSoLuongHoaDon(); // Hàm này cần trả về số lượng hóa đơn trong bảng [HoaDon]
        String maHoaDon = "HD" + String.format("%02d", soLuongHoaDon + 1); // Tạo mã HD01, HD02,...
        
        // Lấy thông tin từ giao diện
        String nguoiTao = jLabelTenNV.getText().replace("Nhân viên: ", "").trim(); // Tên nhân viên từ JLabel
        String thoiGianTao = jLabelNgayGio.getText(); // Lấy thời gian từ JLabel
        String trangThai = "Chờ Order"; // Trạng thái mặc định
        String ghiChu = ""; // Ghi chú từ bảng jTableHoaDonDat
        
        // Tổng hợp ghi chú từ cột "Ghi Chú" trong jTableHoaDonDat
        DefaultTableModel modelHoaDonDat = (DefaultTableModel) jTableHoaDonDat.getModel();
        for (int i = 0; i < modelHoaDonDat.getRowCount(); i++) {
            String note = modelHoaDonDat.getValueAt(i, modelHoaDonDat.findColumn("Ghi Chú")).toString();
            ghiChu += note + "; "; // Gộp các ghi chú (nếu có nhiều hàng)
        }
        if (!ghiChu.isEmpty()) {
            ghiChu = ghiChu.substring(0, ghiChu.length() - 2); // Xóa ký tự ";" cuối cùng
        }

        // Cập nhật dữ liệu lên bảng jTableHoaDonCho
        DefaultTableModel modelHoaDonCho = (DefaultTableModel) jTableHoaDonCho.getModel();
        modelHoaDonCho.addRow(new Object[]{maHoaDon, nguoiTao, "", thoiGianTao, trangThai, ghiChu});
        
        // Thêm dữ liệu vào cơ sở dữ liệu
        boolean insertResult = connect.themHoaDonMoi(maHoaDon, nguoiTao, thoiGianTao, trangThai, ghiChu);
        if (insertResult) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn chờ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn vào cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButtonTaoHoaDonChoActionPerformed

    private void jButtonDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangXuatActionPerformed
        // TODO add your handling code here:
        frmLoginNV LoginNV = new frmLoginNV();
        LoginNV.setVisible(true);  // Mở form frmLoginVaitro
        dispose();  // Đóng form hiện tại (frmLoginNhanVien)


    }//GEN-LAST:event_jButtonDangXuatActionPerformed
 class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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
            java.util.logging.Logger.getLogger(frmQuanLyBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQuanLyBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnQuanLyBan;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonDangXuat;
    private javax.swing.JButton jButtonHuyHD;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonTaoHoaDonCho;
    private javax.swing.JButton jButtonThanhToan;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JComboBox<String> jComboBoxLoaiSP;
    private javax.swing.JComboBox<String> jComboBoxLoaiThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelGiaSp_01;
    private javax.swing.JLabel jLabelGiaSp_02;
    private javax.swing.JLabel jLabelGiaSp_03;
    private javax.swing.JLabel jLabelGiaSp_04;
    private javax.swing.JLabel jLabelGiaSp_05;
    private javax.swing.JLabel jLabelGiaSp_06;
    private javax.swing.JLabel jLabelGiaSp_07;
    private javax.swing.JLabel jLabelGiaSp_08;
    private javax.swing.JLabel jLabelGiaSp_09;
    private javax.swing.JLabel jLabelGiaSp_10;
    private javax.swing.JLabel jLabelGiaSp_11;
    private javax.swing.JLabel jLabelGiaSp_12;
    private javax.swing.JLabel jLabelGiaSp_13;
    private javax.swing.JLabel jLabelGiaSp_14;
    private javax.swing.JLabel jLabelGiaSp_15;
    private javax.swing.JLabel jLabelGiaSp_16;
    private javax.swing.JLabel jLabelHoTenKhachHang;
    private javax.swing.JLabel jLabelNgayGio;
    private javax.swing.JLabel jLabelSP_img1;
    private javax.swing.JLabel jLabelSP_img10;
    private javax.swing.JLabel jLabelSP_img11;
    private javax.swing.JLabel jLabelSP_img12;
    private javax.swing.JLabel jLabelSP_img13;
    private javax.swing.JLabel jLabelSP_img14;
    private javax.swing.JLabel jLabelSP_img15;
    private javax.swing.JLabel jLabelSP_img16;
    private javax.swing.JLabel jLabelSP_img2;
    private javax.swing.JLabel jLabelSP_img4;
    private javax.swing.JLabel jLabelSP_img5;
    private javax.swing.JLabel jLabelSP_img6;
    private javax.swing.JLabel jLabelSP_img7;
    private javax.swing.JLabel jLabelSP_img8;
    private javax.swing.JLabel jLabelSP_img9;
    private javax.swing.JLabel jLabelSoDienThoai;
    private javax.swing.JLabel jLabelTenNV;
    private javax.swing.JLabel jLabelTenSP;
    private javax.swing.JLabel jLabelTenSP1;
    private javax.swing.JLabel jLabelTenSP2;
    private javax.swing.JLabel jLabelTenSP3;
    private javax.swing.JLabel jLabelTenSp_01;
    private javax.swing.JLabel jLabelTenSp_02;
    private javax.swing.JLabel jLabelTenSp_03;
    private javax.swing.JLabel jLabelTenSp_04;
    private javax.swing.JLabel jLabelTenSp_05;
    private javax.swing.JLabel jLabelTenSp_06;
    private javax.swing.JLabel jLabelTenSp_07;
    private javax.swing.JLabel jLabelTenSp_08;
    private javax.swing.JLabel jLabelTenSp_09;
    private javax.swing.JLabel jLabelTenSp_10;
    private javax.swing.JLabel jLabelTenSp_11;
    private javax.swing.JLabel jLabelTenSp_12;
    private javax.swing.JLabel jLabelTenSp_13;
    private javax.swing.JLabel jLabelTenSp_14;
    private javax.swing.JLabel jLabelTenSp_15;
    private javax.swing.JLabel jLabelTenSp_16;
    private javax.swing.JLabel jLabelTrangThai;
    private javax.swing.JLabel jLabel_img3;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanelBanHang;
    private javax.swing.JPanel jPanelHoaDon;
    private javax.swing.JPanel jPanelMenuSP;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelSP_1;
    private javax.swing.JPanel jPanelSP_10;
    private javax.swing.JPanel jPanelSP_11;
    private javax.swing.JPanel jPanelSP_12;
    private javax.swing.JPanel jPanelSP_13;
    private javax.swing.JPanel jPanelSP_14;
    private javax.swing.JPanel jPanelSP_15;
    private javax.swing.JPanel jPanelSP_16;
    private javax.swing.JPanel jPanelSP_2;
    private javax.swing.JPanel jPanelSP_3;
    private javax.swing.JPanel jPanelSP_4;
    private javax.swing.JPanel jPanelSP_5;
    private javax.swing.JPanel jPanelSP_6;
    private javax.swing.JPanel jPanelSP_7;
    private javax.swing.JPanel jPanelSP_8;
    private javax.swing.JPanel jPanelSP_9;
    private javax.swing.JPanel jPanelThanhQuanLy;
    private javax.swing.JRadioButton jRadioButtonTatCa;
    private javax.swing.JRadioButton jRadioButtonTheoGia;
    private javax.swing.JRadioButton jRadioButtonTheoLoai;
    private javax.swing.JRadioButton jRadioButtonTheoTen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPaneHoaDon;
    private javax.swing.JTable jTableHoaDonCho;
    private javax.swing.JTable jTableHoaDonDat;
    private javax.swing.JTextArea jTextAreaDiaChi;
    private javax.swing.JTextArea jTextAreaGhiChu;
    private javax.swing.JTextField jTextFieldGiaDen;
    private javax.swing.JTextField jTextFieldGiaTu;
    private javax.swing.JTextField jTextFieldHoTenKhachHang;
    private javax.swing.JTextField jTextFieldSoDienThoai;
    private javax.swing.JTextField jTextFieldTenSP;
    private javax.swing.JTextField jTextFieldTienKhachTra;
    private javax.swing.JTextField jTextFieldTienThua;
    private javax.swing.JTextField jTextFieldTongTienHoaDon;
    private javax.swing.JTextField jTextFieldTongTienSP;
    // End of variables declaration//GEN-END:variables
}
