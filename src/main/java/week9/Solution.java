/*
    https://programmers.co.kr/learn/courses/30/lessons/49189
    [프로그래머스][그래프] 가장 먼 노드
 */
package week9;

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        return bfs(n, edge);
    }

    private Node[] getNodeInfo(int n, int[][] edges) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int[] edge : edges) {
            nodes[edge[0] - 1].addEdges(edge[1]);
            nodes[edge[1] - 1].addEdges(edge[0]);
        }

        return nodes;
    }

    private int bfs(int n, int[][] edge) {
        Node[] nodes = getNodeInfo(n, edge);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        nodes[0].setDistance(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int num = nodes[node - 1].getEdge();
            while (num != -1) {
                queue.offer(num);
                nodes[num - 1].setDistance(nodes[node - 1].getDistance() + 1);
                num = nodes[node - 1].getEdge();
            }
        }

        return getMaxDistanceCount(nodes);
    }

    private int getMaxDistanceCount(Node[] nodes) {
        int count = 1;
        Arrays.sort(nodes);
        int distance = nodes[0].getDistance();
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].getDistance() != distance) break;
            count++;
        }

        return count;
    }

}

class Node implements Comparable<Node> {
    private int key;
    private int distance;
    private Queue<Integer> edges;

    Node(int key) {
        this.key = key;
        this.edges = new LinkedList<>();
        this.distance = -1;
    }

    public void addEdges(int num) {
        edges.offer(num);
    }

    public void setDistance(int distance) {
        if (this.distance == -1) {
            this.distance = distance;
        }
    }

    public int getEdge() {
        if (edges.isEmpty()) return -1;
        return edges.poll();
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public int compareTo(Node node) {
        return node.distance - this.distance;
    }
}