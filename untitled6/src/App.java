import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class App {
    private static final String CONTACT_BOOK_FILE = "contactbook.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        ContactBook contactBook = loadContactBook();

        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println("Ласкаво просимо в додаток Contact Book!");

        while (true) {
            System.out.print("> ");
            command = sc.nextLine().toLowerCase();

            switch (command) {
                case "add contact":
                    System.out.print("Введіть ім'я: ");
                    String name = sc.nextLine();
                    System.out.print("Введіть номер телефону: ");
                    String phoneNumber = sc.nextLine();
                    System.out.print("Введіть email: ");
                    String email = sc.nextLine();
                    System.out.print("Введіть дату народження (у форматі dd-mm-yyyy): ");
                    String dob = sc.nextLine();
                    contactBook.addContact(name, phoneNumber, email, dob);
                    break;

                case "show all":
                case "print all":
                    contactBook.showAllContacts();
                    break;

                case "delete contact":
                    System.out.print("Введіть ім'я контакту, який потрібно видалити: ");
                    String deleteName = sc.nextLine();
                    contactBook.deleteContact(deleteName);
                    break;

                case "search by name":
                    System.out.print("Введіть ім'я контакту, який потрібно знайти: ");
                    String searchName = sc.nextLine();
                    contactBook.searchByName(searchName);
                    break;

                case "search by number":
                    System.out.print("Введіть номер телефону контакту, який потрібно знайти: ");
                    String searchNumber = sc.nextLine();
                    contactBook.searchByNumber(searchNumber);
                    break;

                case "search by email":
                    System.out.print("Введіть email контакту, який потрібно знайти: ");
                    String searchEmail = sc.nextLine();
                    contactBook.searchByEmail(searchEmail);
                    break;

                case "edit contact":
                    System.out.print("Введіть ім'я контакту, який потрібно відредагувати: ");
                    String editName = sc.nextLine();
                    contactBook.editContact(editName);
                    break;

                case "help":
                    showHelp();
                    break;

                case "exit":
                case "quit":
                    saveContactBook(contactBook);
                    System.out.println("Вихід з програми...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Недійсна команда. Введіть 'help' для доступних команд.");
                    break;
            }
        }
    }

    private static void showHelp() {
        System.out.println("Доступні команди:");
        System.out.println("1. Add contact - Додати новий контакт до Книги контактів.");
        System.out.println("2. Show all - Відобразити всі контакти у Книзі контактів.");
        System.out.println("3. Delete contact - Видалити контакт з Книги контактів.");
        System.out.println("4. Search by name - Знайти контакт за ім'ям.");
        System.out.println("5. Search by number - Знайти контакт за номером телефону.");
        System.out.println("6. Search by email - Знайти контакт за email.");
        System.out.println("7. Edit contact - Редагувати контакт.");
        System.out.println("8. Help - Показати доступні команди.");
        System.out.println("9. Exit - Вийти з програми.");
    }

    private static void saveContactBook(ContactBook contactBook) {
        try (Writer writer = new FileWriter(CONTACT_BOOK_FILE)) {
            gson.toJson(contactBook, writer);
            System.out.println("Книгу контактів збережено у файл: " + CONTACT_BOOK_FILE);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні книги контактів у файл: " + e.getMessage());
        }
    }

    private static ContactBook loadContactBook() {
        File file = new File(CONTACT_BOOK_FILE);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                return gson.fromJson(reader, ContactBook.class);
            } catch (IOException e) {
                System.out.println("Помилка при зчитуванні книги контактів з файлу: " + e.getMessage());
            }
        }
        return new ContactBook();
    }
}
