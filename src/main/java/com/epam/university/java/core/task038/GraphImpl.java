package com.epam.university.java.core.task038;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphImpl implements Graph {

    private Map<Integer, List<Integer>> adjacencyList;
    private int vertexCount;

    private List<VertexImpl> vertexList;

    /**
     * Constructor for a class.
     * Create new instance of Graph.
     * @param vertexCount - number of vertices
     */
    public GraphImpl(int vertexCount) {
        adjacencyList = new HashMap<>();
        this.vertexCount = vertexCount;
        this.vertexList = new ArrayList<>(vertexCount);
    }

    @Override
    public void createVertex(int id, int x, int y) {
        if (vertexList.size() == vertexCount) {
            throw new IllegalArgumentException();
        }
        vertexList.add(new VertexImpl(id, x, y));
        adjacencyList.put(id, new ArrayList<>());
    }

    @Override
    public void connectVertices(int fromId, int toId) {
        adjacencyList.get(fromId).add(toId);

    }

    public VertexImpl getVertexById(int id) {
        return vertexList.get(id);
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
