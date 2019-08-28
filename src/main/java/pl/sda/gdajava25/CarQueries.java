package pl.sda.gdajava25;

public interface CarQueries {
    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `cars`(\n" +
            "            `id` int not null auto_increment primary key,\n" +
            "            `nr_rejstracyjny` varchar(10) not null unique,\n" +
            "            `przebieg` LONG not null,\n" +
            "            `marka_model` VARCHAR(100) not null,\n" +
            "            `rocznik` int not null,\n" +
            "            `typ` VARCHAR(15) NOT NULL,\n" +
            "            `nazwisko_właściciela` VARCHAR(100) not null\n" +
            "            );";

    String INSERT_QUERY = "insert into `cars`\n" +
            "            (`nr_rejstracyjny`,`przebieg`,`marka_model`,`rocznik`,`typ`,`nazwisko_właściciela`)\n" +
            "            values( ? , ? , ? , ? , ? , ?);";

    String DELETE_BY_ID_QUERY = "DELETE FROM `cars`\n" +
            "WHERE `id` = ?;";

    String DELETE_BY_NR_REJ_QUERY = "DELETE FROM `cars`\n" +
            "WHERE `nr_rejstracyjny` = ?;";

    String LIST_ALL_CARS = "SELECT * FROM `cars`;";

    String SHOW_BY_NR_REJ = "SELECT * FORM `cars` \n" +
            "WHERE `nr_rejstracyjny` LIKE ?;";

    String SHOW_BY_NAZWISKO = "SELECT * FORM `cars` \n" +
            "WHERE `nazwisko_właściciela` LIKE ?;";

    String SHOW_BY_MARKA_MODEL = "SELECT * FORM `cars` \n"+
            "WHERE `marka_model` LIKE ?;";

}
