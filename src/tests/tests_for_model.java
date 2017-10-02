package tests;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import code.model.*;
import code.logicGates.*;
import code.IO.*;

public class tests_for_model {
	
	// Test method public boolean makeCircuitConnection(Object parent, Object child) {}
	@Test public void childEqualsParent(){
		Model m = new Model();
		andGate a1 = new andGate();
		Boolean expected = false;
		Boolean actual = m.makeCircuitConnection(a1, a1);
		assertTrue("",expected == actual);
	}
	
	@Test public void attemptToMakeFeedBackOneLayerDeep(){
		Model m = new Model();
		andGate a1 = new andGate();
		andGate a2 = new andGate();
		m.makeCircuitConnection(a1, a2);
		Boolean actual = m.makeCircuitConnection(a2, a1);
		Boolean expected = false;
		assertTrue("",expected == actual);
	}
	
	@Test public void attemptToMakeFeedBackTwoLayersDeep(){
		Model m = new Model();
		andGate a1 = new andGate();
		andGate a2 = new andGate();
		andGate a3 = new andGate();
		m.makeCircuitConnection(a1, a2);
		m.makeCircuitConnection(a2, a3);
		Boolean actual = m.makeCircuitConnection(a3, a2);
		Boolean expected = false;
		assertTrue("",expected == actual);	
	}
	
	
	// Tests for public boolean areListsEqual(ArrayList<Integer> list1, ArrayList<Integer> list2) {
	@Test public void checkIfTwoEqualListsReturnsTrue(){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(new Integer(5));
		list1.add(new Integer(5));
		list1.add(new Integer(5));
		list1.add(new Integer(7));
		list1.add(new Integer(1));
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(new Integer(5));
		list2.add(new Integer(5));
		list2.add(new Integer(5));
		list2.add(new Integer(7));
		list2.add(new Integer(1));
		boolean expected = true;
		boolean actual = Model.areListsEqual(list1, list2);
		assertTrue("",expected == actual);	
	}
	
	// Tests for public boolean evaluateCircuitNetwork() {
	@Test public void checkIfEvaluateCircuitNetworkEvaluatesCircuitWithoutMissingConnections() {
		Model m = new Model();
		andGate and1 = new andGate();
		Input in1 = new Input();
		Output out1 = new Output();
		andGate and2 = new andGate();
		Input in2 = new Input();
		orGate or1 = new orGate();
		m.addObjectToWorkspace(and1);
		m.addObjectToWorkspace(in1);
		m.addObjectToWorkspace(out1);
		m.addObjectToWorkspace(in2);
		m.addObjectToWorkspace(and2);
		m.addObjectToWorkspace(or1);
		
		m.makeCircuitConnection(in1, and1);
		m.makeCircuitConnection(in1, and2);
		m.makeCircuitConnection(in2, and1);
		m.makeCircuitConnection(in2, and2);
		m.makeCircuitConnection(and1, or1);
		m.makeCircuitConnection(and2, or1);
		m.makeCircuitConnection(or1, out1);
		
		in1.setInputValue(1);
		in2.setInputValue(1);
		
		boolean actual = m.evaluateCircuitNetwork();
		boolean expected = true;
		
		assertTrue("", expected==actual);
	}
	
	// Tests for public boolean evaluateCircuitNetwork() {
		@Test public void checkIfEvaluateCircuitNetworkEvaluatesCircuitWithoutMissingConnections2() {
			Model m = new Model();
			
			Input in0 = new Input();
			Input in1 = new Input();
			Input in2 = new Input();
			
			andGate and0 = new andGate();
			andGate and1 = new andGate();
			andGate and2 = new andGate();
			
			xorGate xor0 = new xorGate();
			
			notGate not0 = new notGate();
			notGate not1 = new notGate();
			
			nandGate nand0 = new nandGate();
			
			orGate or0 = new orGate();
			
			Output out0 = new Output();
			Output out1 = new Output();
			Output out2 = new Output();
			
			m.addObjectToWorkspace(in0);
			m.addObjectToWorkspace(in1);
			m.addObjectToWorkspace(in2);
			
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(and1);
			m.addObjectToWorkspace(and2);
			
			m.addObjectToWorkspace(xor0);
			
			m.addObjectToWorkspace(not0);
			m.addObjectToWorkspace(not1);
			
			m.addObjectToWorkspace(nand0);
			
			m.addObjectToWorkspace(or0);
			
			m.addObjectToWorkspace(out0);
			m.addObjectToWorkspace(out1);
			m.addObjectToWorkspace(out2);
			
			m.makeCircuitConnection(in0, and0);
			m.makeCircuitConnection(in0, and1);
			m.makeCircuitConnection(in0, and2);
			
			m.makeCircuitConnection(in1, and1);
			
			m.makeCircuitConnection(in2, and0);
			
			m.makeCircuitConnection(not0, or0);
			m.makeCircuitConnection(in2, or0);
			
			m.makeCircuitConnection(and0, out0);
			
			m.makeCircuitConnection(and1, and2);
			m.makeCircuitConnection(and1, xor0);
			m.makeCircuitConnection(in2, xor0);
			
			m.makeCircuitConnection(and2, not0);
			m.makeCircuitConnection(and2, out1);
			
			m.makeCircuitConnection(xor0, not1);
			
			m.makeCircuitConnection(not1, nand0);
			
			m.makeCircuitConnection(or0, nand0);
			
			m.makeCircuitConnection(nand0, out2);
			
			
			// Test pattern 000
			in0.setInputValue(0); in1.setInputValue(0); in2.setInputValue(0);
			boolean actual = m.evaluateCircuitNetwork();
			boolean expected = true;
			m.printAllInputsAndValues(); 
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 001
			in0.setInputValue(0); in1.setInputValue(0); in2.setInputValue(1);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues(); 
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 010
			in0.setInputValue(0); in1.setInputValue(1); in2.setInputValue(0);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 011
			in0.setInputValue(0); in1.setInputValue(1); in2.setInputValue(1);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues(); 
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 100
			in0.setInputValue(1); in1.setInputValue(0); in2.setInputValue(0);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 101
			in0.setInputValue(1); in1.setInputValue(0); in2.setInputValue(1);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 110
			in0.setInputValue(1); in1.setInputValue(1); in2.setInputValue(0);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			// Test pattern 111
			in0.setInputValue(1); in1.setInputValue(1); in2.setInputValue(1);
			m.evaluateCircuitNetwork();
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			System.out.println("\n\n\n");
			
			System.out.println(m.makeCircuitConnection(or0, and1));
			System.out.println(m.evaluateCircuitNetwork());
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			m.printAllConnectionsForAllCircuitElements();
			
			
			
			assertTrue("", expected==actual);
		}
		
	
}
