import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {


	static final int max_value = 1_000_001;
	static boolean[] prime = new boolean[max_value];
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		init();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n == 4) {
				sb.append(1).append("\n");
				continue;
			}
			
			int c = result[n/2-1];
			int cnt = c/2;
			if (c%2 != 0) {
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
		
	}
	
	private static void init() {
		int size = 1<<20;
		genPrime();	
		
		Complex[] f = new Complex[size];
		for (int i=0; i<size; i++) {
			f[i] = new Complex(0,0);
		}
		for (int p=3; p<max_value; p++) {
			if (prime[p]) {
				f[(p-1)/2].real = 1.0;
			}
		}
		
		fft(f, false);
		for (int i=0; i<size; i++) {
			f[i] = f[i].multi(f[i]);
		}
		fft(f, true);
		result = new int[size];
		for (int i=0; i<size; i++) {
			result[i] = (int) Math.round(f[i].real);
		}
	}

	private static void fft(Complex[] s, boolean inv) {
		int n = s.length;
		
		for (int i=0; i<n; i++) {
			int rev = 0;
			for (int j=1, target=i; j<n; j<<=1, target >>= 1) {
				rev = (rev<<1) + (target & 1);
			}
			if (rev>i) {
				Complex swap = s[i];
				s[i] = s[rev];
				s[rev] = swap;				
			}
		}
		
		
		
		for (int len=2; len<=n; len <<= 1) { // 재귀 대신 사용
			double ang = 2*Math.PI / len * (inv?-1:1);
			Complex basis = new Complex(Math.cos(ang), Math.sin(ang));
			for (int start=0; start<n; start+=len) {
				Complex w = new Complex(1,0);
				for (int offset=0; offset<len/2; offset++) {
					int j = start + offset;
					Complex even = s[j];
					Complex odd = s[len/2+j];
					s[j] = even.add(odd.multi(w));
					s[j+len/2] = even.sub(odd.multi(w));
					w = w.multi(basis);
				}
			}
		}
		if (inv) {
			for (int i=0; i<n; i++) {
				s[i] = s[i].scale(n);
			}
		}
	}

	private static void genPrime() {
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		for (int i=2; i<=Math.sqrt(max_value); i++) {
			if (!prime[i]) {
				continue;
			}
			for (int j=i*i; j<max_value; j+=i) {
				prime[j] = false;
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
		public Complex scale(int n) {
			return new Complex(real*(1.0/n), imaginary*(1.0/n));
		}
		public Complex conj() {
			return new Complex(real, -imaginary);
		}
		public Complex sub(Complex subi) {
			return new Complex(real-subi.real, imaginary-subi.imaginary);
		}
		public Complex add(Complex addi) {
			return new Complex(real+addi.real, imaginary+addi.imaginary);
		}
		public Complex multi(Complex o) {
			return new Complex(real*o.real-imaginary*o.imaginary, real*o.imaginary+o.real*imaginary);
		}
	}
}