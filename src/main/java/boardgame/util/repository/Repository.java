package boardgame.util.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public abstract class Repository<T> {

    protected Class<T> elementType;

    protected List<T> elements;

    protected Repository(Class<T> elementType) {
        this.elementType = elementType;
        elements = new ArrayList<>();
    }

    public int size() {
        return elements.size();
    }

    public void add(T element) {
        elements.add(element);
    }

    public void remove(T element) {
        elements.remove(element);
    }

    public void clear() {
        elements.clear();
    }

    public List<T> find(Predicate<T> predicate) {
        return elements.stream().filter(predicate).toList();
    }

    public List<T> findAll() {
        return Collections.unmodifiableList(elements);
    }

}
