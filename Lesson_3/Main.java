package Lesson_3;

import java.util.*;

/*
      1.Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
        Найти список слов, из которых состоит текст (дубликаты не считать);
        Посчитать сколько раз встречается каждое слово (использовать HashMap);
      2.Написать простой класс PhoneBook(внутри использовать HashMap):
        В качестве ключа использовать фамилию
        В каждой записи всего два поля: phone, e-mail
        Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
        и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть несколько записей.
        Итого должно получиться 3 класса Main, PhoneBook, Person.
*/
public class Main {
    private static String[] arrayWord;
    private static String text = "Катилась апельсинка " +
            "По имени Мальвинка " +
            "Уроки не учила и " +
            "Двойку получила " +
            "А потом пошла гулять " +
            "Получила цифру пять " +
            "Катилась апельсинка " +
            "По имени Мальвинка " +
            "Уроки не учила и " +
            "Двойку получила " +
            "А потом пошла гулять " +
            "Получила цифру пять " +
            "Получила цифру семь ";
    private static PhoneBook myContacts = new PhoneBook("Мои контакты");

    public static String[] arrayMaker (String n){
        return arrayWord = n.split(" ");

    }
    private  static LinkedHashSet<String> doubleSort(String[] n){
        LinkedHashSet<String> doubleSearch = new LinkedHashSet<>();
        for (int i = 0; i<n.length;i++) {
            doubleSearch.add(n[i]);
        }
        return doubleSearch;
    }
    private static LinkedHashMap<String, Integer> countWords (String[] n) {
        LinkedHashMap<String, Integer> wordsCounter =new LinkedHashMap();
        int count = 1;
        for (int i = 0; i<n.length;i++) {
            if (wordsCounter.containsKey(n[i]) == true) {
                wordsCounter.put(n[i], count + wordsCounter.get(n[i]));
            } else {
                wordsCounter.put(n[i], count);
            }
        }
        return wordsCounter;
    }
    public static void makeNote (String f , String ph, String e){
        Person newPerson = new Person(f,ph,e);
        myContacts.newPhoneBookNote(newPerson);
    }
    public static void showPhone(String family){
        System.out.println(myContacts.getPhones(family));
    }
    public static void showMail(String family){
        System.out.println(myContacts.getEmail(family));
    }
    public static void main(String[] args) {
        System.out.println(doubleSort(arrayMaker(text)));
        System.out.println(countWords(arrayWord));
        makeNote("Иванов","89168888888","ivanov@mail.ru");
        makeNote("Никитин","8916698888","nikitinRacer@mail.ru");
        makeNote("Иванов","8916898008","ivanov1982@mail.ru");
        makeNote("Петров","8916898939","petrov@mail.ru");
        showPhone("Петров");
        showMail("Иванов");



    }
}

class PhoneBook {
    private String name;
    private final HashMap<String, ArrayList> dataNote = new HashMap<>();

    PhoneBook (String n){
        this.name = n;
    }

    protected void newPhoneBookNote (Person person){
        ArrayList<String[]> cashCont = new ArrayList<>();
            if (dataNote.containsKey(person.getFamily()) == true) {
                //Достать старые данные
                cashCont = (dataNote.get(person.getFamily()));
                //Соединить с новыми
                cashCont.add(person.getContacts());
                //Положить обратно
                dataNote.put(person.getFamily(), cashCont);
            } else {
                cashCont.add(person.getContacts());
                dataNote.put(person.getFamily(), cashCont);
            }
    }

    protected ArrayList getPhones (String family){
        return viewFields(0, family);
    }
    protected ArrayList getEmail (String family){
        return viewFields(1, family);
    }
    protected ArrayList<String> viewFields (int contactType, String family) throws NullPointerException{

        ArrayList<String> oneNote = new ArrayList<>();
        oneNote.add(family);
        ArrayList<String[]> cashNote;
        try {
            cashNote = dataNote.get(family);
            for (int i = 0; i < cashNote.size(); i++) {
                String[] cashPhone = cashNote.get(i);
                oneNote.add(cashPhone[contactType]);
            }
        } catch (NullPointerException n){
            System.out.println("Contact not found.");
        }
        return oneNote;
    }


}

class Person {
private String family;
private String PhoneNumber;
private String email;

Person (String f , String ph, String e){
    this.PhoneNumber = ph;
    this.email = e;
    this.family = f;

}

protected String getFamily(){
    return family;
}
protected String getEmail (){
    return email;
}
protected String getPhoneNumber (){
    return PhoneNumber;
}
protected String[] getContacts (){
    String[] contacts = new String[]{PhoneNumber,email};
    return contacts;
}
}
