package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javawebinar.topjava.model.Meal;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query(name = Meal.DELETE)
    int delete(@Param("id") int id, @Param("userId") int userId);

//    @Modifying
//    @Query(name = Meal.ALL_SORTED)
//    public List<Meal> getAll(@Param("userId") int userId);

    List<Meal> getByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> getByUserIdAndDateTimeBetweenOrderByDateTimeDesc(
            int userId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

////    Meal save(Meal meal, int userId);
//@Query("select m from Meal m where m.user.id = :userId and m = :meal")
//Meal save(Meal meal, int userId);
}
