package sudoker;

import java.util.Observer;
import java.util.Observable;

/**
 * Created by qhyu on 2015-08-29.
 */
public abstract class Structure implements Observer {

    public abstract void update(Observable cell, Object o);
    public abstract void fill();
    
}
