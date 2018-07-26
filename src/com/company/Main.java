package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        BinarySearchTree tree = new BinarySearchTree();

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (true) {
            ArrayList<Action> list = textToAction(input);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            input = scanner.nextLine();
        }
    }

        private static ArrayList<Action> textToAction(String text){
        ArrayList<Action> list = new ArrayList<>();
        int index =0;
        int temp=0;
        while (temp!=-1){
            temp = text.indexOf(";",index);
            if(temp!=-1)
             list.add(new Action(text.substring(index,temp)));
            index=temp+1;
        }
        return list;
    }
}
