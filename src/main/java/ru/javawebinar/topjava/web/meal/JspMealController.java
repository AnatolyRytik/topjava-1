package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController extends AbstractMealController{

    @GetMapping("/meals")
    public String meals(Model model){
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("/meals/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer mealId){
        super.delete(mealId);
        return "redirect:/meals";
    }

    @GetMapping("/meals/create")
    public String create(Model model){
        model.addAttribute("meal",
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @PostMapping("/meals/create")
    public String create(HttpServletRequest request){
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        super.create(meal);
        return "redirect:/meals";
    }

    @GetMapping("/meals/update/{id}")
    public String update(Model model, @PathVariable(value = "id") Integer mealId){
        Meal meal = super.get(mealId);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/meals/update/{id}")
    public String update(HttpServletRequest request){
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        super.update(meal, Integer.parseInt(request.getParameter("id")));
        return "redirect:/meals";
    }

    @PostMapping("/meals")
    public String filter(HttpServletRequest request){
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "redirect:/meals";
    }
}