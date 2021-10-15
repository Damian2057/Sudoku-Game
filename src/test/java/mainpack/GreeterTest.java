package mainpack;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class GreeterTest {

    @Test
    void greet() {
        Greeter greeter = new Greeter();
        assertEquals("Hello world!",greeter.greet());
    }
}