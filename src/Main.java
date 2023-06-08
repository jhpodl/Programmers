import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7,9,1,1,4);
        Set<Integer> resultSet = new HashSet<>();
        for (int r = 1; r <= list.size(); r++) {
            combine(list, resultSet, new ArrayList<>(), 0, r);
        }
        System.out.println(resultSet);  // Resulting set of sums
    }

    static void combine(List<Integer> list, Set<Integer> resultSet, List<Integer> tempList, int start, int r) {
        if (tempList.size() == r) {
            int sum = tempList.stream().mapToInt(Integer::intValue).sum();
            resultSet.add(sum);
            return;
        }
        for (int i = start; i < list.size(); i++) {
            tempList.add(list.get(i));
            if(i+1 < list.size()) tempList.add(list.get(i+1));  // add next element
            else  tempList.add(list.get(0));
            combine(list, resultSet, tempList, i + r, r);  // Skip the next one as it's already considered
            tempList.remove(tempList.size() - 1);  // Backtrack
            if(i+1 < list.size()) tempList.remove(tempList.size() - 1);  // Backtrack
        }
    }
}

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer,Integer> mandarinNum = new HashMap<Integer,Integer>();
        putHashMap(tangerine, mandarinNum);
        Map<Integer, Integer> sorted = getSortedHashMap(mandarinNum);
        int kCount=0;
        answer = getSliceAndGetCateNum(k, answer, sorted, kCount);
        return answer;
    }

    private static int getSliceAndGetCateNum(int k, int answer, Map<Integer, Integer> sorted, int kCount) {
        for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
            answer++;
            for(int i = 0; i < entry.getValue(); i++){
                kCount++;
                if(k == kCount) break;
            }
            if(k == kCount) break;
        }
        return answer;
    }

    private static Map<Integer, Integer> getSortedHashMap(Map<Integer, Integer> mandarinNum) {
        /**
         * 1.mandarinNum.entrySet().stream(): Map에는 entrySet() 메서드가 있어서 Map의 각 요소를 Entry 타입의 Set으로 변환합니다.
         * 여기서 Entry는 Map에 저장된 키-값 쌍을 나타내는 타입입니다. 이 Set을 stream() 메서드로 스트림으로 변환합니다.
         *
         * 2..sorted(Comparator.comparing(Map.Entry<Integer, Integer>::getValue).reversed()):
         * 이 부분은 스트림의 요소를 특정 조건에 따라 정렬하는 역할을 합니다. 여기서는 Map.Entry의 getValue() 메서드를 호출하여 값을 기준으로 정렬합니다.
         * 이후에 reversed() 메서드를 호출하여 역순으로 정렬하도록 지시합니다.
         *
         * 3..collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1, e2) -> e2,LinkedHashMap::new)):
         * 마지막으로, collect 메서드를 사용하여 스트림의 결과를 새로운 Map으로 수집합니다.
         * 이때, 스트림의 각 요소는 Entry이므로 getKey, getValue 메서드를 사용하여 새 Map의 키와 값으로 변환합니다. 또한,
         * 같은 키에 대해 두 값이 존재할 경우 (e1, e2) -> e2 람다식에 의해 두 번째 값이 선택됩니다. 마지막으로, LinkedHashMap::new를 사용하여 수집된 요소를 LinkedHashMap에 저장합니다.
         * LinkedHashMap은 요소가 추가된 순서를 유지하는 Map입니다. 이로써 sorted는 값에 따라 정렬된 Map이 됩니다.
         *
         * Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
         *                          Function<? super T, ? extends U> valueMapper,
         *                          BinaryOperator<U> mergeFunction,
         *                          Supplier<M> mapSupplier)
         *
         */
        Map<Integer, Integer> sorted = mandarinNum.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<Integer, Integer>::getValue).reversed())
                .collect(
                        Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1, e2) -> e2,LinkedHashMap::new)
                );
        return sorted;
    }

    private static void putHashMap(int[] tangerine, Map<Integer, Integer> mandarinNum) {
        for(int sizeNo : tangerine){
            if(mandarinNum.containsKey(sizeNo)){
                mandarinNum.put(sizeNo, mandarinNum.get(sizeNo)+1);
            }
            else{
                mandarinNum.put(sizeNo,1);
            }
        }
    }
}