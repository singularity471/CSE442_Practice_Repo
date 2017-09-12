package tests;

import code.logicGates.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class gateTests {
	
	
	//Test message

	
	// Tests for AND Gate
	@Test
	public void AND_output_T_for_input_T_T() {
		
		andGate andGate1 = new andGate();
		andGate1.setInput1(true);
		andGate1.setInput2(true);
		boolean expected = true;
		boolean actual = andGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}

	@Test
	public void AND_output_F_for_input_F_T() {
		andGate andGate1 = new andGate();
		andGate1.setInput1(false);
		andGate1.setInput2(true);
		boolean expected = false;
		boolean actual = andGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void AND_output_F_for_input_T_F() {
		andGate andGate1 = new andGate();
		andGate1.setInput1(true);
		andGate1.setInput2(false);
		boolean expected = false;
		boolean actual = andGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void AND_output_F_for_input_F_F() {
		andGate andGate1 = new andGate();
		andGate1.setInput1(false);
		andGate1.setInput2(false);
		boolean expected = false;
		boolean actual = andGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	// Tests for OR Gate
	@Test
	public void OR_output_T_for_input_T_T() {
		orGate orGate1 = new orGate();
		orGate1.setInput1(true);
		orGate1.setInput2(true);
		boolean expected = true;
		boolean actual = orGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void OR_output_T_for_input_F_T() {
		orGate orGate1 = new orGate();
		orGate1.setInput1(false);
		orGate1.setInput2(true);
		boolean expected = true;
		boolean actual = orGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void OR_output_T_for_input_T_F() {
		orGate orGate1 = new orGate();
		orGate1.setInput1(true);
		orGate1.setInput2(false);
		boolean expected = true;
		boolean actual = orGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void OR_output_F_for_input_F_F() {
		orGate orGate1 = new orGate();
		orGate1.setInput1(false);
		orGate1.setInput2(false);
		boolean expected = false;
		boolean actual = orGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	
	// Tests for XOR Gate
	@Test
	public void XOR_output_F_for_input_T_T() {
		xorGate xorGate1 = new xorGate();
		xorGate1.setInput1(true);
		xorGate1.setInput2(true);
		boolean expected = false;
		boolean actual = xorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void XOR_output_T_for_input_F_T() {
		xorGate xorGate1 = new xorGate();
		xorGate1.setInput1(false);
		xorGate1.setInput2(true);
		boolean expected = true;
		boolean actual = xorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void XOR_output_T_for_input_T_F() {
		xorGate xorGate1 = new xorGate();
		xorGate1.setInput1(true);
		xorGate1.setInput2(false);
		boolean expected = true;
		boolean actual = xorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void XOR_output_F_for_input_F_F() {
		xorGate xorGate1 = new xorGate();
		xorGate1.setInput1(false);
		xorGate1.setInput2(false);
		boolean expected = false;
		boolean actual = xorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	
	// Tests for NOT / Inverter Gate
	@Test
	public void NOT_output_T_for_input_F() {
		notGate notGate1 = new notGate();
		notGate1.setInput1(true);
		boolean expected = false;
		boolean actual = notGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
		
	}
	
	@Test
	public void NOT_output_F_for_input_T() {
		notGate notGate1 = new notGate();
		notGate1.setInput1(false);
		boolean expected = true;
		boolean actual = notGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
		
	}
	
	
	// Tests for NOR Gate
	@Test
	public void NOR_output_F_for_input_T_T() {
		norGate norGate1 = new norGate();
		norGate1.setInput1(true);
		norGate1.setInput2(true);
		boolean expected = false;
		boolean actual = norGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + "but got: " + actual, expected == actual);
	}
	
	@Test
	public void NOR_output_F_for_input_F_T() {
		norGate norGate1 = new norGate();
		norGate1.setInput1(false);
		norGate1.setInput2(true);
		boolean expected = false;
		boolean actual = norGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + "but got: " + actual, expected == actual);
	}
	
	@Test
	public void NOR_output_F_for_input_T_F() {
		norGate norGate1 = new norGate();
		norGate1.setInput1(true);
		norGate1.setInput2(false);
		boolean expected = false;
		boolean actual = norGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + "but got: " + actual, expected == actual);
	}
	@Test
	public void NOR_output_T_for_input_F_F() {
		norGate norGate1 = new norGate();
		norGate1.setInput1(false);
		norGate1.setInput2(false);
		boolean expected = true;
		boolean actual = norGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + "but got: " + actual, expected == actual);
	}
	
	// Tests for NAND Gate
	@Test
	public void NAND_output_F_for_input_T_T() {
		nandGate nandGate1 = new nandGate();
		nandGate1.setInput1(true);
		nandGate1.setInput2(true);
		boolean expected = false;
		boolean actual = nandGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void NAND_output_T_for_input_F_T() {
		nandGate nandGate1 = new nandGate();
		nandGate1.setInput1(false);
		nandGate1.setInput2(true);
		boolean expected = true;
		boolean actual = nandGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void NAND_output_T_for_input_T_F() {
		nandGate nandGate1 = new nandGate();
		nandGate1.setInput1(true);
		nandGate1.setInput2(false);
		boolean expected = true;
		boolean actual = nandGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void NAND_output_T_for_input_F_F() {
		nandGate nandGate1 = new nandGate();
		nandGate1.setInput1(false);
		nandGate1.setInput2(false);
		boolean expected = true;
		boolean actual = nandGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	
	// Tests for XNOR Gate
	@Test
	public void XNOR_output_T_for_input_T_T() {
		xnorGate xnorGate1 = new xnorGate();
		xnorGate1.setInput1(true);
		xnorGate1.setInput2(true);
		boolean expected = true;
		boolean actual = xnorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void XNOR_output_F_for_input_F_T() {
		xnorGate xnorGate1 = new xnorGate();
		xnorGate1.setInput1(false);
		xnorGate1.setInput2(true);
		boolean expected = false;
		boolean actual = xnorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void XNOR_output_F_for_input_T_F() {
		xnorGate xnorGate1 = new xnorGate();
		xnorGate1.setInput1(true);
		xnorGate1.setInput2(false);
		boolean expected = false;
		boolean actual = xnorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	
	@Test
	public void XNOR_output_T_for_input_F_F() {
		xnorGate xnorGate1 = new xnorGate();
		xnorGate1.setInput1(false);
		xnorGate1.setInput2(false);
		boolean expected = true;
		boolean actual = xnorGate1.evaluateInputSignals();
		
		assertTrue("We expected: " + expected + " but got: " + actual, expected == actual);
	}
	

}
