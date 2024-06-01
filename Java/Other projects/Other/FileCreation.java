package Other;

import java.util.List;
import Network.*;

public class FileCreation {
	public static void main(String[] args) {
		List<?>[] results = Network.CalculatePerRoad.main(args);
		List<Road> roads = (List<Road>) results[0];
		List<Intersection> intersections = (List<Intersection>) results[1];
		List<ServiceLocation> servicelocations = Network.ReadData.readServiceLocationsFromFile("Data/Raw/Service Point Locations.csv");
		

		FacilityAllocation facilityallocation = new FacilityAllocation(intersections, roads);
		facilityallocation.assignFacilitiesToIntersections();
		
        Network.LinearData.assignPackages(servicelocations, roads);
		
		Network.CalculatePerRoad.writeRoadsToCSV(roads, "Data/Raw/newedges.csv");
		Network.CalculatePerRoad.writeIntersectionsToCSV(intersections, "Data/Raw/newnodes.csv");
		
		System.out.println("end");
	}
}
