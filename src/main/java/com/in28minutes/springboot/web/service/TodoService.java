package com.in28minutes.springboot.web.service;

import com.beeinstant.metrics.MetricsLogger;
import com.beeinstant.metrics.MetricsManager;
import com.beeinstant.metrics.TimerMetric;
import com.in28minutes.springboot.web.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;
    private final MetricsLogger metricsLogger = MetricsManager.getMetricsLogger("class=TodoService");

    static {
        todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "in28Minutes", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {
        try (TimerMetric timer = metricsLogger.extendDimensions("method=retrieveAllToDos").startTimer("Timing")) {
            List<Todo> filteredTodos = new ArrayList<Todo>();
            for (Todo todo : todos) {
                if (todo.getUser().equalsIgnoreCase(user)) {
                    filteredTodos.add(todo);
                }
            }
            return filteredTodos;
        }
    }

    public Todo retrieveTodo(int id) {
        try (TimerMetric timer = metricsLogger.extendDimensions("method=retrieveSingleTodo").startTimer("Timing")) {
            for (Todo todo : todos) {
                if (todo.getId() == id) {
                    return todo;
                }
            }
            return null;
        }
    }

    public void updateTodo(Todo todo) {
        try (TimerMetric timer = metricsLogger.extendDimensions("method=updateTodo").startTimer("Timing")) {
            todos.remove(todo);
            todos.add(todo);
        }
    }

    public void addTodo(String name, String desc, Date targetDate,
                        boolean isDone) {
        try (TimerMetric timer = metricsLogger.extendDimensions("method=addTodo").startTimer("Timing")) {
            todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
        }
    }

    public void deleteTodo(int id) {
        try (TimerMetric timer = metricsLogger.extendDimensions("method=deleteTodo").startTimer("Timing")) {
            Iterator<Todo> iterator = todos.iterator();
            while (iterator.hasNext()) {
                Todo todo = iterator.next();
                if (todo.getId() == id) {
                    iterator.remove();
                }
            }
        }
    }
}