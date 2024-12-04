package task;

import java.util.*;

public class Group {
    private final Integer ID;
    private final List<List<String>> rowsList;
    private final Map<Integer, HashSet<String>> entriesSets;

    public Group(Integer id) {
        this.ID = id;
        this.rowsList = new ArrayList<>();
        this.entriesSets = new HashMap<>();
    }

    public Integer getID() {
        return ID;
    }

    public List<List<String>> getRowsList() {
        return rowsList;
    }

    public Map<Integer, HashSet<String>> getEntriesSets() {
        return entriesSets;
    }

    public List<String> getRow(Integer index) {
        return rowsList.get(index);
    }

    public HashSet<String> getColumnSet(Integer index) {
        return entriesSets.get(index);
    }

    public void addRow(List<String> row) {
        for (int i = 0; i < row.size(); i++) {
            if (!entriesSets.containsKey(i)) {
                entriesSets.put(i, new HashSet<>());
            }
            entriesSets.get(i).add(row.get(i));
        }
        rowsList.add(row);
    }

    public boolean rowMatches(List<String> row) {
        Integer smallNumber = row.size();
        if (smallNumber > entriesSets.size()) {
            smallNumber = entriesSets.size();
        }
        for (int i = 0; i < smallNumber; i++) {
            if (!row.get(i).isEmpty()) {
                if (entriesSets.get(i).contains(row.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer size() {
        return rowsList.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Группа ").append(ID).append("\n");
        for (List<String> row : rowsList) {
            for (String entry : row) {
                result.append("\"").append(entry).append("\"").append(";");
            }
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }
}
