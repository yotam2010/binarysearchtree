package com.company;

public class BinarySearchTree {

    private TreeNode root;
    private int nodeCounter;
    private TreeNode mid;

    public BinarySearchTree() {
        this.root = null;
        nodeCounter=0;
        mid=null;
    }

    public void insert(int id,String name) {
        TreeNode newStudent = new TreeNode(id,name), p = null, temp = root;

        while(temp!=null){
            p=temp;
            if(id<temp.getId())
                temp=temp.getLeftChild();
            else
                temp=temp.getRightChild();
        }
        newStudent.setParent(p);
        if(p==null)
            root=newStudent;
        else {
            if ((id < p.getId())) {
                p.setLeftChild(newStudent);
            } else {
                p.setRightChild(newStudent);
            }
        }
    }
    //END INSERT

}
