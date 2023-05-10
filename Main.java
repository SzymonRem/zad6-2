import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongDateOfBirth extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch(WrongAge e) {
                System.out.println("Błędny wiek!");
            } catch(WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia!");
            } catch(ParseException e) {
                System.out.println("Błędny format daty!");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String readName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();
        return name;
    }

    public static int readAge() throws WrongAge {
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if(age < 0)
            throw new WrongAge();
        return age;
    }

    public static String readDateOfBirth() throws WrongDateOfBirth, ParseException {
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYYY");
        String dateStr = scan.nextLine();
        Date date = dateFormat.parse(dateStr);
        if(!dateStr.equals(dateFormat.format(date)))
            throw new WrongDateOfBirth();
        return dateStr;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth, ParseException {
        String name = readName();
        int age = readAge();
        String dateOfBirth = readDateOfBirth();
        (new Service()).addStudent(new Student(name, age, dateOfBirth));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.toString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.toString());
        }
    }
}



