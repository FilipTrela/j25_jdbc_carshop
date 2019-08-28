package pl.sda.gdajava25;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class Car {
    private Long id;
    private String nr_rejstracyjny;
    private Long przebieg;
    private String marka_model;
    private Integer rocznik;
    private Type typ;
    private String nazwisko_właściciela;

    public Car(String nr_rejstracyjny, Long przebieg, String marka_model, Integer rocznik, Type typ, String nazwisko_właściciela) {
        this.nr_rejstracyjny = nr_rejstracyjny;
        this.przebieg = przebieg;
        this.marka_model = marka_model;
        this.rocznik = rocznik;
        this.typ = typ;
        this.nazwisko_właściciela = nazwisko_właściciela;
    }

}
