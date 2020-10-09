package ru.javawebinar.topjava.model;

import org.slf4j.Logger;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.slf4j.LoggerFactory.getLogger;

public class ListOfMeals implements Crud {


  private static boolean initionalListAlredyFilled = false;
  private static final Logger log = getLogger(MealServlet.class);

  public static volatile AtomicLong idMeal = new AtomicLong();
  public static volatile List<Meal> mealList = new ArrayList<>();
  public static final int caloriesPerDay = 2000;

  public static List<Meal> getInitionalList() {
    initionalListAlredyFilled = true;

    addNewMeal(LocalDateTime.of(2020, 10, 1, 8, 15), "первый завтрак", 280);
    addNewMeal(LocalDateTime.of(2020, 10, 1, 10, 45), "второй завтрак", 370);
    addNewMeal(LocalDateTime.of(2020, 10, 1, 13, 0), "обед", 520);
    addNewMeal(LocalDateTime.of(2020, 10, 1, 16, 30), "полдник", 250);
    addNewMeal(LocalDateTime.of(2020, 10, 1, 19, 45), "ужин", 480);

    addNewMeal(LocalDateTime.of(2020, 10, 2, 8, 0), "первый завтрак", 280);
    addNewMeal(LocalDateTime.of(2020, 10, 2, 10, 15), "второй завтрак", 370);
    addNewMeal(LocalDateTime.of(2020, 10, 2, 13, 15), "обед", 620);
    addNewMeal(LocalDateTime.of(2020, 10, 2, 16, 45), "полдник", 250);
    addNewMeal(LocalDateTime.of(2020, 10, 2, 19, 30), "ужин", 580);

    addNewMeal(LocalDateTime.of(2020, 10, 3, 8, 30), "первый завтрак", 280);
    addNewMeal(LocalDateTime.of(2020, 10, 3, 10, 50), "второй завтрак", 370);
    addNewMeal(LocalDateTime.of(2020, 10, 3, 12, 45), "обед", 670);
    addNewMeal(LocalDateTime.of(2020, 10, 3, 16, 15), "полдник", 250);
    addNewMeal(LocalDateTime.of(2020, 10, 3, 19, 0), "ужин", 630);

    return mealList;
  }

  @Override
  public Object create(Object... values) {
    Long id = idMeal.getAndIncrement();
    Meal newMeal = new Meal( id, (LocalDateTime)values[0], (String)values[1], (int)values[2] );
    log.info("create new meal: " + newMeal);
    return newMeal;
  }

  @Override
  public List<Meal> readAll() {
    if (initionalListAlredyFilled) {
      return mealList;
    } else {
      return ListOfMeals.getInitionalList();
    }
  }

  @Override
  public Object readById(Long id) {
    return mealList.stream().filter(m -> m.getId() == id).findFirst().get();
  }

  @Override
  public Object update(Long id, Object... values) {
    Meal meal = (Meal) new ListOfMeals().readById(id);
    meal.setDateTime((LocalDateTime) values[0]);
    meal.setDescription((String) values[1]);
    meal.setCalories((int) values[2]);
    return meal;
  }

  @Override
  public void delete(Long id) {
    mealList.remove( new ListOfMeals().readById(id) );
  }

  public static void addNewMeal(LocalDateTime dateTime, String description, int calories) {
    Meal newMeal = (Meal) new ListOfMeals().create(dateTime, description, calories);
    mealList.add(newMeal);
    log.info("add new meal to list: " + newMeal);
  }

  public static void addNewMeal(Meal meal) {
    mealList.add(meal);
    log.info("add new meal to list: " + meal);
  }

}
