import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class kasir extends javax.swing.JFrame {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private JTable tableTransaksi;
    private DefaultTableModel model;
    
    public kasir() {
        setTitle("Aplikasi Kasir");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Nota
        JPanel panelNota = new JPanel(new GridBagLayout());
        panelNota.setBorder(BorderFactory.createTitledBorder("Nota"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0
        gbc.gridx = 0; gbc.gridy = 0;
        panelNota.add(new JLabel("Id Kasir :"), gbc);
        gbc.gridx = 1;
        txtIdKasir = new JTextField(15);
        panelNota.add(txtIdKasir, gbc);

        gbc.gridx = 2;
        panelNota.add(new JLabel("Nama Kasir :"), gbc);
        gbc.gridx = 3;
        txtNamaKasir = new JTextField(15);
        panelNota.add(txtNamaKasir, gbc);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 1;
        panelNota.add(new JLabel("Id Nota :"), gbc);
        gbc.gridx = 1;
        txtIdNota = new JTextField(15);
        panelNota.add(txtIdNota, gbc);

        gbc.gridx = 2;
        panelNota.add(new JLabel("Tgl Nota :"), gbc);
        gbc.gridx = 3;
        txtTglNota = new JTextField(15);
        panelNota.add(txtTglNota, gbc);

        // Panel Data Pelanggan
        JPanel panelPelanggan = new JPanel(new GridBagLayout());
        panelPelanggan.setBorder(BorderFactory.createTitledBorder("Data Pelanggan"));
        panelPelanggan.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbcP = new GridBagConstraints();
        gbcP.insets = new Insets(5,5,5,5);
        gbcP.fill = GridBagConstraints.HORIZONTAL;

        // Id Pelanggan + button cari
        gbcP.gridx = 0; gbcP.gridy = 0;
        panelPelanggan.add(new JLabel("Id pelanggan"), gbcP);
        gbcP.gridx = 1;
        txtIdPelanggan = new JTextField(15);
        panelPelanggan.add(txtIdPelanggan, gbcP);
        gbcP.gridx = 2;
        JButton btnCariPelanggan = new JButton("cari");
        panelPelanggan.add(btnCariPelanggan, gbcP);

        // Nama
        gbcP.gridx = 0; gbcP.gridy = 1;
        panelPelanggan.add(new JLabel("Nama"), gbcP);
        gbcP.gridx = 1; gbcP.gridwidth = 2;
        txtNamaPelanggan = new JTextField(20);
        panelPelanggan.add(txtNamaPelanggan, gbcP);
        gbcP.gridwidth = 1;

        // Alamat (textarea)
        gbcP.gridx = 0; gbcP.gridy = 2;
        panelPelanggan.add(new JLabel("Alamat"), gbcP);
        gbcP.gridx = 1; gbcP.gridwidth = 2;
        txtAlamatPelanggan = new JTextArea(5, 20);
        txtAlamatPelanggan.setLineWrap(true);
        txtAlamatPelanggan.setWrapStyleWord(true);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamatPelanggan);
        panelPelanggan.add(scrollAlamat, gbcP);
        gbcP.gridwidth = 1;

        // Panel Data Barang
        JPanel panelBarang = new JPanel(new GridBagLayout());
        panelBarang.setBorder(BorderFactory.createTitledBorder("Data Barang"));
        panelBarang.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbcB = new GridBagConstraints();
        gbcB.insets = new Insets(5,5,5,5);
        gbcB.fill = GridBagConstraints.HORIZONTAL;

        // KD Barang + button cari
        gbcB.gridx = 0; gbcB.gridy = 0;
        panelBarang.add(new JLabel("KD Barang"), gbcB);
        gbcB.gridx = 1;
        txtKdBarang = new JTextField(15);
        panelBarang.add(txtKdBarang, gbcB);
        gbcB.gridx = 2;
        JButton btnCariBarang = new JButton("cari");
        panelBarang.add(btnCariBarang, gbcB);

        // Nama
        gbcB.gridx = 0; gbcB.gridy = 1;
        panelBarang.add(new JLabel("Nama"), gbcB);
        gbcB.gridx = 1; gbcB.gridwidth = 2;
        txtNamaBarang = new JTextField(20);
        panelBarang.add(txtNamaBarang, gbcB);
        gbcB.gridwidth = 1;

        // Harga Beli
        gbcB.gridx = 0; gbcB.gridy = 2;
        panelBarang.add(new JLabel("Harga Beli"), gbcB);
        gbcB.gridx = 1; gbcB.gridwidth = 2;
        txtHargaBeli = new JTextField(20);
        panelBarang.add(txtHargaBeli, gbcB);
        gbcB.gridwidth = 1;

        // Harga Jual
        gbcB.gridx = 0; gbcB.gridy = 3;
        panelBarang.add(new JLabel("Harga Jual"), gbcB);
        gbcB.gridx = 1; gbcB.gridwidth = 2;
        txtHargaJual = new JTextField(20);
        panelBarang.add(txtHargaJual, gbcB);
        gbcB.gridwidth = 1;

        // QTY
        gbcB.gridx = 0; gbcB.gridy = 4;
        panelBarang.add(new JLabel("QTY"), gbcB);
        gbcB.gridx = 1; gbcB.gridwidth = 2;
        txtQTY = new JTextField(20);
        panelBarang.add(txtQTY, gbcB);
        gbcB.gridwidth = 1;

        // Total
        gbcB.gridx = 0; gbcB.gridy = 5;
        panelBarang.add(new JLabel("Total"), gbcB);
        gbcB.gridx = 1; gbcB.gridwidth = 2;
        txtTotal = new JTextField(20);
        txtTotal.setEditable(false);
        panelBarang.add(txtTotal, gbcB);
        gbcB.gridwidth = 1;

        // Button tambah
        gbcB.gridx = 0; gbcB.gridy = 6; gbcB.gridwidth = 3;
        JButton btnTambah = new JButton("tambah");
        panelBarang.add(btnTambah, gbcB);
        gbcB.gridwidth = 1;

        // Panel Transaksi
        JPanel panelTransaksi = new JPanel(new BorderLayout());
        panelTransaksi.setBorder(BorderFactory.createTitledBorder("Transaksi"));

        // Table for transaksi
        model = new DefaultTableModel(new Object[]{"Kd Barang", "Nama", "Harga Beli", "Harga Jual", "QTY", "Total"}, 0);
        tableTransaksi = new JTable(model);
        JScrollPane scrollTable = new JScrollPane(tableTransaksi);
        panelTransaksi.add(scrollTable, BorderLayout.CENTER);

        // Button hapus
        JButton btnHapus = new JButton("hapus");
        JPanel panelHapus = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelHapus.add(btnHapus);
        panelTransaksi.add(panelHapus, BorderLayout.SOUTH);

        // Panel bawah untuk tombol simpan, batal, keluar dan total harga
        JPanel panelBawah = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBawah = new GridBagConstraints();
        gbcBawah.insets = new Insets(5,5,5,5);
        gbcBawah.fill = GridBagConstraints.HORIZONTAL;

        JButton btnSimpan = new JButton("simpan");
        JButton btnBatal = new JButton("batal");
        JButton btnKeluar = new JButton("keluar");

        gbcBawah.gridx = 0; gbcBawah.gridy = 0;
        panelBawah.add(btnSimpan, gbcBawah);
        gbcBawah.gridx = 1;
        panelBawah.add(btnBatal, gbcBawah);
        gbcBawah.gridx = 2;
        panelBawah.add(btnKeluar, gbcBawah);

        gbcBawah.gridx = 3;
        panelBawah.add(new JLabel("total harga"), gbcBawah);
        gbcBawah.gridx = 4;
        txtTotalHarga = new JTextField(15);
        txtTotalHarga.setEditable(false);
        panelBawah.add(txtTotalHarga, gbcBawah);

        // Add panels to main frame
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelNota, BorderLayout.NORTH);

        JPanel panelMiddle = new JPanel(new GridLayout(1,2));
        panelMiddle.add(panelPelanggan);
        panelMiddle.add(panelBarang);

        add(panelTop, BorderLayout.NORTH);
        add(panelMiddle, BorderLayout.CENTER);
        add(panelTransaksi, BorderLayout.SOUTH);
        add(panelBawah, BorderLayout.PAGE_END);
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtIdPelanggan = new javax.swing.JTextField();
        btnCariPelanggan = new javax.swing.JButton();
        txtNamaPelanggan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamatPelanggan = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtKdBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtHargaBeli = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();
        txtQTY = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnCariBarang = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtIdKasir = new javax.swing.JTextField();
        txtIdNota = new javax.swing.JTextField();
        txtNamaKasir = new javax.swing.JTextField();
        txtTglNota = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Nota");
        jLabel1.setToolTipText("");

        jLabel2.setText("Id Kasir ");

        jLabel3.setText("Id Nota ");

        jLabel4.setText("Nama Kasir");

        jLabel5.setText("Tgl Nota ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Id Pelanggan");

        jLabel9.setText("Nama");

        jLabel10.setText("Alamat");

        btnCariPelanggan.setText("cari");
        btnCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPelangganActionPerformed(evt);
            }
        });

        txtAlamatPelanggan.setColumns(20);
        txtAlamatPelanggan.setRows(5);
        jScrollPane2.setViewportView(txtAlamatPelanggan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamaPelanggan)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariPelanggan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Kd Barang");

        jLabel12.setText("Nama");

        jLabel13.setText("Harga Beli");

        jLabel14.setText("Harga Jual ");

        jLabel15.setText("QTY");

        jLabel16.setText("Total");

        btnCariBarang.setText("cari");
        btnCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBarangActionPerformed(evt);
            }
        });

        btnTambah.setText("tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariBarang)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNamaBarang)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHargaBeli)
                            .addComponent(txtHargaJual)
                            .addComponent(txtQTY)
                            .addComponent(txtTotal))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBarang))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtQTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnTambah)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Data Pelanggan");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Data Barang");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kd Barang", "Nama", "Harga Beli", "Harga Jual ", "QTY", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnHapus.setText("hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnHapus)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Transaksi");

        jLabel18.setText("Total Harga");

        btnSimpan.setText("simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setText("keluar");
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
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(72, 72, 72)
                        .addComponent(btnBatal)
                        .addGap(87, 87, 87)
                        .addComponent(btnKeluar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIdNota))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(txtIdKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtTglNota))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(568, 568, 568))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtIdKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtIdNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTglNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(11, 11, 11)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPelangganActionPerformed
String idPelanggan = txtIdPelanggan.getText();
        if (idPelanggan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan Id Pelanggan terlebih dahulu");
            return;
        }
        try {
            String sql = "SELECT nama, alamat FROM pelanggan WHERE id_pelanggan = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, idPelanggan);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNamaPelanggan.setText(rs.getString("nama"));
                txtAlamatPelanggan.setText(rs.getString("alamat"));
            } else {
                JOptionPane.showMessageDialog(this, "Data pelanggan tidak ditemukan");
                txtNamaPelanggan.setText("");
                txtAlamatPelanggan.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cari pelanggan: " + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariPelangganActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        String kdBarang = txtKdBarang.getText();
        if (kdBarang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan KD Barang terlebih dahulu");
            return;
        }
        try {
            String sql = "SELECT nama, harga_beli, harga_jual FROM barang WHERE kd_barang = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kdBarang);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNamaBarang.setText(rs.getString("nama"));
                txtHargaBeli.setText(rs.getString("harga_beli"));
                txtHargaJual.setText(rs.getString("harga_jual"));
                txtQTY.setText("");
                txtTotal.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Data barang tidak ditemukan");
                txtNamaBarang.setText("");
                txtHargaBeli.setText("");
                txtHargaJual.setText("");
                txtQTY.setText("");
                txtTotal.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cari barang: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
String kdBarang = txtKdBarang.getText();
        String nama = txtNamaBarang.getText();
        String hargaBeli = txtHargaBeli.getText();
        String hargaJual = txtHargaJual.getText();
        String qty = txtQTY.getText();
        String total = txtTotal.getText();

        if (kdBarang.isEmpty() || nama.isEmpty() || hargaBeli.isEmpty() || hargaJual.isEmpty() || qty.isEmpty() || total.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi data barang sebelum menambah");
            return;
        }

        model.addRow(new Object[]{kdBarang, nama, hargaBeli, hargaJual, qty, total});
        clearBarangFields();
        
        }

    private void clearBarangFields() {
        txtKdBarang.setText("");
        txtNamaBarang.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtQTY.setText("");
        txtTotal.setText("");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int selectedRow = tableTransaksi.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
            
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String idKasir = txtIdKasir.getText();
        String namaKasir = txtNamaKasir.getText();
        String idNota = txtIdNota.getText();
        String tglNota = txtTglNota.getText();
        String idPelanggan = txtIdPelanggan.getText();

        if (idKasir.isEmpty() || namaKasir.isEmpty() || idNota.isEmpty() || tglNota.isEmpty() || idPelanggan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi data nota dan pelanggan");
            return;
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Data transaksi kosong");
            return;
        }

        try {
            conn.setAutoCommit(false);

            // Insert into nota table
            String sqlNota = "INSERT INTO nota (id_nota, id_kasir, nama_kasir, tgl_nota, id_pelanggan, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sqlNota);
            pst.setString(1, idNota);
            pst.setString(2, idKasir);
            pst.setString(3, namaKasir);
            pst.setString(4, tglNota);
            pst.setString(5, idPelanggan);
            pst.setDouble(6, Double.parseDouble(txtTotalHarga.getText()));
            pst.executeUpdate();

            // Insert into detail_nota table
            String sqlDetail = "INSERT INTO detail_nota (id_nota, kd_barang, nama, harga_beli, harga_jual, qty, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sqlDetail);

            for (int i = 0; i < model.getRowCount(); i++) {
                pst.setString(1, idNota);
                pst.setString(2, model.getValueAt(i, 0).toString());
                pst.setString(3, model.getValueAt(i, 1).toString());
                pst.setDouble(4, Double.parseDouble(model.getValueAt(i, 2).toString()));
                pst.setDouble(5, Double.parseDouble(model.getValueAt(i, 3).toString()));
                pst.setInt(6, Integer.parseInt(model.getValueAt(i, 4).toString()));
                pst.setDouble(7, Double.parseDouble(model.getValueAt(i, 5).toString()));
                pst.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            clearAllFields();
            model.setRowCount(0);
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Rollback gagal: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Error simpan data: " + e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error set auto commit: " + ex.getMessage());
            }
        }
    }

    private void clearAllFields() {
        txtIdKasir.setText("");
        txtNamaKasir.setText("");
        txtIdNota.setText("");
        txtTglNota.setText("");
        txtIdPelanggan.setText("");
        txtNamaPelanggan.setText("");
        txtAlamatPelanggan.setText("");
        clearBarangFields();
        txtTotalHarga.setText("");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearAllFields();
        model.setRowCount(0);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new kasir().setVisible(true);
        });
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtAlamatPelanggan;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtIdKasir;
    private javax.swing.JTextField txtIdNota;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JTextField txtKdBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaKasir;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtQTY;
    private javax.swing.JTextField txtTglNota;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables
}
