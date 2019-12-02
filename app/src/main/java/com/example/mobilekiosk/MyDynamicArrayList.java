package com.example.mobilekiosk;

import java.util.Arrays;

public class MyDynamicArrayList {
    private int size = 0;
    private static final int DEFAULT_SIZE = 10;
    private Object[] elements = new Object[DEFAULT_SIZE];

    // 마지막 원소에 데이터 추가
    public boolean addLast(Object element) {
        checkSize();
        this.elements[size] = element;
        size++;
        return true;
    }

    // 해당 index에 데이터 추가
    public boolean add(int index, Object element) {
        checkSize();
        for(int i = size -1; i >= index; i--) {
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;

        return true;
    }

    public Object remove(int index) {
        Object removedData = elements[index];

        for(int i = index+1; i<size; i++) {
            elements[i-1] = elements[i];
        }

        size--;
        elements[size] = null;

        return removedData;
    }

    // 사이즈를 체크하여, 2배씩 늘린다.
    private void checkSize() {
        if(size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(elements);
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyDynamicArrayList other = (MyDynamicArrayList) obj;
        if (!Arrays.equals(elements, other.elements))
            return false;
        if (size != other.size)
            return false;
        return true;
    }
    public int getSize(){
        return size;
    }
    public Object Getelement(int i){
        return elements[i];
    }

    @Override
    public String toString() {
        return "CustomArrayList [size=" + size + ", elements=" + Arrays.toString(elements) + "]";
    }
}