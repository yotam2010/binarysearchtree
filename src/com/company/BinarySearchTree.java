package com.company;

//Wired Binary Search Tree

public class BinarySearchTree {

    private TreeNode root;
    private int nodeCounter;
    private TreeNode mid;

    /**
     * Empty constructor - creating empty tree
     */
    public BinarySearchTree() {
        this.root = null;
        nodeCounter = 0;
        mid = null;
    }
    //END CONSTRUCTOR

    /**
     * Inserting new student (node) to the tree if not already Exists.
     * @param id the student ID
     * @param name the student Name
     */
    public void insert(int id, String name) {
        TreeNode newStudent = new TreeNode(id, name), p = null, temp = root;
        //check if student with the same ID already exists.
        if(search(id)!=null){
            System.out.println("Student with the id "+id+" already been added.");
            return;
        }
        boolean found = false;
        //going down the tree following Binary Search Tree rules(if id is smaller go to left child else go to right child) until getting to either null node or a wired child.
        while (temp != null && !found) {
            p = temp;
            if (id < temp.getId()) {
                //if true then we found the new Student node position - replace temp left child.
                if (isLeftWire(temp))
                    found = true;
                temp = temp.getLeftChild();
            } else {
                //if true then we found the new Student node position - replace temp right child.
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
        //after inserting a node is left and right nodes are uselly null but in our data structure we will set them as the predecessor and successor of the current node.
        newStudent.setLeftChild(predecessor(newStudent));
        newStudent.setRightChild(successor(newStudent));

        //updating the pointer to the middle node.
        updateMid(newStudent, Constants.INSERT);

        System.out.println("Inserted the student with the "+id+".");

    }
    //END INSERT

    /**
     *
     * @return the middle node. (middle sorted by ID)
     */
    public TreeNode getMid() {
        return mid;
    }
    //END GET MID

    /**
     * Updating the middle node.
     *
     * Important note: the nodeCounter maintains the "balance" of the middle node. meaning - if there amount of nodes smaller then the mid equals to the
     * amount of nodes greater than the mid so nodeCounter (the "balance") is 0. if there is more node greater then the mid so the nodeCounter (the "balance" is 1).
     *
     * @param node the node inserted / to be deleted
     * @param command clarify if its update duo to insert or delete
     */
    private void updateMid(TreeNode node, String command) {

        //Update after inserting new node.
        if (command.equals(Constants.INSERT)) {
            //if there is currently no middle node then set this node as the mid node.
            if (mid == null)
                mid = node;
            else {
                //the mid id is smaller than the new Node so if its currently "balance" add 1 to the nodeCounter,
                // else reduce 1 and update the mid pointer to the successor of the current mid.
                if (mid.getId() < node.getId()) {
                    if (nodeCounter == 1) {
                        mid = successor(mid);
                        nodeCounter--;
                    } else
                        nodeCounter++;
                    //the mid id is greater than the new Node so if its currently "balance" increase by 1 and update the mid pointer to the predecessor of the current mid ,
                    // else reduce by 1 the nodeCounter.
                } else {
                    if (nodeCounter == 0) {
                        mid = predecessor(mid);
                        nodeCounter++;
                    } else
                        nodeCounter--;
                }

            }

            //Update duo to deletion of the node.
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

    /**
     * Delete the given node - similar to the way shown in the book using the helper Wire / Leaf methods rather then check "null".
     * @param node to be deleted
     * @return the replacement node.
     */
    public TreeNode delete(TreeNode node) {
        if (node == null)
            return null;

        //update the middle node
        updateMid(node, Constants.DELETE);

        TreeNode replacement,temp;

        //similar to the book with small changed of using the helper methods to identify wired node.
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
        if(root==null)
            mid=null;

        return replacement;

    }
    //END DELETE

    //DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
    //DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
    //DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
    public void checkLeafs(){
        System.out.println();
        checkLeafs(root);
        System.out.print("END");
        System.out.println();
    }
    public void checkLeafs(TreeNode node){
        if (node != null) {
            if (!isLeftWire(node))
                checkLeafs(node.getLeftChild());
            else if(node.getLeftChild()==null&&predecessor(node)==null)
                System.out.println(node.getId()+" left = pred = null ");
                else
                System.out.println(node.getLeftChild().getId()!=predecessor(node).getId()?node.getId()+" left child "+node.getLeftChild().getId()+" != "+predecessor(node)+" pred ":node.getId()+" left child = pred ");
            if (!isRightWire(node))
                checkLeafs(node.getRightChild());
            else if((node.getRightChild()==null&&successor(node)==null))
                System.out.println(node.getId()+" right = sucess= null ");
            else System.out.println(node.getRightChild().getId()!=successor(node).getId()?node.getId()+" right child "+node.getRightChild().getId()+" != "+successor(node)+" pred ":node.getId()+" left child = pred ");

        }
    }

//    public void checkLeafs(TreeNode node){
//        if (node != null) {
//            if (!isLeftWire(node))
//                checkLeafs(node.getLeftChild());
//            if (!isRightWire(node))
//                checkLeafs(node.getRightChild());
//
//            System.out.println(node.getId()+" right child : "+node.getRightChild()+" and success : "+successor(node));
//            System.out.println(node.getId()+" left child : "+node.getLeftChild()+" and prev : "+predecessor(node));
//        }
//    }

    //DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
    //DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
    //DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE


    /**
     * Searching for node with the given id.
     * @param id of the student to search for.
     * @return the node with the matching id if found else null.
     */
    public TreeNode search(int id) {

        TreeNode temp = root;
        while (temp != null) {
            if (temp.getId() == id)
                return temp;
            if (temp.getId() < id) {
                //if true then the node doesnt exist in the data structure.
                if (isRightWire(temp))
                    return null;
                else
                    temp = temp.getRightChild();
            } else {
                //if true then the node doesnt exist in the data structure.
                if (isLeftWire(temp))
                    return null;
                else
                    temp = temp.getLeftChild();
            }
        }

        return temp;
    }
    //END SEARCH

    /**
     * return the node successor if exists (only maximum node successor is null).
     * @param node to get his successor
     * @return the successor of the node unless its the maximum node and in this case return null.
     */
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

    /**
     * return the node predecessor if exists (only minimum node predecessor is null).
     * @param node to get his predecessor
     * @return the predecessor of the node unless its the minimum node and in this case return null.
     */
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

    /**
     * public call to print inorder.
     */
    public void inorder() {
        System.out.println();
        inorder(root);
        System.out.print("END");
        System.out.println();
    }

    /**
     * recursive method to print inorder - similar to the book with small changed for wire child.
     * inorder print: left child -> root -> right child
     * @param node to continue from.
     */
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

    /**
     * public call to print preorder.
     */
    public void preorder() {
        System.out.println();
        preorder(root);
        System.out.print("END");
        System.out.println();
    }

    /**
     * recursive method to print preorder - similar to the book with small changed for wire child.
     * preorder print: root -> left child -> right child
     * @param node to continue from.
     */
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

    /**
     * public call to print postorder.
     */
    public void postorder() {
        System.out.println();
        postorder(root);
        System.out.print("END");
        System.out.println();
    }

    /**
     * recursive method to print postorder - similar to the book with small changed for wire child.
     * postorder print: left child -> right child -> root
     * @param node to continue from.
     */
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

    /**
     * return the minimum node (the node with the smallest ID) of a subtree of the given node.
     * @param node root of the subtree to return his minimum.
     * @return the subtree minimum.
     */
    private TreeNode minimum(TreeNode node) {
        TreeNode min = node;
        while (node!=null&&!isLeftWire(min))
            min = min.getLeftChild();
        return min;
    }
    //END NODE MIN

    /**
     * return the maximum node (the node with the greatest ID) of a subtree of the given node.
     * @param node root of the subtree to return his maximum.
     * @return the subtree maximum.
     */
    private TreeNode maximum(TreeNode node) {
        TreeNode max = node;
        while (node!=null&&!isRightWire(max))
            max = max.getRightChild();
        return max;
    }
    //END NODE MAX

    /**
     * public call for the tree minimum.
     * @return the minimum node (the node with the smallest ID) of the tree.
     */
    public TreeNode minimum() {
        return minimum(root);
    }
    //END TREE MIN

    /**
     * public call for the tree maximum.
     * @return the maximum node (the node with the greatest ID) of the tree.
     */
    public TreeNode maximum() {
        return maximum(root);
    }
    //END TREE MAX

    /**
     * check if the node would be a leaf in regular BinarySearchTree - if both left and right child are wired or null.
     * @param node to be check if leaf.
     * @return true if the node is leaf, else false.
     */
    private boolean isLeaf(TreeNode node) {
        return isLeftWire(node) && isRightWire(node);
    }
    //END LEAF

    /**
     * check if the left child is wired
     * @param node to check is left child
     * @return true if wired, else false.
     */
    private boolean isLeftWire(TreeNode node) {
        if (node.getLeftChild() == null || node.getLeftChild().getParent() != node)
            return true;
        return false;
    }
    //END LEFT WIRE

    /**
     * check if the right child is wired
     * @param node to check is right child
     * @return true if wired, else false.
     */
    private boolean isRightWire(TreeNode node) {
        if (node.getRightChild() == null || node.getRightChild().getParent() != node)
            return true;
        return false;
    }
    //END RIGHT WIRE


}
