import koneksi.koneksi;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class data_pelanggan extends javax.swing.JFrame {
// Components declaration
    private ButtonGroup bgJenisKelamin;
    private DefaultTableModel tableModel;
    
    public data_pelanggan() {
        initComponents();
        initTable();
        loadData();
    }
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtIDPelanggan = new javax.swing.JTextField();
        rbLakiLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        txtNoTelepon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel1.setText("Data Pelanggan");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        rbLakiLaki.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbLakiLaki);
        rbLakiLaki.setText("laki-laki");

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbPerempuan);
        rbPerempuan.setText("perempuan");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        jLabel2.setText("ID Pelanggan");

        jLabel3.setText("Nama Pelanggan");

        jLabel4.setText("Jenis Kelamin");

        jLabel5.setText("No Telp");

        jLabel6.setText("Alamat");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNoTelepon)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbLakiLaki)
                        .addGap(18, 18, 18)
                        .addComponent(rbPerempuan))
                    .addComponent(txtNamaPelanggan)
                    .addComponent(txtIDPelanggan)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLakiLaki)
                    .addComponent(rbPerempuan)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Pelanggan", "Nama Pelanggan", "Jenis Kelamin", "No Telp", "Alamat"
            }
        ));
        jScrollPane2.setViewportView(tblPelanggan);

        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 16)); // NOI18N
        jLabel7.setText("Data Pelanggan");

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("UBAH");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setBackground(new java.awt.Color(255, 51, 51));
        btnKeluar.setText("X");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKeluar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(35, 35, 35)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initTable() {
        String[] columns = {"ID Pelanggan", "Nama", "Jenis Kelamin", "No. Telepon", "Alamat"};
        tableModel = new DefaultTableModel(columns, 0);
        tblPelanggan.setModel(tableModel);
    }


    private void tblPelangganMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblPelanggan.getSelectedRow();
        if (row != -1) {
            showDataInForm(row);
        }
    }

    private void simpanData() {
        try {
            // Check if ID already exists
            Connection conn = getConnection();
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT id_pelanggan FROM data_pelanggan WHERE id_pelanggan = ?"
            );
            checkStmt.setString(1, txtIDPelanggan.getText());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, 
                    "ID Pelanggan sudah ada! Gunakan ID lain.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Insert new data
            String sql = "INSERT INTO data_pelanggan (id_pelanggan, nama_pelanggan, jenis_kelamin, no_telepon, alamat) " +
                        "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, txtIDPelanggan.getText().trim());
            pstmt.setString(2, txtNamaPelanggan.getText().trim());
            pstmt.setString(3, rbLakiLaki.isSelected() ? "Laki-Laki" : "Perempuan");
            pstmt.setString(4, txtNoTelepon.getText().trim());
            pstmt.setString(5, txtAlamat.getText().trim());
            
            int result = pstmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, 
                    "Data berhasil disimpan!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadData();
                resetForm();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ubahData() {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE data_pelanggan SET nama_pelanggan=?, jenis_kelamin=?, no_telepon=?, alamat=? " +
                        "WHERE id_pelanggan=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, txtNamaPelanggan.getText().trim());
            pstmt.setString(2, rbLakiLaki.isSelected() ? "Laki-Laki" : "Perempuan");
            pstmt.setString(3, txtNoTelepon.getText().trim());
            pstmt.setString(4, txtAlamat.getText().trim());
            pstmt.setString(5, txtIDPelanggan.getText().trim());
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Apakah anda yakin ingin mengubah data ini?", 
                "Konfirmasi", 
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, 
                        "Data berhasil diubah!", 
                        "Sukses", 
                        JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    resetForm();
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusData() {
        try {
            Connection conn = getConnection();
            String sql = "DELETE FROM data_pelanggan WHERE id_pelanggan=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, txtIDPelanggan.getText().trim());
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Apakah anda yakin ingin menghapus data ini?", 
                "Konfirmasi", 
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, 
                        "Data berhasil dihapus!", 
                        "Sukses", 
                        JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    resetForm();
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cariData() {
        String keyword = txtCari.getText().trim();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM data_pelanggan WHERE " +
                        "id_pelanggan LIKE ? OR " +
                        "nama_pelanggan LIKE ? OR " +
                        "no_telepon LIKE ? OR " +
                        "alamat LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("no_telepon"),
                    rs.getString("alamat")
                };
                tableModel.addRow(row);
            }
            
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, 
                    "Data tidak ditemukan!", 
                    "Informasi", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM data_pelanggan");
            
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("no_telepon"),
                    rs.getString("alamat")
                };
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void resetForm() {
        txtIDPelanggan.setText("");
        txtNamaPelanggan.setText("");
        bgJenisKelamin.clearSelection();
        txtNoTelepon.setText("");
        txtAlamat.setText("");
        txtIDPelanggan.requestFocus();
    }

    private void showDataInForm(int row) {
        txtIDPelanggan.setText(tableModel.getValueAt(row, 0).toString());
        txtNamaPelanggan.setText(tableModel.getValueAt(row, 1).toString());
        String jenisKelamin = tableModel.getValueAt(row, 2).toString();
        if (jenisKelamin.equals("Laki-Laki")) {
            rbLakiLaki.setSelected(true);
        } else {
            rbPerempuan.setSelected(true);
        }
        txtNoTelepon.setText(tableModel.getValueAt(row, 3).toString());
        txtAlamat.setText(tableModel.getValueAt(row, 4).toString());
    }

    private Connection getConnection() throws SQLException {
        return koneksi.koneksiDB();
    }
    
    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed

        resetForm();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
      if (txtCari.getText().isEmpty()) {
            loadData(); // If search field is empty, show all data
        } else {
            cariData();
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtIDPelanggan.getText().isEmpty() || 
            txtNamaPelanggan.getText().isEmpty() || 
            (!rbLakiLaki.isSelected() && !rbPerempuan.isSelected()) || 
            txtNoTelepon.getText().isEmpty() || 
            txtAlamat.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
            return;
        }
        simpanData();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        if (txtIDPelanggan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah!");
            return;
        }
        ubahData();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (txtIDPelanggan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus!");
            return;
        }
        hapusData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Apakah anda yakin ingin keluar?", 
            "Konfirmasi", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnKeluarActionPerformed

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new data_pelanggan().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbLakiLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIDPelanggan;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNoTelepon;
    // End of variables declaration//GEN-END:variables
}
