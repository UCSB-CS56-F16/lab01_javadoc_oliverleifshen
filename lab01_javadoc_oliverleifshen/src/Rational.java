public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
    }

    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }

    public static int lcm(int a, int b) {
	int lcm = a * b / gcd(a,b);
	if (lcm < 0)
	    lcm *= -1;
	return lcm;
    }

    public Rational plus(Rational r) {
	int lcm = lcm(this.denom,r.denom);
	return new Rational(this.num*(lcm/this.denom) +
			    r.num*(lcm/r.denom), lcm);
    }

    public static Rational sum(Rational a, Rational b) {
	int lcm = lcm(a.denom,b.denom);
	return new Rational(a.num*(lcm/a.denom) +
			    b.num*(lcm/b.denom), lcm);
    }

    public Rational minus(Rational r) {
	Rational neg = r.times(new Rational(-1,1));
	return plus(neg);
    }

    public static Rational difference(Rational a, Rational b) {
        Rational neg = b.times(new Rational(-1,1));
	return sum(a,neg);
    }

    public Rational reciprocalOf() {
	if (num == 0)
	    throw new ArithmeticException("Reciprocal of this number is illegal");
	return new Rational(this.denom, this.num);
    }

    public Rational dividedBy(Rational r) {
	return times(r.reciprocalOf());
    }

    public static Rational quotient(Rational a, Rational b) {
	return product(a, b.reciprocalOf());
    }
    
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }

    
}
