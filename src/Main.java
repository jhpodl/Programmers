import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String testCase1=Arrays.toString(sol.solution(3,2,5));
        String testCase2=Arrays.toString(sol.solution(4,7,14));
        System.out.println(testCase1);
        System.out.println(testCase2);
    }

}
class Solution {
    public int[] solution(int n, long left, long right) {
        List<Integer> linear = new ArrayList<Integer>();

        int diff = (int)Math.abs(left-right);

        for(int i=0; i<=diff ; i++){
            int next = (int) left +i;
            int row = next/n;
            int col = next%n;

            if(col <=row)
                linear.add(row+1);
            else
                linear.add((row+1)+(col-row));
        }
        int[] answer = linear.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
//참고용
//List<Integer> linear = Arrays.asList(1, 2, 3, 4, 5);
//List<Integer> linear2 = new ArrayList<>(circular);  // 선형 수열을 생성하고 원형 수열의 원소를 복사
//linear2.addAll(circular);