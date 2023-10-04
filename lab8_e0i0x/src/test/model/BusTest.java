package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    private Bus testBus1;
    private Bus testBus2;
    private Chaperone testChaperone;
    private Student testStudent1;
    private Student testStudent2;
    private Student testStudent3;
    private Student testStudent4;

    @BeforeEach
    void setUp() {
        testBus1 = new Bus(1,3);
        testBus2 = new Bus(2,4);
        testChaperone = new Chaperone("testChaperone");
        testStudent1 = new Student(1, "test1", 1);
        testStudent2 = new Student(2, "test2", 2);
        testStudent3 = new Student(3, "test3", 3);
        testStudent4 = new Student(4, "test4", 4);
    }

    @Test
    void testConstructor() {
        assertEquals(1, testBus1.getId());
        assertEquals(3, testBus1.getCapacity());
        assertFalse(testBus1.hasChaperone());
        assertEquals(0, testBus1.getStudents().size());
    }

    @Test
    void testSetChaperone() {
        testBus1.setChaperone(this.testChaperone);

        assertTrue(testBus1.hasChaperone());
        assertEquals(this.testChaperone, testBus1.getChaperone());
    }

    @Test
    void testAddOneStudent() {
        testBus1.addStudent(testStudent1);

        Set<Student> students = testBus1.getStudents();

        assertFalse(testBus1.isFull());
        assertEquals(1, students.size());
        assertTrue(students.contains(testStudent1));
        assertEquals(testBus1, testStudent1.getAssignedBus());
    }

    @Test
    void testAddCapacityStudents() {
        testBus1.addStudent(testStudent1);
        testBus1.addStudent(testStudent2);
        testBus1.addStudent(testStudent3);

        Set<Student> students = testBus1.getStudents();

        assertTrue(testBus1.isFull());
        assertEquals(3, students.size());
        assertTrue(students.contains(testStudent1));
        assertTrue(students.contains(testStudent2));
        assertTrue(students.contains(testStudent3));
        assertEquals(testBus1, testStudent1.getAssignedBus());
        assertEquals(testBus1, testStudent2.getAssignedBus());
        assertEquals(testBus1, testStudent3.getAssignedBus());
    }

    @Test
    void testAddStudentDuplicate() {
        testBus1.addStudent(testStudent1);
        testBus1.addStudent(testStudent2);
        testBus1.addStudent(testStudent3);
        assertTrue(testStudent1.isAssignedToBus());

        testBus2.addStudent(testStudent1);

        assertEquals(testBus2, testStudent1.getAssignedBus());
        Set<Student> students1 = testBus1.getStudents();
        Set<Student> students2 = testBus2.getStudents();
        assertEquals(2, students1.size());
        assertEquals(1, students2.size());
        assertFalse(students1.contains(testStudent1));
        assertTrue(students2.contains(testStudent1));
    }

    @Test
    void testRemoveOneStudent() {
        testBus1.addStudent(testStudent1);
        testBus1.addStudent(testStudent2);
        testBus1.addStudent(testStudent3);

        testBus1.removeStudent(testStudent1);

        Set<Student> students1 = testBus1.getStudents();
        assertEquals(2, students1.size());
        assertFalse(students1.contains(testStudent1));
        assertTrue(students1.contains(testStudent2));
        assertTrue(students1.contains(testStudent3));
    }

    @Test
    void testRemoveMultipleStudent() {
        testBus1.addStudent(testStudent1);
        testBus1.addStudent(testStudent2);
        testBus1.addStudent(testStudent3);

        testBus1.removeStudent(testStudent1);
        testBus1.removeStudent(testStudent2);
        testBus1.removeStudent(testStudent3);

        Set<Student> students1 = testBus1.getStudents();
        assertEquals(0, students1.size());
        assertFalse(students1.contains(testStudent1));
        assertFalse(students1.contains(testStudent2));
        assertFalse(students1.contains(testStudent3));
    }

    @Test
    void testRemoveAndAddStudent() {
        testBus1.addStudent(testStudent1);
        testBus1.addStudent(testStudent2);
        testBus1.addStudent(testStudent3);

        testBus1.removeStudent(testStudent1);

        testBus1.addStudent(testStudent1);


        Set<Student> students1 = testBus1.getStudents();
        assertEquals(3, students1.size());
        assertTrue(testBus1.isFull());
        assertTrue(students1.contains(testStudent1));
        assertTrue(students1.contains(testStudent2));
        assertTrue(students1.contains(testStudent3));
    }

}