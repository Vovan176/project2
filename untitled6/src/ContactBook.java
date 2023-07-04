import java.util.*;

public class ContactBook {
    private List<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber, String email, String dateOfBirth) {
        Contact contact = new Contact(name, phoneNumber, email, dateOfBirth);
        contacts.add(contact);
        System.out.println("Контакт додано: " + contact);
    }

    public void deleteContact(String name) {
        boolean found = false;
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Контакт видалено: " + contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Контакт з ім'ям '" + name + "' не знайдений.");
        }
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Знайдено контакт: " + contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Контакт з ім'ям '" + name + "' не знайдений.");
        }
    }

    public void searchByNumber(String phoneNumber) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Знайдено контакт: " + contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Контакт з номером телефону '" + phoneNumber + "' не знайдений.");
        }
    }

    public void searchByEmail(String email) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Знайдено контакт: " + contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Контакт з email '" + email + "' не знайдений.");
        }
    }

    public void editContact(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Введіть нове ім'я (або залиште порожнім, якщо не потрібно змінювати): ");
                String newName = sc.nextLine();
                if (!newName.isEmpty()) {
                    contact.setName(newName);
                }
                System.out.print("Введіть новий номер телефону (або залиште порожнім, якщо не потрібно змінювати): ");
                String newPhoneNumber = sc.nextLine();
                if (!newPhoneNumber.isEmpty()) {
                    contact.setPhoneNumber(newPhoneNumber);
                }
                System.out.print("Введіть новий email (або залиште порожнім, якщо не потрібно змінювати): ");
                String newEmail = sc.nextLine();
                if (!newEmail.isEmpty()) {
                    contact.setEmail(newEmail);
                }
                System.out.print("Введіть нову дату народження (у форматі dd-mm-yyyy) (або залиште порожнім, якщо не потрібно змінювати): ");
                String newDOB = sc.nextLine();
                if (!newDOB.isEmpty()) {
                    contact.setDateOfBirth(newDOB);
                }
                System.out.println("Контакт відредаговано: " + contact);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Контакт з ім'ям '" + name + "' не знайдений.");
        }
    }

    public void showAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Книга контактів порожня.");
        } else {
            System.out.println("Всі контакти у Книзі контактів:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}