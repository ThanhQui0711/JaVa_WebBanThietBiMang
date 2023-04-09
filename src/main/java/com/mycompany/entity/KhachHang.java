package com.mycompany.entity;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class KhachHang implements java.io.Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer makh;

    @NotBlank(message = "Tên khách hàng không được trống!")
    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", unique = true)
    @NotBlank(message = "SĐT không được trống!")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "SĐT không hợp lệ!")
    private String soDienThoaikh;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email không được trống!")
    @Email(message = "Vui lòng nhập đúng địa chỉ email")
    private String email;

    @NotBlank(message = "Tên đăng nhập không được trống")
    @Column(name = "username", unique = true)
    @NaturalId
    private String username;

    @NotBlank(message = "Mật khẩu không được trống!")
    @Length(min = 8, message = "Mật khẩu tối thiểu 8 ký tự!")
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngayTao")
    private Date ngayTao;

    @OneToMany(mappedBy = "nguoiMua", fetch = FetchType.EAGER)
    private Set<HoaDon> hoaDons;

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoaikh() {
        return soDienThoaikh;
    }

    public void setSoDienThoaikh(String soDienThoaikh) {
        this.soDienThoaikh = soDienThoaikh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMakh(Integer makh) {
        this.makh = makh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Set<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(Set<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
