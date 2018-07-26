package com.company;

public class BinarySearchTree {

    private TreeNode root;
    private int nodeCounter;
    private TreeNode mid;

    public BinarySearchTree() {
        this.root = null;
        nodeCounter = 0;
        mid = null;
    }

    public void insert(int id, String name) {
        TreeNode newStudent = new TreeNode(id, name), p = null, temp = root;
        boolean found = false;
        while (temp != null && !found) {
            p = temp;
            if (id < temp.getId()) {
                if(isLeftWire(temp))
                    found = true;
                temp = temp.getLeftChild();
            }
            else {
                if(isRightWire(temp))
                    found=true;
                temp = temp.getRightChild();
            }
        }

        newStudent.setParent(p);
        if (p == null)
            root = newStudent;
        else {
            if ((id < p.getId())) {
                p.setLeftChild(newStudent);
            } else {
                p.setRightChild(newStudent);
            }
        }
        newStudent.setLeftChild(predecessor(newStudent));
        newStudent.setRightChild(successor(newStudent));

    }
    //END INSERT

    public TreeNode search(int id){
        TreeNode temp = root;
        while (temp!=null){
            if(temp.getId()==id)
                return temp;
            if(temp.getId()<id){
                if(isRightWire(temp))
                    return null;
                else
                    temp=temp.getRightChild();
            } else {
                if(isLeftWire(temp))
                    return null;
                else
                    temp=temp.getLeftChild();
            }
        }
        return temp;
    }

    public TreeNode successor(TreeNode node) {
        if(node==null)
            return null;
    if(!isRightWire(node))
        return minimum(node.getRightChild());
    TreeNode nodeSuccessor = node.getParent();

    while (nodeSuccessor!=null && node==nodeSuccessor.getRightChild()){
        node=nodeSuccessor;
        nodeSuccessor=nodeSuccessor.getParent();
    }

    return nodeSuccessor;
    }

    public TreeNode predecessor(TreeNode node) {
        if(node==null)
            return null;
        if(!isLeftWire(node))
            return maximum(node.getLeftChild());
        TreeNode nodePredecessor = node.getParent();

        while (nodePredecessor!=null && node==nodePredecessor.getLeftChild()){
            node=nodePredecessor;
            nodePredecessor=nodePredecessor.getParent();
        }
        return nodePredecessor;
    }

    public void inorder() {
        System.out.println();
        inorder(root);
        System.out.print("END");
        System.out.println();
    }

    private void inorder(TreeNode node) {
        if (node != null) {
            if (!isLeftWire(node))
                inorder(node.getLeftChild());
            System.out.print(node.getId() + " -> ");
            if (!isRightWire(node))
                inorder(node.getRightChild());
        }
    }

    public void preorder() {
        System.out.println();
        preorder(root);
        System.out.print("END");
        System.out.println();
    }

    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.getId() + " -> ");
            if (!isLeftWire(node))
                preorder(node.getLeftChild());
            if (!isRightWire(node))
                preorder(node.getRightChild());
        }
    }

    public void postorder() {
        System.out.println();
        postorder(root);
        System.out.print("END");
        System.out.println();
    }

    private void postorder(TreeNode node) {
        if (node != null) {
            if (!isLeftWire(node))
                postorder(node.getLeftChild());
            if (!isRightWire(node))
                postorder(node.getRightChild());
            System.out.print(node.getId() + " -> ");
        }
    }
    public TreeNode minimum(TreeNode node) {
        TreeNode min = node;
        while (!isLeftWire(min))
            min = min.getLeftChild();
        return min;
    }

    public TreeNode maximum(TreeNode node) {
        TreeNode max = node;
        while (!isRightWire(max))
            max = max.getRightChild();
        return max;
    }

    public TreeNode minimum() {
       return minimum(root);
    }

    public TreeNode maximum() {
        return maximum(root);
    }

    private boolean isLeaf(TreeNode node) {
        return isLeftWire(node) && isRightWire(node);
    }

    private boolean isLeftWire(TreeNode node) {
        if (node.getLeftChild() == null || node.getLeftChild().getParent() != node)
            return true;
        return false;
    }

    private boolean isRightWire(TreeNode node) {
        if (node.getRightChild() == null || node.getRightChild().getParent() != node)
            return true;
        return false;
    }


}
