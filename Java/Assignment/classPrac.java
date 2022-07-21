// 부모 클래스 구성
package pkg;
import java.util.UUID;

public class PublicTransportation { // 대중교통 클래스
    public String numberOfCar; // 차 번호
    public int amountOfFuel = 100; // 주유량
    public int currentSpeed = 0; // 현재 속도
    public int changeOfSpeed; // 속도 변경 ?? 메서드 아닌가?
    public int maxPassenger; // 최대 승객수
    public int presentPassenger=0; //현재 승객수
    public int calculatedPassenger; // 최대승객수의 변화를 나타내는 변수
    public String serviceCondition; // 운행상태( {bus: 운행중,차고지 행}, {taxi:운행중, 일반)
    public int price; // 운행 요금
    public int profit=0; // 운행 수익
    public boolean stateOfWhile = true; // whileController
    

    public void changeAmountOfFuel(int add) { // 속도 변경

    }

    public void changeServiceCondition(String condition) { // (운행) 상태 변경, 공통, 비워두고 오버라이딩
            serviceCondition = condition;
    }

    public void takePassenger(int add) { // 승객 탑승, 공통
        maxPassenger -= add;
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
    String busNumber = super.numberOfCar; // 버스 번호
    Bus () {
        this.maxPassenger = 30;
        this.numberOfCar = getUUID();
        this.price = 1000;
        this.serviceCondition = "운행중";
        this.calculatedPassenger = 30; // 최대승객수의 변화를 나타내는 변수
    }

    @Override
    public void changeServiceCondition(String condition) { // 버스 상태 변경
        if (amountOfFuel < 10) {
            System.out.println("주유가 필요합니다.");
            serviceCondition = "차고지행";
            System.out.println("운행을 종료하고 차고지로 향합니다.");
        } else if (condition.equals("차고지행")) {
            serviceCondition = "차고지행";
            System.out.println("운행을 종료하고 차고지로 향합니다.");
        } else if (condition.equals("운행중")){
            serviceCondition = condition;
            System.out.println("운행중 입니다.");
        } else {
            System.out.println("잘못된 값을 입력하였습니다. 운행상태가 변경되지 않습니다.");
        }
    }

    @Override
    public void takePassenger(int add) {
        if (maxPassenger < presentPassenger + add) {
            System.out.println("최대 탑승 승객수를 초과하였습니다. 운행이 불가능합니다.");
            System.out.println("현재 추가 탑승가능 인원은 " + String.format("%d", maxPassenger - presentPassenger) + "명 입니다.");
            this.stateOfWhile = true;
        } else {
            presentPassenger += add;
            calculatedPassenger -= add;
            profit += (price * add);
            System.out.println("승객이 탑승하였습니다.");
            System.out.println("탑승한 승객수: " + String.format("%d", add));
            System.out.println("추가 탑승 가능 승객수: " + String.format("%d", calculatedPassenger));
            System.out.println("지불하실 금액은 " + String.format("%d", price * add) + "원 입니다.");
            System.out.println("현재 운행 수익은 " + String.format("%d", profit) + "원 입니다.");
            stateOfWhile = false;
        }
    }

    @Override
    public void changeAmountOfFuel(int add) {
        amountOfFuel += add;
        if (amountOfFuel < 10) {
            System.out.println("주유가 필요합니다.");
            serviceCondition = "차고지행";
            System.out.println("운행을 종료하고 차고지로 향합니다.");
        }
        System.out.println("현재 주유량은 " + String.format("%d",amountOfFuel)+ "입니다.");
        System.out.println("현재 차량 운영상태는 " + String.format("%s",serviceCondition)+" 입니다.");
    }
    
    public void changeSpeed() { // 속도 변경
        
    }
}

class Taxi extends PublicTransportation {
    String destination; // 목적지
    int distanceToDestination; // 목적지까지 거리 == 총 운행거리
    int distance=1; // 기본거리
    int basicPrice; // 기본요금
    int totalPrice; // 총 지불요금
    int pricePerDistance=1000; // 거리당 요금

    Taxi () {
        this.maxPassenger = 4;
        this.numberOfCar = getUUID(); // 별도 요구조건 없음
        this.price = 3000; // 기본요금
        this.serviceCondition = "일반";
        this.calculatedPassenger = 4;
    }

    @Override
    public void changeAmountOfFuel(int add) {
        amountOfFuel += add;
        if (amountOfFuel < 10) {
            System.out.println("주유가 필요합니다.");
            serviceCondition = "차고지행";
            System.out.println("운행을 종료하고 차고지로 향합니다.");
        }
        System.out.println("현재 주유량은 " + String.format("%d",amountOfFuel)+ "입니다.");
        System.out.println("현재 차량 운영상태는 " + String.format("%s",serviceCondition)+" 입니다.");
    }

    public void payment() { // 요금 결제
        presentPassenger=0; // 승객이 내리므로, 현재 승객수 초기화
        calculatedPassenger = maxPassenger; // 승객이 내리므로, 최대 탑승가능인원 초기화
        System.out.println("결제하실 총 요금은 " + String.format("%d", totalPrice) + "원 입니다.");
        System.out.println("현재 운행 수익은 " + String.format("%d", profit) + "원 입니다.");
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("주유상태는 아해와 같습니다.");
        System.out.println("현재 주유량은 " + String.format("%d",amountOfFuel)+"리터입니다.");
        if (amountOfFuel < 10) {
            System.out.println("주유가 필요합니다.");
        }
    }

    public void takePassenger(int add, String destination, int dist) {
        if (maxPassenger < presentPassenger + add) {
            System.out.println("최대 탑승 승객수를 초과하였습니다. 운행이 불가능합니다.");
            System.out.println("현재 추가 탑승가능 인원은 " + String.format("%d", maxPassenger - presentPassenger) + "명 입니다.");
            stateOfWhile = true;
        } else {
            presentPassenger += add;
            calculatedPassenger -= add; // 현재 추가 탑승가능 인원
            distanceToDestination = dist;
            totalPrice = price + (distanceToDestination-distance)*pricePerDistance; // 총 지불요금
            profit += totalPrice;
            serviceCondition="운행중";
            System.out.println("승객이 탑승하였습니다.");
            System.out.println("탑승한 승객수: " + String.format("%d", add));
            System.out.println("추가 탑승 가능 승객수: " + String.format("%d", calculatedPassenger));
            System.out.println("택시운행 기본요금은 " + String.format("%d", price) + "원 입니다.");
            System.out.println("목적지는 " + String.format("%s", destination) + "입니다.");
            System.out.println("목적지까지 거리는 " + String.format("%d",distanceToDestination)+"km 입니다.");
            System.out.println("목적지까지 예상되는 총 요금은 " + String.format("%d", totalPrice) + "원 입니다.");
            System.out.println("현재 운행 수익은 " + String.format("%d", profit) + "원 입니다.");
            System.out.println("현재 운행 상태는 " + String.format("%s",serviceCondition)+"입니다.");
            stateOfWhile = false;
        }
    }


}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isOn = true;

        while (isOn) {
            System.out.println("운행할 교통수단을 입력해 주세요(bus/taxi): ");
            String choice = sc.next();
            if (choice.equals("bus")) {
                Bus bus1 = new Bus();
                Bus bus2 = new Bus();
                System.out.println("버스운행을 시작합니다.");

                System.out.println("첫 번째 운행 차량 bus1의 차량번호는 " + String.format(bus1.numberOfCar)+" 입니다.");
                System.out.println("두 번째 운행 차량 bus2의 차량번호는 " + String.format(bus2.numberOfCar)+" 입니다.");
                System.out.println();


                System.out.println("승객을 태우기 위해 정류장에 정차합니다.");
                System.out.println("몇 명이 탑승합니까? 승객 수를 입력해 주세요. (2명)");
                int addPassenger = sc.nextInt();
                bus1.takePassenger(addPassenger);
                System.out.println();



                System.out.println("총 운행경로의 50%를 운행하였습니다.");
                System.out.println("위에 표시된 운행경로 % 값을 입력해 주세요.");
                System.out.println("%값 만큼 주유량이 감소합니다. (-50)");
                int fuelSubtracted = sc.nextInt();
                bus1.changeAmountOfFuel(fuelSubtracted);


                System.out.println("총 운행경로의 90%를 운행하였습니다.");
                System.out.println("버스운행 상태를 입력하세요: {차고지행}");
                String condition = sc.next();
                bus1.changeServiceCondition(condition);


                System.out.println("필요한 주유량을 정수로 입력하면 주유량이 추가됩니다. (10+ 하시오.)");
                int fuelAdded = sc.nextInt();
                bus1.changeAmountOfFuel(fuelAdded);
                System.out.println();

                System.out.println("주유량을 충전했습니다. 운행을 재개합니다. ");
                System.out.println("버스운행 상태를 입력하세요: {운행중}");
                String condition2 = sc.next();
                bus1.changeServiceCondition(condition2);


                System.out.println("승객을 태우기 위해 정류장에 정차합니다.");
                System.out.println("몇 명이 탑승합니까? 승객 수를 입력해 주세요.(+45 하시오.)");
                int addPassenger2 = sc.nextInt();
                bus1.takePassenger(addPassenger2);

                while (bus1.stateOfWhile) { // 다시 받는 부분
                    addPassenger2 = sc.nextInt();
                    bus1.takePassenger(addPassenger2);
                }
                System.out.println();


                System.out.println("총 운행경로의 55%를 운행하였습니다.");
                System.out.println("위에 표시된 운행경로 % 값을 입력해 주세요.");
                System.out.println("%값 만큼 주유량이 감소합니다.(-55)");
                int fuelSubtracted2 = sc.nextInt();
                bus1.changeAmountOfFuel(fuelSubtracted2);
                System.out.println();

                System.out.println("버스운행을 종료하고 프로그램을 종료합니다.");
                isOn = false;
            } else if (choice.equals("taxi")) {
                Taxi taxi1 = new Taxi();
                Taxi taxi2 = new Taxi();
                System.out.println("택시 운행을 시작합니다.");
                System.out.println();

                System.out.println("첫 번째 운행 차량 taxi1의 차량번호는 " + String.format(taxi1.numberOfCar)+" 입니다.");
                System.out.println("두 번째 운행 차량 taxi2의 차량번호는 " + String.format(taxi2.numberOfCar)+" 입니다.");
                System.out.println();

                System.out.println("승객을 태우기 위해 정류장에 정차합니다.");
                System.out.println("몇 명이 탑승합니까? 승객 수를 입력해 주세요. (2명)");
                int addPassenger = sc.nextInt();
                System.out.println();

                System.out.println("목적지를 입력해 주세요. (서울)");
                String destination = sc.next();
                System.out.println();

                System.out.println("목적지까지의 거리는 2km입니다. 맞으면 거리를 정수로 입력하세요. (2)");
                int distance = sc.nextInt();
                taxi1.takePassenger(addPassenger, destination,distance);
                System.out.println();

                System.out.println("잠시 후 서울역에 도착합니다.");
                System.out.println("운행 결과 주유량이 감소합니다. 괄호값을 입력하세요. (-80)");
                int fuelSubtracted = sc.nextInt();
                taxi1.changeAmountOfFuel(fuelSubtracted);
                System.out.println();

                System.out.println("서울역에 도착했습니다. 요금결제를 진행합니다.");
                taxi1.payment();
                System.out.println();

                System.out.println("서울역을 나서던 중 다음 승객을 태우기 위해 정차합니다.");
                System.out.println("몇 명이 탑승합니까? 승객 수를 입력해 주세요. (5명)");
                int addPassenger2 = sc.nextInt();
                taxi1.takePassenger(addPassenger2);

                while (taxi1.stateOfWhile) {
                    addPassenger2 = sc.nextInt();
                    taxi1.takePassenger(addPassenger2);
                }
                System.out.println();
                System.out.println("목적지를 입력해 주세요. (구로디지털단지)");
                destination = sc.next();
                System.out.println();

                System.out.println("목적지까지의 거리는 12km입니다. 맞으면 거리를 정수로 입력하세요. (12)");
                distance = sc.nextInt();
                taxi1.takePassenger(addPassenger, destination,distance);
                System.out.println();

                System.out.println("잠시 후 구로디지털단지역에 도착합니다.");
                System.out.println("운행 결과 주유량이 감소합니다. 괄호값을 입력하세요. (-20)");
                fuelSubtracted = sc.nextInt();
                taxi1.changeAmountOfFuel(fuelSubtracted);
                System.out.println();

                System.out.println("구디단역에 도착했습니다. 요금결제를 진행합니다.");
                taxi1.payment();
                System.out.println();

                System.out.println("프로그램을 종료합니다.");
            }
            else {
                System.out.println("운행할 수 있는 차량이 없습니다.");
                System.out.println("선택 가능한 옵션은 {bus, taxi} 입니다.");
            }

        }
    }
}
