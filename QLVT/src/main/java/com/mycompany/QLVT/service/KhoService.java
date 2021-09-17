package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.dao.KhoDAO;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class KhoService {

    KhoDAO khoDAO = new KhoDAO();

    public List<Kho> findAll(String id) {
        return khoDAO.findAll(id);
    }

    public Kho findOne(String id) {
        return khoDAO.findOne(id);
    }

    public int checkExist(String value, String type) {
        return khoDAO.checkExist(value, type);
    }

    public void delete(String id) {
        khoDAO.delete(id);
    }

    public void insert(Kho kho) {
        khoDAO.insert(kho);
    }

    public void update(Kho kho) {
        khoDAO.update(kho);
    }
}
