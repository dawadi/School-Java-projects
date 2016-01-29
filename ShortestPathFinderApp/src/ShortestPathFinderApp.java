//Written by Kurt Whalen
//Networking Assignment 7
//4/28/15

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ShortestPathFinderApp 
{

    public static void main(String[] args) 
    {
        String fileName = args[0];
        int root = Integer.parseInt(args[1]);
        AdjacencyList adj = new AdjacencyList(fileName);
        //adj.printAdjacency();
        Map <Node, List<Edge>> adjMap = adj.getAdjacencyList();
        for (Node node : adjMap.keySet())
		{
			List<Edge> list = adjMap.get(node);
			for (ListIterator<Edge> it = list.listIterator(); it.hasNext();)
			{
				Edge edge = it.next();
                                if(edge.start.getAddr() == root) 
                                //I set in node all costs to 999 so root needed to be set to 0
                                {
                                    edge.start.setCost(0);
                                }
                                if((edge.weight + edge.start.getCost()) < edge.end.getCost()) 
                                //if weight + cost < cost of the new node swap cost out
                                //sets new parent also
                                {
                                    edge.end.setCost(edge.weight + edge.start.getCost());
                                    edge.end.setParent(edge.start);
                                }
			}
                        node.setVisitStatus(true); 
                        //once node has run through all edges it is set to visited
		}
        System.out.println("Node    Cost    Next-Hop");
        for(Node node : adjMap.keySet())
        {
            if(node.getAddr() != root)
            {
                if(node.getParent().getAddr() == root)
                {
                    System.out.println(node.getAddr() + "       " + node.getCost() + "       " + node.getAddr());
                }
                else
                {
                    System.out.println(node.getAddr() + "       " + node.getCost() + "       " + node.getParent().getAddr());
                }                      
            }
        }
        //run different loops to get routing table for specific path
    }
}

