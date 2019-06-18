package ru.geekbrains.musicportal;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс для примера
 */
@Slf4j //логгирование для класса
@Getter //геттеры полей
@Setter //сеттеры полей
@NoArgsConstructor //конструктор пустой
@AllArgsConstructor //конструктор со всеми полями
@RequiredArgsConstructor //конструктор с необходимыми полями отмеченными аннотацией @NonNull
public class TestClass {

    @NonNull
    private String name;

    private int age;

    public static void main(String[] args) {
        TestClass testClass1 = new TestClass();
        TestClass testClass2 = new TestClass("Ivan", 10);
        TestClass testClass3 = new TestClass("Ivan");
        log.info("Это пример логирования: {}, {}", testClass1.getAge(), testClass2.getName());
    }
}


