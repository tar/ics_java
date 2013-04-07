package ru.spbstu.ics.java.collections;

public class OwnCollection <T> {
    
    T t;
    
    public boolean isInt(){
        return (t instanceof Integer);
    }
}
