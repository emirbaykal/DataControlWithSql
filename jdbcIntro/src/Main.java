import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        deleteData(960);
    }

    public static void insertDemo() throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "insert into city (Name,CountryCode,District,Population) " +
                    "values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"Duzce 2");
            statement.setString(2,"TUR");
            statement.setString(3,"Turkey");
            statement.setInt(4,70000);
            statement.executeUpdate();

            System.out.println("Kayit eklendi.");
        }catch (SQLException exception){
            helper.showErorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }
    }


    public static void selectDemo() throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Code,Name,Continent,Region from country");
            ArrayList<Country> countries = new ArrayList<Country>();
            while (resultSet.next()){
                countries.add(new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region")));
            }
            System.out.println(countries.size());
        }catch (SQLException exception){
            helper.showErorMessage(exception);
        }
        finally {
            connection.close();
        }
    }

    public static void updateData(int newPopulation, int cityID) throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "update city set population=? where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,newPopulation);
            statement.setInt(2,cityID);
            statement.executeUpdate();

            System.out.println("Kayit guncellendi.");
        }catch (SQLException exception){
            helper.showErorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }
    }

    public static void deleteData(int cityID) throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "delete from city where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,cityID);
            statement.executeUpdate();

            System.out.println("Kayit silindi.");
        }catch (SQLException exception){
            helper.showErorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }
    }
}