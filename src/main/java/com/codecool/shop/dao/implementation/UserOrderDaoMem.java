package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.UserOrder;

import java.util.ArrayList;
import java.util.List;

public class UserOrderDaoMem implements UserOrderDao {

    private List<UserOrder> data = new ArrayList<>();
    private static UserOrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private UserOrderDaoMem() {
    }

    public static UserOrderDaoMem getInstance() {
        if (instance == null) {
            instance = new UserOrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(UserOrder userOrder) {
        data.add(userOrder);
    }

    @Override
    public UserOrder find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<UserOrder> getAll() {
        return data;
    }

    @Override
    public void setPaymentData(String cName, String cNum, String expDate, String cvv) {

    }
}
