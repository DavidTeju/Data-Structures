import lists.arrayList.ArrayList;
import lists.linkedList.LinkedList;

import java.util.Arrays;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        try {
            linkedListTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        arrayListTest();
    }

    public static void arrayListTest(){
        var testList = new ArrayList<String>();
        testList.add("member0");
        testList.add("member1");
        testList.add("member2");
        testList.add("member3");
        testList.add("member4");
        System.out.println(testList);
        System.out.println(testList.indexOf("member1"));
        System.out.println(testList.lastIndexOf("Hello"));
        System.out.println(testList.remove("Hello"));
        System.out.println(testList);
    }

    public static void linkedListTest() throws InterruptedException {
        var testList = new LinkedList<String>();
        System.out.println("{ lists.linkedList testList = new lists.linkedList(); }");
        System.out.println("We have constructed an empty Linked List");
        System.out.print("Let's print our empty list ");
        System.out.println("{ System.out.println(testList); }: ");
        System.out.println(testList);
        System.out.print("\nIs our list empty? ");
        System.out.println("{ System.out.println(testList.isEmpty()); }:");
        System.out.println(testList.isEmpty());
//        Thread.sleep(5500);

        System.out.println("\n//---------------------------------------------------------------------------------------");
        testList.add("member0");
        testList.add("member1");
        testList.add("member2");
        testList.add("member3");
        testList.add("member4");
        System.out.print("""
                {
                testList.add("member0");
                testList.add("member1");
                testList.add("member2");
                testList.add("member3");
                testList.add("member4");
                }""");
        System.out.println("\nNow, we've added five Strings to our list");
        System.out.println("This is now our list { System.out.println(testList); }:");
        System.out.println(testList);
        System.out.println("\nIs our list still empty? { System.out.println(testList.isEmpty()); }: ");
        System.out.println(testList.isEmpty());
//        Thread.sleep(5000);

        System.out.println("\n//---------------------------------------------------------------------------------------");
        String[] array = {"member5", "member6", "member7", "member8", "member9"};
        testList.addAll(List.of(array));
        System.out.print("""
                {
                String[] array = {"member5", "member6", "member7", "member8", "member9"};
                testList.addAll(List.of(array));
                }""");
        System.out.println("\nWe've added five more Strings from an array to our list \n\t(We used listOf because the add all method only accepts Collections)");
        System.out.println("This is now our list { System.out.println(testList); }:");
        System.out.println(testList);
        System.out.println("\nWhat String is at index 4? { System.out.println(testList.get(4)); }:");
        System.out.println(testList.get(4));
        System.out.println("\nHow big is the list? { System.out.println(testList.size()); }:");
        System.out.println(testList.size());
        Thread.sleep(7000);

        System.out.println("\n//---------------------------------------------------------------------------------------");
        System.out.println("Your interviewer asked you to reverse a linked list? Don't be scared. We've done it for you");
        System.out.println("""
                {
                testList.reverseThis();
                System.out.println(testList);
                }""");
        testList = testList.reverse();
        System.out.println(testList);
        System.out.println("Now, what String is at index 4?");
        System.out.println(testList.get(4));
        System.out.println("\nDoes the list contain \"member4\"?");
        System.out.println(testList.contains("member4"));
        Thread.sleep(5000);

        //---------------------------------------------------------------------------------------
        System.out.println("\nYou can also get an array form of the list");
        System.out.println(Arrays.toString(testList.toArray()));
        System.out.println("Oh, yeah. It's still reversed. Let's fix that");
        testList = testList.reverse();
        System.out.println(testList);
        Thread.sleep(5000);

        //---------------------------------------------------------------------------------------
        System.out.println("You're not just limited to Strings");
        var intList = new LinkedList<>(2);
        testList.remove("member3");
        System.out.println(testList.reverse());
        testList.isEmpty(); //Will print false because the list was initialized with The first node added
    }

    public void linkedListExceptionTest(){

    }
}