package Network;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CalculatePerRoad {
	public static List<Road> main(String[] args) {
		List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
		List<Square> squares = ReadData.readSquaresFromFile("Data/Raw/squares.csv");
		
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
					
					
					newroads.add(splitRoad(road, square, square2));
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
					
					Road newroad1 = splitRoad(road, square, square2);
					newroad1.setSquare(road.getSquareMid());
					Road newroad2 = splitRoad(newroad1, square2, square3);
					
					newroads.add(newroad1);
					newroads.add(newroad2);
					
				}
			 }
		   }
		   roads.addAll(newroads);
		   
		   for(int i = 0; i < roads.size(); i++ ) {
			   if(roads.get(i).getType().equals("residential") || roads.get(i).getType().equals("service") ||  roads.get(i).getType().equals("living_street")) {
				   for(int j = 0; j < squares.size(); j++) {
					   if(squares.get(j).getCoordinate().equals(roads.get(i).getSquare1())) {
						   roads.get(i).setPopulation((squares.get(j).getPopulation()/squares.get(j).getTotalRoad()) * roads.get(i).getDist());
						   break;
					   }
				   } 
			   }
		   }
		   return roads;
		}
	
	
	public static Road splitRoad(Road road, Square square, Square square2) {
		int changeX = 7200/2;
		int changeY = 4620/2;
		
		if(road.getGradient()*(square.getX()+ changeX )+road.getC() <= square.getY()+changeY && road.getGradient()*(square.getX()+ changeX )+road.getC() >= square.getY()-changeY) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(square.getX()+ changeX +0.0001);
			newroad2.setY1(road.getGradient()*(square.getX()+ changeX )+road.getC());
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Road newroad = road;
			newroad.setX2(square.getX()+ changeX);
			newroad.setY2(road.getGradient()*(square.getX()+ changeX )+road.getC());
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			
			return newroad2;
		}
		else if(road.getGradient()*(square.getX()- changeX )+road.getC() <= square.getY()+changeY && road.getGradient()*(square.getX()- changeX )+road.getC() >= square.getY()-changeY) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(square.getX()- changeX - 0.0001);
			newroad2.setY1(road.getGradient()*(square.getX()- changeX )+road.getC());
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
			Road newroad = road;
			newroad.setX2(square.getX()- changeX);
			newroad.setY2(road.getGradient()*(square.getX()- changeX )+road.getC());
			newroad.calcDist();
			square.addRoad(newroad.getDist());
			
			return newroad2;
		}
		else if(((square.getY()+ changeY)-1*road.getC())/road.getGradient() <= square.getX()+changeX && ((square.getY()+ changeY)-1*road.getC())/road.getGradient() >= square.getX()-changeX) {
			Road newroad2 = road.cloneRoad();
			newroad2.setX1(((square.getY()+ changeY)-1*road.getC())/road.getGradient());
			newroad2.setY1(square.getY() + changeY + 0.0001);
			newroad2.setSquare(newroad2.getSquare2());
			newroad2.calcDist();
			square2.addRoad(newroad2.getDist());
			
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
            writer.append("V1,V2,dist,x1,y1,x2,y2,type,maxSpeed,timeToDrive,Square1,Square2,SquareMid,Population\n");

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
                      .append(String.valueOf(road.getPopulation())).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
