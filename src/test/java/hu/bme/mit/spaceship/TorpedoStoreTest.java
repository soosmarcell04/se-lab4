package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    TorpedoStore store;
    TorpedoStore store2;

    @BeforeEach
    void init() {
        store = new TorpedoStore(1, 0.0);
        store2 = new TorpedoStore(1);
    }
    

    @Test
    void init_Success() {
        assertEquals(false, store.isEmpty());
        assertEquals(false, store2.isEmpty());

        assertEquals(1, store.getTorpedoCount());
        assertEquals(1, store2.getTorpedoCount());
    }

    @Test 
    void fire_Success() {
        assertEquals(true, store.fire(1));
        assertEquals(true, store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_Exception() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            store.fire(2);
        });
        assertEquals("numberOfTorpedos", exception.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
            store2.fire(0);
        });
        assertEquals("numberOfTorpedos", exception2.getMessage());
    }

    @Test
    void fire_Failure() {
        TorpedoStore store3 = new TorpedoStore(1, 1.0);
        assertEquals(false, store3.fire(1));
        assertEquals(false, store3.isEmpty());
        assertEquals(1, store3.getTorpedoCount());
    }
}
