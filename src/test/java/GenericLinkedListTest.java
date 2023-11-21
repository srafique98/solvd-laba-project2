import com.solvd.laba.custom.GenericLinkedList;

import java.util.*;


public class GenericLinkedListTest {
    public static void main(String[] args) {
        GenericLinkedList<String> list = new GenericLinkedList<>();
        System.out.println("List size: " + list.size());
        System.out.println("List is empty: " + list.isEmpty());

        list.add("apple");
        list.add("orange");
        list.add("banana");

        System.out.println("List size: " + list.size());
        System.out.println("List is empty: " + list.isEmpty());
        System.out.println("List contains 'apple': " + list.contains("apple"));
        System.out.println("List contains 'grape': " + list.contains("grape"));
        System.out.println("Element at index 1: " + list.get(1));

        list.set(2, "grape");
        System.out.println("Element at index 2: " + list.get(2));
        list.add("mango");
        System.out.println("List contains 'mango': " + list.contains("mango"));
        System.out.println("List size: " + list.size());
        list.remove("banana");
        System.out.println("List contains 'banana': " + list.contains("banana"));
        System.out.println("List size: " + list.size());

        List<String> newItems = Arrays.asList("pineapple", "strawberry");
        list.addAll(newItems);
        System.out.println("List contains 'pineapple': " + list.contains("pineapple"));
        System.out.println("List size: " + list.size());
        list.addAll(2, Arrays.asList("kiwi", "coconut"));
        System.out.println("Element at index 3: " + list.get(3));
        System.out.println("List size: " + list.size());
        list.clear();
        System.out.println("List size: " + list.size());
        System.out.println("List is empty: " + list.isEmpty());

        list.add("apple");
        list.add("orange");
        list.add("banana");
        System.out.println("Index of 'apple': " + list.indexOf("apple"));
        System.out.println("Index of 'grape': " + list.indexOf("grape"));
        System.out.println("Last index of 'apple': " + list.lastIndexOf("apple"));
        System.out.println("Last index of 'grape': " + list.lastIndexOf("grape"));
        List subList = list.subList(1, 3);
        System.out.println("Sublist: " + subList.size());

    }
}

