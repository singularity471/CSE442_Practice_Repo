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
		
		
		// Tests for public boolean evaluateCircuitNetwork() {
		@Test public void makeCircuitWithNoInputs() {
			Model m = new Model();
			
			andGate and0 = new andGate();
			xorGate xor0 = new xorGate();
			notGate not0 = new notGate();
			nandGate nand0 = new nandGate();
			orGate or0 = new orGate();
			Output out0 = new Output();
			
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(not0);
			m.addObjectToWorkspace(nand0);
			m.addObjectToWorkspace(or0);
			m.addObjectToWorkspace(out0);
			
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(and0, or0);
			m.makeCircuitConnection(xor0, or0);
			m.makeCircuitConnection(or0, not0);
			m.makeCircuitConnection(not0, nand0);
			m.makeCircuitConnection(or0, nand0);
			m.makeCircuitConnection(nand0, out0);
		
			boolean actual = m.evaluateCircuitNetwork();
			boolean expected = false;
			m.printAllInputsAndValues(); 
			m.printAllOutputsAndValues();
			
			assertTrue("", expected==actual);
				
		}
		

		// Tests for public boolean evaluateCircuitNetwork() {
		@Test public void makeCircuitWithNoOutputs() {
			Model m = new Model();
			
			andGate and0 = new andGate();
			xorGate xor0 = new xorGate();
			notGate not0 = new notGate();
			nandGate nand0 = new nandGate();
			orGate or0 = new orGate();
			Input in0 = new Input();
			
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(not0);
			m.addObjectToWorkspace(nand0);
			m.addObjectToWorkspace(or0);
			m.addObjectToWorkspace(in0);
			
			
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(and0, or0);
			m.makeCircuitConnection(xor0, or0);
			m.makeCircuitConnection(or0, not0);
			m.makeCircuitConnection(not0, nand0);
			m.makeCircuitConnection(or0, nand0);
			m.makeCircuitConnection(in0, and0);
			m.makeCircuitConnection(in0, and0);
		
			boolean actual = m.evaluateCircuitNetwork();
			boolean expected = false;
			m.printAllInputsAndValues(); 
			m.printAllOutputsAndValues();
			
			assertTrue("", expected==actual);
				
		}
		
		// Tests for public boolean evaluateCircuitNetwork() {
		@Test public void makeCircuitWithNoOutputs_NowAddMisingOutputs() {
			Model m = new Model();
			
			andGate and0 = new andGate();
			xorGate xor0 = new xorGate();
			notGate not0 = new notGate();
			nandGate nand0 = new nandGate();
			orGate or0 = new orGate();
			Input in0 = new Input();
			Output out0 = new Output();
			
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(not0);
			m.addObjectToWorkspace(out0);
			m.addObjectToWorkspace(nand0);
			m.addObjectToWorkspace(or0);
			m.addObjectToWorkspace(in0);
			
			
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(and0, or0);
			m.makeCircuitConnection(xor0, or0);
			m.makeCircuitConnection(or0, not0);
			m.makeCircuitConnection(nand0, out0);
			m.makeCircuitConnection(not0, nand0);
			m.makeCircuitConnection(or0, nand0);
			m.makeCircuitConnection(in0, and0);
			m.makeCircuitConnection(in0, and0);
		
			boolean actual = m.evaluateCircuitNetwork();
			boolean expected = true;
			m.printAllInputsAndValues(); 
			m.printAllOutputsAndValues();
			
			assertTrue("", expected==actual);
				
		}
		
		// Test if removeCircuitElement works for a Gate
		@Test public void checkFeedbackWithReverseConnectionBuilding() {
			Model m = new Model();
			
			andGate and0 = new andGate();
			xorGate xor0 = new xorGate();
			orGate or0 = new orGate();
		
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(or0);
			
			System.out.println(m.makeCircuitConnection(xor0, or0));
			System.out.println(m.makeCircuitConnection(and0, xor0));
			System.out.println(m.makeCircuitConnection(or0, and0));
			
			m.printAllFamilyTrees();
			
			//assertTrue("", expected==actual);
				
		}
		
		
		// Test if removeCircuitElement works for a Gate
		@Test public void connectTwoCircuitsTogetherBuiltSeparatelyAndCheckFamilyTrees() {
			Model m = new Model();
			
			// Circuit 1
			Input in0 = new Input();
			xorGate xor0 = new xorGate();
			andGate and0 = new andGate();
			
			m.addObjectToWorkspace(in0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(and0);
			
			m.makeCircuitConnection(in0, and0);
			m.makeCircuitConnection(xor0, and0);
			
			// Circuit 2
			notGate not0 = new notGate();
			xorGate xor1 = new xorGate();
			andGate and1 = new andGate();
			nandGate nand0 = new nandGate();
			
			m.addObjectToWorkspace(not0);
			m.addObjectToWorkspace(and1);
			m.addObjectToWorkspace(xor1);
			m.addObjectToWorkspace(nand0);
			
			m.makeCircuitConnection(not0, nand0);
			m.makeCircuitConnection(and1, nand0);
			m.makeCircuitConnection(not0, and1);
			m.makeCircuitConnection(xor1, and1);
			
			m.makeCircuitConnection(and0, not0);
			
			m.printAllFamilyTrees();
			
		
			
			
			//assertTrue("", expected==actual);
				
		}
		
		// Test to see if a "degenerate" circuit consisting of just inputs and outputs is possible
		// First we check a network of just one input connected to one output
		// Next we check a network composed of three inputs connected to three outputs.
		@Test public void inputOutputNetworks() {
			
			// First Network
			Model m = new Model();
			Input in0 = new Input();
			Output out0 = new Output();
			m.addObjectToWorkspace(in0); m.addObjectToWorkspace(out0);
			boolean actual1 = m.makeCircuitConnection(in0, out0);
			boolean expected1 = true;
			in0.setInputValue(1);
			boolean actual2 = m.evaluateCircuitNetwork();
			boolean expected2 = true;
			
			// Second Network
			Model m1 = new Model();
			Input in1 = new Input();
			Input in2 = new Input();
			Input in3 = new Input();
			Output out1 = new Output();
			Output out2 = new Output();
			Output out3 = new Output();
			m1.addObjectToWorkspace(in1); m1.addObjectToWorkspace(in2); m1.addObjectToWorkspace(in3);
			m1.addObjectToWorkspace(out1); m1.addObjectToWorkspace(out2); m1.addObjectToWorkspace(out3);
			m1.makeCircuitConnection(in1, out1); m1.makeCircuitConnection(in2, out2); m1.makeCircuitConnection(in3, out3);
			in1.setInputValue(1);
			boolean actual3 = m1.evaluateCircuitNetwork();
			boolean expected3 = true;
			
			assertTrue("", expected1 && actual1 && actual2 && expected2 && actual3 && expected3);
		}
		
		
		// Test if removeCircuitElement works for a Gate
		@Test public void removeGateFromWorkspace() {
			Model m = new Model();
			
			andGate and0 = new andGate();
			xorGate xor0 = new xorGate();
			orGate or0 = new orGate();
			Input in0 = new Input();
			Output out0 = new Output();
			
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(or0);
			m.addObjectToWorkspace(in0);
			m.addObjectToWorkspace(out0);
			
			m.makeCircuitConnection(in0, and0);
			m.makeCircuitConnection(and0, out0);
			m.makeCircuitConnection(or0, and0);
			m.makeCircuitConnection(and0, xor0);
			m.makeCircuitConnection(or0, xor0);

			m.printAllWorkspaceElements();
			
			m.printAllConnectionsForAllCircuitElements();
			
			m.printAllFamilyTrees();
			
			m.removeCircuitElement(and0);
			
			m.printAllConnectionsForAllCircuitElements();
			
			m.printAllWorkspaceElements();
			
			m.printAllFamilyTrees();
			
			System.out.println(and0.getInput1Source());
			System.out.println(and0.getInput2Source());
			System.out.println(and0.getChildren_Inputs1());
			System.out.println(and0.getChildren_Inputs2());
			System.out.println(and0.getFamilyTree());
			
			//assertTrue("", expected==actual);
				
		}
		
		// Tests for public boolean evaluateCircuitNetwork() {
		@Test public void FourBitAdderTest() {
			Model m = new Model();
			
			// Full Adder 0
			Input in0 = new Input(); 		// A0
			Input in1 = new Input(); 		// B0
			Input in2 = new Input(); 		// Cin_0
			xorGate xor0 = new xorGate();
			xorGate xor1 = new xorGate();
			andGate and0 = new andGate();
			andGate and1 = new andGate();
			andGate and2 = new andGate();
			orGate or0 = new orGate();
			orGate or1 = new orGate();  	// Cout_0 ---- feeds into ---> Cin_1
			Output out0 = new Output(); 	// S0
			
			// Full Adder 1
			Input in3 = new Input();		// A1
			Input in4 = new Input();		// B1
			xorGate xor2 = new xorGate();
			xorGate xor3 = new xorGate();
			andGate and3 = new andGate();
			andGate and4 = new andGate();
			andGate and5 = new andGate();
			orGate or2 = new orGate();
			orGate or3 = new orGate();
			Output out1 = new Output();		// S1
			
			// Full Adder 2
			Input in5 = new Input();		// A2
			Input in6 = new Input();		// B2
			xorGate xor4 = new xorGate();
			xorGate xor5 = new xorGate();
			andGate and6 = new andGate();
			andGate and7 = new andGate();
			andGate and8 = new andGate();
			orGate or4 = new orGate();
			orGate or5 = new orGate();
			Output out2 = new Output();		// S2
			
			// Full Adder 3
			Input in7 = new Input();		// A3
			Input in8 = new Input();		// B3
			xorGate xor6 = new xorGate();
			xorGate xor7 = new xorGate();
			andGate and9 = new andGate();
			andGate and10 = new andGate();
			andGate and11 = new andGate();
			orGate or6 = new orGate();
			orGate or7 = new orGate();
			Output out3 = new Output();		// S3
			
			Output out4 = new Output();     //Cout_final
			
			
			// Add circuit elements to workspaceElements array inside model m
			m.addObjectToWorkspace(in0);
			m.addObjectToWorkspace(in1);
			m.addObjectToWorkspace(in2);
			m.addObjectToWorkspace(in3);
			m.addObjectToWorkspace(in4);
			m.addObjectToWorkspace(in5);
			m.addObjectToWorkspace(in6);
			m.addObjectToWorkspace(in7);
			m.addObjectToWorkspace(in8);
			m.addObjectToWorkspace(out0);
			m.addObjectToWorkspace(out1);
			m.addObjectToWorkspace(out2);
			m.addObjectToWorkspace(out3);
			m.addObjectToWorkspace(out4);
			m.addObjectToWorkspace(and0);
			m.addObjectToWorkspace(and1);
			m.addObjectToWorkspace(and2);
			m.addObjectToWorkspace(and3);
			m.addObjectToWorkspace(and4);
			m.addObjectToWorkspace(and5);
			m.addObjectToWorkspace(and6);
			m.addObjectToWorkspace(and7);
			m.addObjectToWorkspace(and8);
			m.addObjectToWorkspace(and9);
			m.addObjectToWorkspace(and10);
			m.addObjectToWorkspace(and11);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(xor1);
			m.addObjectToWorkspace(xor2);
			m.addObjectToWorkspace(xor3);
			m.addObjectToWorkspace(xor4);
			m.addObjectToWorkspace(xor5);
			m.addObjectToWorkspace(xor6);
			m.addObjectToWorkspace(xor7);
			m.addObjectToWorkspace(or0);
			m.addObjectToWorkspace(or1);
			m.addObjectToWorkspace(or2);
			m.addObjectToWorkspace(or3);
			m.addObjectToWorkspace(or4);
			m.addObjectToWorkspace(or5);
			m.addObjectToWorkspace(or6);
			m.addObjectToWorkspace(or7);
			
			// Make circuit connections to connect full adders together to form 4-Bit Adder
			
			//in0 is A0
			//in1 is B0
			//in2 is Cin_0
			m.makeCircuitConnection(in0, xor1);
			m.makeCircuitConnection(in1, xor0);
			m.makeCircuitConnection(in2, xor0);
			m.makeCircuitConnection(in2, and2);
			m.makeCircuitConnection(xor0, xor1);
			m.makeCircuitConnection(xor1, out0);
			m.makeCircuitConnection(in0, and2);
			m.makeCircuitConnection(in1, and0);
			m.makeCircuitConnection(in2, and0);
			m.makeCircuitConnection(in1, and1);
			m.makeCircuitConnection(in0, and1);
			m.makeCircuitConnection(and0, or0);
			m.makeCircuitConnection(and1, or0);
			m.makeCircuitConnection(or0, or1);
			m.makeCircuitConnection(and2, or1); // This line carries Cout_0 & Cin_1
			
			//or1 parent is Cin_1
			//in3 is A1
			//in4 is B1
			m.makeCircuitConnection(in3, xor3);
			m.makeCircuitConnection(in4, xor2);
			m.makeCircuitConnection(or1, xor2);
			m.makeCircuitConnection(or1, and5);
			m.makeCircuitConnection(xor2, xor3);
			m.makeCircuitConnection(xor3, out1);
			m.makeCircuitConnection(in3, and5);
			m.makeCircuitConnection(in4, and3);
			m.makeCircuitConnection(or1, and3);
			m.makeCircuitConnection(in4, and4);
			m.makeCircuitConnection(in3, and4);
			m.makeCircuitConnection(and3, or2);
			m.makeCircuitConnection(and4, or2);
			m.makeCircuitConnection(or2, or3);
			m.makeCircuitConnection(and5, or3); // This line carries Cout_1 & Cin_2
//			
//			//or3 parent is Cin_2
//			//in5 is A2
//			//in6 is B2
			m.makeCircuitConnection(in5, xor5);
			m.makeCircuitConnection(in6, xor4);
			m.makeCircuitConnection(or3, xor4);
			m.makeCircuitConnection(or3, and8);
			m.makeCircuitConnection(xor4, xor5);
			m.makeCircuitConnection(xor5, out2);
			m.makeCircuitConnection(in5, and8);
			m.makeCircuitConnection(in6, and6);
			m.makeCircuitConnection(or3, and6);
			m.makeCircuitConnection(in6, and7);
			m.makeCircuitConnection(in5, and7);
			m.makeCircuitConnection(and6, or4);
			m.makeCircuitConnection(and7, or4);
			m.makeCircuitConnection(or4, or5);
			m.makeCircuitConnection(and8, or5); // This line carries Cout_2 & Cin_3
//			
//			//or5 parent is Cin_3
//			//in7 is A3
//			//in8 is B3
			m.makeCircuitConnection(in7, xor7);
			m.makeCircuitConnection(in8, xor6);
			m.makeCircuitConnection(or5, xor6);
			m.makeCircuitConnection(or5, and11);
			m.makeCircuitConnection(xor6, xor7);
			m.makeCircuitConnection(xor7, out3);
			m.makeCircuitConnection(in7, and11);
			m.makeCircuitConnection(in8, and9);
			m.makeCircuitConnection(or5, and9);
			m.makeCircuitConnection(in8, and10);
			m.makeCircuitConnection(in7, and10);
			m.makeCircuitConnection(and9, or6);
			m.makeCircuitConnection(and10, or6);
			m.makeCircuitConnection(or6, or7);
			m.makeCircuitConnection(and11, or7); // This line carries Cout_3 & Cout_final
			
			m.makeCircuitConnection(or7, out4);
				
			// A: A3 A2 A1 A0
			in7.setInputValue(1); in5.setInputValue(0); in3.setInputValue(1); in0.setInputValue(0);
			
			// B: B3 B2 B1 B0
			in8.setInputValue(0); in6.setInputValue(1); in4.setInputValue(0); in1.setInputValue(1); 
			
			// Carry In
			in2.setInputValue(0);
			
			System.out.println(m.evaluateCircuitNetwork());
			m.printAllInputsAndValues();
			m.printAllOutputsAndValues();
			
			System.out.println();
			
			System.out.println("A3 A2 A1 A0: " + in7.getInputValue() + " " + in5.getInputValue() + " " + 
					in3.getInputValue() + " " + in0.getInputValue() + ", In Decimal: " +
					(in7.getInputValue() * 8 + in5.getInputValue() * 4 + in3.getInputValue() * 2 + in0.getInputValue() * 1));
			
			System.out.println("B3 B2 B1 B0: " + in8.getInputValue() + " " + in6.getInputValue() + " " + 
					in4.getInputValue() + " " + in1.getInputValue() + ", In Decimal: " +
					(in8.getInputValue() * 8 + in6.getInputValue() * 4 + in4.getInputValue() * 2 + in1.getInputValue() * 1));
			
			System.out.println("Cin: " + in2.getInputValue());
			
			System.out.println("S3 S2 S1 S0: " + out3.getOutputValue() + " " + out2.getOutputValue() + " " + 
					out1.getOutputValue() + " " + out0.getOutputValue() + ", In Decimal: " +
					(out3.getOutputValue() * 8 + out2.getOutputValue() * 4 + out1.getOutputValue() * 2 + out0.getOutputValue() * 1));
			
			System.out.println("Cout: " + out4.getOutputValue());

			
			//assertTrue("", expected==actual);
		}
		
		
				
		// Tests for public void removeCircuitElement(Object element) 
		@Test public void removeInputFromWorkspace() {
			Model m = new Model();
			
			// Circuit 1
			Input in0 = new Input();
			xorGate xor0 = new xorGate();
			andGate and0 = new andGate();
			
			m.addObjectToWorkspace(in0);
			m.addObjectToWorkspace(xor0);
			m.addObjectToWorkspace(and0);
			
			m.makeCircuitConnection(in0, and0);
			m.makeCircuitConnection(xor0, and0);
			
			// Circuit 2
			notGate not0 = new notGate();
			xorGate xor1 = new xorGate();
			andGate and1 = new andGate();
			nandGate nand0 = new nandGate();
			
			m.addObjectToWorkspace(not0);
			m.addObjectToWorkspace(and1);
			m.addObjectToWorkspace(xor1);
			m.addObjectToWorkspace(nand0);
			
			m.makeCircuitConnection(not0, nand0);
			m.makeCircuitConnection(and1, nand0);
			m.makeCircuitConnection(not0, and1);
			m.makeCircuitConnection(xor1, and1);
			
			// Connect two circuits together
			m.makeCircuitConnection(and0, not0);
			
			m.printAllConnectionsForAllCircuitElements();
			m.printAllFamilyTrees();
			m.printAllWorkspaceElements();
			
			// Remove Input in0
			m.removeCircuitElement(in0);
			
			m.printAllConnectionsForAllCircuitElements();
			m.printAllFamilyTrees();
			m.printAllWorkspaceElements();
			
			System.out.println(in0.getFamilyTree());
			System.out.println(in0.getInput1Children());
			System.out.println(in0.getInput2Children());
			
		}
		
		// Tests for public void removeCircuitElement(Object element) 
		@Test public void removeOutputFromEmptyMdoel() {
			Model m = new Model();
			Output out0 = new Output();
			m.addObjectToWorkspace(out0);
			System.out.println(m.getWorkspaceElements());
			m.removeCircuitElement(out0);
			System.out.println(m.getWorkspaceElements());
			
		}
		
		// Tests for public void removeCircuitElement(Object element) 
		@Test public void removeOutputFromCircuit() {
			Model m = new Model();
			
			Input in0 = new Input();
			Input in1 = new Input();
			nandGate nand0 = new nandGate();
			xnorGate xnor0 = new xnorGate();
			norGate nor0 = new norGate();
			Output out0 = new Output();
			
			m.addObjectToWorkspace(out0);
			m.addObjectToWorkspace(in0);
			m.addObjectToWorkspace(nand0);
			m.addObjectToWorkspace(xnor0);
			m.addObjectToWorkspace(nor0);
			m.addObjectToWorkspace(in1);
			
			m.makeCircuitConnection(nor0, out0);
			m.makeCircuitConnection(nand0, nor0);
			m.makeCircuitConnection(in0, nand0);
			m.makeCircuitConnection(in1, nand0);
			m.makeCircuitConnection(xnor0, nor0);
			m.makeCircuitConnection(in0, xnor0);
			m.makeCircuitConnection(in1, xnor0);
			
			m.printAllConnectionsForAllCircuitElements();
			m.printAllFamilyTrees();
			m.printAllWorkspaceElements();
			
			System.out.println(out0.getFamilyTree());
			System.out.println(out0.getInput());
			
			m.removeCircuitElement(out0);
			
			m.printAllConnectionsForAllCircuitElements();
			m.printAllFamilyTrees();
			m.printAllWorkspaceElements();
			
			System.out.println(out0.getFamilyTree());
			System.out.println(out0.getInput());
			
			
			
		}
		
		
		
	
}
