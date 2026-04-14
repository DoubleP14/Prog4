package hu.pte.mik.gyakzh1.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdProvider {
    // Singleton példány
    private static final IdProvider instance = new IdProvider();

    // Szálbiztos számláló, ami 1-ről indul
    private final AtomicLong id = new AtomicLong(1L);

    private IdProvider() {
    }

    public static IdProvider getInstance() {
        return instance;
    }

    public long getNewId(){
        return this.id.incrementAndGet(); // Növeli 1-gyel, és visszaadja
    }
}
