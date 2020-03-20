import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LiftTest {

    @Test
    public void creationTakeDropOffTest() {
        //given
        Lift.init(10);
        final Lift lift = Lift.getInstance();

        //when the lift is empty

        //then
        assertEquals(0, lift.getOccupancy());
        assertThrows(NoSuchElementException.class, () -> lift.dropOffThePassenger(2));

        //when
        lift.takeThePassenger(7);
        lift.takeThePassenger(3);
        lift.takeThePassenger(5);

        //then
        assertEquals(3, lift.getOccupancy());
        assertTrue(lift.getPassengers().contains(3));
        assertTrue(lift.getPassengers().contains(5));
        assertTrue(lift.getPassengers().contains(7));

        //when
        lift.dropOffThePassenger(3);

        //then
        assertEquals(2, lift.getOccupancy());
        assertFalse(lift.getPassengers().contains(3));

        //when lift is full
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);
        lift.takeThePassenger(1);

        //then
        assertEquals(lift.getCapacity(), lift.getOccupancy());
        assertThrows(IllegalArgumentException.class, () -> lift.takeThePassenger(8));
    }

}