package code.logicGates;

public class andGate extends gate {

	@Override
	public boolean evaluateInputSignals() {
		// TODO Auto-generated method stub
		output_value = (input1 && input2);
		return output_value;
	}

}
