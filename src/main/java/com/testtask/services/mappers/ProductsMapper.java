package com.testtask.services.mappers;

import com.testtask.api.models.DealerModel;
import com.testtask.api.models.ProductModel;
import com.testtask.dao.models.Dealer;
import com.testtask.dao.models.Product;

import java.math.BigDecimal;

public class ProductsMapper {

    public static void mapToDBModel(ProductModel productModel, Product product)
    {
        product.setAmount(productModel.getAmount());
        product.setBrand(productModel.getBrand());
        product.setPrice(new BigDecimal(productModel.getPrice()));
        product.setDescription(productModel.getDescription());
        product.setName(productModel.getName());
        product.setDealerId(productModel.getDealerId());
    }
    public static ProductModel mapFromDBModel(Product product)
    {
        ProductModel productModel = new ProductModel();
        productModel.setAmount(product.getAmount());
        productModel.setBrand(product.getBrand());
        productModel.setPrice(product.getPrice().doubleValue());
        productModel.setDescription(product.getDescription());
        productModel.setName(product.getName());
        productModel.setDealerId(product.getDealerId());
        productModel.setId(product.getId());
        return productModel;
    }
}
