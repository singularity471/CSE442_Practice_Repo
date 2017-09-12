package code.logicGates;

public class xnorGate extends gate{

	@Override
	public boolean evaluateInputSignals() {
		// TODO Auto-generated method stub
		if (input1 != input2) {
			output_value = false;
			return output_value;
		}
		else {
			output_value = true;
			return output_value;
		}
	}

}
