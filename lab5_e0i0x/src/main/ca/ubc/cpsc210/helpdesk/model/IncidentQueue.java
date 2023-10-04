package ca.ubc.cpsc210.helpdesk.model;

import java.util.ArrayList;

// Represents a queue of incidents to be handled by helpdesk
// with maximum size MAX_SIZE
public class IncidentQueue {
    public static final int MAX_SIZE = 10;

    private ArrayList<Incident> queue;


    //EFFECTS: constructs a empty queue
    public IncidentQueue() {
        queue = new ArrayList<>();
    }

    // REQUIRES: the queue does not already contain an incident
    //           with the same case number as the incident we are
    //           trying to add
    // MODIFIES: this
    // EFFECTS: if the queue is not full
    //          - add incident to the end of the queue
    //          - returns ture
    //          otherwise, returns false
    public boolean addIncident(Incident incident) {
        if (!isFull()) {
            queue.add(incident);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: the queue is not empty
    // MODIFIES: this
    // EFFECTS: removes the incident at the front of the queue and returns it
    public Incident getNextIncident() {
        Incident next = queue.get(0);
        queue.remove(0);
        return next;
    }

    //EFFECTS: if an incident in queue have case number same as caseNum
    //          - returns the position of the incident
    //          otherwise returns -1
    public int getPositionInQueueOfCaseNumber(int caseNum) {
        int position = 1;
        for (Incident i : queue) {
            if (i.getCaseNum() == caseNum) {
                return position;
            }
            position++;
        }
        return -1;
    }

    //EFFECTS: returns the number of incidents currently in the queue
    public int length() {
        return queue.size();
    }

    // EFFECTS: if the queue is empty:
    //          - returns true
    //          otherwise returns false
    public boolean isEmpty() {
        return (queue.size() == 0);
    }

    // EFFECTS: if the queue is full:
    //          - returns true
    //          otherwise return false
    public boolean isFull() {
        return (queue.size() == MAX_SIZE);
    }


}
