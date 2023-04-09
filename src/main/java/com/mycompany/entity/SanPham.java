/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @Column(name = "masp")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masp;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "img")
    private byte[] imgSp;

    @Column(name = "gia_san_pham")
    private Double giaSanPham;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "so_luong_trong_kho")
    private Integer soLuongTrongKho;

    @Column(name = "bao_hanh")
    private Integer baoHanh;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "nha_cung_cap")
    private String nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "ma_danh_muc")
    private DanhMuc danhMuc;


    public SanPham() {
    }

    public SanPham(Integer masp, String tenSanPham, byte[] imgSp, Double giaSanPham, Integer trangThai, Integer soLuongTrongKho, Integer baoHanh, String moTa, DanhMuc danhMuc) {
        this.masp = masp;
        this.tenSanPham = tenSanPham;
        this.imgSp = imgSp;
        this.giaSanPham = giaSanPham;
        this.trangThai = trangThai;
        this.soLuongTrongKho = soLuongTrongKho;
        this.baoHanh = baoHanh;
        this.moTa = moTa;
        this.danhMuc = danhMuc;
    }

    public SanPham(String tensanpham, String loaiSanPham, Double giaSanPham, Integer trangThai, Integer soLuongTrongKho) {
    }

    public Integer getMasp() {
        return masp;
    }

    public void setMasp(Integer masp) {
        this.masp = masp;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public byte[] getImgSp() {
        return imgSp;
    }

    public void setImgSp(byte[] imgSp) {
        this.imgSp = imgSp;
    }

    public Double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(Double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoLuongTrongKho() {
        return soLuongTrongKho;
    }

    public void setSoLuongTrongKho(Integer soLuongTrongKho) {
        this.soLuongTrongKho = soLuongTrongKho;
    }

    public Integer getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(Integer baoHanh) {
        this.baoHanh = baoHanh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getImageBase64() {
        if (Objects.isNull(this.imgSp)) {
            return StringUtils.EMPTY;
        }
        return Base64.getEncoder().encodeToString(this.imgSp);
    }
}
