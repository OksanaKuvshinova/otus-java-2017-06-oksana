import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> myList = createList();

        Collections.addAll(myList, new String[]{"a", "b", "c"});

        printOutList(myList, "myList");

        List<String> defArrayList = new ArrayList<String>();
        pad(defArrayList, myList.size());

        Collections.copy(defArrayList, myList);
        printOutList(defArrayList, "defArrayList");

        defArrayList.set(0, "z");

        Collections.copy(myList, defArrayList);

        printOutList(myList, "myList");

        Collections.sort(myList);

    }

    private static void pad(List<String> defArrayList, int size) {
        int sizeDiff = size - defArrayList.size();
        for (int i = 0; i < sizeDiff; i++) {
            defArrayList.add(null);
        }
    }

    private static void printOutList(List<String> myList, String listName) {
        System.out.println(listName + " size = " + myList.size());
        Object[] listElements = myList.toArray();
        for (Object o : listElements) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    private static MyArrayList<String> createList() {
        return new MyArrayList<String>();
    }

}
