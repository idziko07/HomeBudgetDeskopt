package pl.budzet.domowy.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.budzet.domowy.destop.HomeBudget;
import pl.budzet.domowy.destop.HomeBudgetDao;
import pl.budzet.domowy.destop.LoadHomeBudget;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeBudgetController {

    private HomeBudgetDao homeBudgetDao;

    public HomeBudgetController(HomeBudgetDao homeBudgetDao) {
        this.homeBudgetDao = homeBudgetDao;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/przychod")
    public String przychod(Model model){
        model.addAttribute("przychod",new HomeBudget());
        return "przychod";
    }

    @PostMapping("/AddPrzychod")
    public String AddPrzychod(HomeBudget homeBudget){
        homeBudget.setType("przychod");
        homeBudgetDao.save(homeBudget);
        return "redirect:/";
    }

    @GetMapping("/wydatek")
    public String wydatek(Model model){
        model.addAttribute("wydatek",new HomeBudget());
        return "wydatek";
    }
    @PostMapping("/AddWydatek")
    public String AddWydatek(HomeBudget homeBudget){
        homeBudget.setType("wydatek");
        homeBudgetDao.save(homeBudget);
        return "redirect:/";
    }


    @GetMapping("/wyswietl_przychod")
    public String showPrzychod(Model model) {
        List<HomeBudget> przychod = homeBudgetDao.read("przychod");
        model.addAttribute("przychod", przychod);
        return "showPrzychod";
    }

    @GetMapping("/wyswietl_wydatek")
    public String showWydatek(Model model) {
        List<HomeBudget> wydatek = homeBudgetDao.read("wydatek");
        model.addAttribute("wydatek", wydatek);
        return "showWydatek";
    }

    @GetMapping("/przedzial_czasu")
    public String showTimes() {
        return "przedzialCzasu";
    }

    @GetMapping("/times")
    public String times(@RequestParam Date data1, @RequestParam Date data2, Model model){
        List<HomeBudget> time = homeBudgetDao.homeBudgetsListTime(data1, data2);
        model.addAttribute("times",time);
        return "times";
    }

    @GetMapping("/kwota")
    public String readMoney(){
        return "readMoney";
    }

    @GetMapping("/money")
    public String money(@RequestParam BigDecimal money, Model model){
       // BigDecimal moneyBig = BigDecimal.valueOf(money);
        List<HomeBudget> amount = homeBudgetDao.homeBudgetListAmount(money);
        model.addAttribute("amount",amount);
        return "money";
    }



}
