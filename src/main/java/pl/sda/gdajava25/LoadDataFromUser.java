package pl.sda.gdajava25;

import java.util.Scanner;

public class LoadDataFromUser {
    private final static Scanner SCANNER = new Scanner(System.in);

    public String askCommend() {
        System.out.println("Podaj komende [exit] : ");
        return SCANNER.nextLine();
    }


    public Long askId() {
        System.out.println("Podaj ID : ");
        return Long.parseLong(SCANNER.nextLine());
    }

    public String askNmrRej() {
        System.out.println("Podaj numer rejstracyjny : ");
        return SCANNER.nextLine();
    }

    public String askMrkMod() {
        System.out.println("Podaj marke i model : ");
        return SCANNER.nextLine();
    }

    public String askNazWla() {
        System.out.println("Podaj nazwisko właściciela : ");
        return SCANNER.nextLine();
    }

    public Long askPrzebieg() {
        System.out.println("Podaj przebieg : ");
        return Long.parseLong(SCANNER.nextLine());
    }

    public Integer askRocznik() {
        System.out.println("Podaj rocznik : ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public Type askType() {
        Type type = null;
        do {
            type = Type.valueOf(SCANNER.nextLine());
        } while (!type.equals(null));
        return null;
    }
}
