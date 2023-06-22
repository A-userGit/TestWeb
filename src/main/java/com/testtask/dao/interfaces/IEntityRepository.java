package com.testtask.dao.interfaces;

public interface IEntityRepository<T> {
    public long create(T data);
    public boolean delete(long id);
    public T update(T data);
    public T get(long id);
}
