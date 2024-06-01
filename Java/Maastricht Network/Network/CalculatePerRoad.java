package Network;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CalculatePerRoad {
	public static List<?>[] main(String[] args) {
		List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
		List<Square> squares = ReadData.readSquaresFromFile("Data/Raw/squares.csv");
		List<Intersection> intersections = ReadData.readIntersectionsFromFile("Data/Raw/nodes.csv");
		
		List<Road> newroads = new ArrayList<>();
		
		for(int i = 0; i < roads.size(); i++ ) {
			Road road = roads.get(i);

			if(road.getType().equals("residential") || road.getType().equals("service") || road.getType().equals("living_street")) {
				
				if( road.getSquare1().equals(road.getSquare2()) && road.getSquare2().equals(road.getSquareMid())) {
					for(int j = 0; j < squares.size(); j++) {
						if(squares.get(j).getCoordinate() == road.getSquare1()) {
							squares.get(j).addRoad(road.getDist());
							break;
						}
					}  
					
				}
				else if(road.getSquare1().equals(road.getSquareMid()) || road.getSquareMid().equals(road.getSquare2())){
					Square square = null;
					Square square2 = null;
					

					int temp = 0;
					boolean found = false;
					for(int j = 0; j < squares.size(); j++) {
						if(squares.get(j).getCoordinate().equals(road.getSquare1())) {
							square = squares.get(j);
							temp++;
							if(temp == 2) {
								found = true;
								break;
							}
						}
						else if(squares.get(j).getCoordinate().equals(road.getSquare2())) {
							square2 = squares.get(j);
							temp++;
							if(temp == 2) {
								found = true;
								break;
							}
						}
					}
					if(found == false) {
						continue;
					}
					
					
					newroads.add(splitRoad(road, square, square2, intersections));
				}
				else {
					Square square = null;
					Square square2 = null;
					Square square3 = null;
					
					int temp = 0;
					boolean found = false;
					for(int j = 0; j < squares.size(); j++) {
						if(squares.get(j).getCoordinate().equals( road.getSquare1())) {
							square = squares.get(j);
							temp++;
							if(temp == 3) {
								found = true;
								break;
							}
						}
						else if(squares.get(j).getCoordinate().equals(road.getSquareMid())) {
							square2 = squares.get(j);
							temp++;
							if(temp == 3) {
								found = true;
								break;
							}
						}
						else if(squares.get(j).getCoordinate().equals(road.getSquareMid())) {
							square3 = squares.get(j);
							temp++;
							if(temp == 3) {
								found = true;
								break;
							}
						}
					}
					
					if(found == false) {
						continue;
					}
					
					Road newroad1 = splitRoad(road, square, square2, intersections);
					newroad1.setSquare(road.getSquareMid());
					Road newroad2 = splitRoad2(newroad1, square2, square3, intersections);
					
					newroads.add(newroad1);
					newroads.add(newroad2);
					
				}
			 }
		   }
		   roads.addAll(newroads);
		   
		   for(int i = 0; i < roads.size(); i++ ) {
			   Road road = roads.get(i);
			   if(road.getType().equals("residential") || road.getType().equals("service") ||  road.getType().equals("living_street")) {
				   for(int j = 0; j < squares.size(); j++) {
					   if(squares.get(j).getCoordinate().equals(road.getSquare1())) {
						   Square square = squares.get(j);
						   
						   road.setPopulation((square.getPopulation() / square.getTotalRoad()) * road.getDist());
						   road.setMale((square.getMale() / square.getTotalRoad()) * road.getDist());
						   road.setFemale((square.getFemale() / square.getTotalRoad()) * road.getDist());
						   road.setChildren((square.getChildren() / square.getTotalRoad()) * road.getDist());
						   road.setYoungAdults((square.getYoungAdults() / square.getTotalRoad()) * road.getDist());
						   road.setAdults((square.getAdults() / square.getTotalRoad()) * road.getDist());
						   road.setOld((square.getOld() / square.getTotalRoad()) * road.getDist());
						   road.setVeryOld((square.getVeryOld() / square.getTotalRoad()) * road.getDist());
						   road.setHouseholds((square.getHouseholds() / square.getTotalRoad()) * road.getDist());
						   road.setSingleHouseholds((square.getSingleHouseholds() / square.getTotalRoad()) * road.getDist());
						   road.setMultiHouseholds((square.getMultiHouseholds() / square.getTotalRoad()) * road.getDist());
						   road.setSingleParentHouseholds((square.getSingleParentHouseholds() / square.getTotalRoad()) * road.getDist());
						   road.setTwoParentHouseholds((square.getTwoParentHouseholds() / square.getTotalRoad()) * road.getDist());
						   road.setHouses((square.getHouses() / square.getTotalRoad()) * road.getDist());
						   road.setHomeOwnershipPercentage(square.getHomeOwnershipPercentage());
						   road.setRentalPercentage(square.getRentalPercentage());
						   road.setSocialHousingPercentage(square.getSocialHousingPercentage());
						   road.setVacantHouses((square.getVacantHouses() / square.getTotalRoad()) * road.getDist());
						   road.setAvgHomeValue(square.getAvgHomeValue());
						   road.setUrbanizationIndex(square.getUrbanizationIndex());
						   road.setMedianHouseholdIncomeLowBound(square.getMedianHouseholdIncomeLowBound());
						   road.setMedianHouseholdIncomeUpperBound(square.getMedianHouseholdIncomeUpperBound());
						   road.setLowIncomePercentage(square.getLowIncomePercentage());
						   road.setHighIncomePercentage(square.getHighIncomePercentage());

						   break;
					   }
				   } 
			   }
		   }
		 
		   return new List[]{roads, intersections};
		}
	
	
	public static Road splitRoad(Road road, Square square, Square square2, List<Intersection> intersections) {
		int changeX = 7200/2;
		int changeY = 4620/2;
		
		if(road.getGradient()*(square.getX()+ changeX )+road.getC() <= square.getY()+changeY && road.getGradient()*(square.getX()+ changeX )+road.getC() >= square.getY()-changeY) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(square.getX()+ changeX);
			newroad2.setY1(road.getGradient()*(square.getX()+ changeX )+road.getC());
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(square.getX()+ changeX);
			newroad.setY2(road.getGradient()*(square.getX()+ changeX )+road.getC());
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			newroad.setV2(intersection.getNodeId());
			
			return newroad2;
		}
		else if(road.getGradient()*(square.getX()- changeX )+road.getC() <= square.getY()+changeY && road.getGradient()*(square.getX()- changeX )+road.getC() >= square.getY()-changeY) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(square.getX()- changeX);
			newroad2.setY1(road.getGradient()*(square.getX()- changeX )+road.getC());
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(square.getX()- changeX);
			newroad.setY2(road.getGradient()*(square.getX()- changeX )+road.getC());
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			newroad.setV2(intersection.getNodeId());
			
			return newroad2;
		}
		else if(((square.getY()+ changeY)-1*road.getC())/road.getGradient() <= square.getX()+changeX && ((square.getY()+ changeY)-1*road.getC())/road.getGradient() >= square.getX()-changeX) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(((square.getY()+ changeY)-1*road.getC())/road.getGradient());
			newroad2.setY1(square.getY() + changeY);
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(((square.getY()+ changeY)-1*road.getC())/road.getGradient());
			newroad.setY2(square.getY() + changeY);
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			newroad.setV2(intersection.getNodeId());
			
			return newroad2;
			
		}
		else {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(((square.getY()- changeY)-1*road.getC())/road.getGradient());
			newroad2.setY1(square.getY() - changeY);
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(((square.getY()- changeY)-1*road.getC())/road.getGradient());
			newroad.setY2(square.getY() - changeY);
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			newroad.setV2(intersection.getNodeId());
			
			return newroad2;
		}
		
	}
	
	public static Road splitRoad2(Road road, Square square, Square square2, List<Intersection> intersections) {
		int changeX = 7200/2;
		int changeY = 4620/2;
		
		if(road.getGradient()*(square.getX()+ changeX )+road.getC() <= square.getY()+changeY && road.getGradient()*(square.getX()+ changeX )+road.getC() >= square.getY()-changeY &&
				(intersections.get(intersections.size()-1).getX() != road.getX1() && intersections.get(intersections.size()-1).getY() != road.getY1()) ) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(square.getX()+ changeX +0.0001);
			newroad2.setY1(road.getGradient()*(square.getX()+ changeX )+road.getC());
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(square.getX()+ changeX);
			newroad.setY2(road.getGradient()*(square.getX()+ changeX )+road.getC());
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			newroad.setV2(intersection.getNodeId());
			
			return newroad2;
		}
		else if(road.getGradient()*(square.getX()- changeX )+road.getC() <= square.getY()+changeY && road.getGradient()*(square.getX()- changeX )+road.getC() >= square.getY()-changeY && 
				(intersections.get(intersections.size()-1).getX() != road.getX1() && intersections.get(intersections.size()-1).getY() != road.getY1())) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(square.getX()- changeX - 0.0001);
			newroad2.setY1(road.getGradient()*(square.getX()- changeX )+road.getC());
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(square.getX()- changeX);
			newroad.setY2(road.getGradient()*(square.getX()- changeX )+road.getC());
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			
			return newroad2;
		}
		else if(((square.getY()+ changeY)-1*road.getC())/road.getGradient() <= square.getX()+changeX && ((square.getY()+ changeY)-1*road.getC())/road.getGradient() >= square.getX()-changeX && 
				(intersections.get(intersections.size()-1).getX() != road.getX1() && intersections.get(intersections.size()-1).getY() != road.getY1())) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(((square.getY()+ changeY)-1*road.getC())/road.getGradient());
			newroad2.setY1(square.getY() + changeY + 0.0001);
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(((square.getY()+ changeY)-1*road.getC())/road.getGradient());
			newroad.setY2(square.getY() + changeY);
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			
			return newroad2;
			
		}
		else {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(((square.getY()- changeY)-1*road.getC())/road.getGradient());
			newroad2.setY1(square.getY() - changeY - 0.0001);
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Intersection intersection = new Intersection(intersections.get(intersections.size()-1).getNodeId()+1, newroad2.getX1(), newroad2.getY1(), newroad2.getSquare1());
			intersections.add(intersection);
			newroad2.setV1(intersection.getNodeId());
			
			Road newroad = road;
			newroad.setX2(((square.getY()- changeY)-1*road.getC())/road.getGradient());
			newroad.setY2(square.getY() - changeY);
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			
			return newroad2;
		}
		
	}
	
	public static void writeRoadsToCSV(List<Road> roads, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the CSV header
            writer.append("V1,V2,dist,x1,y1,x2,y2,type,maxSpeed,timeToDrive,Square1,Square2,SquareMid,"
            		+ "Population, Male, Female, Children, YoungAdults, Adults, Old, VeryOld, Households, SingleHouseholds,"
            		+ " MultiHouseholds, SingleParentHouseholds, TwoParentHouseholds, Houses, HomeOwnershipPercentage, RentalPercentage, "
            		+ "SocialHousingPercentage, VacantHouses, AvgHomeValue, UrbanizationIndex, MedianHouseholdIncomeLowBound, MedianHouseholdIncomeUpperBound,"
            		+ " LowIncomePercentage, HighIncomePercentage, AssignedFacility, Packages\n");

            // Write each road's attributes as a CSV row
            for (Road road : roads) {
            	writer.append(String.valueOf(road.getV1())).append(",")
                .append(String.valueOf(road.getV2())).append(",")
                .append(String.valueOf(road.getDist())).append(",")
                .append(String.valueOf(road.getX1())).append(",")
                .append(String.valueOf(road.getY1())).append(",")
                .append(String.valueOf(road.getX2())).append(",")
                .append(String.valueOf(road.getY2())).append(",")
                .append(road.getType()).append(",")
                .append(String.valueOf(road.getMaxSpeed())).append(",")
                .append(String.valueOf(road.timeToDrive())).append(",")
                .append(road.getSquare1()).append(",")
                .append(road.getSquare2()).append(",")
                .append(road.getSquareMid()).append(",")
                .append(String.valueOf(road.getPopulation())).append(",")
                .append(String.valueOf(road.getMale())).append(",")
                .append(String.valueOf(road.getFemale())).append(",")
                .append(String.valueOf(road.getChildren())).append(",")
                .append(String.valueOf(road.getYoungAdults())).append(",")
                .append(String.valueOf(road.getAdults())).append(",")
                .append(String.valueOf(road.getOld())).append(",")
                .append(String.valueOf(road.getVeryOld())).append(",")
                .append(String.valueOf(road.getHouseholds())).append(",")
                .append(String.valueOf(road.getSingleHouseholds())).append(",")
                .append(String.valueOf(road.getMultiHouseholds())).append(",")
                .append(String.valueOf(road.getSingleParentHouseholds())).append(",")
                .append(String.valueOf(road.getTwoParentHouseholds())).append(",")
                .append(String.valueOf(road.getHouses())).append(",")
                .append(String.valueOf(road.getHomeOwnershipPercentage())).append(",")
                .append(String.valueOf(road.getRentalPercentage())).append(",")
                .append(String.valueOf(road.getSocialHousingPercentage())).append(",")
                .append(String.valueOf(road.getVacantHouses())).append(",")
                .append(String.valueOf(road.getAvgHomeValue())).append(",")
                .append(String.valueOf(road.getUrbanizationIndex())).append(",")
                .append(String.valueOf(road.getMedianHouseholdIncomeLowBound())).append(",")
                .append(String.valueOf(road.getMedianHouseholdIncomeUpperBound())).append(",")
                .append(String.valueOf(road.getLowIncomePercentage())).append(",")
                .append(String.valueOf(road.getHighIncomePercentage())).append(",")
                .append(String.valueOf(road.getAssignedFacility())).append(",")
                .append(String.valueOf(road.getPackages())).append("\n")    ;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void writeIntersectionsToCSV(List<Intersection> intersections, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the CSV header
            writer.append("NodeId, X, Y, Square, AssignedFacility\n");

            // Write each road's attributes as a CSV row
            for (Intersection intersection : intersections) {
            	writer.append(String.valueOf(intersection.getNodeId())).append(",")
            	.append(String.valueOf(intersection.getX())).append(",")
            	.append(String.valueOf(intersection.getY())).append(",")
                .append(intersection.getSquare()).append(",")
                .append(String.valueOf(intersection.getAssignedFacility())).append("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
