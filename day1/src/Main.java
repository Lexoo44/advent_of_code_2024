import java.io.File;
import java.util.*;

// First Star: Total: 1223326
public class Main {
    private static List<Integer> leftArr = new ArrayList<>();
    private static List<Integer> rightArr = new ArrayList<>();
    private static HashMap<Integer, Integer> similarityArr = new HashMap<>();
    private static int totalDistance = 0;
    private static int totalSimilarity = 0;
    public static void main(String[] args) {
        readFile();
        findSimilarity();
        totalSimilarity();
        System.out.println("Total: " + totalDistance);
        System.out.println("Total Similarity: " + totalSimilarity);
    }

     private static void readFile() {
        try {
            File file = new File("src/input.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                splitLine(data);
            }
            myReader.close();
            for (int i = 0; i < leftArr.size(); i++) {
                distance(leftArr.get(i), rightArr.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addToList(List<Integer> list, int num) {
        list.add(num);
        Collections.sort(list);
    }

    private static void splitLine(String line) {
        int firstNum = Integer.parseInt(line.substring(0, 5));
        int secondNum = Integer.parseInt(line.substring(8));
        addToList(leftArr, firstNum);
        addToList(rightArr, secondNum);
    }

    private static void distance(
            int number,
            int number1
    ) {
        totalDistance += Math.abs(number - number1);
    }

    private static void totalSimilarity() {
        for (Map.Entry<Integer, Integer> entry : similarityArr.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            totalSimilarity += key * value;
        }
    }

    private static void findSimilarity() {
        leftArr.forEach(num -> {
            int amount = Collections.frequency(rightArr, num);
            similarityArr.put(num, amount);
        });
    }
}