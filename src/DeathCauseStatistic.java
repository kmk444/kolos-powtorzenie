public class DeathCauseStatistic {
    private String code;
    private int[] deathCountArray = new int[20];

    public DeathCauseStatistic(String code, int[] deathCountArray) {
        this.code = code;
        this.deathCountArray = deathCountArray;
    }

    public String getCode() {
        return code;
    }

    public String toString(){
        String result = this.code;
        for (int i : deathCountArray) result += " / " + i;
        return result;
    }

    public static DeathCauseStatistic fromCsvLine(String line){
        String[] tokens = line.trim().split(",");
        String code = tokens[0].trim();
        int[] deathCountArray = new int[20];
        for (int i=0; i<deathCountArray.length; i++){
            if(!tokens[i+2].equals("-")) deathCountArray[i] = Integer.parseInt(tokens[i+2]);
        }
        return new DeathCauseStatistic(code, deathCountArray);
    }

    public AgeBracketDeaths ageBracketDeaths(int age){
        // ZNAJDOWANIE W KTOREJ GRUPIE JEST
        int arrayIndex = age/5; // 5, bo tyle sie miesci w jednej grupie

        if (arrayIndex >= deathCountArray.length) {
            arrayIndex = deathCountArray.length - 1; // ostatnia grupa dla wieku >=95
        }

        int young = arrayIndex * 5; // jak sie pomnozy przez to samo co sie podzielilo calkowicie to wychodzi dolna granica tego
        int old = young + 5; // wiadomo jak sie doda 5 to wyjdzie gorna granica
        int deathCount = deathCountArray[arrayIndex];
        return new AgeBracketDeaths(young,old,deathCount);
    }


    public class AgeBracketDeaths {
        public final int young, old, deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }

        public String toString(){
            return young + " / " + old + " / " + deathCount;
        }
    }
}
