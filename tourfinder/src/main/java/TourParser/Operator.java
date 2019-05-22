package TourParser;

public enum Operator {
	ALPIMARIN("Alpimarin-Tur", new OperatorAlpimarin()),
	CITYBREAK("City Break", new OperatorCityBreak()),
	COCOSTUR("CocosTur", new OperatorCocostur()),
	EXPLORE("Explore", new OperatorExplore()),
	METEORTRAVEL("Meteor Travel", new OperatorMeteortravel());

	private String name;

	private OperatorElement operatorObject;

	Operator(final String name, final OperatorElement operatorObject){
			this.name = name;
			this.operatorObject = operatorObject;
	}

	public String getName() {
		return name;
	}

	public OperatorElement getOperatorObject() {
		return operatorObject;
	}
}
