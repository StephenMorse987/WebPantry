package WebPantry;

import java.util.List;

public interface DAO<E> {
    List<E> getAll ();
    void insert (E e);
    void update (E e);
    void delete (E e);
}
