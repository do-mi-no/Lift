public class StateStart extends State {

    @Override
    void next(Lift lift) {
        lift.setState(new StateGoUp());
    }
}
