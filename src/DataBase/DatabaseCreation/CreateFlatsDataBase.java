package DataBase.DatabaseCreation;

import DataBase.MySqlInfo;

public class CreateFlatsDataBase {

    public static void main(String[] args) {

        DataBaseCreator database = new DataBaseCreator( MySqlInfo.USER, MySqlInfo.PASSWORD);

        database.executeStatement("CREATE DATABASE flats_database;");
        database.executeStatement("USE flats_database;");
        database.executeStatement("CREATE TABLE flats (" +
                "Id INT NOT NULL AUTO_INCREMENT," +
                "District VARCHAR(20), " +
                "Street VARCHAR(20), " +
                "House SMALLINT," +
                "Apartment SMALLINT," +
                "RoomQty TINYINT," +
                "Square FLOAT(5,2)," +
                "Price FLOAT(10,2)," +
                "PRIMARY KEY (Id))");

        database.executeStatement("SHOW tables;");

        database.fillTable("flats", new String[]{
                "'0', 'Darnitskiy', 'Tampere', '22', '56', '2', '60.1', '580000'",
                "'0', 'Darnitskiy', 'Tuchinu', '34', '345', '3', '80.4', '2910000'",
                "'0', 'Darnitskiy', 'Tampere', '1', '18', '2', '55.0', '590000'",
                "'0', 'Pecherskiy', 'Arsenalna', '5', '101', '1', '25.0', '690000'",
                "'0', 'Pecherskiy', 'Lavrska', '10', '180', '4', '105.0', '2590000'"
        });

        database.executeStatement("SELECT * FROM flats;");

    }
}
