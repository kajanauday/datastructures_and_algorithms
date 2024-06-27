import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import printer.Printer;

public class Graph {
    boolean printInputFormat = true;
    // Vertex startingVertex = null;
    Scanner scanner = new Scanner(System.in);
    LinkedList<Vertex> queue = new LinkedList<>();
    ArrayList<Vertex> startingVertices = new ArrayList<>();
    HashSet<Vertex> traversedVertices = new HashSet<>();
    HashMap<Integer, Vertex> allVertices = new HashMap<>();

    private void insert() {
        Vertex node;
        int sep, data;
        String input;
        int neighbourData;
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

            if (allVertices.containsKey(data))
                node = allVertices.get(data);
            else {
                node = new Vertex(data);
                startingVertices.add(node);
                allVertices.put(data, node);
            }
            for (String s : neighbours) {
                neighbourData = Integer.parseInt(s);
                if (!allVertices.containsKey(neighbourData))
                    allVertices.put(neighbourData, new Vertex(neighbourData));
                node.vertices.add(allVertices.get(neighbourData));
            }
        }
    }

    private void delete() {

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
            case 1 -> {
                for (Vertex startingVertex : startingVertices)
                    breadthFirstTraversal(startingVertex);
            }
            case 2 -> {
                for (Vertex startingVertex : startingVertices)
                    depthFirstTraversal(startingVertex);
            }
            case 3 -> manualTraversal();
            default -> {
                System.out.print("invalid selection\nDo you want to repeat[y/n]:");
                char cont = scanner.next().charAt(0);
                if (cont == 'y' || cont == 'Y')
                    traverseHandler();
            }
        }
    }

    private void manualTraversal() {
        boolean isUpdated = false;
        int startingVertexToStart;
        if (startingVertices.isEmpty()) {
            System.out.println("Empty Graph.....");
            return;
        }
        System.out.println("initial node(s):");
        startingVertices.forEach(element -> System.out.print(element + " "));
        System.out.println("Choose starting Vertex to start with :");
        startingVertexToStart = scanner.nextInt();
        Vertex vertex = null;
        for (Vertex v : startingVertices)
            if (v.data == startingVertexToStart) {
                vertex = v;
                break;
            }
        if (vertex == null) {
            System.out.println("Invalid Starting Vertex....");
            manualTraversal();
            return;
        }
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
            else if (data.equalsIgnoreCase("reset")) {
                manualTraversal();
                return;
            } else {
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
            case 1 -> {
                for (Vertex v : startingVertices)
                    while (breadthFirstSearch(v, scanner.nextInt())); //checkhere
            }
            case 2 -> {
                for (Vertex v : startingVertices)
                    while (depthFirstSearch(v, scanner.nextInt())) //checkhere
                        ;
            }
            default -> {
                System.out.print("invalid selection\nDo you want to repeat[y/n]:");
                char cont = scanner.next().charAt(0);
                if (cont == 'y' || cont == 'Y')
                    searchHandler();
            }
        }
    }

    private boolean depthFirstSearch(Vertex startingVertex, int searchingData) {
        Stack<Vertex> stack = new Stack<>();
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex top;
        if (startingVertex != null) {
            stack.push(startingVertex);
        }
        while (stack.size() > 0) {
            top = stack.pop();
            while (top != startingVertex && !path.getLast().vertices.contains(top))
                path.removeLast();
            if (top.data == searchingData) {
                path.forEach(element -> System.out.print(element.data + ", "));
                System.out.println(top.data);
                return true;
            }
            if (top.vertices.size() > 0) {
                path.add(top);
                stack.addAll(top.vertices);
            }
        }
        System.out.println(searchingData + " is not found in the graph!");
        return false;
    }

    private boolean breadthFirstSearch(Vertex startingVertex, int searchingData) {
        Stack<Vertex> stack = new Stack<>();
        Stack<Vertex> path = new Stack<>();
        Vertex vv;
        Vertex vertex;
        if (startingVertex != null)
            queue.add(startingVertex);
        while (queue.size() > 0) {
            vertex = queue.pop();
            stack.add(vertex);
            if (vertex.data == searchingData) {
                System.out.println(searchingData + " found :)");
                path.add(vertex);
                while (stack.size() > 0) {
                    vv = stack.pop();
                    if (vv.vertices.contains(path.peek()))
                        ;
                    path.add(vv);
                }
                while (path.size() > 0) {
                    System.out.print(path.pop() + "-> ");
                }
                return true;
            }
            for (Vertex v : vertex.vertices) {
                if (!traversedVertices.contains(v)) {
                    traversedVertices.add(v);
                    queue.add(v);
                }
                if (v.data == searchingData) {
                    System.out.println(searchingData + " found :)");
                    path.add(vertex);
                    while (stack.size() > 0) {
                        vv = stack.pop();
                        if (vv.vertices.contains(path.peek()))
                            ;
                        path.add(vv);
                    }
                    while (path.size() > 0) {
                        System.out.print(path.pop() + "-> ");
                    }
                    return true;
                }
            }
        }
        Printer.printFailureMessage("No such elements found (:");
        return false;
    }

    public static void main(String argvs[]) {
        Graph graph = new Graph();
        List<String> menu = Arrays.asList(
                "1. INSERT",
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