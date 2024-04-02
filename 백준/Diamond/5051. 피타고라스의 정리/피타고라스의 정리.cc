#include <iostream>
#include <vector>
#include <complex>
#include <cmath>

using namespace std;

void fft(vector<complex<double>>& f, bool inv) {
    int n = f.size();
    for (int i = 0; i < n; ++i) {
        int rev = 0;
        for (int j = 1, target = i; j < n; j <<= 1, target >>= 1) {
            rev = (rev << 1) + (target & 1);
        }
        if (rev > i) {
            swap(f[i], f[rev]);
        }
    }
    
    for (int len = 2; len <= n; len <<= 1) {
        double deg = 2 * M_PI / len * (inv ? -1 : 1);
        complex<double> basis(cos(deg), sin(deg));
        for (int start = 0; start < n; start += len) {
            complex<double> w(1, 0);
            for (int offset = 0; offset < len / 2; ++offset) {
                int idx = start + offset;
                complex<double> even = f[idx];
                complex<double> odd = f[idx + len / 2];
                f[idx] = even + odd * w;
                f[idx + len / 2] = even - odd * w;
                w *= basis;
            }
        }
    }
    
    if (inv) {
        for (int i = 0; i < n; ++i) {
            f[i] /= n;
        }
    }
}

int main() {
    long long n;
    cin >> n;
    vector<long long> check(n, 0);
    for (long long i = 1; i < n; ++i) {
        long long idx = (i * i) % n;
        check[idx]++;
    }
    
    int size = 1;
    while (size < 2 * n) {
        size <<= 1;
    }
    
    vector<complex<double>> f(size, complex<double>(0, 0));
    for (int i = 0; i < size; ++i) {
        f[i] = complex<double>(0, 0);
    }
    
    for (int i = 0; i < n; ++i) {
        f[i].real(check[i]);
    }
    
    fft(f, false);
    for (int i = 0; i < size; ++i) {
        f[i] *= f[i];
    }
    fft(f, true);
    
    long long cnt = 0;
    vector<long long> temp(n, 0);
    for (int i = 0; i < n; ++i) {
        temp[(2 * i) % n] += check[i];
    }
    
    for (int i = 0; i < n; ++i) {
        long long c = round(f[i].real()) + round(f[n + i].real());
        cnt += ((c - temp[i]) / 2 + temp[i]) * check[i];
    }
    
    cout << cnt << endl;
    
    return 0;
}