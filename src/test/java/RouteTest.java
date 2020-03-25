import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    @Test
    void directionTest() {
        //given
        Route route = new Route();

        //when
        route.addNewStop(0);
        //then
        assertTrue(route.lastMoveUp());

        //when
        route.addNewStop(1);
        //then
        assertTrue(route.lastMoveUp());

        //when
        route.addNewStop(0);
        //then
        assertTrue(!route.lastMoveUp());

        //when
        route.addNewStop(5);
        //then
        assertTrue(route.lastMoveUp());

        //when
        route.addNewStop(3);
        //then
        assertTrue(!route.lastMoveUp());

    }
}