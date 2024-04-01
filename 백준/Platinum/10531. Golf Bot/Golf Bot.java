import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

	static Complex[] input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int maxD = 0;
		int[] ks = new int[n];
		for (int i=0; i<n; i++) {
			int k = Integer.parseInt(br.readLine());
			maxD = Math.max(maxD, k);
			ks[i] = k;
		}
		int m = Integer.parseInt(br.readLine());
		HashSet<Integer> ds = new HashSet<>();
		for (int i=0; i<m; i++) {
			int d = Integer.parseInt(br.readLine());
			ds.add(d);
		}
		input = init(maxD, ks);
		fft(input);
		for (int i=0; i<input.length; i++) {
			input[i] = input[i].multi(input[i]);
		}
		Complex[] inv = ifft(input);
		
		
		int cnt = 0;
		for (int i=0; i<inv.length; i++) {
			Complex c = inv[i];
			int val = (int) Math.round(c.real);
			if (val == 0) {
				continue;
			}
			if (ds.contains(i)) {
				cnt++;
				ds.remove(i);
			}
		}
		System.out.println(cnt);
	}
	
	private static Complex[] ifft(Complex[] F) {
		int n = F.length;
		Complex[] conj = new Complex[n];
		for (int i=0; i<n; i++) {
			conj[i] = F[i].conj();
		}
		fft(conj);
		for (int i=0; i<n; i++) {
			conj[i] = conj[i].conj().scale(n);
		}
		return conj;
	}

	private static void fft(Complex[] f) {
		int n = f.length;
		if (n == 1) {
			return;
		}
		Complex[] evenSub = new Complex[n/2];
		Complex[] oddSub = new Complex[n/2];
		for (int i=0; i<n/2; i++) {
			evenSub[i] = f[2*i];
			oddSub[i] = f[2*i+1];
		}
		
		fft(evenSub);
		fft(oddSub);
		
		Complex basis = new Complex(Math.cos(2*Math.PI/n), Math.sin(2*Math.PI/n));
		Complex w = new Complex(1,0);
		for (int i=0; i<n/2; i++) {
			f[i] = evenSub[i].add(oddSub[i].multi(w));
			f[i + n / 2] = evenSub[i].sub(oddSub[i].multi(w));
			w = w.multi(basis);
		}
	}

	private static Complex[] init(int maxD, int[] ks) {
		int size = 1;
		while (size<=maxD+1) {
			size*=2;
		}
		size*=2;
		Complex[] input = new Complex[size];
		input[0] = new Complex(1,0);
		for (int i=1; i<size; i++) {
			input[i] = new Complex(0, 0);
		}
		for (int idx : ks) {
			input[idx].real = 1;
		}
			
		return input;
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