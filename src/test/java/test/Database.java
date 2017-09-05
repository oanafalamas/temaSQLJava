package test;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

        String sqlFirstName = BaseTest.readFromProperties("FirstName", "src/test/resources/database.properties");
        String sqlLastName = BaseTest.readFromProperties("LastName", "src/test/resources/database.properties");
        String sqlCompany = BaseTest.readFromProperties("Company", "src/test/resources/database.properties");
        String sqlAdress_1 = BaseTest.readFromProperties("Adress_1", "src/test/resources/database.properties");
        String sqlAdress_2 = BaseTest.readFromProperties("Adress_2", "src/test/resources/database.properties");
        String sqlCity = BaseTest.readFromProperties("City", "src/test/resources/database.properties");
        String sqlPostcode = BaseTest.readFromProperties("Postcode", "src/test/resources/database.properties");
        String sqlCountryID = BaseTest.readFromProperties("Country_id", "src/test/resources/database.properties");
        String sqlZoneID = BaseTest.readFromProperties("Zone_id", "src/test/resources/database.properties");
        String sqlCustom_field = BaseTest.readFromProperties("Custom_field", "src/test/resources/database.properties");

        String loginEmail = BaseTest.readFromProperties("email", "src/test/resources/login.properties");
        String sqlEmail = BaseTest.readFromProperties("email", "src/test/resources/sqllogin.properties");
        String loginPassword = BaseTest.readFromProperties("password", "src/test/resources/login.properties");
    String dburl = BaseTest.readFromProperties("url", "src/test/resources/databaseLogIn.properties");
    String dbuser= BaseTest.readFromProperties("email", "src/test/resources/databaseLogIn.properties");
    String dbpassword = BaseTest.readFromProperties("password", "src/test/resources/databaseLogIn.properties");


    //Cosntructor
    public Database() throws SQLException {
        this.connectToDatabase();
    }


    //Methods
    public Connection connectToDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
        return connection;
    }

    public void clear(Statement stmt) throws SQLException {
        stmt.executeUpdate("DELETE FROM oc_cart where customer_id=(select customer_id from oc_customer where email=\""+loginEmail+"\")");
    }

    public void update(Statement stmt) throws SQLException{
        stmt.executeUpdate("insert into oc_address (customer_id, firstname, lastname, company, address_1, address_2, city, postcode, country_id, zone_id, custom_field) values ((select customer_id from oc_customer where email=\""+sqlEmail+"\"), \""+sqlFirstName+"\", \""+sqlLastName+"\", \""+sqlCompany+"\", \""+sqlAdress_1+"\", \""+sqlAdress_2+"\", \""+sqlCity+"\", \""+sqlPostcode+"\", "+sqlCountryID+", "+sqlZoneID+","+sqlCustom_field+");");
    }

    public void updateQuantity(Statement stmt) throws SQLException{
        stmt.executeUpdate("update oc_cart set quantity=quantity+1 where customer_id=(select customer_id from oc_customer where email=\"Oana253@gmail.com\"); ");
    }

    public String getAddress1(){
        return sqlAdress_1;
    }

    public String getAddress2(){
        return sqlAdress_2;
    }

    public void closeDatabase(Statement stmt) throws SQLException {
        stmt.close();
        connectToDatabase().close();
    }

}
