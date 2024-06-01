package Network;

import java.util.List;

public class LinearData {
     public static void assignPackages(List<ServiceLocation> servicelocations, List<Road> roads) {
    	 
    	 
    	 for(ServiceLocation location : servicelocations) {
    		 for(Road road : roads) {
    			 if(road.getAssignedFacility() == location.getLocationId()) {
    				 location.addtotalKM(road.getDist());
    			 }
    		 }
    	 }
    	 
    	 for(ServiceLocation location : servicelocations) {
    		 for(Road road : roads) {
    			 if(road.getType().equals("residential") || road.getType().equals("service") ||  road.getType().equals("living_street")) {
    				 if(road.getAssignedFacility() == location.getLocationId()) {
    					 road.setPackages( (int) (((location.getTotalDeliveries()+location.getTotalPickups())/location.getTotalKM()) * road.getDist()));
    				 }
    			 }
    		 }
    	 }
     }
     
}
