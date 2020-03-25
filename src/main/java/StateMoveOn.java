public class StateMoveOn implements State {

    @Override
    public void next(Controller controller) {

        Integer nextStop = controller.getNextStop();
        controller.sendLiftTo(nextStop);

        controller.setState(new StateArrived());

    }

    public String toString() {
        return printInfo();
    }
}
