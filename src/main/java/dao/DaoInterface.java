package dao;

import java.util.Date;
import java.util.List;

public interface DaoInterface<T> {

    List<T> orderByDate(Long id, Date date);

    List<T> findAll();

    Long findIdByName(String name);

    boolean ifExistsInProducts(String name);

    boolean ifExistsInSold(Long id);

    T findById(Long id);

    void updateAmount(Long purchase_id, int amount);

    void save(T t);

    void update(T t);

    void delete(T t);

    void deleteById(Long id);

}
