// 부모 클래스 구성

package pkg;
import java.util.UUID;

public class PublicTransportation { // 대중교통 클래스
    public String numberOfCar; // 차 번호
    public int amountOfFuel = 100; // 주유량
    public int currentSpeed = 0; // 현재 속도
    public int changeOfSpeed; // 속도 변경 ?? 메서드 아닌가?
    public int maxPassenger; // 최대 승객수
    public String serviceCondition; // 운행상태( {bus: 운행중,차고지 행}, {taxi:운행중, 일반)
    public int price; // 운행 요금

    public void startDriving() { // 운행 시작, 공통

    }

    public void changeSpeed() { // 속도 변경

    }

    public void changeServiceCondition() { // (운행) 상태 변경, 공통, 비워두고 오버라이딩

    }

    public void takePassenger() { // 승객 탑승, 공통

    }

    protected String getUUID() {
        String busNumber = UUID.randomUUID().toString();
        busNumber = busNumber.replace("-","");
        return busNumber;
    }
}

// ---------------------------------------------------------------------- "-"*70

// 자식 클래스 구성 (버스 클래스, 택시 클래스)
import pkg.PublicTransportation;
import java.util.Scanner;

class Bus extends PublicTransportation {
    int presentPassenger; //현재 승객수
    String busNumber = super.numberOfCar; // 버스 번호

    Bus () {
        super.maxPassenger = 30;
        super.numberOfCar = getUUID();
        super.price = 1000;
        super.serviceCondition = "운행중";

    }

    @Override
    public void changeServiceCondition() { // 버스 상태 변경
        if (amountOfFuel < 10) {
            System.out.println("주유가 필요합니다.");
            serviceCondition = "차고지행";
            System.out.println("운행을 종료하고 차고지로 향합니다.");
        }
        // 메모 : 사용자로부터 어떤 값을 입력 받을거고 어떤 식으로 동작할 것인지 생각
        // 주유량과 속도 어떻게 조절할래?

    }
    
    public void changeSpeed() { // 속도 변경
        
    }


}

class Taxi extends PublicTransportation {
    String destination; // 목적지
    int distanceToDestination; // 목적지까지 거리
    int distance; // 기본거리
    int basicPrice; // 기본요금
    int pricePerDistance; // 거리당 요금

    Taxi () {
        super.maxPassenger = 4;
        super.numberOfCar = getUUID(); // 별도 요구조건 없음
        super.price = 3000; // 기본요금
    }

    public void addPricePerDistance() { // 거리당 요금 추가

    }

    public void payment() { // 요금 결제

    }


}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("운행할 교통수단을 입력해 주세요: ");
        String input = sc.nextLine();

        if (input == "버스") {
            Bus bus = new Bus();
            System.out.println("버스운행을 시작합니다.");
        }
        boolean isOn = true;

        while (isOn) {
//            String input =
        }


    }
}

