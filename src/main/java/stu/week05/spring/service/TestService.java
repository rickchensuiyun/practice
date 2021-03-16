package stu.week05.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stu.week05.spring.dao.ITestDao;

@Component
public class TestService {

    @Autowired
    private ITestDao dao;

    public void addService() {
        dao.add();
    }

    public ITestDao getDao() {
        return dao;
    }

    public void setDao(ITestDao dao) {
        this.dao = dao;
    }
}
