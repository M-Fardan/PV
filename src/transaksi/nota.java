/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi;
import java.sql.*;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import userid.userid;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author frdn1120
 */
public class nota extends javax.swing.JFrame {
    public String id;
    public String nama;
    public String almt;
    public String kdbrg;
    public String nmbrg;
    public String harga_beli;
    public String harga_jual;
    public String jenisbrg;
    public String hb;
    public String hj;
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    
    private boolean isNewTransaction = true;
    

    /**
     * Creates new form nota
     */
    public nota() {
       initComponents();
        
        // --- BLOK INISIALISASI ---
        awal(); // Siapkan tabel
        String id = userid.getIdKasir();
        jLabel4.setText(id);
        kosong();
        aktif();
        autonumber();
        nama();
        
        // >>> INI PERBAIKANNYA: Panggil data saat form pertama kali dibuka <<<
        tampilData(); 
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initTable() {
    tabmode = new DefaultTableModel();
    tabmode.addColumn("idTransaksi");
    tabmode.addColumn("Kode");
    tabmode.addColumn("Harga Beli");
    tabmode.addColumn("Harga Jual");
    tabmode.addColumn("Qty");
    tabmode.addColumn("Subtotal");
}
 
    public void awal() {
    Object[] Baris = {"ID Transaksi", "Kode Barang", "Harga Beli", "Harga Jual", "Kuantitas", "Total"};
        tabmode = new DefaultTableModel(null, Baris);
        table_t.setModel(tabmode);
     tampilData();
}
    private void tampilData() {
    tabmode.setRowCount(0); // Selalu kosongkan tabel sebelum menampilkan data baru
        try {
            String sql = "SELECT * FROM transaksi_detail ORDER BY id_transaksi";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String idTrans = rs.getString("id_transaksi");
                String idBarang = rs.getString("kode_barang");
                String hargaBeli = rs.getString("harga_beli");
                String hargaJual = rs.getString("harga_jual");
                String qtyValue = rs.getString("qty");
                String subtotal = rs.getString("total");
                tabmode.addRow(new Object[]{idTrans, idBarang, hargaBeli, hargaJual, qtyValue, subtotal});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data dari database: " + e.getMessage());
        }
}
    
    protected void nama() {
        try {
            String sql = "SELECT nama_kasir FROM data_kasir WHERE id_kasir = '" + jLabel4.getText() + "'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            if (hasil.next()) {
                jLabel6.setText(hasil.getString("nama_kasir"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil nama kasir: " + e);
        }
    }
    
    protected void aktif() {
        qty.requestFocus();
        Date selectedDate = (Date) tgl.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String tanggalStr = sdf.format(selectedDate);
        System.out.println(tanggalStr);
        tabmode.addRow(new Object[] {
    id_b.getText(),
    
    harga_b.getText(),
    harga_j.getText(),
    qty.getText(),
    total_b.getText()
});
    }
    
    protected void autonumber() {
        try {
            String sql = "SELECT id_transaksi FROM transaksi ORDER BY id_transaksi DESC LIMIT 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String idnota = rs.getString("id_transaksi").substring(2);
                int AN = Integer.parseInt(idnota) + 1;
                String Nol = "";
                if (AN < 10) Nol = "000";
                else if (AN < 100) Nol = "00";
                else if (AN < 1000) Nol = "0";
                txtNota.setText("IN" + Nol + AN);
            } else {
                txtNota.setText("IN0001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Auto Number Gagal: " + e);
        }
    }
    
    public void itemTerpilih() {
        pop_up_data_pelanggan pp = new pop_up_data_pelanggan();
        pp.plgn = this;
        id_p.setText(id);
        nama_p.setText(nama);
        almt_p.setText(almt);
    }
    
    public void itemTerpilihBrg() {
        pop_up_data_barang pb = new pop_up_data_barang();
        pb.brg = this;
        id_b.setText(kdbrg);
        nama_b.setText(nmbrg);
        harga_b.setText(hb);
        harga_j.setText(hj);
        qty.requestFocus();

    }
    
    public void hitung() {
    double total = 0.0;

    for (int i = 0; i < table_t.getRowCount(); i++) {
        try {
            String raw = table_t.getValueAt(i, 5).toString();
            String clean = raw.replace("Rp", "")
                              .replace(".", "")
                              .replace(",", ".")
                              .trim();

            double amount = Double.parseDouble(clean);
            total += amount;
        } catch (Exception e) {
            System.out.println("Baris " + i + " error: " + e.getMessage());
        }
    }

    totalan.setText(String.format("%.2f", total));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        tgl = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        id_p = new javax.swing.JTextField();
        nama_p = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        almt_p = new javax.swing.JTextArea();
        cari_p = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        id_b = new javax.swing.JTextField();
        nama_b = new javax.swing.JTextField();
        harga_b = new javax.swing.JTextField();
        harga_j = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        total_b = new javax.swing.JTextField();
        cari_b = new javax.swing.JButton();
        tambah_b = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_t = new javax.swing.JTable();
        hps_n = new javax.swing.JButton();
        simpan_n = new javax.swing.JButton();
        batal_n = new javax.swing.JButton();
        keluar_n = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        totalan = new javax.swing.JTextField();
        cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(153, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("INI NOTA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Id Kasir :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Tanggal       :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("...");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Nama Kasir :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("...");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Id Nota  :");

        tgl.setModel(new javax.swing.SpinnerDateModel());

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "data pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Id Pelanggan :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nama            :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Alamat          :");

        almt_p.setColumns(20);
        almt_p.setRows(5);
        jScrollPane1.setViewportView(almt_p);

        cari_p.setText("cari");
        cari_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nama_p))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_p, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(id_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari_p))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(nama_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "data barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Id Barang       :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nama Barang :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Harga Beli       :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Harga jual      :");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("QTY               :");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Total              :");

        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        cari_b.setText("cari");
        cari_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_bActionPerformed(evt);
            }
        });

        tambah_b.setText("Tambah");
        tambah_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tambah_b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nama_b))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(harga_b))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(harga_j))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qty))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_b))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_b, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari_b)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(id_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari_b))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(nama_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(harga_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(harga_j, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(total_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tambah_b)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        table_t.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table_t);

        hps_n.setText("Hapus");
        hps_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hps_nActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hps_n)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hps_n)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        simpan_n.setText("Simpan");
        simpan_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_nActionPerformed(evt);
            }
        });

        batal_n.setText("Batal");
        batal_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batal_nActionPerformed(evt);
            }
        });

        keluar_n.setText("Keluar");
        keluar_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluar_nActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Totalan :");

        cetak.setText("cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(544, 544, 544)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(simpan_n)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batal_n)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keluar_n)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan_n)
                    .addComponent(batal_n)
                    .addComponent(keluar_n)
                    .addComponent(jLabel17)
                    .addComponent(totalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cetak))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
int xhj = Integer.parseInt(harga_j.getText());
        int xqty = Integer.parseInt(qty.getText());
        int xjml = xhj * xqty;
        total_b.setText(String.valueOf(xjml));        // TODO add your handling code here:
    }//GEN-LAST:event_qtyActionPerformed

    private void tambah_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_bActionPerformed
try {
        String idTransaksi = txtNota.getText();
        String kode = id_b.getText().trim();
        
        String hargabStr = harga_b.getText().trim();
        String hargajStr = harga_j.getText().trim();
        String qtyStr = qty.getText().trim();
        String subtotalStr = total_b.getText().trim();

        if (kode.isEmpty() || hargabStr.isEmpty() || 
            hargajStr.isEmpty() || qtyStr.isEmpty() || subtotalStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        int hargab = Integer.parseInt(hargabStr);
        int hargaj = Integer.parseInt(hargajStr);
        int qtyVal = Integer.parseInt(qtyStr);
        int subtotal = Integer.parseInt(subtotalStr);

        tabmode.addRow(new Object[] {
            idTransaksi, kode, hargab, hargaj, qtyVal, subtotal
        });
        
        hitung(); // <--- TAMBAHKAN BARIS INI

        System.out.println("Tambah ke tabel sukses. Total baris: " + tabmode.getRowCount());

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Input angka tidak valid: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi error: " + e.getMessage());
    }
// TODO add your handling code here:
    }//GEN-LAST:event_tambah_bActionPerformed

    private void cari_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pActionPerformed
pop_up_data_pelanggan pp = new pop_up_data_pelanggan();
        pp.plgn = this;
        pp.setVisible(true);
        pp.setResizable(false);
        pp.setLocationRelativeTo(null);        // TODO add your handling code here:
    }//GEN-LAST:event_cari_pActionPerformed

    private void cari_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_bActionPerformed
pop_up_data_barang pb = new pop_up_data_barang();
        pb.brg = this;
        pb.setVisible(true);
        pb.setResizable(false);
        pb.setLocationRelativeTo(null);        // TODO add your handling code here:
    }//GEN-LAST:event_cari_bActionPerformed

    private void hps_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hps_nActionPerformed
System.out.println("--- Tombol Hapus Ditekan ---"); // DETEKTIF 1

    int selectedRow = table_t.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String idYangAkanDihapus = table_t.getValueAt(selectedRow, 0).toString();
    System.out.println("ID yang akan dihapus: '" + idYangAkanDihapus + "'"); // DETEKTIF 2

    int konfirmasi = JOptionPane.showConfirmDialog(this, 
        "Anda yakin ingin menghapus transaksi '" + idYangAkanDihapus + "'?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

    if (konfirmasi == JOptionPane.YES_OPTION) {
        System.out.println("Pengguna mengonfirmasi penghapusan."); // DETEKTIF 3
        String sqlDetail = "DELETE FROM transaksi_detail WHERE id_transaksi = ?";
        String sqlHeader = "DELETE FROM transaksi WHERE id_transaksi = ?";

        try {
            System.out.println("Memulai blok TRY..."); // DETEKTIF 4
            conn.setAutoCommit(false);

            // Hapus dari tabel anak
            PreparedStatement stDetail = conn.prepareStatement(sqlDetail);
            stDetail.setString(1, idYangAkanDihapus);
            int rowsDeletedDetail = stDetail.executeUpdate();
            System.out.println("Baris terhapus dari transaksi_detail: " + rowsDeletedDetail); // DETEKTIF 5

            // Hapus dari tabel induk
            PreparedStatement stHeader = conn.prepareStatement(sqlHeader);
            stHeader.setString(1, idYangAkanDihapus);
            int rowsDeletedHeader = stHeader.executeUpdate();
            System.out.println("Baris terhapus dari transaksi: " + rowsDeletedHeader); // DETEKTIF 6
            
            System.out.println("Akan melakukan COMMIT..."); // DETEKTIF 7
            conn.commit();
            System.out.println("COMMIT berhasil."); // DETEKTIF 8
            
            JOptionPane.showMessageDialog(this, "Data '" + idYangAkanDihapus + "' berhasil dihapus.");
            tampilData();

        } catch (SQLException e) {
            System.out.println("--- TERJADI ERROR PADA BLOK CATCH ---"); // DETEKTIF 9
            System.out.println("Pesan Error: " + e.getMessage()); // Cetak pesan errornya
            e.printStackTrace(); // Cetak detail error yang sangat lengkap

            try {
                conn.rollback();
                System.out.println("Rollback berhasil dilakukan.");
            } catch (SQLException ex) {
                System.out.println("Rollback gagal: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Gagal menghapus data.\nLihat output konsol untuk detail error.", "Error Database", JOptionPane.ERROR_MESSAGE);
        
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                //abaikan
            }
        }
    }   // TODO add your handling code here:
    }//GEN-LAST:event_hps_nActionPerformed

    private void simpan_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_nActionPerformed
Date selectedDate = (Date) tgl.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fd = sdf.format(selectedDate);
        String sql = "INSERT INTO transaksi (id_transaksi, tanggal, id_pelanggan, id_kasir, total_bayar) VALUES (?,?,?,?,?)";
        String zsql = "INSERT INTO transaksi_detail (id_transaksi, kode_barang, harga_beli, harga_jual, qty, total) VALUES (?,?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtNota.getText());
            stat.setString(2, fd);
            stat.setString(3, id_p.getText());
            stat.setString(4, jLabel4.getText());
            stat.setString(5, totalan.getText());

            stat.executeUpdate();

            int t = table_t.getRowCount();
            for (int i = 0; i < t; i++) {
                String xkd = table_t.getValueAt(i, 1).toString(); // <-- PERUBAHAN DI SINI
                String xhb = table_t.getValueAt(i, 2).toString();
                String xhj = table_t.getValueAt(i, 3).toString();
                String xqty = table_t.getValueAt(i, 4).toString();
                String xsub = table_t.getValueAt(i, 5).toString();

                PreparedStatement stat2 = conn.prepareStatement(zsql);
                stat2.setString(1, txtNota.getText());
                stat2.setString(2, xkd);
                stat2.setString(3, xhb);
                stat2.setString(4, xhj);
                stat2.setString(5, xqty);
                stat2.setString(6, xsub);
                stat2.executeUpdate();
            }
            conn.commit();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
           
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Gagal rollback: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Data gagal disimpan: " + e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Gagal set auto commit: " + ex.getMessage());
            }
        }
        kosong();
        awal();
        autonumber();        
           // TODO add your handling code here:
    }//GEN-LAST:event_simpan_nActionPerformed

    private void batal_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batal_nActionPerformed
      kosong();
        aktif();
        autonumber();        // TODO add your handling code here:
    }//GEN-LAST:event_batal_nActionPerformed

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
try {
        String qtyText = qty.getText().trim();
        String hargaText = harga_j.getText().replace(".", "").replace(",", "").trim();

        if (!qtyText.isEmpty() && !hargaText.isEmpty()) {
            int QTY = Integer.parseInt(qtyText);
            int harga = Integer.parseInt(hargaText);
            int total = QTY * harga;
            total_b.setText(String.valueOf(total));
        } else {
            total_b.setText("");
        }
    } catch (NumberFormatException e) {
        total_b.setText("");
        System.out.println("Format salah: " + e.getMessage());
    } // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void keluar_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluar_nActionPerformed
this.dispose();        
    }//GEN-LAST:event_keluar_nActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
// 1. Dapatkan baris mana yang sedang dipilih oleh pengguna di tabel
    int selectedRow = table_t.getSelectedRow();

    // 2. Cek apakah ada baris yang dipilih
    // Jika tidak ada (getSelectedRow() akan mengembalikan -1), tampilkan pesan peringatan
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Silakan pilih salah satu data transaksi di tabel terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return; // Hentikan proses
    }

    try {
        // 3. Ambil ID Transaksi dari kolom pertama (indeks 0) di baris yang dipilih
        String idNota = table_t.getValueAt(selectedRow, 0).toString();

        // 4. Lokasi file .jasper Anda (pastikan nama file sudah benar)
        String reportPath = "src/report/report1.jasper"; 

        // 5. Siapkan parameter untuk dikirim ke laporan
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id_nota_parameter", idNota);

        // 6. Dapatkan koneksi ke database
        Connection conn = new koneksi().connect();
        
        // 7. Isi laporan dengan data dan parameter
        JasperPrint jp = JasperFillManager.fillReport(reportPath, parameters, conn);
        
        // 8. Cek apakah laporan berisi data
        if (jp.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data untuk Nota dengan ID '" + idNota + "' tidak ditemukan.", "Data Tidak Ditemukan", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 9. Tampilkan laporan di jendela viewer
        JasperViewer.viewReport(jp, false);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi error saat mencetak laporan.\nError: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_cetakActionPerformed

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
            java.util.logging.Logger.getLogger(nota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea almt_p;
    private javax.swing.JButton batal_n;
    private javax.swing.JButton cari_b;
    private javax.swing.JButton cari_p;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField harga_b;
    private javax.swing.JTextField harga_j;
    private javax.swing.JButton hps_n;
    private javax.swing.JTextField id_b;
    private javax.swing.JTextField id_p;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JButton keluar_n;
    private javax.swing.JTextField nama_b;
    private javax.swing.JTextField nama_p;
    private javax.swing.JTextField qty;
    private javax.swing.JButton simpan_n;
    private javax.swing.JTable table_t;
    private javax.swing.JButton tambah_b;
    private javax.swing.JSpinner tgl;
    private javax.swing.JTextField total_b;
    private javax.swing.JTextField totalan;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables

    private void kosong() {
        id_p.setText("");
        nama_p.setText("");
        almt_p.setText("");
    id_b.setText("");
        nama_b.setText("");
        harga_b.setText("");
        harga_j.setText("");
        qty.setText("");
       total_b.setText("");
    }

    
}
