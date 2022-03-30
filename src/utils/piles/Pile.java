package utils.piles;

public class Pile  implements PileOnlyReadable{
	private PileLinkedElement first=null;
	private PileLinkedElement last=null;
	
	//MANAGMENT FIFO LIFO ---------------------------------------
	 //First IN first OUT (else Last IN Fist OUT
	private boolean isFifo;
	public Pile(boolean isFifo) {
		this.isFifo=isFifo;
	}
	/**Default type is FIFO*/
	public Pile() {
		this(true);
	}
	
	private int size=0;
	public void push(PileElement coord) {
		if(coord==null) return;
		
		PileLinkedElement el=new PileLinkedElement(coord);
		if(this.first==null) {
			this.first=el;
			this.last=el;
		}
		else {
			el.next=first;
			first.previous=el;
		}
		
		//SIZE and element
		this.first=el;
		this.size++;
	}
	public PileElement pop() {
		if(isFifo) return popLast();
		else return popFirst();
	}
	private PileElement popLast() {
		if(this.last==null) return null;
		
		PileElement ris=this.last.getElement();
		this.removeLast();
		
		if(this.size>0) this.size--;
		return ris;
	}
	private PileElement popFirst() {
		if(this.first==null) return null;
		
		PileElement ris=this.first.getElement();
		this.removeFirst();
		
		if(this.size>0) this.size--;
		return ris;
	}
	
	
	//PILES ONLY READABLE ---------------------------------------
	private PileLinkedElement elementReadablePile=null;
	@Override
	public void restartPileOnlyReadable() {
		elementReadablePile=first;
	}
	@Override
	public PileElement next() {
		if(elementReadablePile==null) return null;
		PileElement ris=elementReadablePile.getElement();
		
		elementReadablePile=elementReadablePile.next;
		return ris;
	}
	
	
	//UTILS  ----------------------------------------------------
	public PileElement getFirst() {
		return first.getElement();
	}
	public void removeLast() {
		if(this.last==null) return;
		
		
		this.last=this.last.previous;
		if(this.last!=null)  this.last.next=null;
	}
	public void removeFirst() {
		if(this.first==null) return;
		
		
		this.first=this.first.next;
		if(this.first!=null)   this.first.previous=null;
	}
	public int getSize() {
		return size;
	}

}
