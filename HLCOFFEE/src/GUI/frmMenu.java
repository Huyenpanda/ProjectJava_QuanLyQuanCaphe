/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import Entity.SanPham;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class frmMenu extends javax.swing.JFrame {
    private final SanPham sp = new SanPham(null, null, null, (float) 0.0, null, null);
    private boolean cothem=true;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    //Ham do du lieu vao combobox public
     final void ShowDataCombo() {
        try (ResultSet result = sp.ShowLoaiSP()) {
            while (result.next()) {
                cboLoaiSP.addItem(result.getString("maLoai"));
            }
        } catch (SQLException e) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Error loading product types", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Hien thi ten loai theo ma loai trong combobox
        public void ShowTenloai(String ma) throws SQLException{
        ResultSet result= sp.ShowLoaiSP(ma);
        if(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
        txtTenloai.setText(result.getString("tenLoai"));
        }
        }
      public final void ShowData() {
    try (ResultSet result = sp.ShowSanpham()) {
        ClearData(); // Xóa dữ liệu cũ
        while (result.next()) {
            // Khởi tạo mảng với 6 phần tử, tương ứng với 6 cột
            String[] rows = new String[6];
            rows[0] = result.getString("maSanPham"); // Mã SP
            rows[1] = result.getString("tenSanPham"); // Tên SP
            rows[2] = String.valueOf(result.getFloat("donGia")); // Đơn giá
            rows[3] = result.getString("tenLoai"); // Tên Loại
            rows[4] = result.getString("donVi"); // Đơn vị
            rows[5] = result.getString("moTa"); // Mô tả

            tableModel.addRow(rows); // Thêm hàng vào bảng
        }
    } catch (SQLException e) {
        Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Error loading product data", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

  //Ham xoa du lieu trong tableModel
        public void ClearData() throws SQLException{
        //Lay chi so dong cuoi cung
        int n=tableModel.getRowCount()-1;
        for(int i=n;i>=0;i--)
        tableModel.removeRow(i);//Remove tung dong
        }
        //Ham xoa trang cac TextField
            private void setNull()
            {
            //Xoa trang cac JtextField
            txtMaSP.setText(null);
            txtMaSP.requestFocus();
            txtTenSP.setText(null);
            txtGiaban.setText(null);
            txtTenSP.setText(null);
            }
         //Ham khoa cac TextField
        private void setKhoa(boolean a)
        {
        //Khoa hoac mo khoa cho Cac JTextField
        txtMaSP.setEnabled(!a);
        txtTenSP.setEnabled(!a);
        txtGiaban.setEnabled(!a);
        cboLoaiSP.setEnabled(!a);
        txtTenloai. setEnabled(!a);
        txtDonVi.setEnabled(!a);
        txtMoTa.setEnabled(!a);
        }
        //Ham khoa cac Button
        private void setButton(boolean a)
        {
        //Vo hieu hoac co hieu luc cho cac JButton
        jButtonThem.setEnabled(a);
        jButtonXoa.setEnabled(a);
        jButtonSua.setEnabled(a);
        jButtonLuu.setEnabled(!a);
        jButtonKLuu.setEnabled(!a);
       
        }


    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
        initComponents();
        this.setTitle("Menu");
        String[] colsName = {"Mã SP", "Tên sản phẩm", "Giá bán", "Tên Loại","Đơn Vị","Mô Tả"};
        tableModel.setColumnIdentifiers(colsName);
        jTableSanPham.setModel(tableModel);
        ShowData();
        ShowDataCombo();
        setNull();
        setKhoa(true);
        setButton(true);
         buttonGroup1.add(jRadioButtonTheoTen); // Group các radio buttons
        buttonGroup1.add(jRadioButtonTheoGia);
        loadAllProducts(); 
         // Gắn sự kiện cho các radio buttons
        jRadioButtonTheoTen.addActionListener(e -> toggleSearchFields());
        jRadioButtonTheoGia.addActionListener(e -> toggleSearchFields());

        // Thiết lập mặc định (ẩn các trường nhập giá ban đầu)
        toggleSearchFields();
        }
        private void toggleSearchFields() {
        if (jRadioButtonTheoTen.isSelected()) { // Tìm theo tên
            jTextFieldTenTimKiem.setEnabled(true);
            jTextFieldGiaTu.setEnabled(false);
            jTextFieldGiaDen.setEnabled(false);
            jTextFieldGiaTu.setText(""); // Xóa nội dung các trường không dùng
            jTextFieldGiaDen.setText("");
        } else if (jRadioButtonTheoGia.isSelected()) { // Tìm theo giá
            jTextFieldTenTimKiem.setEnabled(false);
            jTextFieldGiaTu.setEnabled(true);
            jTextFieldGiaDen.setEnabled(true);
            jTextFieldTenTimKiem.setText(""); // Xóa nội dung các trường không dùng
        }

    }
        private void loadAllProducts() {
        try {
            ResultSet rs = sp.ShowSanpham();
            fillTable(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sản phẩm: " + e.getMessage());
        }
    }
    private void fillTable(ResultSet rs) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) jTableSanPham.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("maSanPham"),
                rs.getString("tenSanPham"),
                rs.getInt("donGia"),
                rs.getString("tenLoai"),
                rs.getString("donVi"),
                rs.getString("moTa")
                    
            });
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelThanhQuanLy = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JButton();
        btnTrangChu = new javax.swing.JButton();
        btnDatBan = new javax.swing.JButton();
        btnQuanLyBan = new javax.swing.JButton();
        btnQuanLyMenu = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        jPanelMenu = new javax.swing.JPanel();
        jPanelCapNhatSp = new javax.swing.JPanel();
        jLabelMaSp = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabelTenSp = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        cboLoaiSP = new javax.swing.JComboBox<>();
        txtTenloai = new javax.swing.JTextField();
        jLabelLoaiSp = new javax.swing.JLabel();
        jLabelGiaBan = new javax.swing.JLabel();
        txtGiaban = new javax.swing.JTextField();
        jLabelDonVi = new javax.swing.JLabel();
        txtDonVi = new javax.swing.JTextField();
        jLabelMoTa = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonLuu = new javax.swing.JButton();
        jButtonKLuu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanelTimKiemSp = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelSpTim = new javax.swing.JPanel();
        jLabelTenTimKiem = new javax.swing.JLabel();
        jTextFieldTenTimKiem = new javax.swing.JTextField();
        jRadioButtonTheoTen = new javax.swing.JRadioButton();
        jRadioButtonTheoGia = new javax.swing.JRadioButton();
        jLabelGiaTu = new javax.swing.JLabel();
        jTextFieldGiaTu = new javax.swing.JTextField();
        jLabelGiaDen = new javax.swing.JLabel();
        jButtonTimKiemSp = new javax.swing.JButton();
        jTextFieldGiaDen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSanPham = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnQuanLyMenu.setText("QUẢN LÝ MENU");
        btnQuanLyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyMenuActionPerformed(evt);
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
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
            .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnQuanLyBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnQuanLyMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(31, 31, 31)
                .addComponent(btnQuanLyMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanelMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelCapNhatSp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelMaSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelMaSp.setText("Mã SP:");

        jLabelTenSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelTenSp.setText("Tên sản phẩm:");

        cboLoaiSP.setBackground(new java.awt.Color(153, 204, 255));
        cboLoaiSP.setEditable(true);
        cboLoaiSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiSPItemStateChanged(evt);
            }
        });

        jLabelLoaiSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelLoaiSp.setText("Loại SP:");

        jLabelGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelGiaBan.setText("Giá bán:");

        jLabelDonVi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelDonVi.setText("Đơn Vị:");

        jLabelMoTa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelMoTa.setText("Mô Tả:");

        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonSua.setText("Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        jButtonLuu.setText("Lưu");
        jButtonLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLuuActionPerformed(evt);
            }
        });

        jButtonKLuu.setText("K.Lưu");
        jButtonKLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKLuuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("CẬP NHẬT SẢN PHẨM ");

        javax.swing.GroupLayout jPanelCapNhatSpLayout = new javax.swing.GroupLayout(jPanelCapNhatSp);
        jPanelCapNhatSp.setLayout(jPanelCapNhatSpLayout);
        jPanelCapNhatSpLayout.setHorizontalGroup(
            jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCapNhatSpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCapNhatSpLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCapNhatSpLayout.createSequentialGroup()
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabelTenSp))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCapNhatSpLayout.createSequentialGroup()
                                .addComponent(txtGiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelLoaiSp)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCapNhatSpLayout.createSequentialGroup()
                                .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenloai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenSP)))
                    .addGroup(jPanelCapNhatSpLayout.createSequentialGroup()
                        .addComponent(jLabelDonVi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCapNhatSpLayout.createSequentialGroup()
                                .addComponent(jButtonThem)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonSua)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonXoa)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonLuu)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonKLuu))
                            .addGroup(jPanelCapNhatSpLayout.createSequentialGroup()
                                .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jLabelMoTa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCapNhatSpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(130, 130, 130))
        );
        jPanelCapNhatSpLayout.setVerticalGroup(
            jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCapNhatSpLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaSp)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTenSp)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGiaBan)
                    .addComponent(txtGiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLoaiSp)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDonVi)
                    .addComponent(jLabelMoTa)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCapNhatSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThem)
                    .addComponent(jButtonSua)
                    .addComponent(jButtonXoa)
                    .addComponent(jButtonLuu)
                    .addComponent(jButtonKLuu))
                .addGap(54, 54, 54))
        );

        jPanelTimKiemSp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("TÌM KIẾM SẢN PHẨM");

        jPanelSpTim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelTenTimKiem.setText("Tên sản phẩm:");

        jRadioButtonTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jRadioButtonTheoTen.setText("Theo Tên");

        jRadioButtonTheoGia.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jRadioButtonTheoGia.setText("Theo Giá");

        jLabelGiaTu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelGiaTu.setText("Giá từ:");

        jLabelGiaDen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelGiaDen.setText("Giá đến:");

        jButtonTimKiemSp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonTimKiemSp.setText("TÌM KIẾM");
        jButtonTimKiemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSpTimLayout = new javax.swing.GroupLayout(jPanelSpTim);
        jPanelSpTim.setLayout(jPanelSpTimLayout);
        jPanelSpTimLayout.setHorizontalGroup(
            jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSpTimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTenTimKiem)
                    .addComponent(jLabelGiaTu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSpTimLayout.createSequentialGroup()
                        .addComponent(jTextFieldGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelGiaDen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldGiaDen))
                    .addComponent(jTextFieldTenTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonTheoGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonTheoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanelSpTimLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jButtonTimKiemSp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSpTimLayout.setVerticalGroup(
            jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSpTimLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTenTimKiem)
                    .addComponent(jTextFieldTenTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonTheoTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSpTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonTheoGia)
                    .addComponent(jLabelGiaTu)
                    .addComponent(jTextFieldGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGiaDen)
                    .addComponent(jTextFieldGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonTimKiemSp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelTimKiemSpLayout = new javax.swing.GroupLayout(jPanelTimKiemSp);
        jPanelTimKiemSp.setLayout(jPanelTimKiemSpLayout);
        jPanelTimKiemSpLayout.setHorizontalGroup(
            jPanelTimKiemSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTimKiemSpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(102, 102, 102))
            .addGroup(jPanelTimKiemSpLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanelSpTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTimKiemSpLayout.setVerticalGroup(
            jPanelTimKiemSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTimKiemSpLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanelSpTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên NV: ");

        jTableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Giá Bán", "Tên Loại", "Đơn Vị", "Mô Tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSanPham);

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addComponent(jPanelCapNhatSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTimKiemSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelCapNhatSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 355, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelThanhQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelThanhQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed

       new frmQuanLyBanHang().setVisible(true); // Hiển thị form khách hàng
        this.dispose();
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
        new frmTrangChu().setVisible(true); // Hiển thị form khách hàng
        this.dispose(); // Đóng form hiện tại
    }//GEN-LAST:event_btnTrangChuActionPerformed

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

    private void btnQuanLyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyMenuActionPerformed
  
    }//GEN-LAST:event_btnQuanLyMenuActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        String ma=txtMaSP.getText();
        try {
        if(ma.length()==0)
        JOptionPane.showMessageDialog(null,"Can chon 1 SP de xoa","Thong bao",1);
        else
        {
        if(JOptionPane.showConfirmDialog(null, "Ban muon xoa sp " + ma + "này hay khong?","Thong bao",2)==0)
        {
        sp.DeleteSanpham(ma);//goi ham xoa du lieu theo ma loai
        ClearData();//Xoa du lieu trong tableModel
        ShowData();//Do du lieu vao table Model
        setNull();//Xoa trang Textfield
        }
        }
        }
        catch (SQLException ex) {
        Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void cboLoaiSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiSPItemStateChanged
        // TODO add your handling code here:
         try {
        String ml=cboLoaiSP.getSelectedItem().toString();
        ShowTenloai(ml);
        } catch (SQLException ex) {
        Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cboLoaiSPItemStateChanged

    private void jButtonTimKiemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemSpActionPerformed
        try {
        ResultSet rs = null;
        if (jRadioButtonTheoTen.isSelected()) { // Tìm theo tên sản phẩm
            String tenSanPham = jTextFieldTenTimKiem.getText();
            rs = sp.ShowSPTheoten(tenSanPham);
            if (!rs.isBeforeFirst()) { // Kiểm tra nếu không có dòng nào trong ResultSet
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm tên " + tenSanPham, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } else if (jRadioButtonTheoGia.isSelected()) { // Tìm theo khoảng giá
            int giaTu = Integer.parseInt(jTextFieldGiaTu.getText());
            int giaDen = Integer.parseInt(jTextFieldGiaDen.getText());
            rs = sp.ShowSPTheogia(giaTu, giaDen);
            if (!rs.isBeforeFirst()) { // Kiểm tra nếu không có dòng nào trong ResultSet
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm trong khoảng giá từ " + giaTu + " đến " + giaDen, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức tìm kiếm.");
            return;
        }
        fillTable(rs);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá phải là số !");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
    }

    }//GEN-LAST:event_jButtonTimKiemSpActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        setNull();//Xoa trang TextField
        setKhoa(false);//Mo khoa TextField
        setButton(false);//Goi ham khoa cac Button
        cothem=true;//Gan cothem = true de ghi nhan trang thai them moi

    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
    String ml=txtMaSP.getText();
        if(ml.length()==0) //Chua chon Ma loai 
        JOptionPane.showMessageDialog(null,"Vui long chon loi can sua", "Thong bao",1);
        else
        {
        setKhoa(false);//Mo khoa cac TextField
        setButton(false); //Khoa cac Button
        cothem=false; //Gan cothem=false de ghi nhan trang thai la sua
        }

    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLuuActionPerformed
        // TODO add your handling code here:
    String ma = txtMaSP.getText();
        String ten = txtTenSP.getText();
        int gia;
        try {
            gia = Integer.parseInt(txtGiaban.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Giá bán phải là số", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String loai = cboLoaiSP.getSelectedItem().toString();
        if (ma.length() == 0 || ten.length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Mã SP và Tên", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else if (ma.length() > 4 || ten.length() > 30) {
            JOptionPane.showMessageDialog(null, "Mã SP chỉ tối đa 4 ký tự, Tên SP tối đa 30 ký tự", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                 String dv = txtDonVi.getText();    // Đơn vị
                String moTa = txtMoTa.getText();   // Mô tả
                float dg = gia;                    // Giá bán (đã chuyển đổi ở phần trên)
                String ml = loai; 
                if (cothem) {
                    sp.InsertSanpham( ma,  ten,  dg,  dv,  moTa,  ml);
                } else {
                    sp.EditSanpham( ma, ten,  dg,  dv,  moTa,  ml);
                }
                ClearData();
                ShowData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            setNull();
            setKhoa(true);
            setButton(true);
        }

    }//GEN-LAST:event_jButtonLuuActionPerformed

    private void jButtonKLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKLuuActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
         setNull();
        setKhoa(true);
        setButton(true);

    }//GEN-LAST:event_jButtonKLuuActionPerformed

    private void jTableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSanPhamMouseClicked
        // TODO add your handling code here:
        //Hien thi du lieu len cac JTextField khi Click chuot vao JTable
        try{
        //Lay chi so dong dang chon
        int row =jTableSanPham.getSelectedRow();
        String ma=(jTableSanPham.getModel().getValueAt(row,0)).toString();
        ResultSet rs= sp.ShowSPTheoma(ma);//Goi ham lay du lieu theo ma loai
        if(rs.next())//Neu co du lieu
        {
        txtMaSP.setText(rs.getString("maSanPham"));
        txtTenSP.setText(rs.getString("tenSanPham"));
        txtGiaban.setText(rs.getString("donGia"));
        cboLoaiSP.setSelectedItem(rs.getString("maLoai"));
        txtTenloai.setText(rs.getString("tenLoai"));
        txtDonVi.setText(rs.getString("donVi"));
        txtMoTa.setText(rs.getString("moTa"));
        }
        }
         catch (SQLException e) {
    }
    }//GEN-LAST:event_jTableSanPhamMouseClicked

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
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnQuanLyBan;
    private javax.swing.JButton btnQuanLyMenu;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JButton jButtonKLuu;
    private javax.swing.JButton jButtonLuu;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiemSp;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDonVi;
    private javax.swing.JLabel jLabelGiaBan;
    private javax.swing.JLabel jLabelGiaDen;
    private javax.swing.JLabel jLabelGiaTu;
    private javax.swing.JLabel jLabelLoaiSp;
    private javax.swing.JLabel jLabelMaSp;
    private javax.swing.JLabel jLabelMoTa;
    private javax.swing.JLabel jLabelTenSp;
    private javax.swing.JLabel jLabelTenTimKiem;
    private javax.swing.JPanel jPanelCapNhatSp;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelSpTim;
    private javax.swing.JPanel jPanelThanhQuanLy;
    private javax.swing.JPanel jPanelTimKiemSp;
    private javax.swing.JRadioButton jRadioButtonTheoGia;
    private javax.swing.JRadioButton jRadioButtonTheoTen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSanPham;
    private javax.swing.JTextField jTextFieldGiaDen;
    private javax.swing.JTextField jTextFieldGiaTu;
    private javax.swing.JTextField jTextFieldTenTimKiem;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGiaban;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenloai;
    // End of variables declaration//GEN-END:variables
}
