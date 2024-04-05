import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static final int size = 1<<17;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Complex[] f = new Complex[size];
		Complex[] g = new Complex[size];
		for (int i=0; i<size; i++) {
			f[i] = new Complex(0,0);
			g[i] = new Complex(0,0);
		}
		
		int u = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<u; i++) {
			int x = Integer.parseInt(st.nextToken());
			x+=30000;
			f[x] = new Complex(1, 0);
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] mids = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			mids[i] = Integer.parseInt(st.nextToken())+30000;
		}
		
		int d = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<d; i++) {
			int x = Integer.parseInt(st.nextToken());
			x+=30000;
			g[x] = new Complex(1, 0);
		}
		
		int[] result = new int[size];
		polymulti(f,g,result);
		
		long cnt = 0;
		for (int mid : mids) {
			cnt+= result[2*mid];
		}
		System.out.println(cnt);
	}
	
	private static void polymulti(Complex[] f, Complex[] g, int[] result) {
		fft(f,false);
		fft(g,false);
		for (int i=0; i<size; i++) {
			f[i] = f[i].multiply(g[i]);
		}
		fft(f,true);
		
		for (int i=0; i<size; i++) {
			result[i] = Math.round((float) f[i].real);
		}
	}

	private static void fft(Complex[] f, boolean inv) {
		for (int i=0; i<size; i++) {
			int rev = 0;
			for (int j=1, target=i; j<size; j<<=1, target>>=1 ) {
				rev = (rev<<1) + (target&1);
			}
			if (rev > i) {
				Complex swap = f[i];
				f[i] = f[rev];
				f[rev] = swap;
			}
		}
		
		for (int len=2; len<=size; len<<=1) {
			double deg = 2*Math.PI/len*(inv?-1:1);
			Complex basis = new Complex(Math.cos(deg),Math.sin(deg));
			for (int start=0; start<size; start+=len) {
				Complex w = new Complex(1,0);
				for (int offset=0; offset<len/2; offset++) {
					int i = start+offset;
					Complex even = f[i];
					Complex odd = f[i+len/2];
					f[i] = even.add(odd.multiply(w));
					f[i+len/2] = even.sub(odd.multiply(w));
					w = w.multiply(basis);
				}
			}
		}
		
		if (inv) {
			for (int i=0; i<size; i++) {
				f[i] = f[i].divide(size);
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

		public Complex add(Complex addi) {
			return new Complex(this.real+addi.real, this.imaginary+addi.imaginary);
		}
		
		public Complex sub(Complex subi) {
			return new Complex(this.real-subi.real, this.imaginary-subi.imaginary);
		}
		
		Complex multiply(Complex other) {
	        return new Complex(this.real * other.real - this.imaginary * other.imaginary,
	                           this.real * other.imaginary + this.imaginary * other.real);
	    }
		
		Complex divide(double divisor) {
	        return new Complex(this.real / divisor, this.imaginary / divisor);
	    }
	}
}