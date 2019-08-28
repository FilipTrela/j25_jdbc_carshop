package pl.sda.gdajava25;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private static final LoadDataFromUser SCANNER = new LoadDataFromUser();

    public static void main(String[] args) {
        CarDao carDao = null;
        try {
            carDao = new CarDao();
        } catch (SQLException e) {
            System.err.println("Car data access object cannot be created. Mysql error.");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.err.println("Configuration file error. ");
            e.printStackTrace();
            return;
        }
        System.out.println("Witam w bazie danych warsztatu samochodowego");
        String commend;
        try {
            do {
                commend = SCANNER.askCommend();
                if (commend.toUpperCase().contains("WSTAW")) {
                    carDao.insertCar(makeCar());
                } else if (commend.toUpperCase().contains("USUN") && commend.toUpperCase().contains("ID")) {
                    boolean powodzenie = carDao.deleteCarById(SCANNER.askId());
                    if (powodzenie) {
                        System.out.println("Usunięto");
                    } else {
                        System.out.println("Coś poszło nie tak :(");
                    }
                } else if (commend.toUpperCase().contains("USUN") && commend.toUpperCase().contains("REJSTRACYJNYM")) {
                    boolean powodzenie = carDao.deleteCarByNrRej(SCANNER.askNmrRej());
                    if (powodzenie) {
                        System.out.println("Usunięto");
                    } else {
                        System.out.println("Coś poszło nie tak :(");
                    }
                } else if (commend.equalsIgnoreCase("listuj")) {
                    carDao.getListAllCars().forEach(System.out::println);
                }else if(isSzukaPoRejstracji(commend)){
                    carDao.getCarListByNrRej(SCANNER.askNmrRej()).forEach(System.out::println);
                }else if(isSzukaPoNazwisku(commend)){
                    carDao.getCarListByName(SCANNER.askNazWla()).forEach(System.out::println);
                }else if(isSzukaByModeOrMarka(commend)){
                    carDao.getCarListByMrkMod(SCANNER.askMrkMod()).forEach(System.out::println);
                }
            } while (!commend.toUpperCase().contains("EXIT"));
        } catch (SQLException e) {
            System.err.println("Error executing command: " + e.getMessage());
            e.printStackTrace();
        }


    }

    private static boolean isSzukaPoRejstracji(String commend) {
        return commend.toUpperCase().contains("SZUKAJ")&&commend.toUpperCase().contains("REJSTRACJI");
    }

    private static boolean isSzukaPoNazwisku(String commend) {
        return commend.toUpperCase().contains("SZUKAJ")&&commend.toUpperCase().contains("NAZWISKU");
    }

    private static boolean isSzukaByModeOrMarka(String commend) {
        return commend.toUpperCase().contains("SZUKAJ")&&(commend.toUpperCase().contains("MARCE")||commend.toUpperCase().contains("MODEL"));
    }

    private static Car makeCar() {
        Car car = new Car();
        car.setNr_rejstracyjny(SCANNER.askNmrRej());
        car.setPrzebieg(SCANNER.askPrzebieg());
        car.setMarka_model(SCANNER.askMrkMod());
        car.setRocznik(SCANNER.askRocznik());
        car.setTyp(SCANNER.askType());
        car.setNazwisko_właściciela(SCANNER.askNazWla());
        return car;
    }
}
