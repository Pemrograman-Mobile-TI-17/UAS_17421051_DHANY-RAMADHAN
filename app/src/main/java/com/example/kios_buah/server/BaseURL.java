package com.example.kios_buah.server;

public class BaseURL {

    public static String baseUrl ="http://192.168.43.7:4646/";
    public static String login = baseUrl + "user/login";
    public static String regis = baseUrl + "user/registrasi";

    //buku
    public static String databuah = baseUrl + "buah/databuah/";
    public static String editDatabuah = baseUrl + "buah/ubah/";
    public static String hapusData = baseUrl + "buah/hapus/";
    public static String inputbuah = baseUrl + "buah/input";


}
