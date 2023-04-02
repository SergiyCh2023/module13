/*
Завдання 1

Програма повинна містити методи для реалізації наступного функціоналу:

    створення нового об'єкта в https://jsonplaceholder.typicode.com/users. Можливо, ви не побачите одразу змін на сайті.
     Метод працює правильно, якщо у відповідь на JSON з об'єктом повернувся такий самий JSON, але зі значенням id
     більшим на 1, ніж найбільший id на сайті.

    оновлення об'єкту в https://jsonplaceholder.typicode.com/users. Можливо, ви не побачите одразу змін на сайті.
    Вважаємо, що метод працює правильно, якщо у відповідь ви отримаєте оновлений JSON (він повинен бути таким самим, що
     ви відправили).

    видалення об'єкта з https://jsonplaceholder.typicode.com/users. Тут будемо вважати коректним результат - статус
    відповіді з групи 2xx (наприклад, 200).

    отримання інформації про всіх користувачів https://jsonplaceholder.typicode.com/users

    отримання інформації про користувача за id https://jsonplaceholder.typicode.com/users/{id}

    отримання інформації про користувача за username - https://jsonplaceholder.typicode.com/users?username={username}
 */


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Main {

    private static final String CREATE_USER_URI = "https://jsonplaceholder.typicode.com/users/";
    private static final String GET_USER_BY_ID = "https://jsonplaceholder.typicode.com/users/";
    private static final String GET_USER_COMMENTS = "https://jsonplaceholder.typicode.com/users/1/posts/";

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        User user = createDefaultUser();

        final User createdUser = HttpUtil.userCreate(URI.create(CREATE_USER_URI), user);
        System.out.println("createdUser: " + createdUser);


        final User updateUser= HttpUtil.userUpdate(user);
        System.out.println("updateUser: " + updateUser);


        HttpUtil.delete(createdUser.getId());


        final List<User> users = HttpUtil.getListUsers(URI.create(GET_USER_BY_ID));
        for (User u : users) {
            System.out.println("info about all users: " + u);
        }


        final User someUser = HttpUtil.sendGetUserById(3);
        System.out.println("get information by Id: " + someUser);


        HttpUtil.sendGetUserByName("Bret");


        HttpUtil.createFileAndPrint();


        HttpUtil.getListOpenTasks();


    }

    private static User createDefaultUser() {
        User user = new User();
        user.setId(1);
        user.setName("Papa");
        user.setUsername("Carlo");
        user.setEmail("P.Carlo@gmail.com");
        return user;
    }

}
