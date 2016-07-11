package DataBase;

import java.sql.*;

public class DataBaseRunner {

    private final static DataBaseRunner dataBaseInstance = new DataBaseRunner();
    private static Connection connection;

    private DataBaseRunner(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection( MySqlInfo.LOCAL_HOST, MySqlInfo.USER, MySqlInfo.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseRunner getInstance(){
        return dataBaseInstance;
    }

    public void connect(String dataBaseName){

        try {
            Statement statement = connection.createStatement();
            statement.execute("USE " + dataBaseName + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String select(String district, String street, String roomQtyFrom, String roomQtyTo, String priceFrom, String priceTo){

        try {

            Statement statement = connection.createStatement();

            statement.execute("SELECT * FROM flats WHERE " +
                    "District LIKE '%"  + district    + "%' AND " +
                    "Street LIKE '%"    + street      + "%' AND " +
                    "(RoomQty BETWEEN " + roomQtyFrom + " AND "   + roomQtyTo + " ) AND " +
                    "(Price BETWEEN "   + priceFrom   + " AND "   + priceTo + " );"
            );

            return convertToHtml( statement.getResultSet());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String convertToHtml(ResultSet resultSet) throws SQLException {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.
                append("<html> <head> <title>Result</title> </head> <body>").
                append("<b>Query result:</b>").
                append( createTable(resultSet)).
                append("</body> </html>");

        return stringBuilder.toString();
    }

    private String createTable(ResultSet resultSet) throws SQLException {

        StringBuilder sb = new StringBuilder();

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        sb.append("<table border='1'>");

        sb.append("<tr>");
        for(int i=1; i<=rsMetadata.getColumnCount(); i++){
            sb.append( "<th bgcolor='#A9A9F5'>")
              .append( rsMetadata.getColumnName(i))
              .append( "</th>");
        }
        sb.append("</tr>");

        while ( resultSet.next()){

            sb.append("<tr>");
            for(int i=1; i<=rsMetadata.getColumnCount(); i++){
                sb.append( "<td bgcolor='#E6E6E6'>")
                  .append( resultSet.getString(i))
                  .append( "</td>");
            }
            sb.append("</tr>");
        }

        sb.append("</table>");

        return sb.toString();
    }

}
