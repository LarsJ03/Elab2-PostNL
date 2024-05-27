
import java.util.List;
import Network.*;

public class FileCreation {
	public static void main(String[] args) {
		List<Road> roads = Network.CalculatePerRoad.main(args);
		Network.CalculatePerRoad.writeRoadsToCSV(roads, "Data/Raw/newroads.csv");
		System.out.println("end");
	}
}
