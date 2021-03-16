package stu.week05.spring.dao;

import org.springframework.stereotype.Component;

@Component
public class TestDao implements ITestDao{


    @Override
    public void add() {
        System.out.println("》》》》》》》》add");
    }
}
