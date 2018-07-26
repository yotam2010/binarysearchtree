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
    //END CONSTRUCTOR

    public void insert(int id, String name) {
        TreeNode newStudent = new TreeNode(id, name), p = null, temp = root;
        boolean found = false;
        while (temp != null && !found) {
            p = temp;
            if (id < temp.getId()) {
                if (isLeftWire(temp))
                    found = true;
                temp = temp.getLeftChild();
            } else {
                if (isRightWire(temp))
                    found = true;
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

        updateMid(newStudent, Constants.INSERT);

    }
    //END INSERT

    public TreeNode getMid() {
        return mid;
    }
    //END GET MID

    private void updateMid(TreeNode node, String command) {

        if (command.equals(Constants.INSERT)) {

            if (mid == null)
                mid = node;
            else {
                if (mid.getId() < node.getId()) {
                    if (nodeCounter == 1) {
                        mid = successor(mid);
                        nodeCounter--;
                    } else
                        nodeCounter++;
                } else {
                    if (nodeCounter == 0) {
                        mid = predecessor(mid);
                        nodeCounter++;
                    } else
                        nodeCounter--;
                }

            }

        } else if (command.equals(Constants.DELETE)) {
            if (mid == node)
                if (nodeCounter == 1) {
                    mid = successor(mid);
                    nodeCounter--;
                } else {
                    mid = predecessor(mid);
                    nodeCounter++;
                }
            else {
                if (mid.getId() < node.getId()) {
                    if (nodeCounter == 1)
                        nodeCounter--;
                    else {
                        mid = predecessor(mid);
                        nodeCounter++;
                    }
                } else {
                    if (nodeCounter == 1) {
                        nodeCounter--;
                        mid = successor(mid);
                    } else
                        nodeCounter++;
                }
            }
        }
    }
    //END UPDATE MID

    public TreeNode delete(TreeNode node) {
        if (node == null)
            return null;

        updateMid(node, Constants.DELETE);

        TreeNode replacement = null;
        TreeNode temp = null;
        if (isLeftWire(node) || isRightWire(node))
            replacement = node;
        else
            replacement = successor(node);
        if (isLeaf(replacement))
            temp = null;
        else if (!isLeftWire(replacement))
            temp = replacement.getLeftChild();
        else
            temp = replacement.getRightChild();
        if (temp != null)
            temp.setParent(replacement.getParent());
        if (replacement.getParent() == null)
            root = temp;
        else if (replacement == replacement.getParent().getLeftChild())
            replacement.getParent().setLeftChild(temp);
        else
            replacement.getParent().setRightChild(temp);

        if (replacement != node) {
            node.setId(replacement.getId());
            node.setName(replacement.getName());
        }

        return replacement;

    }
    //END DELETE

    public TreeNode search(int id) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.getId() == id)
                return temp;
            if (temp.getId() < id) {
                if (isRightWire(temp))
                    return null;
                else
                    temp = temp.getRightChild();
            } else {
                if (isLeftWire(temp))
                    return null;
                else
                    temp = temp.getLeftChild();
            }
        }
        return temp;
    }
    //END SEARCH

    public TreeNode successor(TreeNode node) {
        if (node == null)
            return null;
        if (!isRightWire(node))
            return minimum(node.getRightChild());
        TreeNode nodeSuccessor = node.getParent();

        while (nodeSuccessor != null && node == nodeSuccessor.getRightChild()) {
            node = nodeSuccessor;
            nodeSuccessor = nodeSuccessor.getParent();
        }

        return nodeSuccessor;
    }
    //END SUCCESSOR

    public TreeNode predecessor(TreeNode node) {
        if (node == null)
            return null;
        if (!isLeftWire(node))
            return maximum(node.getLeftChild());
        TreeNode nodePredecessor = node.getParent();

        while (nodePredecessor != null && node == nodePredecessor.getLeftChild()) {
            node = nodePredecessor;
            nodePredecessor = nodePredecessor.getParent();
        }
        return nodePredecessor;
    }
    //END PREDECESSOR

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
    //END INORDER

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
    //END PREORDER

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
    //END POSTORDER

    public TreeNode minimum(TreeNode node) {
        TreeNode min = node;
        while (!isLeftWire(min))
            min = min.getLeftChild();
        return min;
    }
    //END NODE MIN

    public TreeNode maximum(TreeNode node) {
        TreeNode max = node;
        while (!isRightWire(max))
            max = max.getRightChild();
        return max;
    }
    //END NODE MAX

    public TreeNode minimum() {
        return minimum(root);
    }
    //END TREE MIN

    public TreeNode maximum() {
        return maximum(root);
    }
    //END TREE MAX

    private boolean isLeaf(TreeNode node) {
        return isLeftWire(node) && isRightWire(node);
    }
    //END LEAF

    private boolean isLeftWire(TreeNode node) {
        if (node.getLeftChild() == null || node.getLeftChild().getParent() != node)
            return true;
        return false;
    }
    //END LEFT WIRE

    private boolean isRightWire(TreeNode node) {
        if (node.getRightChild() == null || node.getRightChild().getParent() != node)
            return true;
        return false;
    }
    //END RIGHT WIRE


}
