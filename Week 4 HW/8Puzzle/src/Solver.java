
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class Solver {
    
    public class SearchNode implements Comparable<SearchNode>
    {
        private final Board searchBoard;
        private final int movesMade;
        private final SearchNode previousSearchNode;
        
        public SearchNode(Board board, 
                          int movesToReachBoard, 
                          SearchNode prevSearchNode)
        {
            searchBoard = board;
            movesMade = movesToReachBoard;
            previousSearchNode = prevSearchNode;
        }
        
        public int priority()
        {
            return searchBoard.manhattan() + movesMade;
        }
        
        @Override
        public int compareTo(SearchNode thatSearchNode) {
            return priority() - thatSearchNode.priority();
        }
        
    }
    
    
    private Board initialSearchBoard;
    private Board goalBoard;
    private ArrayList<Board> originalSolution;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial == null)
        {
            throw new NullPointerException();
        }
        
        initialSearchBoard = initial;
        goalBoard = null;
        
        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        originalSolution = new ArrayList<>();
        int pastMoves = originalSolution.size();
    
        SearchNode initialSearchNode = new SearchNode(initialSearchBoard, pastMoves, null);
        priorityQueue.insert(initialSearchNode);
        
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>();
        ArrayList<Board> twinSolution = new ArrayList<>();
        int twinPastMoves = twinSolution.size();
    
        SearchNode twinInitSearchNode = 
                new SearchNode(initialSearchBoard.twin(), twinPastMoves, null);
        twinPriorityQueue.insert(twinInitSearchNode);
        
        do
        {
            goalBoard = solve(priorityQueue, originalSolution);
            
        }while(goalBoard == null && 
                solve(twinPriorityQueue, twinSolution) == null);
        
        
    }
    
    private Board solve(MinPQ<SearchNode> priorityQueue,
                                   ArrayList<Board> solutions)
    {
        SearchNode currentSearch = priorityQueue.delMin();
        Board currentBoard = currentSearch.searchBoard;
        solutions.add(currentBoard);
        
        Iterable<Board> currentBoardNeighbors;
        if (!currentBoard.isGoal())
        {    
            currentBoardNeighbors = currentBoard.neighbors();
            for (Board neighbor : currentBoardNeighbors)
            {
                if(currentSearch.previousSearchNode == null ||
                   !neighbor.equals(currentSearch.previousSearchNode.searchBoard))
                {
                    priorityQueue.insert(
                        new SearchNode(neighbor, solutions.size(), currentSearch));
                }
            }
        }
        else
        {
            return currentBoard;
        }
        
        return null;
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
        return goalBoard != null;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return (isSolvable() ? originalSolution.size()-1 : -1);
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        return originalSolution;
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args)
    {
    
    }
}
