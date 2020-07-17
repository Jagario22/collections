package com.nix.collections.view;

import com.nix.collections.list.ForwardLinkedList;
import com.nix.collections.util.StringUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CollectionsApp {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("H1el2", "3wor4ld55");
        System.out.println("1. List of strings: " + strings.toString());
        System.out.println("Result: " + StringUtil.getNumFromString(strings) + "\n");

        int size;
        Integer item;
        ForwardLinkedList<Integer> list = new ForwardLinkedList<>();
        list.add(200);
        list.add(300);
        list.add(100);
        list.add(100);
        list.add(400);
        list.add(500);
        list.add(400);
        System.out.println("2. List: " + list.toString());

        list.add(0, 100);
        System.out.println("add item 100 with index 0: " + list.toString());

        System.out.println("index of 100: " + list.indexOf(100));

        list.removeItem(100);
        System.out.println("remove item 100: " + list.toString());

        list.remove(0);
        System.out.println("Remove item with index 0: " + list.toString());

        size = list.size();
        System.out.println("size of list: " + size);

        item = list.get(3);
        System.out.println("item with index 3: " + item);

        list.set(0,-1);
        System.out.println("change value of item with index 0 from 100 to -1: " + list.toString());

        if (list.contains(200))
        {
            System.out.println("list contains item 200");
        }

        list.clear();
        System.out.println("clear list: " + list.toString());


     }
}
