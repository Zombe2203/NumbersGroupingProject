package task;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        // Getting file
//        FileCollector.collectFile();

        // Reading file
        List<List<String>> parsedFile = FileHandeler.parseFile("inputFile.txt");
//        List<List<String>> parsedFile = FileHandeler.parseFile("testInput.txt");

        // Grouping lines
        List<Group> listOfGroups = new ArrayList<>();
        Integer groupsCounter = 0;
        listOfGroups.add(new Group(1));
        listOfGroups.getFirst().addRow(parsedFile.getFirst());

        for (int i = 1; i < parsedFile.size(); i++) {
            List<String> row = parsedFile.get(i);
            boolean found = false;
            for (Group group : listOfGroups) {
                if (group.rowMatches(row)) {
                    group.addRow(row);
                    found = true;
                    break;
                }
            }
            if (!found) {
                groupsCounter++;
                listOfGroups.add(new Group(groupsCounter + 1));
                listOfGroups.get(groupsCounter).addRow(row);
            }
        }

        // Debug printing
//        for (Group group : listOfGroups) {
//            if (group.size() > 1) {
//                System.out.println("Group #" + group.getID() + " size: " + group.size());
//            }
//        }

//        System.out.println();
//        System.out.println(listOfGroups);
//        System.out.println();

//        for (Group group: listOfGroups){
//            System.out.println(group);
//        }

        // File saving
        FileHandeler.saveToFile(listOfGroups, "output.txt");

    }


}
