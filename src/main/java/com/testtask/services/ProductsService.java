package com.testtask.services;

import com.testtask.api.models.ProductModel;
import com.testtask.dao.interfaces.IDealerRepository;
import com.testtask.dao.interfaces.IProductsRepository;
import com.testtask.dao.models.Dealer;
import com.testtask.dao.models.Product;
import com.testtask.services.interfaces.IProductService;
import com.testtask.services.mappers.ProductsMapper;

import java.math.BigDecimal;

public class ProductsService implements IProductService {
    private IProductsRepository productsRepository;
    private IDealerRepository dealerRepository;

    public ProductsService(IProductsRepository productsRepository, IDealerRepository dealerRepository)
    {
        this.productsRepository = productsRepository;
        this.dealerRepository = dealerRepository;
    }

    public ProductModel create(ProductModel productModel)
    {
        validateModel(productModel);
        Dealer dealer = dealerRepository.get(productModel.getDealerId());
        if(dealer == null)
        {
            throw new IllegalArgumentException("There is no such dealer for product");
        }
        Product product = new Product();
        ProductsMapper.mapToDBModel(productModel, product);
        productsRepository.create(product);
        productModel.setId(product.getId());
        return productModel;
    }

    public boolean delete(long id)
    {
        return productsRepository.delete(id);
    }

    public ProductModel get(long id)
    {
        Product product = productsRepository.get(id);
        if(product != null)
            return ProductsMapper.mapFromDBModel(product);
        return null;
    }

    public ProductModel update(ProductModel productModel)
    {
        validateModel(productModel);
        Product oldProduct = productsRepository.get(productModel.getId());
        if(oldProduct == null)
            throw new IllegalArgumentException("There is no such product for update");
        ProductsMapper.mapToDBModel(productModel, oldProduct);
        productsRepository.update(oldProduct);
        return productModel;
    }

    private void validateModel(ProductModel productModel)
    {
        if(productModel.getPrice() < 0)
        {
            throw new IllegalArgumentException("Price of product cannot be less than zero");
        }
        if(productModel.getName()==null||productModel.getName().isBlank())
        {
            throw new IllegalArgumentException("Name of product cannot be blank");
        }
        if(productModel.getAmount()<0)
        {
            throw new IllegalArgumentException("Amount of product cannot be less than zero");
        }
    }

}
