import java.util.List;
import java.util.ArrayList;


public class CalculatePerRoad {
	public static List<Road >main(String[] args) {
		List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
		List<Square> squares = ReadData.readSquaresFromFile("Data/Raw/cbs squares.csv");
		int changeX = 7200/2;
		int changeY = 4620/2;
		
		List<Road> newroads = new ArrayList<>();
		
		for(int i = 0; i < roads.size(); i++ ) {
			Road road = roads.get(i);
			if(road.getType() == "residential" || road.getType()== "service") {
				
				if( road.getSquare1() == road.getSquare2() && road.getSquare2() == road.getSquareMid()) {
					for(int j = 0; j < squares.size(); j++) {
						if(squares.get(j).getCoordinate() == road.getSquare1()) {
							squares.get(j).addRoad(road.getDist());
							break;
						}
					}  
				}
				else if(road.getSquare1() == road.getSquareMid() || road.getSquareMid() == road.getSquare2()){
					Square square = null;
					Square square2 = null;
					
					int temp = 0;
					for(int j = 0; j < squares.size(); j++) {
						if(squares.get(j).getCoordinate() == road.getSquare1()) {
							square = squares.get(j);
							temp++;
							if(temp == 2) {
								break;
							}
						}
						else if(squares.get(j).getCoordinate() == road.getSquare2()) {
							square2 = squares.get(j);
							temp++;
							if(temp == 2) {
								break;
							}
						}
					}
					
					
					newroads.add(splitRoad(road, square, square2));
				}
				else {
					Square square = null;
					Square square2 = null;
					Square square3 = null;
					
					int temp = 0;
					for(int j = 0; j < squares.size(); j++) {
						if(squares.get(j).getCoordinate() == road.getSquare1()) {
							square = squares.get(j);
							temp++;
							if(temp == 3) {
								break;
							}
						}
						else if(squares.get(j).getCoordinate() == road.getSquareMid()) {
							square2 = squares.get(j);
							temp++;
							if(temp == 3) {
								break;
							}
						}
						else if(squares.get(j).getCoordinate() == road.getSquareMid()) {
							square3 = squares.get(j);
							temp++;
							if(temp == 3) {
								break;
							}
						}
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
			   if(roads.get(i).getType() == "residential" || roads.get(i).getType()== "service") {
				   for(int j = 0; j < squares.size(); j++) {
					   if(squares.get(j).getCoordinate() == roads.get(i).getSquare1()) {
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
}
