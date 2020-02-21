import java.util.ArrayList;

/*
    Esta es su clase principal. El unico metodo que debe implementar es
    public String[] solve(Maze maze)
    pero es libre de crear otros metodos y clases en este u otro archivo que desee.
*/
public class Solver{
    private ArrayList<String> routes = new ArrayList<>();
    private Maze maze;
    private ArrayList<Integer> currentRoute = new ArrayList<>();
    private int totalMoves = 0;


    public Solver(){
        //Sientase libre de implementar el contructor de la forma que usted lo desee
    }

    public String[] solve(Maze maze){
        //Implemente su metodo aqui. Sientase libre de implementar métodos adicionales
        this.maze = maze;
        generateRoutes(maze.getStartSpace());
        String[] str = convertArray(routes);
        routes = new ArrayList<>();
        return str;
    }

    public String generateRoutes(int currentPosition){
        currentRoute.add(currentPosition);

        int[] openRoutes = new int[]{    
            this.maze.moveNorth(currentPosition),
            this.maze.moveEast(currentPosition),
            this.maze.moveSouth(currentPosition),
            this.maze.moveWest(currentPosition)
        };

        for(int i = 0; i < openRoutes.length; i++){
            int route = openRoutes[i];

            if(route == currentPosition){
                continue;
            }

            if(route == this.maze.getExitSpace()){
                currentRoute.add(route);
                addRoute();
                totalMoves = 0;
                return null;
            }

            if(this.maze.getMaxMoves() == totalMoves){
                // currentRoute = new ArrayList<>();
                currentRoute.remove(currentRoute.size()-1);
                return null;
            }
            
            
            totalMoves++;
            generateRoutes(route);

        }
        return null;
    }

    public void addRoute(){
        String routeString = "[";
        for(int route : currentRoute){
            routeString = routeString + route + ", ";
        }
        routeString = routeString.substring(0, routeString.length()-2) + "]";
        routes.add(routeString);
        currentRoute = new ArrayList<>();
        // currentRoute.remove(currentRoute.size()-1);
    }

    public String[] convertArray(ArrayList<String> arrayList){
        String[] array = new String[arrayList.size()];
        for(int i=0; i < arrayList.size(); i++){
            array[i] = arrayList.get(i);
        }
        return array;
    }


}