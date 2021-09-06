/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.NhanVien;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class NhanVienDao {

    NhanVien nv = new NhanVien(0, "Minh0", "To", "DangVanLanh", "15-50-2000", 2000, "CN1", 1);
    NhanVien nv1 = new NhanVien(1, "Minh1", "To", "DangVanLanh", "15-50-2000", 2000, "CN1", 1);
    NhanVien nv2 = new NhanVien(2, "Minh2", "To", "DangVanLanh", "15-50-2000", 2000, "CN1", 1);
    NhanVien nv3 = new NhanVien(3, "Minh3", "To", "DangVanLanh", "15-50-2000", 2000, "CN1", 1);
    NhanVien nv4 = new NhanVien(4, "Minh4", "To", "DangVanLanh", "15-50-2000", 2000, "CN1", 1);
    ArrayList<NhanVien> nhanVienList;
    HashMap<Integer, NhanVien> mapNhanVien;

    public NhanVienDao() {
        nhanVienList = new ArrayList<>();
        mapNhanVien = new HashMap<>();
        nhanVienList.add(nv);
        nhanVienList.add(nv1);
        nhanVienList.add(nv2);
        nhanVienList.add(nv3);
        nhanVienList.add(nv4);
        mapNhanVien.put(nv.getMaNhanVien(), nv);
        mapNhanVien.put(nv1.getMaNhanVien(), nv1);
        mapNhanVien.put(nv2.getMaNhanVien(), nv2);
        mapNhanVien.put(nv3.getMaNhanVien(), nv3);
        mapNhanVien.put(nv4.getMaNhanVien(), nv4);

    }

    //save into DB
    public int save(NhanVien nhanVien) {
        if (mapNhanVien.get(nhanVien.getMaNhanVien()) != null) {
            mapNhanVien.remove(nhanVien.getMaNhanVien());
        }
        mapNhanVien.put(nhanVien.getMaNhanVien(), nhanVien);
        return mapNhanVien.get(nhanVien.getMaNhanVien()).getMaNhanVien();
    }

    public boolean delete(NhanVien nv) {
        return mapNhanVien.remove(nv.getMaNhanVien()) != null;
    }

    public NhanVien find(NhanVien nv) {
//         NhanVien nv=new NhanVien(0,"Minh0","To","DangVanLanh", "15-50-2000",2000, "CN1", 1);
//        NhanVien nv1=new NhanVien(1,"Minh1","To","DangVanLanh", "15-50-2000",2000, "CN1", 1);
//         NhanVien nv2=new NhanVien(2,"Minh2","To","DangVanLanh", "15-50-2000",2000, "CN1", 1);
//        NhanVien nv3=new NhanVien(3,"Minh3","To","DangVanLanh", "15-50-2000",2000, "CN1", 1);
//       NhanVien nv4=new NhanVien(4,"Minh4","To","DangVanLanh", "15-50-2000",2000, "CN1", 1);
//        HashMap<Integer, NhanVien> mapNhanVien2=new HashMap<>();
//         mapNhanVien2.put(nv.getMaNhanVien(), nv);
//                       mapNhanVien2.put(nv1.getMaNhanVien(), nv1);
//                        mapNhanVien2.put(nv2.getMaNhanVien(), nv2);
//                         mapNhanVien2.put(nv3.getMaNhanVien(), nv3);
//                          mapNhanVien2.put(nv4.getMaNhanVien(), nv4);
        return mapNhanVien.get(nv.getMaNhanVien());
    }

    public List<NhanVien> findAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        for (NhanVien value : mapNhanVien.values()) {
            list.add(value);
        }
        return list;

    }

}
