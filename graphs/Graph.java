import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.sound.midi.SysexMessage;

import printer.Printer;

public class Graph {
    boolean printInputFormat = true;
    Vertex startingVertex = null;
    Scanner scanner = new Scanner(System.in);
    LinkedList<Vertex> queue = new LinkedList<>();
    HashSet<Vertex> startingVertices = new HashSet<>();
    HashSet<Vertex> traversedVertices = new HashSet<>();
    HashMap<Integer, Vertex> allVertices = new HashMap<>();
    HashSet<Vertex> listOfCreatingVertices = new HashSet<>();

    private void insert() {
        Vertex node, assigner;
        int sep, data;
        String input;
        String[] neighbours;
        while (true) {
            if (printInputFormat) {
                Printer.printHeader(" INSTRUCTIONS ", "-");
                Printer.printHeading("""
                        1. use [-] for seperating vertex and its neighbours.
                        2. use [,] for seperating multiple nodes.
                        3. use [;] for stopping the input.
                        {e.g: 1-2,3,4,5   <--- Vertex [1] and it's neighbours [2,3,4,5]
                            ;             <--- stopping input
                            """);
                Printer.printHeader(" Start building graph ", "-");
                printInputFormat = false;
            }
            input = scanner.next();
            if (input.equals(";"))
                break;
            sep = input.indexOf("-");
            data = Integer.parseInt(input.substring(0, sep));
            neighbours = input.substring(sep + 1).split(",");
            if (allVertices.size() == 0) {
                startingVertex = node = new Vertex(data);
                allVertices.put(data, startingVertex);
            } else if (allVertices.containsKey(data))
                node = allVertices.get(data);
            else
                node = new Vertex(data);
            for (String s : neighbours) {
                int d = Integer.parseInt(s);
                if (allVertices.containsKey(d)) {
                    node.vertices.add(allVertices.get(d));
                } else {
                    assigner = new Vertex(d);
                    allVertices.put(d, assigner);
                    node.vertices.add(assigner);
                }
            }
        }
    }

    private void delete() {

    }

    private void manualTraversal() {
        boolean isUpdated = false;
        if (startingVertex != null)
            System.out.println("initial node:" + startingVertex.data);
        else {
            System.out.println("Empty Graph.....");
            return;
        }
        Vertex vertex = startingVertex;
        String data;
        int intData;
        while (true) {
            System.out.print("choose next element [");
            for (Vertex v : vertex.vertices) {
                System.out.print(v.data + ", ");
            }
            System.out.print("NaN]: ");
            data = scanner.next();
            if (data.equalsIgnoreCase("end"))
                break;
            else if (data.equalsIgnoreCase("reset"))
                vertex = startingVertex;
            else {
                intData = Integer.parseInt(data);
                for (Vertex v : vertex.vertices) {
                    if (v.data == intData) {
                        vertex = v;
                        isUpdated = true;
                        break;
                    }
                }
            }
            if (!isUpdated) {
                System.out.println("Wrong input... choose again: ");
            }
        }
    }

    private void modifyHandler() {

    }

    private void searchHandler() {
        queue.clear();
        Printer.printMenu("SEARCH", "_", Arrays.asList(
                "1. BREADTH FIRST",
                "2. DEPTH FIRST"));
        System.out.print("CHOOSE OPTION:");
        int option = scanner.nextInt();
        System.out.print("Enter data to search :");
        switch (option) {
            case 1 -> breadthFirstSearch(scanner.nextInt());
            case 2 -> {
                depthFirstSearch(scanner.nextInt());
            }
            default -> {
                System.out.print("invalid selection\nDo you want to repeat[y/n]:");
                char cont = scanner.next().charAt(0);
                if (cont == 'y' || cont == 'Y')
                    searchHandler();
            }
        }
    }

    private void depthFirstSearch(int searchingData) {
        Stack<Vertex> stack = new Stack<>();
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex top, v;
        if (startingVertex != null)
            stack.push(startingVertex);
        while (stack.size() > 0) {
            top = stack.pop();
            if (top.data == searchingData) {
                path.forEach(element -> System.out.print(element.data + ", "));
                System.out.println(top.data);
                return;
            }

            while (top != startingVertex && !path.getLast().vertices.contains(top)) {
                path.removeLast();
            }
            if (top.vertices.size() > 0)
                path.add(top);
            stack.addAll(top.vertices);

        }
    }

    private void traverseHandler() {
        traversedVertices.clear();
        queue.clear();
        Printer.printMenu("TRAVERSAL", "_", Arrays.asList(
                "1. BREADTH FIRST",
                "2. DEPTH FIRST",
                "3. MANUAL"));
        System.out.print("CHOOSE OPTION:");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> breadthFirstTraversal(startingVertex);
            case 2 -> depthFirstTraversal(startingVertex);
            case 3 -> manualTraversal();
            default -> {
                System.out.print("invalid selection\nDo you want to repeat[y/n]:");
                char cont = scanner.next().charAt(0);
                if (cont == 'y' || cont == 'Y')
                    traverseHandler();
            }
        }
    }

    private void breadthFirstSearch(int data) {
        Vertex vertex;
        if (startingVertex != null)
            queue.add(startingVertex);
        while (queue.size() > 0) {
            vertex = queue.pop();
            if (vertex.data == data) {
                System.out.println(data + " found :)");
                return;
            }
            for (Vertex v : vertex.vertices) {
                if (!traversedVertices.contains(v)) {
                    traversedVertices.add(v);
                    queue.add(v);
                }
                if (v.data == data) {
                    Printer.printSuccessMessage(data + " found :)");
                    return;
                }
            }
        }
        Printer.printFailureMessage("No such elements found (:");
    }

    private void breadthFirstTraversal(Vertex thisVertex) {
        if (thisVertex == null)
            return;
        if (!traversedVertices.contains(thisVertex)) {
            traversedVertices.add(thisVertex);
            for (Vertex vertex : thisVertex.vertices) {
                queue.add(vertex);
                System.out.print(vertex.data + ", ");
            }
            if (queue.size() > 0)
                breadthFirstTraversal(queue.pop());
        }
    }

    private void depthFirstTraversal(Vertex thisVertex) {
        if (thisVertex == null)
            return;
        System.out.print(thisVertex.data + ", ");
        traversedVertices.add(thisVertex);
        for (Vertex v : thisVertex.vertices) {
            if (!traversedVertices.contains(v))
                depthFirstTraversal(v);
        }
    }

    public static void main(String argvs[]) {
        Graph graph = new Graph();
        List<String> menu = Arrays.asList(
                "1.INSERT",
                "2. DELETE",
                "3. MODIFY",
                "4. SEARCH",
                "5. TRAVERSE",
                "6. EXIT");
        do {
            try {
                Printer.printMenu("GRAPH", "-", menu);
                System.out.print("CHOOSE OPTION:");
                int option = graph.scanner.nextInt();
                switch (option) {
                    case 1 -> graph.insert();
                    case 2 -> graph.delete();
                    case 3 -> graph.modifyHandler();
                    case 4 -> graph.searchHandler();
                    case 5 -> graph.traverseHandler();
                    case 6 -> {
                        graph.scanner.close();
                        System.exit(0);
                    }
                    default -> Printer.printErrorMessage("---Invalid option selected---");
                }
            } catch (Exception e) {
                Printer.printErrorMessage("[ERROR]" + e.getMessage());
                e.printStackTrace();
                graph.scanner.next();
            }
        } while (true);
    }
}