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
        for(int i=1; i<=n ; i++){
            for(int j=1; j<=i ; j++){
                linear.add(i);
            }
            for(int j=i+1; j<=n ; j++){
                linear.add(j);
            }
        }
        Integer[] subInteger = linear.subList((int)left,(int)right+1).toArray(new Integer[0]);
        int[] answer = Arrays.stream(subInteger).mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
//참고용
//List<Integer> linear = Arrays.asList(1, 2, 3, 4, 5);
//List<Integer> linear2 = new ArrayList<>(circular);  // 선형 수열을 생성하고 원형 수열의 원소를 복사
//linear2.addAll(circular);