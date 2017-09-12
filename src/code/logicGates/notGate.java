package code.logicGates;

public class notGate extends gate {

	@Override
	public boolean evaluateInputSignals() {
		// TODO Auto-generated method stub
		output_value = !input1; 
		return output_value;
	}

}
