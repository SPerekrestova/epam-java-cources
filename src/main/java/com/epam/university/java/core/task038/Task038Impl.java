package com.epam.university.java.core.task038;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Task038Impl implements Task038 {
    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        if (sourceGraph == null || actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (GraphAction action : actions) {
            action.run(sourceGraph);
        }
        return sourceGraph;
    }

    @Override
    public Collection<Vertex> getShortestPath(Graph graph, int startId, int endId) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        GraphImpl myGraph = (GraphImpl) graph;
        Map<Integer, List<Integer>> adjacencyList = myGraph.getAdjacencyList();

        ArrayList<Vertex> path = new ArrayList<>();
        ArrayList<Vertex> resPath = new ArrayList<>();
        List<Integer> first = adjacencyList.get(startId);

        for (int i : first) {
            path.add(myGraph.getVertexById(i));
        }

        resPath.add(myGraph.getVertexById(startId));
        for (Vertex ver : path) {
            List<Integer> buf = myGraph.getAdjacencyList().get(ver.getId());
            if (buf.contains(endId)) {
                resPath.add(ver);
            }
        }

        resPath.add(myGraph.getVertexById(endId));
        if (resPath.size() == 2) {
            if (resPath.get(0).getId() > resPath.get(1).getId()) {
                return Collections.emptyList();
            }
        }
        return resPath;
    }
}
