// 클라츠 추측
// 형변환 주의하기 - 직접 어떤 값이 나오는지 찍어보았습니다.

class Sol {
    public int sol(int num) {
        long number = num; // int의 범위를 넘어서기 때문에 overflow가 발생함
        int count=0; // 작업 반복회수&결과값;

        while (number != 1) {// 주어진 수가 1이 될때까지
            if (number%2 == 0) { // 짝수 case
                number = number/2;
            } else { // 홀수 case
                number = number*3+1;
            }
            count++;
            System.out.println("count: "+count+"  "+"intNum: "+String.format("%,d",number)); // 결과값 로그 보기

            if (count>500) {
                count = -1;
                return count;
            }
        }
        return count;
    }
}

public class Test {
    public static void main(String[] args) { // check@line 104
        System.out.println(new Sol().sol(626331)); // 예제 입력값으로 제시된 값을 활용함

    }
}
