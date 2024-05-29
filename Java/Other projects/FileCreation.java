
import java.util.List;
import Network.*;

public class FileCreation {
	public static void main(String[] args) {
		List<?>[] results = Network.CalculatePerRoad.main(args);
		List<Road> roads = (List<Road>) results[0];
		List<Intersection> intersections = (List<Intersection>) results[1];
		
		Network.CalculatePerRoad.writeRoadsToCSV(roads, "Data/Raw/newroads.csv");
		Network.CalculatePerRoad.writeIntersectionsToCSV(intersections, "Data/Raw/newnodes.csv");
		
		System.out.println("end");
	}
}
