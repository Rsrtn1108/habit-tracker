import java.io.*;
import java.time.LocalDate;
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

    /** saving and loading **/
    public void saveHabits(String filename) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for(Habit habit : habits) {
                bw.write(habit.toFileString());
                bw.newLine();
            }
            System.out.println("habits saved to "+filename);
        }catch(IOException e){
            System.out.println("Error saving habits: "+e.getMessage());
        }
    }

    public void loadHabits(String filename) {
        habits.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int i = 0;
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                if(parts.length == 3){
                    String habitName = parts[0];
                    LocalDate startDate = LocalDate.parse(parts[1]);
                    LocalDate lastCheckedIn = LocalDate.parse(parts[2]);
                    habits.add(new Habit(habitName));
                    habits.get(i).setStartDate(startDate);
                    habits.get(i).setLastCheckedIn(lastCheckedIn);
                    i++;
                }
            }
            System.out.println("habits loaded from "+filename);
        }catch (FileNotFoundException e){
            System.out.println("no save file found, starting fresh.");
        }catch(IOException e){
            System.out.println("error loading habits: "+e.getMessage());
        }
    }
}
