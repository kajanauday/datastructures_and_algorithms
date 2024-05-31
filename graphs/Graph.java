import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import printer.Printer;

public class Graph {
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Vertex> allVertices = new HashMap<>();
    Vertex startingVertex = null;
    boolean printInputFormat = true;
    String input;
    int count = 0, level = 1;
    HashSet<Vertex> traversedVertices = new HashSet<>();
    LinkedList<Vertex> queue = new LinkedList<>();

    public static void main(String argvs[]) {
        Graph graph = new Graph();
        List<String> menu = Arrays.asList("1. TOUCH", "2. BFS", "3. DFS", "4. BFT", "5. DFT",
                "6. EXIT(0)", "7. MT");
        do {
            try {
                Printer.printMenu("GRAPH", "-", menu);
                System.out.print("CHOOSE OPTION:");
                int option = graph.scanner.nextInt();
                switch (option) {
                    case 1 -> graph.touchGraph();
                    case 2 -> {/*-----------------------------------> {BFS --> ITERATIVE} */
                        System.out.println("Enter Data to search:");
                        graph.breadthFirstSearch(graph.scanner.nextInt());
                    }
                    case 3 -> {/*-----------------------------------> {DFS --> ITERATIVE} */
                        System.out.println("Enter Element to search: ");
                        graph.depthFirstSearch(graph.scanner.nextInt());
                    }
                    case 4 -> {/*-----------------------------------> {BFT --> RECURSIVE} */
                        graph.traversedVertices.clear();
                        graph.queue.clear();
                        System.out.print(
                                graph.startingVertex != null ? graph.startingVertex.data + ", " : "Empty GRAPH");
                        graph.breadthFirstTraversal(graph.startingVertex);
                        System.out.println("NaN");
                    }
                    case 5 -> {/*-----------------------------------> {DFT --> RECURSIVE} */
                        graph.traversedVertices.clear();
                        System.out.println("DFT:");
                        graph.depthFirstTraversal(graph.startingVertex);
                        System.out.println("NaN");
                    }

                    case 6 -> {/*----------------------------------> {EXIT} */
                        graph.scanner.close();
                        System.exit(0);
                    }
                    case 7 -> {/*----------------------------------> {EXIT} */
                        graph.manualTraversal();
                    }
                    default -> Printer.printErrorMessage("---Invalid option selected---");
                }
            } catch (Exception e) {
                Printer.printErrorMessage("[ERROR]" + e.getMessage());
                graph.scanner.next();
            }
        } while (true);
    }

    private void touchGraph() {
        Vertex node, assigner;
        int seperator, data;
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
            seperator = input.indexOf("-");
            data = Integer.parseInt(input.substring(0, seperator));
            neighbours = input.substring(seperator + 1).split(",");
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

    private void breadthFirstSearch(int data) {
        Vertex vertex;
        traversedVertices.clear();
        queue.clear();
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

    private void depthFirstSearch(int data) {
        Stack<Vertex> stack = new Stack<>();
        if (startingVertex != null)
            stack.push(startingVertex);
        while (stack.size() > 0) {
            Vertex vertex = stack.pop();
            System.out.println(vertex.data + ", ");
            for (Vertex v : vertex.vertices) {
                if (v.data != data)
                    stack.push(v);
                else {
                    Printer.printSuccessMessage(data + " found :)");
                    return;
                }
            }
            Printer.printFailureMessage("No such element found :(");
        }
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
}