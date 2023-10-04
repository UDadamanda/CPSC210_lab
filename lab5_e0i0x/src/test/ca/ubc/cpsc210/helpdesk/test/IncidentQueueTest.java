package ca.ubc.cpsc210.helpdesk.test;


import ca.ubc.cpsc210.helpdesk.model.Incident;
import ca.ubc.cpsc210.helpdesk.model.IncidentQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidentQueueTest {
    private IncidentQueue testIncidentQueue;
    private IncidentQueue testIncidentQueue1;
    private IncidentQueue testIncidentQueueFull;
    private Incident incident1 = new Incident(100, "aa");
    private Incident incident2 = new Incident(200, "bb");

    @BeforeEach
    void runBefore() {
        testIncidentQueue = new IncidentQueue();
        testIncidentQueue1 = new IncidentQueue();
        testIncidentQueue1.addIncident(incident1);
        testIncidentQueue1.addIncident(incident2);
        testIncidentQueueFull = new IncidentQueue();
        testIncidentQueueFull.addIncident(new Incident(1,"a"));
        testIncidentQueueFull.addIncident(new Incident(2,"b"));
        testIncidentQueueFull.addIncident(new Incident(3,"c"));
        testIncidentQueueFull.addIncident(new Incident(4,"d"));
        testIncidentQueueFull.addIncident(new Incident(5,"e"));
        testIncidentQueueFull.addIncident(new Incident(6,"f"));
        testIncidentQueueFull.addIncident(new Incident(7,"g"));
        testIncidentQueueFull.addIncident(new Incident(8,"h"));
        testIncidentQueueFull.addIncident(new Incident(9,"i"));
        testIncidentQueueFull.addIncident(new Incident(10,"j"));



    }

    @Test
    void testConstructor() {
        assertTrue(testIncidentQueue.isEmpty());
    }

    @Test
    void testAddIncident() {
        assertTrue(testIncidentQueue.addIncident(new Incident(1,"a")));
        assertEquals(testIncidentQueue.length(),1);
    }

    @Test
    void testAddIncidentFalse() {
        assertFalse(testIncidentQueueFull.addIncident(new Incident(1,"a")));
    }

    @Test
    void testGetNextIncident() {
        assertEquals(testIncidentQueue1.getNextIncident(), incident1);
        assertEquals(testIncidentQueue1.length(), 1);
        assertEquals(testIncidentQueue1.getNextIncident(), incident2);
        assertEquals(testIncidentQueue1.length(), 0);
    }
    @Test
    void testGetPositionInQueueOfCaseNumber() {
        assertEquals(testIncidentQueue1.getPositionInQueueOfCaseNumber(100),1);
        assertEquals(testIncidentQueue1.getPositionInQueueOfCaseNumber(200), 2);
    }

    @Test
    void testGetPositionInQueueOfCaseNumberFalse() {
        assertEquals(testIncidentQueue1.getPositionInQueueOfCaseNumber(11),-1);
    }

    @Test
    void testGetPositionInQueueOfCaseNumberEmpty() {
        assertEquals(testIncidentQueue.getPositionInQueueOfCaseNumber(1),-1);
    }

    @Test
    void testLength() {
        assertEquals(testIncidentQueue1.length(), 2);
        assertEquals(testIncidentQueue.length(), 0);
        assertEquals(testIncidentQueueFull.length(), 10);
    }

    @Test
    void isEmpty() {
        assertTrue(testIncidentQueue.isEmpty());
        assertFalse(testIncidentQueueFull.isEmpty());
    }

    @Test
    void isFull() {
        assertTrue(testIncidentQueueFull.isFull());
        assertFalse(testIncidentQueue.isFull());
    }



}