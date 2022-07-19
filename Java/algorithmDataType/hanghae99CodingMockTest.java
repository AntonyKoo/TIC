// 몇 시간 했더라?
// 배열에 시간이 담김 (시작시간배열, 끝시간배열)
// 끝시간 배열 중 29 이상은 21로 대체됨
// sum = Arrays.stream(끝시간 - 시작시간).sum();


public class Main {
    public int solution(int[] arr1, int[] arr2) {
        int answer = 0;
        int[] answerArray = new int[7];
        
        for (int i=0; i< arr2.length; i++) {
          answerArray[i] = arr2[i] - arr1[i];
        }      

        for (int j=0; j<answerArray.length; j++) {
          answer += answerArray[j];
        }
      
        return answer;
    }

    public static void main(String[] args) {
        Main method = new Main();
        int[] arr1 = {9, 9, 9, 9, 7, 9, 8};
        int[] arr2 = {23, 23, 30, 28, 30, 23, 23};
      
        for (int i=0; i<arr2.length; i++) {
          if (arr2[i] >= 29) {
            arr2[i] = 21;
          }
        }  
      
      
        System.out.println(method.solution(arr1, arr2));
    }
}
