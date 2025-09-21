import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Habit {

    private String name;
    private LocalDate lastCheckedIn;
    private LocalDate startDate;

    public Habit(String name) {
        this.name = name;
        this.lastCheckedIn = LocalDate.now();
        this.startDate = LocalDate.now();
    }

    /**getters and setters**/
    public long getStreak() { //returns how long user has/hasn't done something by using the start date and last checked in date
        return ChronoUnit.DAYS.between(startDate, LocalDate.now());
    }

    public LocalDate getLastCheckedIn() { //gets the last date user has confirmed habit is still not broken
        return lastCheckedIn;
    }

    public String getName() { //gets the name associated with the habit
        return name;
    }

    public void setName(String newName){ //can set a new name for the habits
        name = newName;
    }

    /**logic for habits**/
    public void checkIn(){ //checks in that habit is still being followed or avoided
        LocalDate today = LocalDate.now();
        if(!lastCheckedIn.equals(today)){
            lastCheckedIn = today;
            System.out.println("confirmed progress for " +name+ "!");
        }else {
            System.out.println("already checked in today for" +name+ "!");
        }
    }

    public void reset(){
        startDate = LocalDate.now();
        lastCheckedIn = LocalDate.now();
        System.out.println("streak reset");
    }

    @Override
    public String toString() {
        return name+ "- Streak: " + getStreak();
    }
}
