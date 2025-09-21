import java.util.ArrayList;
import java.util.List;

public class Tracker {

    public List<Habit> habits = new ArrayList<>();

    /** habit tracker logic **/
    public void addHabit(String name) {
        habits.add(new Habit(name));
    }

    public void checkHabit(String name) {
        for (Habit habit : habits) {
            if (habit.getName().equals(name)) {
                habit.checkIn();
                System.out.println("checked in");
                return;
            }
        }
        System.out.println("Error: Habit not found " + name);
    }

    public void listHabits(){
        for (Habit habit : habits) {
            System.out.println(habit.toString());
        }
    }
}
