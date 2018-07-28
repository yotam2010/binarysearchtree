package com.company;

public abstract class Action {


    /**
     * Extracting the relevant parameters from the text and call the tree insert method using thos parameters
     * @param text to extract the parameters from.
     * @param tree to insert new now with these parameters.
     */
    public static void insert(String text,BinarySearchTree tree){
        int id;
        String name;
        try {
             id = Integer.parseInt(text.substring(text.indexOf('(') + 1, text.indexOf(',')));
             name = text.substring(text.indexOf(',') + 1, text.indexOf(')'));
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
            return;
        }
        if(!nameValidation(name)){
            System.out.println("invalid input.");
        }else{
            tree.insert(id,name);
        }

    }

    /**
     * Extracting the id from the text and call the tree delete method using the id.
     * @param text to extract the id from.
     * @param tree to delete the node with these id.
     */
    public static TreeNode delete(String text,BinarySearchTree tree){
        TreeNode replacementNode;
        int id;
        try{
             id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
            return null;
        }
                replacementNode = tree.delete(tree.search(id));

        return replacementNode;
    }

    /**
     * Extracting the id from the text and call the tree successor method using the id.
     * @param text to extract the id from.
     * @param tree to get the successor of the node with these id.
     * @return the successor of the node with the given id in the tree. (null if dont exist)
     */
    public static TreeNode next(String text, BinarySearchTree tree){
        TreeNode nodeSuccessor;
        int id;
        try{
            id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
            return null;
        }
        nodeSuccessor = tree.successor(tree.search(id));
        return nodeSuccessor;

    }

    /**
     * Extracting the id from the text and call the tree predecessor method using the id.
     * @param text to extract the id from.
     * @param tree to get the predecessor of the node with these id.
     * @return the predecessor of the node with the given id in the tree. (null if dont exist)
     */
    public static TreeNode prev(String text, BinarySearchTree tree){
        TreeNode nodePredecessor;
        int id;
        try{
            id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
            return null;
        }
        nodePredecessor = tree.predecessor(tree.search(id));
        return nodePredecessor;
    }

    /**
     * Extracting the id from the text and call the tree search method using the id.
     * @param text to extract the id from.
     * @param tree to search for the node with these id.
     * @return the node found by the search method. (null if not found)
     */
    public static TreeNode search(String text, BinarySearchTree tree){
        TreeNode resultNode;
        int id;
        try{
            id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
            return null;
        }
        resultNode = tree.search(id);
        return resultNode;
    }

    /**
     * validate the name given as parameter
     * @param name 1. checks if contains only English letters and spaces. 2. checks if contains at least 2 chars.
     * @return true if validated, else false.
     */
    private static boolean nameValidation(String name) {
        //we could add more tests here to validate the name.
        if(name.length()<2 || !name.matches("[a-zA-Z ]+")) {
            System.out.println("the student name most contain at least 2 chars. chars most be English letters and spaces only.");
            return false;
        }
        return true;
    }
}
