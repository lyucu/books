package _12Interceptorsandevents;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

public class LoadListListernerExample implements LoadEventListener {

    @Override
    public void onLoad(LoadEvent arg0, LoadType arg1) throws HibernateException {
        // TODO Auto-generated method stub

    }

}
