//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LiftTest {
//
//    @Test
//    public void creationTakeDropOffTest() {
//        //given
//
//        Lift.init(10);
//        final Lift lift = Lift.getInstance();
//
//        //when the lift is empty
//
//        //then
//        assertEquals(0, lift.getOccupancy());
//        assertThrows(IllegalArgumentException.class, () -> lift.dropPersonOff(2));
//
//        //when
//        lift.addPerson(7);
//        lift.addPerson(3);
//        lift.addPerson(5);
//
//        //then
//        assertEquals(3, lift.getOccupancy());
//        assertTrue(lift.getPassengers().contains(3));
//        assertTrue(lift.getPassengers().contains(5));
//        assertTrue(lift.getPassengers().contains(7));
//
//        //when
//        lift.dropPersonOff(3);
//
//        //then
//        assertEquals(2, lift.getOccupancy());
//        assertFalse(lift.getPassengers().contains(3));
//
//        //when lift is full
//        lift.addPerson(1);
//        lift.addPerson(1);
//        lift.addPerson(1);
//        lift.addPerson(1);
//        lift.addPerson(1);
//        lift.addPerson(1);
//        lift.addPerson(1);
//        lift.addPerson(1);
//
//        //then
//        assertEquals(lift.getCapacity(), lift.getOccupancy());
//        assertThrows(IllegalArgumentException.class, () -> lift.addPerson(8));
//    }
//
//}