package model;

import ui.Printer;

import java.util.ArrayList;
import java.util.List;

public class Clothing extends Wearable {
    private List<Wearable> children;

    public Clothing(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public void add(Wearable wearable) {
        children.add(wearable);
    }

    @Override
    public void display(String onTopOf) {
        Printer.print(onTopOf, name);
        for (Wearable child : children) {
            child.display(name);
        }
    }
}
