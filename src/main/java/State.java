public abstract class State {

    abstract void next(Lift lift);


    @Override
    public String toString() {
        return "State{}";
    }
}
