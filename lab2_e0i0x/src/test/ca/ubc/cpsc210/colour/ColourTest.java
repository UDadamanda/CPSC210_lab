package ca.ubc.cpsc210.colour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.ubc.cpsc210.colour.Colour;


class ColourTest {
    Colour C1;
    Colour C2;
    Colour C3;
    Colour C4;

    @BeforeEach
    void runBefore() {
        C1 = new Colour(200,110,150);
        C2 = new Colour(100, 100, 100);
        C3 = new Colour(255, 255, 255);
        C4 = new Colour(0,0, 0);
    }

    @Test
    void testIsGreyScale() {
        assertTrue(C4.isGreyScale());
        assertTrue(C2.isGreyScale());
        assertFalse(C1.isGreyScale());
    }

    @Test
    void testToHex() {
        assertEquals("c86e96",C1.toHex());
        assertEquals("0", C4.toHex());
        assertEquals("ffffff", C3.toHex());

    }
}