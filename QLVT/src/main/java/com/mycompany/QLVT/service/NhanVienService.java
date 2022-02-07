/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.TaiKhoan;
import com.mycompany.QLVT.Exception.LoginNameExistException;
import com.mycompany.QLVT.Exception.UserNameExistException;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.Utils.EnumChiNhanh;
import com.mycompany.QLVT.dao.NhanVienDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class NhanVienService {

    NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public List<NhanVien> findAll() {
        return nhanVienDAO.findAll();
    }

    public List<NhanVien> findAllOthersite() {
        return nhanVienDAO.findAllOtherSite();
    }

    public NhanVien findOne(int id) {
        return nhanVienDAO.findOne(id);
    }

    public int delete(int id) {
        return nhanVienDAO.delete(id);
    }

    public int isExist(int id) {
        return nhanVienDAO.isExist(id);
    }

    public int save(NhanVien nv) {
        
        return nhanVienDAO.insert(nv);
    }

    public void update(NhanVien nv) {
        nhanVienDAO.update(nv);
    }
 
    public boolean createAccount(TaiKhoan tk)
            {
                int rs=nhanVienDAO.createAccount(tk);
                if(rs==1)
                    {
                        throw new LoginNameExistException("Login name đã tồn tại");
                    }
                if(rs==2)
                    {
                        throw new UserNameExistException("User name đã tồn tại");
                    }
                if(rs==-1)
                {
                        throw new RuntimeException("Tạo tài khoản không thành");
                }
                return rs==0;
            }
    public int chuyenChiNhanh(int id, String cN) {
        if (cN.equals(EnumChiNhanh.CN1.getTp())) {
            return nhanVienDAO.chuyenChiNhanh(id, EnumChiNhanh.CN1.name());
        } else if (cN.equals(EnumChiNhanh.CN2.getTp())) {
            return nhanVienDAO.chuyenChiNhanh(id, EnumChiNhanh.CN2.name());
        }
        return 0;
    }
}
