import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Integer> liftPath = new ArrayList<>();

    public Route() {
    }

    public List<Integer> getLiftPath() {
        return liftPath;
    }

    public void addNewStop(int liftStop){
        liftPath.add(liftStop);
    }

    public boolean lastMoveUp(){
        int size = liftPath.size();
        if(size <2)
            return true;
        else
            return liftPath.get(size-2) < liftPath.get(size-1);
    }

    @Override
    public String toString() {
        return "Route{" +
                "liftPath=" + liftPath +
                '}';
    }
}
