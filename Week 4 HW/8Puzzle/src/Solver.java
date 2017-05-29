
import edu.princeton.cs.algs4.MinPQ;
import java.util.Stack;

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
    private Stack<Board> solution;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial == null)
        {
            throw new NullPointerException();
        }
        
        initialSearchBoard = initial;
        solution = null;
        
        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        int pastMoves = 0;
    
        SearchNode initialSearchNode = new SearchNode(initialSearchBoard, pastMoves, null);
        priorityQueue.insert(initialSearchNode);
        
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>();
        int twinPastMoves = 0;
    
        SearchNode twinInitSearchNode = 
                new SearchNode(initialSearchBoard.twin(), twinPastMoves, null);
        twinPriorityQueue.insert(twinInitSearchNode);
        
        do
        {
            solution = solve(priorityQueue);
            
        }while(solution == null && solve(twinPriorityQueue) == null);
        
    }
    
    private Stack<Board> solve(MinPQ<SearchNode> priorityQueue)
    {
        SearchNode currentSearch = priorityQueue.delMin();
        Board currentBoard = currentSearch.searchBoard;
        
        Iterable<Board> currentBoardNeighbors;
        if (!currentBoard.isGoal())
        {    
            currentBoardNeighbors = currentBoard.neighbors();
            for (Board neighbor : currentBoardNeighbors)
            {
                if (currentSearch.previousSearchNode == null ||
                   !neighbor.equals(currentSearch.previousSearchNode.searchBoard))
                {
                    priorityQueue.insert(
                        new SearchNode(neighbor, 
                                currentSearch.movesMade+1,
                                currentSearch));
                }
            }
        }
        else
        {
            return getSolution(currentSearch);
        }
        
        return null;
    }
    
    private Stack<Board> getSolution(SearchNode goalNode)
    {
        Stack<Board> puzzleSolution = new Stack<>();
        while(goalNode.previousSearchNode != null)
        {
            puzzleSolution.push(goalNode.previousSearchNode.searchBoard);
            goalNode = goalNode.previousSearchNode;
        }
        
        return puzzleSolution;
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
        return solution != null;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return (isSolvable() ? solution.size() : -1);
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        return solution;
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args)
    {
    
    }
}
