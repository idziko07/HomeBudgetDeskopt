package pl.budzet.domowy;


import org.hibernate.validator.constraints.EAN;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LoadHomeBudget load = new LoadHomeBudget();
        input.useLocale(Locale.US);
        Menu[] menu = Menu.values();


        int number;
       do {
           number = 1;
           for (Menu menu1 : menu) {
               System.out.println(number + ". " + menu1);
               number++;
           }
           System.out.println("Co robimy:");
           number = input.nextInt();

           if(number==1){
                load.SaveHomeBudget("przychod");
           }else if (number == 2){
                load.SaveHomeBudget("wydatek");
           }else if (number == 3){
                load.readHomeBudget("przychod");
           }else if (number == 4 ){
                load.readHomeBudget("wydatek");
           }else if (number == 5){
                load.timeInterval();
           }else if (number == 6){
               load.amount();
           }else {
               number = 7;
           }
       }while(number != 7);


    }
}
