package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student testStudent1;
    private Bus testBus1;
    private Bus testBus2;

    @BeforeEach
    void setUp() {
        testBus1 = new Bus(1, 2);
        testBus2 = new Bus(1, 3);
        testStudent1 = new Student(1, "test1", 1);
        Student testStudent2 = new Student(2, "test2", 2);
        Student testStudent3 = new Student(3, "test3", 3);
    }

    @Test
    void testConstructor() {
        assertEquals(1, testStudent1.getId());
        assertEquals("test1", testStudent1.getName());
        assertEquals(1, testStudent1.getGrade());
        assertFalse(testStudent1.isAssignedToBus());
    }

    @Test
    void testAssignToBus() {
        testStudent1.assignToBus(testBus1);

        assertTrue(testStudent1.isAssignedToBus());
        assertEquals(testBus1, testStudent1.getAssignedBus());
        assertTrue(testBus1.getStudents().contains(testStudent1));
    }

    @Test
    void testAssignedToBus() {
        testStudent1.assignToBus(testBus1);
        testStudent1.assignToBus(testBus2);

        Set<Student> students1 = testBus1.getStudents();
        Set<Student> students2 = testBus2.getStudents();
        assertEquals(0, students1.size());
        assertEquals(1, students2.size());
        assertTrue(testStudent1.isAssignedToBus());
        assertEquals(testBus2, testStudent1.getAssignedBus());
        assertFalse(students1.contains(testStudent1));
        assertTrue(students2.contains(testStudent1));
    }

    @Test
    void testRemoveFromBus() {
        testStudent1.assignToBus(testBus1);
        testStudent1.removeFromBus();

        assertNull(testStudent1.getAssignedBus());
        assertFalse(testBus1.getStudents().contains(testStudent1));
    }

    @Test
    void testRemoveNotAssignedFromBus() {
        testStudent1.removeFromBus();

        assertNull(testStudent1.getAssignedBus());
        assertFalse(testBus1.getStudents().contains(testStudent1));
    }
}