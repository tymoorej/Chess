package GenerationalAlgorithm;

public class IDScoreCombo implements Comparable<IDScoreCombo>{
    public static final int winModifier = 50;
    public static final int lossModifier = -50;
    public static final int tieModifier = -1;

    private int id;
    private int wins;
    private int losses;
    private int ties;


    public IDScoreCombo(int id) {
        this.id = id;
        wins = 0;
        losses = 0;
        ties = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return (wins * winModifier) + (losses * lossModifier) + (ties * tieModifier);
    }

    public void addWin(){
        wins++;
    }

    public void addLoss(){
        losses++;
    }

    public void addTie(){
        ties++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }


    @Override
    public int compareTo(IDScoreCombo o) {
        if (getScore() > o.getScore()){
            return 1;
        }

        if (getScore() < o.getScore()){
            return -1;
        }
        return 0;
    }
}
