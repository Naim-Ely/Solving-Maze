
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;



public class Main
{
    public static char[][] maze;
    public static int startR, startC, finishR, finishC;
    public static ArrayList<String> mazeBuffer;

    public static void intializeMaze(String fileName)
    {
        startR=startC=finishC=finishR=-1;

        mazeBuffer = new ArrayList<String>();
        int numC=0;

        try {
            Scanner sc= new Scanner(new File(fileName));
            while(sc.hasNext())
            {
                String nextLine= sc.nextLine();
                mazeBuffer.add(nextLine);
                if(nextLine.length()>numC)
                numC=nextLine.length();
            }
            
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(fileName+"has an issue");
        }
        int numR=mazeBuffer.size();
        maze= new char[numR][numC];
        for(int r= 0; r<numR;r++)
        {
            String row= mazeBuffer.get(r);
            for(int c=0;c<numC; r++)
            {
               if(row.length()>=c)
               maze[r][c]=row.charAt(c);
               else
               maze[r][c]='*';
               if (maze[r][c]=='S')
               {
                   startR= r;
                   startC=c;

               }
               if (maze[r][c]=='+')
               {
                   finishR=r;
                   finishC=c;
               }

            }

        }
        System.out.println("maze successfully loaded");
    }


    public static void printMaze()
    {
        for(char[] row: maze)
        {
            for(char c: row)
            System.out.print(c);
            System.out.println();
            
        }

        System.out.println();
    }
 public static void main(String[] args)
    { 
        intializeMaze("maze.dat");
        printMaze();
       if(solveMaze(startR, startC)) 
        System.out.println("error, can't solve sorry");
    }
    public static boolean solveMaze(int r, int c)
    {
        if(r<0&&c<0 && r>maze.length&&c>=maze[0].length)
        return false;
        if(maze[r][c]=='+')
        return true;
        if(maze[r][c]!=' '&& maze[r][c]!='#')
        return false;
        maze[r][c]='+';
        if (solveMaze(r-1,c))
        {
            maze[r][c]='#';
            return true;

        }
        try {
            if (solveMaze(r+1,c))
            {
            	
                maze[r][c]='#';
                return true;
                
 
            }
            
        } catch (Exception e) {
            //TODO: handle exception
    
        }
      
        if (solveMaze(r,c-1))
        {
            maze[r][c]='#';
            return true;

            
        }
         if (solveMaze(r,c+1))
        {
            maze[r][c]='#';
            return true;

        }
        return false;

    }
}



