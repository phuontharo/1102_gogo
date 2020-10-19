package com.example.testimg;

public class Tess {
//    ArrayList<Node> listBlack, listWhite;
//    Scanner sc = new Scanner(System.in);
//    int max = 9;
//    Node[][] table;
//    ArrayList<Node> graphs;
//
//    int valuesNext = 1;
//
//    void insert(int x, int y) {
//        System.out.println("insert ("+x + ":"+ y+")");
//        table[x][y].setValue(valuesNext);
//        doSth(table[x][y]);
//        valuesNext = valuesNext == 1 ? 2 : 1;
//    }
//
//    void reload() {
//        table = new Node[max][max];
//        for (int i = 0; i < max; i++) {
//            for (int j = 0; j < max; j++) {
//                Node node = new Node(i, j, 0);
//                table[i][j] = node;
//            }
//        }
//    }
//
//    void displayAll() {
//        for (int i = 0; i < max; i++) {
//            for (int j = 0; j < max; j++) {
//                System.out.print(table[i][j].getValue() + "  ");
//            }
//            System.out.println();
//        }
//    }
//
//    // get all Node whith value Opposite
//    ArrayList<Node> allOppositeNode(Node node) {
//        ArrayList<Node> result = new ArrayList<>();
//        ArrayList<Node> list = allNodeAround(node);
//        int value = node.getValue();
//        System.out.println("This node has value:"+ value);
//        for (int i = 0; i < list.size(); i++) {
//            Node thisNode = list.get(i);
//            if (thisNode.getValue() != 0 && thisNode.getValue() != value) {
//                result.add(thisNode);
//            }
//        }
//        return result;
//
//
//    }
//
//    void doSth(Node node) {
//        // List All Oposite
//        ArrayList<Node> listNode = allOppositeNode(node);
//        for (int i = 0; i < listNode.size(); i++) {
//            ArrayList<Node> tempList = new ArrayList<>();
//            getGraphs(listNode.get(i), tempList);
//            boolean isDeath = checkGraphsDeath(tempList);
//            System.out.println("Death : "+ isDeath);
//            if (isDeath) {
//                changeValue(tempList, 5);
//            }
//
//        }
//
//    }
//
//
//    // Lấy 4 Node xung quanh 1 Node
//    ArrayList<Node> allNodeAround(Node node) {
//        ArrayList<Node> nodes = new ArrayList<>();
//        int x = node.getX();
//        int y = node.getY();
//        if (x - 1 >= 0) {
//            nodes.add(table[x - 1][y]);
//        }
//        if (x + 1 <= table.length - 1) {
//            nodes.add(table[x + 1][y]);
//        }
//        if (y - 1 >= 0) {
//            nodes.add(table[x][y - 1]);
//        }
//        if (y + 1 <= table.length - 1) {
//            nodes.add(table[x][y + 1]);
//        }
//        return nodes;
//    }
//
//    /*
//     * Check xem một chuỗi liền kề nhau có tiếp xúc với ô có giá trị 0 hay không, nếu có, Graphs được coi là sống
//     * Nếu không có Node nào tiếp xúc với 0, Graphs chết.
//     * */
//    boolean checkGraphsDeath(ArrayList<Node> graphs) {
//        System.out.println("Graphs have : "+ graphs.size());
//        for (int i = 0; i < graphs.size(); i++) {
//            System.out.println("Visit Node : ("+ graphs.get(i).x + ":"+ graphs.get(i).y+")");
//            ArrayList<Node> aroundNode = allNodeAround(graphs.get(i));
//            System.out.println("there are : "+ aroundNode.size()+ " Node around");
//            for (int j = 0; j < aroundNode.size(); j++) {
//                if (aroundNode.get(j).value == 0) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    // From 1 Node, get 1 Graphs with that Node
//    void getGraphs(Node node, ArrayList<Node> result) {
//        result.add(node);
//        node.setVisited(true);
//        ArrayList<Node> arround = allNodeAround(node);
//        for (int i = 0; i < arround.size(); i++) {
//            Node thisNode = arround.get(i);
//            if (!thisNode.isVisited() && thisNode.getValue() == node.getValue()) {
//                getGraphs(thisNode, result);
//            }
//        }
//        node.setVisited(false);
//    }
//
//    void changeValue(ArrayList<Node> graphs, int value) {
//        for (int i = 0; i < graphs.size(); i++) {
//            graphs.get(i).setValue(value);
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        TestVersion2 main = new TestVersion2();
//        main.reload();
//        for (int i = 0; i < 100; i++) {
//            System.out.println("enter x,y " + i + "th : ");
//            String point = sc.nextLine().trim();
//            String[] pointXY = point.split(":");
//            int x = Integer.parseInt(pointXY[0]);
//            int y = Integer.parseInt(pointXY[1]);
//            main.insert(x, y);
//            main.displayAll();
//        }
//
//    }
}
