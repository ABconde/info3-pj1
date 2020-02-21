import java.util.ArrayList;

/*
    Esta es su clase principal. El unico metodo que debe implementar es
    public String[] solve(Maze maze)
    pero es libre de crear otros metodos y clases en este u otro archivo que desee.
*/
public class Solver{
    private ArrayList<String> routes = new ArrayList<>();
    private Maze maze;
    private int totalMoves = 0;


    public Solver(){
        //Sientase libre de implementar el contructor de la forma que usted lo desee
    }

    public String[] solve(Maze maze){
        //Implemente su metodo aqui. Sientase libre de implementar métodos adicionales
        this.maze = maze;
        generateRoutes(maze.getStartSpace(), "[", 0);
        String[] str = convertArray(routes);
        routes = new ArrayList<>();
        return str;
    }

    public String generateRoutes(int currentPosition, String routeS, int moves){
        routeS = routeS + currentPosition + ", ";

        // if(currentPosition == this.maze.getExitSpace()){
        //     addRoute(routeS);
        //     return null;
        // }

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
                routeS = routeS + route + ", ";
                addRoute(routeS);
                moves--;
                routeS = routeS.substring(0, routeS.length()-3);
                continue;
            }

            if(this.maze.getMaxMoves() == moves){
                return null;
            }
            
            moves++;
            generateRoutes(route, routeS, moves);

        }
        return null;
    }

    public void addRoute(String route){
        route = route.substring(0, route.length()-2) + "]";
        routes.add(route);
    }

    public String[] convertArray(ArrayList<String> arrayList){
        String[] array = new String[arrayList.size()];
        for(int i=0; i < arrayList.size(); i++){
            array[i] = arrayList.get(i);
        }
        return array;
    }


}