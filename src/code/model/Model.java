package code.model;

import java.util.ArrayList;
import code.logicGates.*;
import code.IO.*;

public class Model {
		
	private int andGateNum = 0;
	private int orGateNum = 0;
	private int xorGateNum = 0;
	private int xnorGateNum = 0;
	private int notGateNum = 0;
	private int norGateNum = 0;
	private int nandGateNum = 0;
	private int inputNum = 0;
	private int outputNum = 0;
	
	private ArrayList<Object> workspaceElements = new ArrayList<Object>();
	
	
	public ArrayList<Object> getWorkspaceElements(){return workspaceElements;}
	
	/*
	 * This method allows for a connection in the circuit to be made.
	 * The convention is that the parent is the element whose output will feed into one of the inputs of the child.
	 */
	public boolean makeCircuitConnection(Object parent, Object child) {
		
		// We are not allowing a child's output to feed into one of its inputs (no feedback)
		if(parent == child) {System.out.println("Can't add connection from child into parent!"); return false;}
		
		if(parent instanceof Gate) {
			
			Gate g = (Gate) parent;
			ArrayList<Object> tree = g.getFamilyTree();
		
			if(tree.contains(child)) {System.out.println("Can't add a connection into predecessor!"); return false;}
		}
		else if(parent.getClass() == Output.class) {
			Output o = (Output) parent;
			ArrayList<Object> tree = o.getFamilyTree();
			if(tree.contains(child)) {System.out.println("Can't add a connection into predecessor!"); return false;}
		}
		
		//We cannot allow an output to function as a parent, because it has only a single input
		if(parent.getClass() == Output.class) { System.out.println("Can't add a connection in which Output is parent!") ;return false; }
		
		// We cannot allow an input to function as a child, because it has only a single output
		if(child.getClass() == Input.class) { System.out.println("Can't add a connection in which Input is child!"); return false; }
		
		// return value on attempt to make connection into the child
		int inputNum = 5;
		
		if(child.getClass() == Output.class) {
			Output out = (Output) child; // we know child is of type Output, so cast to Output to access its class members
			inputNum = out.setInput(parent);
			
			if(inputNum == 0) { return false; } // The output must already contain an input, thus adding parent as input was unsuccessful
			else {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs1(child); // add child as an output
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input1(child);
				}
			}
			
		} // end if
		
		
		else if(child instanceof Gate) {
			Gate childGate = (Gate) child;
			inputNum = childGate.setInput(parent);
			
			if(inputNum == 0) {return false;}
			else if(inputNum == 1) {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs1(child); // add child as an output
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input1(child);
				}
			}
			else if(inputNum == 2) {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs2(child); // add child as an output
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input2(child);
				}
			}
			
			else if(inputNum == 3) {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs1(child); // add child as an output
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input1(child);
				}
			}
		}
		
		return true;
		
	} // end of MakeCircuitConnection
	
	
	// This method propagates the signals in the network from gate to gate over and over again
	// until no more change is happening.  This final state corresponds to the settled state when
	// the outputs have the expected outputs from the inputs through the network.  If any connections
	// in the network are missing (e.g., logic gate missing one of its inputs, output not having an
	// input, input not having an output, etc.) then the method returns false immediately, not having
	// done any signal propagation.
	public boolean evaluateCircuitNetwork() {
		
		// Check to see if any missing connections in the network are present
		// If so, return false immediately and thus not evaluating the network
		for(Object obj : workspaceElements) {
			if(obj instanceof Gate) {
				Gate g = (Gate) obj;
				if(g instanceof notGate && g.getInput1Source() == null) {System.out.println("Missing Input Connections Exist!" + g); return false;}
				if((g.getInput1Source() == null || g.getInput2Source() == null) && !(g instanceof notGate)) {System.out.println("Missing Input Connections Exist!" + g); return false;}
				if(g.getChildren_Inputs1().isEmpty() && g.getChildren_Inputs2().isEmpty()) {System.out.println("Missing Output Connections Exist!"); return false;}
			}
			else if(obj.getClass() == Input.class) {
				Input in = (Input) obj;
				if(in.getInput1Children().isEmpty() && in.getInput2Children().isEmpty()) {System.out.println("An input does not have an output child!"); return false;}
			}
			else if(obj.getClass() == Output.class) {
				Output out = (Output) obj;
				if(out.getInput() == null) {System.out.println("An output does not have an input parent!"); return false;}
			}
		}
		
		// All connections exist, thus continue with evaluation of signals and propagation
		ArrayList<Integer> signalsLastPass = new ArrayList<Integer>();
		ArrayList<Integer> signalsCurrentPass = new ArrayList<Integer>();
		do {
			signalsLastPass.clear();
			for(Integer i: signalsCurrentPass) {
				signalsLastPass.add(i);
			}
			signalsCurrentPass.clear();
			for(Object obj : workspaceElements) {
				if(obj instanceof Gate) {
					Gate g = (Gate) obj;
					int signalToPropagate = g.evaluateInputSignals();
					g.setOutputValue(signalToPropagate);
					signalsCurrentPass.add(new Integer(signalToPropagate));
					ArrayList<Object> children1 = g.getChildren_Inputs1();
					for(Object child : children1) {
						In in = (In) child;
						in.setInput1Value(signalToPropagate);
					}
					ArrayList<Object> children2 = g.getChildren_Inputs2();
					for(Object child : children2) {
						In in = (In) child;
						in.setInput2Value(signalToPropagate);
					}
				}
				else if(obj.getClass() == Input.class) {
					Input in = (Input) obj;
					int signalToPropagate = in.getInputValue();
					signalsCurrentPass.add(new Integer(signalToPropagate));
					ArrayList<Object> children1 = in.getInput1Children();
					for(Object child : children1) {
						In childInput = (In) child;
						childInput.setInput1Value(signalToPropagate);
					}
					ArrayList<Object> children2 = in.getInput2Children();
					for(Object child : children2) {
						In childInput = (In) child;
						childInput.setInput2Value(signalToPropagate);
					}
				}
				
			}
			
			printAllInputAndOutputValuesForAllCircuitElements();
			System.out.println("\n\n");
			
			System.out.println(signalsLastPass + "\n" + signalsCurrentPass);
			
			
		} while(!areListsEqual(signalsLastPass, signalsCurrentPass));
		
		for(Integer i : signalsCurrentPass) {
			if(i.equals(new Integer(-1))){System.out.println("At least one signal is still equal to -1!") ;return false;}
		}
		
		return true;
	}
	
	public static boolean areListsEqual(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		
		if(list1.size() != list2.size()) {return false;}
		for(int i = 0; i < list1.size(); ++i) {
			if(!(list1.get(i).equals(list2.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	public void addObjectToWorkspace(Object obj) {
		workspaceElements.add(obj);
		if(obj instanceof andGate) {
			andGate and = (andGate) obj;
			String id = "and" + Integer.toString(andGateNum); 
			and.setID(id); 
			andGateNum++;
		}
		if(obj instanceof orGate) {
			orGate or = (orGate) obj;
			String id = "or" + Integer.toString(orGateNum); 
			or.setID(id); 
			orGateNum++;
		}
		if(obj instanceof notGate) {
			notGate not = (notGate) obj;
			String id = "not" + Integer.toString(notGateNum); 
			not.setID(id); 
			notGateNum++;
		}
		if(obj instanceof norGate) {
			norGate nor = (norGate) obj;
			String id = "nor" + Integer.toString(norGateNum); 
			nor.setID(id); 
			norGateNum++;
		}
		if(obj instanceof nandGate) {
			nandGate nand = (nandGate) obj;
			String id = "nand" + Integer.toString(nandGateNum); 
			nand.setID(id); 
			nandGateNum++;
		}
		if(obj instanceof xorGate) {
			xorGate xor = (xorGate) obj;
			String id = "xor" + Integer.toString(xorGateNum); 
			xor.setID(id); 
			xorGateNum++;
		}
		if(obj instanceof xnorGate) {
			xnorGate xnor = (xnorGate) obj;
			String id = "xnor" + Integer.toString(xnorGateNum); 
			xnor.setID(id); 
			xnorGateNum++;
		}
		if(obj instanceof Input) {
			Input in = (Input) obj;
			String id = "in" + Integer.toString(inputNum); 
			in.setID(id); 
			inputNum++;
		}
		if(obj instanceof Output) {
			Output out = (Output) obj;
			String id = "out" + Integer.toString(outputNum); 
			out.setID(id); 
			outputNum++;
		}
	}
	
	public void addObjectToWorkspace(String elementType) {
		if(elementType.equals("and")) { 
			andGate and = new andGate();
			String id = "and" + Integer.toString(andGateNum);
			and.setID(id);
			andGateNum++;
			workspaceElements.add(and);	
		}
		if(elementType.equals("or")) { 
			orGate or = new orGate();
			String id = "or" + Integer.toString(orGateNum);
			or.setID(id);
			orGateNum++; 
			workspaceElements.add(or);
		}
		if(elementType.equals("xor")) { 
			xorGate xor = new xorGate();
			String id = "xor" + Integer.toString(xorGateNum);
			xor.setID(id);
			xorGateNum++; 
			workspaceElements.add(xor);
		}
		if(elementType.equals("xnor")) { 
			xnorGate xnor = new xnorGate();
			String id = "xnor" + Integer.toString(xnorGateNum);
			xnor.setID(id);
			xnorGateNum++; 
			workspaceElements.add(xnor);
		}
		if(elementType.equals("not")) { 
			notGate not = new notGate();
			String id = "not" + Integer.toString(notGateNum);
			not.setID(id);
			notGateNum++; 
			workspaceElements.add(not);
		}
		if(elementType.equals("nor")) { 
			norGate nor = new norGate();
			String id = "nor" + Integer.toString(norGateNum);
			nor.setID(id);
			norGateNum++; 
			workspaceElements.add(nor);
		}
		if(elementType.equals("nand")) { 
			nandGate nand = new nandGate();
			String id = "nand" + Integer.toString(nandGateNum);
			nand.setID(id);
			nandGateNum++; 
			workspaceElements.add(nand);
		}
		if(elementType.equals("in")) { 
			Input in = new Input();
			String id = "in" + Integer.toString(inputNum);
			in.setID(id);
			inputNum++; 
			workspaceElements.add(in);
		}
		if(elementType.equals("out")) { 
			Output out = new Output();
			String id = "out" + Integer.toString(outputNum);
			out.setID(id);
			outputNum++; 
			workspaceElements.add(out);
		}
		
	}
	
	public void printAllInputsAndValues() {
		for(Object obj: workspaceElements) {
			if(obj.getClass() == Input.class) {
				Input i = (Input) obj;
				System.out.println("Input Identity:" + i + ", Name: " + i.getID() +  ", Value: " + i.getInputValue());
			}
		}
	}
	
	public void printAllOutputsAndValues() {
		for(Object obj: workspaceElements) {
			if(obj.getClass() == Output.class) {
				Output o = (Output) obj;
				System.out.println("Output Identity:" + o + ", Name: " + o.getID() +  ", Value: " + o.getOutputValue());
			}
		}
	}
	
	public void printAllInputAndOutputValuesForAllCircuitElements() {
		for(Object obj: workspaceElements) {
			if(obj instanceof Gate) {
				Gate g = (Gate) obj;
				if(g instanceof notGate) {
					System.out.println("Identity: " + g + ", Name: " + g.getID() + ", Input_Value: " + g.getInput1Value() + ", Output_Value: " + g.getOutputValue() );
				}
				else {
					System.out.println("Identity: " + g + ", Name: " + g.getID() + ", Input1_Value: " + g.getInput1Value() + ", Input2_Value: " + g.getInput2Value() + ", Output_Value: " + g.getOutputValue() );
				}
			}
			else if(obj instanceof Input) {
				Input in = (Input) obj;
				System.out.println("Identity: " + in + " Name: " + in.getID() + ", Value: " + in.getInputValue());
			}
			else if(obj instanceof Output) {
				Output out = (Output) obj;
				System.out.println("Identity: " + out + " Name: " + out.getID() + ", Value: " + out.getOutputValue());	
			}
		}
	}
	
	public void printAllConnectionsForAllCircuitElements() {
		
		for(Object obj: workspaceElements) {
			if(obj instanceof Gate) {
				Gate g = (Gate) obj;
				if(obj instanceof notGate) {
					System.out.println("Identity: " + g + "Name: " + g.getID() + " InputSource: " + g.getInput1Source() + " Outputs: " + g.getChildren_Inputs1() + " " + g.getChildren_Inputs2());
				}
				else {
					System.out.println("Identity: " + g + "Name: " + g.getID() + " Input1Source: " + g.getInput1Source() + " Input2Source: " + g.getInput2Source() +  " Outputs: " + g.getChildren_Inputs1() + " " + g.getChildren_Inputs2());
				}
			}
			else if(obj instanceof Input) {
				Input in = (Input) obj;
				System.out.println("Identity: " + in + "Name: " + in.getID() + " Outputs: " + in.getInput1Children() + " " + in.getInput2Children());
			}
			else if(obj instanceof Output) {
				Output out = (Output) obj;
				System.out.println("Identity: " + out + "Name: " + out.getID() + "Input: " + out.getInput());
			
			}
		}
		
	}
	
	
	
	

	
}
