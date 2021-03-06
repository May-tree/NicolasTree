/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Functions.Tree.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class EthnicTree {

    public static Tree<String> un = new Tree("un");
    public static HashMap<String, Node<String>> nodeMap = new HashMap<>();
    public static ArrayList<String> leaveList = new ArrayList<>();

    public static void buildMap(Node<String> node) {
        nodeMap.put(node.data, node);
    }

    public static void buildTree() {
        buildMap(un.root);
        Node<String> nl = new Node<>("nl");
        Node<String> hl = new Node<>("hl");
        buildMap(nl);
        buildMap(hl);
        un.root.addChild(nl);
        un.root.addChild(hl);
        leaveList.add("hl");
        leaveList.add("nl");

    }

    public static HashSet<String> leaves(Node<String> node) {
        HashSet<String> leaves = new HashSet<>();
        
        if (node.children.isEmpty()) {
            leaves.add(node.data);
            return leaves;
        }
        List<Node<String>> list = node.children;
        for (Node<String> candiNode : list) {
            if (candiNode.children.isEmpty()) {
                leaves.add(candiNode.data);
            } else {
                leaves.addAll(leaves(candiNode));
            }
        }
        return leaves;
    }

    public static ArrayList<Integer> getSemanticSig(HashSet<String> cateSet) {
        ArrayList<Integer> sig = new ArrayList<>();
        HashSet<String> leaves = new HashSet<>();
        for (String cate : cateSet) {
            Node<String> temp = nodeMap.get(cate);
            leaves.addAll(leaves(temp));
        }
        for (String leaveList1 : leaveList) {
            if (leaves.contains(leaveList1)) {
                sig.add(1);
            } else {
                sig.add(0);
            }
        }
        return sig;
    }
}
