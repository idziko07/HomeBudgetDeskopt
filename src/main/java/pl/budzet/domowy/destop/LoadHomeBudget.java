package pl.budzet.domowy.destop;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
public class LoadHomeBudget {
    private Scanner input = new Scanner(System.in);

    public void SaveHomeBudget(String balance){
        System.out.println("Podaj opis: ");
        String description = input.nextLine();

        System.out.println("Podaj kwote: ");
        BigDecimal amount = input.nextBigDecimal();
        input.nextLine();
        HomeBudgetDao homeBudgetDao = new HomeBudgetDao();
        homeBudgetDao.save(new HomeBudget(balance,description,amount));
        homeBudgetDao.close();
    }

    public void readHomeBudget(String select){
        HomeBudgetDao homeBudgetDao = new HomeBudgetDao();
        List<HomeBudget> homeBudgets = homeBudgetDao.read(select);

        for (HomeBudget homeBudget : homeBudgets) {
            System.out.println(homeBudget.toString());
        }
        homeBudgetDao.close();

    }

    public void timeInterval(){
        System.out.println("Podaj 1 date: yyyy-mm-dd");
        String date = input.nextLine();

        System.out.println("Podaj 2 date: yyyy-mm-dd");
        String date2 = input.nextLine();

        Date dateFormat = Date.valueOf(date);
        Date dateFormat2 = Date.valueOf(date2);
        HomeBudgetDao homeBudgetDao = new HomeBudgetDao();
        List<HomeBudget> homeBudgets = homeBudgetDao.homeBudgetsListTime(dateFormat,dateFormat2);
        homeBudgetDao.close();
        for (HomeBudget homeBudget : homeBudgets) {
            System.out.println(homeBudget.toString());
        }
    }

    public void amount(){
        System.out.println("Wpisz kwote: ");
        BigDecimal amount = input.nextBigDecimal();
        HomeBudgetDao homeBudgetDao = new HomeBudgetDao();
        List<HomeBudget> homeBudgets = homeBudgetDao.homeBudgetListAmount(amount);
        homeBudgetDao.close();
        for (HomeBudget homeBudget : homeBudgets) {
            System.out.println(homeBudget.toString());
        }
    }
}
