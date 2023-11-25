package zur.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AdvList <T> {
    public static interface Iter <F> {
        public boolean ended ();
        public F next ();
    }
    public static class ListIter <F> implements Iter <F> {
        private final List <F> l;
        private int index;
        public ListIter (List <F> l) {
            this.l = List.copyOf (l);
            this.index = 0;
        }
        public boolean ended () {
            return this.index >= l.size ();
        }
        public F next () {
            return this.l.get (index++);
        }
    }
    private ArrayList <T> li;
    public AdvList () {
        this.li = new ArrayList <> ();
    }
    public AdvList <T> add (List <T> l) {
        for (T e : l) this.li.add (e);
        return this;
    }
    public <E> AdvList <T> iteration (Function <E, T> func, Iter <E> iter) {
        while (!iter.ended ()) this.li.add (func.apply (iter.next ()));
        return this;
    }
    public List <T> list () {
        return List.copyOf (this.li);
    }
    @SafeVarargs
    public static <E> AdvList <E> of (E... e) {
        return new AdvList <E> () .add (List.of (e));
    }
}
