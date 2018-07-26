package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        BinarySearchTree tree = new BinarySearchTree();

        Scanner scanner = new Scanner(System.in);

        help();

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
            case Constants.FILE:
                uploadFile(text, tree);
                break;
            case Constants.HELP:
                help();
                break;
            default: System.out.println(command+", is not valid command");
        }
    }

    private static void help(){
        System.out.println("commands menu:\n"+Constants.QUICK_GUIDE_HELP+"\n"+Constants.QUICK_GUIDE_INSERT+"\n"+Constants.QUICK_GUIDE_DELETE+"\n"+Constants.QUICK_GUIDE_SEARCH+"\n"
                +Constants.QUICK_GUIDE_MIN+"\n"+Constants.QUICK_GUIDE_MAX+"\n"+Constants.QUICK_GUIDE_MID+"\n"+Constants.QUICK_GUIDE_NEXT+"\n"+Constants.QUICK_GUIDE_PREVIOUS+"\n"
                +Constants.QUICK_GUIDE_INORDER+"\n"+Constants.QUICK_GUIDE_POSTORDER+"\n"+Constants.QUICK_GUIDE_PREORDER+"\n"+Constants.QUICK_GUIDE_FILE+"\n");
    }

    private static void uploadFile(String text, BinarySearchTree tree){
        String fileLocation = text.substring(text.indexOf('(')+1,text.indexOf(')'));
        String fileData=readFile(fileLocation, Charset.defaultCharset());
        textToAction(fileData,tree);

    }

    private static String readFile(String path, Charset encoding)
    {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("couldn't read the file.");
        }
        return new String(encoded, encoding);

    }
}
