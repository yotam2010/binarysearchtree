package com.company;

public class Action {

    enum COMMAND {ADD,DELETE,};
    String text;
    public Action(String text) {
        this.text=text;

    }

    @Override
    public String toString() {
        return "Action{" + text +
                '}';
    }
}
