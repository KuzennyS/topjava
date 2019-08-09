package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.user.AbstractUserController;
import ru.javawebinar.topjava.repository.JpaUtil;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.parse;

@RestController
@RequestMapping("/ajax/meals")
public class MealAjaxController extends AbstractMealController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("dateTime") String dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") String calories) {

        Meal meal = new Meal(id, parse(dateTime), description, Integer.parseInt(calories));
        if (meal.isNew()) {
            super.create(meal);
        }
    }
}
