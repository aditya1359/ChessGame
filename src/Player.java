public class Player extends Account {

    private Person person;
    private boolean isWhiteSide;
    private boolean isChecked;
    private int totalGamesPlayed;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isWhiteSide() {
        return isWhiteSide;
    }

    public void setWhiteSide(boolean whiteSide) {
        isWhiteSide = whiteSide;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }
}
