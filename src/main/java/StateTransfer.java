public class StateTransfer implements State {

    @Override
    public void next(Controller controller) {

        controller.dropOffPassengers();
        controller.takeAsManyPassengersAsPossible();

        controller.setState(new StateMoveOn());

    }

    public String toString() {
        return printInfo();
    }
}
