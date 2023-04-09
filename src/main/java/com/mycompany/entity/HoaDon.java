package com.mycompany.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @Column(name = "mahd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mahd;

    @Column(name = "ngay_mua")
    @Temporal(TemporalType.DATE)
    private Date ngayMua;
    @Column(name = "tong_gia_tien")
    private Double tongGiaTien;
    
    @Column(name = "dia_chi")
    private String diaChi;
    
    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoaDon", fetch = FetchType.EAGER)
    private List<HoaDonChiTiet> chiTiets;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private KhachHang nguoiMua;

    public HoaDon() {
    }

    public Integer getMahd() {
        return mahd;
    }

    public void setMahd(Integer mahd) {
        this.mahd = mahd;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public Double getTongGiaTien() {
        return tongGiaTien;
    }

    public void setTongGiaTien(Double tongGiaTien) {
        this.tongGiaTien = tongGiaTien;
    }

    public List<HoaDonChiTiet> getChiTiets() {
        return chiTiets;
    }

    public void setChiTiets(List<HoaDonChiTiet> chiTiets) {
        this.chiTiets = chiTiets;
        if (Objects.nonNull(chiTiets)) {
            Double reduce = chiTiets.stream()
                    .map(HoaDonChiTiet::getSanPham)
                    .map(SanPham::getGiaSanPham)
                    .reduce(0.0, (total, cur) -> total + cur);
            this.setTongGiaTien(reduce);
        }
    }

    public KhachHang getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(KhachHang nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
    
    
}
