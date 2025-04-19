package koneksi;

import java.sql.*;
import javax.swing.JOptionPane;

public class koneksi {
    private static Connection mysqlkonek;
    
    public static Connection koneksiDB() {
        if (mysqlkonek == null) {
            try {
                // Koneksi ke database yang sudah ada
                String url = "jdbc:mysql://localhost:3306/data";
                String user = "root";
                String pass = "";
                
                Class.forName("com.mysql.jdbc.Driver");
                mysqlkonek = DriverManager.getConnection(url, user, pass);
                
                System.out.println("Koneksi ke database berhasil!");
                
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, 
                    "Driver MySQL tidak ditemukan!\nPastikan MySQL JDBC Driver sudah ditambahkan ke project",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    
            } catch (SQLException e) {
                // Tampilkan pesan error yang lebih spesifik
                if (e.getErrorCode() == 1049) {
                    JOptionPane.showMessageDialog(null,
                        "Database 'data' tidak ditemukan.\nBuat database dengan nama 'data' di phpMyAdmin.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (e.getErrorCode() == 1045) {
                    JOptionPane.showMessageDialog(null,
                        "Username atau password MySQL salah.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (e.getErrorCode() == 1146) {
                    JOptionPane.showMessageDialog(null,
                        "Tabel 'data_pelanggan' tidak ditemukan.\nBuat tabel di database 'data'.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Koneksi Gagal!\nPastikan XAMPP sudah running dan MySQL sudah start\nError: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return mysqlkonek;
    }
    
    public static void closeConnection() {
        try {
            if (mysqlkonek != null && !mysqlkonek.isClosed()) {
                mysqlkonek.close();
                mysqlkonek = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Gagal menutup koneksi!\nError: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // Test koneksi
        try {
            Connection testKoneksi = koneksi.koneksiDB();
            if (testKoneksi != null) {
                System.out.println("Koneksi Berhasil!");
                JOptionPane.showMessageDialog(null, "Koneksi ke Database Berhasil!");
            }
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }
}