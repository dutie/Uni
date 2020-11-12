package accounts;

public abstract class Account {
	
	public abstract int getBalance();
	/*
	 * @pre | 0 <= amount
	 * @post | getBalance() == old(getBalance()) + amount
	 */
	public abstract void deposit(int amount);
	
	/*
	 * @pre | 0 <= amount
	 * @post | getBalance() == old(getBalance()) - result
	 * @post | 0 <= result
	 * @post | result <= amount
	 */
	public abstract int withdraw(int amount);
	
	
}
