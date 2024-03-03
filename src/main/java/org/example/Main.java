package org.example;

import java.util.ArrayList;
import java.util.Scanner;

// Класс, представляющий покупателя
class Customer {
    public String fullName;
    public String email;
    public String phoneNumber;
    public String[] productPosition;
    public int quantity;

    public Customer(String fullName, String email, String phoneNumber, String[] productPosition, int quantity) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.productPosition = productPosition;
        this.quantity = quantity;
    }
}

// Класс, представляющий заказ
class Order {
    private final Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}

// Класс, представляющий магазин
class Store {
    private final String[] watchBrands = {"Rolex", "Omega", "Tag Heuer"};
    private final String[][] watchModels = {{"Submariner", "Daytona"}, {"Speedmaster", "Seamaster"}, {"Carrera", "Aquaracer"}};

    public void displayAvailablePositions() {
        System.out.println("Список доступных позиций в магазине:");
        for (int i = 0; i < watchModels.length; i++) {
            System.out.println(i + 1 + ". " + watchModels[i][0] + " - " + watchBrands[i]);
        }
    }

    public Customer createCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ФИО: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();
        displayAvailablePositions();
        System.out.print("Выберите позицию товара (введите номер из списка): ");
        int position = scanner.nextInt() - 1;
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Считываем оставшийся перевод строки после nextInt()
        String[] productPosition = {watchModels[position][0], watchBrands[position]};
        return new Customer(fullName, email, phoneNumber, productPosition, quantity);
    }

    public Order createOrder() {
        return new Order(createCustomer());
    }
}

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        ArrayList<Order> orders = new ArrayList<>();

        // Создание заказов
        for (int i = 0; i < 3; i++) {
            System.out.println("Создание заказа " + (i + 1));
            orders.add(store.createOrder());
        }

        // Вывод заказов
        for (Order order : orders) {
            Customer customer = order.getCustomer();
            System.out.println("Заказчик: " + customer.fullName);
            System.out.println("Email: " + customer.email);
            System.out.println("Телефон: " + customer.phoneNumber);
            System.out.println("Товар: " + customer.productPosition[0]);
            System.out.println("Фирма: " + customer.productPosition[1]);
            System.out.println("Количество: " + customer.quantity);
            System.out.println();
        }
    }
}
