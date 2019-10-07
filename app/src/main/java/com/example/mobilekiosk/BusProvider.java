package com.example.mobilekiosk;

public final class BusProvider {

    public interface OntimeListener{

        void ontimePickerset(String str, int i);

    }
    /*
    private static final Bus BUS = new Bus(ThreadEnforcer.MAIN);

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
    */
}
