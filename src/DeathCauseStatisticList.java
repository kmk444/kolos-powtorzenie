import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeathCauseStatisticList implements Iterable<DeathCauseStatistic> {
    List<DeathCauseStatistic> deathCauseStatistics;

    @Override
    public Iterator<DeathCauseStatistic> iterator() {
        return deathCauseStatistics.iterator();
    }

    public DeathCauseStatisticList(List<DeathCauseStatistic> deathCauseStatistics) {
//        this.deathCauseStatistics = new ArrayList<>();
        this.deathCauseStatistics = deathCauseStatistics;
    }



    public void repopulate(String path) {
        try {
            this.deathCauseStatistics.clear();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.readLine();
            String line;
            while((line = reader.readLine())!=null)
            {
                String[] tokens = line.trim().split(",");
                String code = tokens[0].trim();
                int[] deathCountArray = new int[20];
                for (int i=0; i<deathCountArray.length; i++){
                    if(!tokens[i+2].equals("-")) deathCountArray[i] = Integer.parseInt(tokens[i+2]);
                }
                if(!tokens[0].equals("OGÓŁEM         ")) {
                    this.deathCauseStatistics.add(new DeathCauseStatistic(code, deathCountArray));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n) {
        // 1. Sortuj listę malejąco według liczby zgonów w grupie wiekowej
        deathCauseStatistics.sort((stat1, stat2) -> {
            int deaths1 = stat1.ageBracketDeaths(age).deathCount;
            int deaths2 = stat2.ageBracketDeaths(age).deathCount;
            return Integer.compare(deaths2, deaths1);
        });

        // 2. Zwróć podlistę pierwszych n elementów (lub mniej, jeśli lista jest krótsza)
        int endIndex = Math.min(n, deathCauseStatistics.size());
        return new ArrayList<>(deathCauseStatistics.subList(0, endIndex));
    }


}

