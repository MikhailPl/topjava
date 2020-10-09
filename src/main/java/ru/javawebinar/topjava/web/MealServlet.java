package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Crud;
import ru.javawebinar.topjava.model.ListOfMeals;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
  private static final Logger log = getLogger(MealServlet.class);

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    // so so, but work:
    String dt = request.getParameter("dateTime").replace("T", " ");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);

    String idString = request.getParameter("id");
    String description = request.getParameter("description");
    int calories = Integer.parseInt(request.getParameter("calories"));
    if (idString.isEmpty()) {
      Meal meal = (Meal) new ListOfMeals().create(dateTime, description, calories);
      ListOfMeals.addNewMeal(meal);
    } else {
      Long id = Long.valueOf(idString);
      new ListOfMeals().update(id, dateTime, description, calories);
    }
    response.sendRedirect(request.getContextPath() + "/meals?action=readAll");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    log.debug("redirect to meals in " + new java.util.Date());


    String action = request.getParameter("action");
    //action = action.isEmpty() ? "readAll" : "";

    log.debug(" action: " + action + ", request: " + request);

    if (action.equalsIgnoreCase("edit")) {
      Long id = Long.valueOf(request.getParameter("id"));
      Meal meal = (Meal) new ListOfMeals().readById(id);
      request.setAttribute("meal", meal);
      RequestDispatcher rd = request.getRequestDispatcher("edit-meal.jsp");
      rd.forward(request, response);
    }

    if (action.equalsIgnoreCase("create")) {
      response.sendRedirect("edit-meal.jsp");
    }

    if (action.equalsIgnoreCase("delete")) {
      Long id = Long.valueOf(request.getParameter("id"));
      new ListOfMeals().delete(id);
      response.sendRedirect(request.getContextPath() + "/meals?action=readAll");
    }

    if (action.equalsIgnoreCase("readAll") || action.isEmpty()) {

      List<Meal> currentList = new ListOfMeals().readAll();

      List<MealTo> mealToList = MealsUtil.filteredByStreams(currentList, LocalTime.MIN,
        LocalTime.MAX, ListOfMeals.caloriesPerDay);

      log.debug("send list of " + mealToList.size() + " elements to meals.jsp");

      request.setAttribute("mealToList", mealToList);
      RequestDispatcher rd = request.getRequestDispatcher("meals.jsp");
      rd.forward(request, response);

    }

  }
}
