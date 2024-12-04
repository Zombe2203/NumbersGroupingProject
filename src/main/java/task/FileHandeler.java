package task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandeler {
    public static List<List<String>> parseFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Parsing file into lines
            List<List<String>> result = new ArrayList<>();
            String readableLine;
            while ((readableLine = reader.readLine()) != null) {
                boolean skipFlag = false;
                List<String> parsedLine = new ArrayList<>(List.of(readableLine.split(";")));
                for (int i = 0; i < parsedLine.size(); i++) {
                    if (countEntries(parsedLine.get(i), "\"") > 2) {
                        skipFlag = true;
                        break;
                    } else {
                        parsedLine.set(i, parsedLine.get(i).replace("\"", ""));
                    }
                }
                if (skipFlag) {
                    continue;
                }
                result.add(parsedLine);
            }
            return result;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }

    public static Integer countEntries(String string, String entry) {
        String replacedString = string.replaceAll(entry, "");
        return string.length() - replacedString.length();
    }

    public static void saveToFile(List<Group> groupList, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            StringBuilder result = new StringBuilder();

            Integer numberOfLargeGroups = 0;
            for (Group group : groupList) {
                if (group.size() > 1) {
                    numberOfLargeGroups++;
                }
            }
            result.append("Количество групп с более чем одним элементом: ").append(numberOfLargeGroups).append("\n\n");

            for (Group group : groupList) {
                result.append(group.toString());
            }

            writer.write(result.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }
}
