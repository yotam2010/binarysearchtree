package com.company;

public class TreeNode {

    private int id;
    private String name;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private TreeNode parent;

    public TreeNode(int id, String name) {
//        if(!nameValidation(name) || !idValidation(id))
//            throw new IllegalArgumentException("Illegal argument. check the student ID / name.");
        this.id = id;
        this.name = name;
        this.leftChild=null;
        this.parent=null;
        this.rightChild=null;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    private boolean idValidation(String id) {
        //we could add more tests here to validate the id.
    if(id.length()!=9 || !id.matches("[0-9]+"))
         return false;
    return true;
    }

    private boolean nameValidation(String name) {
        //we could add more tests here to validate the id.
        if(name.length()<2 || !name.matches("[a-zA-Z]+"))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student: "+getName()+" - "+getId();
    }
}
