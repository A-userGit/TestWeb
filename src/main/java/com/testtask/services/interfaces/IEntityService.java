package com.testtask.services.interfaces;

public interface IEntityService<T> {
    public T create(T data);
    public T get(long id);
    public T update(T data);
    public boolean delete(long id);
}
