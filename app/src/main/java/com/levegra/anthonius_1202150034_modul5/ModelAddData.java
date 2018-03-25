package com.levegra.anthonius_1202150034_modul5;

public class ModelAddData {
    String todo, description, priority;

    //Constructor
    public ModelAddData(String todo, String desc, String prior){
        this.todo=todo;
        this.description=desc;
        this.priority=prior;
    }

    //Declare Setter and Getter
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getPrior() {
        return priority;
    }

    public void setPrior(String prior) {
        this.priority = prior;
    }
}