// 부족한 금액 계산하기

class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        long priceFinal = 0; 
        // typecasting에 신경쓰긔
      
        for (int i=1; i <= count; i++) {
            priceFinal += price*i;
        }
        if (priceFinal > money) {
            answer = Math.abs(money - priceFinal);
        } else {
            answer = 0;
        }
        
        return answer;
    }
}
