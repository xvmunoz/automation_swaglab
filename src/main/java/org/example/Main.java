package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        List<String> test1 = new ArrayList<>();
        List<String> test2 = new ArrayList<>();
        List<List> allLists =  new ArrayList<>();

        test1.add("test1L");
        test2.add("test2L");
        allLists.add(test1);
        allLists.add(test2);

        System.out.println(allLists);
    }


}