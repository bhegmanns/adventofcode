package de.hegmanns.adventofcode2018.day09;


public class CircleElement {
	private CircleElement START_ELEMENT;
	private CircleElement ancestor;
	private CircleElement successor;
	private int number;

	public static CircleElement createWithoutAncestorAndSuccessor(int number) {
		CircleElement element = new CircleElement();
		element.ancestor = element;
		element.successor = element;
		element.number = number;
		element.START_ELEMENT = element;
		return element;
	}

	public void add(int number) {
		int currentNumber = this.number;


			this.number = number;
			CircleElement currentSuccessor = this.successor;
			CircleElement circleElement = new CircleElement();
			circleElement.START_ELEMENT = this.START_ELEMENT;
			circleElement.number = currentNumber;
			this.successor.ancestor = circleElement;
			this.successor = circleElement;
			circleElement.ancestor = this;
			circleElement.successor = currentSuccessor;
	}

	public int getCurrent() {
		int result = this.number;

		CircleElement successor2 = this.successor;
		this.successor = successor2.successor;
		this.number = successor2.number;
		successor2.successor.ancestor = this;
		successor2.ancestor = this;
		successor2.ancestor = null;
		successor2.successor = null;
		
		return result;
	}

	public CircleElement rotateLeft(int num) {
		CircleElement current = this;
		for (int i = 0; i < num; i++) {
			current = current.ancestor;
		}

		return current;
	}
	
	public CircleElement insert(int number) {
		CircleElement current = this;
		for (int i = 0; i < 1; i++) {
			current = current.successor;
			if (current == this.START_ELEMENT) {
				current = this.START_ELEMENT;
				break;
			}
		}
		
		
		CircleElement circleElement = new CircleElement();
		CircleElement currentSuccessor = current.successor;
		currentSuccessor.ancestor = circleElement;
		circleElement.successor = currentSuccessor;
		current.successor = circleElement;
		circleElement.ancestor = current;
		circleElement.START_ELEMENT = this.START_ELEMENT;
		circleElement.number = number;
		
		return circleElement;
	}

	public CircleElement prepareInsert() {
		CircleElement current = this;
		for (int i = 0; i < 2; i++) {
			current = current.successor;
			if (current == this.START_ELEMENT) {
				current = this.START_ELEMENT.successor;
				break;
			}
		}
		return current;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		CircleElement current = this.START_ELEMENT;

		do {
			if (current == this) {
				buffer.append("(" + current.number + ") ");
			} else {
				buffer.append("" + current.number + " ");
			}
			current = current.successor;
		} while (current != this.START_ELEMENT);
		return buffer.toString();
	}

}
