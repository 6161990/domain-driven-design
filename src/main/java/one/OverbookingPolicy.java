package one;

/** 초과 예약 정책 클래스 */
public class OverbookingPolicy {
    public boolean isAllowed(Cargo cargo, Voyage voyage) {
        return (cargo.getSize() + voyage.bookedCargoSize()) <= (voyage.getCapacity() * 1.1);
    }
}
