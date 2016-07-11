package DataBase.DatabaseCreation;

import java.sql.*;

public class DataBaseCreator {

    private static final String MYSQL_LINK = "jdbc:mysql://localhost:3306/";
    private static Connection connection;

    public DataBaseCreator(String user, String password){


        try {
            connection = DriverManager.getConnection(MYSQL_LINK, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillTable(String tableName, String[] records){

        try {

            Statement statement = connection.createStatement();

            for(String record: records)
                statement.execute("INSERT INTO " + tableName + " VALUES (" + record + ");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeStatement(String sql){

        try {

            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            if ( resultSet != null){

                System.out.println(sql);
                displayResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void displayResultSet(ResultSet resultSet) throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for( int i=1; i <= resultSetMetaData.getColumnCount(); i++)
            System.out.print( resultSetMetaData.getColumnName(i) + "\t|");

        System.out.println();

        while ( resultSet.next()){

            for(int i=1; i <= resultSetMetaData.getColumnCount(); i++)
                System.out.print(resultSet.getString(i) + "\t|");

            System.out.println();
        }
    }

}
