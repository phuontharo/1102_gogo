package com.example.testimg;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

import java.util.ArrayList;

public class Controller {
    Node[][] table;
    int black = Color.BLACK;
    int white = Color.WHITE;
    int yellow = Color.YELLOW;

    public Controller(Node[][] table) {
        this.table = table;
    }


    void execute(int x, int y){
        Node node = table[x][y];
        ArrayList<Node> listNode = allOppositeNode(node);
        for (int i = 0; i < listNode.size(); i++) {
            ArrayList<Node> tempList = new ArrayList<>();
            getGraphs(listNode.get(i), tempList);
            boolean isDeath = checkGraphsDeath(tempList);
            if (isDeath) {
                changeValue(tempList, yellow);
            }

        }
    }

    // get all Node whith value Opposite
    ArrayList<Node> allOppositeNode(Node node) {
        ArrayList<Node> result = new ArrayList<>();
        ArrayList<Node> list = allNodeAround(node);
        int value = node.getColor();
        System.out.println("This node has value:"+ value);
        for (int i = 0; i < list.size(); i++) {
            Node thisNode = list.get(i);
            if (thisNode.getColor() != yellow && thisNode.getColor() != value) {
                result.add(thisNode);
            }
        }
        return result;


    }

    //Lấy 4 Node xung quanh 1 Node
    ArrayList<Node> allNodeAround(Node node) {
        ArrayList<Node> nodes = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();
        if (x - 1 >= 0) {
            nodes.add(table[x - 1][y]);
        }
        if (x + 1 <= table.length - 1) {
            nodes.add(table[x + 1][y]);
        }
        if (y - 1 >= 0) {
            nodes.add(table[x][y - 1]);
        }
        if (y + 1 <= table.length - 1) {
            nodes.add(table[x][y + 1]);
        }
        return nodes;
    }

    /*
     * Check xem một chuỗi liền kề nhau có tiếp xúc với ô có giá trị 0 hay không, nếu có, Graphs được coi là sống
     * Nếu không có Node nào tiếp xúc với 0, Graphs chết.
     * */
    boolean checkGraphsDeath(ArrayList<Node> graphs) {
        for (int i = 0; i < graphs.size(); i++) {
            ArrayList<Node> aroundNode = allNodeAround(graphs.get(i));
            for (int j = 0; j < aroundNode.size(); j++) {
                if (aroundNode.get(j).getColor() == yellow) {
                    return false;
                }
            }
        }
        return true;
    }

  //   From 1 Node, get 1 Graphs with that Node
    void getGraphs(Node node, ArrayList<Node> result) {
        result.add(node);
        node.setVisited(true);
        ArrayList<Node> arround = allNodeAround(node);
        for (int i = 0; i < arround.size(); i++) {
            Node thisNode = arround.get(i);
            Button butt = thisNode.getButton();

            if (!thisNode.isVisited() && thisNode.getColor() == node.getColor()) {
                getGraphs(thisNode, result);
            }
        }
        node.setVisited(false);
    }

    void changeValue(ArrayList<Node> graphs, int value) {
        for (int i = 0; i < graphs.size(); i++) {
            graphs.get(i).setColorButton(value);
        }
    }


}
