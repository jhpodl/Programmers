import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] elements = {7,9,1,1,4};
        System.out.println(sol.solution(elements));
    }

}

class Solution {
    public int solution(int[] elements) {
        Set<Integer> resultSet = new HashSet<>();
        for (int r = 1; r <= elements.length; r++) {
            combineConsecutive(elements, resultSet, new ArrayList<>(), 0, r);
        }
        return resultSet.size();
    }

    static void combineConsecutive(int[] elements, Set<Integer> resultSet, List<Integer> tempList, int start, int r) {
        if (tempList.size() == r) {
            int sum = tempList.stream().mapToInt(Integer::intValue).sum();
            resultSet.add(sum);
            return;
        }
        for (int i = start; i <= elements.length - r + tempList.size(); i++) {
            tempList.add(elements[i]);
            if(r>1 && tempList.size() == r) { // if tempList size reaches 'r', do not go deeper
                combineConsecutive(elements, resultSet, tempList, i + 1, r);
                tempList.remove(tempList.size() - 1);
                break;
            }
            combineConsecutive(elements, resultSet, tempList, i + 1, r); // Here we only move to the next element 'i + 1'
            tempList.remove(tempList.size() - 1);
        }
    }
}