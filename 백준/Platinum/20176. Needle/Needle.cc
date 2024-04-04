#include <iostream>
#include <cmath>
#include <complex>
#include <vector>

using namespace std;

const int SIZE = 1 << 17;

void fft(vector<complex<double>>& f, bool inv) {
    for (int i = 0; i < SIZE; i++) {
        int rev = 0;
        for (int j = 1, target = i; j < SIZE; j <<= 1, target >>= 1) {
            rev = (rev << 1) + (target & 1);
        }
        if (rev > i) {
            swap(f[i], f[rev]);
        }
    }

    for (int len = 2; len <= SIZE; len <<= 1) {
        double deg = 2 * M_PI / len * (inv ? -1 : 1);
        complex<double> basis(cos(deg), sin(deg));
        for (int start = 0; start < SIZE; start += len) {
            complex<double> w(1, 0);
            for (int offset = 0; offset < len / 2; offset++) {
                int i = start + offset;
                complex<double> even = f[i];
                complex<double> odd = f[i + len / 2];
                f[i] = even + odd * w;
                f[i + len / 2] = even - odd * w;
                w *= basis;
            }
        }
    }

    if (inv) {
        for (int i = 0; i < SIZE; i++) {
            f[i] /= SIZE;
        }
    }
}

void polymulti(vector<complex<double>>& f, vector<complex<double>>& g, vector<int>& result) {
    fft(f, false);
    fft(g, false);
    for (int i = 0; i < SIZE; i++) {
        f[i] *= g[i];
    }
    fft(f, true);

    for (int i = 0; i < SIZE; i++) {
        result[i] = round(f[i].real());
    }
}

int main() {
    vector<complex<double>> f(SIZE), g(SIZE);
    for (int i = 0; i < SIZE; i++) {
        f[i] = complex<double>(0, 0);
        g[i] = complex<double>(0, 0);
    }

    int u;
    cin >> u;
    for (int i = 0; i < u; i++) {
        int x;
        cin >> x;
        x += 30000;
        f[x].real(1);
    }

    int m;
    cin >> m;
    vector<int> mids(m);
    for (int i = 0; i < m; i++) {
        cin >> mids[i];
        mids[i] += 30000;
    }

    int d;
    cin >> d;
    for (int i = 0; i < d; i++) {
        int x;
        cin >> x;
        x += 30000;
        g[x].real(1);
    }

    vector<int> result(SIZE);
    polymulti(f, g, result);

    long long cnt = 0;
    for (int mid : mids) {
        cnt += result[2 * mid];
    }
    cout << cnt << endl;

    return 0;
}