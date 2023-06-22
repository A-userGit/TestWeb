package com.testtask.dao.interfaces;

import com.testtask.dao.models.Dealer;

public interface IDealerRepository extends IEntityRepository<Dealer> {
    public Dealer getByEmail(String email);
}
