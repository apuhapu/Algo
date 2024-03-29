import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int size = 1;
		while (size < 2*n) {
			size *= 2;
		}
		
		Complex[] a = new Complex[size];
		Complex[] b = new Complex[size];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			int curr1 = Integer.parseInt(st1.nextToken());
			int curr2 = Integer.parseInt(st2.nextToken());
			a[i] = new Complex(curr1, 0);
			a[i+n] = new Complex(curr1, 0);
			b[n-1-i] = new Complex(curr2,0);
		}
		for (int i=2*n; i<size; i++) {
			a[i] = new Complex(0,0);
		}
		for (int i=n; i<size; i++) {
			b[i] = new Complex(0, 0);
		}
		
		fft(a);
		fft(b);
		Complex[] conv = new Complex[size];
		for (int i=0; i<size; i++) {
			conv[i] = a[i].multi(b[i]);
		}
		Complex[] inv = ifft(conv);
		double max = 0.0;
		for (Complex c : inv) {
			max = Math.max(max, c.real);
		}
		System.out.println(Math.round(max));
	}
	
	private static void fft(Complex[] x) {
		int n = x.length;
		if (n == 1) {
			return;
		}
		Complex basis = new Complex(Math.cos(2*Math.PI/n), Math.sin(2*Math.PI/n));
		Complex[] evenSub = new Complex[n/2];
		Complex[] oddSub = new Complex[n/2];
		for (int i=0; i<n/2; i++) {
			evenSub[i] = x[2*i];
			oddSub[i] = x[2*i+1];
		}
		fft(evenSub);
		fft(oddSub);
		
		Complex w = new Complex(1,0);
		for (int i=0; i<n/2; i++) {
			x[i] = evenSub[i].add(oddSub[i].multi(w));
			x[n/2+i] = evenSub[i].sub(oddSub[i].multi(w));
			w = w.multi(basis);
		}
	}
	
	private static Complex[] ifft(Complex[] x) {
		int n = x.length;
		Complex[] conj = new Complex[n];
		for (int i=0; i<n; i++) {
			conj[i] = x[i].conjugate();
		}
		fft(conj);
		for (int i=0; i<n; i++) {
			conj[i] = conj[i].conjugate().scale(n);
		}
		return conj;
	}
	
	static class Complex {
		double real;
		double imaginary;
		
		public Complex(double real, double imaginary) {
			this.real = real;
			this.imaginary = imaginary;
		}

		public Complex add(Complex addi) {
			return new Complex(this.real+addi.real, this.imaginary+addi.imaginary);
		}
		
		public Complex sub(Complex subi) {
			return new Complex(this.real-subi.real, this.imaginary-subi.imaginary);
		}
		
		public Complex multi(Complex mul) {
			double mreal = this.real*mul.real - this.imaginary*mul.imaginary;
			double mimagn = this.real*mul.imaginary + this.imaginary*mul.real;
			return new Complex(mreal, mimagn);
		}
		
		public Complex conjugate() {
			return new Complex(this.real, -this.imaginary);
		}
		
		public Complex scale(int n) {
			return new Complex(this.real*(1.0/n), this.imaginary*(1.0/n));
		}
	}
}