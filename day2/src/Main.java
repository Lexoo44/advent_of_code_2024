import java.io.File;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/input.txt";
    private static final List<Pair<Boolean, List<Integer>>> reports = new ArrayList<>();

    public static void main(String[] args) {
        readFile();
        System.out.println(reports);
        System.out.println("Total safe reports: " + totalSafeReports());
    }

    private static void readFile() {
        try {
            File file = new File(FILE_PATH);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String levels = myReader.nextLine();
                addReport(levels);
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addReport(String levels) {
        ArrayList<Integer> lvls = new ArrayList<>();
        String[] split = levels.split(" ");
        for (String s : split) {
            lvls.add(Integer.parseInt(s));
        }
        reports.add(new Pair<>(isSafeReport(lvls), lvls));
    }

private static boolean isSafeReport(ArrayList<Integer> levels) {
    if (levels.size() < 2) {
        return true;
    }

    if (isMonotonic(levels)) {
        return true;
    }

    for (int i = 0; i < levels.size(); i++) {
        ArrayList<Integer> modifiedLevels = new ArrayList<>(levels);
        modifiedLevels.remove(i);
        if (isMonotonic(modifiedLevels)) {
            return true;
        }
    }

    return false;
}

private static boolean isMonotonic(ArrayList<Integer> levels) {
    boolean increasing = levels.get(1) > levels.get(0);

    for (int i = 1; i < levels.size(); i++) {
        int diff = levels.get(i) - levels.get(i - 1);
        if (diff != 1 && diff != 2 && diff != 3 && diff != -1 && diff != -2 && diff != -3) {
            return false;
        }
        if ((increasing && diff < 0) || (!increasing && diff > 0)) {
            return false;
        }
    }
    return true;
}

    private static int totalSafeReports() {
        int count = 0;
        for (Pair<Boolean, List<Integer>> report : reports) {
            if (Boolean.TRUE.equals(report.getKey())) {
                count++;
            }
        }
        return count;
    }

}