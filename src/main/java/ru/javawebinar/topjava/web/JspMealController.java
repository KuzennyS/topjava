package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends MealRestController {

    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

//    @Autowired
//    private MealRestController service;

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping()
    public String meals(Model model) {
        log.info("input to GET");
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("update/{id}")
    public String delete(@PathVariable int id, Model model) {
        Meal meal = get(id);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("create")
    public String create(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("delete/{id}")
    public String update(@PathVariable int id) {
        delete(id);
        return "redirect:/meals";
    }

    @PostMapping("update/{id}")
    public String update(@Valid Meal meal,
                         BindingResult bindingResult,
                         Model model,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {
        log.info("input to updatePost - {}", request.getHeader("referer"));
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        if (bindingResult.hasErrors()) {
            log.error("Binding error - {}", bindingResult.getAllErrors().toString());
        }

        if (meal.isNew()) {
            create(meal);
        } else {
            update(meal, getId(request));
        }

        return "redirect:/meals";
    }

    @PostMapping("create/{id}")
    public String create(@Valid Meal meal,
                         BindingResult bindingResult,
                         Model model,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {
        log.info("input to create - {}", request.getHeader("referer"));
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        if (bindingResult.hasErrors()) {
            log.error("Binding error - {}", bindingResult.getAllErrors().toString());
        }
        create(meal);
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
