
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Iterator;

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
            return searchBoard.hamming() + movesMade;
        }
        
        @Override
        public int compareTo(SearchNode thatSearchNode) {
            return priority() - thatSearchNode.priority();
        }
        
    }
    
    
    private Board initialSearchBoard;
    private ArrayList<Board> puzzleSolution;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial == null)
        {
            throw new NullPointerException();
        }
        
        initialSearchBoard = initial;
        puzzleSolution = solve(initialSearchBoard);
        
    }
    
    private ArrayList<Board> solve(Board initialBoard)
    {
        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        ArrayList<Board> solutions = new ArrayList<>();
        int pastMoves = solutions.size();
    
        SearchNode newSearchNode = new SearchNode(initialBoard, pastMoves, null);
        priorityQueue.insert(newSearchNode);
        boolean isGoalFound = initialBoard.isGoal();
         
        SearchNode currentSearch;
        Board currentBoard;
        Iterable<Board> currentBoardNeighbors;
        while(!isGoalFound && !priorityQueue.isEmpty())
        {
            currentSearch = priorityQueue.delMin();
            currentBoard = currentSearch.searchBoard;
            
            solutions.add(currentBoard);
            pastMoves = solutions.size();
            
            if (!currentBoard.isGoal())
            {    
                currentBoardNeighbors = currentBoard.neighbors();
                for (Board neighbor : currentBoardNeighbors)
                {

                    if(currentSearch.previousSearchNode == null ||
                       !neighbor.equals(currentSearch.previousSearchNode.searchBoard) &&
                       !isPresentInQueue(neighbor, priorityQueue) &&
                       !solutions.contains(neighbor))
                    {
                        priorityQueue.insert(
                            new SearchNode(neighbor, pastMoves, currentSearch));
                    }
                }
            }
            else
            {
                return solutions;
            }
        }
        
        return null;
    }
    
    private boolean isPresentInQueue(Board board, MinPQ pq)
    {
        Iterator pqItr = pq.iterator();
        while (pqItr.hasNext())
        {
            SearchNode node = (SearchNode)pqItr.next();
            if(board.equals(node.searchBoard))
            {
                return true;
            }
        }
        return false;
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
        return puzzleSolution != null;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return (puzzleSolution != null ? puzzleSolution.size()-1 : -1);
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        return puzzleSolution;
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args)
    {
    
    }
}
