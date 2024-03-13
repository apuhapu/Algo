#include <iostream>

using namespace std;

int main() {
    long a,b;
    cin>>a>>b;
    long long c = a-b;
    if (c<0) {
        c = -c;
    }
    cout<<c;
}