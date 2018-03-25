package pl.budzet.domowy;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeBudgetDao {
    private static final String URL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
    private static final String ROOT = "root";
    private static final String PASSWORD = "root";
    private static final String CREATE_SCHEMA = "create SCHEMA if not exists budget DEFAULT CHARACTER SET utf8";
    private static final String USE = "use budget";
    private static final String CREATE_TABLE_BOOKS = "create table if not exists home_budget(\n" +
            "id integer auto_increment primary key,\n" +
            "type varchar(20),\n" +
            "description varchar(250),\n" +
            "amount int(11),\n" +
            "date DATE\n" +
            ");";
    private Connection connection;

    public HomeBudgetDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SCHEMA);
            preparedStatement.execute();

            PreparedStatement preparedStatementUSE = connection.prepareStatement(USE);
            preparedStatementUSE.execute();

            PreparedStatement preparedStatementTable = connection.prepareStatement(CREATE_TABLE_BOOKS);
            preparedStatementTable.execute();
        } catch (ClassNotFoundException e) {
            System.out.println("Bład podczas ładowania sterownika: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Bład : " + e.getMessage());
        }
    }

    public void save(HomeBudget homeBudget) {
        final String sql = "insert into home_budget values(NULL,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, homeBudget.getType());
            preparedStatement.setString(2, homeBudget.getDescription());
            preparedStatement.setBigDecimal(3, homeBudget.getAmount());
            preparedStatement.setDate(4, homeBudget.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Bład podczas dodwania ksiażki:" + e.getMessage());
        }

    }

    public List read(String select) {
        final String sql = "SELECT * FROM home_budget WHERE type = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,select);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<HomeBudget> listHomeBodget = new ArrayList<>();
            while (resultSet.next()) {
                HomeBudget homeBudget = new HomeBudget();
                homeBudget.setId(resultSet.getLong("id"));
                homeBudget.setType(resultSet.getString("type"));
                homeBudget.setDescription(resultSet.getString("description"));
                homeBudget.setAmount(resultSet.getBigDecimal("amount"));
                homeBudget.setDate(resultSet.getDate("date"));

                listHomeBodget.add(homeBudget);
            }

            return listHomeBodget;

        } catch (SQLException e) {
            System.out.println("Bład podczas wczytywania ksiażki:" + e.getMessage());
        }
        return null;
    }

    public List<HomeBudget> homeBudgetsListTime(Date date, Date date2){
        final String sql = "SELECT * FROM home_budget WHERE date BETWEEN ? AND ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1,date);
            preparedStatement.setDate(2,date2);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<HomeBudget> listHomeBodget = new ArrayList<>();
            while (resultSet.next()) {
                HomeBudget homeBudget = new HomeBudget();
                homeBudget.setId(resultSet.getLong("id"));
                homeBudget.setType(resultSet.getString("type"));
                homeBudget.setDescription(resultSet.getString("description"));
                homeBudget.setAmount(resultSet.getBigDecimal("amount"));
                homeBudget.setDate(resultSet.getDate("date"));

                listHomeBodget.add(homeBudget);
            }

            return listHomeBodget;

        } catch (SQLException e) {
            System.out.println("Bład podczas wczytywania ksiażki:" + e.getMessage());
        }
        return null;

    }

    public List<HomeBudget> homeBudgetListAmount(BigDecimal amount){

        final String sql = "SELECT * FROM home_budget WHERE amount > ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1,amount);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<HomeBudget> listHomeBodget = new ArrayList<>();
            while (resultSet.next()) {
                HomeBudget homeBudget = new HomeBudget();
                homeBudget.setId(resultSet.getLong("id"));
                homeBudget.setType(resultSet.getString("type"));
                homeBudget.setDescription(resultSet.getString("description"));
                homeBudget.setAmount(resultSet.getBigDecimal("amount"));
                homeBudget.setDate(resultSet.getDate("date"));

                listHomeBodget.add(homeBudget);
            }

            return listHomeBodget;

        } catch (SQLException e) {
            System.out.println("Bład podczas wczytywania ksiażki:" + e.getMessage());
        }
        return null;

    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Bład podczas zamykania bazy:" + e.getMessage());
        }
    }
}

