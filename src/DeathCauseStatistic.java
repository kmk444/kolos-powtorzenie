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
}
