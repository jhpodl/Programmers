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
        stepByLength(elements, resultSet);
        return resultSet.size();
    }

    private static void stepByLength(int[] elements, Set<Integer> resultSet) {
        for (int r = 1; r <= elements.length; r++) {
            combineConsecutive(elements, resultSet, new ArrayList<>(), r);
        }
    }
    static void combineConsecutive(int[] elements, Set<Integer> resultSet, List<Integer> tempList, int r) {
        for(int i=0; i<elements.length; i++){
            addUntilLength(elements, tempList, r, i);
            resultSet.add(tempList.stream().mapToInt(Integer::intValue).sum());
            tempList.clear();
        }
    }
    private static void addUntilLength(int[] elements, List<Integer> tempList, int r, int i) {
        for(int j = 0; j< r; j++){
            tempList.add(elements[(i +j)% elements.length]);
        }
    }
}