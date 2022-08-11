package uz.spring.service;

public interface BaseService<O> {
    boolean add(O o);
    void list();
}
