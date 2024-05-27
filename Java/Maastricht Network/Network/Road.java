package Network;
public class Road {
    private int V1;
    private int V2;
    private double dist; 
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private String type;  // Type of the road
    private int maxSpeed; // Maximum speed allowed on the road
    private double timeToDrive;  // Time to drive the road at max speed
    private String Square1;
    private String Square2;
    private String SquareMid;
    private double Population;
    
    private double Male;
    private double Female;
    private double Children;
    private double YoungAdults;
    private double Adults;
    private double Old;  
    private double VeryOld; 
    private double Households; 
    private double SingleHouseholds;
    private double MultiHouseholds;
    private double SingleParentHouseholds;
    private double TwoParentHouseholds;
    private double Houses;
    private int HomeOwnershipPercentage;
    private int RentalPercentage;
    private int SocialHousingPercentage;
    private double VacantHouses;
    private int AvgHomeValue;
    private int UrbanizationIndex;
    private int MedianHouseholdIncomeLowBound;
    private int MedianHouseholdIncomeUpperBound;
    private int LowIncomePercentage;
    private int HighIncomePercentage;
    

    // Updated constructor to include type and maxSpeed
    public Road( int V1, int V2, double dist, double x1, double y1, double x2, double y2, String type, int maxSpeed, String Square1, String Square2, String SquareMid) {
        this.V1 = V1;
        this.V2 = V2;
        this.dist = dist;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        this.maxSpeed = maxSpeed;
        this.timeToDrive = timeToDrive();
        this.Square1 = Square1;
        this.Square2 = Square2;
        this.SquareMid = SquareMid;
        
        this.Male = -1;
        this.Female = -1;
        this.Children = -1;
        this.YoungAdults = -1;
        this.Adults = -1;
        this.Old = -1;
        this.VeryOld = -1;
        this.Households = -1;
        this.SingleHouseholds = -1;
        this.MultiHouseholds = -1;
        this.SingleParentHouseholds = -1;
        this.TwoParentHouseholds = -1;
        this.Houses = -1;
        this.HomeOwnershipPercentage = -1;
        this.RentalPercentage = -1;
        this.SocialHousingPercentage = -1;
        this.VacantHouses = -1;
        this.AvgHomeValue = -1;
        this.UrbanizationIndex = -1;
        this.MedianHouseholdIncomeLowBound = -1;
        this.MedianHouseholdIncomeUpperBound = -1;
        this.LowIncomePercentage = -1;
        this.HighIncomePercentage = -1;
    }

    // Getters
    public int getV1() { return V1; }
    public int getV2() { return V2; }
    public double getX1() { return x1; }
    public double getY1() { return y1; }
    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public String getType() { return type; }
    public int getMaxSpeed() { return maxSpeed; }
    public String getSquare1() {return Square1;}
    public String getSquare2() {return Square2;}
    public String getSquareMid() {return SquareMid;}
    public double getPopulation() {return Population;}

    public double getDist() {
        return dist;
    }
    
    public void calcDist() {
    	this.dist = Math.sqrt(Math.pow((this.x2-this.x1), 2) + Math.pow((this.y2-this.y1), 2))/10;
    }

    public double timeToDrive() {
        return dist / (maxSpeed/3.6);
    }
    
    public void setPopulation(double PopPerDist) {
    	this.Population = PopPerDist * dist;
    }
    
    
    
    public void setX1(double x1) {
    	this.x1 = x1;
    }
    
    public void setX2(double x2) {
    	this.x2 = x2;
    }
    
    public void setY1(double y1) {
    	this.y1 = y1;
    }
    
    public void setY2(double y2) {
    	this.y2 = y2;
    }
    
    public void setSquare(String squarename) {
    	this.Square1= squarename;
    }
    
    public double getGradient() {
    	return (y2-y1)/(x2-x1);  
    }
    
    public double getC() {
    	return -1*getGradient()* x1 + y1;
    }
    
    public Road cloneRoad() {
    	Road clone = new Road(this.V1,
        this.V2,
        this.dist,
        this.x1,
        this.y1,
        this.x2,
        this.y2,
        this.type,
        this.maxSpeed,
        this.Square1,
        this.Square2,
        this.SquareMid);
    	
    	return clone;
    }
    
    public double getMale() {return Male;}

    public double getFemale() {return Female;  }

    public double getChildren() {return Children;}

    public double getYoungAdults() {return YoungAdults;}

    public double getAdults() {return Adults;}

    public double getOld() {return Old;}

    public double getVeryOld() {return VeryOld;}

    public double getHouseholds() {return Households;}

    public double getSingleHouseholds() {return SingleHouseholds;}

    public double getMultiHouseholds() {return MultiHouseholds;}

    public double getSingleParentHouseholds() {return SingleParentHouseholds;}

    public double getTwoParentHouseholds() {return TwoParentHouseholds;}

    public double getHouses() {return Houses;}

    public int getHomeOwnershipPercentage() {return HomeOwnershipPercentage;}

    public int getRentalPercentage() {return RentalPercentage;}

    public int getSocialHousingPercentage() {return SocialHousingPercentage;}

    public double getVacantHouses() {return VacantHouses;}

    public int getAvgHomeValue() {return AvgHomeValue;}

    public int getUrbanizationIndex() {return UrbanizationIndex;}

    public int getMedianHouseholdIncomeLowBound() {return MedianHouseholdIncomeLowBound;}

    public int getMedianHouseholdIncomeUpperBound() {return MedianHouseholdIncomeUpperBound;}

    public int getLowIncomePercentage() {return LowIncomePercentage;}

    public int getHighIncomePercentage() {return HighIncomePercentage;}
    
    public void setMale(double male) {
        Male = male;
    }

    public void setFemale(double female) {
        Female = female;
    }

    public void setChildren(double children) {
        Children = children;
    }

    public void setYoungAdults(double youngAdults) {
        YoungAdults = youngAdults;
    }

    public void setAdults(double adults) {
        Adults = adults;
    }

    public void setOld(double old) {
        Old = old;
    }

    public void setVeryOld(double veryOld) {
        VeryOld = veryOld;
    }

    public void setHouseholds(double households) {
        Households = households;
    }

    public void setSingleHouseholds(double singleHouseholds) {
        SingleHouseholds = singleHouseholds;
    }

    public void setMultiHouseholds(double multiHouseholds) {
        MultiHouseholds = multiHouseholds;
    }

    public void setSingleParentHouseholds(double singleParentHouseholds) {
        SingleParentHouseholds = singleParentHouseholds;
    }

    public void setTwoParentHouseholds(double twoParentHouseholds) {
        TwoParentHouseholds = twoParentHouseholds;
    }

    public void setHouses(double houses) {
        Houses = houses;
    }

    public void setHomeOwnershipPercentage(int homeOwnershipPercentage) {
        HomeOwnershipPercentage = homeOwnershipPercentage;
    }

    public void setRentalPercentage(int rentalPercentage) {
        RentalPercentage = rentalPercentage;
    }

    public void setSocialHousingPercentage(int socialHousingPercentage) {
        SocialHousingPercentage = socialHousingPercentage;
    }

    public void setVacantHouses(double vacantHouses) {
        VacantHouses = vacantHouses;
    }

    public void setAvgHomeValue(int avgHomeValue) {
        AvgHomeValue = avgHomeValue;
    }

    public void setUrbanizationIndex(int urbanizationIndex) {
        UrbanizationIndex = urbanizationIndex;
    }

    public void setMedianHouseholdIncomeLowBound(int medianHouseholdIncomeLowBound) {
        MedianHouseholdIncomeLowBound = medianHouseholdIncomeLowBound;
    }

    public void setMedianHouseholdIncomeUpperBound(int medianHouseholdIncomeUpperBound) {
        MedianHouseholdIncomeUpperBound = medianHouseholdIncomeUpperBound;
    }

    public void setLowIncomePercentage(int lowIncomePercentage) {
        LowIncomePercentage = lowIncomePercentage;
    }

    public void setHighIncomePercentage(int highIncomePercentage) {
        HighIncomePercentage = highIncomePercentage;
    }
    
}
