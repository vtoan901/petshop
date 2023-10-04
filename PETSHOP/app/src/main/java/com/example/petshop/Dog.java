package com.example.petshop;

public class Dog {
    private int Id;
    private String Ten;
    private String Gia;
    private byte[] Hinh;

    public Dog(int id, String ten, String gia, byte[] hinh) {
        Id = id;
        Ten = ten;
        Gia = gia;
        Hinh = hinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }


    public String getGia() {
        return Gia;
    }


    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }
}

