package com.testtask.services.mappers;

import com.testtask.api.models.DealerModel;
import com.testtask.dao.models.Dealer;
import com.testtask.dao.models.Product;

public class DealersMapper {
    public static void mapToDBModel(DealerModel dealerModel, Dealer dealer)
    {
        dealer.setEmail(dealerModel.getEmail());
        dealer.setName(dealerModel.getName());
        dealer.setAddress(dealerModel.getAddress());
        dealer.setSurname(dealerModel.getSurname());
        dealer.setPhone(dealerModel.getPhone());
    }
    public static DealerModel mapFromDBModel(Dealer dealer)
    {
        DealerModel dealerModel = new DealerModel();
        dealerModel.setEmail(dealer.getEmail());
        dealerModel.setName(dealer.getName());
        dealerModel.setAddress(dealer.getAddress());
        dealerModel.setSurname(dealer.getSurname());
        dealerModel.setPhone(dealer.getPhone());
        dealerModel.setId(dealer.getId());
        for (Product product:dealer.getProducts()) {
            dealerModel.getProducts().add(ProductsMapper.mapFromDBModel(product));
        }
        return dealerModel;
    }
}
