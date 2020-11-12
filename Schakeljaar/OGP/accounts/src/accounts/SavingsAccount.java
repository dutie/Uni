package accounts;

public class SavingsAccount extends Account {
	/**
	 * @invar | balance >= 0
	 */
	private int balance;
	
	/**
	 * @pre | 0 <= amount
	 * @throws IllegalArgumentException when amount is negative
	 * @post | getBalance() == old(getBalance()) + amount
	 */
	@Override
	public void deposit(int amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
	}
	
	@Override
	public int getBalance() {
		return this.balance;
	}
	/**
	 * @post | result == 0
	 * @post | getBalance() == old(getBalance())
	 */
	@Override
	public int withdraw(int amount) {
		return 0;
	}
	
	public SavingsAccount() {
		// TODO Auto-generated constructor stub
	}
}
