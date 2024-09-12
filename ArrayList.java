import java.util.Objects;

public class ArrayList<E> implements List<E>{

    private E[] data;
    private int size;

    public ArrayList(E[] data) {
        this.data = data;
        size = data.length;
    }

    public ArrayList(int size) {
        this.size = size;
        data = (E[]) new Object[size];
    }

    public ArrayList() {
        data = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public void add(E val) {
        checkSize();
        size++;
        data[size-1] = val;
    }

    @Override
    public void add(int index, E val) {
        checkSize();
        if(index != size) {
            checkIndexValidity(index);
        }
        E[] data2 = (E[]) new Object[data.length];
        System.arraycopy(data, 0, data2, 0, index);
        data2[index] = val;
        System.arraycopy(data, index, data2, index + 1, (size) - (index));
        size++;
        data = data2;
    }

    @Override
    public void set(int index, E val) {
        checkIndexValidity(index);
        data[index] = val;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public E remove(int index) {
        checkIndexValidity(index);
        E[] data2 = (E[]) new Object[data.length];
        E val = data[index];
        System.arraycopy(data, 0, data2, 0, index);
        System.arraycopy(data, index + 1, data2, index + 1 - 1, size - (index + 1));
        size--;
        data = data2;
        return val;
    }

    @Override
    public E get(int index) {
        checkIndexValidity(index);
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E val) {
        for (int i = 0; i < size; i++) {
            if(Objects.equals(data[i], val)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E val) {
        for (int i = 0; i < size; i++) {
            if(Objects.equals(data[i], val)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(List<E> other) {
        if(other.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(other.get(i) != data[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < size-1; i++) {
            output.append(data[i]).append(", ");
        }
        output.append(data[size-1]).append("]");
        return output.toString();
    }

    // checks internal size of array and increases it if it's full
    private void checkSize() {
        if(data.length == size) {
            E[] data2 = (E[]) new Object[data.length * 2];
            System.arraycopy(data, 0, data2, 0, data.length);
            data = data2;
        }
    }
    private void checkIndexValidity(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
