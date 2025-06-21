package userid;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author frdn1120
 */
public class userid {
     private static String id_kasir;
    private static String nama_kasir;

    public static void setIdKasir(String idKasir) {
        userid.id_kasir = idKasir;
    }

    public static String getIdKasir() {
        return id_kasir;
    }

    public static void setNamaKasir(String namaKasir) {
        userid.nama_kasir = namaKasir;
    }

    public static String getNamaKasir() {
        return nama_kasir;
    }

    
}
