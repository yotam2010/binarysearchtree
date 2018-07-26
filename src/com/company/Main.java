package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        BinarySearchTree tree = new BinarySearchTree();

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (true) {
            textToAction(input,tree);
            input = scanner.nextLine();
        }
    }

        private static void textToAction(String text,BinarySearchTree tree){
        int index =0;
        int temp=0;
        while (temp!=-1){
            temp = text.indexOf(";",index);
            if(temp!=-1)
             action(text.substring(index,temp),tree);
            index=temp+1;
        }
    }

    private static void action(String text,BinarySearchTree tree){
        String command="";
        try {
            int firstIndex = text.indexOf('(');
            command = text.substring(0, firstIndex);
        }catch (Exception e){
            System.out.println("invalid input - syntax error.");
            return;
        }
        TreeNode result=null;
        switch (command){
            case Constants.INSERT:
                Action.insert(text,tree);
                break;
            case Constants.DELETE:
                result = Action.delete(text,tree);
                System.out.println((result==null)?"the node could not be deleted":result);
                break;
            case Constants.SEARCH:
                 result = Action.search(text,tree);
                System.out.println((result==null)?"not found":result);
                break;
            case Constants.MID:
                System.out.println(tree.getMid());
                break;
            case Constants.MIN:
                System.out.println(tree.minimum());
                break;
            case Constants.MAX:
                System.out.println(tree.maximum());
                break;
            case Constants.NEXT:
                 result = Action.next(text,tree);
                System.out.println((result==null)?"don't have successor":result);
                break;
            case Constants.PREVIOUS:
                result = Action.prev(text,tree);
                System.out.println((result==null)?"don't have predecessor":result);
                break;
            case Constants.INORDER:
                tree.inorder();
                break;
            case Constants.POSTORDER:
                tree.postorder();
                break;
            case Constants.PREORDER:
                tree.preorder();
                break;

            default: System.out.println(command+", is not valid command");
        }
    }
}
