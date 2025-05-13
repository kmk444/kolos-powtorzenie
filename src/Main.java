public class Main {
    public static void main(String[] args) {


        DeathCauseStatistic stat = DeathCauseStatistic.fromCsvLine("A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-");
        System.out.println(stat);
    }
}