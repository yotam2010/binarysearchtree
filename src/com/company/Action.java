package com.company;

public abstract class Action {



    public static void insert(String text,BinarySearchTree tree){
        try {
            int id = Integer.parseInt(text.substring(text.indexOf('(') + 1, text.indexOf(',')));
            String name = text.substring(text.indexOf(',') + 1, text.indexOf(')'));

        if(!nameValidation(name)){
            System.out.println("invalid input.");
        }else{
            tree.insert(id,name);
        }
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
        }
    }

    public static TreeNode delete(String text,BinarySearchTree tree){
        TreeNode replacementNode= null;
        try{
            int id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));

                replacementNode = tree.delete(tree.search(id));

        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
        }
        return replacementNode;
    }

    public static TreeNode next(String text, BinarySearchTree tree){
        TreeNode nodeSuccessor = null;
        try{
        int id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));

            nodeSuccessor = tree.successor(tree.search(id));

        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
        }
        return nodeSuccessor;

    }

    public static TreeNode prev(String text, BinarySearchTree tree){
        TreeNode nodePredecessor = null;
        try{
        int id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));

            nodePredecessor = tree.predecessor(tree.search(id));

    }catch (Exception e){
        System.out.println("invalid input - syntax error.");
    }
        return nodePredecessor;
    }

    public static TreeNode search(String text, BinarySearchTree tree){
        TreeNode resultNode = null;
        try{
        int id = Integer.parseInt(text.substring(text.indexOf('(')+1,text.indexOf(')')));

            resultNode = tree.search(id);
    }catch (Exception e){
        System.out.println("invalid input - syntax error.");
    }
        return resultNode;
    }

//    private static boolean idValidation(int id) {
//        //we could add more tests here to validate the id.
//        if(id<100000000 || id>999999999)
//            return false;
//        return true;
//    }

    private static boolean nameValidation(String name) {
        //we could add more tests here to validate the id.
        if(name.length()<2 || !name.matches("[a-zA-Z ]+"))
            return false;
        return true;
    }
}
