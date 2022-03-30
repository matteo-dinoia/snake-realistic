package utils.piles;

public class PileLinkedElement { //ALMOST ABSTRACT
	public PileLinkedElement previous;
	public PileLinkedElement next;
	

	PileElement elementInPile;
	public PileElement getElement() {
		return elementInPile;
	}
	public PileLinkedElement(PileElement elementInPile) {
		this.elementInPile=elementInPile;
	}
}
