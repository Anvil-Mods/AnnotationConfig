package ambossmann.annotationconfig.adapters;

public abstract class NumberAdapter<T extends Number> extends AbstractAdapter<T> {
	
	protected final float stepSize;
	protected final int decimalCount;
	protected final T minValue;
	protected final T maxValue;

	protected NumberAdapter(T defaultValue, T minValue, T maxValue, float stepSizeIn, int decimalCountIn) {
		super(defaultValue);
		this.stepSize = stepSizeIn;
		this.decimalCount = decimalCountIn;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public abstract void setValue(Number newValue);

	public byte getByteValue() {
		return getValue().byteValue();
	}

	public short getShortValue() {
		return getValue().shortValue();
	}

	public int getIntValue() {
		return getValue().intValue();
	}

	public long getLongValue() {
		return getValue().longValue();
	}

	public float getFloatValue() {
		return getValue().floatValue();
	}

	public double getDoubleValue() {
		return getValue().doubleValue();
	}
	
	public float getStepSize() {
		return stepSize;
	}
	
	public int getDecimalCount() {
		return decimalCount;
	}
	
}
