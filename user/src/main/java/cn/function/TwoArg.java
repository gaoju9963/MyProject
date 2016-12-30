package cn.function;

/**
 * Created by pengshu on 2016/11/5.
 */
public class TwoArg<T, V> {
    private T _first;
    private V v;

    public TwoArg() {
    }

    public TwoArg(T t, V v) {
        this._first = t;
        this.v = v;
    }

    public T get_first() {
        return _first;
    }

    public void set_first(T _first) {
        this._first = _first;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}
