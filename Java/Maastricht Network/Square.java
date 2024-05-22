
public class Square {
	private String Coordinate;
    private int Population;
    private int Male;
    private int Female;
    private int Children;
    private int YoungAdults;
    private int Adults;
    private int Old;  
    private int VeryOld; 
    private int Households; 
    private int SingleHouseholds;
    private int MultiHouseholds;
    private int SingleParentHouseholds;
    private int TwoParentHouseholds;
    private int Houses;
    private int HomeOwnershipPercentage;
    private int RentalPercentage;
    private int SocialHousingPercentage;
    private int VacantHouses;
    private int AvgHomeValue;
    private int UrbanizationIndex;
    private int MedianHouseholdIncomeLowBound;
    private int MedianHouseholdIncomeUpperBound;
    private int LowIncomePercentage;
    private int HighIncomePercentage;
    private int distToSupermarket;
    private int X;
    private int Y;


    public Square(String coordinate, int population, int male, int female, int children, int youngAdults, int adults, int old, int veryOld, int households, int singleHouseholds, int multiHouseholds, int singleParentHouseholds, int twoParentHouseholds, int houses, int homeOwnershipPercentage, int rentalPercentage, int socialHousingPercentage, int vacantHouses, int avgHomeValue, int urbanizationIndex, int medianHouseholdIncomeLowBound, int medianHouseholdIncomeUpperBound, int lowIncomePercentage, int highIncomePercentage, int distToSupermarket, int x, int y) {
        this.Coordinate = coordinate;
        this.Population = population;
        this.Male = male;
        this.Female = female;
        this.Children = children;
        this.YoungAdults = youngAdults;
        this.Adults = adults;
        this.Old = old;
        this.VeryOld = veryOld;
        this.Households = households;
        this.SingleHouseholds = singleHouseholds;
        this.MultiHouseholds = multiHouseholds;
        this.SingleParentHouseholds = singleParentHouseholds;
        this.TwoParentHouseholds = twoParentHouseholds;
        this.Houses = houses;
        this.HomeOwnershipPercentage = homeOwnershipPercentage;
        this.RentalPercentage = rentalPercentage;
        this.SocialHousingPercentage = socialHousingPercentage;
        this.VacantHouses = vacantHouses;
        this.AvgHomeValue = avgHomeValue;
        this.UrbanizationIndex = urbanizationIndex;
        this.MedianHouseholdIncomeLowBound = medianHouseholdIncomeLowBound;
        this.MedianHouseholdIncomeUpperBound = medianHouseholdIncomeUpperBound;
        this.LowIncomePercentage = lowIncomePercentage;
        this.HighIncomePercentage = highIncomePercentage;
        this.distToSupermarket = distToSupermarket;
        this.X = x;
        this.Y = y;
    }

    // Getter methods
    public String getCoordinate() {return Coordinate;   }

    public int getPopulation()  {return Population;    }

    public int getMale() {return Male;}

    public int getFemale() {return Female;  }

    public int getChildren() {return Children;}

    public int getYoungAdults() {return YoungAdults;}

    public int getAdults() {return Adults;}

    public int getOld() {return Old;}

    public int getVeryOld() {return VeryOld;}

    public int getHouseholds() {return Households;}

    public int getSingleHouseholds() {return SingleHouseholds;}

    public int getMultiHouseholds() {return MultiHouseholds;}

    public int getSingleParentHouseholds() {return SingleParentHouseholds;}

    public int getTwoParentHouseholds() {return TwoParentHouseholds;}

    public int getHouses() {return Houses;}

    public int getHomeOwnershipPercentage() {return HomeOwnershipPercentage;}

    public int getRentalPercentage() {return RentalPercentage;}

    public int getSocialHousingPercentage() {return SocialHousingPercentage;}

    public int getVacantHouses() {return VacantHouses;}

    public int getAvgHomeValue() {return AvgHomeValue;}

    public int getUrbanizationIndex() {return UrbanizationIndex;}

    public int getMedianHouseholdIncomeLowBound() {return MedianHouseholdIncomeLowBound;}

    public int getMedianHouseholdIncomeUpperBound() {return MedianHouseholdIncomeUpperBound;}

    public int getLowIncomePercentage() {return LowIncomePercentage;}

    public int getHighIncomePercentage() {return HighIncomePercentage;}

    public int getDistToSupermarket() {return distToSupermarket; }

    public int getX() {return X; }

    public int getY() {return Y; }
}

