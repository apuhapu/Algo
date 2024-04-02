import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static final int max_value = 1_000_001;
	static boolean[] prime;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			int k = (n-1)/2-1;
			int cnt = result[k];
			if (prime[n-4]) { // 세미소수가 4인 경우
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void init() {
		genPrime();
		Complex[] f = new Complex[500_001], g = new Complex[500_001];
		for (int i=0; i<f.length; i++) {
			f[i] = new Complex(0,0);
			g[i] = new Complex(0,0);
		}
		for (int p=3; p<max_value; p++) {
			if (prime[p]) {
				f[(p-1)/2].real = 1;
				if (p-1<=500_000) {
					g[p-1].real = 1;
				}
			}
		}
		polymulti(f,g);
	}
	
	private static void polymulti(Complex[] f, Complex[] g) {
		int n1 = f.length, n2 = g.length, size = 1;
		while (n1+n2 > size) {
			size<<=1;
		}
		Complex[] temp = new Complex[size];
		for (int i=0; i<n1; i++) {
			temp[i] = f[i];
		}
		for (int i=n1; i<size; i++) {
			temp[i] = new Complex(0,0);
		}
		f = temp;
		temp = new Complex[size];
		for (int i=0; i<n2; i++) {
			temp[i] = g[i];
		}
		for (int i=n2; i<size; i++) {
			temp[i] = new Complex(0,0);
		}
		g = temp;
		
		fft(f,false);
		fft(g,false);
		for (int i=0; i<size; i++) {
			f[i] = f[i].multi(g[i]);
		}
		fft(f,true);
		result = new int[size];
		for (int i=0; i<size; i++) {
			result[i] = (int) Math.round(f[i].real);
		}
	}

	private static void fft(Complex[] f, boolean inv) {
		int n = f.length;
		for (int i=0; i<n; i++) {
			int rev = 0;
			for (int j=1, target=i; j<n; j<<=1, target>>=1) {
				rev = (rev<<1) + (target&1);
			}
			if (rev > i) {
				Complex temp = f[i];
				f[i] = f[rev];
				f[rev] = temp;
			}
		}
		for (int len=2; len<=n; len<<=1) {
			double ang = 2*Math.PI/len * (inv?-1:1);
			Complex basis = new Complex(Math.cos(ang), Math.sin(ang));
			for (int start=0; start<n; start+=len) {
				Complex w = new Complex(1,0);
				for (int offset=0; offset<len/2; offset++) {
					int i = start + offset;
					Complex even = f[i];
					Complex odd = f[len/2+i];
					f[i] = even.add(odd.multi(w));
					f[len/2+i] = even.sub(odd.multi(w));
					w = w.multi(basis);
				}
			}
		}
		if (inv) {
			for (int i=0; i<n; i++) {
				f[i] = f[i].scale(n);
			}
		}
	}

	private static void genPrime() {
		prime = new boolean[max_value];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		for (int i=2; i<Math.sqrt(max_value); i++) {
			if (prime[i]) {
				for (int j=i*i; j<max_value; j+=i) {
					prime[j] = false;
				}
			}
		}
	}

	static class Complex {
		double real;
		double imaginary;
		public Complex(double real, double imaginary) {
			this.real = real;
			this.imaginary = imaginary;
		}
		public Complex add(Complex o) {
			return new Complex(this.real+o.real, this.imaginary+o.imaginary);
		}
		public Complex sub(Complex o) {
			return new Complex(this.real-o.real, this.imaginary-o.imaginary);
		}
		public Complex multi(Complex o) {
			return new Complex(this.real*o.real-this.imaginary*o.imaginary, this.real*o.imaginary+this.imaginary*o.real);
		}
		public Complex scale(int n) {
			return new Complex(this.real*(1.0/n), this.imaginary*(1.0/n));
		}
		public Complex conj() {
			return new Complex(this.real, -this.imaginary);
		}
	}
}