package com.testtask.services;

import com.testtask.api.models.DealerModel;
import com.testtask.dao.interfaces.IDealerRepository;
import com.testtask.dao.models.Dealer;
import com.testtask.services.interfaces.IDealerService;
import com.testtask.services.mappers.DealersMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DealerService implements IDealerService {

    private static final String PHONE_PATTERN = "^\\+?[0-9]{3}-?[0-9]{6,12}$";
    private static final String EMAIL_PATTERN ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private IDealerRepository dealerRepository;

    public DealerService(IDealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public DealerModel get(long id)
    {
       Dealer dealer = dealerRepository.get(id);
       if (dealer != null)
        return DealersMapper.mapFromDBModel(dealer);
       return null;
    }



    public DealerModel create(DealerModel dealerModel)
    {
        validateModel(dealerModel);
        if(dealerRepository.getByEmail(dealerModel.getEmail()) != null)
            throw new IllegalArgumentException("Email is already taken");
        Dealer dealer = new Dealer();
        DealersMapper.mapToDBModel(dealerModel, dealer);
        long id = dealerRepository.create(dealer);
        dealerModel.setId(id);
        return dealerModel;
    }
    public DealerModel update(DealerModel dealerModel)
    {
        validateModel(dealerModel);
        Dealer oldDealer = dealerRepository.get(dealerModel.getId());
        if(oldDealer == null)
            throw new IllegalArgumentException("Such dealer does not exist");
        if(!oldDealer.getEmail().equals(dealerModel.getEmail()))
        {
            if(dealerRepository.getByEmail(dealerModel.getEmail()) != null)
                throw new IllegalArgumentException("Email is already taken");
        }
        DealersMapper.mapToDBModel(dealerModel,oldDealer);
        dealerRepository.update(oldDealer);
        return dealerModel;
    }

    public boolean delete(long id)
    {
        return dealerRepository.delete(id);
    }



    private void validateModel(DealerModel dealerModel)
    {
        if(dealerModel.getEmail() == null)
        {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if(!verifyEmail(dealerModel.getEmail()))
        {
            throw new IllegalArgumentException("Email is incorrect");
        }
        if(dealerModel.getName() == null || dealerModel.getName().isBlank())
        {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if(dealerModel.getSurname() == null || dealerModel.getSurname().isBlank())
        {
            throw new IllegalArgumentException("Surname cannot be blank");
        }

        if (!verifyPhoneNumber(dealerModel.getPhone()))
        {
            throw new IllegalArgumentException("Phone number is incorrect");
        }
    }

    private boolean verifyEmail(String email)
    {
        Pattern pattern =  Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean verifyPhoneNumber(String phoneNumber)
    {
        if(phoneNumber == null || phoneNumber.isBlank())
            return true;
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
