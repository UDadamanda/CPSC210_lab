package model;

import java.util.Set;

// Represents a student with an id, name, the grade in which the student is registered and bus to which
// student is assigned to travel
public class Student {
    private final int id;
    private final String name;
    private final int grade;
    private Bus bus;

    // EFFECTS: constructs student with id, name and registered grade, and with no bus assigned
    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.bus = null;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getGrade() {
        return this.grade;
    }

    public Bus getAssignedBus() {
        return this.bus;
    }

    // EFFECTS: returns true if student is assigned to a bus, false otherwise
    public boolean isAssignedToBus() {
        return (this.bus != null);
    }

    // REQUIRES: !bus.isFull()
    // MODIFIES: this, bus
    // EFFECTS: assigns student to travel on bus
    public void assignToBus(Bus bus) {
        removeFromBus();
        this.bus = bus;
        Set<Student> students = bus.getStudents();
        if (!students.contains(this)) {
            this.bus.addStudent(this);
        }
    }

    // MODIFIES: this, Bus b = getAssignedBus()
    // EFFECTS: if student is assigned to a bus, removes student from assigned bus;
    // otherwise has no effect
    public void removeFromBus() {
        if (this.isAssignedToBus()) {
            this.bus.removeStudent(this);
            this.bus = null;
        }
    }
}
