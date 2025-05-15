import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<DeathCauseStatistic> stats = new ArrayList<>();
        DeathCauseStatistic stat1 = DeathCauseStatistic.fromCsvLine("A04.4          ,2,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,2,-,-");
        DeathCauseStatistic stat2 = DeathCauseStatistic.fromCsvLine("A08.3          ,1,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,1,-");
        DeathCauseStatistic stat3 = DeathCauseStatistic.fromCsvLine("B20.8          ,4,-,-,-,-,-,-,-,-,1,1,2,-,-,-,-,-,-,-,-,-");
        stats.add(stat1); stats.add(stat2); stats.add(stat3);
        DeathCauseStatisticList statisticList = new DeathCauseStatisticList(stats);
        statisticList.repopulate("zgony.csv");
        for(DeathCauseStatistic stat : statisticList){
            System.out.println(stat);
        }
        List<DeathCauseStatistic> deadly = statisticList.mostDeadlyDiseases(0, 3);
        System.out.println("----");
        for(DeathCauseStatistic ded : deadly){
            System.out.println(ded);
        }

    }
}