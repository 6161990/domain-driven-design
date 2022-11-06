package one;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Voyage {

    OrderConfirm orderConfirmationSequence = new OrderConfirm();
    List<Cargo> cargos = new ArrayList<>();
    int capacity;

    public int makeBooking(Cargo cargo, Voyage voyage){
        int confirmation = orderConfirmationSequence.next();
        voyage.addCargo(cargo);
        return confirmation;
    }

    /** 요구 사항 추가 : 10% 초과예약 허용 */
    public int makeBooking_ver2(Cargo cargo, Voyage voyage){
        double maxBooking = voyage.capacity() * 1.1;
        if((voyage.bookedCargoSize() + cargo.getSize()) > maxBooking){
            return -1;
        }
        int confirmation = orderConfirmationSequence.next();
        voyage.addCargo(cargo, confirmation);
        return confirmation;
    }

    /** 초과 예약 정책 클래스를 이용한 협력
     * 초과 예약이 별개의 정책이라는 사실을 모든 이가 분명히 알게 되면서,
     * 이 규칙의 구현 또한 명시적으로 드러나고 다른 구현과 분리된다.
     *
     * 설계를 이러한 수준까지 끌어올리려면 프로그래머와 그 밖의 관련된 모든 이가 초과예약의 특성을 단순히 불분명한
     * 계산에 불과한 것이 아니라 별개의 중요한 업무 규칙임을 알아야만 할 것이다.
     * */
    public int makeBooking_ver3(Cargo cargo, Voyage voyage){
        OverbookingPolicy overbookingPolicy = new OverbookingPolicy();
        if(!overbookingPolicy.isAllowed(cargo, voyage)) return -1;
        int confirmation = orderConfirmationSequence.next();
        voyage.addCargo(cargo, confirmation);
        return confirmation;
    }

    private void addCargo(Cargo cargo, int confirmation) {
        cargos.add(cargo);
    }

    private void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    public int bookedCargoSize() {
        return cargos.stream().mapToInt(Cargo::getSize).sum();
    }

    private int capacity() {
        return this.capacity();
    }

}
