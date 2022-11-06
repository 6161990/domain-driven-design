package one;

import java.util.ArrayList;
import java.util.List;

public class Voyage {

    OrderConfirm orderConfirmationSequence = new OrderConfirm();
    List<Cargo> cargos = new ArrayList<>();

    public int makeBooking(Cargo cargo, Voyage voyage){
        int confirmation = orderConfirmationSequence.next();
        voyage.addCargo(cargo);
        return confirmation;
    }

    private void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }
}
