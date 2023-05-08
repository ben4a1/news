package ru.clevertec.predicate;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CriteriaPredicate {

    private final List<Predicate> predicates = new ArrayList<>();

    public static CriteriaPredicate build() {
        return new CriteriaPredicate();
    }

    public <T> CriteriaPredicate addPredicate(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }
}
