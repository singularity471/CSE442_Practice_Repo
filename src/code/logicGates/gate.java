package code.logicGates;

import java.util.ArrayList;

public abstract class gate {
	
	protected boolean input1;
	protected boolean input2;
	protected boolean input1_ready;
	protected boolean input2_ready;
	protected boolean output_value;
	protected ArrayList<gate> outputs_into_input1 = new ArrayList<gate>(); 
	protected ArrayList<gate> outputs_into_input2 = new ArrayList<gate>();
	
	abstract public boolean evaluateInputSignals();
	
	public void setInput1(boolean newInput) {
		input1 = newInput;
	}
	public void setInput2(boolean newInput) {
		input2 = newInput;
	}
	
	public boolean readyToEvaluateInputSignals() {
		return (input1_ready && input2_ready);
	}
	
	public void set_input1_ready(boolean b) {
		input1_ready = b;
	}
	
	public void set_input2_ready(boolean b) {
		input2_ready = b;
	}
	
	public void addToOutputs_into_input1(gate newOutput) {
		outputs_into_input1.add(newOutput);
	}
	
	public void addToOutputs_into_input2(gate newOutput) {
		outputs_into_input2.add(newOutput);
	}
	
	
	
	
}
