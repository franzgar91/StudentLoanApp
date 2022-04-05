
public interface Francisco_Garcia_TempConvertable
{
	
	double ABS_ZERO_F = -459.66999999999996;
	double ABS_ZERO_C = -273.15;
	int ABS_ZERO_K=0;
	
	public double convertFtoC(double fTemp);
	public double convertCtoF(double cTemp);
	public double convertCtoK(double cTemp);
	public double convertKtoC(double kTemp);
	public double convertFtoK(double fTemp);
	public double convertKtoF(double kTemp);
	
}
//end class