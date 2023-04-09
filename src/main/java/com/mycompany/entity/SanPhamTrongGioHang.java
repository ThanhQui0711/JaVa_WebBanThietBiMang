package com.mycompany.entity;

import javax.persistence.*;

@Entity
@Table(name = "san_pham_trong_gio_hang")
public class SanPhamTrongGioHang {
    @Id
    @Column(name = "maspgh")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maspgh;

    @Column(name = "so_luong")
    private Integer soLuong;

    @ManyToOne
    @JoinColumn(name = "masp")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private KhachHang nguoiMua;

    public Integer getMaspgh() {
        return maspgh;
    }

    public void setMaspgh(Integer maspgh) {
        this.maspgh = maspgh;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public KhachHang getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(KhachHang nguoiMua) {
        this.nguoiMua = nguoiMua;
    }
}
