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

    public List<Kho> findAll() {
        return khoDAO.findAll();
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

    public void save(Kho kho) {
        khoDAO.save(kho);
    }

    public void update(Kho kho) {
        khoDAO.update(kho);
    }
}
